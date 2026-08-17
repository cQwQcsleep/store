package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Di, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0435Di extends AbstractC0911Vr {
    public int f;
    public int g;
    public int h;

    public C0435Di() {
        super(null);
    }

    public final C0435Di a(C0461Ei c0461Ei) {
        if (c0461Ei == C0461Ei.i) {
            return this;
        }
        if ((c0461Ei.e & 1) != 0) {
            int i = c0461Ei.f;
            this.f |= 1;
            this.g = i;
            p();
        }
        if ((c0461Ei.e & 2) != 0) {
            int i2 = c0461Ei.g;
            this.f |= 2;
            this.h = i2;
            p();
        }
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof C0461Ei) {
            return a((C0461Ei) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        C0461Ei c0461EiI = i();
        if (c0461EiI.a()) {
            return c0461EiI;
        }
        throw H0.c(c0461EiI);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001f  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final C0435Di a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        C0461Ei c0461Ei = null;
        try {
            try {
                C0461Ei.j.getClass();
                a(new C0461Ei(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                C0461Ei c0461Ei2 = (C0461Ei) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    c0461Ei = c0461Ei2;
                    if (c0461Ei != null) {
                        a(c0461Ei);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (c0461Ei != null) {
                a(c0461Ei);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (C0435Di) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC0877Uj.g;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        C0461Ei c0461EiI = i();
        if (c0461EiI.a()) {
            return c0461EiI;
        }
        throw H0.c(c0461EiI);
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC0877Uj.h.a(C0461Ei.class, C0435Di.class);
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public final C0461Ei i() {
        int i;
        C0461Ei c0461Ei = new C0461Ei(this);
        int i2 = this.f;
        if ((i2 & 1) != 0) {
            c0461Ei.f = this.g;
            i = 1;
        } else {
            i = 0;
        }
        if ((i2 & 2) != 0) {
            c0461Ei.g = this.h;
            i |= 2;
        }
        c0461Ei.e = i;
        o();
        return c0461Ei;
    }

    public C0435Di(C0859Tr c0859Tr) {
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
        return C0461Ei.i;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(J0 j0) {
        if (j0 instanceof C0461Ei) {
            return a((C0461Ei) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (C0435Di) c(c2712tk0);
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        C2123ms.a(n(), c1856jk).a(this, obj);
        return this;
    }
}
