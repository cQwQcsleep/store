package com.android.tools.r8.internal;

import com.android.tools.r8.graph.AbstractC0194e;
import com.android.tools.r8.graph.C0229j;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.shaking.C3403i;
import com.android.tools.r8.shaking.C3462u;
import java.util.Collections;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Qv, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0785Qv extends AbstractC0890Uw {
    public static final /* synthetic */ boolean j = true;
    public final com.android.tools.r8.graph.I2 i;

    public C0785Qv(com.android.tools.r8.graph.I2 i2, C2543rl0 c2543rl0) {
        super(c2543rl0);
        boolean z = j;
        if (!z && !c1()) {
            x1f.a();
            throw null;
        }
        if (!z) {
            AbstractC2624sj0 abstractC2624sj0T = c2543rl0.t();
            abstractC2624sj0T.getClass();
            if (!(abstractC2624sj0T instanceof GA)) {
                x1f.a();
                throw null;
            }
        }
        if (z || i2.M0()) {
            this.i = i2;
        } else {
            x1f.a();
            throw null;
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int F2() {
        return 255;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int G2() {
        return 255;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int H2() {
        return 27;
    }

    public final com.android.tools.r8.graph.I2 K2() {
        return this.i;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean M1() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final C0785Qv S() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean a(C0333y c0333y, com.android.tools.r8.graph.B5 b5, J1 j1, C0864Tw c0864Tw) {
        if (!j && !c0333y.o()) {
            x1f.a();
            return false;
        }
        C0333y<C3403i> c0333yV = c0333y.V();
        com.android.tools.r8.graph.E0 e0D = c0333y.d(this.i);
        return e0D == null || AbstractC0194e.a(e0D, b5, c0333yV, (C0229j) c0333yV.g()).b() || this.i.a(c0333y, b5);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean b(AbstractC0890Uw abstractC0890Uw) {
        return abstractC0890Uw.M1() && this.i == abstractC0890Uw.S().i;
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
    public final String toString() {
        return super.toString() + "; " + this.i.m0();
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(C3462u c3462u) {
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean a(com.android.tools.r8.graph.I2 i2, com.android.tools.r8.graph.B5 b5, C0333y c0333y, int i, int i3) {
        com.android.tools.r8.graph.E0 e0D;
        return (i3 == 2 || (e0D = c0333y.d(this.i)) == null || !AbstractC1244cc.a(this, i2, e0D, c0333y, i)) ? false : true;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final AbstractC2624sj0 a(C0333y c0333y) {
        return AbstractC2624sj0.k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(C2884vl c2884vl) {
        throw new Kk0();
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(J8 j8) {
        throw new Kk0();
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(LK lk) {
        com.android.tools.r8.graph.I2 i2 = this.i;
        lk.getClass();
        lk.a(221, Collections.singletonList(i2), Collections.EMPTY_LIST);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final Object a(C0941Wv c0941Wv) {
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final com.android.tools.r8.ir.optimize.N a(com.android.tools.r8.ir.optimize.W w, com.android.tools.r8.graph.B5 b5) {
        return com.android.tools.r8.ir.optimize.N.a(w.a, this.i, b5);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(K5 k5, IL il) {
        il.b(this, k5);
    }
}
