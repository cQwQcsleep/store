package com.android.tools.r8.internal;

import com.android.tools.r8.DiagnosticsLevel;

/* JADX INFO: renamed from: com.android.tools.r8.internal.s50, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract /* synthetic */ class AbstractC2571s50 {
    public static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[DiagnosticsLevel.values().length];
        a = iArr;
        try {
            iArr[DiagnosticsLevel.INFO.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            a[DiagnosticsLevel.WARNING.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            a[DiagnosticsLevel.ERROR.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            a[DiagnosticsLevel.NONE.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
    }
}
