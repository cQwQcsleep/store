package com.android.tools.r8.internal;

import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.o60, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2231o60 extends AbstractC2209ns {
    public static final C2231o60 k = new C2231o60();
    public static final C2060m60 l = new C2060m60();
    public P70 e;
    public volatile Object f;
    public J70 g;
    public int h;
    public int i;
    public byte j;

    public C2231o60() {
        this.j = (byte) -1;
        this.f = XmlPullParser.NO_NAMESPACE;
    }

    @Override // com.android.tools.r8.internal.TN
    public final void a(AbstractC0793Rd abstractC0793Rd) throws C0741Pd {
        if (this.e != null) {
            abstractC0793Rd.a(1, l());
        }
        if (!AbstractC2209ns.a(this.f)) {
            AbstractC2209ns.a(abstractC0793Rd, 2, this.f);
        }
        if (this.g != null) {
            abstractC0793Rd.a(3, k());
        }
        int i = this.h;
        if (i != 0) {
            C0689Nd c0689Nd = (C0689Nd) abstractC0793Rd;
            c0689Nd.c(4, 0);
            c0689Nd.f(i);
        }
        int i2 = this.i;
        if (i2 != 0) {
            C0689Nd c0689Nd2 = (C0689Nd) abstractC0793Rd;
            c0689Nd2.c(5, 0);
            c0689Nd2.f(i2);
        }
        this.d.a(abstractC0793Rd);
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return k;
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
        if (this.g != null) {
            J70 j70K = k();
            iA += AbstractC0793Rd.a(j70K) + AbstractC0793Rd.b(3);
        }
        int i2 = this.h;
        if (i2 != 0) {
            iA = AbstractC0484Ff.a(i2, AbstractC0793Rd.b(4), iA);
        }
        int i3 = this.i;
        if (i3 != 0) {
            iA = AbstractC0484Ff.a(i3, AbstractC0793Rd.b(5), iA);
        }
        int iC = this.d.c() + iA;
        this.c = iC;
        return iC;
    }

    @Override // com.android.tools.r8.internal.TN
    public final SN d() {
        return this == k ? new C2145n60() : new C2145n60().a(this);
    }

    @Override // com.android.tools.r8.internal.J0
    public final boolean equals(Object obj) {
        String strC;
        String strC2;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C2231o60)) {
            return super.equals(obj);
        }
        C2231o60 c2231o60 = (C2231o60) obj;
        P70 p70 = this.e;
        if ((p70 != null) != (c2231o60.e != null)) {
            return false;
        }
        if (p70 != null && !l().equals(c2231o60.l())) {
            return false;
        }
        Object obj2 = this.f;
        if (obj2 instanceof String) {
            strC = (String) obj2;
        } else {
            strC = ((U7) obj2).c();
            this.f = strC;
        }
        Object obj3 = c2231o60.f;
        if (obj3 instanceof String) {
            strC2 = (String) obj3;
        } else {
            strC2 = ((U7) obj3).c();
            c2231o60.f = strC2;
        }
        if (!strC.equals(strC2)) {
            return false;
        }
        J70 j70 = this.g;
        if ((j70 != null) != (c2231o60.g != null)) {
            return false;
        }
        return (j70 == null || k().equals(c2231o60.k())) && this.h == c2231o60.h && this.i == c2231o60.i && this.d.equals(c2231o60.d);
    }

    @Override // com.android.tools.r8.internal.WN
    public final C2712tk0 g() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.J0
    public final H0 h() {
        k.getClass();
        return new C2145n60();
    }

    @Override // com.android.tools.r8.internal.J0
    public final int hashCode() {
        String strC;
        int i = this.b;
        if (i != 0) {
            return i;
        }
        int iHashCode = AbstractC1468f90.o0.hashCode() + 779;
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
        int iHashCode3 = this.d.hashCode() + ((AbstractC0406Cf.a(Z50.a(iHashCode2, 37, 4, 53), this.h, 37, 5, 53) + this.i) * 29);
        this.b = iHashCode3;
        return iHashCode3;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final C2123ms j() {
        return AbstractC1468f90.p0.a(C2231o60.class, C2145n60.class);
    }

    public final J70 k() {
        J70 j70 = this.g;
        return j70 == null ? J70.m : j70;
    }

    public final P70 l() {
        P70 p70 = this.e;
        return p70 == null ? P70.h : p70;
    }

    public C2231o60(AbstractC0911Vr abstractC0911Vr) {
        super(abstractC0911Vr);
        this.j = (byte) -1;
    }

    @Override // com.android.tools.r8.internal.VN
    public final boolean a() {
        byte b = this.j;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        this.j = (byte) 1;
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final H0 a(C0859Tr c0859Tr) {
        return new C2145n60(c0859Tr);
    }
}
