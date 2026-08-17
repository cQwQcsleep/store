package com.android.tools.r8.graph;

import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.graph.z3, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0344z3 extends AbstractC0330x3.a {
    public C0344z3(E0 e0, D2 d2, C0210g1 c0210g1) {
        super(e0, d2, c0210g1);
    }

    @Override // com.android.tools.r8.graph.AbstractC0330x3
    public final void a(Consumer consumer, Consumer consumer2, Consumer consumer3) {
        consumer.accept(this);
    }

    @Override // com.android.tools.r8.graph.AbstractC0330x3
    public final C0344z3 m() {
        return this;
    }

    @Override // com.android.tools.r8.graph.AbstractC0330x3
    public final C0346z5 o() {
        return r();
    }

    @Override // com.android.tools.r8.graph.AbstractC0330x3
    public final C0346z5 r() {
        return new C0346z5((D2) d(), this.d);
    }

    @Override // com.android.tools.r8.graph.AbstractC0330x3
    public final boolean t() {
        return true;
    }

    @Override // com.android.tools.r8.graph.AbstractC0330x3
    public final boolean u() {
        return true;
    }
}
