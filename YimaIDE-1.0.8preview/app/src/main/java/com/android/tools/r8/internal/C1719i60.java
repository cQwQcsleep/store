package com.android.tools.r8.internal;

import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.i60, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1719i60 extends AbstractC2209ns {
    public static final C1719i60 i = new C1719i60();
    public static final C1548g60 j = new C1548g60();
    public P70 e;
    public volatile Object f;
    public R60 g;
    public byte h;

    public C1719i60() {
        this.h = (byte) -1;
        this.f = XmlPullParser.NO_NAMESPACE;
    }

    @Override // com.android.tools.r8.internal.TN
    public final void a(AbstractC0793Rd abstractC0793Rd) {
        if (this.e != null) {
            abstractC0793Rd.a(1, l());
        }
        if (!AbstractC2209ns.a(this.f)) {
            AbstractC2209ns.a(abstractC0793Rd, 2, this.f);
        }
        if (this.g != null) {
            abstractC0793Rd.a(3, k());
        }
        this.d.a(abstractC0793Rd);
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return i;
    }

    @Override // com.android.tools.r8.internal.TN
    public final int c() {
        int iA;
        int i2 = this.c;
        if (i2 != -1) {
            return i2;
        }
        if (this.e != null) {
            P70 p70L = l();
            iA = AbstractC0793Rd.a(p70L) + AbstractC0793Rd.b(1);
        } else {
            iA = 0;
        }
        if (!AbstractC2209ns.a(this.f)) {
            iA += AbstractC2209ns.a(2, this.f);
        }
        if (this.g != null) {
            R60 r60K = k();
            iA += AbstractC0793Rd.a(r60K) + AbstractC0793Rd.b(3);
        }
        int iC = this.d.c() + iA;
        this.c = iC;
        return iC;
    }

    @Override // com.android.tools.r8.internal.TN
    public final SN d() {
        return this == i ? new C1633h60() : new C1633h60().a(this);
    }

    @Override // com.android.tools.r8.internal.J0
    public final boolean equals(Object obj) {
        String strC;
        String strC2;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C1719i60)) {
            return super.equals(obj);
        }
        C1719i60 c1719i60 = (C1719i60) obj;
        P70 p70 = this.e;
        if ((p70 != null) != (c1719i60.e != null)) {
            return false;
        }
        if (p70 != null && !l().equals(c1719i60.l())) {
            return false;
        }
        Object obj2 = this.f;
        if (obj2 instanceof String) {
            strC = (String) obj2;
        } else {
            strC = ((U7) obj2).c();
            this.f = strC;
        }
        Object obj3 = c1719i60.f;
        if (obj3 instanceof String) {
            strC2 = (String) obj3;
        } else {
            strC2 = ((U7) obj3).c();
            c1719i60.f = strC2;
        }
        if (!strC.equals(strC2)) {
            return false;
        }
        R60 r60 = this.g;
        if ((r60 != null) != (c1719i60.g != null)) {
            return false;
        }
        return (r60 == null || k().equals(c1719i60.k())) && this.d.equals(c1719i60.d);
    }

    @Override // com.android.tools.r8.internal.WN
    public final C2712tk0 g() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.J0
    public final H0 h() {
        i.getClass();
        return new C1633h60();
    }

    @Override // com.android.tools.r8.internal.J0
    public final int hashCode() {
        String strC;
        int i2 = this.b;
        if (i2 != 0) {
            return i2;
        }
        int iHashCode = AbstractC1468f90.A0.hashCode() + 779;
        if (this.e != null) {
            iHashCode = Z50.a(iHashCode, 37, 1, 53) + l().hashCode();
        }
        int iA = Z50.a(iHashCode, 37, 2, 53);
        Object obj = this.f;
        if (obj instanceof String) {
            strC = (String) obj;
        } else {
            strC = ((U7) obj).c();
            this.f = strC;
        }
        int iHashCode2 = strC.hashCode() + iA;
        if (this.g != null) {
            iHashCode2 = k().hashCode() + Z50.a(iHashCode2, 37, 3, 53);
        }
        int iHashCode3 = this.d.hashCode() + (iHashCode2 * 29);
        this.b = iHashCode3;
        return iHashCode3;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final C2123ms j() {
        return AbstractC1468f90.B0.a(C1719i60.class, C1633h60.class);
    }

    public final R60 k() {
        R60 r60 = this.g;
        return r60 == null ? R60.h : r60;
    }

    public final P70 l() {
        P70 p70 = this.e;
        return p70 == null ? P70.h : p70;
    }

    public C1719i60(AbstractC0911Vr abstractC0911Vr) {
        super(abstractC0911Vr);
        this.h = (byte) -1;
    }

    @Override // com.android.tools.r8.internal.VN
    public final boolean a() {
        byte b = this.h;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        this.h = (byte) 1;
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final H0 a(C0859Tr c0859Tr) {
        return new C1633h60(c0859Tr);
    }
}
