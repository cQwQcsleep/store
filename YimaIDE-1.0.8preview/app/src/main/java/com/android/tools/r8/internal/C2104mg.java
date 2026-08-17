package com.android.tools.r8.internal;

import com.android.tools.r8.ClassFileConsumer;
import com.android.tools.r8.dex.code.C0028d0;
import com.android.tools.r8.graph.C0333y;
import java.io.UTFDataFormatException;

/* JADX INFO: renamed from: com.android.tools.r8.internal.mg, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C2104mg extends AbstractC1252cg {
    public static final /* synthetic */ boolean k = true;
    public final com.android.tools.r8.graph.H2 j;

    public C2104mg(C2543rl0 c2543rl0, com.android.tools.r8.graph.H2 h2) {
        super(c2543rl0);
        this.j = h2;
    }

    public static C2019lg K2() {
        return new C2019lg();
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean A1() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int F2() {
        if (k) {
            return 0;
        }
        x01.a("ConstString has no register arguments.");
        return 0;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final C2104mg G() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int G2() {
        return 255;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int H2() {
        return 16;
    }

    public com.android.tools.r8.graph.H2 L2() {
        return this.j;
    }

    public final boolean M2() {
        try {
            this.j.toString();
            return false;
        } catch (RuntimeException e) {
            if (e.getCause() instanceof UTFDataFormatException) {
                return true;
            }
            throw e;
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final com.android.tools.r8.ir.optimize.E a(C0333y c0333y, C0705Nt c0705Nt) {
        return ((c0333y.M().j instanceof ClassFileConsumer) || !a(c0333y, c0705Nt.i())) ? com.android.tools.r8.ir.optimize.E.a : com.android.tools.r8.ir.optimize.E.b;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean b(AbstractC0890Uw abstractC0890Uw) {
        return abstractC0890Uw.A1() && abstractC0890Uw.G().j == this.j;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw, com.android.tools.r8.internal.H
    public final boolean g() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean g1() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean r2() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final String toString() {
        return super.toString() + " \"" + this.j + "\"";
    }

    public static C2104mg a(C2543rl0 c2543rl0, C2104mg c2104mg) {
        if (k || c2543rl0 != c2104mg.c()) {
            return new C2104mg(c2543rl0, c2104mg.L2());
        }
        x1f.a();
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(C2884vl c2884vl) {
        c2884vl.a(this, new C0028d0(c2884vl.d.a(this.b, this.e), this.j));
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final B1 a(C0333y c0333y, com.android.tools.r8.graph.B5 b5, J1 j1) {
        if (!M2()) {
            return c0333y.t.a(this.j);
        }
        return Ak0.a;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean a(C0333y c0333y, com.android.tools.r8.graph.B5 b5, J1 j1, C0864Tw c0864Tw) {
        return M2();
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final Object a(C0941Wv c0941Wv) {
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(K5 k5, IL il) {
        il.b(this, k5);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(J8 j8) {
        j8.a(new C1213c9(this.j), this);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final com.android.tools.r8.graph.I2 a(C0333y c0333y, Nj0 nj0) {
        return c0333y.a().Y1;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final AbstractC2624sj0 a(C0333y c0333y) {
        return AbstractC2624sj0.a((C0333y<?>) c0333y, C2427qS.b());
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(C0333y c0333y, com.android.tools.r8.graph.B5 b5, Ol0 ol0) {
        boolean z = k;
        C2441qd c2441qdA = AbstractC2624sj0.a((C0333y<?>) c0333y, C2427qS.b());
        if (z || a().equals(c2441qdA)) {
            return;
        }
        x1f.a();
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(LK lk) {
        lk.a(this.j);
    }
}
