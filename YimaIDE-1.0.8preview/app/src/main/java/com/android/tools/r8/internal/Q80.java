package com.android.tools.r8.internal;

import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Q80 extends AbstractC0911Vr {
    public int f;
    public P70 g;
    public Object h;
    public boolean i;

    public Q80() {
        super(null);
        this.f = 0;
        this.h = XmlPullParser.NO_NAMESPACE;
        S80 s80 = S80.j;
    }

    public final Q80 a(S80 s80) {
        String strC;
        if (s80 == S80.j) {
            return this;
        }
        int i = s80.e;
        if (i != 0) {
            this.f = i;
            p();
        }
        if (s80.f != null) {
            P70 p70K = s80.k();
            P70 p70 = this.g;
            if (p70 != null) {
                this.g = P70.h.d().a(p70).a(p70K).i();
            } else {
                this.g = p70K;
            }
            p();
        }
        Object obj = s80.g;
        if (obj instanceof String) {
            strC = (String) obj;
        } else {
            strC = ((U7) obj).c();
            s80.g = strC;
        }
        if (!strC.isEmpty()) {
            this.h = s80.g;
            p();
        }
        boolean z = s80.h;
        if (z) {
            this.i = z;
            p();
        }
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.v.a(S80.class, Q80.class), c1856jk).b(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        S80 s80I = i();
        if (s80I.a()) {
            return s80I;
        }
        throw H0.c(s80I);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001d  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final Q80 a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        S80 s80 = null;
        try {
            try {
                a((S80) S80.k.a(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                S80 s81 = (S80) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    s80 = s81;
                    if (s80 != null) {
                        a(s80);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (s80 != null) {
                a(s80);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (Q80) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC1468f90.u;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        S80 s80I = i();
        if (s80I.a()) {
            return s80I;
        }
        throw H0.c(s80I);
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC1468f90.v.a(S80.class, Q80.class);
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public final S80 i() {
        S80 s80 = new S80(this);
        s80.e = this.f;
        s80.f = this.g;
        s80.g = this.h;
        s80.h = this.i;
        o();
        return s80;
    }

    public Q80(C0859Tr c0859Tr) {
        super(c0859Tr);
        this.f = 0;
        this.h = XmlPullParser.NO_NAMESPACE;
        S80 s80 = S80.j;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof S80) {
            return a((S80) j0);
        }
        super.b(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return S80.j;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.v.a(S80.class, Q80.class), c1856jk).a(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: a */
    public final H0 b(J0 j0) {
        if (j0 instanceof S80) {
            return a((S80) j0);
        }
        super.b(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (Q80) c(c2712tk0);
    }
}
