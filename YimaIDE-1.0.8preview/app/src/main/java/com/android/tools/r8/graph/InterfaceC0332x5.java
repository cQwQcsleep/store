package com.android.tools.r8.graph;

import java.util.function.BiFunction;

/* JADX INFO: renamed from: com.android.tools.r8.graph.x5, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface InterfaceC0332x5 extends InterfaceC0265o0, InterfaceC0339y5, D5 {
    default void J() {
        e().J();
    }

    default void a(BiFunction biFunction) {
        e().a(biFunction);
    }

    D2 b();

    @Override // com.android.tools.r8.graph.InterfaceC0265o0
    default InterfaceC0332x5 i() {
        return this;
    }

    @Override // com.android.tools.r8.graph.InterfaceC0265o0
    default boolean j() {
        return true;
    }

    default D2 asClass() {
        return null;
    }

    default B5 c() {
        return null;
    }

    @Override // com.android.tools.r8.graph.InterfaceC0265o0
    default C0346z5 d() {
        return null;
    }

    @Override // com.android.tools.r8.graph.InterfaceC0265o0
    default InterfaceC0339y5 a(InterfaceC0339y5 interfaceC0339y5) {
        return this;
    }
}
