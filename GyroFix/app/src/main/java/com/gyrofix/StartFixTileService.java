package com.gyrofix;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 原版 7.0：快捷设置磁贴，一键启停陀螺仪修复服务。
 * 保持原版行为不变（只做加法，不做减法）。
 */
public class StartFixTileService extends TileService {

    @Override
    public void onTileAdded() {
        Tile tile = getQsTile();
        if (tile != null) {
            tile.setState(isGyroFixServiceRunning() ? Tile.STATE_ACTIVE : Tile.STATE_INACTIVE);
            tile.updateTile();
        }
        super.onTileAdded();
    }

    @Override
    public void onStartListening() {
        Tile tile = getQsTile();
        if (tile != null) {
            tile.setState(isGyroFixServiceRunning() ? Tile.STATE_ACTIVE : Tile.STATE_INACTIVE);
            tile.updateTile();
        }
        super.onStartListening();
    }

    @Override
    public void onClick() {
        Tile tile = getQsTile();
        if (tile == null) {
            return;
        }
        if (tile.getState() == Tile.STATE_ACTIVE) {
            stopService(new Intent(this, GyroFixService.class));
            tile.setState(Tile.STATE_INACTIVE);
        } else {
            if (Build.VERSION.SDK_INT >= 26) {
                SharedPreferences prefs = getSharedPreferences("data", 0);
                if (prefs.getBoolean("foreground", true)) {
                    if (Build.VERSION.SDK_INT >= 33) {
                        NotificationManager nm = (NotificationManager) getSystemService("notification");
                        if (nm != null && !nm.areNotificationsEnabled()) {
                            return; // 未授权通知，前台服务无法启动
                        }
                    }
                    startForegroundService(new Intent(this, GyroFixService.class));
                    tile.setState(Tile.STATE_ACTIVE);
                }
            }
            startService(new Intent(this, GyroFixService.class));
            tile.setState(Tile.STATE_ACTIVE);
        }
        tile.updateTile();

        if (Build.VERSION.SDK_INT <= 30) {
            try {
                Object bar = getSystemService("statusbar");
                Method collapse = bar.getClass().getMethod("collapsePanels");
                collapse.setAccessible(true);
                collapse.invoke(bar);
            } catch (Exception e) {
                launchAndCollapse();
            }
        } else {
            launchAndCollapse();
        }
        super.onClick();
    }

    private void launchAndCollapse() {
        Intent intent = new Intent(this, EmptyActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        if (Build.VERSION.SDK_INT >= 34) {
            startActivityAndCollapse(PendingIntent.getActivity(this, 0, intent,
                    PendingIntent.FLAG_IMMUTABLE));
        } else {
            startActivityAndCollapse(intent);
        }
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