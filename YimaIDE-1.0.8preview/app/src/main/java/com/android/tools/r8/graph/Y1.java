package com.android.tools.r8.graph;

import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Y1 extends AbstractC0169a2 {
    public final C0245l1 a;
    public final C0245l1 b;
    public final C0245l1 c;

    public Y1(B1 b1) {
        I2 i2 = b1.H3;
        this.a = b1.a(i2, i2, "ENGLISH");
        I2 i3 = b1.H3;
        this.b = b1.a(i3, i3, "ROOT");
        I2 i4 = b1.H3;
        this.c = b1.a(i4, i4, "US");
    }

    @Override // com.android.tools.r8.graph.AbstractC0169a2
    public final void a(Consumer consumer) {
        consumer.accept(this.a);
        consumer.accept(this.b);
        consumer.accept(this.c);
    }
}
