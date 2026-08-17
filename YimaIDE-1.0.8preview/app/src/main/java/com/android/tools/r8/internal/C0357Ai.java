package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Ai, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0357Ai extends AbstractC0911Vr {
    public int f;
    public int g;
    public int h;
    public C0954Xi i;

    public C0357Ai() {
        super(null);
    }

    public final C0357Ai a(C0383Bi c0383Bi) {
        C0954Xi c0954Xi;
        C0954Xi c0954Xi2;
        if (c0383Bi == C0383Bi.j) {
            return this;
        }
        if ((c0383Bi.e & 1) != 0) {
            int i = c0383Bi.f;
            this.f |= 1;
            this.g = i;
            p();
        }
        if ((c0383Bi.e & 2) != 0) {
            int i2 = c0383Bi.g;
            this.f |= 2;
            this.h = i2;
            p();
        }
        if (c0383Bi.l()) {
            C0954Xi c0954XiK = c0383Bi.k();
            if ((this.f & 4) == 0 || (c0954Xi = this.i) == null || c0954Xi == (c0954Xi2 = C0954Xi.h)) {
                this.i = c0954XiK;
            } else {
                this.i = c0954Xi2.d().a(c0954Xi).a(c0954XiK).i();
            }
            p();
            this.f |= 4;
        }
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof C0383Bi) {
            return a((C0383Bi) j0);
        }
        super.b(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        C0383Bi c0383BiI = i();
        if (c0383BiI.a()) {
            return c0383BiI;
        }
        throw H0.c(c0383BiI);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001f  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final C0357Ai a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        C0383Bi c0383Bi = null;
        try {
            try {
                C0383Bi.k.getClass();
                a(new C0383Bi(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                C0383Bi c0383Bi2 = (C0383Bi) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    c0383Bi = c0383Bi2;
                    if (c0383Bi != null) {
                        a(c0383Bi);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (c0383Bi != null) {
                a(c0383Bi);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (C0357Ai) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC0877Uj.e;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        C0383Bi c0383BiI = i();
        if (c0383BiI.a()) {
            return c0383BiI;
        }
        throw H0.c(c0383BiI);
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC0877Uj.f.a(C0383Bi.class, C0357Ai.class);
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public final C0383Bi i() {
        int i;
        C0383Bi c0383Bi = new C0383Bi(this);
        int i2 = this.f;
        if ((i2 & 1) != 0) {
            c0383Bi.f = this.g;
            i = 1;
        } else {
            i = 0;
        }
        if ((i2 & 2) != 0) {
            c0383Bi.g = this.h;
            i |= 2;
        }
        if ((i2 & 4) != 0) {
            c0383Bi.h = this.i;
            i |= 4;
        }
        c0383Bi.e = i;
        o();
        return c0383Bi;
    }

    public C0357Ai(C0859Tr c0859Tr) {
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
        return C0383Bi.j;
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: a */
    public final H0 b(J0 j0) {
        if (j0 instanceof C0383Bi) {
            return a((C0383Bi) j0);
        }
        super.b(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (C0357Ai) c(c2712tk0);
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        C2123ms.a(n(), c1856jk).a(this, obj);
        return this;
    }
}
