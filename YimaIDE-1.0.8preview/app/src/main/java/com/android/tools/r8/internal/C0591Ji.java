package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Ji, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0591Ji extends AbstractC0911Vr {
    public int f;
    public int g;
    public int h;

    public C0591Ji() {
        super(null);
    }

    public final C0591Ji a(C0617Ki c0617Ki) {
        if (c0617Ki == C0617Ki.i) {
            return this;
        }
        if ((c0617Ki.e & 1) != 0) {
            int i = c0617Ki.f;
            this.f |= 1;
            this.g = i;
            p();
        }
        if ((c0617Ki.e & 2) != 0) {
            int i2 = c0617Ki.g;
            this.f |= 2;
            this.h = i2;
            p();
        }
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof C0617Ki) {
            return a((C0617Ki) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        C0617Ki c0617KiI = i();
        if (c0617KiI.a()) {
            return c0617KiI;
        }
        throw H0.c(c0617KiI);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001f  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final C0591Ji a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        C0617Ki c0617Ki = null;
        try {
            try {
                C0617Ki.j.getClass();
                a(new C0617Ki(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                C0617Ki c0617Ki2 = (C0617Ki) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    c0617Ki = c0617Ki2;
                    if (c0617Ki != null) {
                        a(c0617Ki);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (c0617Ki != null) {
                a(c0617Ki);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (C0591Ji) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC0877Uj.q;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        C0617Ki c0617KiI = i();
        if (c0617KiI.a()) {
            return c0617KiI;
        }
        throw H0.c(c0617KiI);
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC0877Uj.r.a(C0617Ki.class, C0591Ji.class);
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public final C0617Ki i() {
        int i;
        C0617Ki c0617Ki = new C0617Ki(this);
        int i2 = this.f;
        if ((i2 & 1) != 0) {
            c0617Ki.f = this.g;
            i = 1;
        } else {
            i = 0;
        }
        if ((i2 & 2) != 0) {
            c0617Ki.g = this.h;
            i |= 2;
        }
        c0617Ki.e = i;
        o();
        return c0617Ki;
    }

    public C0591Ji(C0859Tr c0859Tr) {
        super(c0859Tr);
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C1856jk c1856jk, Object obj) {
        C2123ms.a(n(), c1856jk).b(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return C0617Ki.i;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(J0 j0) {
        if (j0 instanceof C0617Ki) {
            return a((C0617Ki) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (C0591Ji) c(c2712tk0);
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        C2123ms.a(n(), c1856jk).a(this, obj);
        return this;
    }
}
