package com.android.tools.r8.graph;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class G1 extends D1 {
    public final C0245l1 a;
    public final C0322w2 b;
    public final C0322w2 c;
    public final C0322w2 d;

    public G1(B1 b1) {
        this.a = b1.a(b1.P1, b1.o2, "TYPE");
        this.b = b1.a(b1.P1, b1.a(b1.y1, new I2[0]), "charValue");
        this.c = b1.a(b1.P1, b1.a(b1.Y1, new I2[0]), "toString");
        I2 i2 = b1.P1;
        this.d = b1.a(i2, b1.a(i2, b1.y1), "valueOf");
    }

    @Override // com.android.tools.r8.graph.D1
    public final C0245l1 a() {
        return this.a;
    }
}
