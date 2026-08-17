package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.a80, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1042a80 extends AbstractC0911Vr {
    public U7 f;

    public C1042a80() {
        super(null);
        this.f = U7.c;
        C1128b80 c1128b80 = C1128b80.g;
    }

    public final C1042a80 a(C1128b80 c1128b80) {
        if (c1128b80 == C1128b80.g) {
            return this;
        }
        U7 u7 = c1128b80.e;
        if (u7 != U7.c) {
            u7.getClass();
            this.f = u7;
            p();
        }
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.b.a(C1128b80.class, C1042a80.class), c1856jk).b(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        C1128b80 c1128b80 = new C1128b80(this);
        c1128b80.e = this.f;
        o();
        if (c1128b80.a()) {
            return c1128b80;
        }
        throw H0.c(c1128b80);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001d  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final C1042a80 a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        C1128b80 c1128b80 = null;
        try {
            try {
                a((C1128b80) C1128b80.h.a(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                C1128b80 c1128b81 = (C1128b80) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    c1128b80 = c1128b81;
                    if (c1128b80 != null) {
                        a(c1128b80);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (c1128b80 != null) {
                a(c1128b80);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (C1042a80) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC1468f90.a;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        C1128b80 c1128b80 = new C1128b80(this);
        c1128b80.e = this.f;
        o();
        if (c1128b80.a()) {
            return c1128b80;
        }
        throw H0.c(c1128b80);
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 i() {
        C1128b80 c1128b80 = new C1128b80(this);
        c1128b80.e = this.f;
        o();
        return c1128b80;
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC1468f90.b.a(C1128b80.class, C1042a80.class);
    }

    public C1042a80(C0859Tr c0859Tr) {
        super(c0859Tr);
        this.f = U7.c;
        C1128b80 c1128b80 = C1128b80.g;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof C1128b80) {
            return a((C1128b80) j0);
        }
        super.b(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return C1128b80.g;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.b.a(C1128b80.class, C1042a80.class), c1856jk).a(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: a */
    public final H0 b(J0 j0) {
        if (j0 instanceof C1128b80) {
            return a((C1128b80) j0);
        }
        super.b(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (C1042a80) c(c2712tk0);
    }
}
