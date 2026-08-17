package com.android.tools.r8.internal;

import com.android.tools.r8.AbstractC0007c;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class R60 extends AbstractC2209ns {
    public static final R60 h = new R60();
    public static final P60 i = new P60();
    public int e;
    public Object f;
    public byte g;

    public R60(AbstractC0911Vr abstractC0911Vr) {
        super(abstractC0911Vr);
        this.e = 0;
        this.g = (byte) -1;
    }

    @Override // com.android.tools.r8.internal.TN
    public final void a(AbstractC0793Rd abstractC0793Rd) {
        if (this.e == 1) {
            abstractC0793Rd.a(1, (J70) this.f);
        }
        if (this.e == 2) {
            abstractC0793Rd.a(2, (Y70) this.f);
        }
        if (this.e == 3) {
            abstractC0793Rd.a(3, (F70) this.f);
        }
        if (this.e == 4) {
            abstractC0793Rd.a(4, (C3174z80) this.f);
        }
        if (this.e == 5) {
            abstractC0793Rd.a(5, (L60) this.f);
        }
        if (this.e == 6) {
            abstractC0793Rd.a(6, (O60) this.f);
        }
        if (this.e == 7) {
            abstractC0793Rd.a(7, (C70) this.f);
        }
        this.d.a(abstractC0793Rd);
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return h;
    }

    @Override // com.android.tools.r8.internal.TN
    public final int c() {
        int iA;
        int i2 = this.c;
        if (i2 != -1) {
            return i2;
        }
        if (this.e == 1) {
            J70 j70 = (J70) this.f;
            iA = AbstractC0793Rd.a(j70) + AbstractC0793Rd.b(1);
        } else {
            iA = 0;
        }
        if (this.e == 2) {
            Y70 y70 = (Y70) this.f;
            iA += AbstractC0793Rd.a(y70) + AbstractC0793Rd.b(2);
        }
        if (this.e == 3) {
            F70 f70 = (F70) this.f;
            iA += AbstractC0793Rd.a(f70) + AbstractC0793Rd.b(3);
        }
        if (this.e == 4) {
            C3174z80 c3174z80 = (C3174z80) this.f;
            iA += AbstractC0793Rd.a(c3174z80) + AbstractC0793Rd.b(4);
        }
        if (this.e == 5) {
            L60 l60 = (L60) this.f;
            iA += AbstractC0793Rd.a(l60) + AbstractC0793Rd.b(5);
        }
        if (this.e == 6) {
            O60 o60 = (O60) this.f;
            iA += AbstractC0793Rd.a(o60) + AbstractC0793Rd.b(6);
        }
        if (this.e == 7) {
            C70 c70 = (C70) this.f;
            iA += AbstractC0793Rd.a(c70) + AbstractC0793Rd.b(7);
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
        if (!(obj instanceof R60)) {
            return super.equals(obj);
        }
        R60 r60 = (R60) obj;
        if (!AbstractC0007c.b(m(), r60.m())) {
            return false;
        }
        int i2 = this.e;
        switch (i2) {
            case 1:
                if (!l().equals(r60.l())) {
                    return false;
                }
                break;
            case 2:
                if (!(i2 == 2 ? (Y70) this.f : Y70.g).equals(r60.e == 2 ? (Y70) r60.f : Y70.g)) {
                    return false;
                }
                break;
            case XmlPullParser.END_TAG /* 3 */:
                if (!(i2 == 3 ? (F70) this.f : F70.g).equals(r60.e == 3 ? (F70) r60.f : F70.g)) {
                    return false;
                }
                break;
            case 4:
                if (!(i2 == 4 ? (C3174z80) this.f : C3174z80.h).equals(r60.e == 4 ? (C3174z80) r60.f : C3174z80.h)) {
                    return false;
                }
                break;
            case XmlPullParser.CDSECT /* 5 */:
                if (!k().equals(r60.k())) {
                    return false;
                }
                break;
            case XmlPullParser.ENTITY_REF /* 6 */:
                if (!(i2 == 6 ? (O60) this.f : O60.f).equals(r60.e == 6 ? (O60) r60.f : O60.f)) {
                    return false;
                }
                break;
            case 7:
                if (!(i2 == 7 ? (C70) this.f : C70.h).equals(r60.e == 7 ? (C70) r60.f : C70.h)) {
                    return false;
                }
                break;
        }
        return this.d.equals(r60.d);
    }

    @Override // com.android.tools.r8.internal.WN
    public final C2712tk0 g() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.J0
    public final H0 h() {
        return h.d();
    }

    @Override // com.android.tools.r8.internal.J0
    public final int hashCode() {
        int iA;
        int iHashCode;
        int i2 = this.b;
        if (i2 != 0) {
            return i2;
        }
        int iHashCode2 = AbstractC1468f90.M.hashCode() + 779;
        int i3 = this.e;
        switch (i3) {
            case 1:
                iA = Z50.a(iHashCode2, 37, 1, 53);
                iHashCode = l().hashCode();
                break;
            case 2:
                iA = Z50.a(iHashCode2, 37, 2, 53);
                iHashCode = (i3 == 2 ? (Y70) this.f : Y70.g).hashCode();
                break;
            case XmlPullParser.END_TAG /* 3 */:
                iA = Z50.a(iHashCode2, 37, 3, 53);
                iHashCode = (i3 == 3 ? (F70) this.f : F70.g).hashCode();
                break;
            case 4:
                iA = Z50.a(iHashCode2, 37, 4, 53);
                iHashCode = (i3 == 4 ? (C3174z80) this.f : C3174z80.h).hashCode();
                break;
            case XmlPullParser.CDSECT /* 5 */:
                iA = Z50.a(iHashCode2, 37, 5, 53);
                iHashCode = k().hashCode();
                break;
            case XmlPullParser.ENTITY_REF /* 6 */:
                iA = Z50.a(iHashCode2, 37, 6, 53);
                iHashCode = (i3 == 6 ? (O60) this.f : O60.f).hashCode();
                break;
            case 7:
                iA = Z50.a(iHashCode2, 37, 7, 53);
                iHashCode = (i3 == 7 ? (C70) this.f : C70.h).hashCode();
                break;
            default:
                int iHashCode3 = this.d.hashCode() + (iHashCode2 * 29);
                this.b = iHashCode3;
                return iHashCode3;
        }
        iHashCode2 = iA + iHashCode;
        int iHashCode4 = this.d.hashCode() + (iHashCode2 * 29);
        this.b = iHashCode4;
        return iHashCode4;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final C2123ms j() {
        return AbstractC1468f90.N.a(R60.class, Q60.class);
    }

    public final L60 k() {
        return this.e == 5 ? (L60) this.f : L60.h;
    }

    public final J70 l() {
        return this.e == 1 ? (J70) this.f : J70.m;
    }

    public final int m() {
        switch (this.e) {
            case 0:
                return 8;
            case 1:
                return 1;
            case 2:
                return 2;
            case XmlPullParser.END_TAG /* 3 */:
                return 3;
            case 4:
                return 4;
            case XmlPullParser.CDSECT /* 5 */:
                return 5;
            case XmlPullParser.ENTITY_REF /* 6 */:
                return 6;
            case 7:
                return 7;
            default:
                return 0;
        }
    }

    @Override // com.android.tools.r8.internal.TN
    /* JADX INFO: renamed from: n, reason: merged with bridge method [inline-methods] */
    public final Q60 d() {
        return this == h ? new Q60() : new Q60().a(this);
    }

    public R60() {
        this.e = 0;
        this.g = (byte) -1;
    }

    @Override // com.android.tools.r8.internal.VN
    public final boolean a() {
        byte b = this.g;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        this.g = (byte) 1;
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final H0 a(C0859Tr c0859Tr) {
        return new Q60(c0859Tr);
    }
}
