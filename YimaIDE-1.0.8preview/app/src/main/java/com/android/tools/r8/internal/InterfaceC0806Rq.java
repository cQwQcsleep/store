package com.android.tools.r8.internal;

import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Rq, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface InterfaceC0806Rq<T> {
    default void a(final InterfaceC2494rA interfaceC2494rA) {
        final C1131bA c1131bA = new C1131bA();
        forEach(new Consumer() { // from class: alc
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                interfaceC2494rA.a(c1131bA.b(), obj);
            }
        });
    }

    void forEach(Consumer<T> consumer);
}
