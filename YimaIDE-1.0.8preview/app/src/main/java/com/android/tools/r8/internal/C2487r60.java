package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.r60, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2487r60 extends AbstractC0911Vr {
    public boolean f;

    public C2487r60() {
        super(null);
        C2573s60 c2573s60 = C2573s60.g;
    }

    public final C2487r60 a(C2573s60 c2573s60) {
        if (c2573s60 == C2573s60.g) {
            return this;
        }
        boolean z = c2573s60.e;
        if (z) {
            this.f = z;
            p();
        }
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.R.a(C2573s60.class, C2487r60.class), c1856jk).b(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        C2573s60 c2573s60 = new C2573s60(this);
        c2573s60.e = this.f;
        o();
        if (c2573s60.a()) {
            return c2573s60;
        }
        throw H0.c(c2573s60);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001f  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final C2487r60 a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        C2573s60 c2573s60 = null;
        try {
            try {
                C2573s60.h.getClass();
                a(new C2573s60(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                C2573s60 c2573s61 = (C2573s60) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    c2573s60 = c2573s61;
                    if (c2573s60 != null) {
                        a(c2573s60);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (c2573s60 != null) {
                a(c2573s60);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (C2487r60) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC1468f90.Q;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        C2573s60 c2573s60 = new C2573s60(this);
        c2573s60.e = this.f;
        o();
        if (c2573s60.a()) {
            return c2573s60;
        }
        throw H0.c(c2573s60);
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 i() {
        C2573s60 c2573s60 = new C2573s60(this);
        c2573s60.e = this.f;
        o();
        return c2573s60;
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC1468f90.R.a(C2573s60.class, C2487r60.class);
    }

    public C2487r60(C0859Tr c0859Tr) {
        super(c0859Tr);
        C2573s60 c2573s60 = C2573s60.g;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof C2573s60) {
            return a((C2573s60) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return C2573s60.g;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.R.a(C2573s60.class, C2487r60.class), c1856jk).a(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(J0 j0) {
        if (j0 instanceof C2573s60) {
            return a((C2573s60) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (C2487r60) c(c2712tk0);
    }
}
