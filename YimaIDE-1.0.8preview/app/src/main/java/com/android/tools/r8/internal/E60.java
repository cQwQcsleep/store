package com.android.tools.r8.internal;

import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class E60 extends AbstractC2209ns {
    public static final E60 m = new E60();
    public static final C60 n = new C60();
    public H60 e;
    public volatile Object f;
    public S80 g;
    public C1208c60 h;
    public C1550g70 i;
    public List j;
    public V70 k;
    public byte l;

    public E60() {
        this.l = (byte) -1;
        this.f = XmlPullParser.NO_NAMESPACE;
        this.j = Collections.EMPTY_LIST;
    }

    @Override // com.android.tools.r8.internal.TN
    public final void a(AbstractC0793Rd abstractC0793Rd) {
        if (this.e != null) {
            abstractC0793Rd.a(1, l());
        }
        if (!AbstractC2209ns.a(this.f)) {
            AbstractC2209ns.a(abstractC0793Rd, 2, this.f);
        }
        if (this.g != null) {
            abstractC0793Rd.a(3, p());
        }
        if (this.h != null) {
            abstractC0793Rd.a(4, k());
        }
        if (this.i != null) {
            abstractC0793Rd.a(5, n());
        }
        for (int i = 0; i < this.j.size(); i++) {
            abstractC0793Rd.a(6, (TN) this.j.get(i));
        }
        if (this.k != null) {
            abstractC0793Rd.a(7, o());
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
        int iA = this.e != null ? AbstractC0793Rd.a(l()) + AbstractC0793Rd.b(1) : 0;
        if (!AbstractC2209ns.a(this.f)) {
            iA += AbstractC2209ns.a(2, this.f);
        }
        if (this.g != null) {
            iA += AbstractC0793Rd.a(p()) + AbstractC0793Rd.b(3);
        }
        if (this.h != null) {
            iA += AbstractC0793Rd.a(k()) + AbstractC0793Rd.b(4);
        }
        if (this.i != null) {
            iA += AbstractC0793Rd.a(n()) + AbstractC0793Rd.b(5);
        }
        for (int i2 = 0; i2 < this.j.size(); i2++) {
            iA = AbstractC1292d60.a((TN) this.j.get(i2), AbstractC0793Rd.b(6), iA);
        }
        if (this.k != null) {
            iA += AbstractC0793Rd.a(o()) + AbstractC0793Rd.b(7);
        }
        int iC = this.d.c() + iA;
        this.c = iC;
        return iC;
    }

    @Override // com.android.tools.r8.internal.TN
    public final SN d() {
        return this == m ? new D60() : new D60().a(this);
    }

    @Override // com.android.tools.r8.internal.J0
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof E60)) {
            return super.equals(obj);
        }
        E60 e60 = (E60) obj;
        H60 h60 = this.e;
        if ((h60 != null) != (e60.e != null)) {
            return false;
        }
        if ((h60 != null && !l().equals(e60.l())) || !m().equals(e60.m())) {
            return false;
        }
        S80 s80 = this.g;
        if ((s80 != null) != (e60.g != null)) {
            return false;
        }
        if (s80 != null && !p().equals(e60.p())) {
            return false;
        }
        C1208c60 c1208c60 = this.h;
        if ((c1208c60 != null) != (e60.h != null)) {
            return false;
        }
        if ((c1208c60 != null && !k().equals(e60.k())) || q() != e60.q()) {
            return false;
        }
        if ((q() && !n().equals(e60.n())) || !this.j.equals(e60.j)) {
            return false;
        }
        V70 v70 = this.k;
        if ((v70 != null) != (e60.k != null)) {
            return false;
        }
        return (v70 == null || o().equals(e60.o())) && this.d.equals(e60.d);
    }

    @Override // com.android.tools.r8.internal.WN
    public final C2712tk0 g() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.J0
    public final H0 h() {
        m.getClass();
        return new D60();
    }

    @Override // com.android.tools.r8.internal.J0
    public final int hashCode() {
        int i = this.b;
        if (i != 0) {
            return i;
        }
        int iHashCode = AbstractC1468f90.G.hashCode() + 779;
        if (this.e != null) {
            iHashCode = Z50.a(iHashCode, 37, 1, 53) + l().hashCode();
        }
        int iHashCode2 = m().hashCode() + Z50.a(iHashCode, 37, 2, 53);
        if (this.g != null) {
            iHashCode2 = p().hashCode() + Z50.a(iHashCode2, 37, 3, 53);
        }
        if (this.h != null) {
            iHashCode2 = k().hashCode() + Z50.a(iHashCode2, 37, 4, 53);
        }
        if (q()) {
            iHashCode2 = n().hashCode() + Z50.a(iHashCode2, 37, 5, 53);
        }
        if (this.j.size() > 0) {
            iHashCode2 = this.j.hashCode() + Z50.a(iHashCode2, 37, 6, 53);
        }
        if (this.k != null) {
            iHashCode2 = o().hashCode() + Z50.a(iHashCode2, 37, 7, 53);
        }
        int iHashCode3 = this.d.hashCode() + (iHashCode2 * 29);
        this.b = iHashCode3;
        return iHashCode3;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final C2123ms j() {
        return AbstractC1468f90.H.a(E60.class, D60.class);
    }

    public final C1208c60 k() {
        C1208c60 c1208c60 = this.h;
        return c1208c60 == null ? C1208c60.h : c1208c60;
    }

    public final H60 l() {
        H60 h60 = this.e;
        return h60 == null ? H60.g : h60;
    }

    public final String m() {
        Object obj = this.f;
        if (obj instanceof String) {
            return (String) obj;
        }
        String strC = ((U7) obj).c();
        this.f = strC;
        return strC;
    }

    public final C1550g70 n() {
        C1550g70 c1550g70 = this.i;
        return c1550g70 == null ? C1550g70.l : c1550g70;
    }

    public final V70 o() {
        V70 v70 = this.k;
        return v70 == null ? V70.h : v70;
    }

    public final S80 p() {
        S80 s80 = this.g;
        return s80 == null ? S80.j : s80;
    }

    public final boolean q() {
        return this.i != null;
    }

    public E60(AbstractC0911Vr abstractC0911Vr) {
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
        this.l = (byte) 1;
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final H0 a(C0859Tr c0859Tr) {
        return new D60(c0859Tr);
    }
}
