package com.android.tools.r8.internal;

import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class J60 extends AbstractC0911Vr {
    public Object f;
    public int g;

    public J60() {
        super(null);
        this.f = XmlPullParser.NO_NAMESPACE;
        this.g = 0;
        L60 l60 = L60.h;
    }

    public final J60 a(L60 l60) {
        if (l60 == L60.h) {
            return this;
        }
        if (!l60.k().isEmpty()) {
            this.f = l60.e;
            p();
        }
        int i = l60.f;
        if (i != 0) {
            this.g = i;
            p();
        }
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.f0.a(L60.class, J60.class), c1856jk).b(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        L60 l60 = new L60(this);
        l60.e = this.f;
        l60.f = this.g;
        o();
        if (l60.a()) {
            return l60;
        }
        throw H0.c(l60);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001d  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final J60 a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        L60 l60 = null;
        try {
            try {
                a((L60) L60.i.a(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                L60 l61 = (L60) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    l60 = l61;
                    if (l60 != null) {
                        a(l60);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (l60 != null) {
                a(l60);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (J60) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC1468f90.e0;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        L60 l60 = new L60(this);
        l60.e = this.f;
        l60.f = this.g;
        o();
        if (l60.a()) {
            return l60;
        }
        throw H0.c(l60);
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 i() {
        L60 l60 = new L60(this);
        l60.e = this.f;
        l60.f = this.g;
        o();
        return l60;
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC1468f90.f0.a(L60.class, J60.class);
    }

    public J60(C0859Tr c0859Tr) {
        super(c0859Tr);
        this.f = XmlPullParser.NO_NAMESPACE;
        this.g = 0;
        L60 l60 = L60.h;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof L60) {
            return a((L60) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return L60.h;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.f0.a(L60.class, J60.class), c1856jk).a(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(J0 j0) {
        if (j0 instanceof L60) {
            return a((L60) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (J60) c(c2712tk0);
    }
}
