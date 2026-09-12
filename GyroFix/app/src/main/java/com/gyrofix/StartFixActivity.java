package com.gyrofix;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

/** 原版 7.0：启动修复服务（桌面快捷方式/磁贴入口），带权限前置检查 */
public class StartFixActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(android.R.style.Theme_NoDisplay);
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 26
                && getSharedPreferences("data", 0).getBoolean("foreground", true)) {
            if (Build.VERSION.SDK_INT >= 33) {
                NotificationManager nm = (NotificationManager) getSystemService("notification");
                if (nm != null && !nm.areNotificationsEnabled()) {
                    return; // 未授权通知，前台服务无法启动
                }
            }
            startForegroundService(new Intent(this, GyroFixService.class));
            return;
        }
        startService(new Intent(this, GyroFixService.class));
    }

    @Override
    protected void onResume() {
        finish();
        super.onResume();
    }
}