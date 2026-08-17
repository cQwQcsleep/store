package com.intellij.util.io.zip;

import java.util.Calendar;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class DosTime {
    public static long dosToJavaTime(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.set((int) (((j >> 25) & 127) + 1980), (int) (((j >> 21) & 15) - 1), (int) ((j >> 16) & 31), (int) ((j >> 11) & 31), (int) ((j >> 5) & 63), (int) ((j << 1) & 62));
        calendar.clear(14);
        return calendar.getTimeInMillis();
    }

    public static long javaToDosTime(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        int i = calendar.get(1);
        if (i < 1980) {
            return 2162688L;
        }
        return ((long) (calendar.get(13) >> 1)) | ((((long) i) - 1980) << 25) | ((long) ((calendar.get(2) + 1) << 21)) | ((long) (calendar.get(5) << 16)) | ((long) (calendar.get(11) << 11)) | ((long) (calendar.get(12) << 5));
    }
}
