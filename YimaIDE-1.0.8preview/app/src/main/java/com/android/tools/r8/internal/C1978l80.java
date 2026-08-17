package com.android.tools.r8.internal;

import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.l80, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1978l80 extends AbstractC0911Vr {
    public Object f;
    public int g;
    public int h;

    public C1978l80() {
        super(null);
        this.f = XmlPullParser.NO_NAMESPACE;
        C2064m80 c2064m80 = C2064m80.i;
    }

    public final C1978l80 a(C2064m80 c2064m80) {
        String strC;
        if (c2064m80 == C2064m80.i) {
            return this;
        }
        Object obj = c2064m80.e;
        if (obj instanceof String) {
            strC = (String) obj;
        } else {
            strC = ((U7) obj).c();
            c2064m80.e = strC;
        }
        if (!strC.isEmpty()) {
            this.f = c2064m80.e;
            p();
        }
        int i = c2064m80.f;
        if (i != 0) {
            this.g = i;
            p();
        }
        int i2 = c2064m80.g;
        if (i2 != 0) {
            this.h = i2;
            p();
        }
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.V0.a(C2064m80.class, C1978l80.class), c1856jk).b(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        C2064m80 c2064m80 = new C2064m80(this);
        c2064m80.e = this.f;
        c2064m80.f = this.g;
        c2064m80.g = this.h;
        o();
        if (c2064m80.a()) {
            return c2064m80;
        }
        throw H0.c(c2064m80);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001d  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final C1978l80 a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        C2064m80 c2064m80 = null;
        try {
            try {
                a((C2064m80) C2064m80.j.a(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                C2064m80 c2064m81 = (C2064m80) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    c2064m80 = c2064m81;
                    if (c2064m80 != null) {
                        a(c2064m80);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (c2064m80 != null) {
                a(c2064m80);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (C1978l80) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC1468f90.U0;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        C2064m80 c2064m80 = new C2064m80(this);
        c2064m80.e = this.f;
        c2064m80.f = this.g;
        c2064m80.g = this.h;
        o();
        if (c2064m80.a()) {
            return c2064m80;
        }
        throw H0.c(c2064m80);
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 i() {
        C2064m80 c2064m80 = new C2064m80(this);
        c2064m80.e = this.f;
        c2064m80.f = this.g;
        c2064m80.g = this.h;
        o();
        return c2064m80;
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC1468f90.V0.a(C2064m80.class, C1978l80.class);
    }

    public C1978l80(C0859Tr c0859Tr) {
        super(c0859Tr);
        this.f = XmlPullParser.NO_NAMESPACE;
        C2064m80 c2064m80 = C2064m80.i;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof C2064m80) {
            return a((C2064m80) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return C2064m80.i;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.V0.a(C2064m80.class, C1978l80.class), c1856jk).a(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(J0 j0) {
        if (j0 instanceof C2064m80) {
            return a((C2064m80) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (C1978l80) c(c2712tk0);
    }
}
