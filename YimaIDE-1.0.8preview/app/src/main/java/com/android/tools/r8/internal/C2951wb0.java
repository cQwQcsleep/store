package com.android.tools.r8.internal;

import com.android.tools.r8.graph.AbstractC0223i0;
import com.android.tools.r8.graph.B1;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.internal.AbstractC0855Tn;
import com.android.tools.r8.internal.C2951wb0;
import com.android.tools.r8.synthesis.N;
import com.android.tools.r8.synthesis.S;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.wb0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2951wb0 extends AbstractC1011Zn {
    public final com.android.tools.r8.graph.D2 b;
    public final com.android.tools.r8.graph.B5 c;

    public C2951wb0(com.android.tools.r8.graph.D2 d2, com.android.tools.r8.graph.D2 d3, com.android.tools.r8.graph.B5 b5) {
        super(d3);
        this.b = d2;
        this.c = b5;
    }

    public static AbstractC0223i0 b(com.android.tools.r8.graph.B1 b1, C0322w2 c0322w2) {
        K9 k9 = new K9();
        K9 k10 = new K9();
        K9 k11 = new K9();
        K9 k12 = new K9();
        com.android.tools.r8.graph.I2 i2 = c0322w2.f;
        El0 el0 = El0.c;
        P9 p9 = new P9(el0, 0);
        C2492r9 c2492r9 = new C2492r9(EnumC2211nu.g, el0, k11);
        W9 w9 = new W9(b1.e("Ljava/lang/NullPointerException;"));
        C0479Fa c0479Fa = new C0479Fa(C0479Fa.a.e);
        G9 g9 = new G9(183, b1.a(b1.e("Ljava/lang/NullPointerException;"), b1.a(b1.E1, new com.android.tools.r8.graph.I2[0]), b1.c("<init>")), false);
        C0946Xa c0946Xa = new C0946Xa();
        int i = InterfaceC1101ar.a;
        AbstractC0551Hu abstractC0551HuA = AbstractC0551Hu.a(k9, p9, c2492r9, k10, w9, c0479Fa, g9, c0946Xa, k11, new C1724i9(new C0813Rx(new int[]{0}, new InterfaceC1101ar[]{C1469fA.c})), new C3034xa(), k12, new AbstractC3175z9[0]);
        P40 p40 = P40.e;
        return new com.android.tools.r8.graph.G(i2, 2, 1, abstractC0551HuA, p40, p40);
    }

    public static AbstractC0223i0 c(com.android.tools.r8.graph.B1 b1, C0322w2 c0322w2) {
        K9 k9 = new K9();
        K9 k10 = new K9();
        K9 k11 = new K9();
        K9 k12 = new K9();
        com.android.tools.r8.graph.I2 i2 = c0322w2.f;
        El0 el0 = El0.c;
        P9 p9 = new P9(el0, 0);
        C2492r9 c2492r9 = new C2492r9(EnumC2211nu.g, el0, k11);
        W9 w9 = new W9(b1.e("Ljava/lang/NullPointerException;"));
        C0479Fa c0479Fa = new C0479Fa(C0479Fa.a.e);
        P9 p10 = new P9(El0.b, 1);
        G9 g9 = new G9(183, b1.a(b1.e("Ljava/lang/NullPointerException;"), b1.a(b1.E1, b1.Y1), b1.c("<init>")), false);
        C0946Xa c0946Xa = new C0946Xa();
        int i = InterfaceC1101ar.a;
        AbstractC0551Hu abstractC0551HuA = AbstractC0551Hu.a(k9, p9, c2492r9, k10, w9, c0479Fa, p10, g9, c0946Xa, k11, new C1724i9(new C0813Rx(new int[]{0, 1}, new InterfaceC1101ar[]{C1469fA.c, InterfaceC1101ar.b(b1.Y1)})), new C3034xa(), k12);
        P40 p40 = P40.e;
        return new com.android.tools.r8.graph.G(i2, 3, 2, abstractC0551HuA, p40, p40);
    }

    public static AbstractC0223i0 d(com.android.tools.r8.graph.B1 b1, C0322w2 c0322w2) {
        K9 k9 = new K9();
        K9 k10 = new K9();
        K9 k11 = new K9();
        K9 k12 = new K9();
        com.android.tools.r8.graph.I2 i2 = c0322w2.f;
        El0 el0 = El0.c;
        P9 p9 = new P9(el0, 0);
        C2492r9 c2492r9 = new C2492r9(EnumC2211nu.b, el0, k10);
        P9 p10 = new P9(el0, 1);
        C2492r9 c2492r10 = new C2492r9(EnumC2211nu.g, el0, k11);
        int i = InterfaceC1101ar.a;
        C1469fA c1469fA = C1469fA.c;
        AbstractC0551Hu abstractC0551HuA = AbstractC0551Hu.a(k9, p9, c2492r9, p10, c2492r10, k10, new C1724i9(new C0813Rx(new int[]{0, 1}, new InterfaceC1101ar[]{c1469fA, c1469fA})), new W9(b1.e("Ljava/lang/NullPointerException;")), new C0479Fa(C0479Fa.a.e), new G9(183, b1.a(b1.e("Ljava/lang/NullPointerException;"), b1.a(b1.E1, new com.android.tools.r8.graph.I2[0]), b1.c("<init>")), false), new C0946Xa(), k11, new C1724i9(new C0813Rx(new int[]{0, 1}, new InterfaceC1101ar[]{c1469fA, c1469fA})), new P9(el0, 0), new P9(el0, 1), new C3004x8(C3004x8.a.c, US.e), new C2948wa(el0), k12);
        P40 p40 = P40.e;
        return new com.android.tools.r8.graph.G(i2, 2, 2, abstractC0551HuA, p40, p40);
    }

    public static AbstractC0223i0 g(com.android.tools.r8.graph.B1 b1, C0322w2 c0322w2) {
        K9 k9 = new K9();
        K9 k10 = new K9();
        K9 k11 = new K9();
        K9 k12 = new K9();
        com.android.tools.r8.graph.I2 i2 = c0322w2.f;
        El0 el0 = El0.c;
        P9 p9 = new P9(el0, 0);
        C2492r9 c2492r9 = new C2492r9(EnumC2211nu.g, el0, k11);
        W9 w9 = new W9(b1.e("Ljava/lang/NullPointerException;"));
        C0479Fa c0479Fa = new C0479Fa(C0479Fa.a.e);
        G9 g9 = new G9(183, b1.a(b1.e("Ljava/lang/NullPointerException;"), b1.a(b1.E1, new com.android.tools.r8.graph.I2[0]), b1.c("<init>")), false);
        C0946Xa c0946Xa = new C0946Xa();
        int i = InterfaceC1101ar.a;
        AbstractC0551Hu abstractC0551HuA = AbstractC0551Hu.a(k9, p9, c2492r9, k10, w9, c0479Fa, g9, c0946Xa, k11, new C1724i9(new C0813Rx(new int[]{0}, new InterfaceC1101ar[]{C1469fA.c})), new P9(el0, 0), new C1129b9(1L, el0), new C3004x8(C3004x8.a.c, US.e), new C2948wa(el0), k12);
        P40 p40 = P40.e;
        return new com.android.tools.r8.graph.G(i2, 2, 1, abstractC0551HuA, p40, p40);
    }

    public final com.android.tools.r8.graph.B5 a(final C0333y c0333y, com.android.tools.r8.graph.H2 h2, com.android.tools.r8.graph.E2 e2, final com.android.tools.r8.synthesis.M m) {
        com.android.tools.r8.synthesis.J jG = c0333y.a.g();
        com.android.tools.r8.synthesis.I i = new com.android.tools.r8.synthesis.I() { // from class: poi
            @Override // com.android.tools.r8.synthesis.I
            public final S.b a(S s) {
                return s.i;
            }
        };
        com.android.tools.r8.graph.D2 d2 = this.a;
        Consumer consumerB = C0822Sg.b();
        Consumer consumer = new Consumer() { // from class: qoi
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                C2951wb0.a(c0333y, m, (N) obj);
            }
        };
        jG.getClass();
        return jG.a(h2, e2, i, d2, c0333y, consumerB, consumer, C0822Sg.b());
    }

    public final com.android.tools.r8.graph.B5 e(C0333y c0333y) {
        final com.android.tools.r8.graph.B1 b1A = c0333y.a();
        com.android.tools.r8.graph.H2 h2X0 = b1A.B4.g.x0();
        com.android.tools.r8.graph.I2 i2 = b1A.B1;
        return a(c0333y, h2X0, b1A.a(i2, i2, i2), new com.android.tools.r8.synthesis.M() { // from class: koi
            @Override // com.android.tools.r8.synthesis.M
            public final AbstractC0223i0 a(C0322w2 c0322w2) {
                return C2951wb0.d(b1A, c0322w2);
            }
        });
    }

    public final com.android.tools.r8.graph.B5 f(C0333y c0333y) {
        final com.android.tools.r8.graph.B1 b1A = c0333y.a();
        com.android.tools.r8.graph.H2 h2X0 = b1A.B4.i.x0();
        com.android.tools.r8.graph.I2 i2 = b1A.w1;
        com.android.tools.r8.graph.I2 i3 = b1A.B1;
        return a(c0333y, h2X0, b1A.a(i2, i3, i3), new com.android.tools.r8.synthesis.M() { // from class: loi
            @Override // com.android.tools.r8.synthesis.M
            public final AbstractC0223i0 a(C0322w2 c0322w2) {
                return AbstractC0855Tn.b(b1A, c0322w2);
            }
        });
    }

    public final com.android.tools.r8.graph.B5 h(C0333y c0333y) {
        final com.android.tools.r8.graph.B1 b1A = c0333y.a();
        com.android.tools.r8.graph.H2 h2X0 = b1A.B4.d.x0();
        com.android.tools.r8.graph.I2 i2 = b1A.B1;
        return a(c0333y, h2X0, b1A.a(i2, i2), new com.android.tools.r8.synthesis.M() { // from class: roi
            @Override // com.android.tools.r8.synthesis.M
            public final AbstractC0223i0 a(C0322w2 c0322w2) {
                return C2951wb0.g(b1A, c0322w2);
            }
        });
    }

    public static void a(C0333y c0333y, com.android.tools.r8.synthesis.M m, com.android.tools.r8.synthesis.N n) {
        n.h = com.android.tools.r8.graph.F4.b(4105, false);
        com.android.tools.r8.androidapi.f fVar = c0333y.U;
        n.l = fVar;
        n.m = fVar;
        n.g = m;
        n.f = C1159bb.g;
    }

    @Override // com.android.tools.r8.internal.AbstractC1011Zn
    public final com.android.tools.r8.graph.D2 a() {
        return this.b;
    }

    @Override // com.android.tools.r8.internal.AbstractC1011Zn
    public final void a(C0333y c0333y) {
        c(c0333y);
        d(c0333y);
        e(c0333y);
        f(c0333y);
        g(c0333y);
        h(c0333y);
        b(c0333y);
    }

    public final com.android.tools.r8.graph.B5 b(C0333y c0333y) {
        final com.android.tools.r8.graph.B1 b1A = c0333y.a();
        return a(c0333y, b1A.c("boxedOrdinalOrNull"), b1A.a(b1A.S1, b1A.B1), new com.android.tools.r8.synthesis.M() { // from class: ooi
            @Override // com.android.tools.r8.synthesis.M
            public final AbstractC0223i0 a(C0322w2 c0322w2) {
                return AbstractC0855Tn.a(b1A, c0322w2);
            }
        });
    }

    public final com.android.tools.r8.graph.B5 c(C0333y c0333y) {
        final com.android.tools.r8.graph.B1 b1A = c0333y.a();
        return a(c0333y, b1A.c("checkNotZero"), b1A.a(b1A.E1, b1A.B1), new com.android.tools.r8.synthesis.M() { // from class: moi
            @Override // com.android.tools.r8.synthesis.M
            public final AbstractC0223i0 a(C0322w2 c0322w2) {
                return C2951wb0.b(b1A, c0322w2);
            }
        });
    }

    public final com.android.tools.r8.graph.B5 g(C0333y c0333y) {
        final com.android.tools.r8.graph.B1 b1A = c0333y.a();
        com.android.tools.r8.graph.H2 h2C = b1A.c("objects$equals");
        com.android.tools.r8.graph.I2 i2 = b1A.w1;
        com.android.tools.r8.graph.I2 i3 = b1A.B1;
        return a(c0333y, h2C, b1A.a(i2, i3, i3), new com.android.tools.r8.synthesis.M() { // from class: noi
            @Override // com.android.tools.r8.synthesis.M
            public final AbstractC0223i0 a(C0322w2 c0322w2) {
                B1 b1 = b1A;
                return AbstractC0855Tn.a(c0322w2);
            }
        });
    }

    public final com.android.tools.r8.graph.B5 d(C0333y c0333y) {
        final com.android.tools.r8.graph.B1 b1A = c0333y.a();
        return a(c0333y, b1A.c("checkNotZero"), b1A.a(b1A.E1, b1A.B1, b1A.Y1), new com.android.tools.r8.synthesis.M() { // from class: joi
            @Override // com.android.tools.r8.synthesis.M
            public final AbstractC0223i0 a(C0322w2 c0322w2) {
                return C2951wb0.c(b1A, c0322w2);
            }
        });
    }
}
