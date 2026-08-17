package com.android.tools.r8.internal;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ij, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1769ij extends AbstractC2209ns {
    public static final C1769ij s = new C1769ij();
    public static final C1598gj t = new C1598gj();
    public int e;
    public volatile Object f;
    public volatile Object g;
    public AJ h;
    public InterfaceC1216cB i;
    public InterfaceC1216cB j;
    public List k;
    public List l;
    public List m;
    public List n;
    public C2110mj o;
    public C0695Nj p;
    public volatile Object q;
    public byte r;

    public C1769ij() {
        this.r = (byte) -1;
        this.f = XmlPullParser.NO_NAMESPACE;
        this.g = XmlPullParser.NO_NAMESPACE;
        this.h = C3101yJ.d;
        C0945Wz c0945Wz = C0945Wz.e;
        this.i = c0945Wz;
        this.j = c0945Wz;
        List list = Collections.EMPTY_LIST;
        this.k = list;
        this.l = list;
        this.m = list;
        this.n = list;
        this.q = XmlPullParser.NO_NAMESPACE;
    }

    @Override // com.android.tools.r8.internal.TN
    public final void a(AbstractC0793Rd abstractC0793Rd) {
        if ((this.e & 1) != 0) {
            AbstractC2209ns.a(abstractC0793Rd, 1, this.f);
        }
        if ((this.e & 2) != 0) {
            AbstractC2209ns.a(abstractC0793Rd, 2, this.g);
        }
        for (int i = 0; i < this.h.size(); i++) {
            AbstractC2209ns.a(abstractC0793Rd, 3, this.h.e(i));
        }
        for (int i2 = 0; i2 < this.k.size(); i2++) {
            abstractC0793Rd.a(4, (TN) this.k.get(i2));
        }
        for (int i3 = 0; i3 < this.l.size(); i3++) {
            abstractC0793Rd.a(5, (TN) this.l.get(i3));
        }
        for (int i4 = 0; i4 < this.m.size(); i4++) {
            abstractC0793Rd.a(6, (TN) this.m.get(i4));
        }
        for (int i5 = 0; i5 < this.n.size(); i5++) {
            abstractC0793Rd.a(7, (TN) this.n.get(i5));
        }
        if ((this.e & 4) != 0) {
            abstractC0793Rd.a(8, l());
        }
        if ((this.e & 8) != 0) {
            abstractC0793Rd.a(9, n());
        }
        int i6 = 0;
        while (true) {
            C0945Wz c0945Wz = (C0945Wz) this.i;
            if (i6 >= c0945Wz.d) {
                break;
            }
            c0945Wz.k(i6);
            int i7 = c0945Wz.c[i6];
            C0689Nd c0689Nd = (C0689Nd) abstractC0793Rd;
            c0689Nd.c(10, 0);
            c0689Nd.e(i7);
            i6++;
        }
        int i8 = 0;
        while (true) {
            C0945Wz c0945Wz2 = (C0945Wz) this.j;
            if (i8 >= c0945Wz2.d) {
                break;
            }
            c0945Wz2.k(i8);
            int i9 = c0945Wz2.c[i8];
            C0689Nd c0689Nd2 = (C0689Nd) abstractC0793Rd;
            c0689Nd2.c(11, 0);
            c0689Nd2.e(i9);
            i8++;
        }
        if ((this.e & 16) != 0) {
            AbstractC2209ns.a(abstractC0793Rd, 12, this.q);
        }
        this.d.a(abstractC0793Rd);
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return s;
    }

    @Override // com.android.tools.r8.internal.TN
    public final int c() {
        AJ aj;
        int i;
        int i2;
        int iC;
        int i3 = this.c;
        if (i3 != -1) {
            return i3;
        }
        int i4 = 0;
        int iA = (this.e & 1) != 0 ? AbstractC2209ns.a(1, this.f) : 0;
        if ((this.e & 2) != 0) {
            iA += AbstractC2209ns.a(2, this.g);
        }
        int i5 = 0;
        int iA2 = 0;
        while (true) {
            int size = this.h.size();
            aj = this.h;
            if (i5 >= size) {
                break;
            }
            Object objE = aj.e(i5);
            iA2 += objE instanceof String ? AbstractC0793Rd.a((String) objE) : AbstractC0793Rd.a((U7) objE);
            i5++;
        }
        int size2 = aj.size() + iA + iA2;
        for (int i6 = 0; i6 < this.k.size(); i6++) {
            size2 = AbstractC1292d60.a((TN) this.k.get(i6), AbstractC0793Rd.b(4), size2);
        }
        for (int i7 = 0; i7 < this.l.size(); i7++) {
            size2 = AbstractC1292d60.a((TN) this.l.get(i7), AbstractC0793Rd.b(5), size2);
        }
        for (int i8 = 0; i8 < this.m.size(); i8++) {
            size2 = AbstractC1292d60.a((TN) this.m.get(i8), AbstractC0793Rd.b(6), size2);
        }
        for (int i9 = 0; i9 < this.n.size(); i9++) {
            size2 = AbstractC1292d60.a((TN) this.n.get(i9), AbstractC0793Rd.b(7), size2);
        }
        if ((this.e & 4) != 0) {
            size2 += AbstractC0793Rd.a(l()) + AbstractC0793Rd.b(8);
        }
        if ((this.e & 8) != 0) {
            size2 += AbstractC0793Rd.a(n()) + AbstractC0793Rd.b(9);
        }
        int i10 = 0;
        int i11 = 0;
        while (true) {
            C0945Wz c0945Wz = (C0945Wz) this.i;
            i = c0945Wz.d;
            int iC2 = 10;
            if (i10 >= i) {
                break;
            }
            c0945Wz.k(i10);
            int i12 = c0945Wz.c[i10];
            if (i12 >= 0) {
                iC2 = AbstractC0793Rd.c(i12);
            } else {
                Logger logger = AbstractC0793Rd.a;
            }
            i11 += iC2;
            i10++;
        }
        int i13 = size2 + i11 + i;
        int i14 = 0;
        while (true) {
            C0945Wz c0945Wz2 = (C0945Wz) this.j;
            i2 = c0945Wz2.d;
            if (i4 >= i2) {
                break;
            }
            c0945Wz2.k(i4);
            int i15 = c0945Wz2.c[i4];
            if (i15 >= 0) {
                iC = AbstractC0793Rd.c(i15);
            } else {
                Logger logger2 = AbstractC0793Rd.a;
                iC = 10;
            }
            i14 += iC;
            i4++;
        }
        int iA3 = i13 + i14 + i2;
        if ((this.e & 16) != 0) {
            iA3 += AbstractC2209ns.a(12, this.q);
        }
        int iC3 = this.d.c() + iA3;
        this.c = iC3;
        return iC3;
    }

    @Override // com.android.tools.r8.internal.J0
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C1769ij)) {
            return super.equals(obj);
        }
        C1769ij c1769ij = (C1769ij) obj;
        int i = this.e & 1;
        if ((i != 0) != ((c1769ij.e & 1) != 0)) {
            return false;
        }
        if (i != 0 && !k().equals(c1769ij.k())) {
            return false;
        }
        int i2 = this.e & 2;
        if ((i2 != 0) != ((c1769ij.e & 2) != 0)) {
            return false;
        }
        if ((i2 != 0 && !m().equals(c1769ij.m())) || !this.h.equals(c1769ij.h)) {
            return false;
        }
        if (!((C0945Wz) this.i).equals(c1769ij.i)) {
            return false;
        }
        if (!((C0945Wz) this.j).equals(c1769ij.j) || !this.k.equals(c1769ij.k) || !this.l.equals(c1769ij.l) || !this.m.equals(c1769ij.m) || !this.n.equals(c1769ij.n) || p() != c1769ij.p()) {
            return false;
        }
        if (p() && !l().equals(c1769ij.l())) {
            return false;
        }
        int i3 = this.e & 8;
        if ((i3 != 0) != ((c1769ij.e & 8) != 0)) {
            return false;
        }
        if (i3 != 0 && !n().equals(c1769ij.n())) {
            return false;
        }
        int i4 = this.e & 16;
        if ((i4 != 0) != ((c1769ij.e & 16) != 0)) {
            return false;
        }
        return (i4 == 0 || o().equals(c1769ij.o())) && this.d.equals(c1769ij.d);
    }

    @Override // com.android.tools.r8.internal.WN
    public final C2712tk0 g() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.J0
    public final H0 h() {
        return s.d();
    }

    @Override // com.android.tools.r8.internal.J0
    public final int hashCode() {
        int i = this.b;
        if (i != 0) {
            return i;
        }
        int iHashCode = AbstractC0877Uj.a.hashCode() + 779;
        if ((this.e & 1) != 0) {
            iHashCode = Z50.a(iHashCode, 37, 1, 53) + k().hashCode();
        }
        if ((this.e & 2) != 0) {
            iHashCode = Z50.a(iHashCode, 37, 2, 53) + m().hashCode();
        }
        if (this.h.size() > 0) {
            iHashCode = Z50.a(iHashCode, 37, 3, 53) + this.h.hashCode();
        }
        C0945Wz c0945Wz = (C0945Wz) this.i;
        if (c0945Wz.d > 0) {
            iHashCode = Z50.a(iHashCode, 37, 10, 53) + c0945Wz.hashCode();
        }
        C0945Wz c0945Wz2 = (C0945Wz) this.j;
        if (c0945Wz2.d > 0) {
            iHashCode = Z50.a(iHashCode, 37, 11, 53) + c0945Wz2.hashCode();
        }
        if (this.k.size() > 0) {
            iHashCode = Z50.a(iHashCode, 37, 4, 53) + this.k.hashCode();
        }
        if (this.l.size() > 0) {
            iHashCode = Z50.a(iHashCode, 37, 5, 53) + this.l.hashCode();
        }
        if (this.m.size() > 0) {
            iHashCode = Z50.a(iHashCode, 37, 6, 53) + this.m.hashCode();
        }
        if (this.n.size() > 0) {
            iHashCode = Z50.a(iHashCode, 37, 7, 53) + this.n.hashCode();
        }
        if (p()) {
            iHashCode = Z50.a(iHashCode, 37, 8, 53) + l().hashCode();
        }
        if ((this.e & 8) != 0) {
            iHashCode = Z50.a(iHashCode, 37, 9, 53) + n().hashCode();
        }
        if ((this.e & 16) != 0) {
            iHashCode = Z50.a(iHashCode, 37, 12, 53) + o().hashCode();
        }
        int iHashCode2 = this.d.hashCode() + (iHashCode * 29);
        this.b = iHashCode2;
        return iHashCode2;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final C2123ms j() {
        return AbstractC0877Uj.b.a(C1769ij.class, C1684hj.class);
    }

    public final String k() {
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

    public final C2110mj l() {
        C2110mj c2110mj = this.o;
        return c2110mj == null ? C2110mj.C : c2110mj;
    }

    public final String m() {
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

    public final C0695Nj n() {
        C0695Nj c0695Nj = this.p;
        return c0695Nj == null ? C0695Nj.g : c0695Nj;
    }

    public final String o() {
        Object obj = this.q;
        if (obj instanceof String) {
            return (String) obj;
        }
        U7 u7 = (U7) obj;
        String strC = u7.c();
        if (u7.a()) {
            this.q = strC;
        }
        return strC;
    }

    public final boolean p() {
        return (this.e & 4) != 0;
    }

    @Override // com.android.tools.r8.internal.TN
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public final C1684hj d() {
        return this == s ? new C1684hj() : new C1684hj().a(this);
    }

    public C1769ij(AbstractC0911Vr abstractC0911Vr) {
        super(abstractC0911Vr);
        this.r = (byte) -1;
    }

    @Override // com.android.tools.r8.internal.VN
    public final boolean a() {
        byte b = this.r;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        for (int i = 0; i < this.k.size(); i++) {
            if (!((C0487Fi) this.k.get(i)).a()) {
                this.r = (byte) 0;
                return false;
            }
        }
        for (int i2 = 0; i2 < this.l.size(); i2++) {
            if (!((C0643Li) this.l.get(i2)).a()) {
                this.r = (byte) 0;
                return false;
            }
        }
        for (int i3 = 0; i3 < this.m.size(); i3++) {
            if (!((C0462Ej) this.m.get(i3)).a()) {
                this.r = (byte) 0;
                return false;
            }
        }
        for (int i4 = 0; i4 < this.n.size(); i4++) {
            if (!((C1258cj) this.n.get(i4)).a()) {
                this.r = (byte) 0;
                return false;
            }
        }
        if (p() && !l().a()) {
            this.r = (byte) 0;
            return false;
        }
        this.r = (byte) 1;
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final H0 a(C0859Tr c0859Tr) {
        return new C1684hj(c0859Tr);
    }
}
