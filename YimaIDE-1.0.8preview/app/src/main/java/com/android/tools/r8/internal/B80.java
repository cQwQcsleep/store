package com.android.tools.r8.internal;

import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class B80 extends AbstractC0911Vr {
    public Object f;
    public Object g;

    public B80() {
        super(null);
        this.f = XmlPullParser.NO_NAMESPACE;
        this.g = XmlPullParser.NO_NAMESPACE;
        C80 c80 = C80.h;
    }

    public final B80 a(C80 c80) {
        String strC;
        String strC2;
        if (c80 == C80.h) {
            return this;
        }
        Object obj = c80.e;
        if (obj instanceof String) {
            strC = (String) obj;
        } else {
            strC = ((U7) obj).c();
            c80.e = strC;
        }
        if (!strC.isEmpty()) {
            this.f = c80.e;
            p();
        }
        Object obj2 = c80.f;
        if (obj2 instanceof String) {
            strC2 = (String) obj2;
        } else {
            strC2 = ((U7) obj2).c();
            c80.f = strC2;
        }
        if (!strC2.isEmpty()) {
            this.g = c80.f;
            p();
        }
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.h.a(C80.class, B80.class), c1856jk).b(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        C80 c80 = new C80(this);
        c80.e = this.f;
        c80.f = this.g;
        o();
        if (c80.a()) {
            return c80;
        }
        throw H0.c(c80);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001d  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final B80 a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        C80 c80 = null;
        try {
            try {
                a((C80) C80.i.a(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                C80 c81 = (C80) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    c80 = c81;
                    if (c80 != null) {
                        a(c80);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (c80 != null) {
                a(c80);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (B80) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC1468f90.g;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        C80 c80 = new C80(this);
        c80.e = this.f;
        c80.f = this.g;
        o();
        if (c80.a()) {
            return c80;
        }
        throw H0.c(c80);
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 i() {
        C80 c80 = new C80(this);
        c80.e = this.f;
        c80.f = this.g;
        o();
        return c80;
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC1468f90.h.a(C80.class, B80.class);
    }

    public B80(C0859Tr c0859Tr) {
        super(c0859Tr);
        this.f = XmlPullParser.NO_NAMESPACE;
        this.g = XmlPullParser.NO_NAMESPACE;
        C80 c80 = C80.h;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof C80) {
            return a((C80) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return C80.h;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.h.a(C80.class, B80.class), c1856jk).a(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(J0 j0) {
        if (j0 instanceof C80) {
            return a((C80) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (B80) c(c2712tk0);
    }
}
