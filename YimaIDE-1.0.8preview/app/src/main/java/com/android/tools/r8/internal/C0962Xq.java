package com.android.tools.r8.internal;

import com.android.apksig.internal.util.AndroidSdkVersion;
import com.android.tools.r8.DataResource;
import com.sun.jna.platform.linux.Fcntl;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Xq, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0962Xq {
    public WI a;
    public int[] b;
    public int[] c;
    public int[] d;
    public int[] e;
    public short f;
    public short g;
    public int h;
    public int[] i;

    public C0962Xq(WI wi) {
        this.a = wi;
    }

    /* JADX WARN: Code duplicated, block: B:147:0x029e  */
    /* JADX WARN: Code duplicated, block: B:149:0x02a8  */
    /* JADX WARN: Code duplicated, block: B:151:0x02af  */
    /* JADX WARN: Code duplicated, block: B:153:0x02b9  */
    /* JADX WARN: Code duplicated, block: B:180:0x0322  */
    /* JADX WARN: Code duplicated, block: B:182:0x0329  */
    /* JADX WARN: Code duplicated, block: B:184:0x032d  */
    /* JADX WARN: Code duplicated, block: B:186:0x0334  */
    public void a(int i, int i2, C3130yg0 c3130yg0, Ag0 ag0) {
        switch (i) {
            case 0:
                break;
            case 1:
                c(4194309);
                break;
            case 2:
            case XmlPullParser.END_TAG /* 3 */:
            case 4:
            case XmlPullParser.CDSECT /* 5 */:
            case XmlPullParser.ENTITY_REF /* 6 */:
            case 7:
            case 8:
            case Fcntl.S_IWGRP /* 16 */:
            case 17:
                c(4194305);
                break;
            case 9:
            case XmlPullParser.DOCDECL /* 10 */:
                c(4194308);
                c(4194304);
                break;
            case AndroidSdkVersion.HONEYCOMB /* 11 */:
            case 12:
            case 13:
                c(4194306);
                break;
            case 14:
            case 15:
                c(4194307);
                c(4194304);
                break;
            case AndroidSdkVersion.JELLY_BEAN_MR2 /* 18 */:
                int i3 = c3130yg0.b;
                switch (i3) {
                    case XmlPullParser.END_TAG /* 3 */:
                        c(4194305);
                        break;
                    case 4:
                        c(4194306);
                        break;
                    case XmlPullParser.CDSECT /* 5 */:
                        c(4194308);
                        c(4194304);
                        break;
                    case XmlPullParser.ENTITY_REF /* 6 */:
                        c(4194307);
                        c(4194304);
                        break;
                    case 7:
                        c(ag0.b("java/lang/Class") | 8388608);
                        break;
                    case 8:
                        c(ag0.b("java/lang/String") | 8388608);
                        break;
                    default:
                        switch (i3) {
                            case 15:
                                c(ag0.b("java/lang/invoke/MethodHandle") | 8388608);
                                break;
                            case Fcntl.S_IWGRP /* 16 */:
                                c(ag0.b("java/lang/invoke/MethodType") | 8388608);
                                break;
                            case 17:
                                a(ag0, c3130yg0.e);
                                break;
                            default:
                                x1f.a();
                                break;
                        }
                        break;
                }
                break;
            default:
                switch (i) {
                    case AndroidSdkVersion.LOLLIPOP /* 21 */:
                        c(4194305);
                        break;
                    case 22:
                        c(4194308);
                        c(4194304);
                        break;
                    case AndroidSdkVersion.M /* 23 */:
                        c(4194306);
                        break;
                    case AndroidSdkVersion.N /* 24 */:
                        c(4194307);
                        c(4194304);
                        break;
                    case 25:
                        c(a(i2));
                        break;
                    default:
                        switch (i) {
                            case 46:
                            case 51:
                            case 52:
                            case 53:
                                b(2);
                                c(4194305);
                                break;
                            case 47:
                                b(2);
                                c(4194308);
                                c(4194304);
                                break;
                            case 48:
                                b(2);
                                c(4194306);
                                break;
                            case 49:
                                b(2);
                                c(4194307);
                                c(4194304);
                                break;
                            case 50:
                                b(1);
                                int iA = a();
                                if (iA != 4194309) {
                                    iA -= 67108864;
                                }
                                c(iA);
                                break;
                            case 54:
                            case Fcntl.S_IRWXG /* 56 */:
                            case 58:
                                b(i2, a());
                                if (i2 > 0) {
                                    int i4 = i2 - 1;
                                    int iA2 = a(i4);
                                    if (iA2 == 4194308 || iA2 == 4194307) {
                                        b(i4, 4194304);
                                    } else {
                                        int i5 = iA2 & 62914560;
                                        if (i5 == 20971520 || i5 == 25165824) {
                                            b(i4, iA2 | 1048576);
                                        }
                                    }
                                }
                                break;
                            case 55:
                            case 57:
                                b(1);
                                b(i2, a());
                                b(i2 + 1, 4194304);
                                if (i2 > 0) {
                                    int i6 = i2 - 1;
                                    int iA3 = a(i6);
                                    if (iA3 == 4194308 || iA3 == 4194307) {
                                        b(i6, 4194304);
                                    } else {
                                        int i7 = iA3 & 62914560;
                                        if (i7 == 20971520 || i7 == 25165824) {
                                            b(i6, iA3 | 1048576);
                                        }
                                    }
                                }
                                break;
                            default:
                                switch (i) {
                                    case 79:
                                    case 81:
                                    case 83:
                                    case 84:
                                    case 85:
                                    case 86:
                                        b(3);
                                        break;
                                    case 80:
                                    case 82:
                                        b(4);
                                        break;
                                    case 87:
                                    case 153:
                                    case 154:
                                    case 155:
                                    case 156:
                                    case 157:
                                    case 158:
                                    case 170:
                                    case 171:
                                    case 172:
                                    case 174:
                                    case 176:
                                    case 191:
                                    case 194:
                                    case 195:
                                        break;
                                    case 88:
                                    case 159:
                                    case 160:
                                    case 161:
                                    case 162:
                                    case 163:
                                    case 164:
                                    case 165:
                                    case 166:
                                    case 173:
                                    case 175:
                                        b(2);
                                        break;
                                    case 89:
                                        int iA4 = a();
                                        c(iA4);
                                        c(iA4);
                                        break;
                                    case 90:
                                        int iA5 = a();
                                        int iA6 = a();
                                        c(iA5);
                                        c(iA6);
                                        c(iA5);
                                        break;
                                    case 91:
                                        int iA7 = a();
                                        int iA8 = a();
                                        int iA9 = a();
                                        c(iA7);
                                        c(iA9);
                                        c(iA8);
                                        c(iA7);
                                        break;
                                    case 92:
                                        int iA10 = a();
                                        int iA11 = a();
                                        c(iA11);
                                        c(iA10);
                                        c(iA11);
                                        c(iA10);
                                        break;
                                    case 93:
                                        int iA12 = a();
                                        int iA13 = a();
                                        int iA14 = a();
                                        c(iA13);
                                        c(iA12);
                                        c(iA14);
                                        c(iA13);
                                        c(iA12);
                                        break;
                                    case 94:
                                        int iA15 = a();
                                        int iA16 = a();
                                        int iA17 = a();
                                        int iA18 = a();
                                        c(iA16);
                                        c(iA15);
                                        c(iA18);
                                        c(iA17);
                                        c(iA16);
                                        c(iA15);
                                        break;
                                    case 95:
                                        int iA19 = a();
                                        int iA20 = a();
                                        c(iA19);
                                        c(iA20);
                                        break;
                                    case 96:
                                    case 100:
                                    case 104:
                                    case 108:
                                    case 112:
                                    case 120:
                                    case 122:
                                    case 124:
                                    case 126:
                                    case 128:
                                    case 130:
                                    case 136:
                                    case 142:
                                    case 149:
                                    case 150:
                                        b(2);
                                        c(4194305);
                                        break;
                                    case 97:
                                    case 101:
                                    case 105:
                                    case 109:
                                    case 113:
                                    case 127:
                                    case 129:
                                    case 131:
                                        b(4);
                                        c(4194308);
                                        c(4194304);
                                        break;
                                    case 98:
                                    case 102:
                                    case 106:
                                    case 110:
                                    case 114:
                                    case 137:
                                    case 144:
                                        b(2);
                                        c(4194306);
                                        break;
                                    case 99:
                                    case 103:
                                    case 107:
                                    case 111:
                                    case 115:
                                        b(4);
                                        c(4194307);
                                        c(4194304);
                                        break;
                                    case 116:
                                    case 117:
                                    case 118:
                                    case 119:
                                    case 145:
                                    case 146:
                                    case 147:
                                    case 167:
                                    case 177:
                                        break;
                                    case 121:
                                    case 123:
                                    case 125:
                                        b(3);
                                        c(4194308);
                                        c(4194304);
                                        break;
                                    case 132:
                                        b(i2, 4194305);
                                        break;
                                    case 133:
                                    case 140:
                                        b(1);
                                        c(4194308);
                                        c(4194304);
                                        break;
                                    case 134:
                                        b(1);
                                        c(4194306);
                                        break;
                                    case 135:
                                    case 141:
                                        b(1);
                                        c(4194307);
                                        c(4194304);
                                        break;
                                    case 138:
                                        b(2);
                                        c(4194307);
                                        c(4194304);
                                        break;
                                    case 139:
                                    case 190:
                                    case 193:
                                        b(1);
                                        c(4194305);
                                        break;
                                    case 143:
                                        b(2);
                                        c(4194308);
                                        c(4194304);
                                        break;
                                    case 148:
                                    case 151:
                                    case 152:
                                        b(4);
                                        c(4194305);
                                        break;
                                    case 168:
                                    case 169:
                                        w01.a("JSR/RET are not supported with computeFrames option");
                                        break;
                                    case 178:
                                        a(ag0, c3130yg0.e);
                                        break;
                                    case 179:
                                        a(c3130yg0.e);
                                        break;
                                    case 180:
                                        b(1);
                                        a(ag0, c3130yg0.e);
                                        break;
                                    case 181:
                                        a(c3130yg0.e);
                                        a();
                                        break;
                                    case 182:
                                    case 183:
                                    case 184:
                                    case 185:
                                        a(c3130yg0.e);
                                        if (i != 184) {
                                            int iA21 = a();
                                            if (i == 183 && c3130yg0.d.charAt(0) == '<') {
                                                if (this.i == null) {
                                                    this.i = new int[2];
                                                }
                                                int length = this.i.length;
                                                int i8 = this.h;
                                                if (i8 >= length) {
                                                    int[] iArr = new int[Math.max(i8 + 1, length * 2)];
                                                    System.arraycopy(this.i, 0, iArr, 0, length);
                                                    this.i = iArr;
                                                }
                                                int[] iArr2 = this.i;
                                                int i9 = this.h;
                                                this.h = i9 + 1;
                                                iArr2[i9] = iA21;
                                            }
                                        }
                                        a(ag0, c3130yg0.e);
                                        break;
                                    case 186:
                                        a(c3130yg0.e);
                                        a(ag0, c3130yg0.e);
                                        break;
                                    case 187:
                                        c(ag0.b(i2, c3130yg0.e) | 12582912);
                                        break;
                                    case 188:
                                        a();
                                        switch (i2) {
                                            case 4:
                                                c(71303177);
                                                break;
                                            case XmlPullParser.CDSECT /* 5 */:
                                                c(71303179);
                                                break;
                                            case XmlPullParser.ENTITY_REF /* 6 */:
                                                c(71303170);
                                                break;
                                            case 7:
                                                c(71303171);
                                                break;
                                            case 8:
                                                c(71303178);
                                                break;
                                            case 9:
                                                c(71303180);
                                                break;
                                            case XmlPullParser.DOCDECL /* 10 */:
                                                c(71303169);
                                                break;
                                            case AndroidSdkVersion.HONEYCOMB /* 11 */:
                                                c(71303172);
                                                break;
                                            default:
                                                j2d.a();
                                                break;
                                        }
                                        break;
                                    case 189:
                                        String str = c3130yg0.e;
                                        a();
                                        if (str.charAt(0) != '[') {
                                            c(ag0.b(str) | 75497472);
                                        } else {
                                            a(ag0, "[".concat(str));
                                        }
                                        break;
                                    case 192:
                                        String str2 = c3130yg0.e;
                                        a();
                                        if (str2.charAt(0) != '[') {
                                            c(ag0.b(str2) | 8388608);
                                        } else {
                                            a(ag0, str2);
                                        }
                                        break;
                                    default:
                                        switch (i) {
                                            case 197:
                                                b(i2);
                                                a(ag0, c3130yg0.e);
                                                break;
                                            case 198:
                                            case 199:
                                                break;
                                            default:
                                                j2d.a();
                                                break;
                                        }
                                        break;
                                }
                                b(1);
                                break;
                        }
                        break;
                }
                break;
        }
    }

    public final void b(int i, int i2) {
        if (this.d == null) {
            this.d = new int[10];
        }
        int length = this.d.length;
        if (i >= length) {
            int[] iArr = new int[Math.max(i + 1, length * 2)];
            System.arraycopy(this.d, 0, iArr, 0, length);
            this.d = iArr;
        }
        this.d[i] = i2;
    }

    public final void c(int i) {
        if (this.e == null) {
            this.e = new int[10];
        }
        int length = this.e.length;
        short s = this.g;
        if (s >= length) {
            int[] iArr = new int[Math.max(s + 1, length * 2)];
            System.arraycopy(this.e, 0, iArr, 0, length);
            this.e = iArr;
        }
        int[] iArr2 = this.e;
        short s2 = this.g;
        short s3 = (short) (s2 + 1);
        this.g = s3;
        iArr2[s2] = i;
        short s4 = (short) (this.f + s3);
        WI wi = this.a;
        if (s4 > wi.i) {
            wi.i = s4;
        }
    }

    public final void b(int i) {
        short s = this.g;
        if (s >= i) {
            this.g = (short) (s - i);
        } else {
            this.f = (short) (this.f - (i - s));
            this.g = (short) 0;
        }
    }

    public static int a(Ag0 ag0, String str, int i) {
        int iB;
        char cCharAt = str.charAt(i);
        if (cCharAt == 'F') {
            return 4194306;
        }
        if (cCharAt == 'L') {
            return ag0.b(str.substring(i + 1, str.length() - 1)) | 8388608;
        }
        if (cCharAt != 'S') {
            if (cCharAt == 'V') {
                return 0;
            }
            if (cCharAt != 'I') {
                if (cCharAt == 'J') {
                    return 4194308;
                }
                if (cCharAt != 'Z') {
                    if (cCharAt != '[') {
                        switch (cCharAt) {
                            case 'B':
                            case 'C':
                                break;
                            case 'D':
                                return 4194307;
                            default:
                                w01.a(F40.a("Invalid descriptor: ", str.substring(i)));
                                return 0;
                        }
                    } else {
                        int i2 = i + 1;
                        while (str.charAt(i2) == '[') {
                            i2++;
                        }
                        char cCharAt2 = str.charAt(i2);
                        if (cCharAt2 == 'F') {
                            iB = 4194306;
                        } else if (cCharAt2 == 'L') {
                            iB = ag0.b(str.substring(i2 + 1, str.length() - 1)) | 8388608;
                        } else if (cCharAt2 == 'S') {
                            iB = 4194316;
                        } else if (cCharAt2 == 'Z') {
                            iB = 4194313;
                        } else if (cCharAt2 == 'I') {
                            iB = 4194305;
                        } else if (cCharAt2 != 'J') {
                            switch (cCharAt2) {
                                case 'B':
                                    iB = 4194314;
                                    break;
                                case 'C':
                                    iB = 4194315;
                                    break;
                                case 'D':
                                    iB = 4194307;
                                    break;
                                default:
                                    w01.a(F40.a("Invalid descriptor fragment: ", str.substring(i2)));
                                    return 0;
                            }
                        } else {
                            iB = 4194308;
                        }
                        return ((i2 - i) << 26) | iB;
                    }
                }
            }
        }
        return 4194305;
    }

    public final void a(Ag0 ag0, int i, String str, int i2) {
        int i3;
        int[] iArr = new int[i2];
        this.b = iArr;
        this.c = new int[0];
        if ((i & 8) == 0) {
            i3 = 1;
            if ((i & 262144) == 0) {
                iArr[0] = ag0.b(ag0.d) | 8388608;
            } else {
                iArr[0] = 4194310;
            }
        } else {
            i3 = 0;
        }
        for (C3050xi0 c3050xi0 : C3050xi0.b(str)) {
            int iA = a(ag0, c3050xi0.b(), 0);
            int[] iArr2 = this.b;
            int i4 = i3 + 1;
            iArr2[i3] = iA;
            if (iA == 4194308 || iA == 4194307) {
                i3 += 2;
                iArr2[i4] = 4194304;
            } else {
                i3 = i4;
            }
        }
        while (i3 < i2) {
            this.b[i3] = 4194304;
            i3++;
        }
    }

    public final int a(int i) {
        int[] iArr = this.d;
        if (iArr == null || i >= iArr.length) {
            return i | 20971520;
        }
        int i2 = iArr[i];
        if (i2 != 0) {
            return i2;
        }
        int i3 = 20971520 | i;
        iArr[i] = i3;
        return i3;
    }

    public final void a(Ag0 ag0, String str) {
        int iA = a(ag0, str, str.charAt(0) == '(' ? C3050xi0.f(str) : 0);
        if (iA != 0) {
            c(iA);
            if (iA == 4194308 || iA == 4194307) {
                c(4194304);
            }
        }
    }

    public final int a() {
        short s = this.g;
        if (s > 0) {
            int[] iArr = this.e;
            short s2 = (short) (s - 1);
            this.g = s2;
            return iArr[s2];
        }
        short s3 = (short) (this.f - 1);
        this.f = s3;
        return (-s3) | 25165824;
    }

    public final void a(String str) {
        char cCharAt = str.charAt(0);
        if (cCharAt == '(') {
            b((C3050xi0.c(str) >> 2) - 1);
        } else if (cCharAt != 'J' && cCharAt != 'D') {
            b(1);
        } else {
            b(2);
        }
    }

    /* JADX WARN: Code duplicated, block: B:21:0x003f  */
    /* JADX WARN: Code duplicated, block: B:24:0x0047  */
    /* JADX WARN: Code duplicated, block: B:25:0x0053 A[LOOP:0: B:9:0x0011->B:25:0x0053, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:27:0x003b A[SYNTHETIC] */
    public final int a(Ag0 ag0, int i) {
        int i2;
        int iB;
        int i3;
        if (i == 4194310 || (i3 = (-4194304) & i) == 12582912 || i3 == 16777216) {
            for (int i4 = 0; i4 < this.h; i4++) {
                int i5 = this.i[i4];
                int i6 = (-67108864) & i5;
                int i7 = 62914560 & i5;
                int i8 = i5 & 1048575;
                if (i7 == 20971520) {
                    i2 = this.b[i8];
                } else {
                    if (i7 == 25165824) {
                        int[] iArr = this.c;
                        i2 = iArr[iArr.length - i8];
                    }
                    if (i == i5) {
                        if (i == 4194310) {
                            iB = ag0.b(ag0.d);
                        } else {
                            iB = ag0.b(ag0.l[i & 1048575].e);
                        }
                        return 8388608 | iB;
                    }
                }
                i5 = i2 + i6;
                if (i == i5) {
                    if (i == 4194310) {
                        iB = ag0.b(ag0.d);
                    } else {
                        iB = ag0.b(ag0.l[i & 1048575].e);
                    }
                    return 8388608 | iB;
                }
            }
        }
        return i;
    }

    public static int a(Ag0 ag0, Object obj) {
        int iA;
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue() | 4194304;
        }
        if (obj instanceof String) {
            return a(ag0, C3050xi0.e((String) obj).b(), 0);
        }
        WI wi = (WI) obj;
        if ((wi.b & 4) != 0) {
            return ag0.b(wi.e, XmlPullParser.NO_NAMESPACE) | 12582912;
        }
        if (ag0.o == null) {
            ag0.o = new C3216zg0[16];
            ag0.n = new C3216zg0[16];
        }
        int iIdentityHashCode = System.identityHashCode(wi);
        C3216zg0[] c3216zg0Arr = ag0.o;
        C3216zg0 c3216zg0 = c3216zg0Arr[iIdentityHashCode % c3216zg0Arr.length];
        while (c3216zg0 != null && c3216zg0.b != wi) {
            c3216zg0 = c3216zg0.c;
        }
        if (c3216zg0 == null) {
            int i = ag0.m;
            C3216zg0[] c3216zg0Arr2 = ag0.o;
            if (i > (c3216zg0Arr2.length * 3) / 4) {
                int length = c3216zg0Arr2.length;
                int i2 = (length * 2) + 1;
                C3216zg0[] c3216zg0Arr3 = new C3216zg0[i2];
                for (int i3 = length - 1; i3 >= 0; i3--) {
                    C3216zg0 c3216zg1 = ag0.o[i3];
                    while (c3216zg1 != null) {
                        int iIdentityHashCode2 = System.identityHashCode(c3216zg1.b) % i2;
                        C3216zg0 c3216zg2 = c3216zg1.c;
                        c3216zg1.c = c3216zg0Arr3[iIdentityHashCode2];
                        c3216zg0Arr3[iIdentityHashCode2] = c3216zg1;
                        c3216zg1 = c3216zg2;
                    }
                }
                ag0.o = c3216zg0Arr3;
            }
            int i4 = ag0.m;
            C3216zg0[] c3216zg0Arr4 = ag0.n;
            if (i4 == c3216zg0Arr4.length) {
                C3216zg0[] c3216zg0Arr5 = new C3216zg0[c3216zg0Arr4.length * 2];
                System.arraycopy(c3216zg0Arr4, 0, c3216zg0Arr5, 0, c3216zg0Arr4.length);
                ag0.n = c3216zg0Arr5;
            }
            int i5 = ag0.m;
            c3216zg0 = new C3216zg0(i5, wi);
            C3216zg0[] c3216zg0Arr6 = ag0.o;
            int length2 = iIdentityHashCode % c3216zg0Arr6.length;
            c3216zg0.c = c3216zg0Arr6[length2];
            c3216zg0Arr6[length2] = c3216zg0;
            C3216zg0[] c3216zg0Arr7 = ag0.n;
            ag0.m = i5 + 1;
            c3216zg0Arr7[i5] = c3216zg0;
        }
        int i6 = c3216zg0.a;
        int i7 = (i6 + 130) & Integer.MAX_VALUE;
        C3130yg0[] c3130yg0Arr = ag0.f;
        for (C3130yg0 c3130yg0 = c3130yg0Arr[i7 % c3130yg0Arr.length]; c3130yg0 != null; c3130yg0 = c3130yg0.i) {
            if (c3130yg0.b == 130 && c3130yg0.h == i7 && c3130yg0.f == i6 && c3130yg0.e.equals(XmlPullParser.NO_NAMESPACE)) {
                iA = c3130yg0.a;
                return iA | 16777216;
            }
        }
        iA = ag0.a(new C3130yg0(ag0.k, 130, XmlPullParser.NO_NAMESPACE, i6, i7));
        return iA | 16777216;
    }

    public final int a(int i, int i2) {
        int i3 = (-67108864) & i;
        int i4 = 62914560 & i;
        if (i4 == 20971520) {
            int i5 = i3 + this.b[i & 1048575];
            if ((i & 1048576) == 0 || !(i5 == 4194308 || i5 == 4194307)) {
                return i5;
            }
            return 4194304;
        }
        if (i4 != 25165824) {
            return i;
        }
        int i6 = i3 + this.c[i2 - (i & 1048575)];
        if ((i & 1048576) == 0 || !(i6 == 4194308 || i6 == 4194307)) {
            return i6;
        }
        return 4194304;
    }

    public final boolean a(Ag0 ag0, C0962Xq c0962Xq, int i) {
        boolean zA;
        int iA;
        int i2;
        int length = this.b.length;
        int length2 = this.c.length;
        boolean zA2 = true;
        if (c0962Xq.b == null) {
            c0962Xq.b = new int[length];
            zA = true;
        } else {
            zA = false;
        }
        int i3 = 0;
        while (i3 < length) {
            int[] iArr = this.d;
            if (iArr == null || i3 >= iArr.length || (i2 = iArr[i3]) == 0) {
                iA = this.b[i3];
            } else {
                iA = a(i2, length2);
            }
            if (this.i != null) {
                iA = a(ag0, iA);
            }
            zA |= a(ag0, iA, c0962Xq.b, i3);
            i3++;
        }
        if (i > 0) {
            for (int i4 = 0; i4 < length; i4++) {
                zA |= a(ag0, this.b[i4], c0962Xq.b, i4);
            }
            if (c0962Xq.c == null) {
                c0962Xq.c = new int[1];
            } else {
                zA2 = zA;
            }
            return a(ag0, i, c0962Xq.c, 0) | zA2;
        }
        int length3 = this.c.length + this.f;
        if (c0962Xq.c == null) {
            c0962Xq.c = new int[this.g + length3];
        } else {
            zA2 = zA;
        }
        for (int i5 = 0; i5 < length3; i5++) {
            int iA2 = this.c[i5];
            if (this.i != null) {
                iA2 = a(ag0, iA2);
            }
            zA2 |= a(ag0, iA2, c0962Xq.c, i5);
        }
        for (int i6 = 0; i6 < this.g; i6++) {
            int iA3 = a(this.e[i6], length2);
            if (this.i != null) {
                iA3 = a(ag0, iA3);
            }
            zA2 |= a(ag0, iA3, c0962Xq.c, length3 + i6);
        }
        return zA2;
    }

    /* JADX WARN: Code duplicated, block: B:78:0x0119  */
    public static boolean a(Ag0 ag0, int i, int[] iArr, int i2) {
        int iMin;
        int iB;
        long j;
        long j2;
        int i3;
        int i4 = i;
        int i5 = iArr[i2];
        if (i5 == i4) {
            return false;
        }
        if ((67108863 & i4) == 4194309) {
            if (i5 == 4194309) {
                return false;
            }
            i4 = 4194309;
        }
        if (i5 == 0) {
            iArr[i2] = i4;
            return true;
        }
        int i6 = i5 & (-67108864);
        if (i6 != 0 || (i5 & 62914560) == 8388608) {
            if (i4 == 4194309) {
                return false;
            }
            String strReplace = "java/lang/Object";
            if ((i4 & (-4194304)) != ((-4194304) & i5)) {
                int i7 = i4 & (-67108864);
                if (i7 != 0 || (i4 & 62914560) == 8388608) {
                    if (i7 != 0 && (i4 & 62914560) != 8388608) {
                        i7 -= 67108864;
                    }
                    if (i6 != 0 && (i5 & 62914560) != 8388608) {
                        i6 -= 67108864;
                    }
                    iMin = Math.min(i7, i6) | 8388608;
                    iB = ag0.b("java/lang/Object");
                } else {
                    i4 = 4194304;
                }
            } else if ((i5 & 62914560) == 8388608) {
                int i8 = (i4 & (-67108864)) | 8388608;
                int i9 = i4 & 1048575;
                int i10 = 1048575 & i5;
                ag0.getClass();
                if (i9 < i10) {
                    j = i9;
                    j2 = i10;
                } else {
                    j = i10;
                    j2 = i9;
                }
                long j3 = j | (j2 << 32);
                int i11 = (i9 + i10 + 131) & Integer.MAX_VALUE;
                C3130yg0[] c3130yg0Arr = ag0.f;
                C3130yg0 c3130yg0 = c3130yg0Arr[i11 % c3130yg0Arr.length];
                while (true) {
                    if (c3130yg0 != null) {
                        if (c3130yg0.b == 131 && c3130yg0.h == i11 && c3130yg0.f == j3) {
                            i3 = c3130yg0.g;
                            break;
                        }
                        c3130yg0 = c3130yg0.i;
                    } else {
                        C3130yg0[] c3130yg0Arr2 = ag0.l;
                        String str = c3130yg0Arr2[i9].e;
                        String str2 = c3130yg0Arr2[i10].e;
                        ag0.a.getClass();
                        ClassLoader classLoader = C2611sd.class.getClassLoader();
                        try {
                            Class<?> cls = Class.forName(str.replace(DataResource.SEPARATOR, '.'), false, classLoader);
                            try {
                                Class<?> cls2 = Class.forName(str2.replace(DataResource.SEPARATOR, '.'), false, classLoader);
                                if (cls.isAssignableFrom(cls2)) {
                                    strReplace = str;
                                } else if (cls2.isAssignableFrom(cls)) {
                                    strReplace = str2;
                                } else if (!cls.isInterface() && !cls2.isInterface()) {
                                    do {
                                        cls = cls.getSuperclass();
                                    } while (!cls.isAssignableFrom(cls2));
                                    strReplace = cls.getName().replace('.', DataResource.SEPARATOR);
                                }
                                int iB2 = ag0.b(strReplace);
                                ag0.b(new C3130yg0(ag0.k, 131, j3, i11)).g = iB2;
                                i3 = iB2;
                                break;
                            } catch (ClassNotFoundException e) {
                                throw new TypeNotPresentException(str2, e);
                            }
                        } catch (ClassNotFoundException e2) {
                            throw new TypeNotPresentException(str, e2);
                        }
                    }
                }
                i4 = i8 | i3;
            } else {
                iMin = ((i4 & (-67108864)) - 67108864) | 8388608;
                iB = ag0.b("java/lang/Object");
            }
            i4 = iMin | iB;
        } else if (i5 != 4194309 || ((i4 & (-67108864)) == 0 && (i4 & 62914560) != 8388608)) {
            i4 = 4194304;
        }
        if (i4 == i5) {
            return false;
        }
        iArr[i2] = i4;
        return true;
    }

    public final void a(YO yo) {
        int[] iArr = this.b;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            int i5 = 2;
            if (i2 >= iArr.length) {
                break;
            }
            int i6 = iArr[i2];
            if (i6 != 4194308 && i6 != 4194307) {
                i5 = 1;
            }
            i2 += i5;
            i3++;
            if (i6 != 4194304) {
                i4 += i3;
                i3 = 0;
            }
        }
        int[] iArr2 = this.c;
        int i7 = 0;
        int i8 = 0;
        while (i7 < iArr2.length) {
            int i9 = iArr2[i7];
            i7 += (i9 == 4194308 || i9 == 4194307) ? 2 : 1;
            i8++;
        }
        yo.a(this.a.e, i4, i8);
        int i10 = 3;
        int i11 = 0;
        while (true) {
            int i12 = i4 - 1;
            if (i4 <= 0) {
                break;
            }
            int i13 = iArr[i11];
            i11 += (i13 == 4194308 || i13 == 4194307) ? 2 : 1;
            yo.V[i10] = i13;
            i4 = i12;
            i10++;
        }
        while (true) {
            int i14 = i8 - 1;
            if (i8 > 0) {
                int i15 = iArr2[i];
                i += (i15 == 4194308 || i15 == 4194307) ? 2 : 1;
                yo.V[i10] = i15;
                i8 = i14;
                i10++;
            } else {
                yo.e();
                return;
            }
        }
    }
}
