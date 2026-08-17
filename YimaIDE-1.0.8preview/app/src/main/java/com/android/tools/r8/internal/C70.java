package com.android.tools.r8.internal;

import com.android.apksig.internal.util.AndroidSdkVersion;
import com.android.tools.r8.AbstractC0007c;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C70 extends AbstractC2209ns {
    public static final C70 h = new C70();
    public static final C2746u70 i = new C2746u70();
    public int e;
    public Object f;
    public byte g;

    public C70(AbstractC0911Vr abstractC0911Vr) {
        super(abstractC0911Vr);
        this.e = 0;
        this.g = (byte) -1;
    }

    @Override // com.android.tools.r8.internal.TN
    public final void a(AbstractC0793Rd abstractC0793Rd) throws C0741Pd {
        if (this.e == 1) {
            abstractC0793Rd.a(1, (B70) this.f);
        }
        if (this.e == 2) {
            abstractC0793Rd.a(2, (C3087y70) this.f);
        }
        if (this.e == 3) {
            float fFloatValue = ((Float) this.f).floatValue();
            abstractC0793Rd.getClass();
            int iFloatToRawIntBits = Float.floatToRawIntBits(fFloatValue);
            C0689Nd c0689Nd = (C0689Nd) abstractC0793Rd;
            c0689Nd.c(3, 5);
            c0689Nd.d(iFloatToRawIntBits);
        }
        if (this.e == 4) {
            float fFloatValue2 = ((Float) this.f).floatValue();
            abstractC0793Rd.getClass();
            int iFloatToRawIntBits2 = Float.floatToRawIntBits(fFloatValue2);
            C0689Nd c0689Nd2 = (C0689Nd) abstractC0793Rd;
            c0689Nd2.c(4, 5);
            c0689Nd2.d(iFloatToRawIntBits2);
        }
        if (this.e == 5) {
            float fFloatValue3 = ((Float) this.f).floatValue();
            abstractC0793Rd.getClass();
            int iFloatToRawIntBits3 = Float.floatToRawIntBits(fFloatValue3);
            C0689Nd c0689Nd3 = (C0689Nd) abstractC0793Rd;
            c0689Nd3.c(5, 5);
            c0689Nd3.d(iFloatToRawIntBits3);
        }
        if (this.e == 6) {
            int iIntValue = ((Integer) this.f).intValue();
            C0689Nd c0689Nd4 = (C0689Nd) abstractC0793Rd;
            c0689Nd4.c(6, 0);
            c0689Nd4.e(iIntValue);
        }
        if (this.e == 7) {
            int iIntValue2 = ((Integer) this.f).intValue();
            C0689Nd c0689Nd5 = (C0689Nd) abstractC0793Rd;
            c0689Nd5.c(7, 0);
            c0689Nd5.f(iIntValue2);
        }
        if (this.e == 8) {
            abstractC0793Rd.a(8, ((Boolean) this.f).booleanValue());
        }
        if (this.e == 9) {
            int iIntValue3 = ((Integer) this.f).intValue();
            C0689Nd c0689Nd6 = (C0689Nd) abstractC0793Rd;
            c0689Nd6.c(9, 0);
            c0689Nd6.f(iIntValue3);
        }
        if (this.e == 10) {
            int iIntValue4 = ((Integer) this.f).intValue();
            C0689Nd c0689Nd7 = (C0689Nd) abstractC0793Rd;
            c0689Nd7.c(10, 0);
            c0689Nd7.f(iIntValue4);
        }
        if (this.e == 11) {
            int iIntValue5 = ((Integer) this.f).intValue();
            C0689Nd c0689Nd8 = (C0689Nd) abstractC0793Rd;
            c0689Nd8.c(11, 0);
            c0689Nd8.f(iIntValue5);
        }
        if (this.e == 12) {
            int iIntValue6 = ((Integer) this.f).intValue();
            C0689Nd c0689Nd9 = (C0689Nd) abstractC0793Rd;
            c0689Nd9.c(12, 0);
            c0689Nd9.f(iIntValue6);
        }
        if (this.e == 13) {
            int iIntValue7 = ((Integer) this.f).intValue();
            C0689Nd c0689Nd10 = (C0689Nd) abstractC0793Rd;
            c0689Nd10.c(13, 0);
            c0689Nd10.f(iIntValue7);
        }
        if (this.e == 14) {
            int iIntValue8 = ((Integer) this.f).intValue();
            C0689Nd c0689Nd11 = (C0689Nd) abstractC0793Rd;
            c0689Nd11.c(14, 0);
            c0689Nd11.f(iIntValue8);
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
            B70 b70 = (B70) this.f;
            iA = AbstractC0793Rd.a(b70) + AbstractC0793Rd.b(1);
        } else {
            iA = 0;
        }
        if (this.e == 2) {
            C3087y70 c3087y70 = (C3087y70) this.f;
            iA += AbstractC0793Rd.a(c3087y70) + AbstractC0793Rd.b(2);
        }
        if (this.e == 3) {
            ((Float) this.f).getClass();
            iA += AbstractC0793Rd.b(3) + 4;
        }
        if (this.e == 4) {
            ((Float) this.f).getClass();
            iA += AbstractC0793Rd.b(4) + 4;
        }
        if (this.e == 5) {
            ((Float) this.f).getClass();
            iA += AbstractC0793Rd.b(5) + 4;
        }
        if (this.e == 6) {
            iA += AbstractC0793Rd.a(6, ((Integer) this.f).intValue());
        }
        if (this.e == 7) {
            iA = AbstractC0484Ff.a(((Integer) this.f).intValue(), AbstractC0793Rd.b(7), iA);
        }
        if (this.e == 8) {
            ((Boolean) this.f).getClass();
            iA += AbstractC0793Rd.b(8) + 1;
        }
        if (this.e == 9) {
            iA = AbstractC0484Ff.a(((Integer) this.f).intValue(), AbstractC0793Rd.b(9), iA);
        }
        if (this.e == 10) {
            iA = AbstractC0484Ff.a(((Integer) this.f).intValue(), AbstractC0793Rd.b(10), iA);
        }
        if (this.e == 11) {
            iA = AbstractC0484Ff.a(((Integer) this.f).intValue(), AbstractC0793Rd.b(11), iA);
        }
        if (this.e == 12) {
            iA = AbstractC0484Ff.a(((Integer) this.f).intValue(), AbstractC0793Rd.b(12), iA);
        }
        if (this.e == 13) {
            iA = AbstractC0484Ff.a(((Integer) this.f).intValue(), AbstractC0793Rd.b(13), iA);
        }
        if (this.e == 14) {
            iA = AbstractC0484Ff.a(((Integer) this.f).intValue(), AbstractC0793Rd.b(14), iA);
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
        if (!(obj instanceof C70)) {
            return super.equals(obj);
        }
        C70 c70 = (C70) obj;
        if (!AbstractC0007c.b(k(), c70.k())) {
            return false;
        }
        int i2 = this.e;
        switch (i2) {
            case 1:
                if (!(i2 == 1 ? (B70) this.f : B70.f).equals(c70.e == 1 ? (B70) c70.f : B70.f)) {
                    return false;
                }
                break;
            case 2:
                if (!(i2 == 2 ? (C3087y70) this.f : C3087y70.f).equals(c70.e == 2 ? (C3087y70) c70.f : C3087y70.f)) {
                    return false;
                }
                break;
            case XmlPullParser.END_TAG /* 3 */:
                if (Float.floatToIntBits(i2 == 3 ? ((Float) this.f).floatValue() : 0.0f) != Float.floatToIntBits(c70.e == 3 ? ((Float) c70.f).floatValue() : 0.0f)) {
                    return false;
                }
                break;
            case 4:
                if (Float.floatToIntBits(i2 == 4 ? ((Float) this.f).floatValue() : 0.0f) != Float.floatToIntBits(c70.e == 4 ? ((Float) c70.f).floatValue() : 0.0f)) {
                    return false;
                }
                break;
            case XmlPullParser.CDSECT /* 5 */:
                if (Float.floatToIntBits(i2 == 5 ? ((Float) this.f).floatValue() : 0.0f) != Float.floatToIntBits(c70.e == 5 ? ((Float) c70.f).floatValue() : 0.0f)) {
                    return false;
                }
                break;
            case XmlPullParser.ENTITY_REF /* 6 */:
                if ((i2 == 6 ? ((Integer) this.f).intValue() : 0) != (c70.e == 6 ? ((Integer) c70.f).intValue() : 0)) {
                    return false;
                }
                break;
            case 7:
                if ((i2 == 7 ? ((Integer) this.f).intValue() : 0) != (c70.e == 7 ? ((Integer) c70.f).intValue() : 0)) {
                    return false;
                }
                break;
            case 8:
                if ((i2 == 8 ? ((Boolean) this.f).booleanValue() : false) != (c70.e == 8 ? ((Boolean) c70.f).booleanValue() : false)) {
                    return false;
                }
                break;
            case 9:
                if ((i2 == 9 ? ((Integer) this.f).intValue() : 0) != (c70.e == 9 ? ((Integer) c70.f).intValue() : 0)) {
                    return false;
                }
                break;
            case XmlPullParser.DOCDECL /* 10 */:
                if ((i2 == 10 ? ((Integer) this.f).intValue() : 0) != (c70.e == 10 ? ((Integer) c70.f).intValue() : 0)) {
                    return false;
                }
                break;
            case AndroidSdkVersion.HONEYCOMB /* 11 */:
                if ((i2 == 11 ? ((Integer) this.f).intValue() : 0) != (c70.e == 11 ? ((Integer) c70.f).intValue() : 0)) {
                    return false;
                }
                break;
            case 12:
                if ((i2 == 12 ? ((Integer) this.f).intValue() : 0) != (c70.e == 12 ? ((Integer) c70.f).intValue() : 0)) {
                    return false;
                }
                break;
            case 13:
                if ((i2 == 13 ? ((Integer) this.f).intValue() : 0) != (c70.e == 13 ? ((Integer) c70.f).intValue() : 0)) {
                    return false;
                }
                break;
            case 14:
                if ((i2 == 14 ? ((Integer) this.f).intValue() : 0) != (c70.e == 14 ? ((Integer) c70.f).intValue() : 0)) {
                    return false;
                }
                break;
        }
        return this.d.equals(c70.d);
    }

    @Override // com.android.tools.r8.internal.WN
    public final C2712tk0 g() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.J0
    public final H0 h() {
        return h.d();
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:6:0x0015. Please report as an issue. */
    @Override // com.android.tools.r8.internal.J0
    public final int hashCode() {
        int iA;
        int iHashCode;
        int iA2;
        int i2 = this.b;
        if (i2 != 0) {
            return i2;
        }
        int iHashCode2 = AbstractC1468f90.g0.hashCode() + 779;
        int i3 = this.e;
        int iIntValue = 0;
        iIntValue = 0;
        iIntValue = 0;
        iIntValue = 0;
        iIntValue = 0;
        iIntValue = 0;
        iIntValue = 0;
        iIntValue = 0;
        switch (i3) {
            case 1:
                iA = Z50.a(iHashCode2, 37, 1, 53);
                iHashCode = (i3 == 1 ? (B70) this.f : B70.f).hashCode();
                iHashCode2 = iA + iHashCode;
                break;
            case 2:
                iA = Z50.a(iHashCode2, 37, 2, 53);
                iHashCode = (i3 == 2 ? (C3087y70) this.f : C3087y70.f).hashCode();
                iHashCode2 = iA + iHashCode;
                break;
            case XmlPullParser.END_TAG /* 3 */:
                iA = Z50.a(iHashCode2, 37, 3, 53);
                iHashCode = Float.floatToIntBits(i3 == 3 ? ((Float) this.f).floatValue() : 0.0f);
                iHashCode2 = iA + iHashCode;
                break;
            case 4:
                iA = Z50.a(iHashCode2, 37, 4, 53);
                iHashCode = Float.floatToIntBits(i3 == 4 ? ((Float) this.f).floatValue() : 0.0f);
                iHashCode2 = iA + iHashCode;
                break;
            case XmlPullParser.CDSECT /* 5 */:
                iA = Z50.a(iHashCode2, 37, 5, 53);
                iHashCode = Float.floatToIntBits(i3 == 5 ? ((Float) this.f).floatValue() : 0.0f);
                iHashCode2 = iA + iHashCode;
                break;
            case XmlPullParser.ENTITY_REF /* 6 */:
                iA2 = Z50.a(iHashCode2, 37, 6, 53);
                if (i3 == 6) {
                    iIntValue = ((Integer) this.f).intValue();
                }
                iHashCode2 = iA2 + iIntValue;
                break;
            case 7:
                iA2 = Z50.a(iHashCode2, 37, 7, 53);
                if (i3 == 7) {
                    iIntValue = ((Integer) this.f).intValue();
                }
                iHashCode2 = iA2 + iIntValue;
                break;
            case 8:
                iA = Z50.a(iHashCode2, 37, 8, 53);
                iHashCode = AbstractC1556gB.a(i3 == 8 ? ((Boolean) this.f).booleanValue() : false);
                iHashCode2 = iA + iHashCode;
                break;
            case 9:
                iA2 = Z50.a(iHashCode2, 37, 9, 53);
                if (i3 == 9) {
                    iIntValue = ((Integer) this.f).intValue();
                }
                iHashCode2 = iA2 + iIntValue;
                break;
            case XmlPullParser.DOCDECL /* 10 */:
                iA2 = Z50.a(iHashCode2, 37, 10, 53);
                if (i3 == 10) {
                    iIntValue = ((Integer) this.f).intValue();
                }
                iHashCode2 = iA2 + iIntValue;
                break;
            case AndroidSdkVersion.HONEYCOMB /* 11 */:
                iA2 = Z50.a(iHashCode2, 37, 11, 53);
                if (i3 == 11) {
                    iIntValue = ((Integer) this.f).intValue();
                }
                iHashCode2 = iA2 + iIntValue;
                break;
            case 12:
                iA2 = Z50.a(iHashCode2, 37, 12, 53);
                if (i3 == 12) {
                    iIntValue = ((Integer) this.f).intValue();
                }
                iHashCode2 = iA2 + iIntValue;
                break;
            case 13:
                iA2 = Z50.a(iHashCode2, 37, 13, 53);
                if (i3 == 13) {
                    iIntValue = ((Integer) this.f).intValue();
                }
                iHashCode2 = iA2 + iIntValue;
                break;
            case 14:
                iA2 = Z50.a(iHashCode2, 37, 14, 53);
                if (i3 == 14) {
                    iIntValue = ((Integer) this.f).intValue();
                }
                iHashCode2 = iA2 + iIntValue;
                break;
        }
        int iHashCode3 = this.d.hashCode() + (iHashCode2 * 29);
        this.b = iHashCode3;
        return iHashCode3;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final C2123ms j() {
        return AbstractC1468f90.h0.a(C70.class, C2831v70.class);
    }

    public final int k() {
        switch (this.e) {
            case 0:
                return 15;
            case 1:
                return 1;
            case 2:
                return 2;
            case XmlPullParser.END_TAG /* 3 */:
                return 3;
            case 4:
                return 13;
            case XmlPullParser.CDSECT /* 5 */:
                return 14;
            case XmlPullParser.ENTITY_REF /* 6 */:
                return 6;
            case 7:
                return 7;
            case 8:
                return 8;
            case 9:
                return 9;
            case XmlPullParser.DOCDECL /* 10 */:
                return 10;
            case AndroidSdkVersion.HONEYCOMB /* 11 */:
                return 11;
            case 12:
                return 12;
            case 13:
                return 4;
            case 14:
                return 5;
            default:
                return 0;
        }
    }

    @Override // com.android.tools.r8.internal.TN
    /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
    public final C2831v70 d() {
        return this == h ? new C2831v70() : new C2831v70().a(this);
    }

    public C70() {
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
        return new C2831v70(c0859Tr);
    }
}
