package com.android.tools.r8.internal;

import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.b90, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1130b90 extends AbstractC2209ns {
    public static final C1130b90 i = new C1130b90();
    public static final Z80 j = new Z80();
    public volatile Object e;
    public volatile Object f;
    public S70 g;
    public byte h;

    public C1130b90() {
        this.h = (byte) -1;
        this.e = XmlPullParser.NO_NAMESPACE;
        this.f = XmlPullParser.NO_NAMESPACE;
    }

    @Override // com.android.tools.r8.internal.TN
    public final void a(AbstractC0793Rd abstractC0793Rd) {
        if (!AbstractC2209ns.a(this.e)) {
            AbstractC2209ns.a(abstractC0793Rd, 1, this.e);
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
        int i2 = this.c;
        if (i2 != -1) {
            return i2;
        }
        int iA = !AbstractC2209ns.a(this.e) ? AbstractC2209ns.a(1, this.e) : 0;
        if (!AbstractC2209ns.a(this.f)) {
            iA += AbstractC2209ns.a(2, this.f);
        }
        if (this.g != null) {
            iA += AbstractC0793Rd.a(k()) + AbstractC0793Rd.b(3);
        }
        int iC = this.d.c() + iA;
        this.c = iC;
        return iC;
    }

    @Override // com.android.tools.r8.internal.TN
    public final SN d() {
        return this == i ? new C1044a90() : new C1044a90().a(this);
    }

    @Override // com.android.tools.r8.internal.J0
    public final boolean equals(Object obj) {
        String strC;
        String strC2;
        String strC3;
        String strC4;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C1130b90)) {
            return super.equals(obj);
        }
        C1130b90 c1130b90 = (C1130b90) obj;
        Object obj2 = this.e;
        if (obj2 instanceof String) {
            strC = (String) obj2;
        } else {
            strC = ((U7) obj2).c();
            this.e = strC;
        }
        Object obj3 = c1130b90.e;
        if (obj3 instanceof String) {
            strC2 = (String) obj3;
        } else {
            strC2 = ((U7) obj3).c();
            c1130b90.e = strC2;
        }
        if (!strC.equals(strC2)) {
            return false;
        }
        Object obj4 = this.f;
        if (obj4 instanceof String) {
            strC3 = (String) obj4;
        } else {
            strC3 = ((U7) obj4).c();
            this.f = strC3;
        }
        Object obj5 = c1130b90.f;
        if (obj5 instanceof String) {
            strC4 = (String) obj5;
        } else {
            strC4 = ((U7) obj5).c();
            c1130b90.f = strC4;
        }
        if (!strC3.equals(strC4)) {
            return false;
        }
        S70 s70 = this.g;
        if ((s70 != null) != (c1130b90.g != null)) {
            return false;
        }
        return (s70 == null || k().equals(c1130b90.k())) && this.d.equals(c1130b90.d);
    }

    @Override // com.android.tools.r8.internal.WN
    public final C2712tk0 g() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.J0
    public final H0 h() {
        i.getClass();
        return new C1044a90();
    }

    @Override // com.android.tools.r8.internal.J0
    public final int hashCode() {
        String strC;
        String strC2;
        int i2 = this.b;
        if (i2 != 0) {
            return i2;
        }
        int iA = AbstractC0432Df.a(AbstractC1468f90.K0, 779, 37, 1, 53);
        Object obj = this.e;
        if (obj instanceof String) {
            strC = (String) obj;
        } else {
            strC = ((U7) obj).c();
            this.e = strC;
        }
        int iHashCode = (((strC.hashCode() + iA) * 37) + 2) * 53;
        Object obj2 = this.f;
        if (obj2 instanceof String) {
            strC2 = (String) obj2;
        } else {
            strC2 = ((U7) obj2).c();
            this.f = strC2;
        }
        int iHashCode2 = strC2.hashCode() + iHashCode;
        if (this.g != null) {
            iHashCode2 = Z50.a(iHashCode2, 37, 3, 53) + k().hashCode();
        }
        int iHashCode3 = this.d.hashCode() + (iHashCode2 * 29);
        this.b = iHashCode3;
        return iHashCode3;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final C2123ms j() {
        return AbstractC1468f90.L0.a(C1130b90.class, C1044a90.class);
    }

    public final S70 k() {
        S70 s70 = this.g;
        return s70 == null ? S70.h : s70;
    }

    public C1130b90(AbstractC0911Vr abstractC0911Vr) {
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
        return new C1044a90(c0859Tr);
    }
}
