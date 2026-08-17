package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Pj, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0747Pj extends AbstractC0911Vr {
    public int f;
    public List g;
    public C2401q50 h;
    public Object i;
    public long j;
    public long k;
    public double l;
    public U7 m;
    public Object n;

    public C0747Pj() {
        super(null);
        this.g = Collections.EMPTY_LIST;
        this.i = XmlPullParser.NO_NAMESPACE;
        this.m = U7.c;
        this.n = XmlPullParser.NO_NAMESPACE;
    }

    public final C0747Pj a(C0851Tj c0851Tj) {
        if (c0851Tj == C0851Tj.n) {
            return this;
        }
        if (this.h == null) {
            if (!c0851Tj.f.isEmpty()) {
                if (this.g.isEmpty()) {
                    this.g = c0851Tj.f;
                    this.f &= -2;
                } else {
                    if ((this.f & 1) == 0) {
                        this.g = new ArrayList(this.g);
                        this.f |= 1;
                    }
                    this.g.addAll(c0851Tj.f);
                }
                p();
            }
        } else if (!c0851Tj.f.isEmpty()) {
            boolean zIsEmpty = this.h.b.isEmpty();
            C2401q50 c2401q50 = this.h;
            if (zIsEmpty) {
                c2401q50.a = null;
                this.g = c0851Tj.f;
                this.f &= -2;
                this.h = null;
            } else {
                c2401q50.a(c0851Tj.f);
            }
        }
        if ((c0851Tj.e & 1) != 0) {
            this.f |= 2;
            this.i = c0851Tj.g;
            p();
        }
        if ((c0851Tj.e & 2) != 0) {
            long j = c0851Tj.h;
            this.f |= 4;
            this.j = j;
            p();
        }
        if ((c0851Tj.e & 4) != 0) {
            long j2 = c0851Tj.i;
            this.f |= 8;
            this.k = j2;
            p();
        }
        if ((c0851Tj.e & 8) != 0) {
            double d = c0851Tj.j;
            this.f |= 16;
            this.l = d;
            p();
        }
        if ((c0851Tj.e & 16) != 0) {
            U7 u7 = c0851Tj.k;
            u7.getClass();
            this.f |= 32;
            this.m = u7;
            p();
        }
        if ((c0851Tj.e & 32) != 0) {
            this.f |= 64;
            this.n = c0851Tj.l;
            p();
        }
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof C0851Tj) {
            return a((C0851Tj) j0);
        }
        super.b(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        C0851Tj c0851TjI = i();
        if (c0851TjI.a()) {
            return c0851TjI;
        }
        throw H0.c(c0851TjI);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001d  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final C0747Pj a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        C0851Tj c0851Tj = null;
        try {
            try {
                a((C0851Tj) C0851Tj.o.a(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                C0851Tj c0851Tj2 = (C0851Tj) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    c0851Tj = c0851Tj2;
                    if (c0851Tj != null) {
                        a(c0851Tj);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (c0851Tj != null) {
                a(c0851Tj);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (C0747Pj) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC0877Uj.O;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        C0851Tj c0851TjI = i();
        if (c0851TjI.a()) {
            return c0851TjI;
        }
        throw H0.c(c0851TjI);
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC0877Uj.P.a(C0851Tj.class, C0747Pj.class);
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public final C0851Tj i() {
        C0851Tj c0851Tj = new C0851Tj(this);
        int i = this.f;
        C2401q50 c2401q50 = this.h;
        if (c2401q50 == null) {
            if ((i & 1) != 0) {
                this.g = Collections.unmodifiableList(this.g);
                this.f &= -2;
            }
            c0851Tj.f = this.g;
        } else {
            c0851Tj.f = c2401q50.b();
        }
        int i2 = (i & 2) != 0 ? 1 : 0;
        c0851Tj.g = this.i;
        if ((i & 4) != 0) {
            c0851Tj.h = this.j;
            i2 |= 2;
        }
        if ((i & 8) != 0) {
            c0851Tj.i = this.k;
            i2 |= 4;
        }
        if ((i & 16) != 0) {
            c0851Tj.j = this.l;
            i2 |= 8;
        }
        if ((i & 32) != 0) {
            i2 |= 16;
        }
        c0851Tj.k = this.m;
        if ((i & 64) != 0) {
            i2 |= 32;
        }
        c0851Tj.l = this.n;
        c0851Tj.e = i2;
        o();
        return c0851Tj;
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

    public C0747Pj(C0859Tr c0859Tr) {
        super(c0859Tr);
        this.g = Collections.EMPTY_LIST;
        this.i = XmlPullParser.NO_NAMESPACE;
        this.m = U7.c;
        this.n = XmlPullParser.NO_NAMESPACE;
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return C0851Tj.n;
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: a */
    public final H0 b(J0 j0) {
        if (j0 instanceof C0851Tj) {
            return a((C0851Tj) j0);
        }
        super.b(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (C0747Pj) c(c2712tk0);
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        C2123ms.a(n(), c1856jk).a(this, obj);
        return this;
    }
}
