package com.android.tools.r8.internal;

import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Tj, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0851Tj extends AbstractC2209ns {
    public static final C0851Tj n = new C0851Tj();
    public static final C0721Oj o = new C0721Oj();
    public int e;
    public List f;
    public volatile Object g;
    public long h;
    public long i;
    public double j;
    public U7 k;
    public volatile Object l;
    public byte m;

    public C0851Tj() {
        this.m = (byte) -1;
        this.f = Collections.EMPTY_LIST;
        this.g = XmlPullParser.NO_NAMESPACE;
        this.k = U7.c;
        this.l = XmlPullParser.NO_NAMESPACE;
    }

    @Override // com.android.tools.r8.internal.TN
    public final void a(AbstractC0793Rd abstractC0793Rd) throws C0741Pd {
        for (int i = 0; i < this.f.size(); i++) {
            abstractC0793Rd.a(2, (TN) this.f.get(i));
        }
        if ((this.e & 1) != 0) {
            AbstractC2209ns.a(abstractC0793Rd, 3, this.g);
        }
        if ((this.e & 2) != 0) {
            long j = this.h;
            C0689Nd c0689Nd = (C0689Nd) abstractC0793Rd;
            c0689Nd.c(4, 0);
            c0689Nd.c(j);
        }
        if ((this.e & 4) != 0) {
            long j2 = this.i;
            abstractC0793Rd.getClass();
            C0689Nd c0689Nd2 = (C0689Nd) abstractC0793Rd;
            c0689Nd2.c(5, 0);
            c0689Nd2.c(j2);
        }
        if ((this.e & 8) != 0) {
            double d = this.j;
            abstractC0793Rd.getClass();
            long jDoubleToRawLongBits = Double.doubleToRawLongBits(d);
            C0689Nd c0689Nd3 = (C0689Nd) abstractC0793Rd;
            c0689Nd3.c(6, 1);
            c0689Nd3.b(jDoubleToRawLongBits);
        }
        if ((this.e & 16) != 0) {
            U7 u7 = this.k;
            C0689Nd c0689Nd4 = (C0689Nd) abstractC0793Rd;
            c0689Nd4.c(7, 2);
            c0689Nd4.b(u7);
        }
        if ((this.e & 32) != 0) {
            AbstractC2209ns.a(abstractC0793Rd, 8, this.l);
        }
        this.d.a(abstractC0793Rd);
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return n;
    }

    @Override // com.android.tools.r8.internal.TN
    public final int c() {
        int i = this.c;
        if (i != -1) {
            return i;
        }
        int iA = 0;
        for (int i2 = 0; i2 < this.f.size(); i2++) {
            iA = AbstractC1292d60.a((TN) this.f.get(i2), AbstractC0793Rd.b(2), iA);
        }
        if ((this.e & 1) != 0) {
            iA += AbstractC2209ns.a(3, this.g);
        }
        if ((this.e & 2) != 0) {
            iA += AbstractC0793Rd.a(this.h) + AbstractC0793Rd.b(4);
        }
        if ((this.e & 4) != 0) {
            iA += AbstractC0793Rd.a(this.i) + AbstractC0793Rd.b(5);
        }
        if ((this.e & 8) != 0) {
            iA = V60.a(6, 8, iA);
        }
        if ((this.e & 16) != 0) {
            iA += AbstractC0793Rd.a(this.k) + AbstractC0793Rd.b(7);
        }
        if ((this.e & 32) != 0) {
            iA += AbstractC2209ns.a(8, this.l);
        }
        int iC = this.d.c() + iA;
        this.c = iC;
        return iC;
    }

    @Override // com.android.tools.r8.internal.TN
    public final SN d() {
        return this == n ? new C0747Pj() : new C0747Pj().a(this);
    }

    @Override // com.android.tools.r8.internal.J0
    public final boolean equals(Object obj) {
        String str;
        String str2;
        String str3;
        String str4;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C0851Tj)) {
            return super.equals(obj);
        }
        C0851Tj c0851Tj = (C0851Tj) obj;
        if (!this.f.equals(c0851Tj.f)) {
            return false;
        }
        int i = this.e & 1;
        if ((i != 0) != ((c0851Tj.e & 1) != 0)) {
            return false;
        }
        if (i != 0) {
            Object obj2 = this.g;
            if (obj2 instanceof String) {
                str3 = (String) obj2;
            } else {
                U7 u7 = (U7) obj2;
                String strC = u7.c();
                if (u7.a()) {
                    this.g = strC;
                }
                str3 = strC;
            }
            Object obj3 = c0851Tj.g;
            if (obj3 instanceof String) {
                str4 = (String) obj3;
            } else {
                U7 u8 = (U7) obj3;
                String strC2 = u8.c();
                if (u8.a()) {
                    c0851Tj.g = strC2;
                }
                str4 = strC2;
            }
            if (!str3.equals(str4)) {
                return false;
            }
        }
        int i2 = this.e;
        int i3 = i2 & 2;
        boolean z = i3 != 0;
        int i4 = c0851Tj.e;
        if (z != ((i4 & 2) != 0)) {
            return false;
        }
        if (i3 != 0 && this.h != c0851Tj.h) {
            return false;
        }
        int i5 = i2 & 4;
        if ((i5 != 0) != ((i4 & 4) != 0)) {
            return false;
        }
        if (i5 != 0 && this.i != c0851Tj.i) {
            return false;
        }
        int i6 = i2 & 8;
        if ((i6 != 0) != ((i4 & 8) != 0)) {
            return false;
        }
        if (i6 != 0 && Double.doubleToLongBits(this.j) != Double.doubleToLongBits(c0851Tj.j)) {
            return false;
        }
        int i7 = this.e & 16;
        if ((i7 != 0) != ((c0851Tj.e & 16) != 0)) {
            return false;
        }
        if (i7 != 0 && !this.k.equals(c0851Tj.k)) {
            return false;
        }
        int i8 = this.e & 32;
        if ((i8 != 0) != ((c0851Tj.e & 32) != 0)) {
            return false;
        }
        if (i8 != 0) {
            Object obj4 = this.l;
            if (obj4 instanceof String) {
                str = (String) obj4;
            } else {
                U7 u9 = (U7) obj4;
                String strC3 = u9.c();
                if (u9.a()) {
                    this.l = strC3;
                }
                str = strC3;
            }
            Object obj5 = c0851Tj.l;
            if (obj5 instanceof String) {
                str2 = (String) obj5;
            } else {
                U7 u10 = (U7) obj5;
                String strC4 = u10.c();
                if (u10.a()) {
                    c0851Tj.l = strC4;
                }
                str2 = strC4;
            }
            if (!str.equals(str2)) {
                return false;
            }
        }
        return this.d.equals(c0851Tj.d);
    }

    @Override // com.android.tools.r8.internal.WN
    public final C2712tk0 g() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.J0
    public final H0 h() {
        n.getClass();
        return new C0747Pj();
    }

    @Override // com.android.tools.r8.internal.J0
    public final int hashCode() {
        String str;
        String str2;
        int i = this.b;
        if (i != 0) {
            return i;
        }
        int iHashCode = AbstractC0877Uj.O.hashCode() + 779;
        if (this.f.size() > 0) {
            iHashCode = Z50.a(iHashCode, 37, 2, 53) + this.f.hashCode();
        }
        if ((this.e & 1) != 0) {
            int iA = Z50.a(iHashCode, 37, 3, 53);
            Object obj = this.g;
            if (obj instanceof String) {
                str2 = (String) obj;
            } else {
                U7 u7 = (U7) obj;
                String strC = u7.c();
                if (u7.a()) {
                    this.g = strC;
                }
                str2 = strC;
            }
            iHashCode = iA + str2.hashCode();
        }
        int i2 = this.e;
        if ((i2 & 2) != 0) {
            int iA2 = Z50.a(iHashCode, 37, 4, 53);
            long j = this.h;
            iHashCode = iA2 + ((int) (j ^ (j >>> 32)));
        }
        if ((i2 & 4) != 0) {
            int iA3 = Z50.a(iHashCode, 37, 5, 53);
            long j2 = this.i;
            iHashCode = iA3 + ((int) (j2 ^ (j2 >>> 32)));
        }
        if ((i2 & 8) != 0) {
            int iA4 = Z50.a(iHashCode, 37, 6, 53);
            long jDoubleToLongBits = Double.doubleToLongBits(this.j);
            iHashCode = iA4 + ((int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32)));
        }
        if ((this.e & 16) != 0) {
            iHashCode = Z50.a(iHashCode, 37, 7, 53) + this.k.hashCode();
        }
        if ((this.e & 32) != 0) {
            int iA5 = Z50.a(iHashCode, 37, 8, 53);
            Object obj2 = this.l;
            if (obj2 instanceof String) {
                str = (String) obj2;
            } else {
                U7 u8 = (U7) obj2;
                String strC2 = u8.c();
                if (u8.a()) {
                    this.l = strC2;
                }
                str = strC2;
            }
            iHashCode = iA5 + str.hashCode();
        }
        int iHashCode2 = this.d.hashCode() + (iHashCode * 29);
        this.b = iHashCode2;
        return iHashCode2;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final C2123ms j() {
        return AbstractC0877Uj.P.a(C0851Tj.class, C0747Pj.class);
    }

    public C0851Tj(AbstractC0911Vr abstractC0911Vr) {
        super(abstractC0911Vr);
        this.m = (byte) -1;
    }

    @Override // com.android.tools.r8.internal.VN
    public final boolean a() {
        byte b = this.m;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        for (int i = 0; i < this.f.size(); i++) {
            if (!((C0825Sj) this.f.get(i)).a()) {
                this.m = (byte) 0;
                return false;
            }
        }
        this.m = (byte) 1;
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final H0 a(C0859Tr c0859Tr) {
        return new C0747Pj(c0859Tr);
    }
}
