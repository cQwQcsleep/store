package com.android.tools.r8.graph;

import com.android.tools.r8.internal.AbstractC2554rv;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class H1 {
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
    public final C0322w2 m;
    public final C0322w2 n;
    public final AbstractC2554rv o;
    public final AbstractC2554rv p;

    public H1(B1 b1) {
        this.d = b1.a(b1.o2, b1.a(b1.q2, new I2[0]), "getClassLoader");
        this.m = b1.a(b1.o2, b1.a(b1.p2, new I2[0]), "getPackage");
        H2 h2 = b1.M0;
        H2 h3 = b1.s0;
        H2 h4 = b1.w;
        H2[] h2Arr = H2.g;
        this.a = b1.a(h2, h3, h4, h2Arr);
        H2 h5 = b1.M0;
        this.b = b1.a(h5, b1.t0, h5, new H2[]{b1.J0});
        H2 h6 = b1.M0;
        this.c = b1.a(h6, b1.t0, h6, new H2[]{b1.J0, b1.w, b1.N0});
        C0322w2 c0322w2A = b1.a(b1.M0, b1.u0, b1.J0, h2Arr);
        this.e = c0322w2A;
        C0322w2 c0322w2A2 = b1.a(b1.M0, b1.v0, b1.J0, h2Arr);
        this.f = c0322w2A2;
        C0322w2 c0322w2A3 = b1.a(b1.M0, b1.w0, b1.J0, h2Arr);
        this.g = c0322w2A3;
        C0322w2 c0322w2A4 = b1.a(b1.M0, b1.x0, b1.J0, h2Arr);
        this.h = c0322w2A4;
        this.i = b1.a(b1.o2, b1.a(b1.r2, b1.e2), "getConstructor");
        this.j = b1.a(b1.M0, b1.y0, b1.P0, new H2[]{b1.O0});
        C0322w2 c0322w2A5 = b1.a(b1.M0, b1.z0, b1.Q0, new H2[]{b1.J0});
        C0322w2 c0322w2A6 = b1.a(b1.M0, b1.A0, b1.Q0, new H2[]{b1.J0});
        C0322w2 c0322w2A7 = b1.a(b1.M0, b1.B0, b1.R0, new H2[]{b1.J0, b1.O0});
        this.k = c0322w2A7;
        C0322w2 c0322w2A8 = b1.a(b1.M0, b1.C0, b1.R0, new H2[]{b1.J0, b1.O0});
        this.l = c0322w2A8;
        this.n = b1.a(b1.M0, b1.D0, b1.K0, h2Arr);
        this.o = AbstractC2554rv.a(4, 4, c0322w2A5, c0322w2A6, c0322w2A7, c0322w2A8);
        this.p = AbstractC2554rv.a(4, 4, c0322w2A, c0322w2A2, c0322w2A3, c0322w2A4);
    }

    public final boolean a(C0322w2 c0322w2) {
        return c0322w2 == this.b || c0322w2 == this.c;
    }
}
