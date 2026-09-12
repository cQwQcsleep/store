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
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;

import java.util.Locale;

/**
 * 新增：全局陀螺仪无障碍服务（v8.0 新能力，原版 7.0 无此服务）。
 * <p>
 * 在系统无障碍设置中开启本服务后：
 * <ul>
 *   <li>后台持续读取真实陀螺仪数据（TYPE_GYROSCOPE）；</li>
 *   <li>把设备姿态转化为滑动操作：横滚（绕纵轴左右倾斜）→ 左右滑动，俯仰（绕横轴前后倾斜）→ 上下滑动；</li>
 *   <li>通过无障碍 {@link #dispatchGesture(GestureDescription, GestureResultCallback, Handler)}
 *       注入到任意前台应用，让不支持陀螺仪的应用也能用体感操控。</li>
 * </ul>
 * 注入是否生效由应用内开关（{@link #PREF_ENABLED}）实时控制，本身不改变任何系统设置。
 */
public class GlobalGyroService extends AccessibilityService {

    /** 应用内：是否启用倾斜滑动注入 */
    public static final String PREF_ENABLED = "global_gyro_enabled";
    /** 服务写入：无障碍服务已连接 */
    public static final String PREF_ACTIVE = "global_gyro_active";
    /** 服务写入：当前前台应用包名（用于状态展示） */
    public static final String PREF_TARGET = "global_gyro_target";

    // 倾斜→滑动 手感参数（基础版固定值，后续可做成 UI 可调项）
    private static final double DEAD_ZONE = 0.30;      // rad/s：低于该角速度视为静止
    private static final double TRIGGER_RAD = 0.45;    // rad：累计位移达到该值触发一次滑动
    private static final double DECAY = 2.0;           // /s：累计位移自然衰减，防止惯性连发
    private static final long MIN_INTERVAL_MS = 140;   // 相邻两次滑动的最小间隔
    private static final float SWIPE_FRACTION = 0.26f; // 滑动距离占屏幕对应边长的比例

    private SensorManager sensorManager;
    private Sensor gyro;
    private boolean sensorRegistered = false;
    private boolean enabled = false;
    private boolean gestureRunning = false;
    private long lastSensorTs = -1;
    private double accRoll = 0;   // 累计水平位移（横滚，围绕纵轴）
    private double accPitch = 0;  // 累计垂直位移（俯仰，围绕横轴）
    private long lastGestureTime = 0;

    private SharedPreferences prefs;
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    private final SensorEventListener gyroListener = new SensorEventListener() {
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            if (!enabled || event.values.length < 3) {
                return;
            }
            long ts = event.timestamp; // 纳秒
            if (lastSensorTs > 0) {
                double dt = (ts - lastSensorTs) / 1e9;
                if (dt <= 0 || dt > 0.5) {
                    lastSensorTs = ts;
                    return;
                }
                // 横滚：绕 y 轴角速度 → 左右滑动；俯仰：绕 x 轴角速度 → 上下滑动
                double decay = Math.exp(-DECAY * dt);
                accRoll = accRoll * decay + event.values[1] * dt;
                accPitch = accPitch * decay + event.values[0] * dt;
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
                    }
                }
            };

    @Override
    public void onServiceConnected() {
        super.onServiceConnected();
        prefs = getSharedPreferences("data", 0);
        enabled = prefs.getBoolean(PREF_ENABLED, false);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        gyro = sensorManager == null ? null
                : sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if (gyro == null) {
            prefs.edit().putBoolean(PREF_ACTIVE, true).apply();
            return; // 设备无陀螺仪：服务保持连接以便展示状态，但不感知
        }
        prefs.registerOnSharedPreferenceChangeListener(prefListener);
        updateSensing();
        prefs.edit().putBoolean(PREF_ACTIVE, true).apply();
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
        } else if ((!enabled || Build.VERSION.SDK_INT < 24) && sensorRegistered) {
            sensorManager.unregisterListener(gyroListener);
            sensorRegistered = false;
        }
    }

    /** 手势注入核心：累计位移达到阈值即向前台应用派发一次滑动 */
    private void maybeFireGesture() {
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
        fireSwipe(horizontal, horizontal ? Math.signum(accRoll) : Math.signum(accPitch));
        accRoll = 0;
        accPitch = 0;
        lastGestureTime = now;
    }

    private void fireSwipe(boolean horizontal, double directionSign) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        if (wm == null) {
            return;
        }
        wm.getDefaultDisplay().getRealMetrics(dm);
        float screenW = dm.widthPixels;
        float screenH = dm.heightPixels;
        float length = (horizontal ? screenW : screenH) * SWIPE_FRACTION;

        float cx = screenW / 2f;
        float cy = screenH / 2f;
        float delta = length * (float) directionSign;
        float ex = horizontal ? cx + delta : cx;
        float ey = horizontal ? cy : cy + delta;

        Path path = new Path();
        path.moveTo(cx, cy);
        path.lineTo(ex, ey);

        long duration = Math.max(60L, (long) (length * 0.30f));
        GestureDescription.StrokeDescription stroke =
                new GestureDescription.StrokeDescription(path, 0, duration);
        GestureDescription gesture = new GestureDescription.Builder().addStroke(stroke).build();

        gestureRunning = true;
        dispatchGesture(gesture, new GestureResultCallback() {
            @Override
            public void onCompleted(GestureDescription gestureDescription) {
                gestureRunning = false;
            }

            @Override
            public void onCancelled(GestureDescription gestureDescription) {
                gestureRunning = false;
            }
        }, mainHandler);
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
        if (isBlockedPackage(name)) {
            return;
        }
        String current = prefs.getString(PREF_TARGET, null);
        if (!name.equals(current)) {
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