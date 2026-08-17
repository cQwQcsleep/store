package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class N60 extends AbstractC0911Vr {
    public N60() {
        super(null);
        O60 o60 = O60.f;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.V.a(O60.class, N60.class), c1856jk).a(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.V.a(O60.class, N60.class), c1856jk).b(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        O60 o60 = new O60(this);
        o();
        if (o60.a()) {
            return o60;
        }
        throw H0.c(o60);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001f  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final N60 a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        O60 o60 = null;
        try {
            try {
                O60.g.getClass();
                a(new O60(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                O60 o61 = (O60) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    o60 = o61;
                    if (o60 != null) {
                        a(o60);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (o60 != null) {
                a(o60);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (N60) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC1468f90.U;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        O60 o60 = new O60(this);
        o();
        if (o60.a()) {
            return o60;
        }
        throw H0.c(o60);
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 i() {
        O60 o60 = new O60(this);
        o();
        return o60;
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC1468f90.V.a(O60.class, N60.class);
    }

    public N60(C0859Tr c0859Tr) {
        super(c0859Tr);
        O60 o60 = O60.f;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(J0 j0) {
        if (j0 instanceof O60) {
            return a((O60) j0);
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
        if (j0 instanceof O60) {
            return a((O60) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (N60) c(c2712tk0);
    }

    public final N60 a(O60 o60) {
        if (o60 == O60.f) {
            return this;
        }
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return O60.f;
    }
}
