package com.android.tools.r8.internal;

import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Rj, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0799Rj extends AbstractC0911Vr {
    public int f;
    public Object g;
    public boolean h;

    public C0799Rj() {
        super(null);
        this.g = XmlPullParser.NO_NAMESPACE;
    }

    public final C0799Rj a(C0825Sj c0825Sj) {
        if (c0825Sj == C0825Sj.i) {
            return this;
        }
        if (c0825Sj.l()) {
            this.f |= 1;
            this.g = c0825Sj.f;
            p();
        }
        if (c0825Sj.k()) {
            boolean z = c0825Sj.g;
            this.f |= 2;
            this.h = z;
            p();
        }
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof C0825Sj) {
            return a((C0825Sj) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        C0825Sj c0825SjI = i();
        if (c0825SjI.a()) {
            return c0825SjI;
        }
        throw H0.c(c0825SjI);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001d  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final C0799Rj a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        C0825Sj c0825Sj = null;
        try {
            try {
                a((C0825Sj) C0825Sj.j.a(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                C0825Sj c0825Sj2 = (C0825Sj) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    c0825Sj = c0825Sj2;
                    if (c0825Sj != null) {
                        a(c0825Sj);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (c0825Sj != null) {
                a(c0825Sj);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (C0799Rj) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC0877Uj.Q;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        C0825Sj c0825SjI = i();
        if (c0825SjI.a()) {
            return c0825SjI;
        }
        throw H0.c(c0825SjI);
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC0877Uj.R.a(C0825Sj.class, C0799Rj.class);
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public final C0825Sj i() {
        C0825Sj c0825Sj = new C0825Sj(this);
        int i = this.f;
        int i2 = (i & 1) != 0 ? 1 : 0;
        c0825Sj.f = this.g;
        if ((i & 2) != 0) {
            c0825Sj.g = this.h;
            i2 |= 2;
        }
        c0825Sj.e = i2;
        o();
        return c0825Sj;
    }

    public C0799Rj(C0859Tr c0859Tr) {
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
        return C0825Sj.i;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(J0 j0) {
        if (j0 instanceof C0825Sj) {
            return a((C0825Sj) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (C0799Rj) c(c2712tk0);
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        C2123ms.a(n(), c1856jk).a(this, obj);
        return this;
    }
}
