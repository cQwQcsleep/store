package com.android.tools.r8.shaking;

import com.android.tools.r8.internal.EnumC2630sm0;

/* JADX INFO: renamed from: com.android.tools.r8.shaking.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract /* synthetic */ class AbstractC3433o {
    public static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[EnumC2630sm0.values().length];
        a = iArr;
        try {
            iArr[EnumC2630sm0.c.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            a[EnumC2630sm0.e.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            a[EnumC2630sm0.b.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
    }
}
