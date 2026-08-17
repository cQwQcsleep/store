package com.android.tools.r8.dex;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract /* synthetic */ class V {
    public static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[W.b.values().length];
        a = iArr;
        try {
            iArr[W.b.b.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            a[W.b.d.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            a[W.b.e.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
    }
}
