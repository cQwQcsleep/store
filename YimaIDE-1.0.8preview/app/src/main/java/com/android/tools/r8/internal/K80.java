package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class K80 extends AbstractC0911Vr {
    public long f;
    public long g;

    public K80() {
        super(null);
        L80 l80 = L80.h;
    }

    public final K80 a(L80 l80) {
        if (l80 == L80.h) {
            return this;
        }
        long j = l80.e;
        if (j != 0) {
            this.f = j;
            p();
        }
        long j2 = l80.f;
        if (j2 != 0) {
            this.g = j2;
            p();
        }
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.X0.a(L80.class, K80.class), c1856jk).b(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        L80 l80 = new L80(this);
        l80.e = this.f;
        l80.f = this.g;
        o();
        if (l80.a()) {
            return l80;
        }
        throw H0.c(l80);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001f  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final K80 a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        L80 l80 = null;
        try {
            try {
                L80.i.getClass();
                a(new L80(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                L80 l81 = (L80) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    l80 = l81;
                    if (l80 != null) {
                        a(l80);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (l80 != null) {
                a(l80);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (K80) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC1468f90.W0;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        L80 l80 = new L80(this);
        l80.e = this.f;
        l80.f = this.g;
        o();
        if (l80.a()) {
            return l80;
        }
        throw H0.c(l80);
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 i() {
        L80 l80 = new L80(this);
        l80.e = this.f;
        l80.f = this.g;
        o();
        return l80;
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC1468f90.X0.a(L80.class, K80.class);
    }

    public K80(C0859Tr c0859Tr) {
        super(c0859Tr);
        L80 l80 = L80.h;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof L80) {
            return a((L80) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return L80.h;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.X0.a(L80.class, K80.class), c1856jk).a(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(J0 j0) {
        if (j0 instanceof L80) {
            return a((L80) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (K80) c(c2712tk0);
    }
}
