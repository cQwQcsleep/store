package com.intellij.openapi.util;

import com.intellij.util.ArrayUtil;
import java.lang.ref.SoftReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class ThreadLocalCachedByteArray {
    private final ThreadLocal<SoftReference<byte[]>> myThreadLocal = new ThreadLocal<>();

    public byte[] getBuffer(int i) {
        byte[] bArr = (byte[]) com.intellij.reference.SoftReference.dereference(this.myThreadLocal.get());
        if (bArr != null && bArr.length >= i) {
            return bArr;
        }
        byte[] bArrNewByteArray = ArrayUtil.newByteArray(i);
        this.myThreadLocal.set(new SoftReference<>(bArrNewByteArray));
        return bArrNewByteArray;
    }
}
