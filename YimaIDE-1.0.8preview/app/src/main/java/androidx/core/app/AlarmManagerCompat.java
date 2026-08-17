package androidx.core.app;

import android.app.AlarmManager;
import android.app.PendingIntent;
import androidx.annotation.ReplaceWith;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public final class AlarmManagerCompat {

    public static class Api31Impl {
        private Api31Impl() {
        }

        public static boolean canScheduleExactAlarms(AlarmManager alarmManager) {
            return alarmManager.canScheduleExactAlarms();
        }
    }

    private AlarmManagerCompat() {
    }

    public static boolean canScheduleExactAlarms(AlarmManager alarmManager) {
        return Api31Impl.canScheduleExactAlarms(alarmManager);
    }

    public static void setAlarmClock(AlarmManager alarmManager, long j, PendingIntent pendingIntent, PendingIntent pendingIntent2) {
        alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(j, pendingIntent), pendingIntent2);
    }

    public static void setAndAllowWhileIdle(AlarmManager alarmManager, int i, long j, PendingIntent pendingIntent) {
        alarmManager.setAndAllowWhileIdle(i, j, pendingIntent);
    }

    @ReplaceWith(expression = "alarmManager.setExact(type, triggerAtMillis, operation)")
    @Deprecated
    public static void setExact(AlarmManager alarmManager, int i, long j, PendingIntent pendingIntent) {
        alarmManager.setExact(i, j, pendingIntent);
    }

    public static void setExactAndAllowWhileIdle(AlarmManager alarmManager, int i, long j, PendingIntent pendingIntent) {
        alarmManager.setExactAndAllowWhileIdle(i, j, pendingIntent);
    }
}
