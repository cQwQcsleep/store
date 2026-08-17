package com.android.tools.r8.internal;

import com.android.tools.r8.AbstractC0007c;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.e90, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1382e90 extends AbstractC2209ns {
    public static final C1382e90 i = new C1382e90();
    public static final C1214c90 j = new C1214c90();
    public int e;
    public Object f;
    public S70 g;
    public byte h;

    public C1382e90(AbstractC0911Vr abstractC0911Vr) {
        super(abstractC0911Vr);
        this.e = 0;
        this.h = (byte) -1;
    }

    @Override // com.android.tools.r8.internal.TN
    public final void a(AbstractC0793Rd abstractC0793Rd) {
        if (this.e == 1) {
            abstractC0793Rd.a(1, (Y80) this.f);
        }
        if (this.e == 2) {
            AbstractC2209ns.a(abstractC0793Rd, 2, this.f);
        }
        if (this.g != null) {
            abstractC0793Rd.a(3, m());
        }
        this.d.a(abstractC0793Rd);
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return i;
    }

    @Override // com.android.tools.r8.internal.TN
    public final int c() {
        int iA;
        int i2 = this.c;
        if (i2 != -1) {
            return i2;
        }
        if (this.e == 1) {
            Y80 y80 = (Y80) this.f;
            iA = AbstractC0793Rd.a(y80) + AbstractC0793Rd.b(1);
        } else {
            iA = 0;
        }
        if (this.e == 2) {
            iA += AbstractC2209ns.a(2, this.f);
        }
        if (this.g != null) {
            S70 s70M = m();
            iA += AbstractC0793Rd.a(s70M) + AbstractC0793Rd.b(3);
        }
        int iC = this.d.c() + iA;
        this.c = iC;
        return iC;
    }

    @Override // com.android.tools.r8.internal.TN
    public final SN d() {
        return this == i ? new C1298d90() : new C1298d90().a(this);
    }

    @Override // com.android.tools.r8.internal.J0
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C1382e90)) {
            return super.equals(obj);
        }
        C1382e90 c1382e90 = (C1382e90) obj;
        S70 s70 = this.g;
        if ((s70 != null) != (c1382e90.g != null)) {
            return false;
        }
        if ((s70 != null && !m().equals(c1382e90.m())) || !AbstractC0007c.b(l(), c1382e90.l())) {
            return false;
        }
        int i2 = this.e;
        if (i2 != 1) {
            if (i2 == 2 && !n().equals(c1382e90.n())) {
                return false;
            }
        } else if (!k().equals(c1382e90.k())) {
            return false;
        }
        return this.d.equals(c1382e90.d);
    }

    @Override // com.android.tools.r8.internal.WN
    public final C2712tk0 g() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.J0
    public final H0 h() {
        i.getClass();
        return new C1298d90();
    }

    @Override // com.android.tools.r8.internal.J0
    public final int hashCode() {
        int iA;
        int iHashCode;
        int i2 = this.b;
        if (i2 != 0) {
            return i2;
        }
        int iHashCode2 = AbstractC1468f90.G0.hashCode() + 779;
        if (this.g != null) {
            iHashCode2 = Z50.a(iHashCode2, 37, 3, 53) + m().hashCode();
        }
        int i3 = this.e;
        if (i3 != 1) {
            if (i3 == 2) {
                iA = Z50.a(iHashCode2, 37, 2, 53);
                iHashCode = n().hashCode();
            }
            int iHashCode3 = this.d.hashCode() + (iHashCode2 * 29);
            this.b = iHashCode3;
            return iHashCode3;
        }
        iA = Z50.a(iHashCode2, 37, 1, 53);
        iHashCode = k().hashCode();
        iHashCode2 = iA + iHashCode;
        int iHashCode4 = this.d.hashCode() + (iHashCode2 * 29);
        this.b = iHashCode4;
        return iHashCode4;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final C2123ms j() {
        return AbstractC1468f90.H0.a(C1382e90.class, C1298d90.class);
    }

    public final Y80 k() {
        return this.e == 1 ? (Y80) this.f : Y80.k;
    }

    public final int l() {
        int i2 = this.e;
        if (i2 == 0) {
            return 3;
        }
        int i3 = 1;
        if (i2 != 1) {
            i3 = 2;
            if (i2 != 2) {
                return 0;
            }
        }
        return i3;
    }

    public final S70 m() {
        S70 s70 = this.g;
        return s70 == null ? S70.h : s70;
    }

    public final String n() {
        Object obj = this.e == 2 ? this.f : XmlPullParser.NO_NAMESPACE;
        if (obj instanceof String) {
            return (String) obj;
        }
        String strC = ((U7) obj).c();
        if (this.e == 2) {
            this.f = strC;
        }
        return strC;
    }

    public C1382e90() {
        this.e = 0;
        this.h = (byte) -1;
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
        this.h = (byte) 1;
        return true;
    }

    public static C1382e90 a(byte[] bArr) {
        return (C1382e90) j.a(bArr);
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final H0 a(C0859Tr c0859Tr) {
        return new C1298d90(c0859Tr);
    }
}
