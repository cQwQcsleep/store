package com.android.tools.r8.internal;

import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class X60 extends AbstractC0911Vr {
    public Object f;
    public Object g;
    public boolean h;

    public X60() {
        super(null);
        this.f = XmlPullParser.NO_NAMESPACE;
        this.g = XmlPullParser.NO_NAMESPACE;
        Y60 y60 = Y60.i;
    }

    public final X60 a(Y60 y60) {
        String strC;
        String strC2;
        if (y60 == Y60.i) {
            return this;
        }
        Object obj = y60.e;
        if (obj instanceof String) {
            strC = (String) obj;
        } else {
            strC = ((U7) obj).c();
            y60.e = strC;
        }
        if (!strC.isEmpty()) {
            this.f = y60.e;
            p();
        }
        Object obj2 = y60.f;
        if (obj2 instanceof String) {
            strC2 = (String) obj2;
        } else {
            strC2 = ((U7) obj2).c();
            y60.f = strC2;
        }
        if (!strC2.isEmpty()) {
            this.g = y60.f;
            p();
        }
        boolean z = y60.g;
        if (z) {
            this.h = z;
            p();
        }
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.R0.a(Y60.class, X60.class), c1856jk).b(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        Y60 y60 = new Y60(this);
        y60.e = this.f;
        y60.f = this.g;
        y60.g = this.h;
        o();
        if (y60.a()) {
            return y60;
        }
        throw H0.c(y60);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001d  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final X60 a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        Y60 y60 = null;
        try {
            try {
                a((Y60) Y60.j.a(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                Y60 y61 = (Y60) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    y60 = y61;
                    if (y60 != null) {
                        a(y60);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (y60 != null) {
                a(y60);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (X60) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC1468f90.Q0;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        Y60 y60 = new Y60(this);
        y60.e = this.f;
        y60.f = this.g;
        y60.g = this.h;
        o();
        if (y60.a()) {
            return y60;
        }
        throw H0.c(y60);
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 i() {
        Y60 y60 = new Y60(this);
        y60.e = this.f;
        y60.f = this.g;
        y60.g = this.h;
        o();
        return y60;
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC1468f90.R0.a(Y60.class, X60.class);
    }

    public X60(C0859Tr c0859Tr) {
        super(c0859Tr);
        this.f = XmlPullParser.NO_NAMESPACE;
        this.g = XmlPullParser.NO_NAMESPACE;
        Y60 y60 = Y60.i;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof Y60) {
            return a((Y60) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return Y60.i;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.R0.a(Y60.class, X60.class), c1856jk).a(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(J0 j0) {
        if (j0 instanceof Y60) {
            return a((Y60) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (X60) c(c2712tk0);
    }
}
