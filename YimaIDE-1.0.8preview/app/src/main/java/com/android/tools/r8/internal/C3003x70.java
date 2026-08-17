package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.x70, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3003x70 extends AbstractC0911Vr {
    public C3003x70() {
        super(null);
        C3087y70 c3087y70 = C3087y70.f;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.l0.a(C3087y70.class, C3003x70.class), c1856jk).a(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.l0.a(C3087y70.class, C3003x70.class), c1856jk).b(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        C3087y70 c3087y70 = new C3087y70(this);
        o();
        if (c3087y70.a()) {
            return c3087y70;
        }
        throw H0.c(c3087y70);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001f  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final C3003x70 a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        C3087y70 c3087y70 = null;
        try {
            try {
                C3087y70.g.getClass();
                a(new C3087y70(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                C3087y70 c3087y71 = (C3087y70) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    c3087y70 = c3087y71;
                    if (c3087y70 != null) {
                        a(c3087y70);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (c3087y70 != null) {
                a(c3087y70);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (C3003x70) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC1468f90.k0;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        C3087y70 c3087y70 = new C3087y70(this);
        o();
        if (c3087y70.a()) {
            return c3087y70;
        }
        throw H0.c(c3087y70);
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 i() {
        C3087y70 c3087y70 = new C3087y70(this);
        o();
        return c3087y70;
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC1468f90.l0.a(C3087y70.class, C3003x70.class);
    }

    public C3003x70(C0859Tr c0859Tr) {
        super(c0859Tr);
        C3087y70 c3087y70 = C3087y70.f;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(J0 j0) {
        if (j0 instanceof C3087y70) {
            return a((C3087y70) j0);
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
        if (j0 instanceof C3087y70) {
            return a((C3087y70) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (C3003x70) c(c2712tk0);
    }

    public final C3003x70 a(C3087y70 c3087y70) {
        if (c3087y70 == C3087y70.f) {
            return this;
        }
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return C3087y70.f;
    }
}
