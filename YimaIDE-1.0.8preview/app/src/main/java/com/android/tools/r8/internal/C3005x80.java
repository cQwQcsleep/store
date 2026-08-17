package com.android.tools.r8.internal;

import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.x80, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3005x80 extends AbstractC0911Vr {
    public Object f;
    public int g;
    public int h;

    public C3005x80() {
        super(null);
        this.f = XmlPullParser.NO_NAMESPACE;
        C3089y80 c3089y80 = C3089y80.i;
    }

    public final C3005x80 a(C3089y80 c3089y80) {
        String strC;
        if (c3089y80 == C3089y80.i) {
            return this;
        }
        Object obj = c3089y80.e;
        if (obj instanceof String) {
            strC = (String) obj;
        } else {
            strC = ((U7) obj).c();
            c3089y80.e = strC;
        }
        if (!strC.isEmpty()) {
            this.f = c3089y80.e;
            p();
        }
        int i = c3089y80.f;
        if (i != 0) {
            this.g = i;
            p();
        }
        int i2 = c3089y80.g;
        if (i2 != 0) {
            this.h = i2;
            p();
        }
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.d0.a(C3089y80.class, C3005x80.class), c1856jk).b(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        C3089y80 c3089y80 = new C3089y80(this);
        c3089y80.e = this.f;
        c3089y80.f = this.g;
        c3089y80.g = this.h;
        o();
        if (c3089y80.a()) {
            return c3089y80;
        }
        throw H0.c(c3089y80);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001d  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final C3005x80 a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        C3089y80 c3089y80 = null;
        try {
            try {
                a((C3089y80) C3089y80.j.a(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                C3089y80 c3089y81 = (C3089y80) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    c3089y80 = c3089y81;
                    if (c3089y80 != null) {
                        a(c3089y80);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (c3089y80 != null) {
                a(c3089y80);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (C3005x80) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC1468f90.c0;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        C3089y80 c3089y80 = new C3089y80(this);
        c3089y80.e = this.f;
        c3089y80.f = this.g;
        c3089y80.g = this.h;
        o();
        if (c3089y80.a()) {
            return c3089y80;
        }
        throw H0.c(c3089y80);
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 i() {
        C3089y80 c3089y80 = new C3089y80(this);
        c3089y80.e = this.f;
        c3089y80.f = this.g;
        c3089y80.g = this.h;
        o();
        return c3089y80;
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC1468f90.d0.a(C3089y80.class, C3005x80.class);
    }

    public C3005x80(C0859Tr c0859Tr) {
        super(c0859Tr);
        this.f = XmlPullParser.NO_NAMESPACE;
        C3089y80 c3089y80 = C3089y80.i;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof C3089y80) {
            return a((C3089y80) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return C3089y80.i;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.d0.a(C3089y80.class, C3005x80.class), c1856jk).a(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(J0 j0) {
        if (j0 instanceof C3089y80) {
            return a((C3089y80) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (C3005x80) c(c2712tk0);
    }
}
