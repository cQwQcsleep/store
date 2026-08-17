package com.android.tools.r8.internal;

import com.android.tools.r8.dex.code.AbstractC0138z1;
import com.android.tools.r8.graph.C0229j;
import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.shaking.C3403i;
import com.android.tools.r8.shaking.C3462u;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class VB extends AbstractC1133bC {
    public static final /* synthetic */ boolean n = true;
    public final boolean m;

    public VB(C0322w2 c0322w2, C2543rl0 c2543rl0, List list, boolean z) {
        super(c0322w2, c2543rl0, list);
        this.m = z;
        if (n || !c0322w2.g.toString().equals("<init>") || c2543rl0 == null) {
            return;
        }
        x1f.a();
        throw null;
    }

    public static UB W2() {
        return new UB();
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int H2() {
        return 33;
    }

    @Override // com.android.tools.r8.internal.SB
    public final EnumC2326pC P2() {
        return EnumC2326pC.d;
    }

    @Override // com.android.tools.r8.internal.SB
    public final String Q2() {
        return "Direct";
    }

    @Override // com.android.tools.r8.internal.AbstractC1047aC
    public final boolean T2() {
        return this.m;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean U1() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final VB Z() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(C2884vl c2884vl) {
        AbstractC0138z1 k1;
        int iR2 = R2();
        c2884vl.a(iR2);
        if (d(c2884vl)) {
            if (!n) {
                c(c2884vl);
            }
            k1 = new com.android.tools.r8.dex.code.L1(b(c2884vl), iR2, U2());
        } else {
            int[] iArr = new int[5];
            k1 = new com.android.tools.r8.dex.code.K1(a(c2884vl, iArr), U2(), iArr[0], iArr[1], iArr[2], iArr[3], iArr[4]);
        }
        a(k1, c2884vl);
    }

    @Override // com.android.tools.r8.internal.AbstractC1047aC, com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean b(AbstractC0890Uw abstractC0890Uw) {
        return abstractC0890Uw.U1() && super.b(abstractC0890Uw);
    }

    @Override // com.android.tools.r8.internal.AbstractC1047aC, com.android.tools.r8.internal.AbstractC0890Uw
    public final AbstractC2389q e(C0333y c0333y, com.android.tools.r8.graph.B5 b5) {
        com.android.tools.r8.graph.H0 h0G;
        if (!c0333y.a().b(U2()) || (h0G = g(c0333y, b5)) == null || !(h0G instanceof com.android.tools.r8.graph.B5)) {
            return ZJ.a(c0333y, this);
        }
        C0231j1 c0231j1E = h0G.e();
        c0231j1E.O0();
        return c0231j1E.m.a(this).f();
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean a(com.android.tools.r8.graph.B1 b1) {
        return U2().b(b1);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final com.android.tools.r8.ir.optimize.N a(com.android.tools.r8.ir.optimize.W w, com.android.tools.r8.graph.B5 b5) {
        C0322w2 c0322w2U2 = U2();
        w.getClass();
        if (c0322w2U2.f.I0()) {
            return com.android.tools.r8.ir.optimize.N.d;
        }
        com.android.tools.r8.graph.T4.c<?> cVarO = ((C3403i) w.a.g()).f(c0322w2U2).o();
        if (cVarO == null) {
            return com.android.tools.r8.ir.optimize.N.c;
        }
        com.android.tools.r8.graph.D2 d2A = b5.a();
        C0333y c0333y = w.a;
        com.android.tools.r8.graph.H0 h0A = cVarO.a(d2A, c0333y, (C0229j) c0333y.g());
        if (h0A == null) {
            return com.android.tools.r8.ir.optimize.N.c;
        }
        return w.a(cVarO.b, b5, h0A);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(J8 j8) {
        j8.a(new G9(183, U2(), this.m), this);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final com.android.tools.r8.ir.optimize.E a(C0333y c0333y, C0705Nt c0705Nt) {
        com.android.tools.r8.graph.B5 b5I = c0705Nt.i();
        if (b(c0333y, b5I, J1.a(c0333y, b5I), C0864Tw.c)) {
            return com.android.tools.r8.ir.optimize.E.b;
        }
        if (!U2().b(c0333y.a())) {
            return com.android.tools.r8.ir.optimize.E.a;
        }
        if (V2().h() == c0705Nt.o()) {
            return com.android.tools.r8.ir.optimize.E.b;
        }
        return new com.android.tools.r8.ir.optimize.D(V2());
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final Object a(C0941Wv c0941Wv) {
        c0941Wv.a(this);
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(LK lk) {
        lk.a(U2(), this.c, this.m);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(C3462u c3462u) {
        c3462u.a((com.android.tools.r8.graph.F2) U2());
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean a(com.android.tools.r8.graph.I2 i2, com.android.tools.r8.graph.B5 b5, C0333y c0333y, int i, int i3) {
        if (i3 == 2 && V2().t().F()) {
            return false;
        }
        C0322w2 c0322w2U2 = U2();
        c0333y.getClass();
        C0231j1 c0231j1C = c0322w2U2.c(c0333y.d(c0322w2U2.f));
        return c0231j1C != null && AbstractC1244cc.a(this, i2, c0231j1C, c0333y, i);
    }
}
