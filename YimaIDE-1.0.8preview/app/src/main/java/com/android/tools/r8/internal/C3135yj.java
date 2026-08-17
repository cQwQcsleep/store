package com.android.tools.r8.internal;

import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.yj, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3135yj extends AbstractC2209ns {
    public static final C3135yj i = new C3135yj();
    public static final C2966wj j = new C2966wj();
    public int e;
    public volatile Object f;
    public C0384Bj g;
    public byte h;

    public C3135yj() {
        this.h = (byte) -1;
        this.f = XmlPullParser.NO_NAMESPACE;
    }

    @Override // com.android.tools.r8.internal.VN
    public final boolean a() {
        byte b = this.h;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        if (!m() || l().a()) {
            this.h = (byte) 1;
            return true;
        }
        this.h = (byte) 0;
        return false;
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return i;
    }

    @Override // com.android.tools.r8.internal.TN
    public final int c() {
        int i2 = this.c;
        if (i2 != -1) {
            return i2;
        }
        int iA = (this.e & 1) != 0 ? AbstractC2209ns.a(1, this.f) : 0;
        if ((this.e & 2) != 0) {
            iA += AbstractC0793Rd.a(l()) + AbstractC0793Rd.b(2);
        }
        int iC = this.d.c() + iA;
        this.c = iC;
        return iC;
    }

    @Override // com.android.tools.r8.internal.TN
    public final SN d() {
        return this == i ? new C3051xj() : new C3051xj().a(this);
    }

    @Override // com.android.tools.r8.internal.J0
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C3135yj)) {
            return super.equals(obj);
        }
        C3135yj c3135yj = (C3135yj) obj;
        int i2 = this.e & 1;
        if ((i2 != 0) != ((c3135yj.e & 1) != 0)) {
            return false;
        }
        if ((i2 == 0 || k().equals(c3135yj.k())) && m() == c3135yj.m()) {
            return (!m() || l().equals(c3135yj.l())) && this.d.equals(c3135yj.d);
        }
        return false;
    }

    @Override // com.android.tools.r8.internal.WN
    public final C2712tk0 g() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.J0
    public final H0 h() {
        i.getClass();
        return new C3051xj();
    }

    @Override // com.android.tools.r8.internal.J0
    public final int hashCode() {
        int i2 = this.b;
        if (i2 != 0) {
            return i2;
        }
        int iHashCode = AbstractC0877Uj.m.hashCode() + 779;
        if ((this.e & 1) != 0) {
            iHashCode = Z50.a(iHashCode, 37, 1, 53) + k().hashCode();
        }
        if (m()) {
            iHashCode = Z50.a(iHashCode, 37, 2, 53) + l().hashCode();
        }
        int iHashCode2 = this.d.hashCode() + (iHashCode * 29);
        this.b = iHashCode2;
        return iHashCode2;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final C2123ms j() {
        return AbstractC0877Uj.n.a(C3135yj.class, C3051xj.class);
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

    public final C0384Bj l() {
        C0384Bj c0384Bj = this.g;
        return c0384Bj == null ? C0384Bj.h : c0384Bj;
    }

    public final boolean m() {
        return (this.e & 2) != 0;
    }

    public C3135yj(AbstractC0911Vr abstractC0911Vr) {
        super(abstractC0911Vr);
        this.h = (byte) -1;
    }

    @Override // com.android.tools.r8.internal.TN
    public final void a(AbstractC0793Rd abstractC0793Rd) {
        if ((this.e & 1) != 0) {
            AbstractC2209ns.a(abstractC0793Rd, 1, this.f);
        }
        if ((this.e & 2) != 0) {
            abstractC0793Rd.a(2, l());
        }
        this.d.a(abstractC0793Rd);
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final H0 a(C0859Tr c0859Tr) {
        return new C3051xj(c0859Tr);
    }
}
