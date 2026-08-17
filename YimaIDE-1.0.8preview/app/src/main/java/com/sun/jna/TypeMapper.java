package com.sun.jna;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface TypeMapper {
    FromNativeConverter getFromNativeConverter(Class<?> cls);

    ToNativeConverter getToNativeConverter(Class<?> cls);
}
