package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class H80 extends AbstractC0911Vr {
    public int f;

    public H80() {
        super(null);
        I80 i80 = I80.g;
    }

    public final H80 a(I80 i80) {
        if (i80 == I80.g) {
            return this;
        }
        int i = i80.e;
        if (i != 0) {
            this.f = i;
            p();
        }
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.r.a(I80.class, H80.class), c1856jk).b(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        I80 i80 = new I80(this);
        i80.e = this.f;
        o();
        if (i80.a()) {
            return i80;
        }
        throw H0.c(i80);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001f  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final H80 a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        I80 i80 = null;
        try {
            try {
                I80.h.getClass();
                a(new I80(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                I80 i81 = (I80) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    i80 = i81;
                    if (i80 != null) {
                        a(i80);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (i80 != null) {
                a(i80);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (H80) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC1468f90.q;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        I80 i80 = new I80(this);
        i80.e = this.f;
        o();
        if (i80.a()) {
            return i80;
        }
        throw H0.c(i80);
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 i() {
        I80 i80 = new I80(this);
        i80.e = this.f;
        o();
        return i80;
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC1468f90.r.a(I80.class, H80.class);
    }

    public H80(C0859Tr c0859Tr) {
        super(c0859Tr);
        I80 i80 = I80.g;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof I80) {
            return a((I80) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return I80.g;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.r.a(I80.class, H80.class), c1856jk).a(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(J0 j0) {
        if (j0 instanceof I80) {
            return a((I80) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (H80) c(c2712tk0);
    }
}
