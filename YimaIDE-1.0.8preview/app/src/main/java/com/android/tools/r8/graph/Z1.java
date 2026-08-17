package com.android.tools.r8.graph;

import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Z1 extends AbstractC0169a2 {
    public final C0245l1 a;
    public final C0245l1 b;
    public final C0245l1 c;
    public final C0245l1 d;
    public final C0245l1 e;
    public final C0245l1 f;

    public Z1(B1 b1) {
        I2 i2 = b1.I3;
        this.a = b1.a(i2, i2, "CONFIG");
        I2 i3 = b1.I3;
        this.b = b1.a(i3, i3, "FINE");
        I2 i4 = b1.I3;
        this.c = b1.a(i4, i4, "FINER");
        I2 i5 = b1.I3;
        this.d = b1.a(i5, i5, "FINEST");
        I2 i6 = b1.I3;
        this.e = b1.a(i6, i6, "SEVERE");
        I2 i7 = b1.I3;
        this.f = b1.a(i7, i7, "WARNING");
    }

    @Override // com.android.tools.r8.graph.AbstractC0169a2
    public final void a(Consumer consumer) {
        consumer.accept(this.a);
        consumer.accept(this.b);
        consumer.accept(this.c);
        consumer.accept(this.d);
        consumer.accept(this.e);
        consumer.accept(this.f);
    }
}
