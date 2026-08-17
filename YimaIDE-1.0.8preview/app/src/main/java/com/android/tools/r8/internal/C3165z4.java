package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;
import defpackage.bb3;
import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.z4, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C3165z4 extends AbstractC0890Uw {
    public static final /* synthetic */ boolean k = true;
    public AbstractC0439Dm i;
    public final AbstractC0890Uw j;

    public C3165z4(AbstractC0439Dm abstractC0439Dm, C2543rl0 c2543rl0, C2543rl0 c2543rl1, AbstractC0890Uw abstractC0890Uw) {
        super(c2543rl0, c2543rl1);
        boolean z = k;
        if (!z && abstractC0439Dm == null) {
            x1f.a();
            throw null;
        }
        if (!z && abstractC0439Dm.l()) {
            x1f.a();
            throw null;
        }
        this.i = abstractC0439Dm;
        this.j = abstractC0890Uw;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int F2() {
        throw new Kk0("Expected Assume instructions to be removed after IR processing.");
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int G2() {
        throw new Kk0("Expected Assume instructions to be removed after IR processing.");
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int H2() {
        return 9;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean J2() {
        return this.i.d().d();
    }

    public final boolean K2() {
        if (this.i.j()) {
            return false;
        }
        boolean z = k;
        if (!z && this.i.l()) {
            x1f.a();
            return false;
        }
        if (z || this.i.h()) {
            return true;
        }
        x1f.a();
        return false;
    }

    public C2543rl0 L2() {
        return (C2543rl0) this.c.get(0);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final String U0() {
        return "Assume";
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final C2543rl0 V0() {
        return L2();
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean a(C0333y c0333y, C2543rl0 c2543rl0) {
        boolean z = k;
        if (!z && (c2543rl0 == null || !c2543rl0.t().I())) {
            x1f.a();
            return false;
        }
        if (!z && this.b == null) {
            x1f.a();
            return false;
        }
        AbstractC2624sj0 abstractC2624sj0T = this.b.t();
        if (abstractC2624sj0T.H()) {
            return false;
        }
        if (K2()) {
            abstractC2624sj0T = this.i.a().n();
        }
        if (c0333y.g().i() && abstractC2624sj0T.w() && c2543rl0.t().w() && c0333y.g().m().a(abstractC2624sj0T.b().Q(), c2543rl0.t().b().Q())) {
            return false;
        }
        return abstractC2624sj0T.I();
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean b(AbstractC0890Uw abstractC0890Uw) {
        abstractC0890Uw.getClass();
        if (!(abstractC0890Uw instanceof C3165z4)) {
            return false;
        }
        return this.i.equals(abstractC0890Uw.x().i);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean b1() {
        return false;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean d(C0333y c0333y, com.android.tools.r8.graph.B5 b5) {
        return false;
    }

    public final void f(C0333y c0333y, com.android.tools.r8.graph.B5 b5) {
        boolean z = k;
        if (!z && L2().J()) {
            bb3.a("Unexpected Assume value ", c(), " for constant value ", L2(), " defined by ", L2().p(), " (context: ", b5.v(), ", type: ", L2().t());
            return;
        }
        if (!z && L2().t().y()) {
            x1f.a();
            return;
        }
        if (!z && L2().t().E()) {
            x1f.a();
            return;
        }
        if (!z && !c1()) {
            x1f.a();
            return;
        }
        if (K2()) {
            if (!z && this.i.g()) {
                x1f.a();
                return;
            }
            if (!z && this.i.j()) {
                x1f.a();
                return;
            }
            if (!z && this.i.k()) {
                x1f.a();
                return;
            }
            if (!z && this.i.l()) {
                x1f.a();
                return;
            }
            C0491Fm c0491FmA = this.i.a();
            if (!z && !c0491FmA.n().b(L2().t(), c0333y)) {
                x1f.a();
                return;
            }
        } else {
            if (!z && !this.i.j()) {
                x1f.a();
                return;
            }
            if (!z && !this.i.d().d()) {
                x1f.a();
                return;
            }
            if (!z && L2().t().x()) {
                throw new AssertionError("Unexpected AssumeNotNull instruction for non-null value " + L2() + " defined by " + (L2().j() ? "phi" : L2().p()) + " (context: " + b5.v() + ", type: " + L2().t() + ")");
            }
        }
        if (z || !this.i.d().d() || c().t().x()) {
            return;
        }
        bb3.a("Unexpected nullability for value ", c(), " defined by ", this, ": ", c().t().N(), ", but expected: ", C2427qS.b(), " (context: ", b5.v());
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        if (this.i.d().d()) {
            sb.append("; not null");
        }
        if (K2()) {
            C0491Fm c0491FmA = this.i.a();
            if (c1() && !c0491FmA.n().a(this.b.t())) {
                sb.append("; upper bound: ");
                sb.append(c0491FmA.n());
            }
            if (c0491FmA.e()) {
                sb.append("; lower bound: ");
                sb.append(c0491FmA.b());
            }
        }
        return sb.toString();
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final C3165z4 x() {
        return this;
    }

    public static C3165z4 a(AbstractC0439Dm abstractC0439Dm, C2543rl0 c2543rl0, C2543rl0 c2543rl1, AbstractC0890Uw abstractC0890Uw, C0333y c0333y, com.android.tools.r8.graph.B5 b5) {
        C3165z4 c3165z4 = new C3165z4(abstractC0439Dm, c2543rl0, c2543rl1, abstractC0890Uw);
        if (!k) {
            c3165z4.f(c0333y, b5);
        }
        return c3165z4;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean a(Set set) {
        return L2().b(set);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final AbstractC2624sj0 a(C0333y c0333y) {
        if (this.i.d().d()) {
            if (k || L2().t().I()) {
                return L2().t().d().P();
            }
            x1f.a();
            return null;
        }
        return L2().t();
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final Object a(C0941Wv c0941Wv) {
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(C2884vl c2884vl) {
        throw new Kk0("Expected Assume instructions to be removed after IR processing.");
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(J8 j8) {
        throw new Kk0("Expected Assume instructions to be removed after IR processing.");
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(LK lk) {
        throw new Kk0("Expected Assume instructions to be removed after IR processing.");
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final com.android.tools.r8.graph.I2 a(C0333y c0333y, Nj0 nj0) {
        return nj0.a(L2());
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(K5 k5, IL il) {
        throw new Kk0("Expected Assume instructions to be removed after IR processing.");
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(C0333y c0333y, com.android.tools.r8.graph.B5 b5, Ol0 ol0) {
        f(c0333y, b5);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final com.android.tools.r8.ir.optimize.N a(com.android.tools.r8.ir.optimize.W w, com.android.tools.r8.graph.B5 b5) {
        return com.android.tools.r8.ir.optimize.N.d;
    }
}
