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
 * 新增：全局陀螺仪无障碍服务（v8.5：连续位置跟踪 + 复合多段手势）。
 * <p>
 * 「一段一段」的根因：旧版每段滑动都从屏幕中心重新按下，且段短促，游戏把每次
 * 「中心起笔的轻扫」当独立操作。v8.5 修正三点：
 * <ul>
 *   <li><b>位置连续</b>：相邻手势的起点 = 上一段终点，手指位置沿路径连续移动，
 *       不再跳回屏幕中心重新按下；</li>
 *   <li><b>复合多段手势</b>：一次注入包含 3 段连续 move（共 300ms）的复合 stroke，
 *       段内完全无抬起事件，抬起频率从每秒 10+ 次降到约 3 次；</li>
 *   <li><b>无缝衔接</b>：手势完成回调立即按最新姿态构建下一复合手势，间隙仅毫秒级。</li>
 * </ul>
 * 输入仍为：200Hz 采样（联动原版采样率滑杆）+ 低通滤波，目标偏移由角速度实时映射
 * （横滚→水平、俯仰→垂直），静止时平滑拖回中心模拟回正；灵敏度滑杆实时可调。
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
    private static final double DEAD_ZONE = 0.05;            // rad/s：低于该角速度视为静止
    private static final float RATE_FULL = 2.0f;             // rad/s：达到该角速度即为满量程偏移
    private static final float MAX_OFFSET_FRACTION = 0.42f;  // 满量程偏移占屏幕对应边长的比例
    private static final int COMPOSITE_SEGMENTS = 3;         // 一次复合手势包含的段数
    private static final long SEGMENT_MS = 100;              // 每段时长，复合手势总时长 = 段数 × 段长
    private static final float MIN_RETURN_DIP = 12f;         // 距中心小于该值视为已回中
    private static final float MIN_AWAY_DIP = 24f;           // 目标偏离中心超过该值才视为启动拖动
    private static final float SMOOTH_RESPONSE = 30f;        // 低通滤波响应速率（/s）
    private static final int SAMPLE_RATE_DEFAULT = 200;      // 默认采样率 Hz（联动原版采样率滑杆）
    public static final int GAIN_DEFAULT = 100;              // 默认灵敏度 1.0x

    private SensorManager sensorManager;
    private Sensor gyro;
    private boolean sensorRegistered = false;
    private boolean enabled = false;
    private boolean gestureRunning = false;
    private long lastSensorTs = -1;
    private float density = 1f;
    private int sampleRateHz = SAMPLE_RATE_DEFAULT;
    private float gain = 1f;
    /** 低通滤波后的角速度（rad/s），比原始值更平滑精准 */
    private float smoothPitch = 0f;
    private float smoothRoll = 0f;
    private boolean smoothInit = false;
    /** 屏幕尺寸（像素） */
    private float screenW = 0f;
    private float screenH = 0f;
    /** 当前指尖位置（上一段终点，屏幕像素坐标）；null 表示尚未开始（从中心按下） */
    private float[] fingerPos;
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
            if (!enabled) {
                return;
            }
            if (!gestureRunning) {
                tryStartComposite(); // 空闲时启动/续接复合手势
            }
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

    // ==================== 复合连续手势（消除「一段一段」） ====================

    /**
     * 根据当前角速度计算目标偏移，并派发一个「起点=上一段终点」的多段复合手势。
     * 手势完成后回调会立即用最新姿态续接，保证路径连续、抬起频率极低。
     */
    private void tryStartComposite() {
        if (!enabled || gestureRunning) {
            return;
        }
        if (currentPackage != null && isBlockedPackage(currentPackage)) {
            fingerPos = null;
            return;
        }
        if (screenW <= 0 || screenH <= 0) {
            queryScreenSize();
        }
        float cx = screenW / 2f;
        float cy = screenH / 2f;
        // 角速度 → 目标偏移（px）：横滚→水平，俯仰→垂直
        float maxW = screenW * MAX_OFFSET_FRACTION * gain;
        float maxH = screenH * MAX_OFFSET_FRACTION * gain;
        float targetX = clamp(cx + smoothRoll / RATE_FULL * maxW, 0, screenW);
        float targetY = clamp(cy - smoothPitch / RATE_FULL * maxH, 0, screenH); // 屏幕 y 向下

        float minReturn = MIN_RETURN_DIP * density;
        float minAway = MIN_AWAY_DIP * density;
        boolean activeInput = Math.abs(smoothRoll) >= DEAD_ZONE
                || Math.abs(smoothPitch) >= DEAD_ZONE;
        boolean atCenter = fingerPos == null
                || (Math.abs(fingerPos[0] - cx) < minReturn && Math.abs(fingerPos[1] - cy) < minReturn);
        boolean targetAway = Math.abs(targetX - cx) >= minAway || Math.abs(targetY - cy) >= minAway;

        // 静止且已回中 → 结束复合链
        if (!activeInput && atCenter) {
            fingerPos = null;
            return;
        }
        // 静止但手指还在偏移处 → 目标回中心（平滑回正，由复合段插值完成）
        boolean retuning = !activeInput && !atCenter;
        float destX = retuning || !targetAway ? cx : targetX;
        float destY = retuning || !targetAway ? cy : targetY;

        // 起点：首次从屏幕中心按下；后续从上一段终点继续 —— 位置永不跳变
        float startX = fingerPos == null ? cx : fingerPos[0];
        float startY = fingerPos == null ? cy : fingerPos[1];
        dispatchComposite(startX, startY, destX, destY);
        fingerPos = new float[]{destX, destY};
    }

    /**
     * 派发一个多段复合手势：从 (fromX,fromY) 经过 COMPOSITE_SEGMENTS 段线性插值到 (toX,toY)，
     * 段内无抬起（down → 连续 move → hold），系统按 startTime 顺序无缝执行。
     */
    private void dispatchComposite(float fromX, float fromY, float toX, float toY) {
        GestureDescription.Builder builder = new GestureDescription.Builder();
        float segX = (toX - fromX) / COMPOSITE_SEGMENTS;
        float segY = (toY - fromY) / COMPOSITE_SEGMENTS;
        long startTime = 0;
        float curX = fromX;
        float curY = fromY;
        for (int i = 0; i < COMPOSITE_SEGMENTS; i++) {
            float nx = i == COMPOSITE_SEGMENTS - 1 ? toX : curX + segX;
            float ny = i == COMPOSITE_SEGMENTS - 1 ? toY : curY + segY;
            // 段间位移小于 1px（到位后原地保持）：给 1px 微移避免空 stroke
            if (Math.abs(nx - curX) < 1f && Math.abs(ny - curY) < 1f) {
                nx += 1f;
            }
            Path segment = new Path();
            segment.moveTo(curX, curY);
            segment.lineTo(nx, ny);
            builder.addStroke(new GestureDescription.StrokeDescription(
                    segment, startTime, SEGMENT_MS, i < COMPOSITE_SEGMENTS - 1));
            startTime += SEGMENT_MS;
            curX = nx;
            curY = ny;
        }

        gestureRunning = true;
        boolean submitted = dispatchGesture(builder.build(), new GestureResultCallback() {
            @Override
            public void onCompleted(GestureDescription gestureDescription) {
                gestureRunning = false;
                tryStartComposite(); // 无缝续接：移动位置连续、抬起频率极低
            }

            @Override
            public void onCancelled(GestureDescription gestureDescription) {
                gestureRunning = false;
                fingerPos = null; // 被系统打断，从中心重新开始
                Log.i(TAG, "复合手势被系统取消");
            }
        }, mainHandler);
        if (!submitted) {
            gestureRunning = false;
            Log.w(TAG, "复合手势派发失败");
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
            fingerPos = null;
            Log.i(TAG, "注册陀螺仪监听(" + sampleRateHz + "Hz) -> " + sensorRegistered);
        } else if ((!enabled || Build.VERSION.SDK_INT < 24) && sensorRegistered) {
            sensorManager.unregisterListener(gyroListener);
            sensorRegistered = false;
            fingerPos = null;
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