package com.android.tools.r8.internal;

import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.b60, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1124b60 extends AbstractC0911Vr {
    public P70 f;
    public Object g;

    public C1124b60() {
        super(null);
        this.g = XmlPullParser.NO_NAMESPACE;
        C1208c60 c1208c60 = C1208c60.h;
    }

    public final C1124b60 a(C1208c60 c1208c60) {
        String strC;
        if (c1208c60 == C1208c60.h) {
            return this;
        }
        if (c1208c60.e != null) {
            P70 p70K = c1208c60.k();
            P70 p70 = this.f;
            if (p70 != null) {
                this.f = P70.h.d().a(p70).a(p70K).i();
            } else {
                this.f = p70K;
            }
            p();
        }
        Object obj = c1208c60.f;
        if (obj instanceof String) {
            strC = (String) obj;
        } else {
            strC = ((U7) obj).c();
            c1208c60.f = strC;
        }
        if (!strC.isEmpty()) {
            this.g = c1208c60.f;
            p();
        }
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.x.a(C1208c60.class, C1124b60.class), c1856jk).b(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        C1208c60 c1208c60 = new C1208c60(this);
        c1208c60.e = this.f;
        c1208c60.f = this.g;
        o();
        if (c1208c60.a()) {
            return c1208c60;
        }
        throw H0.c(c1208c60);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001d  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final C1124b60 a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        C1208c60 c1208c60 = null;
        try {
            try {
                a((C1208c60) C1208c60.i.a(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                C1208c60 c1208c61 = (C1208c60) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    c1208c60 = c1208c61;
                    if (c1208c60 != null) {
                        a(c1208c60);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (c1208c60 != null) {
                a(c1208c60);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (C1124b60) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC1468f90.w;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        C1208c60 c1208c60 = new C1208c60(this);
        c1208c60.e = this.f;
        c1208c60.f = this.g;
        o();
        if (c1208c60.a()) {
            return c1208c60;
        }
        throw H0.c(c1208c60);
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 i() {
        C1208c60 c1208c60 = new C1208c60(this);
        c1208c60.e = this.f;
        c1208c60.f = this.g;
        o();
        return c1208c60;
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC1468f90.x.a(C1208c60.class, C1124b60.class);
    }

    public C1124b60(C0859Tr c0859Tr) {
        super(c0859Tr);
        this.g = XmlPullParser.NO_NAMESPACE;
        C1208c60 c1208c60 = C1208c60.h;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof C1208c60) {
            return a((C1208c60) j0);
        }
        super.b(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return C1208c60.h;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.x.a(C1208c60.class, C1124b60.class), c1856jk).a(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: a */
    public final H0 b(J0 j0) {
        if (j0 instanceof C1208c60) {
            return a((C1208c60) j0);
        }
        super.b(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (C1124b60) c(c2712tk0);
    }
}
