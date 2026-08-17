package com.android.tools.r8.graph;

/* JADX INFO: renamed from: com.android.tools.r8.graph.k2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0239k2 {
    public final C0322w2 a;
    public final C0322w2 b;
    public final C0322w2 c;
    public final C0322w2 d;

    public C0239k2(B1 b1) {
        H2 h2C = b1.c("load");
        I2 i2 = b1.K2;
        this.a = b1.a(i2, b1.a(i2, b1.o2), h2C);
        I2 i3 = b1.K2;
        this.b = b1.a(i3, b1.a(i3, b1.o2, b1.q2), h2C);
        I2 i4 = b1.K2;
        this.c = b1.a(i4, b1.a(i4, b1.o2), b1.c("loadInstalled"));
        this.d = b1.a(b1.K2, b1.a(b1.D5, new I2[0]), b1.c("iterator"));
    }

    public final boolean a(C0322w2 c0322w2) {
        return c0322w2 == this.a || c0322w2 == this.b || c0322w2 == this.c;
    }
}
