package com.sun.jna;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface NativeMapped {
    Object fromNative(Object obj, FromNativeContext fromNativeContext);

    Class<?> nativeType();

    Object toNative();
}
