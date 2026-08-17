package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class G60 extends AbstractC0911Vr {
    public int f;

    public G60() {
        super(null);
        H60 h60 = H60.g;
    }

    public final G60 a(H60 h60) {
        if (h60 == H60.g) {
            return this;
        }
        int i = h60.e;
        if (i != 0) {
            this.f = i;
            p();
        }
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.F.a(H60.class, G60.class), c1856jk).b(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        H60 h60 = new H60(this);
        h60.e = this.f;
        o();
        if (h60.a()) {
            return h60;
        }
        throw H0.c(h60);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001f  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final G60 a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        H60 h60 = null;
        try {
            try {
                H60.h.getClass();
                a(new H60(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                H60 h61 = (H60) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    h60 = h61;
                    if (h60 != null) {
                        a(h60);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (h60 != null) {
                a(h60);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (G60) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC1468f90.E;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        H60 h60 = new H60(this);
        h60.e = this.f;
        o();
        if (h60.a()) {
            return h60;
        }
        throw H0.c(h60);
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 i() {
        H60 h60 = new H60(this);
        h60.e = this.f;
        o();
        return h60;
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC1468f90.F.a(H60.class, G60.class);
    }

    public G60(C0859Tr c0859Tr) {
        super(c0859Tr);
        H60 h60 = H60.g;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof H60) {
            return a((H60) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return H60.g;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.F.a(H60.class, G60.class), c1856jk).a(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(J0 j0) {
        if (j0 instanceof H60) {
            return a((H60) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (G60) c(c2712tk0);
    }
}
