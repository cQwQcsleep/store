package com.android.tools.r8.internal;

import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Sj, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0825Sj extends AbstractC2209ns {
    public static final C0825Sj i = new C0825Sj();
    public static final C0773Qj j = new C0773Qj();
    public int e;
    public volatile Object f;
    public boolean g;
    public byte h;

    public C0825Sj() {
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
        if (!l()) {
            this.h = (byte) 0;
            return false;
        }
        if (k()) {
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
            iA = V60.a(2, 1, iA);
        }
        int iC = this.d.c() + iA;
        this.c = iC;
        return iC;
    }

    @Override // com.android.tools.r8.internal.TN
    public final SN d() {
        return this == i ? new C0799Rj() : new C0799Rj().a(this);
    }

    @Override // com.android.tools.r8.internal.J0
    public final boolean equals(Object obj) {
        String str;
        String str2;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C0825Sj)) {
            return super.equals(obj);
        }
        C0825Sj c0825Sj = (C0825Sj) obj;
        if (l() != c0825Sj.l()) {
            return false;
        }
        if (l()) {
            Object obj2 = this.f;
            if (obj2 instanceof String) {
                str = (String) obj2;
            } else {
                U7 u7 = (U7) obj2;
                String strC = u7.c();
                if (u7.a()) {
                    this.f = strC;
                }
                str = strC;
            }
            Object obj3 = c0825Sj.f;
            if (obj3 instanceof String) {
                str2 = (String) obj3;
            } else {
                U7 u8 = (U7) obj3;
                String strC2 = u8.c();
                if (u8.a()) {
                    c0825Sj.f = strC2;
                }
                str2 = strC2;
            }
            if (!str.equals(str2)) {
                return false;
            }
        }
        if (k() != c0825Sj.k()) {
            return false;
        }
        return (!k() || this.g == c0825Sj.g) && this.d.equals(c0825Sj.d);
    }

    @Override // com.android.tools.r8.internal.WN
    public final C2712tk0 g() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.J0
    public final H0 h() {
        i.getClass();
        return new C0799Rj();
    }

    @Override // com.android.tools.r8.internal.J0
    public final int hashCode() {
        String str;
        int i2 = this.b;
        if (i2 != 0) {
            return i2;
        }
        int iHashCode = AbstractC0877Uj.Q.hashCode() + 779;
        if (l()) {
            int iA = Z50.a(iHashCode, 37, 1, 53);
            Object obj = this.f;
            if (obj instanceof String) {
                str = (String) obj;
            } else {
                U7 u7 = (U7) obj;
                String strC = u7.c();
                if (u7.a()) {
                    this.f = strC;
                }
                str = strC;
            }
            iHashCode = iA + str.hashCode();
        }
        if (k()) {
            iHashCode = Z50.a(iHashCode, 37, 2, 53) + AbstractC1556gB.a(this.g);
        }
        int iHashCode2 = this.d.hashCode() + (iHashCode * 29);
        this.b = iHashCode2;
        return iHashCode2;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final C2123ms j() {
        return AbstractC0877Uj.R.a(C0825Sj.class, C0799Rj.class);
    }

    public final boolean k() {
        return (this.e & 2) != 0;
    }

    public final boolean l() {
        return (this.e & 1) != 0;
    }

    public C0825Sj(AbstractC0911Vr abstractC0911Vr) {
        super(abstractC0911Vr);
        this.h = (byte) -1;
    }

    @Override // com.android.tools.r8.internal.TN
    public final void a(AbstractC0793Rd abstractC0793Rd) {
        if ((this.e & 1) != 0) {
            AbstractC2209ns.a(abstractC0793Rd, 1, this.f);
        }
        if ((this.e & 2) != 0) {
            abstractC0793Rd.a(2, this.g);
        }
        this.d.a(abstractC0793Rd);
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final H0 a(C0859Tr c0859Tr) {
        return new C0799Rj(c0859Tr);
    }
}
