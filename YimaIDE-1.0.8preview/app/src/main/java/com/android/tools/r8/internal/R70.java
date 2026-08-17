package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class R70 extends AbstractC0911Vr {
    public int f;
    public int g;

    public R70() {
        super(null);
        S70 s70 = S70.h;
    }

    public final R70 a(S70 s70) {
        if (s70 == S70.h) {
            return this;
        }
        int i = s70.e;
        if (i != 0) {
            this.f = i;
            p();
        }
        int i2 = s70.f;
        if (i2 != 0) {
            this.g = i2;
            p();
        }
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.d.a(S70.class, R70.class), c1856jk).b(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        S70 s70I = i();
        if (s70I.a()) {
            return s70I;
        }
        throw H0.c(s70I);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001f  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final R70 a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        S70 s70 = null;
        try {
            try {
                S70.i.getClass();
                a(new S70(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                S70 s71 = (S70) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    s70 = s71;
                    if (s70 != null) {
                        a(s70);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (s70 != null) {
                a(s70);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (R70) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC1468f90.c;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        S70 s70I = i();
        if (s70I.a()) {
            return s70I;
        }
        throw H0.c(s70I);
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC1468f90.d.a(S70.class, R70.class);
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public final S70 i() {
        S70 s70 = new S70(this);
        s70.e = this.f;
        s70.f = this.g;
        o();
        return s70;
    }

    public R70(C0859Tr c0859Tr) {
        super(c0859Tr);
        S70 s70 = S70.h;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof S70) {
            return a((S70) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return S70.h;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.d.a(S70.class, R70.class), c1856jk).a(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(J0 j0) {
        if (j0 instanceof S70) {
            return a((S70) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (R70) c(c2712tk0);
    }
}
