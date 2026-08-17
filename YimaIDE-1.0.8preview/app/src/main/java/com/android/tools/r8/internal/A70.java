package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class A70 extends AbstractC0911Vr {
    public A70() {
        super(null);
        B70 b70 = B70.f;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.j0.a(B70.class, A70.class), c1856jk).a(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.j0.a(B70.class, A70.class), c1856jk).b(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        B70 b70 = new B70(this);
        o();
        if (b70.a()) {
            return b70;
        }
        throw H0.c(b70);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001f  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final A70 a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        B70 b70 = null;
        try {
            try {
                B70.g.getClass();
                a(new B70(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                B70 b71 = (B70) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    b70 = b71;
                    if (b70 != null) {
                        a(b70);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (b70 != null) {
                a(b70);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (A70) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC1468f90.i0;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        B70 b70 = new B70(this);
        o();
        if (b70.a()) {
            return b70;
        }
        throw H0.c(b70);
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 i() {
        B70 b70 = new B70(this);
        o();
        return b70;
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC1468f90.j0.a(B70.class, A70.class);
    }

    public A70(C0859Tr c0859Tr) {
        super(c0859Tr);
        B70 b70 = B70.f;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(J0 j0) {
        if (j0 instanceof B70) {
            return a((B70) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof B70) {
            return a((B70) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (A70) c(c2712tk0);
    }

    public final A70 a(B70 b70) {
        if (b70 == B70.f) {
            return this;
        }
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return B70.f;
    }
}
