package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract /* synthetic */ class B3 {
    public static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[((US[]) US.i.clone()).length];
        a = iArr;
        try {
            iArr[6] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            a[5] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            a[US.e.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            a[US.f.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
    }
}
