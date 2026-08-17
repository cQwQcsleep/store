package com.android.tools.r8.internal;

import com.android.apksig.internal.util.AndroidSdkVersion;
import com.sun.jna.platform.linux.Fcntl;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.gd, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1586gd {
    public final int a;
    public final byte[] b;
    public final int[] c;
    public final String[] d;
    public final C2190ng[] e;
    public final int[] f;
    public final int g;

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public C1586gd(byte[] bArr, boolean z) {
        this.b = bArr;
        if (z && c(6) > 67) {
            w01.a(CX.a(c(6), "Unsupported class file major version "));
            throw null;
        }
        int iD = d(8);
        this.c = new int[iD];
        this.d = new String[iD];
        int i = 10;
        boolean z2 = false;
        int i2 = 0;
        boolean z3 = false;
        int i3 = 1;
        while (true) {
            int iD2 = 4;
            if (i3 >= iD) {
                this.g = i2;
                this.a = i;
                int[] iArr = null;
                this.e = z2 ? new C2190ng[iD] : null;
                if (z3) {
                    char[] cArr = new char[i2];
                    int iA = a();
                    for (int iD3 = d(iA - 2); iD3 > 0; iD3--) {
                        String strC = c(iA, cArr);
                        int iA2 = a(iA + 2);
                        int i4 = iA + 6;
                        if ("BootstrapMethods".equals(strC)) {
                            int iD4 = d(i4);
                            int[] iArr2 = new int[iD4];
                            int iD5 = iA + 8;
                            for (int i5 = 0; i5 < iD4; i5++) {
                                iArr2[i5] = iD5;
                                iD5 += (d(iD5 + 2) * 2) + 4;
                            }
                            iArr = iArr2;
                        } else {
                            iA = i4 + iA2;
                        }
                    }
                    j2d.a();
                    throw null;
                }
                this.f = iArr;
                return;
            }
            int i6 = i3 + 1;
            int i7 = i + 1;
            this.c[i3] = i7;
            switch (bArr[i]) {
                case 1:
                    iD2 = d(i7) + 3;
                    if (iD2 > i2) {
                        i2 = iD2;
                    }
                    i3 = i6;
                    i += iD2;
                    break;
                case 2:
                case 13:
                case 14:
                default:
                    j2d.a();
                    throw null;
                case XmlPullParser.END_TAG /* 3 */:
                case 4:
                case 9:
                case XmlPullParser.DOCDECL /* 10 */:
                case AndroidSdkVersion.HONEYCOMB /* 11 */:
                case 12:
                    iD2 = 5;
                    i3 = i6;
                    i += iD2;
                    break;
                case XmlPullParser.CDSECT /* 5 */:
                case XmlPullParser.ENTITY_REF /* 6 */:
                    i3 += 2;
                    iD2 = 9;
                    i += iD2;
                    break;
                case 7:
                case 8:
                case Fcntl.S_IWGRP /* 16 */:
                case AndroidSdkVersion.KITKAT /* 19 */:
                case 20:
                    iD2 = 3;
                    i3 = i6;
                    i += iD2;
                    break;
                case 15:
                    i3 = i6;
                    i += iD2;
                    break;
                case 17:
                    z2 = true;
                    z3 = true;
                    iD2 = 5;
                    i3 = i6;
                    i += iD2;
                    break;
                case AndroidSdkVersion.JELLY_BEAN_MR2 /* 18 */:
                    z3 = true;
                    iD2 = 5;
                    i3 = i6;
                    i += iD2;
                    break;
            }
        }
    }

    /*  JADX ERROR: Type inference failed
        jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached with updateSeq = 40681. Try increasing type updates limit count.
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:79)
        */
    public final void a(com.android.tools.r8.internal.XO r48, com.android.tools.r8.internal.C0874Ug r49, int r50) {
        /*
            Method dump skipped, instruction units count: 4068
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.tools.r8.internal.C1586gd.a(com.android.tools.r8.internal.XO, com.android.tools.r8.internal.Ug, int):void");
    }

    public final Object b(int i, char[] cArr) {
        int[] iArr = this.c;
        int i2 = iArr[i];
        byte[] bArr = this.b;
        byte b = bArr[i2 - 1];
        switch (b) {
            case XmlPullParser.END_TAG /* 3 */:
                return Integer.valueOf(a(i2));
            case 4:
                return Float.valueOf(Float.intBitsToFloat(a(i2)));
            case XmlPullParser.CDSECT /* 5 */:
                return Long.valueOf(b(i2));
            case XmlPullParser.ENTITY_REF /* 6 */:
                return Double.valueOf(Double.longBitsToDouble(b(i2)));
            case 7:
                return C3050xi0.e(c(i2, cArr));
            case 8:
                return c(i2, cArr);
            default:
                switch (b) {
                    case 15:
                        int i3 = bArr[i2] & 255;
                        int i4 = iArr[d(i2 + 1)];
                        int i5 = this.c[d(i4 + 2)];
                        return new C0497Fs(i3, a(i4, cArr), c(i5, cArr), c(i5 + 2, cArr), this.b[i4 - 1] == 11);
                    case Fcntl.S_IWGRP /* 16 */:
                        return C3050xi0.d(c(i2, cArr));
                    case 17:
                        C2190ng c2190ng = this.e[i];
                        if (c2190ng != null) {
                            return c2190ng;
                        }
                        int i6 = iArr[d(i2 + 2)];
                        String strC = c(i6, cArr);
                        String strC2 = c(i6 + 2, cArr);
                        int i7 = this.f[d(i2)];
                        C0497Fs c0497Fs = (C0497Fs) b(d(i7), cArr);
                        int iD = d(i7 + 2);
                        Object[] objArr = new Object[iD];
                        int i8 = i7 + 4;
                        for (int i9 = 0; i9 < iD; i9++) {
                            objArr[i9] = b(d(i8), cArr);
                            i8 += 2;
                        }
                        C2190ng[] c2190ngArr = this.e;
                        C2190ng c2190ng2 = new C2190ng(strC, strC2, c0497Fs, objArr);
                        c2190ngArr[i] = c2190ng2;
                        return c2190ng2;
                    default:
                        j2d.a();
                        return null;
                }
        }
    }

    public final String c(int i, char[] cArr) {
        int iD = d(i);
        if (i == 0 || iD == 0) {
            return null;
        }
        String[] strArr = this.d;
        String str = strArr[iD];
        if (str != null) {
            return str;
        }
        int i2 = this.c[iD];
        String strA = a(cArr, i2 + 2, d(i2));
        strArr[iD] = strA;
        return strA;
    }

    public final int d(int i) {
        byte[] bArr = this.b;
        return (bArr[i + 1] & 255) | ((bArr[i] & 255) << 8);
    }

    public final short c(int i) {
        byte[] bArr = this.b;
        return (short) ((bArr[i + 1] & 255) | ((bArr[i] & 255) << 8));
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public C1586gd(byte[] bArr) {
        this(bArr, true);
        int length = bArr.length;
    }

    /* JADX WARN: Code duplicated, block: B:15:0x004c A[FALL_THROUGH] */
    public final int[] b(XO xo, C0874Ug c0874Ug, int i, boolean z) {
        int i2;
        char[] cArr = c0874Ug.c;
        int iD = d(i);
        int[] iArr = new int[iD];
        int iA = i + 2;
        for (int i3 = 0; i3 < iD; i3++) {
            iArr[i3] = iA;
            int iA2 = a(iA);
            int i4 = iA2 >>> 24;
            if (i4 != 23) {
                switch (i4) {
                    default:
                        switch (i4) {
                            case 64:
                            case 65:
                                int iD2 = d(iA + 1);
                                i2 = iA + 3;
                                while (true) {
                                    int i5 = iD2 - 1;
                                    if (iD2 > 0) {
                                        int iD3 = d(i2);
                                        int iD4 = d(i2 + 2);
                                        i2 += 6;
                                        b(iD3, c0874Ug.g);
                                        b(iD3 + iD4, c0874Ug.g);
                                        iD2 = i5;
                                    }
                                    break;
                                }
                                break;
                            case 66:
                            case 67:
                            case 68:
                            case 69:
                            case 70:
                                break;
                            case 71:
                            case 72:
                            case 73:
                            case 74:
                            case 75:
                                i2 = iA + 4;
                                break;
                            default:
                                j2d.a();
                                return null;
                        }
                    case Fcntl.S_IWGRP /* 16 */:
                    case 17:
                    case AndroidSdkVersion.JELLY_BEAN_MR2 /* 18 */:
                        i2 = iA + 3;
                        break;
                }
            } else {
                i2 = iA + 3;
            }
            byte[] bArr = this.b;
            int i6 = bArr[i2] & 255;
            if (i4 == 66) {
                C3052xj0 c3052xj0 = i6 != 0 ? new C3052xj0(i2, bArr) : null;
                int i7 = (i6 * 2) + 1 + i2;
                iA = a(xo.b(iA2 & (-256), c3052xj0, c(i7, cArr), z), i7 + 2, true, cArr);
            } else {
                iA = a((J2) null, (i6 * 2) + 3 + i2, true, cArr);
            }
        }
        return iArr;
    }

    public final long b(int i) {
        return (((long) a(i + 4)) & 4294967295L) | (((long) a(i)) << 32);
    }

    public static WI b(int i, WI[] wiArr) {
        if (wiArr[i] == null) {
            wiArr[i] = new WI();
        }
        WI wi = wiArr[i];
        wi.b = (short) (wi.b & (-2));
        return wi;
    }

    public final void a(AbstractC2526rd abstractC2526rd, H4[] h4Arr, int i) {
        String str;
        String str2;
        int i2;
        int i3;
        C0874Ug c0874Ug;
        String str3;
        C1586gd c1586gd;
        String[] strArr;
        String[] strArr2;
        int i4;
        int i5;
        int i6;
        C1586gd c1586gd2 = this;
        C0874Ug c0874Ug2 = new C0874Ug();
        c0874Ug2.a = h4Arr;
        c0874Ug2.b = i;
        char[] cArr = new char[c1586gd2.g];
        c0874Ug2.c = cArr;
        int i7 = c1586gd2.a;
        int iD = c1586gd2.d(i7);
        String strA = c1586gd2.a(i7 + 2, cArr);
        String strA2 = c1586gd2.a(i7 + 4, cArr);
        int iD2 = c1586gd2.d(i7 + 6);
        String[] strArr3 = new String[iD2];
        int i8 = i7 + 8;
        for (int i9 = 0; i9 < iD2; i9++) {
            strArr3[i9] = c1586gd2.a(i8, cArr);
            i8 += 2;
        }
        int iA = c1586gd2.a();
        int iD3 = c1586gd2.d(iA - 2);
        String str4 = null;
        String strA3 = null;
        String strA4 = null;
        String strA5 = null;
        String strC = null;
        H4 h4 = null;
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        int i15 = 0;
        int i16 = 0;
        int i17 = 0;
        int i18 = 0;
        int i19 = 0;
        int i20 = 0;
        while (true) {
            String str5 = strA;
            String str6 = strA2;
            String[] strArr4 = strArr3;
            int i21 = i8;
            String str7 = strA4;
            String str8 = "Signature";
            if (iD3 > 0) {
                int i22 = iA;
                String strC2 = c1586gd2.c(i22, cArr);
                int iA2 = c1586gd2.a(i22 + 2);
                String strC3 = str4;
                int i23 = i22 + 6;
                String str9 = strA3;
                if ("SourceFile".equals(strC2)) {
                    strC3 = c1586gd2.c(i23, cArr);
                } else {
                    if ("InnerClasses".equals(strC2)) {
                        i6 = iD;
                        i4 = i23;
                        i11 = i4;
                    } else if ("EnclosingMethod".equals(strC2)) {
                        i6 = iD;
                        i4 = i23;
                        i12 = i4;
                    } else {
                        if ("NestHost".equals(strC2)) {
                            strA4 = c1586gd2.a(i23, cArr);
                            i6 = iD;
                            i4 = i23;
                            strA3 = str9;
                            i5 = iA2;
                            c1586gd2 = c1586gd2;
                        } else if ("NestMembers".equals(strC2)) {
                            i6 = iD;
                            i4 = i23;
                            i17 = i4;
                        } else if ("PermittedSubclasses".equals(strC2)) {
                            i6 = iD;
                            i4 = i23;
                            i18 = i4;
                        } else if ("Signature".equals(strC2)) {
                            strC = c1586gd2.c(i23, cArr);
                        } else if ("RuntimeVisibleAnnotations".equals(strC2)) {
                            i6 = iD;
                            i4 = i23;
                            i13 = i4;
                        } else if ("RuntimeVisibleTypeAnnotations".equals(strC2)) {
                            i6 = iD;
                            i4 = i23;
                            i15 = i4;
                        } else {
                            if ("Deprecated".equals(strC2)) {
                                i6 = iD | 131072;
                            } else if ("Synthetic".equals(strC2)) {
                                i6 = iD | 4096;
                            } else if ("SourceDebugExtension".equals(strC2)) {
                                if (iA2 <= c1586gd2.b.length - i23) {
                                    strA3 = c1586gd2.a(new char[iA2], i23, iA2);
                                    i6 = iD;
                                    i4 = i23;
                                    strA4 = str7;
                                    i5 = iA2;
                                    c1586gd2 = c1586gd2;
                                } else {
                                    j2d.a();
                                    return;
                                }
                            } else if ("RuntimeInvisibleAnnotations".equals(strC2)) {
                                i6 = iD;
                                i4 = i23;
                                i14 = i4;
                            } else if ("RuntimeInvisibleTypeAnnotations".equals(strC2)) {
                                i6 = iD;
                                i4 = i23;
                                i16 = i4;
                            } else if ("Record".equals(strC2)) {
                                i6 = 65536 | iD;
                                i4 = i23;
                                i19 = i4;
                            } else if ("Module".equals(strC2)) {
                                i6 = iD;
                                i4 = i23;
                                i20 = i4;
                            } else if ("ModuleMainClass".equals(strC2)) {
                                strA5 = c1586gd2.a(i23, cArr);
                            } else if ("ModulePackages".equals(strC2)) {
                                i6 = iD;
                                i4 = i23;
                                i10 = i4;
                            } else {
                                if ("BootstrapMethods".equals(strC2)) {
                                    i4 = i23;
                                    i5 = iA2;
                                } else {
                                    i4 = i23;
                                    i5 = iA2;
                                    H4 h4A = c1586gd2.a(h4Arr, strC2, i4, i5, cArr, -1, null);
                                    h4A.c = h4;
                                    h4 = h4A;
                                }
                                i6 = iD;
                                strA3 = str9;
                                i20 = i20;
                                strA4 = str7;
                            }
                            i4 = i23;
                        }
                        int i24 = i4 + i5;
                        iD3--;
                        iD = i6;
                        strA = str5;
                        strA2 = str6;
                        strArr3 = strArr4;
                        i8 = i21;
                        str4 = strC3;
                        iA = i24;
                        c1586gd2 = c1586gd2;
                    }
                    strA4 = str7;
                    strA3 = str9;
                    i5 = iA2;
                    c1586gd2 = c1586gd2;
                    int i25 = i4 + i5;
                    iD3--;
                    iD = i6;
                    strA = str5;
                    strA2 = str6;
                    strArr3 = strArr4;
                    i8 = i21;
                    str4 = strC3;
                    iA = i25;
                    c1586gd2 = c1586gd2;
                }
                i6 = iD;
                i4 = i23;
                strA4 = str7;
                strA3 = str9;
                i5 = iA2;
                c1586gd2 = c1586gd2;
                int i26 = i4 + i5;
                iD3--;
                iD = i6;
                strA = str5;
                strA2 = str6;
                strArr3 = strArr4;
                i8 = i21;
                str4 = strC3;
                iA = i26;
                c1586gd2 = c1586gd2;
            } else {
                C1586gd c1586gd3 = c1586gd2;
                String str10 = str4;
                String str11 = strA3;
                H4 h5 = h4;
                int i27 = i20;
                String str12 = "Synthetic";
                String str13 = "RuntimeInvisibleTypeAnnotations";
                C1586gd c1586gd4 = c1586gd3;
                String str14 = "RuntimeVisibleAnnotations";
                String str15 = "RuntimeVisibleTypeAnnotations";
                String str16 = strC;
                String str17 = "Deprecated";
                String str18 = "RuntimeInvisibleAnnotations";
                AbstractC2526rd abstractC2526rd2 = abstractC2526rd;
                abstractC2526rd2.a(c1586gd3.a(c1586gd3.c[1] - 7), iD, str5, str16, str6, strArr4);
                if ((i & 2) == 0 && (str10 != null || str11 != null)) {
                    abstractC2526rd2.a(str10, str11);
                }
                if (i27 != 0) {
                    char[] cArr2 = c0874Ug2.c;
                    int i28 = i27 + 6;
                    AbstractC2167nP abstractC2167nPA = abstractC2526rd2.a(c1586gd4.d(i27 + 2), c1586gd4.c(c1586gd4.c[c1586gd4.d(i27)], cArr2), c1586gd4.c(i27 + 4, cArr2));
                    if (abstractC2167nPA != null) {
                        if (strA5 != null) {
                            abstractC2167nPA.a(strA5);
                        }
                        int i29 = i10;
                        if (i29 != 0) {
                            int iD4 = c1586gd4.d(i29);
                            int i30 = i29 + 2;
                            while (true) {
                                int i31 = iD4 - 1;
                                if (iD4 <= 0) {
                                    break;
                                }
                                abstractC2167nPA.b(c1586gd4.c(c1586gd4.c[c1586gd4.d(i30)], cArr2));
                                i30 += 2;
                                iD4 = i31;
                            }
                        }
                        int iD5 = c1586gd4.d(i28);
                        int i32 = i27 + 8;
                        while (true) {
                            int i33 = iD5 - 1;
                            if (iD5 <= 0) {
                                break;
                            }
                            String strC4 = c1586gd4.c(c1586gd4.c[c1586gd4.d(i32)], cArr2);
                            int iD6 = c1586gd4.d(i32 + 2);
                            String strC5 = c1586gd4.c(i32 + 4, cArr2);
                            i32 += 6;
                            abstractC2167nPA.a(iD6, strC4, strC5);
                            iD5 = i33;
                        }
                        int iD7 = c1586gd4.d(i32);
                        int i34 = i32 + 2;
                        while (true) {
                            int i35 = iD7 - 1;
                            if (iD7 <= 0) {
                                break;
                            }
                            String strC6 = c1586gd4.c(c1586gd4.c[c1586gd4.d(i34)], cArr2);
                            int iD8 = c1586gd4.d(i34 + 2);
                            int iD9 = c1586gd4.d(i34 + 4);
                            i34 += 6;
                            if (iD9 != 0) {
                                strArr2 = new String[iD9];
                                for (int i36 = 0; i36 < iD9; i36++) {
                                    strArr2[i36] = c1586gd4.c(c1586gd4.c[c1586gd4.d(i34)], cArr2);
                                    i34 += 2;
                                }
                            } else {
                                strArr2 = null;
                            }
                            abstractC2167nPA.a(strC6, iD8, strArr2);
                            iD7 = i35;
                        }
                        int iD10 = c1586gd4.d(i34);
                        int i37 = i34 + 2;
                        while (true) {
                            int i38 = iD10 - 1;
                            if (iD10 <= 0) {
                                break;
                            }
                            String strC7 = c1586gd4.c(c1586gd4.c[c1586gd4.d(i37)], cArr2);
                            int iD11 = c1586gd4.d(i37 + 2);
                            int iD12 = c1586gd4.d(i37 + 4);
                            i37 += 6;
                            if (iD12 != 0) {
                                strArr = new String[iD12];
                                for (int i39 = 0; i39 < iD12; i39++) {
                                    strArr[i39] = c1586gd4.c(c1586gd4.c[c1586gd4.d(i37)], cArr2);
                                    i37 += 2;
                                }
                            } else {
                                strArr = null;
                            }
                            abstractC2167nPA.b(strC7, iD11, strArr);
                            iD10 = i38;
                        }
                        int iD13 = c1586gd4.d(i37);
                        int i40 = i37 + 2;
                        while (true) {
                            int i41 = iD13 - 1;
                            if (iD13 <= 0) {
                                break;
                            }
                            abstractC2167nPA.c(c1586gd4.a(i40, cArr2));
                            i40 += 2;
                            iD13 = i41;
                        }
                        int iD14 = c1586gd4.d(i40);
                        int i42 = i40 + 2;
                        while (true) {
                            int i43 = iD14 - 1;
                            if (iD14 <= 0) {
                                break;
                            }
                            String strA6 = c1586gd4.a(i42, cArr2);
                            int iD15 = c1586gd4.d(i42 + 2);
                            i42 += 4;
                            String[] strArr5 = new String[iD15];
                            for (int i44 = 0; i44 < iD15; i44++) {
                                strArr5[i44] = c1586gd4.a(i42, cArr2);
                                i42 += 2;
                            }
                            abstractC2167nPA.a(strA6, strArr5);
                            iD14 = i43;
                        }
                        abstractC2167nPA.a();
                    }
                }
                if (str7 != null) {
                    abstractC2526rd2.a(str7);
                }
                int i45 = i12;
                if (i45 != 0) {
                    String strA7 = c1586gd4.a(i45, cArr);
                    int iD16 = c1586gd4.d(i45 + 2);
                    abstractC2526rd2.a(strA7, iD16 == 0 ? null : c1586gd4.c(c1586gd4.c[iD16], cArr), iD16 == 0 ? null : c1586gd4.c(c1586gd4.c[iD16] + 2, cArr));
                }
                int i46 = i13;
                if (i46 != 0) {
                    int iD17 = c1586gd4.d(i46);
                    int iA3 = i46 + 2;
                    while (true) {
                        int i47 = iD17 - 1;
                        if (iD17 <= 0) {
                            break;
                        }
                        iA3 = c1586gd4.a(abstractC2526rd2.a(c1586gd4.c(iA3, cArr), true), iA3 + 2, true, cArr);
                        iD17 = i47;
                    }
                }
                int i48 = i14;
                if (i48 != 0) {
                    int iD18 = c1586gd4.d(i48);
                    int iA4 = i48 + 2;
                    while (true) {
                        int i49 = iD18 - 1;
                        if (iD18 <= 0) {
                            break;
                        }
                        iA4 = c1586gd4.a(abstractC2526rd2.a(c1586gd4.c(iA4, cArr), false), iA4 + 2, true, cArr);
                        iD18 = i49;
                    }
                }
                int i50 = i15;
                if (i50 != 0) {
                    int iD19 = c1586gd4.d(i50);
                    int iA5 = i50 + 2;
                    while (true) {
                        int i51 = iD19 - 1;
                        if (iD19 <= 0) {
                            break;
                        }
                        int iA6 = c1586gd4.a(c0874Ug2, iA5);
                        iA5 = c1586gd4.a(abstractC2526rd2.a(c0874Ug2.h, c0874Ug2.i, c1586gd4.c(iA6, cArr), true), iA6 + 2, true, cArr);
                        iD19 = i51;
                    }
                }
                int i52 = i16;
                if (i52 != 0) {
                    int iD20 = c1586gd4.d(i52);
                    int iA7 = i52 + 2;
                    while (true) {
                        int i53 = iD20 - 1;
                        if (iD20 <= 0) {
                            break;
                        }
                        int iA8 = c1586gd4.a(c0874Ug2, iA7);
                        iA7 = c1586gd4.a(abstractC2526rd2.a(c0874Ug2.h, c0874Ug2.i, c1586gd4.c(iA8, cArr), false), iA8 + 2, true, cArr);
                        iD20 = i53;
                    }
                }
                H4 h6 = h5;
                while (h6 != null) {
                    H4 h7 = h6.c;
                    h6.c = null;
                    abstractC2526rd2.a(h6);
                    h6 = h7;
                }
                int i54 = i17;
                if (i54 != 0) {
                    int iD21 = c1586gd4.d(i54);
                    int i55 = i54 + 2;
                    while (true) {
                        int i56 = iD21 - 1;
                        if (iD21 <= 0) {
                            break;
                        }
                        abstractC2526rd2.b(c1586gd4.a(i55, cArr));
                        i55 += 2;
                        iD21 = i56;
                    }
                }
                int i57 = i18;
                if (i57 != 0) {
                    int iD22 = c1586gd4.d(i57);
                    int i58 = i57 + 2;
                    while (true) {
                        int i59 = iD22 - 1;
                        if (iD22 <= 0) {
                            break;
                        }
                        abstractC2526rd2.c(c1586gd4.a(i58, cArr));
                        i58 += 2;
                        iD22 = i59;
                    }
                }
                int i60 = i11;
                if (i60 != 0) {
                    int iD23 = c1586gd4.d(i60);
                    int i61 = i60 + 2;
                    while (true) {
                        int i62 = iD23 - 1;
                        if (iD23 <= 0) {
                            break;
                        }
                        abstractC2526rd2.a(c1586gd4.d(i61 + 6), c1586gd4.a(i61, cArr), c1586gd4.a(i61 + 2, cArr), c1586gd4.c(i61 + 4, cArr));
                        i61 += 8;
                        iD23 = i62;
                    }
                }
                int i63 = i19;
                if (i63 != 0) {
                    int iD24 = c1586gd4.d(i63);
                    int i64 = i63 + 2;
                    while (true) {
                        int i65 = iD24 - 1;
                        if (iD24 <= 0) {
                            break;
                        }
                        char[] cArr3 = c0874Ug2.c;
                        String strC8 = c1586gd4.c(i64, cArr3);
                        String strC9 = c1586gd4.c(i64 + 2, cArr3);
                        int iD25 = c1586gd4.d(i64 + 4);
                        i64 += 6;
                        int i66 = 0;
                        int i67 = 0;
                        int i68 = 0;
                        String str19 = null;
                        H4 h8 = null;
                        int i69 = 0;
                        while (true) {
                            int i70 = iD25 - 1;
                            if (iD25 <= 0) {
                                break;
                            }
                            String strC10 = c1586gd4.c(i64, cArr3);
                            int iA9 = c1586gd4.a(i64 + 2);
                            int i71 = i64 + 6;
                            if (str8.equals(strC10)) {
                                String strC11 = c1586gd4.c(i71, cArr3);
                                i71 = i66;
                                str19 = strC11;
                                str3 = str14;
                            } else {
                                int i72 = i68;
                                String str20 = str14;
                                if (str20.equals(strC10)) {
                                    str3 = str20;
                                    i68 = i72;
                                } else {
                                    String str21 = str15;
                                    if (str21.equals(strC10)) {
                                        str15 = str21;
                                        i65 = i65;
                                        str12 = str12;
                                        str13 = str13;
                                        str18 = str18;
                                        str3 = str20;
                                        i68 = i71;
                                        c0874Ug2 = c0874Ug2;
                                        str8 = str8;
                                        i71 = i66;
                                        i71 = i68;
                                        c1586gd = c1586gd4;
                                    } else {
                                        String str22 = str18;
                                        if (str22.equals(strC10)) {
                                            str18 = str22;
                                            iA9 = iA9;
                                            str12 = str12;
                                            str13 = str13;
                                            str15 = str21;
                                            str3 = str20;
                                            i68 = i72;
                                            abstractC2526rd2 = abstractC2526rd2;
                                            c1586gd = c1586gd4;
                                            i65 = i65;
                                            c0874Ug2 = c0874Ug2;
                                            str8 = str8;
                                            i67 = i71;
                                            i71 = i66;
                                            i71 = i67;
                                        } else {
                                            String str23 = str13;
                                            if (str23.equals(strC10)) {
                                                i69 = i71;
                                                str13 = str23;
                                                str12 = str12;
                                                str18 = str22;
                                                str15 = str21;
                                                str3 = str20;
                                                i68 = i72;
                                                i71 = i66;
                                                i65 = i65;
                                                i71 = i69;
                                                c1586gd = c1586gd4;
                                            } else {
                                                int i73 = i66;
                                                i71 = i71;
                                                i65 = i65;
                                                c0874Ug2 = c0874Ug2;
                                                str12 = str12;
                                                str13 = str23;
                                                str18 = str22;
                                                str15 = str21;
                                                str3 = str20;
                                                str8 = str8;
                                                i67 = i67;
                                                iA9 = iA9;
                                                abstractC2526rd2 = abstractC2526rd2;
                                                c1586gd = this;
                                                H4 h4A2 = c1586gd.a(c0874Ug2.a, strC10, i71, iA9, cArr3, -1, null);
                                                h4A2.c = h8;
                                                h8 = h4A2;
                                                i71 = i73;
                                                i68 = i72;
                                            }
                                        }
                                    }
                                }
                                int i74 = i71 + iA9;
                                i65 = i65;
                                c1586gd4 = c1586gd;
                                i66 = i71;
                                i64 = i74;
                                i67 = i67;
                                abstractC2526rd2 = abstractC2526rd2;
                                str8 = str8;
                                c0874Ug2 = c0874Ug2;
                                iD25 = i70;
                                str12 = str12;
                                str13 = str13;
                                str18 = str18;
                                str15 = str15;
                                str14 = str3;
                            }
                            c1586gd = c1586gd4;
                            int i75 = i71 + iA9;
                            i65 = i65;
                            c1586gd4 = c1586gd;
                            i66 = i71;
                            i64 = i75;
                            i67 = i67;
                            abstractC2526rd2 = abstractC2526rd2;
                            str8 = str8;
                            c0874Ug2 = c0874Ug2;
                            iD25 = i70;
                            str12 = str12;
                            str13 = str13;
                            str18 = str18;
                            str15 = str15;
                            str14 = str3;
                        }
                        AbstractC2526rd abstractC2526rd3 = abstractC2526rd2;
                        C1586gd c1586gd5 = c1586gd4;
                        int i76 = i65;
                        String str24 = str12;
                        String str25 = str13;
                        String str26 = str18;
                        String str27 = str15;
                        String str28 = str14;
                        int i77 = i66;
                        C0874Ug c0874Ug3 = c0874Ug2;
                        String str29 = str8;
                        int i78 = i67;
                        int i79 = i68;
                        AbstractC1966l20 abstractC1966l20B = abstractC2526rd3.b(strC8, strC9, str19);
                        if (abstractC1966l20B == null) {
                            c0874Ug = c0874Ug3;
                        } else {
                            if (i77 != 0) {
                                int iD26 = c1586gd5.d(i77);
                                int iA10 = i77 + 2;
                                while (true) {
                                    int i80 = iD26 - 1;
                                    if (iD26 <= 0) {
                                        break;
                                    }
                                    iA10 = c1586gd5.a(abstractC1966l20B.a(c1586gd5.c(iA10, cArr3), true), iA10 + 2, true, cArr3);
                                    iD26 = i80;
                                }
                            }
                            if (i78 != 0) {
                                int iD27 = c1586gd5.d(i78);
                                int iA11 = i78 + 2;
                                while (true) {
                                    int i81 = iD27 - 1;
                                    if (iD27 <= 0) {
                                        break;
                                    }
                                    iA11 = c1586gd5.a(abstractC1966l20B.a(c1586gd5.c(iA11, cArr3), false), iA11 + 2, true, cArr3);
                                    iD27 = i81;
                                }
                            }
                            if (i79 != 0) {
                                int iD28 = c1586gd5.d(i79);
                                int iA12 = i79 + 2;
                                while (true) {
                                    int i82 = iD28 - 1;
                                    if (iD28 <= 0) {
                                        break;
                                    }
                                    int iA13 = c1586gd5.a(c0874Ug3, iA12);
                                    iA12 = c1586gd5.a(abstractC1966l20B.a(c0874Ug3.h, c0874Ug3.i, c1586gd5.c(iA13, cArr3), true), iA13 + 2, true, cArr3);
                                    iD28 = i82;
                                }
                            }
                            c0874Ug = c0874Ug3;
                            int i83 = i69;
                            if (i83 != 0) {
                                int iD29 = c1586gd5.d(i83);
                                int iA14 = i83 + 2;
                                while (true) {
                                    int i84 = iD29 - 1;
                                    if (iD29 <= 0) {
                                        break;
                                    }
                                    int iA15 = c1586gd5.a(c0874Ug, iA14);
                                    iA14 = c1586gd5.a(abstractC1966l20B.a(c0874Ug.h, c0874Ug.i, c1586gd5.c(iA15, cArr3), false), iA15 + 2, true, cArr3);
                                    iD29 = i84;
                                }
                            }
                            while (h8 != null) {
                                H4 h9 = h8.c;
                                h8.c = null;
                                abstractC1966l20B.a(h8);
                                h8 = h9;
                            }
                            abstractC1966l20B.a();
                        }
                        iD24 = i76;
                        c1586gd4 = c1586gd5;
                        c0874Ug2 = c0874Ug;
                        abstractC2526rd2 = abstractC2526rd3;
                        str8 = str29;
                        str12 = str24;
                        str13 = str25;
                        str18 = str26;
                        str15 = str27;
                        str14 = str28;
                    }
                }
                AbstractC2526rd abstractC2526rd4 = abstractC2526rd2;
                C1586gd c1586gd6 = c1586gd4;
                C0874Ug c0874Ug4 = c0874Ug2;
                String str30 = str12;
                String str31 = str13;
                String str32 = str18;
                String str33 = str15;
                String str34 = str14;
                String str35 = str8;
                int iD30 = c1586gd6.d(i21);
                int i85 = i21 + 2;
                while (true) {
                    int i86 = iD30 - 1;
                    if (iD30 <= 0) {
                        break;
                    }
                    char[] cArr4 = c0874Ug4.c;
                    int iD31 = c1586gd6.d(i85);
                    String strC12 = c1586gd6.c(i85 + 2, cArr4);
                    String strC13 = c1586gd6.c(i85 + 4, cArr4);
                    int iD32 = c1586gd6.d(i85 + 6);
                    int i87 = iD31;
                    int i88 = i85 + 8;
                    H4 h4A3 = null;
                    int i89 = 0;
                    int i90 = 0;
                    int i91 = 0;
                    String strC14 = null;
                    Object objB = null;
                    int i92 = 0;
                    while (true) {
                        int i93 = iD32 - 1;
                        if (iD32 <= 0) {
                            break;
                        }
                        i89 = i89;
                        String strC15 = c1586gd6.c(i88, cArr4);
                        h4A3 = h4A3;
                        int iA16 = c1586gd6.a(i88 + 2);
                        i90 = i88 + 6;
                        int i94 = iA16;
                        if ("ConstantValue".equals(strC15)) {
                            int iD33 = c1586gd6.d(i90);
                            if (iD33 == 0) {
                                i91 = i91;
                                objB = null;
                            } else {
                                i91 = i91;
                                objB = c1586gd6.b(iD33, cArr4);
                            }
                            strC14 = strC14;
                            str34 = str34;
                        } else {
                            str35 = str35;
                            if (str35.equals(strC15)) {
                                i91 = i91;
                                i90 = i90;
                                str17 = str17;
                                str32 = str32;
                                str33 = str33;
                                str34 = str34;
                                strC14 = c1586gd6.c(i90, cArr4);
                                i90 = i90;
                            } else {
                                i90 = i90;
                                String str36 = str17;
                                if (str36.equals(strC15)) {
                                    str17 = str36;
                                    i87 |= 131072;
                                } else {
                                    str17 = str36;
                                    String str37 = str30;
                                    if (str37.equals(strC15)) {
                                        str30 = str37;
                                        str17 = str17;
                                        i87 |= 4096;
                                    } else {
                                        str30 = str37;
                                        String str38 = str34;
                                        if (str38.equals(strC15)) {
                                            int i95 = i91;
                                            c1586gd6 = c1586gd6;
                                            i87 = i87;
                                            i91 = i95;
                                            i86 = i86;
                                            objB = objB;
                                            i89 = i90;
                                            i90 = i89;
                                            str17 = str17;
                                            str31 = str31;
                                            str32 = str32;
                                            str33 = str33;
                                            str34 = str38;
                                        } else {
                                            String str39 = str33;
                                            if (str39.equals(strC15)) {
                                                str33 = str39;
                                                i90 = i90;
                                                i91 = i90;
                                                str31 = str31;
                                                str32 = str32;
                                            } else {
                                                str33 = str39;
                                                String str40 = str32;
                                                if (str40.equals(strC15)) {
                                                    i91 = i91;
                                                    str32 = str40;
                                                    i90 = i90;
                                                    str17 = str17;
                                                    strC14 = strC14;
                                                    str34 = str38;
                                                } else {
                                                    str32 = str40;
                                                    String str41 = str31;
                                                    if (str41.equals(strC15)) {
                                                        i91 = i91;
                                                        str31 = str41;
                                                        i90 = i90;
                                                        i92 = i90;
                                                    } else {
                                                        str31 = str41;
                                                        int i96 = i90;
                                                        i94 = i94;
                                                        strC13 = strC13;
                                                        objB = objB;
                                                        str35 = str35;
                                                        i86 = i86;
                                                        str17 = str17;
                                                        strC12 = strC12;
                                                        strC14 = strC14;
                                                        str34 = str38;
                                                        i90 = i90;
                                                        int i97 = i87;
                                                        i91 = i91;
                                                        h4A3 = c1586gd6.a(c0874Ug4.a, strC15, i90, i94, cArr4, -1, null);
                                                        c1586gd6 = c1586gd6;
                                                        h4A3.c = h4A3;
                                                        i89 = i89;
                                                        i87 = i97;
                                                        i90 = i96;
                                                    }
                                                }
                                            }
                                            str34 = str38;
                                        }
                                        strC12 = strC12;
                                        strC13 = strC13;
                                    }
                                    i88 = i90 + i94;
                                    int i98 = i91;
                                    i87 = i87;
                                    c1586gd6 = c1586gd6;
                                    i91 = i98;
                                    strC12 = strC12;
                                    strC13 = strC13;
                                    str34 = str34;
                                    i90 = i90;
                                    strC14 = strC14;
                                    iD32 = i93;
                                    str31 = str31;
                                    i86 = i86;
                                    str32 = str32;
                                    str33 = str33;
                                    str17 = str17;
                                    str35 = str35;
                                    objB = objB;
                                    abstractC2526rd4 = abstractC2526rd;
                                }
                                h4A3 = h4A3;
                                i90 = i90;
                                strC12 = strC12;
                            }
                            h4A3 = h4A3;
                            strC12 = strC12;
                            i88 = i90 + i94;
                            int i99 = i91;
                            i87 = i87;
                            c1586gd6 = c1586gd6;
                            i91 = i99;
                            strC12 = strC12;
                            strC13 = strC13;
                            str34 = str34;
                            i90 = i90;
                            strC14 = strC14;
                            iD32 = i93;
                            str31 = str31;
                            i86 = i86;
                            str32 = str32;
                            str33 = str33;
                            str17 = str17;
                            str35 = str35;
                            objB = objB;
                            abstractC2526rd4 = abstractC2526rd;
                        }
                        i94 = i94;
                        strC13 = strC13;
                        i88 = i90 + i94;
                        int i910 = i91;
                        i87 = i87;
                        c1586gd6 = c1586gd6;
                        i91 = i910;
                        strC12 = strC12;
                        strC13 = strC13;
                        str34 = str34;
                        i90 = i90;
                        strC14 = strC14;
                        iD32 = i93;
                        str31 = str31;
                        i86 = i86;
                        str32 = str32;
                        str33 = str33;
                        str17 = str17;
                        str35 = str35;
                        objB = objB;
                        abstractC2526rd4 = abstractC2526rd;
                    }
                    String str42 = strC12;
                    int i100 = i89;
                    int i101 = i88;
                    int i102 = i86;
                    String str43 = strC13;
                    String str44 = str31;
                    String str45 = str32;
                    String str46 = str33;
                    H4 h10 = h4A3;
                    int i103 = i90;
                    int i104 = i87;
                    String str47 = strC14;
                    Object obj = objB;
                    String str48 = str35;
                    String str49 = str34;
                    int i105 = i91;
                    C1586gd c1586gd7 = c1586gd6;
                    AbstractC2526rd abstractC2526rd5 = abstractC2526rd4;
                    String str50 = str17;
                    AbstractC0779Qp abstractC0779QpA = abstractC2526rd5.a(i104, str42, str43, str47, obj);
                    if (abstractC0779QpA != null) {
                        if (i100 != 0) {
                            int iD34 = c1586gd7.d(i100);
                            int iA17 = i100 + 2;
                            while (true) {
                                int i106 = iD34 - 1;
                                if (iD34 <= 0) {
                                    break;
                                }
                                iA17 = c1586gd7.a(abstractC0779QpA.a(c1586gd7.c(iA17, cArr4), true), iA17 + 2, true, cArr4);
                                iD34 = i106;
                            }
                        }
                        if (i103 != 0) {
                            int iD35 = c1586gd7.d(i103);
                            int iA18 = i103 + 2;
                            while (true) {
                                int i107 = iD35 - 1;
                                if (iD35 <= 0) {
                                    break;
                                }
                                iA18 = c1586gd7.a(abstractC0779QpA.a(c1586gd7.c(iA18, cArr4), false), iA18 + 2, true, cArr4);
                                iD35 = i107;
                            }
                        }
                        if (i105 != 0) {
                            int iD36 = c1586gd7.d(i105);
                            int iA19 = i105 + 2;
                            while (true) {
                                int i108 = iD36 - 1;
                                if (iD36 <= 0) {
                                    break;
                                }
                                int iA20 = c1586gd7.a(c0874Ug4, iA19);
                                iA19 = c1586gd7.a(abstractC0779QpA.a(c0874Ug4.h, c0874Ug4.i, c1586gd7.c(iA20, cArr4), true), iA20 + 2, true, cArr4);
                                iD36 = i108;
                            }
                        }
                        int i109 = i92;
                        if (i109 != 0) {
                            int iD37 = c1586gd7.d(i109);
                            int iA21 = i109 + 2;
                            while (true) {
                                int i110 = iD37 - 1;
                                if (iD37 <= 0) {
                                    break;
                                }
                                int iA22 = c1586gd7.a(c0874Ug4, iA21);
                                iA21 = c1586gd7.a(abstractC0779QpA.a(c0874Ug4.h, c0874Ug4.i, c1586gd7.c(iA22, cArr4), false), iA22 + 2, true, cArr4);
                                iD37 = i110;
                            }
                        }
                        while (h10 != null) {
                            H4 h11 = h10.c;
                            h10.c = null;
                            abstractC0779QpA.a(h10);
                            h10 = h11;
                        }
                        abstractC0779QpA.a();
                    }
                    c1586gd6 = c1586gd7;
                    str34 = str49;
                    str17 = str50;
                    str35 = str48;
                    str31 = str44;
                    i85 = i101;
                    iD30 = i102;
                    str32 = str45;
                    str33 = str46;
                    abstractC2526rd4 = abstractC2526rd;
                }
                C1586gd c1586gd8 = c1586gd6;
                String str51 = str35;
                String str52 = str17;
                String str53 = str31;
                String str54 = str32;
                String str55 = str33;
                String str56 = str34;
                int iD38 = c1586gd8.d(i85);
                int i111 = i85 + 2;
                while (true) {
                    int i112 = iD38 - 1;
                    if (iD38 > 0) {
                        char[] cArr5 = c0874Ug4.c;
                        c0874Ug4.d = c1586gd8.d(i111);
                        c0874Ug4.e = c1586gd8.c(i111 + 2, cArr5);
                        int i113 = i111 + 4;
                        c0874Ug4.f = c1586gd8.c(i113, cArr5);
                        int i114 = i111 + 6;
                        int iD39 = c1586gd8.d(i114);
                        int i115 = i111 + 8;
                        int i116 = i111;
                        int i117 = 0;
                        int i118 = 0;
                        int i119 = 0;
                        H4 h12 = null;
                        int i120 = 0;
                        boolean z = false;
                        int iD40 = 0;
                        int i121 = 0;
                        String[] strArr6 = null;
                        int i122 = 0;
                        int i123 = 0;
                        int i124 = 0;
                        int i125 = 0;
                        int i126 = 0;
                        while (true) {
                            int i127 = iD39 - 1;
                            if (iD39 <= 0) {
                                break;
                            }
                            int i128 = i119;
                            String strC16 = c1586gd8.c(i115, cArr5);
                            int iA23 = c1586gd8.a(i115 + 2);
                            H4 h13 = h12;
                            int i129 = i115 + 6;
                            if ("Code".equals(strC16)) {
                                if ((c0874Ug4.b & 1) == 0) {
                                    cArr5 = cArr5;
                                    str56 = str56;
                                    str2 = str53;
                                    i119 = i128;
                                    str52 = str52;
                                    str51 = str51;
                                    i2 = i118;
                                    i3 = i129;
                                } else {
                                    str2 = str53;
                                    i119 = i128;
                                    i2 = i118;
                                    i3 = i117;
                                }
                            } else {
                                if ("Exceptions".equals(strC16)) {
                                    int iD41 = c1586gd8.d(i129);
                                    String[] strArr7 = new String[iD41];
                                    int i130 = i115 + 8;
                                    for (int i131 = 0; i131 < iD41; i131++) {
                                        strArr7[i131] = c1586gd8.a(i130, cArr5);
                                        i130 += 2;
                                    }
                                    strArr6 = strArr7;
                                    i121 = i129;
                                } else if (str51.equals(strC16)) {
                                    iD40 = c1586gd8.d(i129);
                                } else if (str52.equals(strC16)) {
                                    c0874Ug4.d |= 131072;
                                } else if (str56.equals(strC16)) {
                                    i125 = i129;
                                } else {
                                    String str57 = str55;
                                    if (str57.equals(strC16)) {
                                        str55 = str57;
                                        i120 = i129;
                                    } else if ("AnnotationDefault".equals(strC16)) {
                                        str55 = str57;
                                        i124 = i129;
                                    } else {
                                        String str58 = str30;
                                        if (str58.equals(strC16)) {
                                            c0874Ug4.d |= 4096;
                                            str55 = str57;
                                            cArr5 = cArr5;
                                            str30 = str58;
                                            str2 = str53;
                                            i119 = i128;
                                            z = true;
                                        } else {
                                            str30 = str58;
                                            String str59 = str54;
                                            if (str59.equals(strC16)) {
                                                str55 = str57;
                                                i126 = i129;
                                                str54 = str59;
                                                str2 = str53;
                                                i119 = i128;
                                            } else {
                                                str54 = str59;
                                                String str60 = str53;
                                                if (str60.equals(strC16)) {
                                                    i3 = i117;
                                                    str55 = str57;
                                                    cArr5 = cArr5;
                                                    str2 = str60;
                                                    str56 = str56;
                                                    str52 = str52;
                                                    i119 = i128;
                                                    str51 = str51;
                                                    i2 = i129;
                                                } else {
                                                    str55 = str57;
                                                    if ("RuntimeVisibleParameterAnnotations".equals(strC16)) {
                                                        i119 = i129;
                                                        cArr5 = cArr5;
                                                        str2 = str60;
                                                    } else {
                                                        if ("RuntimeInvisibleParameterAnnotations".equals(strC16)) {
                                                            i122 = i129;
                                                        } else if ("MethodParameters".equals(strC16)) {
                                                            i123 = i129;
                                                        } else {
                                                            str56 = str56;
                                                            str2 = str60;
                                                            str52 = str52;
                                                            i2 = i118;
                                                            H4 h4A4 = a(c0874Ug4.a, strC16, i129, iA23, cArr5, -1, null);
                                                            c1586gd8 = this;
                                                            cArr5 = cArr5;
                                                            h4A4.c = h13;
                                                            h13 = h4A4;
                                                            i120 = i120;
                                                            i119 = i128;
                                                        }
                                                        cArr5 = cArr5;
                                                        str2 = str60;
                                                        str56 = str56;
                                                        i119 = i128;
                                                        i2 = i118;
                                                    }
                                                    i3 = i117;
                                                }
                                            }
                                        }
                                        i2 = i118;
                                        i3 = i117;
                                    }
                                }
                                str2 = str53;
                                i119 = i128;
                                i2 = i118;
                                i3 = i117;
                            }
                            i115 = i129 + iA23;
                            cArr5 = cArr5;
                            i117 = i3;
                            i118 = i2;
                            str52 = str52;
                            iD39 = i127;
                            str51 = str51;
                            h12 = h13;
                            str56 = str56;
                            str53 = str2;
                        }
                        int i132 = i119;
                        char[] cArr6 = cArr5;
                        String str61 = str56;
                        String str62 = str51;
                        String str63 = str53;
                        int i133 = i118;
                        H4 h14 = h12;
                        String str64 = str52;
                        int i134 = i120;
                        int i135 = c0874Ug4.d;
                        String str65 = c0874Ug4.e;
                        String str66 = c0874Ug4.f;
                        if (iD40 == 0) {
                            str = null;
                        } else {
                            String[] strArr8 = c1586gd8.d;
                            String str67 = strArr8[iD40];
                            if (str67 != null) {
                                str = str67;
                            } else {
                                int i136 = c1586gd8.c[iD40];
                                String strA8 = c1586gd8.a(cArr6, i136 + 2, c1586gd8.d(i136));
                                strArr8[iD40] = strA8;
                                str = strA8;
                                i135 = i135;
                            }
                        }
                        XO xoA = abstractC2526rd.a(i135, str65, str66, str, strArr6);
                        if (xoA != null) {
                            if (xoA instanceof YO) {
                                YO yo = (YO) xoA;
                                boolean z2 = (c0874Ug4.d & 131072) != 0;
                                int iD42 = c1586gd8.d(i113);
                                Ag0 ag0 = yo.c;
                                if (c1586gd8 == ag0.b && iD42 == yo.g && iD40 == yo.z) {
                                    int i137 = yo.d;
                                    if (z2 == ((i137 & 131072) != 0)) {
                                        if (z == (ag0.c < 49 && (i137 & 4096) != 0)) {
                                            if (i121 == 0) {
                                                if (yo.x == 0) {
                                                    yo.Z = i114;
                                                    yo.a0 = (i115 - i116) - 6;
                                                }
                                            } else {
                                                if (c1586gd8.d(i121) == yo.x) {
                                                    int i138 = i121 + 2;
                                                    int i139 = 0;
                                                    while (true) {
                                                        if (i139 < yo.x) {
                                                            if (c1586gd8.d(i138) == yo.y[i139]) {
                                                                i138 += 2;
                                                                i139++;
                                                            }
                                                        }
                                                    }
                                                }
                                                yo.Z = i114;
                                                yo.a0 = (i115 - i116) - 6;
                                            }
                                        }
                                    }
                                }
                            }
                            if (i123 != 0 && (c0874Ug4.b & 2) == 0) {
                                int i140 = c1586gd8.b[i123] & 255;
                                int i141 = i123 + 1;
                                while (true) {
                                    int i142 = i140 - 1;
                                    if (i140 <= 0) {
                                        break;
                                    }
                                    xoA.b(c1586gd8.d(i141 + 2), c1586gd8.c(i141, cArr6));
                                    i141 += 4;
                                    i140 = i142;
                                }
                            }
                            int i143 = i124;
                            if (i143 != 0) {
                                J2 j2A = xoA.a();
                                c1586gd8.a(j2A, i143, (String) null, cArr6);
                                if (j2A != null) {
                                    j2A.a();
                                }
                            }
                            int i144 = i125;
                            if (i144 != 0) {
                                int iD43 = c1586gd8.d(i144);
                                int iA24 = i144 + 2;
                                while (true) {
                                    int i145 = iD43 - 1;
                                    if (iD43 <= 0) {
                                        break;
                                    }
                                    iA24 = c1586gd8.a(xoA.a(c1586gd8.c(iA24, cArr6), true), iA24 + 2, true, cArr6);
                                    iD43 = i145;
                                }
                            }
                            int i146 = i126;
                            if (i146 != 0) {
                                int iD44 = c1586gd8.d(i146);
                                int iA25 = i146 + 2;
                                while (true) {
                                    int i147 = iD44 - 1;
                                    if (iD44 <= 0) {
                                        break;
                                    }
                                    iA25 = c1586gd8.a(xoA.a(c1586gd8.c(iA25, cArr6), false), iA25 + 2, true, cArr6);
                                    iD44 = i147;
                                }
                            }
                            if (i134 != 0) {
                                int iD45 = c1586gd8.d(i134);
                                int iA26 = i134 + 2;
                                while (true) {
                                    int i148 = iD45 - 1;
                                    if (iD45 <= 0) {
                                        break;
                                    }
                                    int iA27 = c1586gd8.a(c0874Ug4, iA26);
                                    iA26 = c1586gd8.a(xoA.c(c0874Ug4.h, c0874Ug4.i, c1586gd8.c(iA27, cArr6), true), iA27 + 2, true, cArr6);
                                    iD45 = i148;
                                }
                            }
                            if (i133 != 0) {
                                int iD46 = c1586gd8.d(i133);
                                int iA28 = i133 + 2;
                                while (true) {
                                    int i149 = iD46 - 1;
                                    if (iD46 <= 0) {
                                        break;
                                    }
                                    int iA29 = c1586gd8.a(c0874Ug4, iA28);
                                    iA28 = c1586gd8.a(xoA.c(c0874Ug4.h, c0874Ug4.i, c1586gd8.c(iA29, cArr6), false), iA29 + 2, true, cArr6);
                                    iD46 = i149;
                                }
                            }
                            if (i132 != 0) {
                                c1586gd8.a(xoA, c0874Ug4, i132, true);
                            }
                            if (i122 != 0) {
                                c1586gd8.a(xoA, c0874Ug4, i122, false);
                            }
                            H4 h15 = h14;
                            while (h15 != null) {
                                H4 h16 = h15.c;
                                h15.c = null;
                                xoA.a(h15);
                                h15 = h16;
                            }
                            if (i117 != 0) {
                                xoA.b();
                                c1586gd8.a(xoA, c0874Ug4, i117);
                            }
                            xoA.c();
                        }
                        iD38 = i112;
                        str52 = str64;
                        str51 = str62;
                        i111 = i115;
                        str56 = str61;
                        str53 = str63;
                    } else {
                        abstractC2526rd.a();
                        return;
                    }
                }
            }
        }
    }

    public final void a(AbstractC2526rd abstractC2526rd, int i) {
        a(abstractC2526rd, new H4[0], i);
    }

    public static void a(int i, WI[] wiArr) {
        WI wi = wiArr[i];
        if (wi == null) {
            if (wi == null) {
                wiArr[i] = new WI();
            }
            WI wi2 = wiArr[i];
            wi2.b = (short) (wi2.b | 1);
        }
    }

    public final int a(int[] iArr, int i) {
        if (iArr == null || i >= iArr.length) {
            return -1;
        }
        int i2 = iArr[i];
        if ((this.b[i2] & 255) < 67) {
            return -1;
        }
        return d(i2 + 1);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:18:0x006b  */
    /* JADX WARN: Code duplicated, block: B:19:0x006e  */
    public final int a(C0874Ug c0874Ug, int i) {
        int i2;
        int i3;
        int iA = a(i);
        int i4 = iA >>> 24;
        if (i4 != 0 && i4 != 1) {
            switch (i4) {
                case Fcntl.S_IWGRP /* 16 */:
                case 17:
                case AndroidSdkVersion.JELLY_BEAN_MR2 /* 18 */:
                case AndroidSdkVersion.M /* 23 */:
                    i2 = iA & (-256);
                    i3 = i + 3;
                    break;
                case AndroidSdkVersion.KITKAT /* 19 */:
                case 20:
                case AndroidSdkVersion.LOLLIPOP /* 21 */:
                    i2 = iA & (-16777216);
                    i3 = i + 1;
                    break;
                case 22:
                    i2 = iA & (-65536);
                    i3 = i + 2;
                    break;
                default:
                    switch (i4) {
                        case 64:
                        case 65:
                            i2 = iA & (-16777216);
                            int iD = d(i + 1);
                            i3 = i + 3;
                            c0874Ug.j = new WI[iD];
                            c0874Ug.k = new WI[iD];
                            c0874Ug.l = new int[iD];
                            for (int i5 = 0; i5 < iD; i5++) {
                                int iD2 = d(i3);
                                int iD3 = d(i3 + 2);
                                int iD4 = d(i3 + 4);
                                i3 += 6;
                                c0874Ug.j[i5] = b(iD2, c0874Ug.g);
                                c0874Ug.k[i5] = b(iD2 + iD3, c0874Ug.g);
                                c0874Ug.l[i5] = iD4;
                            }
                            break;
                        case 66:
                            i2 = iA & (-256);
                            i3 = i + 3;
                            break;
                        case 67:
                        case 68:
                        case 69:
                        case 70:
                            i2 = iA & (-16777216);
                            i3 = i + 3;
                            break;
                        case 71:
                        case 72:
                        case 73:
                        case 74:
                        case 75:
                            i2 = iA & (-16776961);
                            i3 = i + 4;
                            break;
                        default:
                            j2d.a();
                            return 0;
                    }
                    break;
            }
        } else {
            i2 = iA & (-65536);
            i3 = i + 2;
        }
        c0874Ug.h = i2;
        byte[] bArr = this.b;
        int i6 = bArr[i3] & 255;
        c0874Ug.i = i6 == 0 ? null : new C3052xj0(i3, bArr);
        return (i6 * 2) + i3 + 1;
    }

    public final void a(XO xo, C0874Ug c0874Ug, int i, boolean z) {
        int iA = i + 1;
        int i2 = this.b[i] & 255;
        xo.a(i2, z);
        char[] cArr = c0874Ug.c;
        for (int i3 = 0; i3 < i2; i3++) {
            int iD = d(iA);
            iA += 2;
            while (true) {
                int i4 = iD - 1;
                if (iD > 0) {
                    iA = a(xo.a(i3, c(iA, cArr), z), iA + 2, true, cArr);
                    iD = i4;
                }
            }
        }
    }

    public final int a(J2 j2, int i, boolean z, char[] cArr) {
        int iD = d(i);
        int iA = i + 2;
        if (!z) {
            while (true) {
                int i2 = iD - 1;
                if (iD <= 0) {
                    break;
                }
                iA = a(j2, iA, (String) null, cArr);
                iD = i2;
            }
        } else {
            while (true) {
                int i3 = iD - 1;
                if (iD <= 0) {
                    break;
                }
                iA = a(j2, iA + 2, c(iA, cArr), cArr);
                iD = i3;
            }
        }
        if (j2 != null) {
            j2.a();
        }
        return iA;
    }

    public final int a(J2 j2, int i, String str, char[] cArr) {
        Object obj;
        byte[] bArr = this.b;
        int i2 = 0;
        if (j2 == null) {
            int i3 = bArr[i] & 255;
            if (i3 == 64) {
                return a((J2) null, i + 3, true, cArr);
            }
            if (i3 != 91) {
                return i3 != 101 ? i + 3 : i + 5;
            }
            return a((J2) null, i + 1, false, cArr);
        }
        int i4 = i + 1;
        int i5 = bArr[i] & 255;
        if (i5 != 64) {
            if (i5 != 70) {
                if (i5 == 83) {
                    j2.a(Short.valueOf((short) a(this.c[d(i4)])), str);
                    return i + 3;
                }
                if (i5 == 99) {
                    String strC = c(i4, cArr);
                    j2.a(C3050xi0.a(0, strC.length(), strC), str);
                    return i + 3;
                }
                if (i5 == 101) {
                    j2.a(str, c(i4, cArr), c(i + 3, cArr));
                    return i + 5;
                }
                if (i5 == 115) {
                    j2.a(c(i4, cArr), str);
                    return i + 3;
                }
                if (i5 != 73 && i5 != 74) {
                    if (i5 == 90) {
                        if (a(this.c[d(i4)]) == 0) {
                            obj = Boolean.FALSE;
                        } else {
                            obj = Boolean.TRUE;
                        }
                        j2.a(obj, str);
                        return i + 3;
                    }
                    if (i5 != 91) {
                        switch (i5) {
                            case 66:
                                j2.a(Byte.valueOf((byte) a(this.c[d(i4)])), str);
                                return i + 3;
                            case 67:
                                j2.a(Character.valueOf((char) a(this.c[d(i4)])), str);
                                return i + 3;
                            case 68:
                                break;
                            default:
                                j2d.a();
                                return 0;
                        }
                    } else {
                        int iD = d(i4);
                        int i6 = i + 3;
                        if (iD == 0) {
                            return a(j2.a(str), i4, false, cArr);
                        }
                        int i7 = this.b[i6] & 255;
                        if (i7 == 70) {
                            float[] fArr = new float[iD];
                            while (i2 < iD) {
                                fArr[i2] = Float.intBitsToFloat(a(this.c[d(i6 + 1)]));
                                i6 += 3;
                                i2++;
                            }
                            j2.a(fArr, str);
                            return i6;
                        }
                        if (i7 == 83) {
                            short[] sArr = new short[iD];
                            while (i2 < iD) {
                                sArr[i2] = (short) a(this.c[d(i6 + 1)]);
                                i6 += 3;
                                i2++;
                            }
                            j2.a(sArr, str);
                            return i6;
                        }
                        if (i7 == 90) {
                            boolean[] zArr = new boolean[iD];
                            for (int i8 = 0; i8 < iD; i8++) {
                                zArr[i8] = a(this.c[d(i6 + 1)]) != 0;
                                i6 += 3;
                            }
                            j2.a(zArr, str);
                            return i6;
                        }
                        if (i7 == 73) {
                            int[] iArr = new int[iD];
                            while (i2 < iD) {
                                iArr[i2] = a(this.c[d(i6 + 1)]);
                                i6 += 3;
                                i2++;
                            }
                            j2.a(iArr, str);
                            return i6;
                        }
                        if (i7 != 74) {
                            switch (i7) {
                                case 66:
                                    byte[] bArr2 = new byte[iD];
                                    while (i2 < iD) {
                                        bArr2[i2] = (byte) a(this.c[d(i6 + 1)]);
                                        i6 += 3;
                                        i2++;
                                    }
                                    j2.a(bArr2, str);
                                    return i6;
                                case 67:
                                    char[] cArr2 = new char[iD];
                                    while (i2 < iD) {
                                        cArr2[i2] = (char) a(this.c[d(i6 + 1)]);
                                        i6 += 3;
                                        i2++;
                                    }
                                    j2.a(cArr2, str);
                                    return i6;
                                case 68:
                                    double[] dArr = new double[iD];
                                    while (i2 < iD) {
                                        dArr[i2] = Double.longBitsToDouble(b(this.c[d(i6 + 1)]));
                                        i6 += 3;
                                        i2++;
                                    }
                                    j2.a(dArr, str);
                                    return i6;
                                default:
                                    return a(j2.a(str), i4, false, cArr);
                            }
                        }
                        long[] jArr = new long[iD];
                        while (i2 < iD) {
                            jArr[i2] = b(this.c[d(i6 + 1)]);
                            i6 += 3;
                            i2++;
                        }
                        j2.a(jArr, str);
                        return i6;
                    }
                }
            }
            j2.a(b(d(i4), cArr), str);
            return i + 3;
        }
        return a(j2.a(str, c(i4, cArr)), i + 3, true, cArr);
    }

    public final int a(int i, Object[] objArr, int i2, char[] cArr, WI[] wiArr) {
        int i3 = i + 1;
        switch (this.b[i] & 255) {
            case 0:
                objArr[i2] = 0;
                return i3;
            case 1:
                objArr[i2] = 1;
                return i3;
            case 2:
                objArr[i2] = 2;
                return i3;
            case XmlPullParser.END_TAG /* 3 */:
                objArr[i2] = 3;
                return i3;
            case 4:
                objArr[i2] = 4;
                return i3;
            case XmlPullParser.CDSECT /* 5 */:
                objArr[i2] = 5;
                return i3;
            case XmlPullParser.ENTITY_REF /* 6 */:
                objArr[i2] = 6;
                return i3;
            case 7:
                objArr[i2] = a(i3, cArr);
                break;
            case 8:
                objArr[i2] = b(d(i3), wiArr);
                break;
            default:
                j2d.a();
                return 0;
        }
        return i + 3;
    }

    public final int a() {
        int i = this.a;
        int iD = (d(i + 6) * 2) + i + 8;
        int iD2 = d(iD);
        int iA = iD + 2;
        while (true) {
            int i2 = iD2 - 1;
            if (iD2 <= 0) {
                break;
            }
            int iD3 = d(iA + 6);
            iA += 8;
            while (true) {
                int i3 = iD3 - 1;
                if (iD3 > 0) {
                    iA += a(iA + 2) + 6;
                    iD3 = i3;
                }
            }
            iD2 = i2;
        }
        int iD4 = d(iA);
        int iA2 = iA + 2;
        while (true) {
            int i4 = iD4 - 1;
            if (iD4 <= 0) {
                return iA2 + 2;
            }
            int iD5 = d(iA2 + 6);
            iA2 += 8;
            while (true) {
                int i5 = iD5 - 1;
                if (iD5 > 0) {
                    iA2 += a(iA2 + 2) + 6;
                    iD5 = i5;
                }
            }
            iD4 = i4;
        }
    }

    public final H4 a(H4[] h4Arr, String str, int i, int i2, char[] cArr, int i3, WI[] wiArr) {
        for (H4 h4 : h4Arr) {
            if (h4.a.equals(str)) {
                return h4.a(this, i, i2);
            }
        }
        H4 h5 = new H4(str);
        byte[] bArr = new byte[i2];
        h5.b = bArr;
        System.arraycopy(this.b, i, bArr, 0, i2);
        return h5;
    }

    public final int a(int i) {
        byte[] bArr = this.b;
        return (bArr[i + 3] & 255) | ((bArr[i] & 255) << 24) | ((bArr[i + 1] & 255) << 16) | ((bArr[i + 2] & 255) << 8);
    }

    public final String a(char[] cArr, int i, int i2) {
        int i3;
        int i4 = i2 + i;
        byte[] bArr = this.b;
        int i5 = 0;
        while (i < i4) {
            int i6 = i + 1;
            byte b = bArr[i];
            if ((b & 128) == 0) {
                cArr[i5] = (char) (b & 127);
                i5++;
                i = i6;
            } else {
                if ((b & 224) == 192) {
                    i3 = i5 + 1;
                    i += 2;
                    cArr[i5] = (char) (((b & 31) << 6) + (bArr[i6] & 63));
                } else {
                    i3 = i5 + 1;
                    int i7 = i + 2;
                    i += 3;
                    cArr[i5] = (char) (((b & 15) << 12) + ((bArr[i6] & 63) << 6) + (bArr[i7] & 63));
                }
                i5 = i3;
            }
        }
        return new String(cArr, 0, i5);
    }

    public final String a(int i, char[] cArr) {
        return c(this.c[d(i)], cArr);
    }
}
