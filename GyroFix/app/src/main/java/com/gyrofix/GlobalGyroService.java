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
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;

import java.util.Locale;

/**
 * 新增：全局陀螺仪无障碍服务（v8.3 原生手感版）。
 * <p>
 * 无障碍服务无法真正“按住不放”，因此用「连续拖动链」逼近原生陀螺仪手感：
 * 一段拖动手势结束后立即续上一段（间隙仅几毫秒），期间绝大部分时间都保持在
 * “按住 → 跟随偏移 → 回中”的状态，赛车/射击等应用感受到的是连续转向与平滑移动，
 * 而非一段段离散滑动。
 * <ul>
 *   <li><b>输入</b>：读取真实陀螺仪（采样率联动原版「采样率(Hz)」设置，默认 200Hz），
 *       一阶低通滤波去手抖；</li>
 *   <li><b>映射</b>：角速度 → 目标偏移（横滚→水平位移、俯仰→垂直位移），
 *       倾斜越大位移越大、位移上限约 40% 屏幕；速率回零时自动派发“回中”段，模拟原生回正；</li>
 *   <li><b>输出</b>：每段 240ms 的按住-拖动 stroke，段与段之间由完成回调直接衔接，无空隙。</li>
 * </ul>
 * 注入是否生效由应用内开关（{@link #PREF_ENABLED}）控制；灵敏度由应用内滑杆
 * （{@link #PREF_GAIN}）实时调整。
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
    private static final float RATE_FULL = 2.0f;         // rad/s：达到该角速度即为满量程偏移
    private static final float MAX_OFFSET_FRACTION = 0.42f; // 满量程偏移占屏幕对应边长的比例
    private static final long DRAG_STROKE_MS = 240;      // 每段按住-拖动时长，越短越跟手
    private static final float MIN_OFFSET_DIP = 16f;     // 目标偏移小于该值视为已回中，停止拖动链
    private static final float SMOOTH_RESPONSE = 30f;    // 低通滤波响应速率（/s）
    private static final int SAMPLE_RATE_DEFAULT = 200;  // 默认采样率 Hz（联动原版滑杆）
    public static final int GAIN_DEFAULT = 100;         // 默认灵敏度 1.0x

    private SensorManager sensorManager;
    private Sensor gyro;
    private boolean sensorRegistered = false;
    private boolean enabled = false;
    private boolean gestureRunning = false;
    private long lastSensorTs = -1;
    private float density = 1f;
    private int sampleRateHz = SAMPLE_RATE_DEFAULT;
    private float gain = 1f;
    /** 低通滤波后的角速度（rad/s） */
    private float smoothPitch = 0f;
    private float smoothRoll = 0f;
    private boolean smoothInit = false;
    /** 拖动链状态：上一段终点（屏幕像素坐标），未开始为 null */
    private float[] lastEnd;
    /** 屏幕尺寸（像素） */
    private float screenW = 0f;
    private float screenH = 0f;
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
            // 一阶低通滤波：滤除手部抖动
            float alpha = (float) Math.min(0.9, Math.max(0.05, dt * SMOOTH_RESPONSE));
            if (!smoothInit) {
                smoothPitch = event.values[0];
                smoothRoll = event.values[1];
                smoothInit = true;
            } else {
                smoothPitch += (event.values[0] - smoothPitch) * alpha;
                smoothRoll += (event.values[1] - smoothRoll) * alpha;
            }
            // 实时回显（无论开关，便于验证数据链路）
            lastPitchRate = smoothPitch;
            lastRollRate = smoothRoll;
            if (!enabled || gestureRunning) {
                return; // 拖动链进行中：由完成回调接管下一段
            }
            tryStartDragLoop();
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

    // ==================== 拖动链（原生手感核心） ====================

    /** 空闲时尝试启动/续接拖动链：把当前角速度映射为目标偏移并派发一段拖动 */
    private void tryStartDragLoop() {
        if (!enabled || gestureRunning) {
            return;
        }
        if (screenW <= 0 || screenH <= 0) {
            queryScreenSize();
        }
        float cx = screenW / 2f;
        float cy = screenH / 2f;
        float minDip = MIN_OFFSET_DIP * density;

        // 角速度 → 目标偏移（px）：横滚→水平，俯仰→垂直；上限 MAX_OFFSET_FRACTION
        float maxW = screenW * MAX_OFFSET_FRACTION * gain;
        float maxH = screenH * MAX_OFFSET_FRACTION * gain;
        float offsetX = clamp(smoothRoll / RATE_FULL * maxW, -maxW, maxW);
        float offsetY = clamp(smoothPitch / RATE_FULL * maxH, -maxH, maxH);
        float targetX = cx + offsetX;
        float targetY = cy - offsetY; // 屏幕 y 向下：俯仰向前倾(>0) → 向上位移

        boolean active = Math.abs(smoothRoll) >= DEAD_ZONE || Math.abs(smoothPitch) >= DEAD_ZONE;
        boolean heldAway = lastEnd != null
                && (Math.abs(lastEnd[0] - cx) >= minDip || Math.abs(lastEnd[1] - cy) >= minDip);
        boolean targetAway = Math.abs(offsetX) >= minDip || Math.abs(offsetY) >= minDip;

        if (!active) {
            if (!heldAway) {
                // 静止且已在中心：结束拖动链
                lastEnd = null;
                return;
            }
            // 静止但手指仍停在偏移处：派发一段「回中」拖动，模拟原生陀螺仪回正
            dispatchDragSegment(lastEnd[0], lastEnd[1], cx, cy);
            lastEnd = new float[]{cx, cy};
            return;
        }
        if (!heldAway && !targetAway) {
            // 有微小输入但尚未真正离开中心：不启动，避免抖动空转
            return;
        }

        // 起点：首次从屏幕中心按下；后续从上一段终点继续，形成连续拖拽轨迹
        float fromX = lastEnd == null ? cx : lastEnd[0];
        float fromY = lastEnd == null ? cy : lastEnd[1];
        // 目标与起点相同（到位后原地保持）时给 1px 微位移防止空 stroke 被忽略
        if (Math.abs(targetX - fromX) < 1f && Math.abs(targetY - fromY) < 1f) {
            targetX += 1f;
        }
        dispatchDragSegment(fromX, fromY, targetX, targetY);
        lastEnd = new float[]{targetX, targetY};
    }

    /** 派发一段「按下→拖动到目标→保持」的 stroke，结束后立即续接下一段（无空隙） */
    private void dispatchDragSegment(float fromX, float fromY, float toX, float toY) {
        Path path = new Path();
        path.moveTo(fromX, fromY);
        path.lineTo(toX, toY);

        GestureDescription.StrokeDescription stroke =
                new GestureDescription.StrokeDescription(path, 0, DRAG_STROKE_MS);
        GestureDescription gesture = new GestureDescription.Builder().addStroke(stroke).build();

        gestureRunning = true;
        boolean submitted = dispatchGesture(gesture, new GestureResultCallback() {
            @Override
            public void onCompleted(GestureDescription gd) {
                gestureRunning = false;
                tryStartDragLoop(); // 无缝续接下一段
            }

            @Override
            public void onCancelled(GestureDescription gd) {
                gestureRunning = false;
                lastEnd = null; // 被系统打断，从中心重新开始
                Log.i(TAG, "拖动段被系统取消");
            }
        }, mainHandler);
        if (!submitted) {
            gestureRunning = false;
            Log.w(TAG, "拖动段派发失败");
        }
    }

    // ==================== 传感器采样 ====================

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
            smoothInit = false;
            lastEnd = null;
            Log.i(TAG, "注册陀螺仪监听(" + sampleRateHz + "Hz) -> " + sensorRegistered);
        } else if ((!enabled || Build.VERSION.SDK_INT < 24) && sensorRegistered) {
            sensorManager.unregisterListener(gyroListener);
            sensorRegistered = false;
            lastEnd = null;
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

    private void queryScreenSize() {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        if (wm == null) {
            return;
        }
        wm.getDefaultDisplay().getRealMetrics(dm);
        screenW = dm.widthPixels;
        screenH = dm.heightPixels;
    }

    private static float clamp(float v, float lo, float hi) {
        return Math.max(lo, Math.min(hi, v));
    }

    // ==================== 前台跟踪与安全护栏 ====================

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
    }
}