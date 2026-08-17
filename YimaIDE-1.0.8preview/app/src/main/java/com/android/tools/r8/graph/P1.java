package com.android.tools.r8.graph;

import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class P1 extends AbstractC0169a2 {
    public final C0245l1 a;
    public final C0245l1 b;

    public P1(B1 b1) {
        this.a = b1.a(b1.z3, b1.Y1, "pathSeparator");
        this.b = b1.a(b1.z3, b1.Y1, "separator");
    }

    @Override // com.android.tools.r8.graph.AbstractC0169a2
    public final void a(Consumer consumer) {
        consumer.accept(this.a);
        consumer.accept(this.b);
    }
}
