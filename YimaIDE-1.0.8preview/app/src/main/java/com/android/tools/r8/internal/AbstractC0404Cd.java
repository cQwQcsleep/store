package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Cd, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract /* synthetic */ class AbstractC0404Cd {
    public static final /* synthetic */ int[] a;
    public static final /* synthetic */ int[] b;

    static {
        int[] iArr = new int[EnumC0430Dd.values().length];
        b = iArr;
        try {
            iArr[EnumC0430Dd.b.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            b[EnumC0430Dd.c.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            b[EnumC0430Dd.d.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        int[] iArr2 = new int[((US[]) US.i.clone()).length];
        a = iArr2;
        try {
            iArr2[6] = 1;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            a[5] = 2;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            a[US.f.ordinal()] = 3;
        } catch (NoSuchFieldError unused6) {
        }
    }
}
