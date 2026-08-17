package com.intellij.openapi.util;

import java.lang.ref.SoftReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class ThreadLocalCachedIntArray {
    private final ThreadLocal<SoftReference<int[]>> myThreadLocal = new ThreadLocal<>();

    public int[] getBuffer(int i) {
        int[] iArr = (int[]) com.intellij.reference.SoftReference.dereference(this.myThreadLocal.get());
        if (iArr != null && iArr.length >= i) {
            return iArr;
        }
        int[] iArr2 = new int[i];
        this.myThreadLocal.set(new SoftReference<>(iArr2));
        return iArr2;
    }
}
