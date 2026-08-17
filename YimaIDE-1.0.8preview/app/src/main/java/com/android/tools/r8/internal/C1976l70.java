package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.l70, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1976l70 extends AbstractC0911Vr {
    public int f;

    public C1976l70() {
        super(null);
        C2062m70 c2062m70 = C2062m70.g;
    }

    public final C1976l70 a(C2062m70 c2062m70) {
        if (c2062m70 == C2062m70.g) {
            return this;
        }
        int i = c2062m70.e;
        if (i != 0) {
            this.f = i;
            p();
        }
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.n.a(C2062m70.class, C1976l70.class), c1856jk).b(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        C2062m70 c2062m70I = i();
        if (c2062m70I.a()) {
            return c2062m70I;
        }
        throw H0.c(c2062m70I);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001f  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final C1976l70 a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        C2062m70 c2062m70 = null;
        try {
            try {
                C2062m70.h.getClass();
                a(new C2062m70(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                C2062m70 c2062m71 = (C2062m70) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    c2062m70 = c2062m71;
                    if (c2062m70 != null) {
                        a(c2062m70);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (c2062m70 != null) {
                a(c2062m70);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (C1976l70) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC1468f90.m;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        C2062m70 c2062m70I = i();
        if (c2062m70I.a()) {
            return c2062m70I;
        }
        throw H0.c(c2062m70I);
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC1468f90.n.a(C2062m70.class, C1976l70.class);
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public final C2062m70 i() {
        C2062m70 c2062m70 = new C2062m70(this);
        c2062m70.e = this.f;
        o();
        return c2062m70;
    }

    public C1976l70(C0859Tr c0859Tr) {
        super(c0859Tr);
        C2062m70 c2062m70 = C2062m70.g;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof C2062m70) {
            return a((C2062m70) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return C2062m70.g;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.n.a(C2062m70.class, C1976l70.class), c1856jk).a(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(J0 j0) {
        if (j0 instanceof C2062m70) {
            return a((C2062m70) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (C1976l70) c(c2712tk0);
    }
}
