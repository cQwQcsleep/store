package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class A6 {
    public static final /* synthetic */ boolean a = true;

    public static boolean a(int i, int i2) {
        if (a || ((i - 1) & i) == 0) {
            return ((i - 1) & i2) == 0;
        }
        x1f.a();
        return false;
    }

    public static boolean b(int i, int i2) {
        return (i & i2) != 0;
    }

    public static boolean c(int i, int i2) {
        return b(i, 1 << (i2 - 1));
    }
}
