package com.gyrofix;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.content.SharedPreferences;
import android.graphics.Path;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;

import java.util.Locale;

/**
 * 新增：全局陀螺仪无障碍服务（v8.1 修复手感参数后的版本）。
 * <p>
 * 在系统无障碍设置中开启本服务后：
 * <ul>
 *   <li>后台持续读取真实陀螺仪数据（TYPE_GYROSCOPE），并把最新角速度写入
 *       {@link #lastPitchRate} / {@link #lastRollRate} 供界面实时回显；</li>
 *   <li>把设备姿态转化为滑动操作：横滚（绕纵轴左右倾斜）→ 左右滑动，俯仰（绕横轴前后倾斜）→ 上下滑动；</li>
 *   <li>通过无障碍 {@link #dispatchGesture(GestureDescription, GestureResultCallback, Handler)}
 *       注入到任意前台应用，让不支持陀螺仪的应用也能用体感操控。</li>
 * </ul>
 * 注入是否生效由应用内开关（{@link #PREF_ENABLED}）实时控制，本身不改变任何系统设置。
 * <p>
 * 手感算法说明（v8.0 无效果的根因已修复）：
 * 旧版死区 0.30 rad/s + 指数衰减 2.0/s，导致稳态累计 = 角速度/2，需要持续倾斜 ≥ 0.9 rad/s
 * 才能触发，正常使用（0.2~0.6 rad/s）永远达不到阈值。
 * 新版改为「死区门控的纯积分 + 单边重置」：死区 0.06 rad/s，累计位移超过 0.30 rad（约 17°）
 * 即触发一次滑动，正常倾斜 0.3 s 左右即可连续输出。
 */
public class GlobalGyroService extends AccessibilityService {

    /** 应用内：是否启用倾斜滑动注入 */
    public static final String PREF_ENABLED = "global_gyro_enabled";
    /** 服务写入：无障碍服务已连接 */
    public static final String PREF_ACTIVE = "global_gyro_active";
    /** 服务写入：当前前台应用包名（用于状态展示） */
    public static final String PREF_TARGET = "global_gyro_target";

    /** 实时回显：最近一次俯仰/横滚角速度（rad/s），仅本进程读取，供主界面展示 */
    public static volatile float lastPitchRate = 0f;
    public static volatile float lastRollRate = 0f;

    private static final String TAG = "GyroFix/GlobalGyro";

    // 倾斜→滑动 手感参数
    private static final double DEAD_ZONE = 0.06;      // rad/s：低于该角速度（约 3.5°/s）视为静止，不累计
    private static final double TRIGGER_RAD = 0.30;    // rad：累计位移达到该值（约 17°）触发一次滑动
    private static final long MIN_INTERVAL_MS = 120;   // 相邻两次滑动的最小间隔，防止连发过密
    private static final float SWIPE_FRACTION = 0.24f; // 滑动距离占屏幕对应边长的比例
    private static final float MIN_SWIPE_DIP = 56f;    // 最小滑动距离（dp），保证小屏可感知

    private SensorManager sensorManager;
    private Sensor gyro;
    private boolean sensorRegistered = false;
    private boolean enabled = false;
    private boolean gestureRunning = false;
    private long lastSensorTs = -1;
    private double accRoll = 0;   // 累计水平位移（横滚，围绕纵轴）
    private double accPitch = 0;  // 累计垂直位移（俯仰，围绕横轴）
    private long lastGestureTime = 0;
    private float density = 1f;
    /** 当前前台应用包名（来自窗口事件，用于注入前的安全过滤与状态展示） */
    private volatile String currentPackage;

    private SharedPreferences prefs;
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    private final SensorEventListener gyroListener = new SensorEventListener() {
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.values.length < 3) {
                return;
            }
            float pitch = event.values[0];
            float roll = event.values[1];
            // 无论注入开关如何，都更新实时回显，方便界面验证数据链路
            lastPitchRate = pitch;
            lastRollRate = roll;
            if (!enabled) {
                return;
            }
            long ts = event.timestamp; // 纳秒
            if (lastSensorTs > 0) {
                double dt = (ts - lastSensorTs) / 1e9;
                if (dt <= 0 || dt > 0.5) {
                    lastSensorTs = ts;
                    return;
                }
                // 死区门控的纯积分；反向倾斜时（符号翻转）重新起算，避免回摆误触发
                integrate(roll, dt, true);
                integrate(pitch, dt, false);
                maybeFireGesture();
            }
            lastSensorTs = ts;
        }
    };

    private final SharedPreferences.OnSharedPreferenceChangeListener prefListener =
            new SharedPreferences.OnSharedPreferenceChangeListener() {
                @Override
                public void onSharedPreferenceChanged(SharedPreferences sp, String key) {
                    if (PREF_ENABLED.equals(key)) {
                        enabled = sp.getBoolean(PREF_ENABLED, false);
                        updateSensing();
                        Log.i(TAG, "开关变化 -> enabled=" + enabled);
                    }
                }
            };

    @Override
    public void onServiceConnected() {
        super.onServiceConnected();
        prefs = getSharedPreferences("data", 0);
        enabled = prefs.getBoolean(PREF_ENABLED, false);
        density = getResources().getDisplayMetrics().density;

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        gyro = sensorManager == null ? null
                : sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        // 无论有无陀螺仪都先注册开关监听，保证之后随时可启用/停用
        prefs.registerOnSharedPreferenceChangeListener(prefListener);
        if (gyro == null) {
            Log.w(TAG, "设备无陀螺仪，全局体感不可用");
            prefs.edit().putBoolean(PREF_ACTIVE, true).apply();
            return;
        }
        updateSensing();
        prefs.edit().putBoolean(PREF_ACTIVE, true).apply();
        Log.i(TAG, "无障碍服务已连接, enabled=" + enabled + ", sensor=" + gyro.getName());
    }

    private void updateSensing() {
        if (gyro == null || sensorManager == null) {
            return;
        }
        if (enabled && !sensorRegistered && Build.VERSION.SDK_INT >= 24) {
            sensorRegistered = sensorManager.registerListener(
                    gyroListener, gyro, SensorManager.SENSOR_DELAY_GAME, mainHandler);
            lastSensorTs = -1;
            accRoll = 0;
            accPitch = 0;
            Log.i(TAG, "注册陀螺仪监听 -> " + sensorRegistered);
        } else if ((!enabled || Build.VERSION.SDK_INT < 24) && sensorRegistered) {
            sensorManager.unregisterListener(gyroListener);
            sensorRegistered = false;
            Log.i(TAG, "注销陀螺仪监听");
        }
    }

    /** 单轴积分：低于死区不累计；符号翻转时重新起算 */
    private void integrate(double rate, double dt, boolean horizontal) {
        if (Math.abs(rate) < DEAD_ZONE) {
            return;
        }
        if (horizontal) {
            if (accRoll != 0 && Math.signum(accRoll) != Math.signum(rate)) {
                accRoll = 0;
            }
            accRoll += rate * dt;
        } else {
            if (accPitch != 0 && Math.signum(accPitch) != Math.signum(rate)) {
                accPitch = 0;
            }
            accPitch += rate * dt;
        }
    }

    /** 手势注入核心：累计位移达到阈值即向前台应用派发一次滑动 */
    private void maybeFireGesture() {
        // 安全护栏：前台为系统 UI 或本应用时不注入
        if (currentPackage != null && isBlockedPackage(currentPackage)) {
            accRoll = 0;
            accPitch = 0;
            return;
        }
        double absRoll = Math.abs(accRoll);
        double absPitch = Math.abs(accPitch);
        if (absRoll < TRIGGER_RAD && absPitch < TRIGGER_RAD) {
            return;
        }
        long now = SystemClock.uptimeMillis();
        if (gestureRunning || now - lastGestureTime < MIN_INTERVAL_MS) {
            return;
        }
        boolean horizontal = absRoll >= absPitch;
        double dominant = horizontal ? accRoll : accPitch;
        boolean fire = fireSwipe(horizontal, Math.signum(dominant));
        // 无论是否派发成功都复位，避免同一方向连续堆叠
        accRoll = 0;
        accPitch = 0;
        if (fire) {
            lastGestureTime = now;
        }
    }

    /**
     * 派发一次滑动手势。
     *
     * @return 是否成功提交给系统
     */
    private boolean fireSwipe(boolean horizontal, double directionSign) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        if (wm == null) {
            return false;
        }
        wm.getDefaultDisplay().getRealMetrics(dm);
        float screenW = dm.widthPixels;
        float screenH = dm.heightPixels;
        // 滑动方向约定：横滚向右倾斜(roll>0) -> 向右滑；俯仰向前倾(pitch>0) -> 向上滑
        float axis = horizontal ? screenW : screenH;
        float length = Math.max(axis * SWIPE_FRACTION, MIN_SWIPE_DIP * density);

        float cx = screenW / 2f;
        float cy = screenH / 2f;
        float delta = length * (float) directionSign;
        float ex = horizontal ? cx + delta : cx;
        float ey = horizontal ? cy : cy - delta; // 屏幕 y 轴向下，向上滑用负号

        Path path = new Path();
        path.moveTo(cx, cy);
        path.lineTo(ex, ey);

        long duration = Math.max(60L, Math.min(160L, (long) (length * 0.30f)));
        GestureDescription.StrokeDescription stroke =
                new GestureDescription.StrokeDescription(path, 0, duration);
        GestureDescription gesture = new GestureDescription.Builder().addStroke(stroke).build();

        gestureRunning = true;
        boolean submitted = dispatchGesture(gesture, new GestureResultCallback() {
            @Override
            public void onCompleted(GestureDescription gestureDescription) {
                gestureRunning = false;
                Log.i(TAG, "手势注入完成");
            }

            @Override
            public void onCancelled(GestureDescription gestureDescription) {
                gestureRunning = false;
                Log.i(TAG, "手势注入被系统取消");
            }
        }, mainHandler);
        if (!submitted) {
            gestureRunning = false;
            Log.w(TAG, "手势注入提交失败");
        }
        return submitted;
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (event == null) {
            return;
        }
        CharSequence pkg = event.getPackageName();
        if (pkg == null) {
            return;
        }
        String name = pkg.toString();
        currentPackage = name; // 记录原始前台包名，供注入前过滤
        if (isBlockedPackage(name)) {
            return;
        }
        String current = prefs == null ? null : prefs.getString(PREF_TARGET, null);
        if (!name.equals(current) && prefs != null) {
            prefs.edit().putString(PREF_TARGET, name).apply();
        }
    }

    /** 基础版安全护栏：跳过系统界面与自身应用，避免在系统 UI 上误注入 */
    private boolean isBlockedPackage(String pkg) {
        if (pkg == null || pkg.isEmpty()) {
            return true;
        }
        if (pkg.equals(getPackageName())) {
            return true;
        }
        String lower = pkg.toLowerCase(Locale.ROOT);
        return lower.startsWith("android.")
                || lower.startsWith("com.android.");
    }

    @Override
    public void onInterrupt() {
    }

    @Override
    public boolean onUnbind(android.content.Intent intent) {
        cleanup();
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        cleanup();
        super.onDestroy();
    }

    private void cleanup() {
        if (sensorRegistered && sensorManager != null) {
            sensorManager.unregisterListener(gyroListener);
            sensorRegistered = false;
        }
        if (prefs != null) {
            prefs.unregisterOnSharedPreferenceChangeListener(prefListener);
            prefs.edit().putBoolean(PREF_ACTIVE, false).apply();
        }
    }
}