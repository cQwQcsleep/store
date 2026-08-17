package com.android.tools.r8.internal;

import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.sj, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2623sj extends AbstractC2209ns {
    public static final C2623sj m = new C2623sj();
    public static final C2453qj n = new C2453qj();
    public int e;
    public volatile Object f;
    public volatile Object g;
    public volatile Object h;
    public C2880vj i;
    public boolean j;
    public boolean k;
    public byte l;

    public C2623sj() {
        this.l = (byte) -1;
        this.f = XmlPullParser.NO_NAMESPACE;
        this.g = XmlPullParser.NO_NAMESPACE;
        this.h = XmlPullParser.NO_NAMESPACE;
    }

    @Override // com.android.tools.r8.internal.TN
    public final void a(AbstractC0793Rd abstractC0793Rd) {
        if ((this.e & 1) != 0) {
            AbstractC2209ns.a(abstractC0793Rd, 1, this.f);
        }
        if ((this.e & 2) != 0) {
            AbstractC2209ns.a(abstractC0793Rd, 2, this.g);
        }
        if ((this.e & 4) != 0) {
            AbstractC2209ns.a(abstractC0793Rd, 3, this.h);
        }
        if ((this.e & 8) != 0) {
            abstractC0793Rd.a(4, m());
        }
        if ((this.e & 16) != 0) {
            abstractC0793Rd.a(5, this.j);
        }
        if ((this.e & 32) != 0) {
            abstractC0793Rd.a(6, this.k);
        }
        this.d.a(abstractC0793Rd);
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return m;
    }

    @Override // com.android.tools.r8.internal.TN
    public final int c() {
        int i = this.c;
        if (i != -1) {
            return i;
        }
        int iA = (this.e & 1) != 0 ? AbstractC2209ns.a(1, this.f) : 0;
        if ((this.e & 2) != 0) {
            iA += AbstractC2209ns.a(2, this.g);
        }
        if ((this.e & 4) != 0) {
            iA += AbstractC2209ns.a(3, this.h);
        }
        if ((this.e & 8) != 0) {
            iA += AbstractC0793Rd.a(m()) + AbstractC0793Rd.b(4);
        }
        if ((this.e & 16) != 0) {
            iA = V60.a(5, 1, iA);
        }
        if ((this.e & 32) != 0) {
            iA = V60.a(6, 1, iA);
        }
        int iC = this.d.c() + iA;
        this.c = iC;
        return iC;
    }

    @Override // com.android.tools.r8.internal.TN
    public final SN d() {
        return this == m ? new C2538rj() : new C2538rj().a(this);
    }

    @Override // com.android.tools.r8.internal.J0
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C2623sj)) {
            return super.equals(obj);
        }
        C2623sj c2623sj = (C2623sj) obj;
        int i = this.e & 1;
        if ((i != 0) != ((c2623sj.e & 1) != 0)) {
            return false;
        }
        if (i != 0 && !l().equals(c2623sj.l())) {
            return false;
        }
        int i2 = this.e & 2;
        if ((i2 != 0) != ((c2623sj.e & 2) != 0)) {
            return false;
        }
        if (i2 != 0 && !k().equals(c2623sj.k())) {
            return false;
        }
        int i3 = this.e & 4;
        if ((i3 != 0) != ((c2623sj.e & 4) != 0)) {
            return false;
        }
        if ((i3 != 0 && !n().equals(c2623sj.n())) || o() != c2623sj.o()) {
            return false;
        }
        if (o() && !m().equals(c2623sj.m())) {
            return false;
        }
        int i4 = this.e;
        int i5 = i4 & 16;
        boolean z = i5 != 0;
        int i6 = c2623sj.e;
        if (z != ((i6 & 16) != 0)) {
            return false;
        }
        if (i5 != 0 && this.j != c2623sj.j) {
            return false;
        }
        int i7 = i4 & 32;
        if ((i7 != 0) != ((i6 & 32) != 0)) {
            return false;
        }
        return (i7 == 0 || this.k == c2623sj.k) && this.d.equals(c2623sj.d);
    }

    @Override // com.android.tools.r8.internal.WN
    public final C2712tk0 g() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.J0
    public final H0 h() {
        m.getClass();
        return new C2538rj();
    }

    @Override // com.android.tools.r8.internal.J0
    public final int hashCode() {
        int i = this.b;
        if (i != 0) {
            return i;
        }
        int iHashCode = AbstractC0877Uj.w.hashCode() + 779;
        if ((this.e & 1) != 0) {
            iHashCode = Z50.a(iHashCode, 37, 1, 53) + l().hashCode();
        }
        if ((this.e & 2) != 0) {
            iHashCode = Z50.a(iHashCode, 37, 2, 53) + k().hashCode();
        }
        if ((this.e & 4) != 0) {
            iHashCode = Z50.a(iHashCode, 37, 3, 53) + n().hashCode();
        }
        if (o()) {
            iHashCode = Z50.a(iHashCode, 37, 4, 53) + m().hashCode();
        }
        if ((this.e & 16) != 0) {
            iHashCode = Z50.a(iHashCode, 37, 5, 53) + AbstractC1556gB.a(this.j);
        }
        if ((this.e & 32) != 0) {
            iHashCode = Z50.a(iHashCode, 37, 6, 53) + AbstractC1556gB.a(this.k);
        }
        int iHashCode2 = this.d.hashCode() + (iHashCode * 29);
        this.b = iHashCode2;
        return iHashCode2;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final C2123ms j() {
        return AbstractC0877Uj.x.a(C2623sj.class, C2538rj.class);
    }

    public final String k() {
        Object obj = this.g;
        if (obj instanceof String) {
            return (String) obj;
        }
        U7 u7 = (U7) obj;
        String strC = u7.c();
        if (u7.a()) {
            this.g = strC;
        }
        return strC;
    }

    public final String l() {
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

    public final C2880vj m() {
        C2880vj c2880vj = this.i;
        return c2880vj == null ? C2880vj.k : c2880vj;
    }

    public final String n() {
        Object obj = this.h;
        if (obj instanceof String) {
            return (String) obj;
        }
        U7 u7 = (U7) obj;
        String strC = u7.c();
        if (u7.a()) {
            this.h = strC;
        }
        return strC;
    }

    public final boolean o() {
        return (this.e & 8) != 0;
    }

    public C2623sj(AbstractC0911Vr abstractC0911Vr) {
        super(abstractC0911Vr);
        this.l = (byte) -1;
    }

    @Override // com.android.tools.r8.internal.VN
    public final boolean a() {
        byte b = this.l;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        if (o() && !m().a()) {
            this.l = (byte) 0;
            return false;
        }
        this.l = (byte) 1;
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final H0 a(C0859Tr c0859Tr) {
        return new C2538rj(c0859Tr);
    }
}
