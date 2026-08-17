package com.android.tools.r8.graph;

/* JADX INFO: renamed from: com.android.tools.r8.graph.l2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0246l2 extends D1 {
    public final C0245l1 a;
    public final C0322w2 b;
    public final C0322w2 c;
    public final C0322w2 d;

    public C0246l2(B1 b1) {
        this.a = b1.a(b1.U1, b1.o2, "TYPE");
        this.b = b1.a(b1.U1, b1.a(b1.D1, new I2[0]), "shortValue");
        this.c = b1.a(b1.U1, b1.a(b1.Y1, new I2[0]), "toString");
        I2 i2 = b1.U1;
        this.d = b1.a(i2, b1.a(i2, b1.D1), "valueOf");
    }

    @Override // com.android.tools.r8.graph.D1
    public final C0245l1 a() {
        return this.a;
    }
}
