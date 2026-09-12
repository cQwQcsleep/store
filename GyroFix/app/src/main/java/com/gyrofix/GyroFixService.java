package com.gyrofix;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ServiceInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Icon;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.service.quicksettings.TileService;
import android.text.TextPaint;
import android.widget.Toast;

import java.util.Locale;

/**
 * 原版 7.0：陀螺仪修复前台服务。
 * 以指定（可为最高）采样率注册陀螺仪监听，使设置对全局正在使用陀螺仪的应用立即生效；
 * 通知栏常驻，可选显示实时采样率，带「停止服务」快捷操作。
 * <p>
 * 本类保持原版行为不变（只做加法，不做减法）。
 */
public class GyroFixService extends Service {

    public static final String BroadcastIntentFilter = "intent.gyrofix.exit";

    private SensorManager mSensorMgr;
    private boolean isGyroEnabled = false;
    /** 通知栏实时采样率显示开关 */
    private boolean isUpdateNotificationEnabled = false;
    private int counter = 0;
    private final Handler handler = new Handler();
    private int interval = 1;

    private Notification.Builder notification;
    private NotificationManager notificationManager;
    private Bitmap bitmap;
    private Canvas canvas;
    private TextPaint textPaint1;
    private TextPaint textPaint2;
    private TextPaint textPaint3;

    private final Runnable taskRunnable = new Runnable() {
        @Override
        public void run() {
            int hz = counter / interval;
            notification.setContentText(getString(R.string.noti_realtime_text) + hz + "Hz");
            if (Build.VERSION.SDK_INT >= 23) {
                drawCurrentText(hz);
                notification.setSmallIcon(Icon.createWithBitmap(bitmap));
            }
            notificationManager.notify(1, notification.build());
            counter = 0;
            handler.postDelayed(this, interval * 1000L);
        }
    };

    private final SensorEventListener mGyroListener = new SensorEventListener() {
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            if (isUpdateNotificationEnabled) {
                counter++;
            }
        }
    };

    private final BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            stopSelf();
        }
    };

    private final SharedPreferences.OnSharedPreferenceChangeListener myListener =
            new SharedPreferences.OnSharedPreferenceChangeListener() {
                @Override
                public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
                    if (MainActivity.UpdateIntervalPref.equals(key)) {
                        interval = prefs.getInt(MainActivity.UpdateIntervalPref, 1);
                    }
                }
            };

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= 24) {
            TileService.requestListeningState(this, new ComponentName(this, StartFixTileService.class));
        }
        final SharedPreferences prefs = getSharedPreferences("data", 0);
        if (Build.VERSION.SDK_INT >= 26) {
            registerReceiver(mBroadcastReceiver,
                    new IntentFilter(BroadcastIntentFilter), Context.RECEIVER_NOT_EXPORTED);
        } else {
            registerReceiver(mBroadcastReceiver, new IntentFilter(BroadcastIntentFilter));
        }

        int sampleRate = prefs.getInt(MainActivity.SampleRatePref, MainActivity.SampleRatePrefDefValue);

        if (prefs.getBoolean("foreground", true)) {
            notificationManager = (NotificationManager) getSystemService("notification");

            String rateText = sampleRate > 200
                    ? getString(R.string.max)
                    : sampleRate + "Hz";
            Notification.Builder builder = new Notification.Builder(getApplication())
                    .setAutoCancel(true)
                    .setContentText(getString(R.string.noti_text) + rateText)
                    .setContentTitle(getString(R.string.noti_title))
                    .addAction(android.R.drawable.ic_delete, getString(R.string.noti_stop),
                            PendingIntent.getBroadcast(this, 0,
                                    new Intent(BroadcastIntentFilter), PendingIntent.FLAG_IMMUTABLE))
                    .setWhen(System.currentTimeMillis())
                    .setSound(null)
                    .setVibrate(null)
                    .setContentIntent(PendingIntent.getActivity(this, 0,
                            new Intent(this, MainActivity.class), PendingIntent.FLAG_IMMUTABLE));
            if (Build.VERSION.SDK_INT >= 23) {
                builder.setSmallIcon(Icon.createWithResource(this, R.drawable.tile));
            }
            if (Build.VERSION.SDK_INT >= 26) {
                NotificationChannel channel = new NotificationChannel(
                        "daemon", "陀螺仪服务", NotificationManager.IMPORTANCE_DEFAULT);
                channel.enableLights(false);
                channel.setShowBadge(false);
                channel.setSound(null, null);
                channel.enableVibration(false);
                channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
                notificationManager.createNotificationChannel(channel);
                builder.setChannelId("daemon");
            }
            if (Build.VERSION.SDK_INT >= 31) {
                builder.setForegroundServiceBehavior(Notification.FOREGROUND_SERVICE_IMMEDIATE);
            }
            if (Build.VERSION.SDK_INT >= 34) {
                startForeground(1, builder.build(), ServiceInfo.FOREGROUND_SERVICE_TYPE_SPECIAL_USE);
            } else {
                startForeground(1, builder.build());
            }
            notification = builder;

            if (prefs.getBoolean("update_notification", true)) {
                interval = prefs.getInt(MainActivity.UpdateIntervalPref, 1);
                isUpdateNotificationEnabled = true;
                if (Build.VERSION.SDK_INT >= 23) {
                    bitmap = Bitmap.createBitmap(64, 64, Bitmap.Config.ARGB_8888);
                    canvas = new Canvas(bitmap);

                    textPaint1 = newTextPaint(40f);
                    textPaint2 = newTextPaint(27f);
                    textPaint3 = newTextPaint(50f);
                }
                handler.postDelayed(taskRunnable, interval * 1000L);
            }
        }

        mSensorMgr = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor gyro = mSensorMgr == null ? null
                : mSensorMgr.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if (gyro == null
                || !mSensorMgr.registerListener(mGyroListener, gyro,
                sampleRate > 200 ? SensorManager.SENSOR_DELAY_FASTEST : 1000000 / sampleRate)) {
            Toast.makeText(this, R.string.gyro_unsupport, Toast.LENGTH_SHORT).show();
            stopSelf();
        } else {
            isGyroEnabled = true;
            prefs.registerOnSharedPreferenceChangeListener(myListener);
        }
    }

    private static TextPaint newTextPaint(float size) {
        TextPaint paint = new TextPaint();
        paint.setAntiAlias(true);
        paint.setTextSize(size);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setStyle(Paint.Style.FILL);
        paint.setFakeBoldText(true);
        paint.setSubpixelText(true);
        paint.setLetterSpacing(0f);
        return paint;
    }

    /** 把采样率数字画进 64x64 通知小图标 */
    public void drawCurrentText(int value) {
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        String s = String.valueOf(value);
        if (value > 999) {
            canvas.drawText(String.format(Locale.getDefault(), "%.1f", value / 1000f),
                    31f, 40f, textPaint3);
            canvas.drawText("K", 31f, 64f, textPaint2);
        } else if (value > 99) {
            canvas.drawText(s, 31f, 48f, textPaint1);
        } else {
            canvas.drawText(s, 31f, 50f, textPaint3);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isGyroEnabled && mSensorMgr != null) {
            mSensorMgr.unregisterListener(mGyroListener);
        }
        if (isUpdateNotificationEnabled) {
            handler.removeCallbacksAndMessages(null);
        }
        unregisterReceiver(mBroadcastReceiver);
        getSharedPreferences("data", 0).unregisterOnSharedPreferenceChangeListener(myListener);
        if (Build.VERSION.SDK_INT >= 24) {
            TileService.requestListeningState(this, new ComponentName(this, StartFixTileService.class));
        }
    }
}