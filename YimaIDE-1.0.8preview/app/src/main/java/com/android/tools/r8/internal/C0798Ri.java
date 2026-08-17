package com.android.tools.r8.internal;

import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Ri, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0798Ri extends AbstractC2209ns {
    public static final C0798Ri j = new C0798Ri();
    public static final C0746Pi k = new C0746Pi();
    public int e;
    public volatile Object f;
    public int g;
    public C0876Ui h;
    public byte i;

    public C0798Ri() {
        this.i = (byte) -1;
        this.f = XmlPullParser.NO_NAMESPACE;
    }

    @Override // com.android.tools.r8.internal.TN
    public final void a(AbstractC0793Rd abstractC0793Rd) {
        if ((this.e & 1) != 0) {
            AbstractC2209ns.a(abstractC0793Rd, 1, this.f);
        }
        if ((this.e & 2) != 0) {
            int i = this.g;
            C0689Nd c0689Nd = (C0689Nd) abstractC0793Rd;
            c0689Nd.c(2, 0);
            c0689Nd.e(i);
        }
        if ((this.e & 4) != 0) {
            abstractC0793Rd.a(3, l());
        }
        this.d.a(abstractC0793Rd);
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return j;
    }

    @Override // com.android.tools.r8.internal.TN
    public final int c() {
        int i = this.c;
        if (i != -1) {
            return i;
        }
        int iA = (this.e & 1) != 0 ? AbstractC2209ns.a(1, this.f) : 0;
        if ((this.e & 2) != 0) {
            iA += AbstractC0793Rd.a(2, this.g);
        }
        if ((this.e & 4) != 0) {
            iA += AbstractC0793Rd.a(l()) + AbstractC0793Rd.b(3);
        }
        int iC = this.d.c() + iA;
        this.c = iC;
        return iC;
    }

    @Override // com.android.tools.r8.internal.J0
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C0798Ri)) {
            return super.equals(obj);
        }
        C0798Ri c0798Ri = (C0798Ri) obj;
        int i = this.e & 1;
        if ((i != 0) != ((c0798Ri.e & 1) != 0)) {
            return false;
        }
        if (i != 0 && !k().equals(c0798Ri.k())) {
            return false;
        }
        int i2 = this.e & 2;
        if ((i2 != 0) != ((c0798Ri.e & 2) != 0)) {
            return false;
        }
        if ((i2 == 0 || this.g == c0798Ri.g) && m() == c0798Ri.m()) {
            return (!m() || l().equals(c0798Ri.l())) && this.d.equals(c0798Ri.d);
        }
        return false;
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
        int i = this.b;
        if (i != 0) {
            return i;
        }
        int iHashCode = AbstractC0877Uj.s.hashCode() + 779;
        if ((this.e & 1) != 0) {
            iHashCode = Z50.a(iHashCode, 37, 1, 53) + k().hashCode();
        }
        if ((this.e & 2) != 0) {
            iHashCode = Z50.a(iHashCode, 37, 2, 53) + this.g;
        }
        if (m()) {
            iHashCode = Z50.a(iHashCode, 37, 3, 53) + l().hashCode();
        }
        int iHashCode2 = this.d.hashCode() + (iHashCode * 29);
        this.b = iHashCode2;
        return iHashCode2;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final C2123ms j() {
        return AbstractC0877Uj.t.a(C0798Ri.class, C0772Qi.class);
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

    public final C0876Ui l() {
        C0876Ui c0876Ui = this.h;
        return c0876Ui == null ? C0876Ui.j : c0876Ui;
    }

    public final boolean m() {
        return (this.e & 4) != 0;
    }

    @Override // com.android.tools.r8.internal.TN
    /* JADX INFO: renamed from: n, reason: merged with bridge method [inline-methods] */
    public final C0772Qi d() {
        return this == j ? new C0772Qi() : new C0772Qi().a(this);
    }

    public C0798Ri(AbstractC0911Vr abstractC0911Vr) {
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
        if (m() && !l().a()) {
            this.i = (byte) 0;
            return false;
        }
        this.i = (byte) 1;
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final H0 a(C0859Tr c0859Tr) {
        return new C0772Qi(c0859Tr);
    }
}
