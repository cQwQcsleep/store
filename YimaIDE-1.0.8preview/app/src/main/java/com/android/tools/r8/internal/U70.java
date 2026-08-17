package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class U70 extends AbstractC0911Vr {
    public P70 f;
    public int g;

    public U70() {
        super(null);
        V70 v70 = V70.h;
    }

    public final U70 a(V70 v70) {
        if (v70 == V70.h) {
            return this;
        }
        if (v70.e != null) {
            P70 p70K = v70.k();
            P70 p70 = this.f;
            if (p70 != null) {
                this.f = P70.h.d().a(p70).a(p70K).i();
            } else {
                this.f = p70K;
            }
            p();
        }
        int i = v70.f;
        if (i != 0) {
            this.g = i;
            p();
        }
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.D.a(V70.class, U70.class), c1856jk).b(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        V70 v70 = new V70(this);
        v70.e = this.f;
        v70.f = this.g;
        o();
        if (v70.a()) {
            return v70;
        }
        throw H0.c(v70);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001f  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final U70 a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        V70 v70 = null;
        try {
            try {
                V70.i.getClass();
                a(new V70(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                V70 v71 = (V70) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    v70 = v71;
                    if (v70 != null) {
                        a(v70);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (v70 != null) {
                a(v70);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (U70) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC1468f90.C;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        V70 v70 = new V70(this);
        v70.e = this.f;
        v70.f = this.g;
        o();
        if (v70.a()) {
            return v70;
        }
        throw H0.c(v70);
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 i() {
        V70 v70 = new V70(this);
        v70.e = this.f;
        v70.f = this.g;
        o();
        return v70;
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC1468f90.D.a(V70.class, U70.class);
    }

    public U70(C0859Tr c0859Tr) {
        super(c0859Tr);
        V70 v70 = V70.h;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof V70) {
            return a((V70) j0);
        }
        super.b(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return V70.h;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.D.a(V70.class, U70.class), c1856jk).a(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: a */
    public final H0 b(J0 j0) {
        if (j0 instanceof V70) {
            return a((V70) j0);
        }
        super.b(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (U70) c(c2712tk0);
    }
}
