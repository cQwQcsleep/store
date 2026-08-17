package com.android.tools.r8.graph;

import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.graph.x4, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface InterfaceC0331x4 extends C4 {
    E0 a();

    @Override // com.android.tools.r8.graph.C4
    default void a(Consumer consumer, Consumer consumer2) {
        consumer.accept(this);
    }

    C0231j1 e();

    @Override // com.android.tools.r8.graph.C4
    default InterfaceC0331x4 f() {
        return this;
    }

    C0322w2 getReference();

    @Override // com.android.tools.r8.graph.C4
    default boolean h() {
        return true;
    }

    H0 q();
}
