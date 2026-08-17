package com.android.tools.r8.internal;

import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.rj, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2538rj extends AbstractC0911Vr {
    public int f;
    public Object g;
    public Object h;
    public Object i;
    public C2880vj j;
    public boolean k;
    public boolean l;

    public C2538rj() {
        super(null);
        this.g = XmlPullParser.NO_NAMESPACE;
        this.h = XmlPullParser.NO_NAMESPACE;
        this.i = XmlPullParser.NO_NAMESPACE;
    }

    public final C2538rj a(C2623sj c2623sj) {
        C2880vj c2880vj;
        C2880vj c2880vj2;
        if (c2623sj == C2623sj.m) {
            return this;
        }
        if ((c2623sj.e & 1) != 0) {
            this.f |= 1;
            this.g = c2623sj.f;
            p();
        }
        if ((c2623sj.e & 2) != 0) {
            this.f |= 2;
            this.h = c2623sj.g;
            p();
        }
        if ((c2623sj.e & 4) != 0) {
            this.f |= 4;
            this.i = c2623sj.h;
            p();
        }
        if (c2623sj.o()) {
            C2880vj c2880vjM = c2623sj.m();
            if ((this.f & 8) == 0 || (c2880vj = this.j) == null || c2880vj == (c2880vj2 = C2880vj.k)) {
                this.j = c2880vjM;
            } else {
                this.j = c2880vj2.d().a(c2880vj).a(c2880vjM).i();
            }
            p();
            this.f |= 8;
        }
        if ((c2623sj.e & 16) != 0) {
            boolean z = c2623sj.j;
            this.f |= 16;
            this.k = z;
            p();
        }
        if ((c2623sj.e & 32) != 0) {
            boolean z2 = c2623sj.k;
            this.f |= 32;
            this.l = z2;
            p();
        }
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof C2623sj) {
            return a((C2623sj) j0);
        }
        super.b(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        C2623sj c2623sjI = i();
        if (c2623sjI.a()) {
            return c2623sjI;
        }
        throw H0.c(c2623sjI);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001d  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final C2538rj a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        C2623sj c2623sj = null;
        try {
            try {
                a((C2623sj) C2623sj.n.a(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                C2623sj c2623sj2 = (C2623sj) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    c2623sj = c2623sj2;
                    if (c2623sj != null) {
                        a(c2623sj);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (c2623sj != null) {
                a(c2623sj);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (C2538rj) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC0877Uj.w;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        C2623sj c2623sjI = i();
        if (c2623sjI.a()) {
            return c2623sjI;
        }
        throw H0.c(c2623sjI);
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC0877Uj.x.a(C2623sj.class, C2538rj.class);
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public final C2623sj i() {
        C2623sj c2623sj = new C2623sj(this);
        int i = this.f;
        int i2 = (i & 1) != 0 ? 1 : 0;
        c2623sj.f = this.g;
        if ((i & 2) != 0) {
            i2 |= 2;
        }
        c2623sj.g = this.h;
        if ((i & 4) != 0) {
            i2 |= 4;
        }
        c2623sj.h = this.i;
        if ((i & 8) != 0) {
            c2623sj.i = this.j;
            i2 |= 8;
        }
        if ((i & 16) != 0) {
            c2623sj.j = this.k;
            i2 |= 16;
        }
        if ((i & 32) != 0) {
            c2623sj.k = this.l;
            i2 |= 32;
        }
        c2623sj.e = i2;
        o();
        return c2623sj;
    }

    public C2538rj(C0859Tr c0859Tr) {
        super(c0859Tr);
        this.g = XmlPullParser.NO_NAMESPACE;
        this.h = XmlPullParser.NO_NAMESPACE;
        this.i = XmlPullParser.NO_NAMESPACE;
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
        return C2623sj.m;
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: a */
    public final H0 b(J0 j0) {
        if (j0 instanceof C2623sj) {
            return a((C2623sj) j0);
        }
        super.b(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (C2538rj) c(c2712tk0);
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        C2123ms.a(n(), c1856jk).a(this, obj);
        return this;
    }
}
