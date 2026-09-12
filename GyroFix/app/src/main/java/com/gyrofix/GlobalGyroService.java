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
 * 新增：全局陀螺仪无障碍服务（v8.4：滑动脉冲 + 高频率 + 异步并发）。
 * <p>
 * 方案沿袭 v8.2 的「滑动脉冲」思路（累计位移达到阈值 → 从屏幕中心派发一次滑动），
 * 并针对 v8.3 拖动链「难用、无序」的问题做三处增强（功耗不在本轮考虑，先求效果）：
 * <ul>
 *   <li><b>更高触发频率</b>：阈值降至 0.16 rad，最小间隔降至 50ms，且触发后采用
 *       「消去式累积」（扣除本次阈值，保留残余角度），持续倾斜时输出更密集连续；</li>
 *   <li><b>异步并发</b>：不再等上一个手势完成后才派发下一个，改为「在途手势计数」，
 *       最多允许 2 个手势在系统队列中并行/排队执行（{@link #MAX_IN_FLIGHT}），
 *       输出节奏不再被单手势时长阻塞；</li>
 *   <li><b>保留既有增强</b>：200Hz 采样（联动原版采样率滑杆）、低通滤波去抖、
 *       幅度映射滑动长度（倾斜越猛滑得越远越快）、灵敏度滑杆实时可调、前台包安全过滤。</li>
 * </ul>
 */
public class GlobalGyroService extends AccessibilityService {

    /** 应用内：是否启用倾斜滑动注入 */
    public static final String PREF_ENABLED = "global_gyro_enabled";
    /** 应用内：体感灵敏度（int，10~250，除以 100 得倍率） */
    public static final String PREF_GAIN = "global_gyro_gain";
    /** 服务写入：无障碍服务已连接 */
    public static final String PREF_ACTIVE = "global_gyro_active";
    /** 服务写入：当前前台应用包名（用于状态展示） */
    public static final String PREF_TARGET = "global_gyro_target";

    /** 实时回显：最近一次俯仰/横滚角速度（rad/s，已平滑），仅本进程读取，供主界面展示 */
    public static volatile float lastPitchRate = 0f;
    public static volatile float lastRollRate = 0f;

    private static final String TAG = "GyroFix/GlobalGyro";

    // ---- 手感参数 ----
    private static final double DEAD_ZONE = 0.05;        // rad/s：低于该角速度视为静止
    private static final double TRIGGER_RAD = 0.16;      // rad：累计位移达到该值（约 9°）触发一次滑动
    private static final long MIN_INTERVAL_MS = 50;      // 相邻两次滑动的最小间隔（提高频率）
    private static final int MAX_IN_FLIGHT = 2;          // 异步并发：最多同时在途的手势数
    private static final float SWIPE_FRACTION = 0.24f;   // 基础滑动距离占屏幕对应边长的比例
    private static final float MIN_SWIPE_DIP = 48f;      // 最小滑动距离（dp），保证小屏可感知
    private static final float MAX_SWIPE_FACTOR = 1.8f;  // 滑动长度随倾斜幅度的放大上限
    private static final float SMOOTH_RESPONSE = 30f;    // 低通滤波响应速率（/s），滤除手抖更精准
    private static final int SAMPLE_RATE_DEFAULT = 200;  // 默认采样率 Hz（联动原版采样率滑杆）
    public static final int GAIN_DEFAULT = 100;          // 默认灵敏度 1.0x

    private SensorManager sensorManager;
    private Sensor gyro;
    private boolean sensorRegistered = false;
    private boolean enabled = false;
    private long lastSensorTs = -1;
    private double accRoll = 0;   // 累计水平位移（横滚，围绕纵轴）
    private double accPitch = 0;  // 累计垂直位移（俯仰，围绕横轴）
    private long lastGestureTime = 0;
    /** 异步并发：已提交但尚未完成的手势数 */
    private int inFlight = 0;
    private float density = 1f;
    private int sampleRateHz = SAMPLE_RATE_DEFAULT;
    private float gain = 1f;
    /** 低通滤波后的角速度（rad/s），比原始值更平滑精准 */
    private float smoothPitch = 0f;
    private float smoothRoll = 0f;
    private boolean smoothInit = false;
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
            long ts = event.timestamp; // 纳秒
            double dt = -1;
            if (lastSensorTs > 0) {
                dt = (ts - lastSensorTs) / 1e9;
            }
            lastSensorTs = ts;
            if (dt <= 0 || dt > 0.5) {
                return;
            }
            // 一阶低通滤波：滤除手部抖动，输出更平滑精准
            float alpha = (float) Math.min(0.9, Math.max(0.05, dt * SMOOTH_RESPONSE));
            if (!smoothInit) {
                smoothPitch = event.values[0];
                smoothRoll = event.values[1];
                smoothInit = true;
            } else {
                smoothPitch += (event.values[0] - smoothPitch) * alpha;
                smoothRoll += (event.values[1] - smoothRoll) * alpha;
            }
            // 无论注入开关如何，都更新实时回显，方便界面验证数据链路
            lastPitchRate = smoothPitch;
            lastRollRate = smoothRoll;
            if (!enabled) {
                return;
            }
            // 死区门控的纯积分；反向倾斜时（符号翻转）重新起算，避免回摆误触发
            integrate(smoothRoll, dt, true);
            integrate(smoothPitch, dt, false);
            maybeFireGesture();
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
                    } else if (MainActivity.SampleRatePref.equals(key)) {
                        // 联动原版采样率设置：改采样率后按新周期重新注册监听
                        sampleRateHz = sp.getInt(MainActivity.SampleRatePref, SAMPLE_RATE_DEFAULT);
                        if (sensorRegistered) {
                            reRegisterListener();
                        }
                        Log.i(TAG, "采样率变化 -> " + sampleRateHz + "Hz");
                    } else if (PREF_GAIN.equals(key)) {
                        gain = sp.getInt(PREF_GAIN, GAIN_DEFAULT) / 100f;
                        Log.i(TAG, "灵敏度变化 -> " + gain);
                    }
                }
            };

    @Override
    public void onServiceConnected() {
        super.onServiceConnected();
        prefs = getSharedPreferences("data", 0);
        enabled = prefs.getBoolean(PREF_ENABLED, false);
        sampleRateHz = prefs.getInt(MainActivity.SampleRatePref, SAMPLE_RATE_DEFAULT);
        gain = prefs.getInt(PREF_GAIN, GAIN_DEFAULT) / 100f;
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

    private int samplingPeriodUs() {
        return sampleRateHz > 200 ? SensorManager.SENSOR_DELAY_FASTEST : 1000000 / Math.max(1, sampleRateHz);
    }

    private void updateSensing() {
        if (gyro == null || sensorManager == null) {
            return;
        }
        if (enabled && !sensorRegistered && Build.VERSION.SDK_INT >= 24) {
            sensorRegistered = sensorManager.registerListener(
                    gyroListener, gyro, samplingPeriodUs(), mainHandler);
            lastSensorTs = -1;
            accRoll = 0;
            accPitch = 0;
            smoothInit = false;
            Log.i(TAG, "注册陀螺仪监听(" + sampleRateHz + "Hz) -> " + sensorRegistered);
        } else if ((!enabled || Build.VERSION.SDK_INT < 24) && sensorRegistered) {
            sensorManager.unregisterListener(gyroListener);
            sensorRegistered = false;
            Log.i(TAG, "注销陀螺仪监听");
        }
    }

    /** 采样率变化后按新周期重新注册（先注销再注册） */
    private void reRegisterListener() {
        if (sensorManager == null || gyro == null || !sensorRegistered) {
            return;
        }
        sensorManager.unregisterListener(gyroListener);
        sensorRegistered = sensorManager.registerListener(
                gyroListener, gyro, samplingPeriodUs(), mainHandler);
        lastSensorTs = -1;
        Log.i(TAG, "按新采样率重新注册 -> " + sensorRegistered);
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

    /** 手势注入核心：累计位移达到阈值即派发一次滑动；采用「消去式」累积提升连续倾斜时的频率 */
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
        if (now - lastGestureTime < MIN_INTERVAL_MS) {
            return;
        }
        if (inFlight >= MAX_IN_FLIGHT) {
            return; // 异步并发已达上限：让系统队列先消化
        }
        boolean horizontal = absRoll >= absPitch;
        double dominant = horizontal ? accRoll : accPitch;
        // 幅度精度匹配：以当前平滑角速度的强弱决定滑动长度
        float strength = horizontal ? Math.abs(smoothRoll) : Math.abs(smoothPitch);
        boolean fire = fireSwipe(horizontal, Math.signum(dominant), strength);
        // 消去式累积：主导轴扣除本次阈值（保留残余角加速下一发），副轴清零
        if (horizontal) {
            accRoll -= Math.signum(dominant) * TRIGGER_RAD;
            accPitch = 0;
        } else {
            accPitch -= Math.signum(dominant) * TRIGGER_RAD;
            accRoll = 0;
        }
        if (fire) {
            lastGestureTime = now;
        }
    }

    /**
     * 派发一次滑动手势（异步：不等待完成即返回，由 inFlight 计数控制并发上限）。
     *
     * @param strength 当前平滑角速度绝对值（rad/s），决定滑动长度，实现幅度精度匹配
     * @return 是否成功提交给系统
     */
    private boolean fireSwipe(boolean horizontal, double directionSign, float strength) {
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
        float factor = Math.min(MAX_SWIPE_FACTOR,
                Math.max(0.35f, strength / 1.2f + 0.5f)); // 幅度越大滑得越长
        float length = Math.max(axis * SWIPE_FRACTION * factor * gain, MIN_SWIPE_DIP * density);
        length = Math.min(length, axis * SWIPE_FRACTION * MAX_SWIPE_FACTOR * Math.max(1f, gain));

        float cx = screenW / 2f;
        float cy = screenH / 2f;
        float delta = length * (float) directionSign;
        float ex = horizontal ? cx + delta : cx;
        float ey = horizontal ? cy : cy - delta; // 屏幕 y 轴向下，向上滑用负号

        Path path = new Path();
        path.moveTo(cx, cy);
        path.lineTo(ex, ey);

        // 倾斜越猛，手势滑动越快（时长越短），跟手不拖沓
        long duration = Math.max(40L,
                Math.min(120L, (long) (length * 0.22f / Math.max(0.6f, strength))));
        GestureDescription.StrokeDescription stroke =
                new GestureDescription.StrokeDescription(path, 0, duration);
        GestureDescription gesture = new GestureDescription.Builder().addStroke(stroke).build();

        inFlight++;
        boolean submitted = dispatchGesture(gesture, new GestureResultCallback() {
            @Override
            public void onCompleted(GestureDescription gestureDescription) {
                inFlight = Math.max(0, inFlight - 1);
            }

            @Override
            public void onCancelled(GestureDescription gestureDescription) {
                inFlight = Math.max(0, inFlight - 1);
                Log.i(TAG, "手势被系统取消");
            }
        }, mainHandler);
        if (!submitted) {
            inFlight = Math.max(0, inFlight - 1);
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

    /** 安全护栏：跳过系统界面与自身应用 */
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
        inFlight = 0;
    }
}