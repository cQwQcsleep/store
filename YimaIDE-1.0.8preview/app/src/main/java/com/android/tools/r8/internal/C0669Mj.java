package com.android.tools.r8.internal;

import java.util.logging.Logger;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Mj, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0669Mj extends AbstractC2209ns {
    public static final C0669Mj n = new C0669Mj();
    public static final C0618Kj o = new C0618Kj();
    public int e;
    public InterfaceC1216cB f;
    public int g;
    public InterfaceC1216cB h;
    public int i;
    public volatile Object j;
    public volatile Object k;
    public AJ l;
    public byte m;

    public C0669Mj() {
        this.g = -1;
        this.i = -1;
        this.m = (byte) -1;
        C0945Wz c0945Wz = C0945Wz.e;
        this.f = c0945Wz;
        this.h = c0945Wz;
        this.j = XmlPullParser.NO_NAMESPACE;
        this.k = XmlPullParser.NO_NAMESPACE;
        this.l = C3101yJ.d;
    }

    @Override // com.android.tools.r8.internal.TN
    public final void a(AbstractC0793Rd abstractC0793Rd) {
        c();
        if (((C0945Wz) this.f).d > 0) {
            abstractC0793Rd.f(10);
            abstractC0793Rd.f(this.g);
        }
        int i = 0;
        while (true) {
            C0945Wz c0945Wz = (C0945Wz) this.f;
            if (i >= c0945Wz.d) {
                break;
            }
            c0945Wz.k(i);
            abstractC0793Rd.e(c0945Wz.c[i]);
            i++;
        }
        if (((C0945Wz) this.h).d > 0) {
            abstractC0793Rd.f(18);
            abstractC0793Rd.f(this.i);
        }
        int i2 = 0;
        while (true) {
            C0945Wz c0945Wz2 = (C0945Wz) this.h;
            if (i2 >= c0945Wz2.d) {
                break;
            }
            c0945Wz2.k(i2);
            abstractC0793Rd.e(c0945Wz2.c[i2]);
            i2++;
        }
        if ((this.e & 1) != 0) {
            AbstractC2209ns.a(abstractC0793Rd, 3, this.j);
        }
        if ((this.e & 2) != 0) {
            AbstractC2209ns.a(abstractC0793Rd, 4, this.k);
        }
        for (int i3 = 0; i3 < this.l.size(); i3++) {
            AbstractC2209ns.a(abstractC0793Rd, 6, this.l.e(i3));
        }
        this.d.a(abstractC0793Rd);
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return n;
    }

    @Override // com.android.tools.r8.internal.TN
    public final int c() {
        InterfaceC1216cB interfaceC1216cB;
        int iC;
        int i;
        InterfaceC1216cB interfaceC1216cB2;
        int iC2;
        int iC3;
        int i2 = this.c;
        if (i2 != -1) {
            return i2;
        }
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (true) {
            interfaceC1216cB = this.f;
            C0945Wz c0945Wz = (C0945Wz) interfaceC1216cB;
            iC = 10;
            if (i4 >= c0945Wz.d) {
                break;
            }
            c0945Wz.k(i4);
            int i6 = c0945Wz.c[i4];
            if (i6 >= 0) {
                iC = AbstractC0793Rd.c(i6);
            } else {
                Logger logger = AbstractC0793Rd.a;
            }
            i5 += iC;
            i4++;
        }
        if (interfaceC1216cB.isEmpty()) {
            i = i5;
        } else {
            int i7 = i5 + 1;
            if (i5 >= 0) {
                iC3 = AbstractC0793Rd.c(i5);
            } else {
                Logger logger2 = AbstractC0793Rd.a;
                iC3 = 10;
            }
            i = i7 + iC3;
        }
        this.g = i5;
        int i8 = 0;
        int i9 = 0;
        while (true) {
            interfaceC1216cB2 = this.h;
            C0945Wz c0945Wz2 = (C0945Wz) interfaceC1216cB2;
            if (i8 >= c0945Wz2.d) {
                break;
            }
            c0945Wz2.k(i8);
            int i10 = c0945Wz2.c[i8];
            if (i10 >= 0) {
                iC2 = AbstractC0793Rd.c(i10);
            } else {
                Logger logger3 = AbstractC0793Rd.a;
                iC2 = 10;
            }
            i9 += iC2;
            i8++;
        }
        int iA = i + i9;
        if (!interfaceC1216cB2.isEmpty()) {
            int i11 = iA + 1;
            if (i9 >= 0) {
                iC = AbstractC0793Rd.c(i9);
            } else {
                Logger logger4 = AbstractC0793Rd.a;
            }
            iA = i11 + iC;
        }
        this.i = i9;
        if ((this.e & 1) != 0) {
            iA += AbstractC2209ns.a(3, this.j);
        }
        if ((this.e & 2) != 0) {
            iA += AbstractC2209ns.a(4, this.k);
        }
        int iA2 = 0;
        while (true) {
            int size = this.l.size();
            AJ aj = this.l;
            if (i3 >= size) {
                int iC4 = this.d.c() + aj.size() + iA + iA2;
                this.c = iC4;
                return iC4;
            }
            Object objE = aj.e(i3);
            iA2 += objE instanceof String ? AbstractC0793Rd.a((String) objE) : AbstractC0793Rd.a((U7) objE);
            i3++;
        }
    }

    @Override // com.android.tools.r8.internal.TN
    public final SN d() {
        return this == n ? new C0644Lj() : new C0644Lj().a(this);
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
        if (!(obj instanceof C0669Mj)) {
            return super.equals(obj);
        }
        C0669Mj c0669Mj = (C0669Mj) obj;
        if (!((C0945Wz) this.f).equals(c0669Mj.f)) {
            return false;
        }
        if (!((C0945Wz) this.h).equals(c0669Mj.h)) {
            return false;
        }
        int i = this.e & 1;
        if ((i != 0) != ((c0669Mj.e & 1) != 0)) {
            return false;
        }
        if (i != 0) {
            Object obj2 = this.j;
            if (obj2 instanceof String) {
                str3 = (String) obj2;
            } else {
                U7 u7 = (U7) obj2;
                String strC = u7.c();
                if (u7.a()) {
                    this.j = strC;
                }
                str3 = strC;
            }
            Object obj3 = c0669Mj.j;
            if (obj3 instanceof String) {
                str4 = (String) obj3;
            } else {
                U7 u8 = (U7) obj3;
                String strC2 = u8.c();
                if (u8.a()) {
                    c0669Mj.j = strC2;
                }
                str4 = strC2;
            }
            if (!str3.equals(str4)) {
                return false;
            }
        }
        int i2 = this.e & 2;
        if ((i2 != 0) != ((c0669Mj.e & 2) != 0)) {
            return false;
        }
        if (i2 != 0) {
            Object obj4 = this.k;
            if (obj4 instanceof String) {
                str = (String) obj4;
            } else {
                U7 u9 = (U7) obj4;
                String strC3 = u9.c();
                if (u9.a()) {
                    this.k = strC3;
                }
                str = strC3;
            }
            Object obj5 = c0669Mj.k;
            if (obj5 instanceof String) {
                str2 = (String) obj5;
            } else {
                U7 u10 = (U7) obj5;
                String strC4 = u10.c();
                if (u10.a()) {
                    c0669Mj.k = strC4;
                }
                str2 = strC4;
            }
            if (!str.equals(str2)) {
                return false;
            }
        }
        return this.l.equals(c0669Mj.l) && this.d.equals(c0669Mj.d);
    }

    @Override // com.android.tools.r8.internal.WN
    public final C2712tk0 g() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.J0
    public final H0 h() {
        n.getClass();
        return new C0644Lj();
    }

    @Override // com.android.tools.r8.internal.J0
    public final int hashCode() {
        String str;
        String str2;
        int i = this.b;
        if (i != 0) {
            return i;
        }
        int iHashCode = AbstractC0877Uj.U.hashCode() + 779;
        C0945Wz c0945Wz = (C0945Wz) this.f;
        if (c0945Wz.d > 0) {
            iHashCode = Z50.a(iHashCode, 37, 1, 53) + c0945Wz.hashCode();
        }
        C0945Wz c0945Wz2 = (C0945Wz) this.h;
        if (c0945Wz2.d > 0) {
            iHashCode = Z50.a(iHashCode, 37, 2, 53) + c0945Wz2.hashCode();
        }
        if ((this.e & 1) != 0) {
            int iA = Z50.a(iHashCode, 37, 3, 53);
            Object obj = this.j;
            if (obj instanceof String) {
                str2 = (String) obj;
            } else {
                U7 u7 = (U7) obj;
                String strC = u7.c();
                if (u7.a()) {
                    this.j = strC;
                }
                str2 = strC;
            }
            iHashCode = iA + str2.hashCode();
        }
        if ((this.e & 2) != 0) {
            int iA2 = Z50.a(iHashCode, 37, 4, 53);
            Object obj2 = this.k;
            if (obj2 instanceof String) {
                str = (String) obj2;
            } else {
                U7 u8 = (U7) obj2;
                String strC2 = u8.c();
                if (u8.a()) {
                    this.k = strC2;
                }
                str = strC2;
            }
            iHashCode = iA2 + str.hashCode();
        }
        if (this.l.size() > 0) {
            iHashCode = Z50.a(iHashCode, 37, 6, 53) + this.l.hashCode();
        }
        int iHashCode2 = this.d.hashCode() + (iHashCode * 29);
        this.b = iHashCode2;
        return iHashCode2;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final C2123ms j() {
        return AbstractC0877Uj.V.a(C0669Mj.class, C0644Lj.class);
    }

    public C0669Mj(AbstractC0911Vr abstractC0911Vr) {
        super(abstractC0911Vr);
        this.g = -1;
        this.i = -1;
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
        this.m = (byte) 1;
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final H0 a(C0859Tr c0859Tr) {
        return new C0644Lj(c0859Tr);
    }
}
