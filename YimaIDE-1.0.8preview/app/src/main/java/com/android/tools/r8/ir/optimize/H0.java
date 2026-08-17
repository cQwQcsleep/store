package com.android.tools.r8.ir.optimize;

import com.android.tools.r8.graph.AbstractC0223i0;
import com.android.tools.r8.graph.B1;
import com.android.tools.r8.graph.B5;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.E2;
import com.android.tools.r8.graph.F4;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.AbstractC2856vU;
import com.android.tools.r8.internal.AbstractC3175z9;
import com.android.tools.r8.internal.C0479Fa;
import com.android.tools.r8.internal.C0483Fe;
import com.android.tools.r8.internal.C0813Rx;
import com.android.tools.r8.internal.C0946Xa;
import com.android.tools.r8.internal.C1159bb;
import com.android.tools.r8.internal.C1724i9;
import com.android.tools.r8.internal.C2492r9;
import com.android.tools.r8.internal.C3034xa;
import com.android.tools.r8.internal.El0;
import com.android.tools.r8.internal.EnumC2211nu;
import com.android.tools.r8.internal.G9;
import com.android.tools.r8.internal.InterfaceC1101ar;
import com.android.tools.r8.internal.K9;
import com.android.tools.r8.internal.KO;
import com.android.tools.r8.internal.P40;
import com.android.tools.r8.internal.P9;
import com.android.tools.r8.internal.W9;
import com.android.tools.r8.ir.optimize.H0;
import com.android.tools.r8.synthesis.N;
import com.android.tools.r8.synthesis.S;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class H0 {
    public static AbstractC0223i0 a(B1 b1, C0322w2 c0322w2) {
        K9 k9 = new K9();
        K9 k10 = new K9();
        K9 k11 = new K9();
        K9 k12 = new K9();
        I2 i2 = c0322w2.f;
        El0 el0 = El0.b;
        AbstractC0551Hu abstractC0551HuA = AbstractC0551Hu.a(k9, new P9(el0, 0), new C2492r9(EnumC2211nu.b, el0, k11), k10, new W9(b1.e("Ljava/lang/ClassCastException;")), new C0479Fa(C0479Fa.a.e), new G9(183, b1.a(b1.e("Ljava/lang/ClassCastException;"), b1.a(b1.E1, new I2[0]), b1.c("<init>")), false), new C0946Xa(), k11, new C1724i9(new C0813Rx(new int[]{0}, new InterfaceC1101ar[]{InterfaceC1101ar.b(b1.a2)})), new C3034xa(), k12, new AbstractC3175z9[0]);
        P40 p40 = P40.e;
        return new com.android.tools.r8.graph.G(i2, 2, 1, abstractC0551HuA, p40, p40);
    }

    public static AbstractC0223i0 b(B1 b1, C0322w2 c0322w2) {
        K9 k9 = new K9();
        I2 i2 = c0322w2.f;
        AbstractC0551Hu abstractC0551HuA = AbstractC0551Hu.a(k9, new W9(b1.e("Ljava/lang/IllegalAccessError;")), new C0479Fa(C0479Fa.a.e), new G9(183, b1.a(b1.e("Ljava/lang/IllegalAccessError;"), b1.a(b1.E1, new I2[0]), b1.c("<init>")), false), new C0946Xa());
        P40 p40 = P40.e;
        return new com.android.tools.r8.graph.G(i2, 2, 0, abstractC0551HuA, p40, p40);
    }

    public static AbstractC0223i0 c(B1 b1, C0322w2 c0322w2) {
        K9 k9 = new K9();
        I2 i2 = c0322w2.f;
        AbstractC0551Hu abstractC0551HuA = AbstractC0551Hu.a(k9, new W9(b1.e("Ljava/lang/IncompatibleClassChangeError;")), new C0479Fa(C0479Fa.a.e), new G9(183, b1.a(b1.e("Ljava/lang/IncompatibleClassChangeError;"), b1.a(b1.E1, new I2[0]), b1.c("<init>")), false), new C0946Xa());
        P40 p40 = P40.e;
        return new com.android.tools.r8.graph.G(i2, 2, 0, abstractC0551HuA, p40, p40);
    }

    public static AbstractC0223i0 d(B1 b1, C0322w2 c0322w2) {
        K9 k9 = new K9();
        I2 i2 = c0322w2.f;
        AbstractC0551Hu abstractC0551HuA = AbstractC0551Hu.a(k9, new W9(b1.e("Ljava/lang/NoSuchMethodError;")), new C0479Fa(C0479Fa.a.e), new G9(183, b1.a(b1.e("Ljava/lang/NoSuchMethodError;"), b1.a(b1.E1, new I2[0]), b1.c("<init>")), false), new C0946Xa());
        P40 p40 = P40.e;
        return new com.android.tools.r8.graph.G(i2, 2, 0, abstractC0551HuA, p40, p40);
    }

    public static AbstractC0223i0 e(B1 b1, C0322w2 c0322w2) {
        K9 k9 = new K9();
        K9 k10 = new K9();
        I2 i2 = c0322w2.f;
        AbstractC0551Hu abstractC0551HuA = AbstractC0551Hu.a(k9, new W9(b1.e("Ljava/lang/RuntimeException;")), new C0479Fa(C0479Fa.a.e), new P9(El0.b, 0), new G9(183, b1.a(b1.e("Ljava/lang/RuntimeException;"), b1.a(b1.E1, b1.Y1), b1.c("<init>")), false), new C0946Xa(), k10);
        P40 p40 = P40.e;
        return new com.android.tools.r8.graph.G(i2, 3, 1, abstractC0551HuA, p40, p40);
    }

    public static AbstractC0223i0 f(B1 b1, C0322w2 c0322w2) {
        K9 k9 = new K9();
        K9 k10 = new K9();
        K9 k11 = new K9();
        K9 k12 = new K9();
        I2 i2 = c0322w2.f;
        El0 el0 = El0.b;
        Object[] objArrA = AbstractC2856vU.a(11, new Object[]{k9, new P9(el0, 0), new C2492r9(EnumC2211nu.b, el0, k11), k10, new P9(el0, 0), new G9(182, b1.a(b1.a2, b1.a(b1.Y1, new I2[0]), b1.c("toString")), false), new C0479Fa(C0479Fa.a.c), k11, new C1724i9(new C0813Rx(new int[]{0}, new InterfaceC1101ar[]{InterfaceC1101ar.b(b1.a2)})), new C3034xa(), k12});
        AbstractC0551Hu abstractC0551HuB = AbstractC0551Hu.b(objArrA.length, objArrA);
        P40 p40 = P40.e;
        return new com.android.tools.r8.graph.G(i2, 1, 1, abstractC0551HuB, p40, p40);
    }

    public static G0 b(final C0333y c0333y, KO ko, C0483Fe c0483Fe) {
        final B1 b1A = c0333y.a();
        final E2 e2A = b1A.a(b1A.E1, b1A.a2);
        B5 b5B = c0333y.a.g().b(new com.android.tools.r8.synthesis.I() { // from class: t16
            @Override // com.android.tools.r8.synthesis.I
            public final S.b a(S s) {
                return s.B;
            }
        }, c0483Fe.a(), c0333y, new Consumer() { // from class: v16
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                H0.f(c0333y, b1A, e2A, (N) obj);
            }
        });
        ko.r(b5B, c0483Fe.c);
        return new G0(b5B);
    }

    public static void c(C0333y c0333y, final B1 b1, E2 e2, com.android.tools.r8.synthesis.N n) {
        n.h = F4.b(4105, false);
        n.f = C1159bb.i;
        com.android.tools.r8.androidapi.f fVar = c0333y.U;
        n.l = fVar;
        n.m = fVar;
        n.g = new com.android.tools.r8.synthesis.M() { // from class: c26
            @Override // com.android.tools.r8.synthesis.M
            public final AbstractC0223i0 a(C0322w2 c0322w2) {
                return H0.c(b1, c0322w2);
            }
        };
        n.e = e2;
    }

    public static void d(C0333y c0333y, final B1 b1, E2 e2, com.android.tools.r8.synthesis.N n) {
        n.h = F4.b(4105, false);
        n.f = C1159bb.i;
        com.android.tools.r8.androidapi.f fVar = c0333y.U;
        n.l = fVar;
        n.m = fVar;
        n.g = new com.android.tools.r8.synthesis.M() { // from class: i16
            @Override // com.android.tools.r8.synthesis.M
            public final AbstractC0223i0 a(C0322w2 c0322w2) {
                return H0.d(b1, c0322w2);
            }
        };
        n.e = e2;
    }

    public static void b(C0333y c0333y, final B1 b1, E2 e2, com.android.tools.r8.synthesis.N n) {
        n.h = F4.b(4105, false);
        n.f = C1159bb.i;
        com.android.tools.r8.androidapi.f fVar = c0333y.U;
        n.l = fVar;
        n.m = fVar;
        n.g = new com.android.tools.r8.synthesis.M() { // from class: n16
            @Override // com.android.tools.r8.synthesis.M
            public final AbstractC0223i0 a(C0322w2 c0322w2) {
                return H0.b(b1, c0322w2);
            }
        };
        n.e = e2;
    }

    public static G0 c(final C0333y c0333y, I0 i0, C0483Fe c0483Fe) {
        final B1 b1A = c0333y.a();
        final E2 e2A = b1A.a(b1A.u3, new I2[0]);
        B5 b5B = c0333y.a.g().b(new com.android.tools.r8.synthesis.I() { // from class: l16
            @Override // com.android.tools.r8.synthesis.I
            public final S.b a(S s) {
                return s.F;
            }
        }, c0483Fe.a(), c0333y, new Consumer() { // from class: m16
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                H0.d(c0333y, b1A, e2A, (N) obj);
            }
        });
        i0.s(b5B, c0483Fe.c);
        return new G0(b5B);
    }

    public static G0 d(final C0333y c0333y, I0 i0, C0483Fe c0483Fe) {
        final B1 b1A = c0333y.a();
        final E2 e2A = b1A.a(b1A.m3, b1A.Y1);
        B5 b5B = c0333y.a.g().b(new com.android.tools.r8.synthesis.I() { // from class: x16
            @Override // com.android.tools.r8.synthesis.I
            public final S.b a(S s) {
                return s.G;
            }
        }, c0483Fe.a(), c0333y, new Consumer() { // from class: z16
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                H0.e(c0333y, b1A, e2A, (N) obj);
            }
        });
        i0.n(b5B, c0483Fe.c);
        return new G0(b5B);
    }

    public static void e(C0333y c0333y, final B1 b1, E2 e2, com.android.tools.r8.synthesis.N n) {
        n.h = F4.b(4105, false);
        n.f = C1159bb.i;
        com.android.tools.r8.androidapi.f fVar = c0333y.U;
        n.l = fVar;
        n.m = fVar;
        n.g = new com.android.tools.r8.synthesis.M() { // from class: j16
            @Override // com.android.tools.r8.synthesis.M
            public final AbstractC0223i0 a(C0322w2 c0322w2) {
                return H0.e(b1, c0322w2);
            }
        };
        n.e = e2;
    }

    public static G0 b(final C0333y c0333y, I0 i0, C0483Fe c0483Fe) {
        final B1 b1A = c0333y.a();
        final E2 e2A = b1A.a(b1A.s3, new I2[0]);
        B5 b5B = c0333y.a.g().b(new com.android.tools.r8.synthesis.I() { // from class: g16
            @Override // com.android.tools.r8.synthesis.I
            public final S.b a(S s) {
                return s.E;
            }
        }, c0483Fe.a(), c0333y, new Consumer() { // from class: h16
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                H0.c(c0333y, b1A, e2A, (N) obj);
            }
        });
        i0.u(b5B, c0483Fe.c);
        return new G0(b5B);
    }

    public static void f(C0333y c0333y, final B1 b1, E2 e2, com.android.tools.r8.synthesis.N n) {
        n.h = F4.b(4105, false);
        n.f = C1159bb.i;
        com.android.tools.r8.androidapi.f fVar = c0333y.U;
        n.l = fVar;
        n.m = fVar;
        n.g = new com.android.tools.r8.synthesis.M() { // from class: k16
            @Override // com.android.tools.r8.synthesis.M
            public final AbstractC0223i0 a(C0322w2 c0322w2) {
                return H0.f(b1, c0322w2);
            }
        };
        n.e = e2;
    }

    public static G0 a(final C0333y c0333y, KO ko, C0483Fe c0483Fe) {
        final B1 b1A = c0333y.a();
        final E2 e2A = b1A.a(b1A.E1, b1A.a2);
        B5 b5B = c0333y.a.g().b(new com.android.tools.r8.synthesis.I() { // from class: f16
            @Override // com.android.tools.r8.synthesis.I
            public final S.b a(S s) {
                return s.C;
            }
        }, c0483Fe.a(), c0333y, new Consumer() { // from class: p16
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                H0.a(c0333y, b1A, e2A, (N) obj);
            }
        });
        ko.t(b5B, c0483Fe.c);
        return new G0(b5B);
    }

    public static void a(C0333y c0333y, final B1 b1, E2 e2, com.android.tools.r8.synthesis.N n) {
        n.h = F4.b(4105, false);
        n.f = C1159bb.i;
        com.android.tools.r8.androidapi.f fVar = c0333y.U;
        n.l = fVar;
        n.m = fVar;
        n.g = new com.android.tools.r8.synthesis.M() { // from class: r16
            @Override // com.android.tools.r8.synthesis.M
            public final AbstractC0223i0 a(C0322w2 c0322w2) {
                return H0.a(b1, c0322w2);
            }
        };
        n.e = e2;
    }

    public static G0 a(final C0333y c0333y, I0 i0, C0483Fe c0483Fe) {
        final B1 b1A = c0333y.a();
        final E2 e2A = b1A.a(b1A.p3, new I2[0]);
        B5 b5B = c0333y.a.g().b(new com.android.tools.r8.synthesis.I() { // from class: a26
            @Override // com.android.tools.r8.synthesis.I
            public final S.b a(S s) {
                return s.D;
            }
        }, c0483Fe.a(), c0333y, new Consumer() { // from class: b26
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                H0.b(c0333y, b1A, e2A, (N) obj);
            }
        });
        i0.d(b5B, c0483Fe.c);
        return new G0(b5B);
    }
}
