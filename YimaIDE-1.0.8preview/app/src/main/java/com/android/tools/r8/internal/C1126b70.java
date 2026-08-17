package com.android.tools.r8.internal;

import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.b70, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1126b70 extends AbstractC2209ns {
    public static final C1126b70 i = new C1126b70();
    public static final Z60 j = new Z60();
    public volatile Object e;
    public P70 f;
    public volatile Object g;
    public byte h;

    public C1126b70() {
        this.h = (byte) -1;
        this.e = XmlPullParser.NO_NAMESPACE;
        this.g = XmlPullParser.NO_NAMESPACE;
    }

    @Override // com.android.tools.r8.internal.TN
    public final void a(AbstractC0793Rd abstractC0793Rd) {
        if (!AbstractC2209ns.a(this.e)) {
            AbstractC2209ns.a(abstractC0793Rd, 1, this.e);
        }
        if (this.f != null) {
            abstractC0793Rd.a(2, k());
        }
        if (!AbstractC2209ns.a(this.g)) {
            AbstractC2209ns.a(abstractC0793Rd, 3, this.g);
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
        if (this.f != null) {
            iA += AbstractC0793Rd.a(k()) + AbstractC0793Rd.b(2);
        }
        if (!AbstractC2209ns.a(this.g)) {
            iA += AbstractC2209ns.a(3, this.g);
        }
        int iC = this.d.c() + iA;
        this.c = iC;
        return iC;
    }

    @Override // com.android.tools.r8.internal.TN
    public final SN d() {
        return this == i ? new C1040a70() : new C1040a70().a(this);
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
        if (!(obj instanceof C1126b70)) {
            return super.equals(obj);
        }
        C1126b70 c1126b70 = (C1126b70) obj;
        Object obj2 = this.e;
        if (obj2 instanceof String) {
            strC = (String) obj2;
        } else {
            strC = ((U7) obj2).c();
            this.e = strC;
        }
        Object obj3 = c1126b70.e;
        if (obj3 instanceof String) {
            strC2 = (String) obj3;
        } else {
            strC2 = ((U7) obj3).c();
            c1126b70.e = strC2;
        }
        if (!strC.equals(strC2)) {
            return false;
        }
        P70 p70 = this.f;
        if ((p70 != null) != (c1126b70.f != null)) {
            return false;
        }
        if (p70 != null && !k().equals(c1126b70.k())) {
            return false;
        }
        Object obj4 = this.g;
        if (obj4 instanceof String) {
            strC3 = (String) obj4;
        } else {
            strC3 = ((U7) obj4).c();
            this.g = strC3;
        }
        Object obj5 = c1126b70.g;
        if (obj5 instanceof String) {
            strC4 = (String) obj5;
        } else {
            strC4 = ((U7) obj5).c();
            c1126b70.g = strC4;
        }
        return strC3.equals(strC4) && this.d.equals(c1126b70.d);
    }

    @Override // com.android.tools.r8.internal.WN
    public final C2712tk0 g() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.J0
    public final H0 h() {
        i.getClass();
        return new C1040a70();
    }

    @Override // com.android.tools.r8.internal.J0
    public final int hashCode() {
        String strC;
        String strC2;
        int i2 = this.b;
        if (i2 != 0) {
            return i2;
        }
        int iA = AbstractC0432Df.a(AbstractC1468f90.y, 779, 37, 1, 53);
        Object obj = this.e;
        if (obj instanceof String) {
            strC = (String) obj;
        } else {
            strC = ((U7) obj).c();
            this.e = strC;
        }
        int iHashCode = strC.hashCode() + iA;
        if (this.f != null) {
            iHashCode = k().hashCode() + Z50.a(iHashCode, 37, 2, 53);
        }
        int iA2 = Z50.a(iHashCode, 37, 3, 53);
        Object obj2 = this.g;
        if (obj2 instanceof String) {
            strC2 = (String) obj2;
        } else {
            strC2 = ((U7) obj2).c();
            this.g = strC2;
        }
        int iHashCode2 = this.d.hashCode() + ((strC2.hashCode() + iA2) * 29);
        this.b = iHashCode2;
        return iHashCode2;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final C2123ms j() {
        return AbstractC1468f90.z.a(C1126b70.class, C1040a70.class);
    }

    public final P70 k() {
        P70 p70 = this.f;
        return p70 == null ? P70.h : p70;
    }

    public C1126b70(AbstractC0911Vr abstractC0911Vr) {
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
        return new C1040a70(c0859Tr);
    }
}
