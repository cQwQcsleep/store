package com.android.tools.r8.internal;

import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.a70, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1040a70 extends AbstractC0911Vr {
    public Object f;
    public P70 g;
    public Object h;

    public C1040a70() {
        super(null);
        this.f = XmlPullParser.NO_NAMESPACE;
        this.h = XmlPullParser.NO_NAMESPACE;
        C1126b70 c1126b70 = C1126b70.i;
    }

    public final C1040a70 a(C1126b70 c1126b70) {
        String strC;
        String strC2;
        if (c1126b70 == C1126b70.i) {
            return this;
        }
        Object obj = c1126b70.e;
        if (obj instanceof String) {
            strC = (String) obj;
        } else {
            strC = ((U7) obj).c();
            c1126b70.e = strC;
        }
        if (!strC.isEmpty()) {
            this.f = c1126b70.e;
            p();
        }
        if (c1126b70.f != null) {
            P70 p70K = c1126b70.k();
            P70 p70 = this.g;
            if (p70 != null) {
                this.g = P70.h.d().a(p70).a(p70K).i();
            } else {
                this.g = p70K;
            }
            p();
        }
        Object obj2 = c1126b70.g;
        if (obj2 instanceof String) {
            strC2 = (String) obj2;
        } else {
            strC2 = ((U7) obj2).c();
            c1126b70.g = strC2;
        }
        if (!strC2.isEmpty()) {
            this.h = c1126b70.g;
            p();
        }
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.z.a(C1126b70.class, C1040a70.class), c1856jk).b(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        C1126b70 c1126b70 = new C1126b70(this);
        c1126b70.e = this.f;
        c1126b70.f = this.g;
        c1126b70.g = this.h;
        o();
        if (c1126b70.a()) {
            return c1126b70;
        }
        throw H0.c(c1126b70);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001d  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final C1040a70 a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        C1126b70 c1126b70 = null;
        try {
            try {
                a((C1126b70) C1126b70.j.a(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                C1126b70 c1126b71 = (C1126b70) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    c1126b70 = c1126b71;
                    if (c1126b70 != null) {
                        a(c1126b70);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (c1126b70 != null) {
                a(c1126b70);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (C1040a70) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC1468f90.y;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        C1126b70 c1126b70 = new C1126b70(this);
        c1126b70.e = this.f;
        c1126b70.f = this.g;
        c1126b70.g = this.h;
        o();
        if (c1126b70.a()) {
            return c1126b70;
        }
        throw H0.c(c1126b70);
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 i() {
        C1126b70 c1126b70 = new C1126b70(this);
        c1126b70.e = this.f;
        c1126b70.f = this.g;
        c1126b70.g = this.h;
        o();
        return c1126b70;
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC1468f90.z.a(C1126b70.class, C1040a70.class);
    }

    public C1040a70(C0859Tr c0859Tr) {
        super(c0859Tr);
        this.f = XmlPullParser.NO_NAMESPACE;
        this.h = XmlPullParser.NO_NAMESPACE;
        C1126b70 c1126b70 = C1126b70.i;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof C1126b70) {
            return a((C1126b70) j0);
        }
        super.b(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return C1126b70.i;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.z.a(C1126b70.class, C1040a70.class), c1856jk).a(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: a */
    public final H0 b(J0 j0) {
        if (j0 instanceof C1126b70) {
            return a((C1126b70) j0);
        }
        super.b(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (C1040a70) c(c2712tk0);
    }
}
