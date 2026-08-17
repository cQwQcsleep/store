package com.android.tools.r8.shaking;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract /* synthetic */ class N2 {
    public static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[((O2[]) O2.f.clone()).length];
        a = iArr;
        try {
            iArr[O2.b.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            a[O2.c.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            a[2] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            a[3] = 4;
        } catch (NoSuchFieldError unused4) {
        }
    }
}
