package com.android.tools.r8.graph;

/* JADX INFO: renamed from: com.android.tools.r8.graph.f2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0204f2 {
    public final C0322w2 a;
    public final C0322w2 b;
    public final C0322w2 c;
    public final C0322w2 d;
    public final C0322w2 e;
    public final C0322w2 f;
    public final C0322w2 g;
    public final C0322w2 h;
    public final C0322w2 i;
    public final C0322w2 j;
    public final C0322w2 k;
    public final C0322w2 l;

    public C0204f2(B1 b1) {
        I2 i2 = b1.i2;
        I2 i3 = b1.w1;
        I2 i4 = b1.a2;
        this.a = b1.a(i2, b1.a(i3, i4, i4), "equals");
        this.b = b1.a(b1.i2, b1.a(b1.B1, b1.d2), "hash");
        this.c = b1.a(b1.i2, b1.a(b1.B1, b1.a2), "hashCode");
        this.d = b1.a(b1.i2, b1.a(b1.w1, b1.a2), "isNull");
        this.e = b1.a(b1.i2, b1.a(b1.w1, b1.a2), "nonNull");
        I2 i5 = b1.i2;
        I2 i6 = b1.a2;
        this.i = b1.a(i5, b1.a(i6, i6, i6), "requireNonNullElse");
        I2 i7 = b1.i2;
        I2 i8 = b1.a2;
        this.j = b1.a(i7, b1.a(i8, i8, b1.T2), "requireNonNullElseGet");
        this.k = b1.a(b1.i2, b1.a(b1.Y1, b1.a2), "toString");
        I2 i9 = b1.i2;
        I2 i10 = b1.Y1;
        this.l = b1.a(i9, b1.a(i10, b1.a2, i10), "toString");
        H2 h2C = b1.c("requireNonNull");
        I2 i11 = b1.i2;
        I2 i12 = b1.a2;
        this.f = b1.a(i11, b1.a(i12, i12), h2C);
        I2 i13 = b1.i2;
        I2 i14 = b1.a2;
        this.g = b1.a(i13, b1.a(i14, i14, b1.Y1), h2C);
        I2 i15 = b1.i2;
        I2 i16 = b1.a2;
        this.h = b1.a(i15, b1.a(i16, i16, b1.T2), h2C);
    }
}
