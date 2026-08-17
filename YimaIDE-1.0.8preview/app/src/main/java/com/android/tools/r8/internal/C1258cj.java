package com.android.tools.r8.internal;

import com.sun.jna.platform.linux.Fcntl;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.cj, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1258cj extends AbstractC2209ns {
    public static final C1258cj r = new C1258cj();
    public static final C0980Yi s = new C0980Yi();
    public int e;
    public volatile Object f;
    public int g;
    public int h;
    public int i;
    public volatile Object j;
    public volatile Object k;
    public volatile Object l;
    public int m;
    public volatile Object n;
    public C1513fj o;
    public boolean p;
    public byte q;

    public C1258cj() {
        this.q = (byte) -1;
        this.f = XmlPullParser.NO_NAMESPACE;
        this.h = 1;
        this.i = 1;
        this.j = XmlPullParser.NO_NAMESPACE;
        this.k = XmlPullParser.NO_NAMESPACE;
        this.l = XmlPullParser.NO_NAMESPACE;
        this.n = XmlPullParser.NO_NAMESPACE;
    }

    @Override // com.android.tools.r8.internal.TN
    public final void a(AbstractC0793Rd abstractC0793Rd) {
        if ((this.e & 1) != 0) {
            AbstractC2209ns.a(abstractC0793Rd, 1, this.f);
        }
        if ((this.e & 32) != 0) {
            AbstractC2209ns.a(abstractC0793Rd, 2, this.k);
        }
        if ((this.e & 2) != 0) {
            int i = this.g;
            C0689Nd c0689Nd = (C0689Nd) abstractC0793Rd;
            c0689Nd.c(3, 0);
            c0689Nd.e(i);
        }
        if ((this.e & 4) != 0) {
            abstractC0793Rd.b(4, this.h);
        }
        if ((this.e & 8) != 0) {
            abstractC0793Rd.b(5, this.i);
        }
        if ((this.e & 16) != 0) {
            AbstractC2209ns.a(abstractC0793Rd, 6, this.j);
        }
        if ((this.e & 64) != 0) {
            AbstractC2209ns.a(abstractC0793Rd, 7, this.l);
        }
        if ((this.e & 512) != 0) {
            abstractC0793Rd.a(8, n());
        }
        if ((this.e & 128) != 0) {
            int i2 = this.m;
            C0689Nd c0689Nd2 = (C0689Nd) abstractC0793Rd;
            c0689Nd2.c(9, 0);
            c0689Nd2.e(i2);
        }
        if ((this.e & Fcntl.S_IRUSR) != 0) {
            AbstractC2209ns.a(abstractC0793Rd, 10, this.n);
        }
        if ((this.e & Fcntl.S_ISGID) != 0) {
            abstractC0793Rd.a(17, this.p);
        }
        this.d.a(abstractC0793Rd);
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return r;
    }

    @Override // com.android.tools.r8.internal.TN
    public final int c() {
        int i = this.c;
        if (i != -1) {
            return i;
        }
        int iA = (this.e & 1) != 0 ? AbstractC2209ns.a(1, this.f) : 0;
        if ((this.e & 32) != 0) {
            iA += AbstractC2209ns.a(2, this.k);
        }
        if ((this.e & 2) != 0) {
            iA += AbstractC0793Rd.a(3, this.g);
        }
        if ((this.e & 4) != 0) {
            iA = AbstractC0458Ef.a(this.h, AbstractC0793Rd.b(4), iA);
        }
        if ((this.e & 8) != 0) {
            iA = AbstractC0458Ef.a(this.i, AbstractC0793Rd.b(5), iA);
        }
        if ((this.e & 16) != 0) {
            iA += AbstractC2209ns.a(6, this.j);
        }
        if ((this.e & 64) != 0) {
            iA += AbstractC2209ns.a(7, this.l);
        }
        if ((this.e & 512) != 0) {
            iA += AbstractC0793Rd.a(n()) + AbstractC0793Rd.b(8);
        }
        if ((this.e & 128) != 0) {
            iA += AbstractC0793Rd.a(9, this.m);
        }
        if ((this.e & Fcntl.S_IRUSR) != 0) {
            iA += AbstractC2209ns.a(10, this.n);
        }
        if ((this.e & Fcntl.S_ISGID) != 0) {
            iA = V60.a(17, 1, iA);
        }
        int iC = this.d.c() + iA;
        this.c = iC;
        return iC;
    }

    @Override // com.android.tools.r8.internal.TN
    public final SN d() {
        return this == r ? new C1006Zi() : new C1006Zi().a(this);
    }

    @Override // com.android.tools.r8.internal.J0
    public final boolean equals(Object obj) {
        String str;
        String str2;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C1258cj)) {
            return super.equals(obj);
        }
        C1258cj c1258cj = (C1258cj) obj;
        int i = this.e & 1;
        if ((i != 0) != ((c1258cj.e & 1) != 0)) {
            return false;
        }
        if (i != 0 && !m().equals(c1258cj.m())) {
            return false;
        }
        int i2 = this.e;
        int i3 = i2 & 2;
        boolean z = i3 != 0;
        int i4 = c1258cj.e;
        if (z != ((i4 & 2) != 0)) {
            return false;
        }
        if (i3 != 0 && this.g != c1258cj.g) {
            return false;
        }
        int i5 = i2 & 4;
        if ((i5 != 0) != ((i4 & 4) != 0)) {
            return false;
        }
        if ((i5 != 0 && this.h != c1258cj.h) || t() != c1258cj.t()) {
            return false;
        }
        if ((t() && this.i != c1258cj.i) || u() != c1258cj.u()) {
            return false;
        }
        if ((u() && !o().equals(c1258cj.o())) || q() != c1258cj.q()) {
            return false;
        }
        if ((q() && !l().equals(c1258cj.l())) || p() != c1258cj.p()) {
            return false;
        }
        if ((p() && !k().equals(c1258cj.k())) || r() != c1258cj.r()) {
            return false;
        }
        if (r() && this.m != c1258cj.m) {
            return false;
        }
        int i6 = this.e & Fcntl.S_IRUSR;
        if ((i6 != 0) != ((c1258cj.e & Fcntl.S_IRUSR) != 0)) {
            return false;
        }
        if (i6 != 0) {
            Object obj2 = this.n;
            if (obj2 instanceof String) {
                str = (String) obj2;
            } else {
                U7 u7 = (U7) obj2;
                String strC = u7.c();
                if (u7.a()) {
                    this.n = strC;
                }
                str = strC;
            }
            Object obj3 = c1258cj.n;
            if (obj3 instanceof String) {
                str2 = (String) obj3;
            } else {
                U7 u8 = (U7) obj3;
                String strC2 = u8.c();
                if (u8.a()) {
                    c1258cj.n = strC2;
                }
                str2 = strC2;
            }
            if (!str.equals(str2)) {
                return false;
            }
        }
        if (s() != c1258cj.s()) {
            return false;
        }
        if (s() && !n().equals(c1258cj.n())) {
            return false;
        }
        int i7 = this.e & Fcntl.S_ISGID;
        if ((i7 != 0) != ((c1258cj.e & Fcntl.S_ISGID) != 0)) {
            return false;
        }
        return (i7 == 0 || this.p == c1258cj.p) && this.d.equals(c1258cj.d);
    }

    @Override // com.android.tools.r8.internal.WN
    public final C2712tk0 g() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.J0
    public final H0 h() {
        r.getClass();
        return new C1006Zi();
    }

    @Override // com.android.tools.r8.internal.J0
    public final int hashCode() {
        String str;
        int i = this.b;
        if (i != 0) {
            return i;
        }
        int iHashCode = AbstractC0877Uj.k.hashCode() + 779;
        if ((this.e & 1) != 0) {
            iHashCode = Z50.a(iHashCode, 37, 1, 53) + m().hashCode();
        }
        int i2 = this.e;
        if ((i2 & 2) != 0) {
            iHashCode = Z50.a(iHashCode, 37, 3, 53) + this.g;
        }
        if ((i2 & 4) != 0) {
            iHashCode = Z50.a(iHashCode, 37, 4, 53) + this.h;
        }
        if (t()) {
            iHashCode = Z50.a(iHashCode, 37, 5, 53) + this.i;
        }
        if (u()) {
            iHashCode = Z50.a(iHashCode, 37, 6, 53) + o().hashCode();
        }
        if (q()) {
            iHashCode = Z50.a(iHashCode, 37, 2, 53) + l().hashCode();
        }
        if (p()) {
            iHashCode = Z50.a(iHashCode, 37, 7, 53) + k().hashCode();
        }
        if (r()) {
            iHashCode = Z50.a(iHashCode, 37, 9, 53) + this.m;
        }
        if ((this.e & Fcntl.S_IRUSR) != 0) {
            int iA = Z50.a(iHashCode, 37, 10, 53);
            Object obj = this.n;
            if (obj instanceof String) {
                str = (String) obj;
            } else {
                U7 u7 = (U7) obj;
                String strC = u7.c();
                if (u7.a()) {
                    this.n = strC;
                }
                str = strC;
            }
            iHashCode = iA + str.hashCode();
        }
        if (s()) {
            iHashCode = Z50.a(iHashCode, 37, 8, 53) + n().hashCode();
        }
        if ((this.e & Fcntl.S_ISGID) != 0) {
            iHashCode = Z50.a(iHashCode, 37, 17, 53) + AbstractC1556gB.a(this.p);
        }
        int iHashCode2 = this.d.hashCode() + (iHashCode * 29);
        this.b = iHashCode2;
        return iHashCode2;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final C2123ms j() {
        return AbstractC0877Uj.l.a(C1258cj.class, C1006Zi.class);
    }

    public final String k() {
        Object obj = this.l;
        if (obj instanceof String) {
            return (String) obj;
        }
        U7 u7 = (U7) obj;
        String strC = u7.c();
        if (u7.a()) {
            this.l = strC;
        }
        return strC;
    }

    public final String l() {
        Object obj = this.k;
        if (obj instanceof String) {
            return (String) obj;
        }
        U7 u7 = (U7) obj;
        String strC = u7.c();
        if (u7.a()) {
            this.k = strC;
        }
        return strC;
    }

    public final String m() {
        Object obj = this.f;
        if (obj instanceof String) {
            return (String) obj;
        }
        U7 u7 = (U7) obj;
        String strC = u7.c();
        if (u7.a()) {
            this.f = strC;
        }
        return strC;
    }

    public final C1513fj n() {
        C1513fj c1513fj = this.o;
        return c1513fj == null ? C1513fj.o : c1513fj;
    }

    public final String o() {
        Object obj = this.j;
        if (obj instanceof String) {
            return (String) obj;
        }
        U7 u7 = (U7) obj;
        String strC = u7.c();
        if (u7.a()) {
            this.j = strC;
        }
        return strC;
    }

    public final boolean p() {
        return (this.e & 64) != 0;
    }

    public final boolean q() {
        return (this.e & 32) != 0;
    }

    public final boolean r() {
        return (this.e & 128) != 0;
    }

    public final boolean s() {
        return (this.e & 512) != 0;
    }

    public final boolean t() {
        return (this.e & 8) != 0;
    }

    public final boolean u() {
        return (this.e & 16) != 0;
    }

    public C1258cj(AbstractC0911Vr abstractC0911Vr) {
        super(abstractC0911Vr);
        this.q = (byte) -1;
    }

    @Override // com.android.tools.r8.internal.VN
    public final boolean a() {
        byte b = this.q;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        if (s() && !n().a()) {
            this.q = (byte) 0;
            return false;
        }
        this.q = (byte) 1;
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final H0 a(C0859Tr c0859Tr) {
        return new C1006Zi(c0859Tr);
    }
}
