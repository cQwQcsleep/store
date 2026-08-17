package com.android.tools.r8.internal;

import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class V80 extends AbstractC2209ns {
    public static final V80 l = new V80();
    public static final T80 m = new T80();
    public volatile Object e;
    public volatile Object f;
    public volatile Object g;
    public S70 h;
    public int i;
    public R60 j;
    public byte k;

    public V80() {
        this.k = (byte) -1;
        this.e = XmlPullParser.NO_NAMESPACE;
        this.f = XmlPullParser.NO_NAMESPACE;
        this.g = XmlPullParser.NO_NAMESPACE;
    }

    @Override // com.android.tools.r8.internal.TN
    public final void a(AbstractC0793Rd abstractC0793Rd) throws C0741Pd {
        if (!AbstractC2209ns.a(this.e)) {
            AbstractC2209ns.a(abstractC0793Rd, 1, this.e);
        }
        if (!AbstractC2209ns.a(this.f)) {
            AbstractC2209ns.a(abstractC0793Rd, 2, this.f);
        }
        if (!AbstractC2209ns.a(this.g)) {
            AbstractC2209ns.a(abstractC0793Rd, 3, this.g);
        }
        if (this.h != null) {
            abstractC0793Rd.a(4, l());
        }
        int i = this.i;
        if (i != 0) {
            C0689Nd c0689Nd = (C0689Nd) abstractC0793Rd;
            c0689Nd.c(5, 0);
            c0689Nd.f(i);
        }
        if (this.j != null) {
            abstractC0793Rd.a(6, k());
        }
        this.d.a(abstractC0793Rd);
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return l;
    }

    @Override // com.android.tools.r8.internal.TN
    public final int c() {
        int i = this.c;
        if (i != -1) {
            return i;
        }
        int iA = !AbstractC2209ns.a(this.e) ? AbstractC2209ns.a(1, this.e) : 0;
        if (!AbstractC2209ns.a(this.f)) {
            iA += AbstractC2209ns.a(2, this.f);
        }
        if (!AbstractC2209ns.a(this.g)) {
            iA += AbstractC2209ns.a(3, this.g);
        }
        if (this.h != null) {
            iA += AbstractC0793Rd.a(l()) + AbstractC0793Rd.b(4);
        }
        int i2 = this.i;
        if (i2 != 0) {
            iA = AbstractC0484Ff.a(i2, AbstractC0793Rd.b(5), iA);
        }
        if (this.j != null) {
            iA += AbstractC0793Rd.a(k()) + AbstractC0793Rd.b(6);
        }
        int iC = this.d.c() + iA;
        this.c = iC;
        return iC;
    }

    @Override // com.android.tools.r8.internal.TN
    public final SN d() {
        return this == l ? new U80() : new U80().a(this);
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
        if (!(obj instanceof V80)) {
            return super.equals(obj);
        }
        V80 v80 = (V80) obj;
        Object obj2 = this.e;
        if (obj2 instanceof String) {
            strC = (String) obj2;
        } else {
            strC = ((U7) obj2).c();
            this.e = strC;
        }
        Object obj3 = v80.e;
        if (obj3 instanceof String) {
            strC2 = (String) obj3;
        } else {
            strC2 = ((U7) obj3).c();
            v80.e = strC2;
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
        Object obj5 = v80.f;
        if (obj5 instanceof String) {
            strC4 = (String) obj5;
        } else {
            strC4 = ((U7) obj5).c();
            v80.f = strC4;
        }
        if (!strC3.equals(strC4) || !m().equals(v80.m())) {
            return false;
        }
        S70 s70 = this.h;
        if ((s70 != null) != (v80.h != null)) {
            return false;
        }
        if ((s70 == null || l().equals(v80.l())) && this.i == v80.i && n() == v80.n()) {
            return (!n() || k().equals(v80.k())) && this.d.equals(v80.d);
        }
        return false;
    }

    @Override // com.android.tools.r8.internal.WN
    public final C2712tk0 g() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.J0
    public final H0 h() {
        l.getClass();
        return new U80();
    }

    @Override // com.android.tools.r8.internal.J0
    public final int hashCode() {
        String strC;
        String strC2;
        int i = this.b;
        if (i != 0) {
            return i;
        }
        int iA = AbstractC0432Df.a(AbstractC1468f90.M0, 779, 37, 1, 53);
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
        int iHashCode2 = m().hashCode() + ((((strC2.hashCode() + iHashCode) * 37) + 3) * 53);
        if (this.h != null) {
            iHashCode2 = l().hashCode() + Z50.a(iHashCode2, 37, 4, 53);
        }
        int iA2 = Z50.a(iHashCode2, 37, 5, 53) + this.i;
        if (n()) {
            iA2 = Z50.a(iA2, 37, 6, 53) + k().hashCode();
        }
        int iHashCode3 = this.d.hashCode() + (iA2 * 29);
        this.b = iHashCode3;
        return iHashCode3;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final C2123ms j() {
        return AbstractC1468f90.N0.a(V80.class, U80.class);
    }

    public final R60 k() {
        R60 r60 = this.j;
        return r60 == null ? R60.h : r60;
    }

    public final S70 l() {
        S70 s70 = this.h;
        return s70 == null ? S70.h : s70;
    }

    public final String m() {
        Object obj = this.g;
        if (obj instanceof String) {
            return (String) obj;
        }
        String strC = ((U7) obj).c();
        this.g = strC;
        return strC;
    }

    public final boolean n() {
        return this.j != null;
    }

    public V80(AbstractC0911Vr abstractC0911Vr) {
        super(abstractC0911Vr);
        this.k = (byte) -1;
    }

    @Override // com.android.tools.r8.internal.VN
    public final boolean a() {
        byte b = this.k;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        this.k = (byte) 1;
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final H0 a(C0859Tr c0859Tr) {
        return new U80(c0859Tr);
    }
}
