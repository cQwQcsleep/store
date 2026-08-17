package com.android.tools.r8.internal;

import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class E70 extends AbstractC0911Vr {
    public Object f;

    public E70() {
        super(null);
        this.f = XmlPullParser.NO_NAMESPACE;
        F70 f70 = F70.g;
    }

    public final E70 a(F70 f70) {
        String strC;
        if (f70 == F70.g) {
            return this;
        }
        Object obj = f70.e;
        if (obj instanceof String) {
            strC = (String) obj;
        } else {
            strC = ((U7) obj).c();
            f70.e = strC;
        }
        if (!strC.isEmpty()) {
            this.f = f70.e;
            p();
        }
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.Z.a(F70.class, E70.class), c1856jk).b(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        F70 f70 = new F70(this);
        f70.e = this.f;
        o();
        if (f70.a()) {
            return f70;
        }
        throw H0.c(f70);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001d  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final E70 a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        F70 f70 = null;
        try {
            try {
                a((F70) F70.h.a(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                F70 f71 = (F70) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    f70 = f71;
                    if (f70 != null) {
                        a(f70);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (f70 != null) {
                a(f70);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (E70) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC1468f90.Y;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        F70 f70 = new F70(this);
        f70.e = this.f;
        o();
        if (f70.a()) {
            return f70;
        }
        throw H0.c(f70);
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 i() {
        F70 f70 = new F70(this);
        f70.e = this.f;
        o();
        return f70;
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC1468f90.Z.a(F70.class, E70.class);
    }

    public E70(C0859Tr c0859Tr) {
        super(c0859Tr);
        this.f = XmlPullParser.NO_NAMESPACE;
        F70 f70 = F70.g;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof F70) {
            return a((F70) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return F70.g;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.Z.a(F70.class, E70.class), c1856jk).a(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(J0 j0) {
        if (j0 instanceof F70) {
            return a((F70) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (E70) c(c2712tk0);
    }
}
