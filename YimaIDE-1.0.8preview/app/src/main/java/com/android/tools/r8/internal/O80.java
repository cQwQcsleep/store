package com.android.tools.r8.internal;

import com.android.tools.r8.AbstractC0007c;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class O80 extends AbstractC2209ns {
    public static final O80 k = new O80();
    public static final M80 l = new M80();
    public int e;
    public Object f;
    public P70 g;
    public volatile Object h;
    public boolean i;
    public byte j;

    public O80() {
        this.e = 0;
        this.j = (byte) -1;
        this.h = XmlPullParser.NO_NAMESPACE;
    }

    @Override // com.android.tools.r8.internal.TN
    public final void a(AbstractC0793Rd abstractC0793Rd) {
        if (this.g != null) {
            abstractC0793Rd.a(1, l());
        }
        if (!AbstractC2209ns.a(this.h)) {
            AbstractC2209ns.a(abstractC0793Rd, 2, this.h);
        }
        boolean z = this.i;
        if (z) {
            abstractC0793Rd.a(3, z);
        }
        if (this.e == 4) {
            abstractC0793Rd.a(4, (R60) this.f);
        }
        if (this.e == 5) {
            abstractC0793Rd.a(5, (C2829v60) this.f);
        }
        this.d.a(abstractC0793Rd);
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return k;
    }

    @Override // com.android.tools.r8.internal.TN
    public final int c() {
        int iA;
        int i = this.c;
        if (i != -1) {
            return i;
        }
        if (this.g != null) {
            P70 p70L = l();
            iA = AbstractC0793Rd.a(p70L) + AbstractC0793Rd.b(1);
        } else {
            iA = 0;
        }
        if (!AbstractC2209ns.a(this.h)) {
            iA += AbstractC2209ns.a(2, this.h);
        }
        if (this.i) {
            iA = V60.a(3, 1, iA);
        }
        if (this.e == 4) {
            R60 r60 = (R60) this.f;
            iA += AbstractC0793Rd.a(r60) + AbstractC0793Rd.b(4);
        }
        if (this.e == 5) {
            C2829v60 c2829v60 = (C2829v60) this.f;
            iA += AbstractC0793Rd.a(c2829v60) + AbstractC0793Rd.b(5);
        }
        int iC = this.d.c() + iA;
        this.c = iC;
        return iC;
    }

    @Override // com.android.tools.r8.internal.J0
    public final boolean equals(Object obj) {
        String strC;
        String strC2;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof O80)) {
            return super.equals(obj);
        }
        O80 o80 = (O80) obj;
        P70 p70 = this.g;
        if ((p70 != null) != (o80.g != null)) {
            return false;
        }
        if (p70 != null && !l().equals(o80.l())) {
            return false;
        }
        Object obj2 = this.h;
        if (obj2 instanceof String) {
            strC = (String) obj2;
        } else {
            strC = ((U7) obj2).c();
            this.h = strC;
        }
        Object obj3 = o80.h;
        if (obj3 instanceof String) {
            strC2 = (String) obj3;
        } else {
            strC2 = ((U7) obj3).c();
            o80.h = strC2;
        }
        if (!strC.equals(strC2) || this.i != o80.i || !AbstractC0007c.b(m(), o80.m())) {
            return false;
        }
        int i = this.e;
        if (i != 4) {
            if (i == 5) {
                if (!(i == 5 ? (C2829v60) this.f : C2829v60.h).equals(o80.e == 5 ? (C2829v60) o80.f : C2829v60.h)) {
                    return false;
                }
            }
        } else if (!k().equals(o80.k())) {
            return false;
        }
        return this.d.equals(o80.d);
    }

    @Override // com.android.tools.r8.internal.WN
    public final C2712tk0 g() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.J0
    public final H0 h() {
        return k.d();
    }

    @Override // com.android.tools.r8.internal.J0
    public final int hashCode() {
        String strC;
        int iA;
        int iHashCode;
        int i = this.b;
        if (i != 0) {
            return i;
        }
        int iHashCode2 = AbstractC1468f90.K.hashCode() + 779;
        if (this.g != null) {
            iHashCode2 = Z50.a(iHashCode2, 37, 1, 53) + l().hashCode();
        }
        int iA2 = Z50.a(iHashCode2, 37, 2, 53);
        Object obj = this.h;
        if (obj instanceof String) {
            strC = (String) obj;
        } else {
            strC = ((U7) obj).c();
            this.h = strC;
        }
        int iA3 = AbstractC1556gB.a(this.i) + ((((strC.hashCode() + iA2) * 37) + 3) * 53);
        int i2 = this.e;
        if (i2 != 4) {
            if (i2 == 5) {
                iA = Z50.a(iA3, 37, 5, 53);
                iHashCode = (i2 == 5 ? (C2829v60) this.f : C2829v60.h).hashCode();
            }
            int iHashCode3 = this.d.hashCode() + (iA3 * 29);
            this.b = iHashCode3;
            return iHashCode3;
        }
        iA = Z50.a(iA3, 37, 4, 53);
        iHashCode = k().hashCode();
        iA3 = iA + iHashCode;
        int iHashCode4 = this.d.hashCode() + (iA3 * 29);
        this.b = iHashCode4;
        return iHashCode4;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final C2123ms j() {
        return AbstractC1468f90.L.a(O80.class, N80.class);
    }

    public final R60 k() {
        return this.e == 4 ? (R60) this.f : R60.h;
    }

    public final P70 l() {
        P70 p70 = this.g;
        return p70 == null ? P70.h : p70;
    }

    public final int m() {
        int i = this.e;
        if (i == 0) {
            return 3;
        }
        if (i != 4) {
            return i != 5 ? 0 : 2;
        }
        return 1;
    }

    @Override // com.android.tools.r8.internal.TN
    /* JADX INFO: renamed from: n, reason: merged with bridge method [inline-methods] */
    public final N80 d() {
        return this == k ? new N80() : new N80().a(this);
    }

    public O80(AbstractC0911Vr abstractC0911Vr) {
        super(abstractC0911Vr);
        this.e = 0;
        this.j = (byte) -1;
    }

    @Override // com.android.tools.r8.internal.VN
    public final boolean a() {
        byte b = this.j;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        this.j = (byte) 1;
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final H0 a(C0859Tr c0859Tr) {
        return new N80(c0859Tr);
    }
}
