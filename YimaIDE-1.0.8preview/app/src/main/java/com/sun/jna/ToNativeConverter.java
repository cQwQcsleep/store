package com.sun.jna;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface ToNativeConverter {
    Class<?> nativeType();

    Object toNative(Object obj, ToNativeContext toNativeContext);
}
