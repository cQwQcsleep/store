package com.android.tools.r8.graph;

import com.android.tools.r8.internal.AbstractC2166nO;
import com.android.tools.r8.internal.AbstractC3148ys;
import com.android.tools.r8.internal.AbstractC3191zO;
import com.android.tools.r8.internal.C0705Nt;
import com.android.tools.r8.internal.InterfaceC2045lz;
import com.android.tools.r8.ir.optimize.info.C3263d;
import com.android.tools.r8.shaking.C3403i;
import com.sun.jna.platform.linux.Fcntl;
import java.util.BitSet;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class B5 extends H0 implements A5 {
    public static final /* synthetic */ boolean f = true;

    public B5() {
    }

    @Override // com.android.tools.r8.graph.InterfaceC0265o0
    public final boolean U() {
        return true;
    }

    @Override // com.android.tools.r8.graph.A5
    public final com.android.tools.r8.kotlin.P W() {
        return e().p;
    }

    public void a(AbstractC0223i0 abstractC0223i0, C0333y<?> c0333y) {
        AbstractC0223i0 abstractC0223i0U0 = e().U0();
        InterfaceC2045lz interfaceC2045lzA = e().s;
        if (abstractC0223i0U0 != null && abstractC0223i0U0.w0() && e().s == C0231j1.w && (c0333y.Q().l0 || (!c0333y.M().Z0 && !a().f(c0333y)))) {
            interfaceC2045lzA = abstractC0223i0U0.a(c0333y, e());
        }
        e().a(abstractC0223i0, interfaceC2045lzA);
    }

    public final boolean b(C0333y c0333y) {
        c0333y.M().getClass();
        if ((!a().n1() && !a().isInterface()) || getAccessFlags().M() || getAccessFlags().i() || getAccessFlags().n() || e().q1()) {
            return false;
        }
        return !((C3403i) c0333y.g()).n.contains(getReference());
    }

    public final void c(C0333y c0333y) {
        boolean zB = b(c0333y);
        if (zB) {
            F4 f4W = getAccessFlags();
            f4W.a();
            f4W.c = (f4W.c & (-2081)) | Fcntl.S_ISGID;
            C0231j1 c0231j1E = e();
            c0231j1E.getClass();
            int i = com.android.tools.r8.androidapi.f.a;
            c0231j1E.o = com.android.tools.r8.androidapi.g.b;
            C0231j1 c0231j1E2 = e();
            c0231j1E2.O0();
            c0231j1E2.j = null;
            com.android.tools.r8.ir.optimize.info.C cA = com.android.tools.r8.ir.optimize.info.y.a();
            cA.getClass();
            if (D().d()) {
                cA.d(this);
                cA.g(e());
                cA.e(this);
                cA.f(this);
                cA.g(this);
                cA.a(this);
                cA.b(this);
                cA.h(this);
                cA.i(this);
                cA.k(this);
                cA.l(this);
                cA.m(this);
                cA.n(this);
                cA.o(this);
                cA.r(this);
                cA.q(this);
                cA.p(this);
                cA.s(this);
                cA.t(this);
            }
        }
        if (zB) {
            return;
        }
        d(c0333y);
    }

    @Override // com.android.tools.r8.graph.InterfaceC0265o0
    public final B5 c0() {
        return this;
    }

    public final void d(C0333y c0333y) {
        getAccessFlags().c &= -1025;
        e().f(c0333y.U);
        a(W5.e, (C0333y<?>) c0333y);
        com.android.tools.r8.ir.optimize.info.C cA = com.android.tools.r8.ir.optimize.info.y.a();
        C0231j1 c0231j1E = e();
        com.android.tools.r8.ir.optimize.N n = com.android.tools.r8.ir.optimize.N.d;
        cA.getClass();
        c0231j1E.a(n);
        com.android.tools.r8.ir.optimize.info.C cA2 = com.android.tools.r8.ir.optimize.info.y.a();
        cA2.getClass();
        if (!c0333y.g().h() || c0333y.a(this).d(c0333y.M())) {
            if (AbstractC3191zO.a) {
                return;
            }
            com.android.tools.r8.ir.optimize.info.h hVarD = D();
            hVarD.getClass();
            if (hVarD instanceof C3263d) {
                return;
            }
            x1f.a();
            return;
        }
        if (D().d()) {
            cA2.d(this);
            cA2.g(e());
            cA2.e(this);
            cA2.f(this);
            cA2.g(this);
            cA2.a(this);
            cA2.b(this);
            cA2.h(this);
            cA2.i(this);
            cA2.k(this);
            cA2.l(this);
            cA2.m(this);
            cA2.n(this);
            cA2.o(this);
            cA2.r(this);
            cA2.q(this);
            cA2.p(this);
            cA2.s(this);
            cA2.t(this);
        }
        cA2.c(this);
        C0231j1 c0231j1E2 = e();
        int iA = c0231j1E2.getReference().a(c0231j1E2.z0());
        BitSet bitSet = new BitSet(iA);
        for (int i = 0; i < iA; i++) {
            bitSet.set(i, true);
        }
        e().Y0().a(bitSet);
    }

    public final boolean e(C0333y c0333y) {
        return a().f(c0333y);
    }

    @Override // com.android.tools.r8.graph.InterfaceC0339y5
    public final InterfaceC0265o0 getContext() {
        return this;
    }

    @Override // com.android.tools.r8.graph.InterfaceC0265o0
    public final A5 p() {
        return this;
    }

    public B5(D2 d2, C0231j1 c0231j1) {
        super(d2, c0231j1);
    }

    public C0705Nt a(C0333y<?> c0333y) {
        return a(c0333y, AbstractC2166nO.c(c0333y));
    }

    public C0705Nt a(C0333y<?> c0333y, AbstractC2166nO.a aVar) {
        C0231j1 c0231j1E = e();
        if (c0231j1E.i1()) {
            return c0231j1E.U0().a(this, c0333y, aVar);
        }
        return null;
    }

    @Override // com.android.tools.r8.graph.G0, com.android.tools.r8.graph.InterfaceC0331x4, com.android.tools.r8.graph.A5
    public D2 a() {
        E0 e0 = this.b;
        if (f || e0.a0()) {
            return e0.X();
        }
        x1f.a();
        return null;
    }

    public final void a(Z5 z5) {
        AbstractC0223i0 abstractC0223i0U0 = e().U0();
        if (abstractC0223i0U0 != null) {
            abstractC0223i0U0.a(this, z5);
        }
    }

    @Override // com.android.tools.r8.graph.G0, com.android.tools.r8.graph.InterfaceC0265o0, com.android.tools.r8.graph.InterfaceC0332x5
    public final E0 b() {
        return a();
    }

    @Override // com.android.tools.r8.graph.G0, com.android.tools.r8.graph.InterfaceC0265o0, com.android.tools.r8.graph.InterfaceC0332x5
    public final D2 b() {
        return a();
    }

    public final B5 a(InterfaceC0189d1 interfaceC0189d1, AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2) {
        C0322w2 c0322w2B = abstractC3148ys.d(abstractC3148ys2, getReference());
        if (c0322w2B.a(getReference()) && !e().t) {
            boolean z = f;
            if (z) {
                return this;
            }
            H0 h0B = interfaceC0189d1.b(getReference());
            if (z || e() == h0B.e()) {
                return this;
            }
            x1f.a();
            return null;
        }
        return H0.a(interfaceC0189d1.b(c0322w2B));
    }

    @Override // com.android.tools.r8.graph.H0, com.android.tools.r8.graph.InterfaceC0265o0, com.android.tools.r8.graph.InterfaceC0332x5
    public final H0 c() {
        return this;
    }

    @Override // com.android.tools.r8.graph.H0, com.android.tools.r8.graph.InterfaceC0265o0, com.android.tools.r8.graph.InterfaceC0332x5
    public final B5 c() {
        return this;
    }
}
