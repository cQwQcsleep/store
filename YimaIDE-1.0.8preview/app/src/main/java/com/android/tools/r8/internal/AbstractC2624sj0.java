package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.I2;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.sj0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2624sj0 {
    public static final /* synthetic */ boolean a = true;

    public static X6 e() {
        return X6.d;
    }

    public static C1720i7 f() {
        return C1720i7.b;
    }

    public static V7 g() {
        return V7.d;
    }

    public static C2864vb h() {
        return C2864vb.d;
    }

    public static C2459qm i() {
        return C2459qm.d;
    }

    public static C0365Aq j() {
        return C0365Aq.d;
    }

    public static GA k() {
        return GA.d;
    }

    public static C2164nM l() {
        return C2164nM.d;
    }

    public static AbstractC1120b40 m() {
        return AbstractC1120b40.c;
    }

    public static Ab0 n() {
        return Ab0.d;
    }

    public static C2696tc0 o() {
        return C2696tc0.c;
    }

    public static Lh0 p() {
        return Lh0.b;
    }

    public static Bm0 q() {
        return Bm0.c;
    }

    public final boolean A() {
        return (this instanceof X6) || (this instanceof V7) || (this instanceof Ab0) || (this instanceof C2864vb);
    }

    public boolean B() {
        return this instanceof C0365Aq;
    }

    public boolean C() {
        return this instanceof GA;
    }

    public boolean D() {
        return this instanceof C2164nM;
    }

    public boolean E() {
        return this instanceof C1034a40;
    }

    public boolean F() {
        return N().g();
    }

    public final boolean G() {
        return r() || w() || (this instanceof C1034a40) || (this instanceof GA) || (this instanceof C0365Aq) || (this instanceof C2164nM) || (this instanceof C2459qm) || (this instanceof C1720i7);
    }

    public boolean H() {
        return false;
    }

    public boolean I() {
        return false;
    }

    public boolean J() {
        return this instanceof Ab0;
    }

    public boolean K() {
        return false;
    }

    public boolean L() {
        return this instanceof Lh0;
    }

    public boolean M() {
        return false;
    }

    public abstract C2427qS N();

    public int O() {
        if (a) {
            return 1;
        }
        if (!(this instanceof C1720i7) && !(this instanceof Lh0)) {
            return 1;
        }
        x1f.a();
        return 0;
    }

    public final AbstractC2624sj0 a(C0333y c0333y, AbstractC2624sj0 abstractC2624sj0) {
        if (this == abstractC2624sj0) {
            return this;
        }
        abstractC2624sj0.getClass();
        if (abstractC2624sj0 instanceof C1720i7) {
            return this;
        }
        if (this instanceof C1720i7) {
            return abstractC2624sj0;
        }
        if ((this instanceof Lh0) || (abstractC2624sj0 instanceof Lh0) || H() != abstractC2624sj0.H()) {
            return p();
        }
        if (!H()) {
            boolean z = a;
            if (!z && !I()) {
                x1f.a();
                return null;
            }
            if (!z && !G()) {
                x1f.a();
                return null;
            }
            if (!z && !abstractC2624sj0.I()) {
                x1f.a();
                return null;
            }
            if (z || abstractC2624sj0.G()) {
                return d().a(abstractC2624sj0.d(), c0333y);
            }
            x1f.a();
            return null;
        }
        AbstractC2005lY abstractC2005lYC = c();
        AbstractC2005lY abstractC2005lYC2 = abstractC2624sj0.c();
        if (abstractC2005lYC == abstractC2005lYC2) {
            abstractC2005lYC.getClass();
            return abstractC2005lYC;
        }
        if (abstractC2005lYC.K()) {
            if (abstractC2005lYC2.K()) {
                return o();
            }
            if (AbstractC2005lY.b || abstractC2005lYC2.M()) {
                return p();
            }
            x1f.a();
            return null;
        }
        boolean z2 = AbstractC2005lY.b;
        if (!z2 && !abstractC2005lYC.M()) {
            x1f.a();
            return null;
        }
        if (abstractC2005lYC2.M()) {
            return q();
        }
        if (z2 || abstractC2005lYC2.K()) {
            return p();
        }
        x1f.a();
        return null;
    }

    public boolean b(AbstractC2624sj0 abstractC2624sj0, C0333y<?> c0333y) {
        if (this == abstractC2624sj0) {
            return true;
        }
        if (this instanceof Lh0) {
            abstractC2624sj0.getClass();
            return abstractC2624sj0 instanceof Lh0;
        }
        abstractC2624sj0.getClass();
        if ((abstractC2624sj0 instanceof Lh0) || (this instanceof C1720i7)) {
            return true;
        }
        if (abstractC2624sj0 instanceof C1720i7) {
            return false;
        }
        if (H()) {
            return a(abstractC2624sj0, c0333y);
        }
        if (a || (I() && abstractC2624sj0.I())) {
            return a(abstractC2624sj0.F() ? abstractC2624sj0.d() : abstractC2624sj0.d().a(C2427qS.h()), c0333y);
        }
        x1f.a();
        return false;
    }

    public boolean c(AbstractC2624sj0 abstractC2624sj0, C0333y<?> c0333y) {
        if (equals(abstractC2624sj0)) {
            return false;
        }
        AbstractC2624sj0 abstractC2624sj0A = a(c0333y, abstractC2624sj0);
        return !equals(abstractC2624sj0A) && abstractC2624sj0.equals(abstractC2624sj0A);
    }

    public AbstractC1120b40 d() {
        return null;
    }

    public abstract boolean equals(Object obj);

    public abstract int hashCode();

    public boolean r() {
        return false;
    }

    public boolean s() {
        return this instanceof X6;
    }

    public boolean t() {
        return this instanceof C1720i7;
    }

    public abstract String toString();

    public boolean u() {
        return this instanceof V7;
    }

    public boolean v() {
        return this instanceof C2864vb;
    }

    public boolean w() {
        return false;
    }

    public boolean x() {
        return N().d();
    }

    public final boolean y() {
        return N().e();
    }

    public boolean z() {
        return this instanceof C2459qm;
    }

    public AbstractC2005lY c() {
        return null;
    }

    public C2441qd b() {
        return null;
    }

    public AbstractC2624sj0 a(C0333y c0333y, Function function, Set set) {
        return this;
    }

    public boolean a(C0333y c0333y) {
        return false;
    }

    public final boolean a(AbstractC2624sj0 abstractC2624sj0) {
        if (this == abstractC2624sj0) {
            return true;
        }
        boolean z = this instanceof C1720i7;
        abstractC2624sj0.getClass();
        if (z != (abstractC2624sj0 instanceof C1720i7) || H() || abstractC2624sj0.H()) {
            return false;
        }
        if (a || (I() && abstractC2624sj0.I())) {
            return d().a(C2427qS.h()).equals(abstractC2624sj0.d().a(C2427qS.h()));
        }
        x1f.a();
        return false;
    }

    public final AbstractC2624sj0 a(C0333y c0333y, AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2) {
        return a(c0333y, abstractC3148ys, abstractC3148ys2, Collections.EMPTY_SET);
    }

    public final AbstractC2624sj0 a(C0333y c0333y, final AbstractC3148ys abstractC3148ys, final AbstractC3148ys abstractC3148ys2, Set set) {
        return a(c0333y, new Function() { // from class: zai
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return abstractC3148ys.c(abstractC3148ys2, (I2) obj);
            }
        }, set);
    }

    public Q3 a() {
        return null;
    }

    public static AbstractC2624sj0 a(Iterable<AbstractC2624sj0> iterable, C0333y<?> c0333y) {
        AbstractC2624sj0 abstractC2624sj0F = f();
        Iterator<AbstractC2624sj0> it = iterable.iterator();
        while (it.hasNext()) {
            abstractC2624sj0F = abstractC2624sj0F.a(c0333y, it.next());
        }
        return abstractC2624sj0F;
    }

    public boolean a(AbstractC2624sj0 abstractC2624sj0, C0333y<?> c0333y) {
        if (equals(abstractC2624sj0)) {
            return true;
        }
        AbstractC2624sj0 abstractC2624sj0A = a(c0333y, abstractC2624sj0);
        return !equals(abstractC2624sj0A) && abstractC2624sj0.equals(abstractC2624sj0A);
    }

    public final boolean a(com.android.tools.r8.graph.I2 i2) {
        if (a || i2.M0()) {
            return w() && b().Q() == i2;
        }
        x1f.a();
        return false;
    }

    public final boolean a(com.android.tools.r8.graph.B1 b1) {
        return a(b1.Y1);
    }

    public static C2441qd a(C0333y<?> c0333y, C2427qS c2427qS) {
        return a(c0333y.a().Y1, c2427qS, c0333y).b();
    }

    public static AbstractC2624sj0 a(com.android.tools.r8.graph.I2 i2, C2427qS c2427qS, C0333y<?> c0333y) {
        if (i2 == com.android.tools.r8.graph.B1.e6) {
            if (a || !c2427qS.d()) {
                return m();
            }
            x1f.a();
            return null;
        }
        if (i2.T0()) {
            if (AbstractC2005lY.b || i2.T0()) {
                return AbstractC2005lY.a((char) i2.f.f[0], false);
            }
            x1f.a();
            return null;
        }
        return c0333y.a().a(i2, c2427qS, c0333y);
    }
}
