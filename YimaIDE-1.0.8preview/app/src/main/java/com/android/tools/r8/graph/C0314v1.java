package com.android.tools.r8.graph;

import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.graph.v1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0314v1 extends AbstractC0169a2 {
    public final C0245l1 a;
    public final C0245l1 b;

    public C0314v1(B1 b1) {
        this.a = b1.a(b1.P3, b1.Q3, "CREATOR");
        I2 i2 = b1.P3;
        this.b = b1.a(i2, i2, "EMPTY");
    }

    @Override // com.android.tools.r8.graph.AbstractC0169a2
    public final void a(Consumer consumer) {
        consumer.accept(this.a);
        consumer.accept(this.b);
    }
}
