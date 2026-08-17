package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class S3 {
    public static void a(int i, int i2, int i3) {
        if (i2 < 0) {
            throw new ArrayIndexOutOfBoundsException(AbstractC1784iv.a(i2, "Start index (", ") is negative"));
        }
        if (i2 > i3) {
            pnd.a("Start index (", i2, ") is greater than end index (", i3, ")");
        } else {
            if (i3 <= i) {
                return;
            }
            sg0.a("End index (", i3, i);
        }
    }
}
