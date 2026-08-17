package com.android.tools.r8.internal;

import com.sun.jna.platform.linux.Fcntl;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.mj, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2110mj extends AbstractC1102as {
    public static final C2110mj C = new C2110mj();
    public static final C1854jj D = new C1854jj();
    public List A;
    public byte B;
    public int f;
    public volatile Object g;
    public volatile Object h;
    public boolean i;
    public boolean j;
    public boolean k;
    public int l;
    public volatile Object m;
    public boolean n;
    public boolean o;
    public boolean p;
    public boolean q;
    public boolean r;
    public boolean s;
    public volatile Object t;
    public volatile Object u;
    public volatile Object v;
    public volatile Object w;
    public volatile Object x;
    public volatile Object y;
    public volatile Object z;

    public C2110mj() {
        this.B = (byte) -1;
        this.g = XmlPullParser.NO_NAMESPACE;
        this.h = XmlPullParser.NO_NAMESPACE;
        this.l = 1;
        this.m = XmlPullParser.NO_NAMESPACE;
        this.s = true;
        this.t = XmlPullParser.NO_NAMESPACE;
        this.u = XmlPullParser.NO_NAMESPACE;
        this.v = XmlPullParser.NO_NAMESPACE;
        this.w = XmlPullParser.NO_NAMESPACE;
        this.x = XmlPullParser.NO_NAMESPACE;
        this.y = XmlPullParser.NO_NAMESPACE;
        this.z = XmlPullParser.NO_NAMESPACE;
        this.A = Collections.EMPTY_LIST;
    }

    @Override // com.android.tools.r8.internal.TN
    public final void a(AbstractC0793Rd abstractC0793Rd) {
        C1015Zr c1015Zr = new C1015Zr(this);
        if ((this.f & 1) != 0) {
            AbstractC2209ns.a(abstractC0793Rd, 1, this.g);
        }
        if ((this.f & 2) != 0) {
            AbstractC2209ns.a(abstractC0793Rd, 8, this.h);
        }
        if ((this.f & 32) != 0) {
            abstractC0793Rd.b(9, this.l);
        }
        if ((this.f & 4) != 0) {
            abstractC0793Rd.a(10, this.i);
        }
        if ((this.f & 64) != 0) {
            AbstractC2209ns.a(abstractC0793Rd, 11, this.m);
        }
        if ((this.f & 128) != 0) {
            abstractC0793Rd.a(16, this.n);
        }
        if ((this.f & Fcntl.S_IRUSR) != 0) {
            abstractC0793Rd.a(17, this.o);
        }
        if ((this.f & 512) != 0) {
            abstractC0793Rd.a(18, this.p);
        }
        if ((this.f & 8) != 0) {
            abstractC0793Rd.a(20, this.j);
        }
        if ((this.f & Fcntl.S_ISUID) != 0) {
            abstractC0793Rd.a(23, this.r);
        }
        if ((this.f & 16) != 0) {
            abstractC0793Rd.a(27, this.k);
        }
        if ((this.f & 4096) != 0) {
            abstractC0793Rd.a(31, this.s);
        }
        if ((this.f & 8192) != 0) {
            AbstractC2209ns.a(abstractC0793Rd, 36, this.t);
        }
        if ((this.f & 16384) != 0) {
            AbstractC2209ns.a(abstractC0793Rd, 37, this.u);
        }
        if ((this.f & 32768) != 0) {
            AbstractC2209ns.a(abstractC0793Rd, 39, this.v);
        }
        if ((this.f & 65536) != 0) {
            AbstractC2209ns.a(abstractC0793Rd, 40, this.w);
        }
        if ((this.f & 131072) != 0) {
            AbstractC2209ns.a(abstractC0793Rd, 41, this.x);
        }
        if ((this.f & Fcntl.S_ISGID) != 0) {
            abstractC0793Rd.a(42, this.q);
        }
        if ((this.f & 262144) != 0) {
            AbstractC2209ns.a(abstractC0793Rd, 44, this.y);
        }
        if ((this.f & 524288) != 0) {
            AbstractC2209ns.a(abstractC0793Rd, 45, this.z);
        }
        for (int i = 0; i < this.A.size(); i++) {
            abstractC0793Rd.a(999, (TN) this.A.get(i));
        }
        c1015Zr.a(abstractC0793Rd);
        this.d.a(abstractC0793Rd);
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return C;
    }

    @Override // com.android.tools.r8.internal.TN
    public final int c() {
        int i = this.c;
        if (i != -1) {
            return i;
        }
        int iA = (this.f & 1) != 0 ? AbstractC2209ns.a(1, this.g) : 0;
        if ((this.f & 2) != 0) {
            iA += AbstractC2209ns.a(8, this.h);
        }
        if ((this.f & 32) != 0) {
            iA = AbstractC0458Ef.a(this.l, AbstractC0793Rd.b(9), iA);
        }
        if ((this.f & 4) != 0) {
            iA = V60.a(10, 1, iA);
        }
        if ((this.f & 64) != 0) {
            iA += AbstractC2209ns.a(11, this.m);
        }
        if ((this.f & 128) != 0) {
            iA = V60.a(16, 1, iA);
        }
        if ((this.f & Fcntl.S_IRUSR) != 0) {
            iA = V60.a(17, 1, iA);
        }
        if ((this.f & 512) != 0) {
            iA = V60.a(18, 1, iA);
        }
        if ((this.f & 8) != 0) {
            iA = V60.a(20, 1, iA);
        }
        if ((this.f & Fcntl.S_ISUID) != 0) {
            iA = V60.a(23, 1, iA);
        }
        if ((this.f & 16) != 0) {
            iA = V60.a(27, 1, iA);
        }
        if ((this.f & 4096) != 0) {
            iA = V60.a(31, 1, iA);
        }
        if ((this.f & 8192) != 0) {
            iA += AbstractC2209ns.a(36, this.t);
        }
        if ((this.f & 16384) != 0) {
            iA += AbstractC2209ns.a(37, this.u);
        }
        if ((this.f & 32768) != 0) {
            iA += AbstractC2209ns.a(39, this.v);
        }
        if ((this.f & 65536) != 0) {
            iA += AbstractC2209ns.a(40, this.w);
        }
        if ((this.f & 131072) != 0) {
            iA += AbstractC2209ns.a(41, this.x);
        }
        if ((this.f & Fcntl.S_ISGID) != 0) {
            iA = V60.a(42, 1, iA);
        }
        if ((this.f & 262144) != 0) {
            iA += AbstractC2209ns.a(44, this.y);
        }
        if ((this.f & 524288) != 0) {
            iA += AbstractC2209ns.a(45, this.z);
        }
        for (int i2 = 0; i2 < this.A.size(); i2++) {
            iA = AbstractC1292d60.a((TN) this.A.get(i2), AbstractC0793Rd.b(999), iA);
        }
        int iC = this.d.c() + this.e.b() + iA;
        this.c = iC;
        return iC;
    }

    @Override // com.android.tools.r8.internal.J0
    public final boolean equals(Object obj) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        String str10;
        String str11;
        String str12;
        String str13;
        String str14;
        String str15;
        String str16;
        String str17;
        String str18;
        String str19;
        String str20;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C2110mj)) {
            return super.equals(obj);
        }
        C2110mj c2110mj = (C2110mj) obj;
        int i = this.f & 1;
        if ((i != 0) != ((c2110mj.f & 1) != 0)) {
            return false;
        }
        if (i != 0) {
            Object obj2 = this.g;
            if (obj2 instanceof String) {
                str19 = (String) obj2;
            } else {
                U7 u7 = (U7) obj2;
                String strC = u7.c();
                if (u7.a()) {
                    this.g = strC;
                }
                str19 = strC;
            }
            Object obj3 = c2110mj.g;
            if (obj3 instanceof String) {
                str20 = (String) obj3;
            } else {
                U7 u8 = (U7) obj3;
                String strC2 = u8.c();
                if (u8.a()) {
                    c2110mj.g = strC2;
                }
                str20 = strC2;
            }
            if (!str19.equals(str20)) {
                return false;
            }
        }
        int i2 = this.f & 2;
        if ((i2 != 0) != ((c2110mj.f & 2) != 0)) {
            return false;
        }
        if (i2 != 0) {
            Object obj4 = this.h;
            if (obj4 instanceof String) {
                str17 = (String) obj4;
            } else {
                U7 u9 = (U7) obj4;
                String strC3 = u9.c();
                if (u9.a()) {
                    this.h = strC3;
                }
                str17 = strC3;
            }
            Object obj5 = c2110mj.h;
            if (obj5 instanceof String) {
                str18 = (String) obj5;
            } else {
                U7 u10 = (U7) obj5;
                String strC4 = u10.c();
                if (u10.a()) {
                    c2110mj.h = strC4;
                }
                str18 = strC4;
            }
            if (!str17.equals(str18)) {
                return false;
            }
        }
        int i3 = this.f;
        int i4 = i3 & 4;
        boolean z = i4 != 0;
        int i5 = c2110mj.f;
        if (z != ((i5 & 4) != 0)) {
            return false;
        }
        if (i4 != 0 && this.i != c2110mj.i) {
            return false;
        }
        int i6 = i3 & 8;
        if ((i6 != 0) != ((i5 & 8) != 0)) {
            return false;
        }
        if (i6 != 0 && this.j != c2110mj.j) {
            return false;
        }
        int i7 = i3 & 16;
        if ((i7 != 0) != ((i5 & 16) != 0)) {
            return false;
        }
        if (i7 != 0 && this.k != c2110mj.k) {
            return false;
        }
        int i8 = i3 & 32;
        if ((i8 != 0) != ((i5 & 32) != 0)) {
            return false;
        }
        if (i8 != 0 && this.l != c2110mj.l) {
            return false;
        }
        int i9 = i3 & 64;
        if ((i9 != 0) != ((i5 & 64) != 0)) {
            return false;
        }
        if (i9 != 0) {
            Object obj6 = this.m;
            if (obj6 instanceof String) {
                str15 = (String) obj6;
            } else {
                U7 u11 = (U7) obj6;
                String strC5 = u11.c();
                if (u11.a()) {
                    this.m = strC5;
                }
                str15 = strC5;
            }
            Object obj7 = c2110mj.m;
            if (obj7 instanceof String) {
                str16 = (String) obj7;
            } else {
                U7 u12 = (U7) obj7;
                String strC6 = u12.c();
                if (u12.a()) {
                    c2110mj.m = strC6;
                }
                str16 = strC6;
            }
            if (!str15.equals(str16)) {
                return false;
            }
        }
        int i10 = this.f;
        int i11 = i10 & 128;
        boolean z2 = i11 != 0;
        int i12 = c2110mj.f;
        if (z2 != ((i12 & 128) != 0)) {
            return false;
        }
        if (i11 != 0 && this.n != c2110mj.n) {
            return false;
        }
        int i13 = i10 & Fcntl.S_IRUSR;
        if ((i13 != 0) != ((i12 & Fcntl.S_IRUSR) != 0)) {
            return false;
        }
        if (i13 != 0 && this.o != c2110mj.o) {
            return false;
        }
        int i14 = i10 & 512;
        if ((i14 != 0) != ((i12 & 512) != 0)) {
            return false;
        }
        if (i14 != 0 && this.p != c2110mj.p) {
            return false;
        }
        int i15 = i10 & Fcntl.S_ISGID;
        if ((i15 != 0) != ((i12 & Fcntl.S_ISGID) != 0)) {
            return false;
        }
        if (i15 != 0 && this.q != c2110mj.q) {
            return false;
        }
        int i16 = i10 & Fcntl.S_ISUID;
        if ((i16 != 0) != ((i12 & Fcntl.S_ISUID) != 0)) {
            return false;
        }
        if (i16 != 0 && this.r != c2110mj.r) {
            return false;
        }
        int i17 = i10 & 4096;
        if ((i17 != 0) != ((i12 & 4096) != 0)) {
            return false;
        }
        if (i17 != 0 && this.s != c2110mj.s) {
            return false;
        }
        int i18 = i10 & 8192;
        if ((i18 != 0) != ((i12 & 8192) != 0)) {
            return false;
        }
        if (i18 != 0) {
            Object obj8 = this.t;
            if (obj8 instanceof String) {
                str13 = (String) obj8;
            } else {
                U7 u13 = (U7) obj8;
                String strC7 = u13.c();
                if (u13.a()) {
                    this.t = strC7;
                }
                str13 = strC7;
            }
            Object obj9 = c2110mj.t;
            if (obj9 instanceof String) {
                str14 = (String) obj9;
            } else {
                U7 u14 = (U7) obj9;
                String strC8 = u14.c();
                if (u14.a()) {
                    c2110mj.t = strC8;
                }
                str14 = strC8;
            }
            if (!str13.equals(str14)) {
                return false;
            }
        }
        int i19 = this.f & 16384;
        if ((i19 != 0) != ((c2110mj.f & 16384) != 0)) {
            return false;
        }
        if (i19 != 0) {
            Object obj10 = this.u;
            if (obj10 instanceof String) {
                str11 = (String) obj10;
            } else {
                U7 u15 = (U7) obj10;
                String strC9 = u15.c();
                if (u15.a()) {
                    this.u = strC9;
                }
                str11 = strC9;
            }
            Object obj11 = c2110mj.u;
            if (obj11 instanceof String) {
                str12 = (String) obj11;
            } else {
                U7 u16 = (U7) obj11;
                String strC10 = u16.c();
                if (u16.a()) {
                    c2110mj.u = strC10;
                }
                str12 = strC10;
            }
            if (!str11.equals(str12)) {
                return false;
            }
        }
        int i20 = this.f & 32768;
        if ((i20 != 0) != ((32768 & c2110mj.f) != 0)) {
            return false;
        }
        if (i20 != 0) {
            Object obj12 = this.v;
            if (obj12 instanceof String) {
                str9 = (String) obj12;
            } else {
                U7 u17 = (U7) obj12;
                String strC11 = u17.c();
                if (u17.a()) {
                    this.v = strC11;
                }
                str9 = strC11;
            }
            Object obj13 = c2110mj.v;
            if (obj13 instanceof String) {
                str10 = (String) obj13;
            } else {
                U7 u18 = (U7) obj13;
                String strC12 = u18.c();
                if (u18.a()) {
                    c2110mj.v = strC12;
                }
                str10 = strC12;
            }
            if (!str9.equals(str10)) {
                return false;
            }
        }
        int i21 = this.f & 65536;
        if ((i21 != 0) != ((65536 & c2110mj.f) != 0)) {
            return false;
        }
        if (i21 != 0) {
            Object obj14 = this.w;
            if (obj14 instanceof String) {
                str7 = (String) obj14;
            } else {
                U7 u19 = (U7) obj14;
                String strC13 = u19.c();
                if (u19.a()) {
                    this.w = strC13;
                }
                str7 = strC13;
            }
            Object obj15 = c2110mj.w;
            if (obj15 instanceof String) {
                str8 = (String) obj15;
            } else {
                U7 u20 = (U7) obj15;
                String strC14 = u20.c();
                if (u20.a()) {
                    c2110mj.w = strC14;
                }
                str8 = strC14;
            }
            if (!str7.equals(str8)) {
                return false;
            }
        }
        int i22 = this.f & 131072;
        if ((i22 != 0) != ((131072 & c2110mj.f) != 0)) {
            return false;
        }
        if (i22 != 0) {
            Object obj16 = this.x;
            if (obj16 instanceof String) {
                str5 = (String) obj16;
            } else {
                U7 u21 = (U7) obj16;
                String strC15 = u21.c();
                if (u21.a()) {
                    this.x = strC15;
                }
                str5 = strC15;
            }
            Object obj17 = c2110mj.x;
            if (obj17 instanceof String) {
                str6 = (String) obj17;
            } else {
                U7 u22 = (U7) obj17;
                String strC16 = u22.c();
                if (u22.a()) {
                    c2110mj.x = strC16;
                }
                str6 = strC16;
            }
            if (!str5.equals(str6)) {
                return false;
            }
        }
        int i23 = this.f & 262144;
        if ((i23 != 0) != ((262144 & c2110mj.f) != 0)) {
            return false;
        }
        if (i23 != 0) {
            Object obj18 = this.y;
            if (obj18 instanceof String) {
                str3 = (String) obj18;
            } else {
                U7 u23 = (U7) obj18;
                String strC17 = u23.c();
                if (u23.a()) {
                    this.y = strC17;
                }
                str3 = strC17;
            }
            Object obj19 = c2110mj.y;
            if (obj19 instanceof String) {
                str4 = (String) obj19;
            } else {
                U7 u24 = (U7) obj19;
                String strC18 = u24.c();
                if (u24.a()) {
                    c2110mj.y = strC18;
                }
                str4 = strC18;
            }
            if (!str3.equals(str4)) {
                return false;
            }
        }
        int i24 = this.f & 524288;
        if ((i24 != 0) != ((524288 & c2110mj.f) != 0)) {
            return false;
        }
        if (i24 != 0) {
            Object obj20 = this.z;
            if (obj20 instanceof String) {
                str = (String) obj20;
            } else {
                U7 u25 = (U7) obj20;
                String strC19 = u25.c();
                if (u25.a()) {
                    this.z = strC19;
                }
                str = strC19;
            }
            Object obj21 = c2110mj.z;
            if (obj21 instanceof String) {
                str2 = (String) obj21;
            } else {
                U7 u26 = (U7) obj21;
                String strC20 = u26.c();
                if (u26.a()) {
                    c2110mj.z = strC20;
                }
                str2 = strC20;
            }
            if (!str.equals(str2)) {
                return false;
            }
        }
        return this.A.equals(c2110mj.A) && this.d.equals(c2110mj.d) && this.e.a().equals(c2110mj.e.a());
    }

    @Override // com.android.tools.r8.internal.WN
    public final C2712tk0 g() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.J0
    public final H0 h() {
        return C.d();
    }

    @Override // com.android.tools.r8.internal.J0
    public final int hashCode() {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        String str10;
        int i = this.b;
        if (i != 0) {
            return i;
        }
        int iHashCode = AbstractC0877Uj.y.hashCode() + 779;
        if ((this.f & 1) != 0) {
            int iA = Z50.a(iHashCode, 37, 1, 53);
            Object obj = this.g;
            if (obj instanceof String) {
                str10 = (String) obj;
            } else {
                U7 u7 = (U7) obj;
                String strC = u7.c();
                if (u7.a()) {
                    this.g = strC;
                }
                str10 = strC;
            }
            iHashCode = iA + str10.hashCode();
        }
        if ((this.f & 2) != 0) {
            int iA2 = Z50.a(iHashCode, 37, 8, 53);
            Object obj2 = this.h;
            if (obj2 instanceof String) {
                str9 = (String) obj2;
            } else {
                U7 u8 = (U7) obj2;
                String strC2 = u8.c();
                if (u8.a()) {
                    this.h = strC2;
                }
                str9 = strC2;
            }
            iHashCode = iA2 + str9.hashCode();
        }
        if ((this.f & 4) != 0) {
            iHashCode = Z50.a(iHashCode, 37, 10, 53) + AbstractC1556gB.a(this.i);
        }
        if ((this.f & 8) != 0) {
            iHashCode = Z50.a(iHashCode, 37, 20, 53) + AbstractC1556gB.a(this.j);
        }
        if ((this.f & 16) != 0) {
            iHashCode = Z50.a(iHashCode, 37, 27, 53) + AbstractC1556gB.a(this.k);
        }
        int i2 = this.f;
        if ((i2 & 32) != 0) {
            iHashCode = Z50.a(iHashCode, 37, 9, 53) + this.l;
        }
        if ((i2 & 64) != 0) {
            int iA3 = Z50.a(iHashCode, 37, 11, 53);
            Object obj3 = this.m;
            if (obj3 instanceof String) {
                str8 = (String) obj3;
            } else {
                U7 u9 = (U7) obj3;
                String strC3 = u9.c();
                if (u9.a()) {
                    this.m = strC3;
                }
                str8 = strC3;
            }
            iHashCode = iA3 + str8.hashCode();
        }
        if ((this.f & 128) != 0) {
            iHashCode = Z50.a(iHashCode, 37, 16, 53) + AbstractC1556gB.a(this.n);
        }
        if ((this.f & Fcntl.S_IRUSR) != 0) {
            iHashCode = Z50.a(iHashCode, 37, 17, 53) + AbstractC1556gB.a(this.o);
        }
        if ((this.f & 512) != 0) {
            iHashCode = Z50.a(iHashCode, 37, 18, 53) + AbstractC1556gB.a(this.p);
        }
        if ((this.f & Fcntl.S_ISGID) != 0) {
            iHashCode = Z50.a(iHashCode, 37, 42, 53) + AbstractC1556gB.a(this.q);
        }
        if ((this.f & Fcntl.S_ISUID) != 0) {
            iHashCode = Z50.a(iHashCode, 37, 23, 53) + AbstractC1556gB.a(this.r);
        }
        if ((this.f & 4096) != 0) {
            iHashCode = Z50.a(iHashCode, 37, 31, 53) + AbstractC1556gB.a(this.s);
        }
        if ((this.f & 8192) != 0) {
            int iA4 = Z50.a(iHashCode, 37, 36, 53);
            Object obj4 = this.t;
            if (obj4 instanceof String) {
                str7 = (String) obj4;
            } else {
                U7 u10 = (U7) obj4;
                String strC4 = u10.c();
                if (u10.a()) {
                    this.t = strC4;
                }
                str7 = strC4;
            }
            iHashCode = iA4 + str7.hashCode();
        }
        if ((this.f & 16384) != 0) {
            int iA5 = Z50.a(iHashCode, 37, 37, 53);
            Object obj5 = this.u;
            if (obj5 instanceof String) {
                str6 = (String) obj5;
            } else {
                U7 u11 = (U7) obj5;
                String strC5 = u11.c();
                if (u11.a()) {
                    this.u = strC5;
                }
                str6 = strC5;
            }
            iHashCode = iA5 + str6.hashCode();
        }
        if ((this.f & 32768) != 0) {
            int iA6 = Z50.a(iHashCode, 37, 39, 53);
            Object obj6 = this.v;
            if (obj6 instanceof String) {
                str5 = (String) obj6;
            } else {
                U7 u12 = (U7) obj6;
                String strC6 = u12.c();
                if (u12.a()) {
                    this.v = strC6;
                }
                str5 = strC6;
            }
            iHashCode = iA6 + str5.hashCode();
        }
        if ((this.f & 65536) != 0) {
            int iA7 = Z50.a(iHashCode, 37, 40, 53);
            Object obj7 = this.w;
            if (obj7 instanceof String) {
                str4 = (String) obj7;
            } else {
                U7 u13 = (U7) obj7;
                String strC7 = u13.c();
                if (u13.a()) {
                    this.w = strC7;
                }
                str4 = strC7;
            }
            iHashCode = iA7 + str4.hashCode();
        }
        if ((this.f & 131072) != 0) {
            int iA8 = Z50.a(iHashCode, 37, 41, 53);
            Object obj8 = this.x;
            if (obj8 instanceof String) {
                str3 = (String) obj8;
            } else {
                U7 u14 = (U7) obj8;
                String strC8 = u14.c();
                if (u14.a()) {
                    this.x = strC8;
                }
                str3 = strC8;
            }
            iHashCode = iA8 + str3.hashCode();
        }
        if ((this.f & 262144) != 0) {
            int iA9 = Z50.a(iHashCode, 37, 44, 53);
            Object obj9 = this.y;
            if (obj9 instanceof String) {
                str2 = (String) obj9;
            } else {
                U7 u15 = (U7) obj9;
                String strC9 = u15.c();
                if (u15.a()) {
                    this.y = strC9;
                }
                str2 = strC9;
            }
            iHashCode = iA9 + str2.hashCode();
        }
        if ((this.f & 524288) != 0) {
            int iA10 = Z50.a(iHashCode, 37, 45, 53);
            Object obj10 = this.z;
            if (obj10 instanceof String) {
                str = (String) obj10;
            } else {
                U7 u16 = (U7) obj10;
                String strC10 = u16.c();
                if (u16.a()) {
                    this.z = strC10;
                }
                str = strC10;
            }
            iHashCode = iA10 + str.hashCode();
        }
        if (this.A.size() > 0) {
            iHashCode = Z50.a(iHashCode, 37, 999, 53) + this.A.hashCode();
        }
        int iHashCode2 = this.d.hashCode() + (J0.a(iHashCode, this.e.a()) * 29);
        this.b = iHashCode2;
        return iHashCode2;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final C2123ms j() {
        return AbstractC0877Uj.z.a(C2110mj.class, C1939kj.class);
    }

    @Override // com.android.tools.r8.internal.TN
    /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
    public final C1939kj d() {
        return this == C ? new C1939kj() : new C1939kj().a(this);
    }

    public C2110mj(AbstractC0963Xr abstractC0963Xr) {
        super(abstractC0963Xr);
        this.B = (byte) -1;
    }

    @Override // com.android.tools.r8.internal.VN
    public final boolean a() {
        byte b = this.B;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        for (int i = 0; i < this.A.size(); i++) {
            if (!((C0851Tj) this.A.get(i)).a()) {
                this.B = (byte) 0;
                return false;
            }
        }
        if (!this.e.c()) {
            this.B = (byte) 0;
            return false;
        }
        this.B = (byte) 1;
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final H0 a(C0859Tr c0859Tr) {
        return new C1939kj(c0859Tr);
    }
}
