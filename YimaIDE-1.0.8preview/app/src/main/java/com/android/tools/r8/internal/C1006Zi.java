package com.android.tools.r8.internal;

import com.sun.jna.platform.linux.Fcntl;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Zi, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1006Zi extends AbstractC0911Vr {
    public int f;
    public Object g;
    public int h;
    public int i;
    public int j;
    public Object k;
    public Object l;
    public Object m;
    public int n;
    public Object o;
    public C1513fj p;
    public boolean q;

    public C1006Zi() {
        super(null);
        this.g = XmlPullParser.NO_NAMESPACE;
        this.i = 1;
        this.j = 1;
        this.k = XmlPullParser.NO_NAMESPACE;
        this.l = XmlPullParser.NO_NAMESPACE;
        this.m = XmlPullParser.NO_NAMESPACE;
        this.o = XmlPullParser.NO_NAMESPACE;
    }

    public final C1006Zi a(C1258cj c1258cj) {
        C1513fj c1513fj;
        C1513fj c1513fj2;
        char c;
        if (c1258cj == C1258cj.r) {
            return this;
        }
        if ((c1258cj.e & 1) != 0) {
            this.f |= 1;
            this.g = c1258cj.f;
            p();
        }
        int i = 2;
        if ((c1258cj.e & 2) != 0) {
            int i2 = c1258cj.g;
            this.f |= 2;
            this.h = i2;
            p();
        }
        if ((c1258cj.e & 4) != 0) {
            int i3 = c1258cj.h;
            if (i3 == 1) {
                c = 1;
            } else if (i3 != 2) {
                c = i3 != 3 ? (char) 0 : (char) 3;
            } else {
                c = 2;
            }
            if (c == 0) {
                c = 1;
            }
            this.f |= 4;
            if (c == 1) {
                i = 1;
            } else if (c != 2) {
                if (c != 3) {
                    throw null;
                }
                i = 3;
            }
            this.i = i;
            p();
        }
        if (c1258cj.t()) {
            int iA = AbstractC1175bj.a(c1258cj.i);
            int i4 = iA != 0 ? iA : 1;
            this.f |= 8;
            this.j = AbstractC1175bj.b(i4);
            p();
        }
        if (c1258cj.u()) {
            this.f |= 16;
            this.k = c1258cj.j;
            p();
        }
        if (c1258cj.q()) {
            this.f |= 32;
            this.l = c1258cj.k;
            p();
        }
        if (c1258cj.p()) {
            this.f |= 64;
            this.m = c1258cj.l;
            p();
        }
        if (c1258cj.r()) {
            int i5 = c1258cj.m;
            this.f |= 128;
            this.n = i5;
            p();
        }
        if ((c1258cj.e & Fcntl.S_IRUSR) != 0) {
            this.f |= Fcntl.S_IRUSR;
            this.o = c1258cj.n;
            p();
        }
        if (c1258cj.s()) {
            C1513fj c1513fjN = c1258cj.n();
            if ((this.f & 512) == 0 || (c1513fj = this.p) == null || c1513fj == (c1513fj2 = C1513fj.o)) {
                this.p = c1513fjN;
            } else {
                this.p = c1513fj2.d().a(c1513fj).a(c1513fjN).i();
            }
            p();
            this.f |= 512;
        }
        if ((c1258cj.e & Fcntl.S_ISGID) != 0) {
            boolean z = c1258cj.p;
            this.f |= Fcntl.S_ISGID;
            this.q = z;
            p();
        }
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof C1258cj) {
            return a((C1258cj) j0);
        }
        super.b(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        C1258cj c1258cjI = i();
        if (c1258cjI.a()) {
            return c1258cjI;
        }
        throw H0.c(c1258cjI);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001d  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final C1006Zi a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        C1258cj c1258cj = null;
        try {
            try {
                a((C1258cj) C1258cj.s.a(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                C1258cj c1258cj2 = (C1258cj) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    c1258cj = c1258cj2;
                    if (c1258cj != null) {
                        a(c1258cj);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (c1258cj != null) {
                a(c1258cj);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (C1006Zi) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC0877Uj.k;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        C1258cj c1258cjI = i();
        if (c1258cjI.a()) {
            return c1258cjI;
        }
        throw H0.c(c1258cjI);
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC0877Uj.l.a(C1258cj.class, C1006Zi.class);
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public final C1258cj i() {
        C1258cj c1258cj = new C1258cj(this);
        int i = this.f;
        int i2 = (i & 1) != 0 ? 1 : 0;
        c1258cj.f = this.g;
        if ((i & 2) != 0) {
            c1258cj.g = this.h;
            i2 |= 2;
        }
        if ((i & 4) != 0) {
            i2 |= 4;
        }
        c1258cj.h = this.i;
        if ((i & 8) != 0) {
            i2 |= 8;
        }
        c1258cj.i = this.j;
        if ((i & 16) != 0) {
            i2 |= 16;
        }
        c1258cj.j = this.k;
        if ((i & 32) != 0) {
            i2 |= 32;
        }
        c1258cj.k = this.l;
        if ((i & 64) != 0) {
            i2 |= 64;
        }
        c1258cj.l = this.m;
        if ((i & 128) != 0) {
            c1258cj.m = this.n;
            i2 |= 128;
        }
        if ((i & Fcntl.S_IRUSR) != 0) {
            i2 |= Fcntl.S_IRUSR;
        }
        c1258cj.n = this.o;
        if ((i & 512) != 0) {
            c1258cj.o = this.p;
            i2 |= 512;
        }
        if ((i & Fcntl.S_ISGID) != 0) {
            c1258cj.p = this.q;
            i2 |= Fcntl.S_ISGID;
        }
        c1258cj.e = i2;
        o();
        return c1258cj;
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
        return C1258cj.r;
    }

    public C1006Zi(C0859Tr c0859Tr) {
        super(c0859Tr);
        this.g = XmlPullParser.NO_NAMESPACE;
        this.i = 1;
        this.j = 1;
        this.k = XmlPullParser.NO_NAMESPACE;
        this.l = XmlPullParser.NO_NAMESPACE;
        this.m = XmlPullParser.NO_NAMESPACE;
        this.o = XmlPullParser.NO_NAMESPACE;
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: a */
    public final H0 b(J0 j0) {
        if (j0 instanceof C1258cj) {
            return a((C1258cj) j0);
        }
        super.b(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (C1006Zi) c(c2712tk0);
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        C2123ms.a(n(), c1856jk).a(this, obj);
        return this;
    }
}
