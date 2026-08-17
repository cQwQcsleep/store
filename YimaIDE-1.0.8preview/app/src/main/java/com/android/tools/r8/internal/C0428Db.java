package com.android.tools.r8.internal;

import com.android.tools.r8.dex.code.C0095q2;
import com.android.tools.r8.dex.code.C0099r2;
import com.android.tools.r8.graph.AbstractC0194e;
import com.android.tools.r8.graph.C0229j;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.shaking.C3403i;
import com.android.tools.r8.shaking.C3462u;
import defpackage.bb3;
import java.util.Collections;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Db, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0428Db extends AbstractC0890Uw {
    public static final /* synthetic */ boolean k = true;
    public final com.android.tools.r8.graph.I2 i;
    public final boolean j;

    public C0428Db(C2543rl0 c2543rl0, C2543rl0 c2543rl1, com.android.tools.r8.graph.I2 i2) {
        super(c2543rl0, c2543rl1);
        this.i = i2;
        this.j = false;
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
        return 10;
    }

    public final com.android.tools.r8.graph.I2 K2() {
        return this.i;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public boolean a(C0333y c0333y, com.android.tools.r8.graph.B5 b5, J1 j1, C0864Tw c0864Tw) {
        com.android.tools.r8.graph.E0 e0D;
        if (c0333y.M().Z0 || !c0333y.g().i() || this.i.T0()) {
            return true;
        }
        C0333y<C3403i> c0333yV = c0333y.V();
        com.android.tools.r8.graph.I2 i2A = this.i.a(c0333y.a());
        if (!(i2A.M0() && ((e0D = c0333y.d(i2A)) == null || !e0D.d(c0333y) || AbstractC0194e.a(e0D, b5, c0333yV, (C0229j) c0333yV.g()).b())) && c0333y.K.a(c0333yV, f())) {
            return !f().b(c0333yV).b(AbstractC2624sj0.a(this.i, C2427qS.b(), (C0333y<?>) c0333y), c0333y);
        }
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean b(AbstractC0890Uw abstractC0890Uw) {
        return abstractC0890Uw.u1() && abstractC0890Uw.z().i == this.i;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean b1() {
        return false;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean d(C0333y c0333y, com.android.tools.r8.graph.B5 b5) {
        return false;
    }

    public final C2543rl0 f() {
        return (C2543rl0) this.c.get(0);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw, com.android.tools.r8.internal.H
    public final boolean g() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final String toString() {
        return super.toString() + "; " + this.i;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean u1() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final C0428Db z() {
        return this;
    }

    public C0428Db(C2543rl0 c2543rl0, C2543rl0 c2543rl1, com.android.tools.r8.graph.I2 i2, boolean z) {
        super(c2543rl0, c2543rl1);
        this.i = i2;
        this.j = z;
    }

    public com.android.tools.r8.dex.code.O b(int i) {
        return new com.android.tools.r8.dex.code.O(i, this.i, this.j);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(C2884vl c2884vl) {
        int iA = c2884vl.d.a((C2543rl0) this.c.get(0), this.e);
        C2543rl0 c2543rl0 = this.b;
        if (c2543rl0 == null) {
            c2884vl.a(this, b(iA));
            return;
        }
        int iA2 = c2884vl.d.a(c2543rl0, this.e);
        if (iA == iA2) {
            c2884vl.a(this, b(iA2));
            return;
        }
        com.android.tools.r8.dex.code.O oB = b(iA2);
        if (iA2 <= 15 && iA <= 15) {
            c2884vl.a(this, new C0095q2(iA2, iA), oB);
        } else {
            c2884vl.a(this, new C0099r2(iA2, iA), oB);
        }
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
    public final AbstractC2624sj0 a(C0333y c0333y) {
        return AbstractC2624sj0.a(this.i, f().t().N(), (C0333y<?>) c0333y);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(C0333y c0333y, com.android.tools.r8.graph.B5 b5, Ol0 ol0) {
        boolean z = k;
        AbstractC2624sj0 abstractC2624sj0T = f().t();
        if (!z && !abstractC2624sj0T.G()) {
            x1f.a();
            return;
        }
        AbstractC2624sj0 abstractC2624sj0A = a();
        AbstractC2624sj0 abstractC2624sj0A2 = AbstractC2624sj0.a(this.i, abstractC2624sj0T.N(), (C0333y<?>) c0333y);
        if (!z && !abstractC2624sj0A.a(abstractC2624sj0A2)) {
            x1f.a();
        } else {
            if (z || abstractC2624sj0T.N() == abstractC2624sj0A.N() || abstractC2624sj0T.E()) {
                return;
            }
            bb3.a("Expected nullability of value ", c(), " defined by ", this, " to be ", abstractC2624sj0T.N(), ", but was ", abstractC2624sj0A.N(), "(context: ", b5.v());
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(K5 k5, IL il) {
        il.a(this, k5);
        il.b(this, k5);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final com.android.tools.r8.graph.I2 a(C0333y c0333y, Nj0 nj0) {
        return this.i;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public void a(J8 j8) {
        j8.a(new K8(this.i), this);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(C3462u c3462u) {
        c3462u.a((com.android.tools.r8.graph.F2) this.i);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public void a(LK lk) {
        com.android.tools.r8.graph.I2 i2 = this.i;
        C2543rl0 c2543rl0F = f();
        boolean z = this.j;
        lk.getClass();
        lk.a(z ? 225 : 192, Collections.singletonList(i2), Collections.singletonList(c2543rl0F));
    }
}
