package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class O70 extends AbstractC0911Vr {
    public int f;
    public S70 g;

    public O70() {
        super(null);
        P70 p70 = P70.h;
    }

    public final O70 a(P70 p70) {
        if (p70 == P70.h) {
            return this;
        }
        int i = p70.e;
        if (i != 0) {
            this.f = i;
            p();
        }
        if (p70.f != null) {
            S70 s70K = p70.k();
            S70 s70 = this.g;
            if (s70 != null) {
                this.g = S70.h.d().a(s70).a(s70K).i();
            } else {
                this.g = s70K;
            }
            p();
        }
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.f.a(P70.class, O70.class), c1856jk).b(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        P70 p70I = i();
        if (p70I.a()) {
            return p70I;
        }
        throw H0.c(p70I);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001f  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final O70 a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        P70 p70 = null;
        try {
            try {
                P70.i.getClass();
                a(new P70(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                P70 p71 = (P70) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    p70 = p71;
                    if (p70 != null) {
                        a(p70);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (p70 != null) {
                a(p70);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (O70) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC1468f90.e;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        P70 p70I = i();
        if (p70I.a()) {
            return p70I;
        }
        throw H0.c(p70I);
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC1468f90.f.a(P70.class, O70.class);
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public final P70 i() {
        P70 p70 = new P70(this);
        p70.e = this.f;
        p70.f = this.g;
        o();
        return p70;
    }

    public O70(C0859Tr c0859Tr) {
        super(c0859Tr);
        P70 p70 = P70.h;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof P70) {
            return a((P70) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return P70.h;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.f.a(P70.class, O70.class), c1856jk).a(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(J0 j0) {
        if (j0 instanceof P70) {
            return a((P70) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (O70) c(c2712tk0);
    }
}
