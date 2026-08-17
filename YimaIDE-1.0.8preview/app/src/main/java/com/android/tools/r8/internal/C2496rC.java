package com.android.tools.r8.internal;

import com.android.tools.r8.dex.code.AbstractC0138z1;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.shaking.C3403i;
import com.android.tools.r8.shaking.C3462u;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.rC, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C2496rC extends AbstractC1133bC {
    public static final /* synthetic */ boolean m = true;

    public C2496rC(C0322w2 c0322w2, C2543rl0 c2543rl0, List list) {
        super(c0322w2, c2543rl0, list);
    }

    public static C2412qC W2() {
        return new C2412qC();
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int H2() {
        return 40;
    }

    @Override // com.android.tools.r8.internal.SB
    public final EnumC2326pC P2() {
        return EnumC2326pC.h;
    }

    @Override // com.android.tools.r8.internal.SB
    public final String Q2() {
        return "Virtual";
    }

    @Override // com.android.tools.r8.internal.AbstractC1047aC
    public final boolean T2() {
        return false;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(C2884vl c2884vl) {
        AbstractC0138z1 k1;
        int iR2 = R2();
        c2884vl.a(iR2);
        if (d(c2884vl)) {
            if (!m) {
                c(c2884vl);
            }
            int iB = b(c2884vl);
            k1 = (e(c2884vl) || f(c2884vl)) ? new com.android.tools.r8.dex.code.L1(iB, iR2, U2()) : new com.android.tools.r8.dex.code.X1(iB, iR2, U2());
        } else {
            int[] iArr = new int[5];
            int iA = a(c2884vl, iArr);
            k1 = (e(c2884vl) || f(c2884vl)) ? new com.android.tools.r8.dex.code.K1(iA, U2(), iArr[0], iArr[1], iArr[2], iArr[3], iArr[4]) : new com.android.tools.r8.dex.code.W1(iA, U2(), iArr[0], iArr[1], iArr[2], iArr[3], iArr[4]);
        }
        a(k1, c2884vl);
    }

    @Override // com.android.tools.r8.internal.AbstractC1047aC, com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean b(AbstractC0890Uw abstractC0890Uw) {
        return abstractC0890Uw.c2() && super.b(abstractC0890Uw);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean c2() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final C2496rC h0() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final com.android.tools.r8.ir.optimize.N a(com.android.tools.r8.ir.optimize.W w, com.android.tools.r8.graph.B5 b5) {
        C0322w2 c0322w2U2 = U2();
        w.getClass();
        if (c0322w2U2.f.I0()) {
            return com.android.tools.r8.ir.optimize.N.d;
        }
        com.android.tools.r8.graph.T4.c<?> cVarO = ((C3403i) w.a.g()).b(c0322w2U2, false).o();
        if (cVarO != null && cVarO.d.B1()) {
            return w.a(cVarO.b, b5, com.android.tools.r8.graph.H0.a(cVarO.c, cVarO.d));
        }
        return com.android.tools.r8.ir.optimize.N.c;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(J8 j8) {
        j8.a(new G9(182, U2(), false), this);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(C3462u c3462u) {
        c3462u.a((com.android.tools.r8.graph.F2) U2());
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(LK lk) {
        lk.a(U2(), this.c);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final Object a(C0941Wv c0941Wv) {
        c0941Wv.a(this);
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean a(com.android.tools.r8.graph.I2 i2, com.android.tools.r8.graph.B5 b5, C0333y c0333y, int i, int i3) {
        if ((i3 == 2 && V2().t().F()) || i == 1) {
            return false;
        }
        ((C3403i) c0333y.g()).i();
        com.android.tools.r8.graph.H0 h0G = g(c0333y.V(), b5);
        if (h0G != null) {
            return AbstractC1244cc.a(this, i2, h0G.e(), c0333y, i);
        }
        C0322w2 c0322w2U2 = U2();
        com.android.tools.r8.graph.T4 t4A = ((C3403i) c0333y.g()).a(c0322w2U2.f, c0322w2U2);
        if (t4A.w()) {
            return c0333y.a(t4A.s().E0(), i2).d();
        }
        return false;
    }
}
