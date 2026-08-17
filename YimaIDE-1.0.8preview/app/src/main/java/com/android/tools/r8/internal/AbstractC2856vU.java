package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.vU, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2856vU {
    public static Object[] a(int i, Object[] objArr) {
        for (int i2 = 0; i2 < i; i2++) {
            if (objArr[i2] == null) {
                x0e.a(CX.a(i2, "at index "));
                return null;
            }
        }
        return objArr;
    }
}
