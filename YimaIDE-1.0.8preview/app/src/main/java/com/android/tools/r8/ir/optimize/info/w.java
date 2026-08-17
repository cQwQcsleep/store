package com.android.tools.r8.ir.optimize.info;

import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.M5;
import com.android.tools.r8.internal.AbstractC0423Cw;
import com.android.tools.r8.internal.AbstractC0439Dm;
import com.android.tools.r8.internal.AbstractC0449Dw;
import com.android.tools.r8.internal.AbstractC0570In;
import com.android.tools.r8.internal.AbstractC1047aC;
import com.android.tools.r8.internal.AbstractC2173nV;
import com.android.tools.r8.internal.AbstractC2624sj0;
import com.android.tools.r8.internal.AbstractC3148ys;
import com.android.tools.r8.internal.AbstractC3169z6;
import com.android.tools.r8.internal.Ak0;
import com.android.tools.r8.internal.B1;
import com.android.tools.r8.internal.B7;
import com.android.tools.r8.internal.C0713Ob;
import com.android.tools.r8.internal.C0906Vm;
import com.android.tools.r8.internal.C1199c2;
import com.android.tools.r8.internal.C2113mk0;
import com.android.tools.r8.internal.C2199nk0;
import com.android.tools.r8.internal.C2440qc0;
import com.android.tools.r8.internal.C2441qd;
import com.android.tools.r8.internal.EQ;
import com.android.tools.r8.internal.Gb0;
import com.android.tools.r8.internal.InterfaceC2182nc;
import com.android.tools.r8.internal.Lb0;
import com.android.tools.r8.internal.VB;
import com.android.tools.r8.internal.Y6;
import com.sun.jna.platform.linux.Fcntl;
import defpackage.md6;
import java.util.BitSet;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class w extends h {
    public static final int w;
    public static final /* synthetic */ boolean x = true;
    public AbstractC3260a b;
    public Set c;
    public int d;
    public com.android.tools.r8.internal.r e;
    public B1 f;
    public InterfaceC2182nc g;
    public boolean h;
    public AbstractC0570In i;
    public AbstractC0439Dm j;
    public AbstractC2173nV k;
    public B7 l;
    public AbstractC0449Dw m;
    public BitSet n;
    public BitSet o;
    public Gb0 p;
    public Gb0 q;
    public int r;
    public BitSet s;
    public BitSet t;
    public int u;
    public int v;

    static {
        C3263d c3263d = C3263d.b;
        w = (Y6.a(false) * Fcntl.S_IRUSR) | Y6.a(false) | (Y6.a(false) * 2) | (Y6.a(false) * 4) | (Y6.a(true) * 8) | (Y6.a(false) * 16) | (Y6.a(false) * 32) | (Y6.a(false) * 128);
    }

    public w(w wVar) {
        this.b = E.a;
        this.c = C3263d.c;
        this.d = -1;
        this.e = C2113mk0.a;
        this.f = C3263d.d;
        this.g = C1199c2.a;
        this.h = false;
        this.i = C2199nk0.a;
        this.j = AbstractC0439Dm.m();
        this.v = 3;
        this.k = AbstractC2173nV.c;
        this.l = null;
        this.m = C0906Vm.a;
        this.n = null;
        this.o = null;
        EQ eq = EQ.b;
        this.p = eq;
        this.q = eq;
        this.r = 0;
        this.s = null;
        this.t = null;
        this.u = w;
        this.e = wVar.e;
        this.b = wVar.b;
        this.u = wVar.u;
        this.c = wVar.c;
        this.d = wVar.d;
        this.f = wVar.f;
        a(wVar.j);
        this.v = wVar.v;
        this.p = wVar.p;
        this.q = wVar.q;
        this.l = wVar.l;
        this.m = wVar.m;
        this.n = wVar.n;
        this.o = wVar.o;
        this.g = wVar.g;
        this.i = wVar.i;
        this.r = wVar.r;
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final boolean A() {
        return this.v == 1;
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final AbstractC2173nV B() {
        return this.k;
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final boolean C() {
        return a(8);
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final boolean D() {
        return a(32);
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final boolean E() {
        return a(Fcntl.S_IRUSR);
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final boolean F() {
        return a(16);
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final boolean G() {
        return this.d != -1;
    }

    public void H() {
        int i;
        if (x || (i = this.v) == 3 || i == 2) {
            this.v = 2;
        } else {
            x1f.a();
        }
    }

    public final void I() {
        this.f = Ak0.a;
    }

    public final void J() {
        this.l = null;
    }

    public final void K() {
        this.u &= -3;
    }

    public final void L() {
        this.g = C1199c2.a;
    }

    public final w M() {
        return a(AbstractC0439Dm.m());
    }

    public final void N() {
        this.i = C2199nk0.a;
    }

    public final void O() {
        this.v = 3;
    }

    public final void P() {
        this.c = C3263d.c;
    }

    public final void Q() {
        this.u &= -129;
    }

    public final void R() {
        this.u &= -5;
    }

    public final void S() {
        this.m = C0906Vm.a;
    }

    public final void T() {
        this.u |= 8;
    }

    public final void U() {
        this.u &= -33;
    }

    public final void V() {
        this.o = null;
    }

    public final void W() {
        this.n = null;
    }

    public final void X() {
        this.p = EQ.b;
    }

    public final void Y() {
        this.u &= -17;
    }

    public final void Z() {
        this.d = -1;
    }

    public final void a(C0333y c0333y, M5 m5) {
        AbstractC3260a abstractC3260a = this.b;
        abstractC3260a.getClass();
        if (abstractC3260a instanceof C3261b) {
            this.b = m5.a(this.b.a());
        }
        B7 b7 = this.l;
        if (b7 != null) {
            this.l = m5.a(b7);
        }
        this.g = m5.a(c0333y, this.g);
        if (!this.j.l()) {
            this = a(m5.a(this.j));
        }
        if (!this.f.isUnknown()) {
            this.f = m5.a(c0333y, this.f);
        }
        this.i = m5.a(this.i);
        AbstractC0449Dw abstractC0449DwA = this.m;
        if (!m5.a.b.c()) {
            abstractC0449DwA = abstractC0449DwA.a(c0333y, m5.a.b);
        }
        this.m = abstractC0449DwA;
        this.o = m5.a(this.o);
        this.n = m5.a(this.n);
        this.d = m5.a(this.d);
        BitSet bitSetA = m5.a(this.t);
        BitSet bitSet = null;
        if (bitSetA == null || bitSetA.isEmpty()) {
            this.s = null;
        } else {
            this.s = bitSetA;
        }
        Gb0 gb0A = this.p;
        Lb0 lb0 = c0333y.y;
        if (!m5.a.b.c()) {
            gb0A = gb0A.a(c0333y, m5.a.b, lb0);
        }
        this.p = gb0A;
        Gb0 gb0A2 = this.q;
        Lb0 lb1 = c0333y.y;
        if (!m5.a.b.c()) {
            gb0A2 = gb0A2.a(c0333y, m5.a.b, lb1);
        }
        this.q = gb0A2;
        BitSet bitSetA2 = m5.a(this.t);
        if (bitSetA2 != null && !bitSetA2.isEmpty()) {
            bitSet = bitSetA2;
        }
        this.t = bitSet;
    }

    public final void a0() {
        this.q = EQ.b;
    }

    public final void b(int i) {
        int i2;
        boolean z = x;
        if (!z && i < 0) {
            x1f.a();
        } else if (z || (i2 = this.d) == -1 || i2 == i) {
            this.d = i;
        } else {
            x1f.a();
        }
    }

    public final void b0() {
        this.t = null;
    }

    @Override // com.android.tools.r8.ir.optimize.info.g
    public final boolean d() {
        return true;
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final boolean e() {
        return a(1);
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final boolean f() {
        return a(2);
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final boolean g() {
        return this.v == 2;
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final com.android.tools.r8.internal.r h() {
        return this.e;
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final B1 i() {
        return this.f;
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final AbstractC3260a j() {
        return this.b;
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final B7 k() {
        return this.l;
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final InterfaceC2182nc l() {
        return this.g;
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final AbstractC0423Cw m() {
        return this.m.b();
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final AbstractC0439Dm n() {
        return this.j;
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final AbstractC0570In o() {
        return this.i;
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final Set p() {
        return this.c;
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final int q() {
        return this.r;
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final BitSet r() {
        return this.o;
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final BitSet s() {
        return this.n;
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final int t() {
        return this.d;
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final Gb0 u() {
        return this.q;
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final BitSet v() {
        return this.t;
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final boolean w() {
        return a(4);
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final boolean y() {
        return this.h;
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final boolean z() {
        return a(128);
    }

    @Override // com.android.tools.r8.ir.optimize.info.g
    public final g b() {
        return this;
    }

    public w() {
        this.b = E.a;
        this.c = C3263d.c;
        this.d = -1;
        this.e = C2113mk0.a;
        this.f = C3263d.d;
        this.g = C1199c2.a;
        this.h = false;
        this.i = C2199nk0.a;
        this.j = AbstractC0439Dm.m();
        this.v = 3;
        this.k = AbstractC2173nV.c;
        this.l = null;
        this.m = C0906Vm.a;
        this.n = null;
        this.o = null;
        EQ eq = EQ.b;
        this.p = eq;
        this.q = eq;
        this.r = 0;
        this.s = null;
        this.t = null;
        this.u = w;
    }

    public final w a(C0333y c0333y, AbstractC3148ys abstractC3148ys, Set set) {
        AbstractC0439Dm abstractC0439DmA = this.j.a(c0333y, abstractC3148ys, set);
        if (abstractC0439DmA.f() && abstractC0439DmA.a().n().H()) {
            boolean z = x;
            if (!z) {
                AbstractC0439Dm abstractC0439Dm = this.j;
                if (!z && !abstractC0439Dm.h()) {
                    x1f.a();
                    return null;
                }
                AbstractC2624sj0 abstractC2624sj0N = abstractC0439Dm.a().n();
                if (!z && !abstractC2624sj0N.w()) {
                    x1f.a();
                    return null;
                }
                C2441qd c2441qdB = abstractC2624sj0N.b();
                if (!z && !c0333y.D()) {
                    x1f.a();
                    return null;
                }
                if (!z && !c0333y.R().b(c2441qdB.Q())) {
                    x1f.a();
                    return null;
                }
            }
            return a(AbstractC0439Dm.m());
        }
        return a(abstractC0439DmA);
    }

    public final boolean a(int i) {
        return (this.u & i) != 0;
    }

    @Override // com.android.tools.r8.ir.optimize.info.g
    public final w a() {
        return this;
    }

    public final w a(AbstractC0570In abstractC0570In) {
        if (!x) {
            AbstractC0570In abstractC0570In2 = this.i;
            abstractC0570In2.getClass();
            if (abstractC0570In2 instanceof C0713Ob) {
                abstractC0570In.getClass();
                if (!(abstractC0570In instanceof C0713Ob)) {
                    x1f.a();
                    return null;
                }
            }
        }
        this.i = abstractC0570In;
        return this;
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final AbstractC0423Cw a(VB vb) {
        return this.m.a();
    }

    public final void a(BitSet bitSet) {
        boolean z = x;
        if (!z) {
            x();
        }
        if (!z && x()) {
            BitSet bitSet2 = this.t;
            if (!AbstractC3169z6.a) {
                BitSet bitSet3 = (BitSet) bitSet2.clone();
                bitSet3.or(bitSet);
                if (!bitSet.equals(bitSet3)) {
                    x1f.a();
                    return;
                }
            }
        }
        if (bitSet.isEmpty()) {
            bitSet = null;
        }
        this.t = bitSet;
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final boolean a(AbstractC1047aC abstractC1047aC) {
        return a(8) && !this.p.a(abstractC1047aC);
    }

    public final void a(B1 b1, C0231j1 c0231j1) {
        if (!x) {
            b1.getClass();
            if ((b1 instanceof C2440qc0) && !c0231j1.f1().U0()) {
                x1f.a();
                return;
            }
        }
        a(b1);
    }

    public final void a(B1 b1) {
        if (!x && this.f.g() && !this.f.equals(b1) && (!this.f.N() || !b1.M() || !this.f.r().R().a(b1.r().R()))) {
            mu3.a("return single value changed from ", this.f, " to ", b1);
        } else {
            this.f = b1;
        }
    }

    public final void a(C0333y c0333y, C0231j1 c0231j1, AbstractC0439Dm abstractC0439Dm) {
        AbstractC2624sj0 abstractC2624sj0B = c0231j1.f1().b((C0333y<?>) c0333y);
        if (!x) {
            a(c0333y, abstractC0439Dm, abstractC2624sj0B);
        }
        a(abstractC0439Dm);
    }

    public final w a(AbstractC0439Dm abstractC0439Dm) {
        if (!x && abstractC0439Dm.f() && abstractC0439Dm.a().n().H()) {
            x1f.a();
            return null;
        }
        this.j = abstractC0439Dm;
        return this;
    }

    public final void a(C0333y c0333y, AbstractC0439Dm abstractC0439Dm, AbstractC2624sj0 abstractC2624sj0) {
        if (c0333y.o()) {
            AbstractC2624sj0 abstractC2624sj0A = this.j.a(abstractC2624sj0);
            AbstractC2624sj0 abstractC2624sj0A2 = abstractC0439Dm.a(abstractC2624sj0);
            if (x || abstractC2624sj0A2.b(abstractC2624sj0A, c0333y)) {
                return;
            }
            md6.a("upper bound type changed from ", abstractC2624sj0A, " to ", abstractC2624sj0A2);
        }
    }
}
