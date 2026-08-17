package com.sun.jna;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface CallbackProxy extends Callback {
    Object callback(Object[] objArr);

    Class<?>[] getParameterTypes();

    Class<?> getReturnType();
}
