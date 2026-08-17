package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Ws, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0938Ws {
    public static int a(int i, float f) {
        long jMax = Math.max(2L, b((long) Math.ceil(i / f)));
        if (jMax <= 1073741824) {
            return (int) jMax;
        }
        u56.a(i, f);
        return 0;
    }

    public static long b(long j) {
        if (j == 0) {
            return 1L;
        }
        long j2 = j - 1;
        long j3 = j2 | (j2 >> 1);
        long j4 = j3 | (j3 >> 2);
        long j5 = j4 | (j4 >> 4);
        long j6 = j5 | (j5 >> 8);
        long j7 = j6 | (j6 >> 16);
        return (j7 | (j7 >> 32)) + 1;
    }

    public static final long a(long j) {
        long j2 = j * (-7046029254386353131L);
        long j3 = j2 ^ (j2 >>> 32);
        return j3 ^ (j3 >>> 16);
    }

    public static final int a(int i) {
        int i2 = i * (-1640531527);
        return i2 ^ (i2 >>> 16);
    }

    public static int b(int i, float f) {
        return Math.min((int) Math.ceil(i * f), i - 1);
    }
}
