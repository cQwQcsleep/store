package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.internal.AbstractC1808j9;
import com.android.tools.r8.internal.BX;
import com.android.tools.r8.internal.InterfaceC1101ar;
import defpackage.vm4;
import java.util.function.BiFunction;
import java.util.function.UnaryOperator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.j9, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1808j9 extends AbstractC3159z1 {
    public static final /* synthetic */ boolean b = true;

    public static AbstractC1808j9 b(BiFunction biFunction, AbstractC1808j9 abstractC1808j9, BX bx) {
        if (bx.b()) {
            return (AbstractC1808j9) biFunction.apply(abstractC1808j9, bx);
        }
        int i = InterfaceC1101ar.a;
        return a(C2291oo.a(1, bx), C2291oo.a(2, C1490fV.c), "on stack");
    }

    public abstract AbstractC1808j9 a(int i, InterfaceC1101ar interfaceC1101ar, InterfaceC2576s8 interfaceC2576s8);

    public abstract AbstractC1808j9 a(C0322w2 c0322w2, InterfaceC2576s8 interfaceC2576s8);

    public abstract AbstractC1808j9 a(C0333y c0333y, com.android.tools.r8.graph.I2 i2, InterfaceC2576s8 interfaceC2576s8, BiFunction biFunction);

    public final AbstractC1808j9 a(C0333y c0333y, AbstractC1808j9 abstractC1808j9, UnaryOperator unaryOperator) {
        abstractC1808j9.getClass();
        if ((abstractC1808j9 instanceof C1039a7) || (this instanceof C2291oo)) {
            return this;
        }
        if (!(this instanceof C1039a7)) {
            if (abstractC1808j9 instanceof C2291oo) {
                return abstractC1808j9;
            }
            boolean z = b;
            if (!z && !(this instanceof C1419ef)) {
                x1f.a();
                return null;
            }
            if (!z && !(abstractC1808j9 instanceof C1419ef)) {
                x1f.a();
                return null;
            }
            C1419ef c1419efD = d();
            C1419ef c1419efD2 = abstractC1808j9.d();
            c1419efD.getClass();
            C1724i9.a aVarT = C1724i9.T();
            C0813Rx c0813Rx = c1419efD2.c;
            C0476Ex c0476Ex = new C0476Ex(((C0398Bx) c1419efD.c.b()).c);
            C0476Ex c0476Ex2 = new C0476Ex(((C0398Bx) c0813Rx.b()).c);
            while (c0476Ex.hasNext() && c0476Ex2.hasNext()) {
                C0450Dx c0450DxA = c0476Ex.a();
                int iA = c0450DxA.a();
                InterfaceC1101ar interfaceC1101ar = (InterfaceC1101ar) c0450DxA.getValue();
                C0450Dx c0450DxA2 = c0476Ex2.a();
                int iA2 = c0450DxA2.a();
                InterfaceC1101ar interfaceC1101ar2 = (InterfaceC1101ar) c0450DxA2.getValue();
                if (iA < iA2) {
                    if (!C1419ef.f && iA >= iA2) {
                        x1f.a();
                        return null;
                    }
                    C1419ef.a(iA, aVarT);
                    c0476Ex2.previous();
                } else if (iA2 < iA) {
                    if (!C1419ef.f && iA2 >= iA) {
                        x1f.a();
                        return null;
                    }
                    C1419ef.a(iA2, aVarT);
                    c0476Ex.previous();
                } else if (interfaceC1101ar.b()) {
                    if (interfaceC1101ar2.b()) {
                        aVarT.a(iA, interfaceC1101ar.q().a(c0333y, interfaceC1101ar2.q()));
                    } else {
                        C1419ef.a(iA, aVarT);
                    }
                } else if (interfaceC1101ar2.H()) {
                    Am0 am0K = interfaceC1101ar.k();
                    Am0 am0K2 = interfaceC1101ar2.k();
                    if (am0K.r() != am0K2.r()) {
                        C1419ef.a(iA, aVarT);
                    } else {
                        if (am0K == am0K2) {
                            aVarT.a(iA, am0K);
                        } else {
                            C1419ef.b(iA, aVarT);
                        }
                        C1419ef.a(iA, am0K, c0476Ex);
                        C1419ef.a(iA, am0K2, c0476Ex2);
                    }
                } else {
                    C1419ef.a(iA, aVarT);
                }
            }
            if (c0476Ex.hasNext()) {
                InterfaceC0943Wx interfaceC0943Wx = (InterfaceC0943Wx) c0476Ex.next();
                if (((InterfaceC1101ar) interfaceC0943Wx.getValue()).i()) {
                    C1419ef.a(interfaceC0943Wx.a(), aVarT);
                } else {
                    C1419ef.a(c0476Ex, interfaceC0943Wx, aVarT, unaryOperator);
                }
                while (c0476Ex.hasNext()) {
                    C1419ef.a(c0476Ex, (InterfaceC0943Wx) c0476Ex.next(), aVarT, unaryOperator);
                }
            }
            if (c0476Ex2.hasNext()) {
                InterfaceC0943Wx interfaceC0943Wx2 = (InterfaceC0943Wx) c0476Ex2.next();
                if (((InterfaceC1101ar) interfaceC0943Wx2.getValue()).i()) {
                    C1419ef.a(interfaceC0943Wx2.a(), aVarT);
                } else {
                    C1419ef.a(c0476Ex2, interfaceC0943Wx2, aVarT, unaryOperator);
                }
                while (c0476Ex2.hasNext()) {
                    C1419ef.a(c0476Ex2, (InterfaceC0943Wx) c0476Ex2.next(), aVarT, unaryOperator);
                }
            }
            C2291oo c2291ooA = c1419efD.a(c0333y, c1419efD2.d, aVarT);
            if (c2291ooA != null) {
                return c2291ooA;
            }
            InterfaceC0425Cy interfaceC0425Cy = aVarT.a;
            C3150yu c3150yu = C1724i9.e;
            if (interfaceC0425Cy == AbstractC0477Ey.a) {
                aVarT.a = new C0813Rx();
            }
            aVarT.b();
            C1724i9 c1724i9A = aVarT.a();
            if (!C1724i9.f && !(c1724i9A.c instanceof C0813Rx)) {
                x1f.a();
                return null;
            }
            abstractC1808j9 = new C1419ef((C0813Rx) c1724i9A.c, c1724i9A.W(), c1419efD.e);
        }
        return abstractC1808j9;
    }

    public abstract AbstractC1808j9 a(C0333y c0333y, InterfaceC2576s8 interfaceC2576s8, int i, El0 el0, BiFunction biFunction);

    public abstract AbstractC1808j9 a(C0333y c0333y, InterfaceC2576s8 interfaceC2576s8, com.android.tools.r8.graph.I2... i2Arr);

    public abstract AbstractC1808j9 a(C1240ca c1240ca, com.android.tools.r8.graph.I2 i2);

    public abstract AbstractC1808j9 a(AbstractC1430ek0 abstractC1430ek0, com.android.tools.r8.graph.I2 i2);

    public abstract AbstractC1808j9 a(InterfaceC2576s8 interfaceC2576s8, com.android.tools.r8.graph.I2 i2);

    public abstract AbstractC1808j9 a(InterfaceC2576s8 interfaceC2576s8, BX bx);

    public abstract AbstractC1808j9 a(InterfaceC2576s8 interfaceC2576s8, C1724i9 c1724i9);

    public abstract AbstractC1808j9 a(InterfaceC2576s8 interfaceC2576s8, AbstractC2624sj0 abstractC2624sj0);

    public abstract AbstractC1808j9 a(BiFunction biFunction);

    public abstract AbstractC1808j9 b(InterfaceC2576s8 interfaceC2576s8, C1724i9 c1724i9);

    public abstract AbstractC1808j9 c(InterfaceC2576s8 interfaceC2576s8, C1724i9 c1724i9);

    public final AbstractC1808j9 c(final BiFunction biFunction) {
        return a(new BiFunction() { // from class: och
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return AbstractC1808j9.b(biFunction, (AbstractC1808j9) obj, (BX) obj2);
            }
        });
    }

    public C1419ef d() {
        return null;
    }

    public C2291oo e() {
        return null;
    }

    public abstract AbstractC1808j9 f();

    public abstract AbstractC1808j9 g();

    public final AbstractC1808j9 h() {
        return c(new BiFunction() { // from class: mch
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return AbstractC1808j9.a((AbstractC1808j9) obj, (BX) obj2);
            }
        });
    }

    @Override // com.android.tools.r8.internal.Vh0
    public final boolean b() {
        return this instanceof C2291oo;
    }

    public final AbstractC1808j9 b(final BiFunction biFunction) {
        return a(new BiFunction() { // from class: pch
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return AbstractC1808j9.a(biFunction, (AbstractC1808j9) obj, (BX) obj2);
            }
        });
    }

    public final AbstractC1808j9 b(C0333y c0333y, final com.android.tools.r8.graph.I2 i2, final InterfaceC2576s8 interfaceC2576s8, final BiFunction biFunction) {
        final F8 f8C = interfaceC2576s8.c();
        final com.android.tools.r8.graph.B1 b1A = c0333y.a();
        return a(new BiFunction() { // from class: ich
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return AbstractC1808j9.a(f8C, b1A, interfaceC2576s8, i2, biFunction, (AbstractC1808j9) obj, (BX) obj2);
            }
        });
    }

    public static /* synthetic */ AbstractC1808j9 b(InterfaceC1938ki0 interfaceC1938ki0, BX bx, AbstractC1808j9 abstractC1808j9, BX bx2) {
        return (AbstractC1808j9) interfaceC1938ki0.a(abstractC1808j9, bx2, bx);
    }

    public static /* synthetic */ AbstractC1808j9 a(AbstractC1808j9 abstractC1808j9, BX bx) {
        return abstractC1808j9;
    }

    @Override // com.android.tools.r8.internal.Vh0
    public final AbstractC3159z1 a() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC3159z1
    public final boolean a(C0333y c0333y, AbstractC3159z1 abstractC3159z1) {
        AbstractC1808j9 abstractC1808j9 = (AbstractC1808j9) abstractC3159z1;
        if (this == abstractC1808j9) {
            return true;
        }
        if (!b && !c0333y.g().h()) {
            x1f.a();
            return false;
        }
        return equals(a(c0333y.U(), abstractC1808j9, UnaryOperator.identity()));
    }

    public static C2291oo a(String str) {
        return new C2291oo(str);
    }

    public static C2291oo a(InterfaceC1101ar interfaceC1101ar, El0 el0, int i) {
        return a(C2291oo.b(interfaceC1101ar), C2291oo.b(el0), "at local index " + i);
    }

    public static C2291oo a(String str, String str2, String str3) {
        return new C2291oo("Expected " + str2 + " " + str3 + ", but was " + str);
    }

    public final AbstractC1808j9 a(C0333y c0333y, InterfaceC2576s8 interfaceC2576s8, com.android.tools.r8.graph.I2 i2) {
        return a(c0333y, i2, interfaceC2576s8, new vm4());
    }

    public final AbstractC1808j9 a(C0333y c0333y, InterfaceC2576s8 interfaceC2576s8, El0 el0) {
        return a(c0333y, el0.a(c0333y.a()), interfaceC2576s8, new vm4());
    }

    public static AbstractC1808j9 a(BiFunction biFunction, AbstractC1808j9 abstractC1808j9, BX bx) {
        if (bx.p()) {
            return (AbstractC1808j9) biFunction.apply(abstractC1808j9, bx);
        }
        return a(C2291oo.a(1, bx), C2291oo.a(El0.b), "on stack");
    }

    public static AbstractC1808j9 a(F8 f8, com.android.tools.r8.graph.B1 b1, InterfaceC2576s8 interfaceC2576s8, com.android.tools.r8.graph.I2 i2, BiFunction biFunction, AbstractC1808j9 abstractC1808j9, BX bx) {
        if (bx.p() && f8.b(bx.a(b1, interfaceC2576s8.e().w0()), i2)) {
            return (AbstractC1808j9) biFunction.apply(abstractC1808j9, bx);
        }
        return a(C2291oo.a(1, bx), C2291oo.a(i2), "on stack");
    }

    public final AbstractC1808j9 a(final InterfaceC1938ki0 interfaceC1938ki0) {
        return c(new BiFunction() { // from class: qch
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return ((AbstractC1808j9) obj).c(new BiFunction() { // from class: lch
                    @Override // java.util.function.BiFunction
                    public final Object apply(Object obj3, Object obj4) {
                        return AbstractC1808j9.b(interfaceC1938ki0, bx, (AbstractC1808j9) obj3, (BX) obj4);
                    }
                });
            }
        });
    }

    public final AbstractC1808j9 a(final BiFunction biFunction, final BiFunction biFunction2) {
        return a(new BiFunction() { // from class: kch
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return AbstractC1808j9.a(biFunction, biFunction2, (AbstractC1808j9) obj, (BX) obj2);
            }
        });
    }

    public static /* synthetic */ AbstractC1808j9 a(BiFunction biFunction, BiFunction biFunction2, AbstractC1808j9 abstractC1808j9, BX bx) {
        return (AbstractC1808j9) (bx.b() ? biFunction.apply(abstractC1808j9, bx) : biFunction2.apply(abstractC1808j9, bx));
    }

    public final AbstractC1808j9 a(final InterfaceC1938ki0 interfaceC1938ki0, BiFunction biFunction) {
        return a(new BiFunction() { // from class: nch
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return ((AbstractC1808j9) obj).c(new BiFunction() { // from class: jch
                    @Override // java.util.function.BiFunction
                    public final Object apply(Object obj3, Object obj4) {
                        return AbstractC1808j9.a(interfaceC1938ki0, bx, (AbstractC1808j9) obj3, (BX) obj4);
                    }
                });
            }
        }, biFunction);
    }

    public static /* synthetic */ AbstractC1808j9 a(InterfaceC1938ki0 interfaceC1938ki0, BX bx, AbstractC1808j9 abstractC1808j9, BX bx2) {
        return (AbstractC1808j9) interfaceC1938ki0.a(abstractC1808j9, bx2, bx);
    }

    @Override // com.android.tools.r8.internal.AbstractC3159z1
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final AbstractC1808j9 b(C0333y c0333y, AbstractC1808j9 abstractC1808j9) {
        if (!b && !c0333y.g().h()) {
            x1f.a();
            return null;
        }
        return a(c0333y.U(), abstractC1808j9, new UnaryOperator() { // from class: hch
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return AbstractC1808j9.a((InterfaceC1101ar) obj);
            }
        });
    }

    public static /* synthetic */ InterfaceC1101ar a(InterfaceC1101ar interfaceC1101ar) {
        if (interfaceC1101ar.b()) {
            int i = InterfaceC1101ar.a;
            return C1490fV.c;
        }
        int i2 = InterfaceC1101ar.a;
        return C2793ui0.c;
    }
}
