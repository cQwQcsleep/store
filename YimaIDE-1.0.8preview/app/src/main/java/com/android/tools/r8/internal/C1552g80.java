package com.android.tools.r8.internal;

import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.g80, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1552g80 extends AbstractC2209ns {
    public static final C1552g80 j = new C1552g80();
    public static final C1380e80 k = new C1380e80();
    public P70 e;
    public volatile Object f;
    public J70 g;
    public R60 h;
    public byte i;

    public C1552g80() {
        this.i = (byte) -1;
        this.f = XmlPullParser.NO_NAMESPACE;
    }

    @Override // com.android.tools.r8.internal.TN
    public final void a(AbstractC0793Rd abstractC0793Rd) {
        if (this.e != null) {
            abstractC0793Rd.a(1, m());
        }
        if (!AbstractC2209ns.a(this.f)) {
            AbstractC2209ns.a(abstractC0793Rd, 2, this.f);
        }
        if (this.g != null) {
            abstractC0793Rd.a(3, l());
        }
        if (this.h != null) {
            abstractC0793Rd.a(4, k());
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
        if (this.e != null) {
            P70 p70M = m();
            iA = AbstractC0793Rd.a(p70M) + AbstractC0793Rd.b(1);
        } else {
            iA = 0;
        }
        if (!AbstractC2209ns.a(this.f)) {
            iA += AbstractC2209ns.a(2, this.f);
        }
        if (this.g != null) {
            J70 j70L = l();
            iA += AbstractC0793Rd.a(j70L) + AbstractC0793Rd.b(3);
        }
        if (this.h != null) {
            R60 r60K = k();
            iA += AbstractC0793Rd.a(r60K) + AbstractC0793Rd.b(4);
        }
        int iC = this.d.c() + iA;
        this.c = iC;
        return iC;
    }

    @Override // com.android.tools.r8.internal.TN
    public final SN d() {
        return this == j ? new C1466f80() : new C1466f80().a(this);
    }

    @Override // com.android.tools.r8.internal.J0
    public final boolean equals(Object obj) {
        String strC;
        String strC2;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C1552g80)) {
            return super.equals(obj);
        }
        C1552g80 c1552g80 = (C1552g80) obj;
        P70 p70 = this.e;
        if ((p70 != null) != (c1552g80.e != null)) {
            return false;
        }
        if (p70 != null && !m().equals(c1552g80.m())) {
            return false;
        }
        Object obj2 = this.f;
        if (obj2 instanceof String) {
            strC = (String) obj2;
        } else {
            strC = ((U7) obj2).c();
            this.f = strC;
        }
        Object obj3 = c1552g80.f;
        if (obj3 instanceof String) {
            strC2 = (String) obj3;
        } else {
            strC2 = ((U7) obj3).c();
            c1552g80.f = strC2;
        }
        if (!strC.equals(strC2)) {
            return false;
        }
        J70 j70 = this.g;
        if ((j70 != null) != (c1552g80.g != null)) {
            return false;
        }
        if (j70 != null && !l().equals(c1552g80.l())) {
            return false;
        }
        R60 r60 = this.h;
        if ((r60 != null) != (c1552g80.h != null)) {
            return false;
        }
        return (r60 == null || k().equals(c1552g80.k())) && this.d.equals(c1552g80.d);
    }

    @Override // com.android.tools.r8.internal.WN
    public final C2712tk0 g() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.J0
    public final H0 h() {
        j.getClass();
        return new C1466f80();
    }

    @Override // com.android.tools.r8.internal.J0
    public final int hashCode() {
        String strC;
        int i = this.b;
        if (i != 0) {
            return i;
        }
        int iHashCode = AbstractC1468f90.s0.hashCode() + 779;
        if (this.e != null) {
            iHashCode = Z50.a(iHashCode, 37, 1, 53) + m().hashCode();
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
            iHashCode2 = l().hashCode() + Z50.a(iHashCode2, 37, 3, 53);
        }
        if (this.h != null) {
            iHashCode2 = k().hashCode() + Z50.a(iHashCode2, 37, 4, 53);
        }
        int iHashCode3 = this.d.hashCode() + (iHashCode2 * 29);
        this.b = iHashCode3;
        return iHashCode3;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final C2123ms j() {
        return AbstractC1468f90.t0.a(C1552g80.class, C1466f80.class);
    }

    public final R60 k() {
        R60 r60 = this.h;
        return r60 == null ? R60.h : r60;
    }

    public final J70 l() {
        J70 j70 = this.g;
        return j70 == null ? J70.m : j70;
    }

    public final P70 m() {
        P70 p70 = this.e;
        return p70 == null ? P70.h : p70;
    }

    public C1552g80(AbstractC0911Vr abstractC0911Vr) {
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
        return new C1466f80(c0859Tr);
    }
}
