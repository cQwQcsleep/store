package com.android.tools.r8.internal;

import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class S80 extends AbstractC2209ns {
    public static final S80 j = new S80();
    public static final P80 k = new P80();
    public int e;
    public P70 f;
    public volatile Object g;
    public boolean h;
    public byte i;

    public S80() {
        this.i = (byte) -1;
        this.e = 0;
        this.g = XmlPullParser.NO_NAMESPACE;
    }

    @Override // com.android.tools.r8.internal.TN
    public final void a(AbstractC0793Rd abstractC0793Rd) {
        if (this.e != R80.a(1)) {
            abstractC0793Rd.b(1, this.e);
        }
        if (this.f != null) {
            abstractC0793Rd.a(2, k());
        }
        if (!AbstractC2209ns.a(this.g)) {
            AbstractC2209ns.a(abstractC0793Rd, 3, this.g);
        }
        boolean z = this.h;
        if (z) {
            abstractC0793Rd.a(4, z);
        }
        this.d.a(abstractC0793Rd);
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return j;
    }

    @Override // com.android.tools.r8.internal.TN
    public final int c() {
        int iA;
        int i = this.c;
        if (i != -1) {
            return i;
        }
        if (this.e != R80.a(1)) {
            int i2 = this.e;
            iA = AbstractC0793Rd.a(i2) + AbstractC0793Rd.b(1);
        } else {
            iA = 0;
        }
        if (this.f != null) {
            P70 p70K = k();
            iA += AbstractC0793Rd.a(p70K) + AbstractC0793Rd.b(2);
        }
        if (!AbstractC2209ns.a(this.g)) {
            iA += AbstractC2209ns.a(3, this.g);
        }
        if (this.h) {
            iA = V60.a(4, 1, iA);
        }
        int iC = this.d.c() + iA;
        this.c = iC;
        return iC;
    }

    @Override // com.android.tools.r8.internal.J0
    public final boolean equals(Object obj) {
        String strC;
        String strC2;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof S80)) {
            return super.equals(obj);
        }
        S80 s80 = (S80) obj;
        if (this.e != s80.e) {
            return false;
        }
        P70 p70 = this.f;
        if ((p70 != null) != (s80.f != null)) {
            return false;
        }
        if (p70 != null && !k().equals(s80.k())) {
            return false;
        }
        Object obj2 = this.g;
        if (obj2 instanceof String) {
            strC = (String) obj2;
        } else {
            strC = ((U7) obj2).c();
            this.g = strC;
        }
        Object obj3 = s80.g;
        if (obj3 instanceof String) {
            strC2 = (String) obj3;
        } else {
            strC2 = ((U7) obj3).c();
            s80.g = strC2;
        }
        return strC.equals(strC2) && this.h == s80.h && this.d.equals(s80.d);
    }

    @Override // com.android.tools.r8.internal.WN
    public final C2712tk0 g() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.J0
    public final H0 h() {
        return j.d();
    }

    @Override // com.android.tools.r8.internal.J0
    public final int hashCode() {
        String strC;
        int i = this.b;
        if (i != 0) {
            return i;
        }
        int iA = AbstractC0432Df.a(AbstractC1468f90.u, 779, 37, 1, 53) + this.e;
        if (this.f != null) {
            iA = Z50.a(iA, 37, 2, 53) + k().hashCode();
        }
        int iA2 = Z50.a(iA, 37, 3, 53);
        Object obj = this.g;
        if (obj instanceof String) {
            strC = (String) obj;
        } else {
            strC = ((U7) obj).c();
            this.g = strC;
        }
        int iHashCode = this.d.hashCode() + ((AbstractC1556gB.a(this.h) + ((((strC.hashCode() + iA2) * 37) + 4) * 53)) * 29);
        this.b = iHashCode;
        return iHashCode;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final C2123ms j() {
        return AbstractC1468f90.v.a(S80.class, Q80.class);
    }

    public final P70 k() {
        P70 p70 = this.f;
        return p70 == null ? P70.h : p70;
    }

    @Override // com.android.tools.r8.internal.TN
    /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
    public final Q80 d() {
        return this == j ? new Q80() : new Q80().a(this);
    }

    public S80(AbstractC0911Vr abstractC0911Vr) {
        super(abstractC0911Vr);
        this.i = (byte) -1;
    }

    @Override // com.android.tools.r8.internal.VN
    public final boolean a() {
        byte b = this.i;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        this.i = (byte) 1;
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final H0 a(C0859Tr c0859Tr) {
        return new Q80(c0859Tr);
    }
}
