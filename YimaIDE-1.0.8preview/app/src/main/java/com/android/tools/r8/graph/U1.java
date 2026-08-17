package com.android.tools.r8.graph;

import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class U1 extends AbstractC0169a2 {
    public final C0245l1 a;
    public final C0245l1 b;

    public U1(B1 b1) {
        I2 i2 = b1.B3;
        this.a = b1.a(i2, i2, "LITTLE_ENDIAN");
        I2 i3 = b1.B3;
        this.b = b1.a(i3, i3, "BIG_ENDIAN");
    }

    @Override // com.android.tools.r8.graph.AbstractC0169a2
    public final void a(Consumer consumer) {
        consumer.accept(this.a);
        consumer.accept(this.b);
    }
}
