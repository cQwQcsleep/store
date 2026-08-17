package com.android.tools.r8.graph;

import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class W1 extends AbstractC0169a2 {
    public final C0245l1 a;
    public final C0245l1 b;

    public W1(B1 b1) {
        this.a = b1.a(b1.C3, b1.G3, "EMPTY_LIST");
        this.b = b1.a(b1.C3, b1.K3, "EMPTY_SET");
    }

    @Override // com.android.tools.r8.graph.AbstractC0169a2
    public final void a(Consumer consumer) {
        consumer.accept(this.a);
        consumer.accept(this.b);
    }
}
