package com.android.tools.r8.internal;

import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.a90, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1044a90 extends AbstractC0911Vr {
    public Object f;
    public Object g;
    public S70 h;

    public C1044a90() {
        super(null);
        this.f = XmlPullParser.NO_NAMESPACE;
        this.g = XmlPullParser.NO_NAMESPACE;
        C1130b90 c1130b90 = C1130b90.i;
    }

    public final C1044a90 a(C1130b90 c1130b90) {
        String strC;
        String strC2;
        if (c1130b90 == C1130b90.i) {
            return this;
        }
        Object obj = c1130b90.e;
        if (obj instanceof String) {
            strC = (String) obj;
        } else {
            strC = ((U7) obj).c();
            c1130b90.e = strC;
        }
        if (!strC.isEmpty()) {
            this.f = c1130b90.e;
            p();
        }
        Object obj2 = c1130b90.f;
        if (obj2 instanceof String) {
            strC2 = (String) obj2;
        } else {
            strC2 = ((U7) obj2).c();
            c1130b90.f = strC2;
        }
        if (!strC2.isEmpty()) {
            this.g = c1130b90.f;
            p();
        }
        if (c1130b90.g != null) {
            S70 s70K = c1130b90.k();
            S70 s70 = this.h;
            if (s70 != null) {
                this.h = S70.h.d().a(s70).a(s70K).i();
            } else {
                this.h = s70K;
            }
            p();
        }
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.L0.a(C1130b90.class, C1044a90.class), c1856jk).b(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        C1130b90 c1130b90 = new C1130b90(this);
        c1130b90.e = this.f;
        c1130b90.f = this.g;
        c1130b90.g = this.h;
        o();
        if (c1130b90.a()) {
            return c1130b90;
        }
        throw H0.c(c1130b90);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001d  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final C1044a90 a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        C1130b90 c1130b90 = null;
        try {
            try {
                a((C1130b90) C1130b90.j.a(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                C1130b90 c1130b91 = (C1130b90) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    c1130b90 = c1130b91;
                    if (c1130b90 != null) {
                        a(c1130b90);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (c1130b90 != null) {
                a(c1130b90);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (C1044a90) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC1468f90.K0;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        C1130b90 c1130b90 = new C1130b90(this);
        c1130b90.e = this.f;
        c1130b90.f = this.g;
        c1130b90.g = this.h;
        o();
        if (c1130b90.a()) {
            return c1130b90;
        }
        throw H0.c(c1130b90);
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 i() {
        C1130b90 c1130b90 = new C1130b90(this);
        c1130b90.e = this.f;
        c1130b90.f = this.g;
        c1130b90.g = this.h;
        o();
        return c1130b90;
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC1468f90.L0.a(C1130b90.class, C1044a90.class);
    }

    public C1044a90(C0859Tr c0859Tr) {
        super(c0859Tr);
        this.f = XmlPullParser.NO_NAMESPACE;
        this.g = XmlPullParser.NO_NAMESPACE;
        C1130b90 c1130b90 = C1130b90.i;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof C1130b90) {
            return a((C1130b90) j0);
        }
        super.b(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return C1130b90.i;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.L0.a(C1130b90.class, C1044a90.class), c1856jk).a(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: a */
    public final H0 b(J0 j0) {
        if (j0 instanceof C1130b90) {
            return a((C1130b90) j0);
        }
        super.b(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (C1044a90) c(c2712tk0);
    }
}
