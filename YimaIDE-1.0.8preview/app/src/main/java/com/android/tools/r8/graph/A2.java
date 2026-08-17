package com.android.tools.r8.graph;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class A2 extends B2 {
    public final H2 c;
    public final E2 d;

    public A2(E2 e2, H2 h2) {
        this.c = h2;
        this.d = e2;
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.x R() {
        return this;
    }

    @Override // com.android.tools.r8.graph.B2
    public final H2 a() {
        return this.c;
    }

    @Override // com.android.tools.r8.graph.B2
    public final E2 b() {
        return this.d;
    }
}
