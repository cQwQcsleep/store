package com.android.tools.r8.graph;

import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.graph.f5, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0207f5 extends T4.c {
    public C0207f5(E0 e0, D2 d2, C0231j1 c0231j1) {
        super(e0, d2, c0231j1);
    }

    @Override // com.android.tools.r8.graph.T4.c
    public final T4.c a(E0 e0) {
        return e0 != this.b ? new C0207f5(e0, (D2) d(), q()) : this;
    }

    @Override // com.android.tools.r8.graph.T4
    public final C0207f5 n() {
        return this;
    }

    @Override // com.android.tools.r8.graph.T4
    public final void a(Consumer consumer, Consumer consumer2, Consumer consumer3, Consumer consumer4) {
        consumer.accept(this);
    }
}
