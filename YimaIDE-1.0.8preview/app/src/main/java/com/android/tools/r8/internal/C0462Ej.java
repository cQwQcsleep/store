package com.android.tools.r8.internal;

import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Ej, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0462Ej extends AbstractC2209ns {
    public static final C0462Ej j = new C0462Ej();
    public static final C0410Cj k = new C0410Cj();
    public int e;
    public volatile Object f;
    public List g;
    public C0540Hj h;
    public byte i;

    public C0462Ej() {
        this.i = (byte) -1;
        this.f = XmlPullParser.NO_NAMESPACE;
        this.g = Collections.EMPTY_LIST;
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
        for (int i = 0; i < this.g.size(); i++) {
            if (!((C2623sj) this.g.get(i)).a()) {
                this.i = (byte) 0;
                return false;
            }
        }
        if (!m() || l().a()) {
            this.i = (byte) 1;
            return true;
        }
        this.i = (byte) 0;
        return false;
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
        for (int i2 = 0; i2 < this.g.size(); i2++) {
            iA = AbstractC1292d60.a((TN) this.g.get(i2), AbstractC0793Rd.b(2), iA);
        }
        if ((this.e & 2) != 0) {
            iA += AbstractC0793Rd.a(l()) + AbstractC0793Rd.b(3);
        }
        int iC = this.d.c() + iA;
        this.c = iC;
        return iC;
    }

    @Override // com.android.tools.r8.internal.TN
    public final SN d() {
        return this == j ? new C0436Dj() : new C0436Dj().a(this);
    }

    @Override // com.android.tools.r8.internal.J0
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C0462Ej)) {
            return super.equals(obj);
        }
        C0462Ej c0462Ej = (C0462Ej) obj;
        int i = this.e & 1;
        if ((i != 0) != ((c0462Ej.e & 1) != 0)) {
            return false;
        }
        if ((i == 0 || k().equals(c0462Ej.k())) && this.g.equals(c0462Ej.g) && m() == c0462Ej.m()) {
            return (!m() || l().equals(c0462Ej.l())) && this.d.equals(c0462Ej.d);
        }
        return false;
    }

    @Override // com.android.tools.r8.internal.WN
    public final C2712tk0 g() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.J0
    public final H0 h() {
        j.getClass();
        return new C0436Dj();
    }

    @Override // com.android.tools.r8.internal.J0
    public final int hashCode() {
        int i = this.b;
        if (i != 0) {
            return i;
        }
        int iHashCode = AbstractC0877Uj.u.hashCode() + 779;
        if ((this.e & 1) != 0) {
            iHashCode = Z50.a(iHashCode, 37, 1, 53) + k().hashCode();
        }
        if (this.g.size() > 0) {
            iHashCode = Z50.a(iHashCode, 37, 2, 53) + this.g.hashCode();
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
        return AbstractC0877Uj.v.a(C0462Ej.class, C0436Dj.class);
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

    public final C0540Hj l() {
        C0540Hj c0540Hj = this.h;
        return c0540Hj == null ? C0540Hj.j : c0540Hj;
    }

    public final boolean m() {
        return (this.e & 2) != 0;
    }

    public C0462Ej(AbstractC0911Vr abstractC0911Vr) {
        super(abstractC0911Vr);
        this.i = (byte) -1;
    }

    @Override // com.android.tools.r8.internal.TN
    public final void a(AbstractC0793Rd abstractC0793Rd) {
        if ((this.e & 1) != 0) {
            AbstractC2209ns.a(abstractC0793Rd, 1, this.f);
        }
        for (int i = 0; i < this.g.size(); i++) {
            abstractC0793Rd.a(2, (TN) this.g.get(i));
        }
        if ((this.e & 2) != 0) {
            abstractC0793Rd.a(3, l());
        }
        this.d.a(abstractC0793Rd);
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final H0 a(C0859Tr c0859Tr) {
        return new C0436Dj(c0859Tr);
    }
}
