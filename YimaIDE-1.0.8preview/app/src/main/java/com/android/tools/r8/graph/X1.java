package com.android.tools.r8.graph;

import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class X1 extends AbstractC0169a2 {
    public final C0245l1 a;
    public final C0245l1 b;
    public final C0245l1 c;
    public final C0245l1 d;
    public final C0245l1 e;
    public final C0245l1 f;
    public final C0245l1 g;

    public X1(B1 b1) {
        I2 i2 = b1.E3;
        this.a = b1.a(i2, i2, "DAYS");
        I2 i3 = b1.E3;
        this.b = b1.a(i3, i3, "HOURS");
        I2 i4 = b1.E3;
        this.c = b1.a(i4, i4, "MICROSECONDS");
        I2 i5 = b1.E3;
        this.d = b1.a(i5, i5, "MILLISECONDS");
        I2 i6 = b1.E3;
        this.e = b1.a(i6, i6, "MINUTES");
        I2 i7 = b1.E3;
        this.f = b1.a(i7, i7, "NANOSECONDS");
        I2 i8 = b1.E3;
        this.g = b1.a(i8, i8, "SECONDS");
    }

    @Override // com.android.tools.r8.graph.AbstractC0169a2
    public final void a(Consumer consumer) {
        consumer.accept(this.a);
        consumer.accept(this.b);
        consumer.accept(this.c);
        consumer.accept(this.d);
        consumer.accept(this.e);
        consumer.accept(this.f);
        consumer.accept(this.g);
    }
}
