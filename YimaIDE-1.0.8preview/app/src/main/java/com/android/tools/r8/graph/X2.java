package com.android.tools.r8.graph;

import com.android.tools.r8.AbstractC0007c;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract /* synthetic */ class X2 {
    public static final /* synthetic */ boolean a;
    public static final /* synthetic */ int[] b;

    static {
        int[] iArr = new int[AbstractC0007c.c(4).length];
        b = iArr;
        try {
            iArr[0] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            b[1] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            b[2] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            b[3] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        a = true;
    }
}
