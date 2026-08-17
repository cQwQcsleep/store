package com.android.tools.r8.internal;

import java.lang.annotation.RetentionPolicy;

/* JADX INFO: renamed from: com.android.tools.r8.internal.zE, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract /* synthetic */ class AbstractC3181zE {
    public static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[RetentionPolicy.values().length];
        a = iArr;
        try {
            iArr[RetentionPolicy.RUNTIME.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            a[RetentionPolicy.CLASS.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            a[RetentionPolicy.SOURCE.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
    }
}
