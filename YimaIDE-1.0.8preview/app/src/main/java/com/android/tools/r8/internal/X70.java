package com.android.tools.r8.internal;

import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class X70 extends AbstractC0911Vr {
    public Object f;

    public X70() {
        super(null);
        this.f = XmlPullParser.NO_NAMESPACE;
        Y70 y70 = Y70.g;
    }

    public final X70 a(Y70 y70) {
        String strC;
        if (y70 == Y70.g) {
            return this;
        }
        Object obj = y70.e;
        if (obj instanceof String) {
            strC = (String) obj;
        } else {
            strC = ((U7) obj).c();
            y70.e = strC;
        }
        if (!strC.isEmpty()) {
            this.f = y70.e;
            p();
        }
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.X.a(Y70.class, X70.class), c1856jk).b(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        Y70 y70 = new Y70(this);
        y70.e = this.f;
        o();
        if (y70.a()) {
            return y70;
        }
        throw H0.c(y70);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001d  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final X70 a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        Y70 y70 = null;
        try {
            try {
                a((Y70) Y70.h.a(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                Y70 y71 = (Y70) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    y70 = y71;
                    if (y70 != null) {
                        a(y70);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (y70 != null) {
                a(y70);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (X70) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC1468f90.W;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        Y70 y70 = new Y70(this);
        y70.e = this.f;
        o();
        if (y70.a()) {
            return y70;
        }
        throw H0.c(y70);
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 i() {
        Y70 y70 = new Y70(this);
        y70.e = this.f;
        o();
        return y70;
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC1468f90.X.a(Y70.class, X70.class);
    }

    public X70(C0859Tr c0859Tr) {
        super(c0859Tr);
        this.f = XmlPullParser.NO_NAMESPACE;
        Y70 y70 = Y70.g;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof Y70) {
            return a((Y70) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return Y70.g;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.X.a(Y70.class, X70.class), c1856jk).a(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(J0 j0) {
        if (j0 instanceof Y70) {
            return a((Y70) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (X70) c(c2712tk0);
    }
}
