package com.android.tools.r8.internal;

import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Qi, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0772Qi extends AbstractC0911Vr {
    public int f;
    public Object g;
    public int h;
    public C0876Ui i;

    public C0772Qi() {
        super(null);
        this.g = XmlPullParser.NO_NAMESPACE;
    }

    public final C0772Qi a(C0798Ri c0798Ri) {
        C0876Ui c0876Ui;
        C0876Ui c0876Ui2;
        if (c0798Ri == C0798Ri.j) {
            return this;
        }
        if ((c0798Ri.e & 1) != 0) {
            this.f |= 1;
            this.g = c0798Ri.f;
            p();
        }
        if ((c0798Ri.e & 2) != 0) {
            int i = c0798Ri.g;
            this.f |= 2;
            this.h = i;
            p();
        }
        if (c0798Ri.m()) {
            C0876Ui c0876UiL = c0798Ri.l();
            if ((this.f & 4) == 0 || (c0876Ui = this.i) == null || c0876Ui == (c0876Ui2 = C0876Ui.j)) {
                this.i = c0876UiL;
            } else {
                this.i = c0876Ui2.d().a(c0876Ui).a(c0876UiL).i();
            }
            p();
            this.f |= 4;
        }
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof C0798Ri) {
            return a((C0798Ri) j0);
        }
        super.b(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        C0798Ri c0798RiI = i();
        if (c0798RiI.a()) {
            return c0798RiI;
        }
        throw H0.c(c0798RiI);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001d  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final C0772Qi a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        C0798Ri c0798Ri = null;
        try {
            try {
                a((C0798Ri) C0798Ri.k.a(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                C0798Ri c0798Ri2 = (C0798Ri) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    c0798Ri = c0798Ri2;
                    if (c0798Ri != null) {
                        a(c0798Ri);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (c0798Ri != null) {
                a(c0798Ri);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (C0772Qi) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC0877Uj.s;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        C0798Ri c0798RiI = i();
        if (c0798RiI.a()) {
            return c0798RiI;
        }
        throw H0.c(c0798RiI);
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC0877Uj.t.a(C0798Ri.class, C0772Qi.class);
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public final C0798Ri i() {
        C0798Ri c0798Ri = new C0798Ri(this);
        int i = this.f;
        int i2 = (i & 1) != 0 ? 1 : 0;
        c0798Ri.f = this.g;
        if ((i & 2) != 0) {
            c0798Ri.g = this.h;
            i2 |= 2;
        }
        if ((i & 4) != 0) {
            c0798Ri.h = this.i;
            i2 |= 4;
        }
        c0798Ri.e = i2;
        o();
        return c0798Ri;
    }

    public C0772Qi(C0859Tr c0859Tr) {
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
        return C0798Ri.j;
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: a */
    public final H0 b(J0 j0) {
        if (j0 instanceof C0798Ri) {
            return a((C0798Ri) j0);
        }
        super.b(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (C0772Qi) c(c2712tk0);
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        C2123ms.a(n(), c1856jk).a(this, obj);
        return this;
    }
}
