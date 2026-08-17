package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;

/* JADX INFO: renamed from: com.android.tools.r8.internal.tP, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C2680tP extends AbstractC0890Uw {
    public static final /* synthetic */ boolean i = true;

    public C2680tP(C2543rl0 c2543rl0, C2543rl0 c2543rl1) {
        super(c2543rl0, c2543rl1);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int F2() {
        return 65535;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int G2() {
        return 65535;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public int H2() {
        return 43;
    }

    public C2543rl0 K2() {
        return this.b;
    }

    public C2543rl0 L2() {
        return (C2543rl0) this.c.get(0);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final AbstractC1252cg X0() {
        if (i || r2()) {
            return L2().c.X0();
        }
        x1f.a();
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public void a(C0333y c0333y, com.android.tools.r8.graph.B5 b5, Ol0 ol0) {
        if ((this instanceof C0642Lh) || i || L2().t().equals(a())) {
            return;
        }
        x1f.a();
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public boolean b(AbstractC0890Uw abstractC0890Uw) {
        return abstractC0890Uw.i2();
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean b1() {
        return false;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean d(C0333y c0333y, com.android.tools.r8.graph.B5 b5) {
        return false;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean i2() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public C2680tP m0() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public boolean r2() {
        return L2().J();
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final String toString() {
        return super.toString() + " (" + a() + ")";
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public Object a(C0941Wv c0941Wv) {
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean a(C0333y c0333y, C2543rl0 c2543rl0) {
        throw new Kk0("as long as we're analyzing SSA IR.");
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public void a(C2884vl c2884vl) {
        c2884vl.getClass();
        c2884vl.a(this, new C2542rl(this));
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public void a(J8 j8) {
        throw new Kk0("This DEX-specific instruction should not be seen in the CF backend");
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public void a(LK lk) {
        throw new Kk0("This DEX-specific instruction should not be seen in the CF backend");
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final AbstractC2624sj0 a(C0333y c0333y) {
        return L2().t();
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public void a(K5 k5, IL il) {
        throw new Kk0("This DEX-specific instruction should not be seen in the CF backend");
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final com.android.tools.r8.ir.optimize.N a(com.android.tools.r8.ir.optimize.W w, com.android.tools.r8.graph.B5 b5) {
        return com.android.tools.r8.ir.optimize.N.d;
    }
}
