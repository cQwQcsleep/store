package com.android.tools.r8.internal;

import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Fi, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0487Fi extends AbstractC2209ns {
    public static final C0487Fi q = new C0487Fi();
    public static final C3049xi r = new C3049xi();
    public int e;
    public volatile Object f;
    public List g;
    public List h;
    public List i;
    public List j;
    public List k;
    public List l;
    public C2368pj m;
    public List n;
    public AJ o;
    public byte p;

    public C0487Fi() {
        this.p = (byte) -1;
        this.f = XmlPullParser.NO_NAMESPACE;
        List list = Collections.EMPTY_LIST;
        this.g = list;
        this.h = list;
        this.i = list;
        this.j = list;
        this.k = list;
        this.l = list;
        this.n = list;
        this.o = C3101yJ.d;
    }

    @Override // com.android.tools.r8.internal.TN
    public final void a(AbstractC0793Rd abstractC0793Rd) {
        if ((this.e & 1) != 0) {
            AbstractC2209ns.a(abstractC0793Rd, 1, this.f);
        }
        for (int i = 0; i < this.g.size(); i++) {
            abstractC0793Rd.a(2, (TN) this.g.get(i));
        }
        for (int i2 = 0; i2 < this.i.size(); i2++) {
            abstractC0793Rd.a(3, (TN) this.i.get(i2));
        }
        for (int i3 = 0; i3 < this.j.size(); i3++) {
            abstractC0793Rd.a(4, (TN) this.j.get(i3));
        }
        for (int i4 = 0; i4 < this.k.size(); i4++) {
            abstractC0793Rd.a(5, (TN) this.k.get(i4));
        }
        for (int i5 = 0; i5 < this.h.size(); i5++) {
            abstractC0793Rd.a(6, (TN) this.h.get(i5));
        }
        if ((this.e & 2) != 0) {
            abstractC0793Rd.a(7, l());
        }
        for (int i6 = 0; i6 < this.l.size(); i6++) {
            abstractC0793Rd.a(8, (TN) this.l.get(i6));
        }
        for (int i7 = 0; i7 < this.n.size(); i7++) {
            abstractC0793Rd.a(9, (TN) this.n.get(i7));
        }
        for (int i8 = 0; i8 < this.o.size(); i8++) {
            AbstractC2209ns.a(abstractC0793Rd, 10, this.o.e(i8));
        }
        this.d.a(abstractC0793Rd);
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return q;
    }

    @Override // com.android.tools.r8.internal.TN
    public final int c() {
        int i = this.c;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        int iA = (this.e & 1) != 0 ? AbstractC2209ns.a(1, this.f) : 0;
        for (int i3 = 0; i3 < this.g.size(); i3++) {
            iA = AbstractC1292d60.a((TN) this.g.get(i3), AbstractC0793Rd.b(2), iA);
        }
        for (int i4 = 0; i4 < this.i.size(); i4++) {
            iA = AbstractC1292d60.a((TN) this.i.get(i4), AbstractC0793Rd.b(3), iA);
        }
        for (int i5 = 0; i5 < this.j.size(); i5++) {
            iA = AbstractC1292d60.a((TN) this.j.get(i5), AbstractC0793Rd.b(4), iA);
        }
        for (int i6 = 0; i6 < this.k.size(); i6++) {
            iA = AbstractC1292d60.a((TN) this.k.get(i6), AbstractC0793Rd.b(5), iA);
        }
        for (int i7 = 0; i7 < this.h.size(); i7++) {
            iA = AbstractC1292d60.a((TN) this.h.get(i7), AbstractC0793Rd.b(6), iA);
        }
        if ((this.e & 2) != 0) {
            iA += AbstractC0793Rd.a(l()) + AbstractC0793Rd.b(7);
        }
        for (int i8 = 0; i8 < this.l.size(); i8++) {
            iA = AbstractC1292d60.a((TN) this.l.get(i8), AbstractC0793Rd.b(8), iA);
        }
        for (int i9 = 0; i9 < this.n.size(); i9++) {
            iA = AbstractC1292d60.a((TN) this.n.get(i9), AbstractC0793Rd.b(9), iA);
        }
        int iA2 = 0;
        while (true) {
            int size = this.o.size();
            AJ aj = this.o;
            if (i2 >= size) {
                int iC = this.d.c() + aj.size() + iA + iA2;
                this.c = iC;
                return iC;
            }
            Object objE = aj.e(i2);
            iA2 += objE instanceof String ? AbstractC0793Rd.a((String) objE) : AbstractC0793Rd.a((U7) objE);
            i2++;
        }
    }

    @Override // com.android.tools.r8.internal.J0
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C0487Fi)) {
            return super.equals(obj);
        }
        C0487Fi c0487Fi = (C0487Fi) obj;
        int i = this.e & 1;
        if ((i != 0) != ((c0487Fi.e & 1) != 0)) {
            return false;
        }
        if ((i == 0 || k().equals(c0487Fi.k())) && this.g.equals(c0487Fi.g) && this.h.equals(c0487Fi.h) && this.i.equals(c0487Fi.i) && this.j.equals(c0487Fi.j) && this.k.equals(c0487Fi.k) && this.l.equals(c0487Fi.l) && m() == c0487Fi.m()) {
            return (!m() || l().equals(c0487Fi.l())) && this.n.equals(c0487Fi.n) && this.o.equals(c0487Fi.o) && this.d.equals(c0487Fi.d);
        }
        return false;
    }

    @Override // com.android.tools.r8.internal.WN
    public final C2712tk0 g() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.J0
    public final H0 h() {
        return q.d();
    }

    @Override // com.android.tools.r8.internal.J0
    public final int hashCode() {
        int i = this.b;
        if (i != 0) {
            return i;
        }
        int iHashCode = AbstractC0877Uj.c.hashCode() + 779;
        if ((this.e & 1) != 0) {
            iHashCode = Z50.a(iHashCode, 37, 1, 53) + k().hashCode();
        }
        if (this.g.size() > 0) {
            iHashCode = Z50.a(iHashCode, 37, 2, 53) + this.g.hashCode();
        }
        if (this.h.size() > 0) {
            iHashCode = Z50.a(iHashCode, 37, 6, 53) + this.h.hashCode();
        }
        if (this.i.size() > 0) {
            iHashCode = Z50.a(iHashCode, 37, 3, 53) + this.i.hashCode();
        }
        if (this.j.size() > 0) {
            iHashCode = Z50.a(iHashCode, 37, 4, 53) + this.j.hashCode();
        }
        if (this.k.size() > 0) {
            iHashCode = Z50.a(iHashCode, 37, 5, 53) + this.k.hashCode();
        }
        if (this.l.size() > 0) {
            iHashCode = Z50.a(iHashCode, 37, 8, 53) + this.l.hashCode();
        }
        if (m()) {
            iHashCode = Z50.a(iHashCode, 37, 7, 53) + l().hashCode();
        }
        if (this.n.size() > 0) {
            iHashCode = Z50.a(iHashCode, 37, 9, 53) + this.n.hashCode();
        }
        if (this.o.size() > 0) {
            iHashCode = Z50.a(iHashCode, 37, 10, 53) + this.o.hashCode();
        }
        int iHashCode2 = this.d.hashCode() + (iHashCode * 29);
        this.b = iHashCode2;
        return iHashCode2;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final C2123ms j() {
        return AbstractC0877Uj.d.a(C0487Fi.class, C3133yi.class);
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

    public final C2368pj l() {
        C2368pj c2368pj = this.m;
        return c2368pj == null ? C2368pj.m : c2368pj;
    }

    public final boolean m() {
        return (this.e & 2) != 0;
    }

    @Override // com.android.tools.r8.internal.TN
    /* JADX INFO: renamed from: n, reason: merged with bridge method [inline-methods] */
    public final C3133yi d() {
        return this == q ? new C3133yi() : new C3133yi().a(this);
    }

    public C0487Fi(AbstractC0911Vr abstractC0911Vr) {
        super(abstractC0911Vr);
        this.p = (byte) -1;
    }

    @Override // com.android.tools.r8.internal.VN
    public final boolean a() {
        byte b = this.p;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        for (int i = 0; i < this.g.size(); i++) {
            if (!((C1258cj) this.g.get(i)).a()) {
                this.p = (byte) 0;
                return false;
            }
        }
        for (int i2 = 0; i2 < this.h.size(); i2++) {
            if (!((C1258cj) this.h.get(i2)).a()) {
                this.p = (byte) 0;
                return false;
            }
        }
        for (int i3 = 0; i3 < this.i.size(); i3++) {
            if (!((C0487Fi) this.i.get(i3)).a()) {
                this.p = (byte) 0;
                return false;
            }
        }
        for (int i4 = 0; i4 < this.j.size(); i4++) {
            if (!((C0643Li) this.j.get(i4)).a()) {
                this.p = (byte) 0;
                return false;
            }
        }
        for (int i5 = 0; i5 < this.k.size(); i5++) {
            if (!((C0383Bi) this.k.get(i5)).a()) {
                this.p = (byte) 0;
                return false;
            }
        }
        for (int i6 = 0; i6 < this.l.size(); i6++) {
            if (!((C3135yj) this.l.get(i6)).a()) {
                this.p = (byte) 0;
                return false;
            }
        }
        if (m() && !l().a()) {
            this.p = (byte) 0;
            return false;
        }
        this.p = (byte) 1;
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final H0 a(C0859Tr c0859Tr) {
        return new C3133yi(c0859Tr);
    }
}
