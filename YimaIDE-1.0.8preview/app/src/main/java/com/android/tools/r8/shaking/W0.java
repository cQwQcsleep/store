package com.android.tools.r8.shaking;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class W0 extends AbstractC3385e1 {
    public boolean j;
    public boolean k;
    public boolean l;
    public boolean m;
    public boolean n;
    public boolean o;
    public boolean p;

    public W0(Y0 y0) {
        super(y0);
        this.j = y0.i;
        this.k = y0.j;
        this.l = y0.k;
        this.m = y0.l;
        this.n = y0.m;
        this.o = y0.n;
        this.p = y0.o;
    }

    @Override // com.android.tools.r8.shaking.AbstractC3385e1
    public final boolean a(Y0 y0) {
        return super.a((AbstractC3395g1) y0) && this.p == y0.o && this.j == y0.i && this.k == y0.j && this.l == y0.k && this.m == y0.l && this.n == y0.m && this.o == y0.n;
    }

    @Override // com.android.tools.r8.shaking.AbstractC3385e1
    public final boolean b(AbstractC3395g1 abstractC3395g1) {
        return a((Y0) abstractC3395g1);
    }

    @Override // com.android.tools.r8.shaking.AbstractC3385e1
    public final AbstractC3395g1 e() {
        return new Y0(this);
    }

    @Override // com.android.tools.r8.shaking.AbstractC3385e1
    public final AbstractC3395g1 f() {
        return Y0.q;
    }

    @Override // com.android.tools.r8.shaking.AbstractC3385e1
    public final AbstractC3395g1 g() {
        return Y0.p;
    }

    @Override // com.android.tools.r8.shaking.AbstractC3385e1
    public final AbstractC3385e1 j() {
        return this;
    }

    @Override // com.android.tools.r8.shaking.AbstractC3385e1
    /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
    public final W0 i() {
        this.b = false;
        this.c = false;
        this.d = false;
        b();
        c();
        d();
        this.h = false;
        this.i = false;
        W0 w0 = (W0) j();
        w0.j = false;
        w0.k = false;
        w0.l = false;
        w0.m = false;
        w0.n = false;
        w0.o = false;
        w0.p = false;
        return w0;
    }

    public W0() {
    }
}
