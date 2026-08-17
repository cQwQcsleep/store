package com.android.tools.r8.internal;

import java.lang.Throwable;

/* JADX INFO: renamed from: com.android.tools.r8.internal.kh0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface InterfaceC1936kh0<T, E extends Throwable> {
    default void a(T t) {
        try {
            accept(t);
        } catch (Throwable th) {
            if (!(th instanceof RuntimeException)) {
                throw new RuntimeException(th);
            }
        }
    }

    void accept(T t) throws Throwable;
}
