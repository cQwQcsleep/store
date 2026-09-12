package com.gyrofix;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

/**
 * 主界面。
 * <p>
 * 上半部分为原版 7.0 全部功能（修复服务开关 / 采样率 / 前台通知 / 实时采样率 / 更新间隔 / 隐藏任务卡片），
 * 下半部分为新增的「全局陀螺仪（无障碍体感）」分区。只做加法，不做减法。
 */
public class MainActivity extends Activity {

    public static final String SampleRatePref = "sample_rate";
    public static final int SampleRatePrefDefValue = 200;
    public static final String UpdateIntervalPref = "update_interval";
    public static final int UpdateIntervalPrefDefValue = 1;

    private SharedPreferences prefs;

    /** 实时角速度回显轮询（仅在页面可见时运行，4Hz，开销可忽略） */
    private boolean livePolling = false;
    private final Handler uiHandler = new Handler(Looper.getMainLooper());
    private final Runnable livePoller = new Runnable() {
        @Override
        public void run() {
            updateLiveReadout();
            if (livePolling) {
                uiHandler.postDelayed(this, 250);
            }
        }
    };

    private final SharedPreferences.OnSharedPreferenceChangeListener gyroPrefListener =
            new SharedPreferences.OnSharedPreferenceChangeListener() {
                @Override
                public void onSharedPreferenceChanged(SharedPreferences sp, String key) {
                    if (GlobalGyroService.PREF_ACTIVE.equals(key)
                            || GlobalGyroService.PREF_TARGET.equals(key)
                            || GlobalGyroService.PREF_ENABLED.equals(key)) {
                        refreshGlobalGyroStatus();
                    }
                }
            };

    // ==================== 原版 7.0：隐私弹窗 ====================
    private void showPrivacy() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.privacy_title)
                .setPositiveButton(R.string.agree, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getSharedPreferences("data", 0).edit().putBoolean("first", false).apply();
                    }
                })
                .setCancelable(false)
                .setMessage(R.string.privacy_content)
                .setNegativeButton(R.string.exit, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .create().show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 原版：横屏时限制对话框宽度，避免拉满整个屏幕
        if (getResources().getConfiguration().orientation == 2) {
            getWindow().getAttributes().width = getWindowManager().getDefaultDisplay().getHeight();
        }

        // 原版：申请忽略电池优化（保证后台修复服务稳定）
        if (Build.VERSION.SDK_INT >= 23) {
            PowerManager pm = (PowerManager) getSystemService("power");
            if (pm != null && !pm.isIgnoringBatteryOptimizations(getPackageName())) {
                startActivity(new Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS,
                        Uri.parse("package:" + getPackageName())));
            }
        }

        // 原版：Android 13+ 通知权限
        if (Build.VERSION.SDK_INT >= 33) {
            NotificationManager nm = (NotificationManager) getSystemService("notification");
            if (nm != null && !nm.areNotificationsEnabled()) {
                requestPermissions(new String[]{"android.permission.POST_NOTIFICATIONS"}, 0);
            }
        }

        // 原版：帮助按钮（圆角、强调色背景）
        Button helpBtn = (Button) findViewById(R.id.b);
        float radius = getResources().getDisplayMetrics().density * 40f;
        ShapeDrawable shape = new ShapeDrawable(
                new RoundRectShape(new float[]{radius, radius, radius, radius,
                        radius, radius, radius, radius}, null, null));
        @SuppressWarnings("deprecation")
        int btnColor = Build.VERSION.SDK_INT >= 23
                ? getColor(R.color.a) : getResources().getColor(R.color.a);
        shape.getPaint().setColor(btnColor);
        helpBtn.setBackground(shape);
        helpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHelp();
            }
        });

        prefs = getSharedPreferences("data", 0);

        // 原版：隐藏最近任务卡片
        ActivityManager am = (ActivityManager) getSystemService("activity");
        if (am.getAppTasks() != null && !am.getAppTasks().isEmpty()
                && am.getAppTasks().get(0) != null) {
            am.getAppTasks().get(0).setExcludeFromRecents(prefs.getBoolean("hide", true));
        }

        // 原版：首次使用隐私弹窗
        if (prefs.getBoolean("first", true)) {
            showPrivacy();
        }

        setupOriginalSection();
        setupGlobalGyroSection();
    }

    // ==================== 原版 7.0：全部原有开关与滑杆 ====================
    private void setupOriginalSection() {
        // s1 服务运行状态
        Switch s1 = (Switch) findViewById(R.id.s1);
        s1.setChecked(isGyroFixServiceRunning());
        s1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (Build.VERSION.SDK_INT >= 23) {
                    PowerManager pm = (PowerManager) getSystemService("power");
                    if (pm != null && !pm.isIgnoringBatteryOptimizations(getPackageName())) {
                        startActivity(new Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS,
                                Uri.parse("package:" + getPackageName())));
                    }
                }
                if (isChecked) {
                    if (Build.VERSION.SDK_INT >= 26 && prefs.getBoolean("foreground", true)) {
                        if (Build.VERSION.SDK_INT >= 33) {
                            NotificationManager nm = (NotificationManager) getSystemService("notification");
                            if (nm != null && !nm.areNotificationsEnabled()) {
                                return;
                            }
                        }
                        startForegroundService(new Intent(MainActivity.this, GyroFixService.class));
                        return;
                    }
                    startService(new Intent(MainActivity.this, GyroFixService.class));
                } else {
                    stopService(new Intent(MainActivity.this, GyroFixService.class));
                }
            }
        });

        // s2 前台运行通知
        Switch s2 = (Switch) findViewById(R.id.s2);
        s2.setChecked(prefs.getBoolean("foreground", true));
        s2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                prefs.edit().putBoolean("foreground", isChecked).apply();
                if (isChecked && Build.VERSION.SDK_INT >= 33) {
                    NotificationManager nm = (NotificationManager) getSystemService("notification");
                    if (nm != null && !nm.areNotificationsEnabled()) {
                        requestPermissions(new String[]{"android.permission.POST_NOTIFICATIONS"}, 0);
                    }
                }
                Toast.makeText(MainActivity.this, R.string.restart_service, Toast.LENGTH_SHORT).show();
            }
        });

        // s3 通知实时采样率
        Switch s3 = (Switch) findViewById(R.id.s3);
        s3.setChecked(prefs.getBoolean("update_notification", true));
        s3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                prefs.edit().putBoolean("update_notification", isChecked).apply();
                Toast.makeText(MainActivity.this, R.string.restart_service, Toast.LENGTH_SHORT).show();
            }
        });

        // e2/sb2 通知更新间隔 (1~5s)
        final EditText editInterval = (EditText) findViewById(R.id.e2);
        final SeekBar seekInterval = (SeekBar) findViewById(R.id.sb2);
        editInterval.setText(String.format(Locale.getDefault(), "%d",
                prefs.getInt(UpdateIntervalPref, 1)));
        seekInterval.setProgress(prefs.getInt(UpdateIntervalPref, 1));
        seekInterval.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                editInterval.setText(String.format(Locale.getDefault(), "%d", progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                prefs.edit().putInt(UpdateIntervalPref, seekBar.getProgress()).apply();
            }
        });
        editInterval.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN
                        && editInterval.getText().length() > 0) {
                    int value = Integer.parseInt(editInterval.getText().toString());
                    if (value >= 1 && value <= 5) {
                        prefs.edit().putInt(UpdateIntervalPref, value).apply();
                        seekInterval.setProgress(value);
                    } else {
                        Toast.makeText(MainActivity.this, R.string.input_5, Toast.LENGTH_SHORT).show();
                        editInterval.setText(String.format(Locale.getDefault(), "%d",
                                prefs.getInt(UpdateIntervalPref, 1)));
                    }
                }
                return false;
            }
        });
        editInterval.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    return;
                }
                try {
                    int value = Integer.parseInt(editInterval.getText().toString());
                    if (value < 1 || value > 5) {
                        Toast.makeText(MainActivity.this, R.string.input_5, Toast.LENGTH_SHORT).show();
                        editInterval.setText(String.format(Locale.getDefault(), "%d",
                                prefs.getInt(UpdateIntervalPref, 1)));
                    } else {
                        prefs.edit().putInt(UpdateIntervalPref, value).apply();
                        seekInterval.setProgress(value);
                    }
                } catch (Exception ignored) {
                }
            }
        });

        // e1/sb1 采样率 (10~200，超过 200 视为最大 250)
        final EditText editRate = (EditText) findViewById(R.id.e1);
        final SeekBar seekRate = (SeekBar) findViewById(R.id.sb1);
        int savedRate = prefs.getInt(SampleRatePref, SampleRatePrefDefValue);
        editRate.setText(savedRate > 200
                ? getString(R.string.max)
                : String.format(Locale.getDefault(), "%d", savedRate));
        if (savedRate > 200) {
            savedRate = 250;
        }
        seekRate.setProgress(savedRate);
        seekRate.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress > 200) {
                    seekBar.setProgress(250);
                    editRate.setText(getString(R.string.max));
                } else {
                    editRate.setText(String.format(Locale.getDefault(), "%d", progress));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                if (progress > 200) {
                    progress = 250;
                }
                prefs.edit().putInt(SampleRatePref, progress).apply();
            }
        });
        editRate.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN
                        && editRate.getText().length() > 0) {
                    int value = Integer.parseInt(editRate.getText().toString());
                    if (value > 200) {
                        prefs.edit().putInt(SampleRatePref, 250).apply();
                        seekRate.setProgress(250);
                        editRate.setText(getString(R.string.max));
                    } else if (value >= 10) {
                        prefs.edit().putInt(SampleRatePref, value).apply();
                        seekRate.setProgress(value);
                    } else {
                        Toast.makeText(MainActivity.this, R.string.input_200, Toast.LENGTH_SHORT).show();
                        editRate.setText(String.format(Locale.getDefault(), "%d",
                                prefs.getInt(SampleRatePref, SampleRatePrefDefValue)));
                    }
                }
                return false;
            }
        });
        editRate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    return;
                }
                try {
                    int value = Integer.parseInt(editRate.getText().toString());
                    if (value > 200) {
                        prefs.edit().putInt(SampleRatePref, 250).apply();
                        seekRate.setProgress(250);
                        editRate.setText(getString(R.string.max));
                    } else if (value >= 10) {
                        prefs.edit().putInt(SampleRatePref, value).apply();
                        seekRate.setProgress(value);
                    } else {
                        Toast.makeText(MainActivity.this, R.string.input_200, Toast.LENGTH_SHORT).show();
                        editRate.setText(String.format(Locale.getDefault(), "%d",
                                prefs.getInt(SampleRatePref, SampleRatePrefDefValue)));
                    }
                } catch (Exception ignored) {
                }
            }
        });

        // s4 隐藏后台任务卡片
        Switch s4 = (Switch) findViewById(R.id.s4);
        s4.setChecked(prefs.getBoolean("hide", true));
        s4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                prefs.edit().putBoolean("hide", isChecked).apply();
                ActivityManager am = (ActivityManager) getSystemService("activity");
                if (am.getAppTasks() != null && !am.getAppTasks().isEmpty()
                        && am.getAppTasks().get(0) != null) {
                    am.getAppTasks().get(0).setExcludeFromRecents(isChecked);
                }
            }
        });
    }

    // ==================== 新增：全局陀螺仪（无障碍体感）分区 ====================
    private void setupGlobalGyroSection() {
        Switch s5 = (Switch) findViewById(R.id.s5);
        Button gsBtn = (Button) findViewById(R.id.gs_btn);

        s5.setChecked(prefs.getBoolean(GlobalGyroService.PREF_ENABLED, false));
        s5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                prefs.edit().putBoolean(GlobalGyroService.PREF_ENABLED, isChecked).apply();
                if (!isChecked) {
                    return;
                }
                SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
                Sensor gyro = sm == null ? null : sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
                if (gyro == null) {
                    Toast.makeText(MainActivity.this, R.string.global_gyro_no_gyro,
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Build.VERSION.SDK_INT < 24) {
                    Toast.makeText(MainActivity.this, R.string.global_gyro_old_android,
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!isGlobalGyroServiceEnabled()) {
                    Toast.makeText(MainActivity.this, R.string.global_gyro_opening,
                            Toast.LENGTH_SHORT).show();
                    openAccessibilitySettings();
                } else {
                    Toast.makeText(MainActivity.this, R.string.global_gyro_activated,
                            Toast.LENGTH_SHORT).show();
                    updateLiveReadout();
                }
            }
        });
        gsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAccessibilitySettings();
            }
        });

        // 灵敏度滑杆（实时保存，服务即时生效）
        final SeekBar gyroGain = (SeekBar) findViewById(R.id.gyro_gain);
        final TextView gainLabel = (TextView) findViewById(R.id.gyro_gain_label);
        int savedGain = prefs.getInt(GlobalGyroService.PREF_GAIN, GlobalGyroService.GAIN_DEFAULT);
        if (savedGain < 10 || savedGain > 250) {
            savedGain = GlobalGyroService.GAIN_DEFAULT;
        }
        gyroGain.setProgress(savedGain);
        gainLabel.setText(getString(R.string.global_gyro_gain_x, savedGain));
        gyroGain.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                gainLabel.setText(getString(R.string.global_gyro_gain_x, progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                prefs.edit().putInt(GlobalGyroService.PREF_GAIN, seekBar.getProgress()).apply();
            }
        });

        prefs.registerOnSharedPreferenceChangeListener(gyroPrefListener);
        refreshGlobalGyroStatus();
    }

    private void openAccessibilitySettings() {
        try {
            startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS));
        } catch (Exception e) {
            Toast.makeText(this, R.string.global_gyro_status_off, Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isGlobalGyroServiceEnabled() {
        String enabled = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
        if (enabled == null) {
            return false;
        }
        ComponentName cn = new ComponentName(this, GlobalGyroService.class);
        return enabled.contains(cn.flattenToString())
                || enabled.contains(cn.flattenToShortString());
    }

    /** 刷新无障碍体感的状态栏文案与「前往设置」按钮可见性 */
    private void refreshGlobalGyroStatus() {
        TextView status = (TextView) findViewById(R.id.gs_status);
        Button btn = (Button) findViewById(R.id.gs_btn);
        if (status == null) {
            return;
        }
        if (!isGlobalGyroServiceEnabled()) {
            status.setText(R.string.global_gyro_status_off);
            btn.setVisibility(View.VISIBLE);
            updateLiveReadout();
            return;
        }
        btn.setVisibility(View.GONE);
        boolean enabledPref = prefs.getBoolean(GlobalGyroService.PREF_ENABLED, false);
        boolean active = prefs.getBoolean(GlobalGyroService.PREF_ACTIVE, false);
        if (enabledPref && active) {
            String target = prefs.getString(GlobalGyroService.PREF_TARGET, null);
            if (target != null) {
                status.setText(getString(R.string.global_gyro_injecting, target));
            } else {
                status.setText(R.string.global_gyro_status_on);
            }
            status.append(" · " + getString(R.string.global_gyro_activated_short));
        } else {
            status.setText(R.string.global_gyro_status_on);
        }
        updateLiveReadout();
    }

    /** 实时回显陀螺仪角速度，验证数据链路是否工作 */
    private void updateLiveReadout() {
        TextView live = (TextView) findViewById(R.id.gs_live);
        if (live == null) {
            return;
        }
        boolean sensing = prefs != null
                && prefs.getBoolean(GlobalGyroService.PREF_ENABLED, false)
                && isGlobalGyroServiceEnabled()
                && prefs.getBoolean(GlobalGyroService.PREF_ACTIVE, false);
        if (sensing) {
            live.setVisibility(View.VISIBLE);
            live.setText(String.format(Locale.US, getString(R.string.global_gyro_live),
                    GlobalGyroService.lastPitchRate, GlobalGyroService.lastRollRate));
        } else {
            live.setVisibility(View.GONE);
        }
    }

    private void startLivePoll() {
        if (!livePolling) {
            livePolling = true;
            uiHandler.post(livePoller);
        }
    }

    private void stopLivePoll() {
        livePolling = false;
        uiHandler.removeCallbacks(livePoller);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((Switch) findViewById(R.id.s1)).setChecked(isGyroFixServiceRunning());
        refreshGlobalGyroStatus();
        startLivePoll();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopLivePoll();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus) {
            ((Switch) findViewById(R.id.s1)).setChecked(isGyroFixServiceRunning());
            refreshGlobalGyroStatus();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopLivePoll();
        if (prefs != null) {
            prefs.unregisterOnSharedPreferenceChangeListener(gyroPrefListener);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    // ==================== 原版 7.0：辅助方法 ====================
    private void showHelp() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.help_title)
                .setPositiveButton(R.string.enderstand, (DialogInterface.OnClickListener) null)
                .setCancelable(true)
                .setMessage(R.string.help_content)
                .create().show();
    }

    public boolean isGyroFixServiceRunning() {
        ActivityManager am = (ActivityManager) getSystemService("activity");
        if (am == null) {
            return false;
        }
        List<ActivityManager.RunningServiceInfo> services = am.getRunningServices(Integer.MAX_VALUE);
        if (services == null || services.isEmpty()) {
            return false;
        }
        for (ActivityManager.RunningServiceInfo info : services) {
            if (info != null && info.service != null
                    && GyroFixService.class.getName().equals(info.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}