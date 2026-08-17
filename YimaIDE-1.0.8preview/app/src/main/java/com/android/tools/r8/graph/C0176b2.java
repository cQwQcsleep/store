package com.android.tools.r8.graph;

import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.graph.b2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0176b2 extends D1 {
    public final C0245l1 a;
    public final C0322w2 b;
    public final C0322w2 c;
    public final C0322w2 d;

    public C0176b2(B1 b1) {
        this.a = b1.a(b1.T1, b1.o2, "TYPE");
        I2 i2 = b1.T1;
        I2 i3 = b1.B1;
        I2 i4 = b1.C1;
        b1.a(i2, b1.a(i3, i4, i4), "compare");
        this.b = b1.a(b1.T1, b1.a(b1.C1, new I2[0]), "longValue");
        this.c = b1.a(b1.T1, b1.a(b1.Y1, new I2[0]), "toString");
        I2 i5 = b1.T1;
        this.d = b1.a(i5, b1.a(i5, b1.C1), "valueOf");
    }

    @Override // com.android.tools.r8.graph.AbstractC0169a2
    public final void a(Consumer consumer) {
        consumer.accept(this.a);
    }

    @Override // com.android.tools.r8.graph.D1
    public final C0245l1 a() {
        return this.a;
    }
}
