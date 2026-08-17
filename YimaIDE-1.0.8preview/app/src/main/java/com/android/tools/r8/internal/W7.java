package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class W7 {
    public static final /* synthetic */ boolean a = true;

    public static void a(long j, Y7 y7) {
        y7.a(((int) (j >> 56)) & 255);
        y7.a(((int) (j >> 48)) & 255);
        y7.a(((int) (j >> 40)) & 255);
        y7.a(((int) (j >> 32)) & 255);
        y7.a(((int) (j >> 24)) & 255);
        y7.a(((int) (j >> 16)) & 255);
        y7.a(((int) (j >> 8)) & 255);
        y7.a(((int) j) & 255);
    }

    public static boolean b(int i) {
        return i >= 0 && i <= 65535;
    }

    public static int a(int i) {
        if (a || (i >= 0 && i <= 255)) {
            return i & 255;
        }
        x1f.a();
        return 0;
    }

    public static void a(int i, Y7 y7) {
        y7.a((i >> 24) & 255);
        y7.a((i >> 16) & 255);
        y7.a((i >> 8) & 255);
        y7.a(i & 255);
    }

    public static int a(int i, int i2) {
        return i | (1 << (i2 - 1));
    }
}
