package com.android.tools.r8.internal;

import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class H70 extends AbstractC0911Vr {
    public int f;
    public int g;
    public Object h;
    public boolean i;
    public C2573s60 j;
    public int k;
    public boolean l;

    public H70() {
        super(null);
        this.f = 0;
        this.h = XmlPullParser.NO_NAMESPACE;
        J70 j70 = J70.m;
    }

    public final H70 a(J70 j70) {
        if (j70 == J70.m) {
            return this;
        }
        int i = j70.e;
        if (i != 0) {
            this.f = i;
            p();
        }
        int i2 = j70.f;
        if (i2 != 0) {
            this.g = i2;
            p();
        }
        if (!j70.m().isEmpty()) {
            this.h = j70.g;
            p();
        }
        boolean z = j70.h;
        if (z) {
            this.i = z;
            p();
        }
        if (j70.i != null) {
            C2573s60 c2573s60L = j70.l();
            C2573s60 c2573s60 = this.j;
            if (c2573s60 != null) {
                C2487r60 c2487r60A = C2573s60.g.d().a(c2573s60).a(c2573s60L);
                C2573s60 c2573s61 = new C2573s60(c2487r60A);
                c2573s61.e = c2487r60A.f;
                c2487r60A.o();
                this.j = c2573s61;
            } else {
                this.j = c2573s60L;
            }
            p();
        }
        int i3 = j70.j;
        if (i3 != 0) {
            this.k = i3;
            p();
        }
        boolean z2 = j70.k;
        if (z2) {
            this.l = z2;
            p();
        }
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.T.a(J70.class, H70.class), c1856jk).b(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        J70 j70I = i();
        if (j70I.a()) {
            return j70I;
        }
        throw H0.c(j70I);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001d  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final H70 a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        J70 j70 = null;
        try {
            try {
                a((J70) J70.n.a(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                J70 j71 = (J70) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    j70 = j71;
                    if (j70 != null) {
                        a(j70);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (j70 != null) {
                a(j70);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (H70) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC1468f90.S;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        J70 j70I = i();
        if (j70I.a()) {
            return j70I;
        }
        throw H0.c(j70I);
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC1468f90.T.a(J70.class, H70.class);
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public final J70 i() {
        J70 j70 = new J70(this);
        j70.e = this.f;
        j70.f = this.g;
        j70.g = this.h;
        j70.h = this.i;
        j70.i = this.j;
        j70.j = this.k;
        j70.k = this.l;
        o();
        return j70;
    }

    public H70(C0859Tr c0859Tr) {
        super(c0859Tr);
        this.f = 0;
        this.h = XmlPullParser.NO_NAMESPACE;
        J70 j70 = J70.m;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof J70) {
            return a((J70) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return J70.m;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.T.a(J70.class, H70.class), c1856jk).a(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(J0 j0) {
        if (j0 instanceof J70) {
            return a((J70) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (H70) c(c2712tk0);
    }
}
