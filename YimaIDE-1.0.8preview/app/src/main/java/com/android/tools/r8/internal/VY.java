package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class VY {
    public static final int a(int i, int i2, int i3) {
        if (i3 > 0) {
            if (i < i2) {
                return i2 - a(a(i2, i3) - a(i, i3), i3);
            }
        } else {
            if (i3 >= 0) {
                w01.a("Step is zero.");
                return 0;
            }
            if (i > i2) {
                int i4 = -i3;
                return a(a(i, i4) - a(i2, i4), i4) + i2;
            }
        }
        return i2;
    }

    public static final int a(int i, int i2) {
        int i3 = i % i2;
        return i3 >= 0 ? i3 : i3 + i2;
    }
}
