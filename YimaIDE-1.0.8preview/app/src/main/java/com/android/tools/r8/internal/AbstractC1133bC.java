package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0229j;
import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.C0310u4;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.shaking.C3403i;
import com.android.tools.r8.shaking.C3418l;
import defpackage.khg;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.bC, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1133bC extends AbstractC1047aC {
    public static final /* synthetic */ boolean l = true;

    public AbstractC1133bC(C0322w2 c0322w2, C2543rl0 c2543rl0, List list) {
        super(c0322w2, c2543rl0, list);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean J2() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final C2543rl0 V0() {
        return V2();
    }

    public C2543rl0 V2() {
        if (l || this.c.size() > 0) {
            return (C2543rl0) this.c.get(0);
        }
        x1f.a();
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean X1() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(C0333y c0333y, com.android.tools.r8.graph.B5 b5, Ol0 ol0) {
        C0333y<C3403i> c0333yV;
        C2441qd c2441qdA;
        boolean z = l;
        C2543rl0 c2543rl0V2 = V2();
        AbstractC2624sj0 abstractC2624sj0T = c2543rl0V2.t();
        if (!z && !abstractC2624sj0T.G()) {
            x1f.a();
            return;
        }
        if (!c0333y.g().i() || (c2441qdA = c2543rl0V2.a((c0333yV = c0333y.V()), (AbstractC2624sj0) null, C2427qS.h())) == null) {
            return;
        }
        com.android.tools.r8.graph.I2 i2A = C2283oj0.a(V2().a(c0333yV), U2(), c0333yV);
        if (z || ((C3403i) c0333yV.g()).c(c2441qdA.Q(), i2A) || c0333y.M().u1.P) {
            return;
        }
        AbstractC2624sj0 abstractC2624sj0B = c2543rl0V2.b(c0333yV);
        abstractC2624sj0B.getClass();
        if ((abstractC2624sj0B instanceof C1034a40) || c2441qdA.a(c0333yV) || a(c0333yV, i2A, c2441qdA.Q())) {
            return;
        }
        x01.a("The receiver lower bound does not match the receiver type");
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean b(C0333y c0333y, com.android.tools.r8.graph.B5 b5, J1 j1, C0864Tw c0864Tw) {
        boolean zU1;
        if (c0333y.M().Z0) {
            return true;
        }
        C2543rl0 c2543rl0V2 = V2();
        if (!c0864Tw.c() && c2543rl0V2.t().F()) {
            return true;
        }
        if (!U2().f.I0() || !U2().d(c0333y.a().p4.b)) {
            C1055aK c1055aK = c0333y.A;
            c1055aK.getClass();
            if (c1055aK.a(U2(), this.c)) {
                return false;
            }
            if (!c0333y.o()) {
                return true;
            }
            boolean z = l;
            if (!z && !c0333y.g().h()) {
                x1f.a();
                return false;
            }
            C0333y c0333yU = c0333y.U();
            com.android.tools.r8.graph.T4.c<?> cVarO = b(c0333yU).o();
            if (cVarO == null || cVarO.a(b5, (C0333y<? extends C0229j>) c0333yU).b()) {
                return true;
            }
            if (c0864Tw.b()) {
                return false;
            }
            com.android.tools.r8.graph.H0 h0A = com.android.tools.r8.graph.H0.a(cVarO.c, cVarO.d);
            if (!c0333y.e.a(U2()).c) {
                C3418l c3418l = c0333y.e;
                c3418l.getClass();
                if (!c3418l.a(h0A.getReference()).c) {
                    com.android.tools.r8.graph.H0 h0G = g(c0333y, b5);
                    com.android.tools.r8.ir.optimize.info.h hVarA = cVarO.a(c0333y, this, h0G);
                    c0333y.M();
                    if (!hVarA.a(this)) {
                        return false;
                    }
                    if (h0G == null) {
                        return true;
                    }
                    if ((h0G instanceof C0310u4) && c0333y.A.a(this, h0G.T())) {
                        return false;
                    }
                    C3418l c3418l2 = c0333y.e;
                    c3418l2.getClass();
                    if (c3418l2.a(h0G.getReference()).c) {
                        return false;
                    }
                    if ((c0864Tw instanceof C0760Pw) && h0G.e().q1()) {
                        if (!z && !U1()) {
                            x1f.a();
                            return false;
                        }
                        if (!hVarA.a(Z()).e()) {
                            zU1 = U1();
                        }
                    }
                    return true;
                }
            }
            return false;
        }
        zU1 = c2();
        return !zU1;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final AbstractC1133bC c0() {
        return this;
    }

    public final boolean e(C2884vl c2884vl) {
        C0231j1 c0231j1B;
        C0322w2 c0322w2U2 = U2();
        if (c0322w2U2.w0() != c2884vl.d.a().s() || (c0231j1B = c2884vl.d.a().a().b(c0322w2U2)) == null || c0231j1B.z0()) {
            return false;
        }
        boolean z = l;
        if (!z && c0322w2U2.f != c0231j1B.E0()) {
            x1f.a();
            return false;
        }
        if (z || c0231j1B.getReference() == c0322w2U2) {
            return true;
        }
        x1f.a();
        return false;
    }

    public final boolean f(C2884vl c2884vl) {
        com.android.tools.r8.graph.H0 h0B;
        if (!c2884vl.e.l()) {
            return false;
        }
        com.android.tools.r8.graph.D2 d2A = c2884vl.d.a().a();
        if (d2A.t1() && (h0B = c2884vl.a.g().b(U2())) != null && !h0B.a().b0() && h0B.getAccessFlags().i()) {
            return d2A.a(h0B.a());
        }
        return false;
    }

    @Override // com.android.tools.r8.internal.AbstractC1047aC, com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean a(C0333y c0333y, com.android.tools.r8.graph.B5 b5, C2543rl0 c2543rl0) {
        return c2543rl0 == V2() || super.a(c0333y, b5, c2543rl0);
    }

    @Override // com.android.tools.r8.internal.AbstractC1047aC
    public final com.android.tools.r8.ir.optimize.O a(com.android.tools.r8.graph.B5 b5, com.android.tools.r8.ir.optimize.G g, C1328dc c1328dc, AbstractC2973wm0 abstractC2973wm0) {
        g.getClass();
        C2543rl0 c2543rl0V2 = V2();
        if (c2543rl0V2.t().N().e()) {
            abstractC2973wm0.r();
            return null;
        }
        if (c2543rl0V2.t().F()) {
            if (!com.android.tools.r8.ir.optimize.G.i && c2543rl0V2.t().N().e()) {
                x1f.a();
                return null;
            }
            if (!g.c.j) {
                abstractC2973wm0.s();
                return null;
            }
        }
        com.android.tools.r8.ir.optimize.O o = new com.android.tools.r8.ir.optimize.O();
        o.b = this;
        o.e = b5;
        return o;
    }

    public final boolean a(C0333y c0333y, com.android.tools.r8.graph.I2 i2, com.android.tools.r8.graph.I2 i3) {
        com.android.tools.r8.graph.D2 d2B;
        com.android.tools.r8.graph.E0 e0D;
        return V2().h().F() && V2().c(new khg()) && (d2B = c0333y.b(i2)) != null && !((C3403i) c0333y.g()).b(d2B) && (e0D = c0333y.d(i3)) != null && e0D.c(c0333y);
    }
}
