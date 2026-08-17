package com.android.tools.r8.internal;

import com.android.tools.r8.graph.AbstractC0194e;
import com.android.tools.r8.graph.C0229j;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.shaking.C3462u;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class KQ extends AbstractC0890Uw {
    public static final /* synthetic */ boolean k = true;
    public final com.android.tools.r8.graph.I2 i;
    public boolean j;

    public KQ(com.android.tools.r8.graph.I2 i2, C2543rl0 c2543rl0) {
        super(c2543rl0);
        this.j = true;
        if (k || i2 != null) {
            this.i = i2;
        } else {
            x1f.a();
            throw null;
        }
    }

    public static JQ K2() {
        return new JQ();
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int F2() {
        if (k) {
            return 0;
        }
        x01.a("NewInstance has no register arguments");
        return 0;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int G2() {
        return 255;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int H2() {
        return 49;
    }

    public com.android.tools.r8.graph.I2 L2() {
        return this.i;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(C0333y c0333y, com.android.tools.r8.graph.B5 b5, Ol0 ol0) {
        AbstractC2624sj0 abstractC2624sj0A = a();
        boolean z = k;
        if (!z && !abstractC2624sj0A.w()) {
            x1f.a();
            return;
        }
        if (!z && abstractC2624sj0A.b().Q() != this.i && !c0333y.M().u1.P) {
            x1f.a();
        } else {
            if (z || abstractC2624sj0A.x()) {
                return;
            }
            x1f.a();
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean b(C0333y c0333y, com.android.tools.r8.graph.B5 b5, J1 j1, C0864Tw c0864Tw) {
        C0322w2 reference;
        com.android.tools.r8.graph.B1 b1A = c0333y.a();
        if (!c0333y.o()) {
            return (b1A.b6.contains(this.i) && b1A.c6.contains(this.i)) ? false : true;
        }
        boolean z = k;
        if (!z && !c0333y.g().h()) {
            x1f.a();
            return false;
        }
        C0333y c0333yU = c0333y.U();
        if (this.i.T0() || this.i.I0()) {
            if (z) {
                return true;
            }
            x01.a("Unexpected new-instance instruction with primitive or array type");
            return false;
        }
        com.android.tools.r8.graph.E0 e0D = c0333y.d(this.i);
        if (e0D == null || e0D.n1() || !e0D.d(c0333y) || AbstractC0194e.a(e0D, b5, c0333yU, (C0229j) c0333yU.g()).b() || e0D.a(c0333yU, b5)) {
            return true;
        }
        com.android.tools.r8.graph.T4 t4A = ((C0229j) c0333yU.g()).a(this.i, b1A.p4.g);
        return (!t4A.w() || (reference = t4A.s().getReference()) == b1A.B4.l || reference == b1A.p4.g) ? false : true;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean b1() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean d(C0333y c0333y, com.android.tools.r8.graph.B5 b5) {
        boolean zO = c0333y.o();
        com.android.tools.r8.graph.I2 i2 = this.i;
        if (zO) {
            return i2.a(c0333y, b5);
        }
        return i2 != b5.s();
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw, com.android.tools.r8.internal.H
    public final boolean g() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean o2() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final KQ t0() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final String toString() {
        return super.toString() + " " + this.i;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final com.android.tools.r8.ir.optimize.N a(com.android.tools.r8.ir.optimize.W w, com.android.tools.r8.graph.B5 b5) {
        return com.android.tools.r8.ir.optimize.N.a(w.a, this.i, b5);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(K5 k5, IL il) {
        il.b(this, k5);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(J8 j8) {
        j8.a(new W9(this.i), this);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final com.android.tools.r8.graph.I2 a(C0333y c0333y, Nj0 nj0) {
        return this.i;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final AbstractC2624sj0 a(C0333y c0333y) {
        return AbstractC2624sj0.a(this.i, C2427qS.b(), (C0333y<?>) c0333y);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final Object a(C0941Wv c0941Wv) {
        c0941Wv.a(this.i);
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(C2884vl c2884vl) {
        c2884vl.a(this, new com.android.tools.r8.dex.code.N2(c2884vl.d.a(this.b, this.e), this.i));
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(C3462u c3462u) {
        c3462u.a((com.android.tools.r8.graph.F2) this.i);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(LK lk) {
        lk.a(this.i);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean a(com.android.tools.r8.graph.I2 i2, com.android.tools.r8.graph.B5 b5, C0333y c0333y, int i, int i3) {
        com.android.tools.r8.graph.E0 e0D;
        return (i3 == 2 || (e0D = c0333y.d(this.i)) == null || !AbstractC1244cc.a(this, i2, e0D, c0333y, i)) ? false : true;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean b(AbstractC0890Uw abstractC0890Uw) {
        return abstractC0890Uw.o2() && abstractC0890Uw.t0().i == this.i;
    }
}
