package com.android.tools.r8.internal;

import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class A60 extends AbstractC0911Vr {
    public C2062m70 f;
    public Object g;

    public A60() {
        super(null);
        this.g = XmlPullParser.NO_NAMESPACE;
        B60 b60 = B60.h;
    }

    public final A60 a(B60 b60) {
        String strC;
        if (b60 == B60.h) {
            return this;
        }
        if (b60.e != null) {
            C2062m70 c2062m70K = b60.k();
            C2062m70 c2062m70 = this.f;
            if (c2062m70 != null) {
                this.f = C2062m70.g.d().a(c2062m70).a(c2062m70K).i();
            } else {
                this.f = c2062m70K;
            }
            p();
        }
        Object obj = b60.f;
        if (obj instanceof String) {
            strC = (String) obj;
        } else {
            strC = ((U7) obj).c();
            b60.f = strC;
        }
        if (!strC.isEmpty()) {
            this.g = b60.f;
            p();
        }
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.j.a(B60.class, A60.class), c1856jk).b(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        B60 b60 = new B60(this);
        b60.e = this.f;
        b60.f = this.g;
        o();
        if (b60.a()) {
            return b60;
        }
        throw H0.c(b60);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001d  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final A60 a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        B60 b60 = null;
        try {
            try {
                a((B60) B60.i.a(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                B60 b61 = (B60) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    b60 = b61;
                    if (b60 != null) {
                        a(b60);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (b60 != null) {
                a(b60);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (A60) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC1468f90.i;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        B60 b60 = new B60(this);
        b60.e = this.f;
        b60.f = this.g;
        o();
        if (b60.a()) {
            return b60;
        }
        throw H0.c(b60);
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 i() {
        B60 b60 = new B60(this);
        b60.e = this.f;
        b60.f = this.g;
        o();
        return b60;
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC1468f90.j.a(B60.class, A60.class);
    }

    public A60(C0859Tr c0859Tr) {
        super(c0859Tr);
        this.g = XmlPullParser.NO_NAMESPACE;
        B60 b60 = B60.h;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof B60) {
            return a((B60) j0);
        }
        super.b(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return B60.h;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.j.a(B60.class, A60.class), c1856jk).a(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: a */
    public final H0 b(J0 j0) {
        if (j0 instanceof B60) {
            return a((B60) j0);
        }
        super.b(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (A60) c(c2712tk0);
    }
}
