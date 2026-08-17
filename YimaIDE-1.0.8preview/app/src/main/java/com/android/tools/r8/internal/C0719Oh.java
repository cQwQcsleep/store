package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Oh, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0719Oh extends AbstractC0890Uw {
    public static final /* synthetic */ boolean i = true;

    public C0719Oh() {
        super(null);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int F2() {
        throw new Kk0();
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int G2() {
        throw new Kk0();
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int H2() {
        return 19;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final C0719Oh J() {
        return this;
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x0017, code lost:
    
        if ((r0 instanceof com.android.tools.r8.internal.AbstractC2004lX.c) == false) goto L11;
     */
    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void a(C2884vl c2884vl) {
        if (!i) {
            if (!getPosition().n()) {
                AbstractC2004lX position = getPosition();
                position.getClass();
            }
            x1f.a();
            return;
        }
        c2884vl.getClass();
        c2884vl.a(this, new C2200nl(this, new com.android.tools.r8.dex.code.P2()));
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean b(AbstractC0890Uw abstractC0890Uw) {
        abstractC0890Uw.getClass();
        return abstractC0890Uw instanceof C0719Oh;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean b1() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean d(C0333y c0333y, com.android.tools.r8.graph.B5 b5) {
        return false;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean i1() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(K5 k5, IL il) {
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final Object a(C0941Wv c0941Wv) {
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final com.android.tools.r8.ir.optimize.E a(C0333y c0333y, C0705Nt c0705Nt) {
        return com.android.tools.r8.ir.optimize.E.b;
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x0017, code lost:
    
        if ((r0 instanceof com.android.tools.r8.internal.AbstractC2004lX.c) == false) goto L11;
     */
    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void a(J8 j8) {
        if (!i) {
            if (!getPosition().n()) {
                AbstractC2004lX position = getPosition();
                position.getClass();
            }
            x1f.a();
            return;
        }
        j8.a(new Z9(), this);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final com.android.tools.r8.ir.optimize.N a(com.android.tools.r8.ir.optimize.W w, com.android.tools.r8.graph.B5 b5) {
        return com.android.tools.r8.ir.optimize.N.d;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(LK lk) {
        AbstractC2004lX position = getPosition();
        if (!LK.u && lk.k != position) {
            x1f.a();
        } else {
            lk.c();
            lk.c.a(209);
        }
    }
}
