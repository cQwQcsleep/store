package com.android.tools.r8.graph;

import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface S extends InterfaceC0265o0 {
    H4 V();

    C0281q2 Z();

    void a(Consumer consumer);

    boolean b0();

    void c(Consumer consumer);

    I2 getType();

    @Override // com.android.tools.r8.graph.InterfaceC0265o0
    default boolean isClass() {
        return true;
    }

    boolean isInterface();

    I0 m();
}
