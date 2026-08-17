package com.android.tools.r8.internal;

import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.xj, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3051xj extends AbstractC0911Vr {
    public int f;
    public Object g;
    public C0384Bj h;

    public C3051xj() {
        super(null);
        this.g = XmlPullParser.NO_NAMESPACE;
    }

    public final C3051xj a(C3135yj c3135yj) {
        C0384Bj c0384Bj;
        C0384Bj c0384Bj2;
        if (c3135yj == C3135yj.i) {
            return this;
        }
        if ((c3135yj.e & 1) != 0) {
            this.f |= 1;
            this.g = c3135yj.f;
            p();
        }
        if (c3135yj.m()) {
            C0384Bj c0384BjL = c3135yj.l();
            if ((this.f & 2) == 0 || (c0384Bj = this.h) == null || c0384Bj == (c0384Bj2 = C0384Bj.h)) {
                this.h = c0384BjL;
            } else {
                this.h = c0384Bj2.d().a(c0384Bj).a(c0384BjL).i();
            }
            p();
            this.f |= 2;
        }
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof C3135yj) {
            return a((C3135yj) j0);
        }
        super.b(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        C3135yj c3135yjI = i();
        if (c3135yjI.a()) {
            return c3135yjI;
        }
        throw H0.c(c3135yjI);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001d  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final C3051xj a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        C3135yj c3135yj = null;
        try {
            try {
                a((C3135yj) C3135yj.j.a(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                C3135yj c3135yj2 = (C3135yj) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    c3135yj = c3135yj2;
                    if (c3135yj != null) {
                        a(c3135yj);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (c3135yj != null) {
                a(c3135yj);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (C3051xj) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC0877Uj.m;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        C3135yj c3135yjI = i();
        if (c3135yjI.a()) {
            return c3135yjI;
        }
        throw H0.c(c3135yjI);
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC0877Uj.n.a(C3135yj.class, C3051xj.class);
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public final C3135yj i() {
        C3135yj c3135yj = new C3135yj(this);
        int i = this.f;
        int i2 = (i & 1) != 0 ? 1 : 0;
        c3135yj.f = this.g;
        if ((i & 2) != 0) {
            c3135yj.g = this.h;
            i2 |= 2;
        }
        c3135yj.e = i2;
        o();
        return c3135yj;
    }

    public C3051xj(C0859Tr c0859Tr) {
        super(c0859Tr);
        this.g = XmlPullParser.NO_NAMESPACE;
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
        return C3135yj.i;
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: a */
    public final H0 b(J0 j0) {
        if (j0 instanceof C3135yj) {
            return a((C3135yj) j0);
        }
        super.b(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (C3051xj) c(c2712tk0);
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        C2123ms.a(n(), c1856jk).a(this, obj);
        return this;
    }
}
