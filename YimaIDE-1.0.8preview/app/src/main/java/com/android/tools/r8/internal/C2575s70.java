package com.android.tools.r8.internal;

import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.s70, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2575s70 extends AbstractC2209ns {
    public static final C2575s70 j = new C2575s70();
    public static final C2405q70 k = new C2405q70();
    public P70 e;
    public volatile Object f;
    public int g;
    public R60 h;
    public byte i;

    public C2575s70() {
        this.i = (byte) -1;
        this.f = XmlPullParser.NO_NAMESPACE;
        this.g = 0;
    }

    @Override // com.android.tools.r8.internal.TN
    public final void a(AbstractC0793Rd abstractC0793Rd) {
        if (this.e != null) {
            abstractC0793Rd.a(1, l());
        }
        if (!AbstractC2209ns.a(this.f)) {
            AbstractC2209ns.a(abstractC0793Rd, 2, this.f);
        }
        if (this.g != AbstractC2233o70.a(1)) {
            abstractC0793Rd.b(3, this.g);
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
            P70 p70L = l();
            iA = AbstractC0793Rd.a(p70L) + AbstractC0793Rd.b(1);
        } else {
            iA = 0;
        }
        if (!AbstractC2209ns.a(this.f)) {
            iA += AbstractC2209ns.a(2, this.f);
        }
        if (this.g != AbstractC2233o70.a(1)) {
            iA = AbstractC0458Ef.a(this.g, AbstractC0793Rd.b(3), iA);
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
        return this == j ? new C2489r70() : new C2489r70().a(this);
    }

    @Override // com.android.tools.r8.internal.J0
    public final boolean equals(Object obj) {
        String strC;
        String strC2;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C2575s70)) {
            return super.equals(obj);
        }
        C2575s70 c2575s70 = (C2575s70) obj;
        P70 p70 = this.e;
        if ((p70 != null) != (c2575s70.e != null)) {
            return false;
        }
        if (p70 != null && !l().equals(c2575s70.l())) {
            return false;
        }
        Object obj2 = this.f;
        if (obj2 instanceof String) {
            strC = (String) obj2;
        } else {
            strC = ((U7) obj2).c();
            this.f = strC;
        }
        Object obj3 = c2575s70.f;
        if (obj3 instanceof String) {
            strC2 = (String) obj3;
        } else {
            strC2 = ((U7) obj3).c();
            c2575s70.f = strC2;
        }
        if (!strC.equals(strC2) || this.g != c2575s70.g) {
            return false;
        }
        R60 r60 = this.h;
        if ((r60 != null) != (c2575s70.h != null)) {
            return false;
        }
        return (r60 == null || k().equals(c2575s70.k())) && this.d.equals(c2575s70.d);
    }

    @Override // com.android.tools.r8.internal.WN
    public final C2712tk0 g() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.J0
    public final H0 h() {
        j.getClass();
        return new C2489r70();
    }

    @Override // com.android.tools.r8.internal.J0
    public final int hashCode() {
        String strC;
        int i = this.b;
        if (i != 0) {
            return i;
        }
        int iHashCode = AbstractC1468f90.E0.hashCode() + 779;
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
        int iHashCode2 = ((((strC.hashCode() + iA) * 37) + 3) * 53) + this.g;
        if (this.h != null) {
            iHashCode2 = k().hashCode() + Z50.a(iHashCode2, 37, 4, 53);
        }
        int iHashCode3 = this.d.hashCode() + (iHashCode2 * 29);
        this.b = iHashCode3;
        return iHashCode3;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final C2123ms j() {
        return AbstractC1468f90.F0.a(C2575s70.class, C2489r70.class);
    }

    public final R60 k() {
        R60 r60 = this.h;
        return r60 == null ? R60.h : r60;
    }

    public final P70 l() {
        P70 p70 = this.e;
        return p70 == null ? P70.h : p70;
    }

    public C2575s70(AbstractC0911Vr abstractC0911Vr) {
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
        return new C2489r70(c0859Tr);
    }
}
