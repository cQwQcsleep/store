package com.android.tools.r8.ir.optimize;

import com.android.tools.r8.AssertionsConfiguration;

/* JADX INFO: renamed from: com.android.tools.r8.ir.optimize.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract /* synthetic */ class AbstractC3248d {
    public static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[((AssertionsConfiguration.a[]) AssertionsConfiguration.a.e.clone()).length];
        a = iArr;
        try {
            iArr[AssertionsConfiguration.a.c.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            a[AssertionsConfiguration.a.d.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            a[AssertionsConfiguration.a.b.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
    }
}
