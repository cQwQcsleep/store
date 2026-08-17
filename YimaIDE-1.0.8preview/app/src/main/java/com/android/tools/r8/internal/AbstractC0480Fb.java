package com.android.tools.r8.internal;

import com.android.apksig.internal.util.AndroidSdkVersion;
import com.sun.jna.platform.linux.Fcntl;
import defpackage.co4;
import defpackage.do4;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Fb, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0480Fb extends AbstractC2526rd {
    public final boolean c;
    public int d;
    public boolean e;
    public boolean f;
    public boolean g;
    public boolean h;
    public boolean i;
    public String j;
    public boolean k;
    public final HashMap l;

    public AbstractC0480Fb(C0714Oc c0714Oc) {
        super(c0714Oc);
        this.l = new HashMap();
        this.c = true;
    }

    public static int b(int i, String str) {
        char cE = e(i, str);
        if (cE == 'L') {
            return a(i, str);
        }
        if (cE != '[') {
            return a(str, ';', c(a(str, 'T', i), str));
        }
        int i2 = i + 1;
        char cE2 = e(i2, str);
        if (cE2 != 'F' && cE2 != 'S' && cE2 != 'Z' && cE2 != 'I' && cE2 != 'J') {
            switch (cE2) {
                case 'B':
                case 'C':
                case 'D':
                    break;
                default:
                    return b(i2, str);
            }
        }
        return i + 2;
    }

    public static int c(int i, String str) {
        int iOffsetByCodePoints = i;
        while (iOffsetByCodePoints < str.length() && ".;[/<>:".indexOf(str.codePointAt(iOffsetByCodePoints)) == -1) {
            iOffsetByCodePoints = str.offsetByCodePoints(iOffsetByCodePoints, 1);
        }
        if (iOffsetByCodePoints != i) {
            return iOffsetByCodePoints;
        }
        do4.a(str, ": identifier expected at index ", i);
        return 0;
    }

    public static int d(int i, String str) {
        int iA = a(str, ':', c(i, str));
        if ("L[T".indexOf(iA < str.length() ? str.charAt(iA) : (char) 0) != -1) {
            iA = b(iA, str);
        }
        while (true) {
            if ((iA < str.length() ? str.charAt(iA) : (char) 0) != ':') {
                return iA;
            }
            iA = b(iA + 1, str);
        }
    }

    public static String e(String str) {
        int iLastIndexOf = str.lastIndexOf(47);
        if (iLastIndexOf == -1) {
            return str;
        }
        int length = str.length();
        if (str.charAt(length - 1) == ';') {
            length--;
        }
        int iLastIndexOf2 = str.lastIndexOf(91);
        return iLastIndexOf2 == -1 ? str.substring(iLastIndexOf + 1, length) : str.substring(0, iLastIndexOf2 + 1).concat(str.substring(iLastIndexOf + 1, length));
    }

    /* JADX WARN: Code duplicated, block: B:51:0x00c7  */
    /* JADX WARN: Code duplicated, block: B:70:0x00ff  */
    @Override // com.android.tools.r8.internal.AbstractC2526rd
    public final XO a(int i, String str, String str2, String str3, String[] strArr) {
        C0636Lb c0636Lb;
        int i2;
        int iA;
        b();
        int i3 = this.d;
        a(i, 171519);
        if ((i3 & 65535) < 61 && Integer.bitCount(i & 3072) > 1) {
            w01.a(CX.a(i, "strictfp and abstract are mutually exclusive: "));
            return null;
        }
        if (!"<init>".equals(str) && !"<clinit>".equals(str)) {
            C0636Lb.c(this.d, str, "method name");
        }
        C0636Lb.e(this.d, str2);
        char c = 0;
        if (str3 != null) {
            if ((str3.length() > 0 ? str3.charAt(0) : (char) 0) == '<') {
                int iD = d(a(str3, '<', 0), str3);
                while (true) {
                    if ((iD < str3.length() ? str3.charAt(iD) : (char) 0) == '>') {
                        break;
                    }
                    iD = d(iD, str3);
                }
                i2 = iD + 1;
            } else {
                i2 = 0;
            }
            int iA2 = a(str3, '(', i2);
            while (true) {
                if ("ZCBSIFJDL[T".indexOf(iA2 < str3.length() ? str3.charAt(iA2) : c) != -1) {
                    char cCharAt = iA2 < str3.length() ? str3.charAt(iA2) : (char) 0;
                    if (cCharAt != 'F' && cCharAt != 'S' && cCharAt != 'Z' && cCharAt != 'I' && cCharAt != 'J') {
                        switch (cCharAt) {
                            case 'B':
                            case 'C':
                            case 'D':
                                iA2++;
                                break;
                            default:
                                iA2 = b(iA2, str3);
                                break;
                        }
                    } else {
                        iA2++;
                    }
                    c = 0;
                } else {
                    int iA3 = a(str3, ')', iA2);
                    if ((iA3 < str3.length() ? str3.charAt(iA3) : (char) 0) != 'V') {
                        char cCharAt2 = iA3 < str3.length() ? str3.charAt(iA3) : (char) 0;
                        if (cCharAt2 != 'F' && cCharAt2 != 'S' && cCharAt2 != 'Z' && cCharAt2 != 'I' && cCharAt2 != 'J') {
                            switch (cCharAt2) {
                                case 'B':
                                case 'C':
                                case 'D':
                                    iA = iA3 + 1;
                                    break;
                                default:
                                    iA = b(iA3, str3);
                                    break;
                            }
                        } else {
                            iA = iA3 + 1;
                        }
                    } else {
                        iA = iA3 + 1;
                    }
                    while (true) {
                        if ((iA < str3.length() ? str3.charAt(iA) : (char) 0) == '^') {
                            int i4 = iA + 1;
                            iA = (i4 < str3.length() ? str3.charAt(i4) : (char) 0) == 'L' ? a(i4, str3) : a(str3, ';', c(a(str3, 'T', i4), str3));
                        } else if (iA != str3.length()) {
                            do4.a(str3, ": error at index ", iA);
                            return null;
                        }
                    }
                }
            }
        }
        if (strArr != null) {
            for (int i5 = 0; i5 < strArr.length; i5++) {
                C0636Lb.b(this.d, strArr[i5], "exception name at index " + i5);
            }
        }
        XO xoA = super.a(i, str, str2, str3, strArr);
        if (this.c) {
            AbstractC2526rd abstractC2526rd = this.b;
            if (abstractC2526rd instanceof C2611sd) {
                xoA = new C0610Kb(this.a, this.d, (C2611sd) abstractC2526rd, xoA);
            }
            int i6 = this.a;
            c0636Lb = new C0636Lb(i6, new C0558Ib(i6, i, str, str2, xoA), this.l);
            c0636Lb.d = i;
        } else {
            c0636Lb = new C0636Lb(this.a, xoA, this.l);
        }
        c0636Lb.c = this.d;
        return c0636Lb;
    }

    @Override // com.android.tools.r8.internal.AbstractC2526rd
    public final void c(String str) {
        b();
        C0636Lb.b(this.d, str, "permittedSubclass");
        super.c(str);
    }

    public static void d(String str) {
        int iB = b(0, str);
        if (iB == str.length()) {
            return;
        }
        do4.a(str, ": error at index ", iB);
    }

    public static char e(int i, String str) {
        if (i < str.length()) {
            return str.charAt(i);
        }
        return (char) 0;
    }

    @Override // com.android.tools.r8.internal.AbstractC2526rd
    public final AbstractC1966l20 b(String str, String str2, String str3) {
        b();
        C0636Lb.a(this.d, str, 0, -1, "record component name");
        C0636Lb.d(this.d, str2);
        if (str3 != null) {
            d(str3);
        }
        return new C0765Qb(this.a, super.b(str, str2, str3));
    }

    public final void b() {
        if (this.e) {
            if (this.k) {
                k2d.a("Cannot visit member after visitEnd has been called.");
                return;
            }
            return;
        }
        k2d.a("Cannot visit member before visit has been called.");
    }

    public static void b(int i, String str, String str2) {
        int i2 = 0;
        while (true) {
            try {
                int iIndexOf = str.indexOf(46, i2 + 1);
                if (iIndexOf != -1) {
                    C0636Lb.a(i, str, i2, iIndexOf, (String) null);
                    i2 = iIndexOf + 1;
                } else {
                    C0636Lb.a(i, str, i2, str.length(), (String) null);
                    return;
                }
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid " + str2 + " (must be a fully qualified name): " + str, e);
            }
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC2526rd
    public final void b(String str) {
        String strSubstring;
        b();
        C0636Lb.b(this.d, str, "nestMember");
        if (!this.i) {
            int iLastIndexOf = str.lastIndexOf(47);
            if (iLastIndexOf == -1) {
                strSubstring = XmlPullParser.NO_NAMESPACE;
            } else {
                strSubstring = str.substring(0, iLastIndexOf);
            }
            String str2 = this.j;
            if (str2 == null) {
                this.j = strSubstring;
            } else if (!str2.equals(strSubstring)) {
                co4.a("nest member ", str, " should be in the package ", this.j);
                return;
            }
            super.b(str);
            return;
        }
        k2d.a("visitMemberOfNest and visitNestHost are mutually exclusive.");
    }

    @Override // com.android.tools.r8.internal.AbstractC2526rd
    public final void a(String str, String str2) {
        b();
        if (!this.g) {
            this.g = true;
            super.a(str, str2);
        } else {
            k2d.a("visitSource can be called only once.");
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC2526rd
    public final AbstractC2167nP a(int i, String str, String str2) {
        b();
        if (!this.f) {
            this.f = true;
            b(this.d, str, "module name");
            a(i, 36896);
            C0687Nb c0687Nb = new C0687Nb(this.a, super.a(i, str, str2), (i & 32) != 0);
            c0687Nb.h = this.d;
            return c0687Nb;
        }
        k2d.a("visitModule can be called only once.");
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC2526rd
    public final void a(String str) {
        b();
        C0636Lb.b(this.d, str, "nestHost");
        if (!this.i) {
            if (this.j == null) {
                this.i = true;
                super.a(str);
                return;
            } else {
                k2d.a("visitNestHost and visitNestMember are mutually exclusive.");
                return;
            }
        }
        k2d.a("visitNestHost can be called only once.");
    }

    @Override // com.android.tools.r8.internal.AbstractC2526rd
    public final void a(String str, String str2, String str3) {
        b();
        if (!this.h) {
            this.h = true;
            if (str != null) {
                if (str3 != null) {
                    C0636Lb.e(this.d, str3);
                }
                super.a(str, str2, str3);
                return;
            }
            w01.a("Illegal outer class owner");
            return;
        }
        k2d.a("visitOuterClass can be called only once.");
    }

    @Override // com.android.tools.r8.internal.AbstractC2526rd
    public final void a(int i, String str, String str2, String str3) {
        b();
        C0636Lb.b(this.d, str, "class name");
        if (str2 != null) {
            C0636Lb.b(this.d, str2, "outer class name");
        }
        if (str3 != null) {
            int i2 = 0;
            while (i2 < str3.length() && Character.isDigit(str3.charAt(i2))) {
                i2++;
            }
            if (i2 == 0 || i2 < str3.length()) {
                C0636Lb.a(this.d, str3, i2, -1, "inner class name");
            }
        }
        a(i, 30239);
        super.a(i, str, str2, str3);
    }

    @Override // com.android.tools.r8.internal.AbstractC2526rd
    public final AbstractC0779Qp a(int i, String str, String str2, String str3, Object obj) {
        b();
        a(i, 184543);
        C0636Lb.a(this.d, str, 0, -1, "field name");
        C0636Lb.d(this.d, str2);
        if (str3 != null) {
            d(str3);
        }
        if (obj != null && !(obj instanceof Integer) && !(obj instanceof Float) && !(obj instanceof Long) && !(obj instanceof Double) && !(obj instanceof String)) {
            aca.a("Invalid constant: ", obj);
            return null;
        }
        return new C0506Gb(this.a, super.a(i, str, str2, str3, obj));
    }

    @Override // com.android.tools.r8.internal.AbstractC2526rd
    public final void a(int i, int i2, String str, String str2, String str3, String[] strArr) {
        int i3;
        if (!this.e) {
            this.e = true;
            b();
            a(i2, 259633);
            if (str != null) {
                if (!str.endsWith("package-info") && !str.endsWith("module-info")) {
                    C0636Lb.b(i, str, "class name");
                }
                if ("java/lang/Object".equals(str)) {
                    if (str3 != null) {
                        w01.a("The super class name of the Object class must be 'null'");
                        return;
                    }
                } else if (!str.endsWith("module-info")) {
                    C0636Lb.b(i, str3, "super class name");
                } else if (str3 != null) {
                    w01.a("The super class name of a module-info class must be 'null'");
                    return;
                }
                if (str2 != null) {
                    if ((str2.length() > 0 ? str2.charAt(0) : (char) 0) == '<') {
                        int iD = d(a(str2, '<', 0), str2);
                        while (true) {
                            if ((iD < str2.length() ? str2.charAt(iD) : (char) 0) == '>') {
                                break;
                            } else {
                                iD = d(iD, str2);
                            }
                        }
                        i3 = iD + 1;
                    } else {
                        i3 = 0;
                    }
                    int iA = a(i3, str2);
                    while (true) {
                        if ((iA < str2.length() ? str2.charAt(iA) : (char) 0) != 'L') {
                            break;
                        } else {
                            iA = a(iA, str2);
                        }
                    }
                    if (iA != str2.length()) {
                        do4.a(str2, ": error at index ", iA);
                        return;
                    }
                }
                if ((i2 & 512) != 0 && !"java/lang/Object".equals(str3)) {
                    w01.a("The super class name of interfaces must be 'java/lang/Object'");
                    return;
                }
                for (int i4 = 0; i4 < strArr.length; i4++) {
                    C0636Lb.b(i, strArr[i4], "interface name at index " + i4);
                }
                this.d = i;
                super.a(i, i2, str, str2, str3, strArr);
                return;
            }
            w01.a("Illegal class name (null)");
            return;
        }
        k2d.a("visit must be called only once");
    }

    @Override // com.android.tools.r8.internal.AbstractC2526rd
    public final J2 a(String str, boolean z) {
        b();
        C0636Lb.d(this.d, str);
        return new C0376Bb(super.a(str, z));
    }

    @Override // com.android.tools.r8.internal.AbstractC2526rd
    public final J2 a(int i, C3052xj0 c3052xj0, String str, boolean z) {
        b();
        int i2 = i >>> 24;
        if (i2 != 0 && i2 != 17 && i2 != 16) {
            w01.a(F40.a("Invalid type reference sort 0x", Integer.toHexString(i2)));
            return null;
        }
        a(i);
        C0636Lb.d(this.d, str);
        return new C0376Bb(super.a(i, c3052xj0, str, z));
    }

    @Override // com.android.tools.r8.internal.AbstractC2526rd
    public final void a(H4 h4) {
        b();
        super.a(h4);
    }

    @Override // com.android.tools.r8.internal.AbstractC2526rd
    public final void a() {
        b();
        this.k = true;
        super.a();
    }

    public static void a(int i, int i2) {
        if (((~i2) & i) == 0) {
            if (Integer.bitCount(i & 7) <= 1) {
                if (Integer.bitCount(i & 1040) <= 1) {
                    return;
                }
                w01.a(CX.a(i, "final and abstract are mutually exclusive: "));
                return;
            }
            w01.a(CX.a(i, "public, protected and private are mutually exclusive: "));
            return;
        }
        w01.a(CX.a(i, "Invalid access flags: "));
    }

    /* JADX WARN: Code restructure failed: missing block: B:68:0x004f, code lost:
    
        r8 = r8 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00aa, code lost:
    
        r8 = r8 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x00cc, code lost:
    
        r8 = r8 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static int a(int i, String str) {
        int iB;
        int iB2;
        int iC = c(a(str, 'L', i), str);
        while (true) {
            if ((iC < str.length() ? str.charAt(iC) : (char) 0) != '/') {
                break;
            }
            iC = c(iC + 1, str);
        }
        if ((iC < str.length() ? str.charAt(iC) : (char) 0) == '<') {
            int iA = a(str, '<', iC);
            char cE = e(iA, str);
            if (cE != '*') {
                if (cE == '+' || cE == '-') {
                    iA++;
                }
                iB2 = b(iA, str);
                while (true) {
                    if ((iB2 < str.length() ? str.charAt(iB2) : (char) 0) == '>') {
                        break;
                    }
                    char cE2 = e(iB2, str);
                    if (cE2 == '*') {
                        iB2++;
                    } else {
                        if (cE2 == '+' || cE2 == '-') {
                            iB2++;
                        }
                        iB2 = b(iB2, str);
                    }
                }
                iC = iB2 + 1;
            } else {
                iB2++;
            }
        }
        while (true) {
            if ((iC < str.length() ? str.charAt(iC) : (char) 0) == '.') {
                iC = c(iC + 1, str);
                if ((iC < str.length() ? str.charAt(iC) : (char) 0) == '<') {
                    int iA2 = a(str, '<', iC);
                    char cE3 = e(iA2, str);
                    if (cE3 != '*') {
                        if (cE3 == '+' || cE3 == '-') {
                            iA2++;
                        }
                        iB = b(iA2, str);
                        while (true) {
                            if ((iB < str.length() ? str.charAt(iB) : (char) 0) != '>') {
                                char cE4 = e(iB, str);
                                if (cE4 == '*') {
                                    iB++;
                                } else {
                                    if (cE4 == '+' || cE4 == '-') {
                                        iB++;
                                    }
                                    iB = b(iB, str);
                                }
                            }
                        }
                    } else {
                        iB++;
                    }
                }
            } else {
                return a(str, ';', iC);
            }
        }
    }

    public static int a(String str, char c, int i) {
        if (e(i, str) == c) {
            return i + 1;
        }
        throw new IllegalArgumentException(str + ": '" + c + "' expected at index " + i);
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0013  */
    /* JADX WARN: Code duplicated, block: B:11:0x0016  */
    /* JADX WARN: Code duplicated, block: B:12:0x0019  */
    public static void a(int i) {
        int i2;
        int i3 = i >>> 24;
        if (i3 != 0 && i3 != 1) {
            switch (i3) {
                case Fcntl.S_IWGRP /* 16 */:
                case 17:
                case AndroidSdkVersion.JELLY_BEAN_MR2 /* 18 */:
                case AndroidSdkVersion.M /* 23 */:
                    i2 = -256;
                    break;
                case AndroidSdkVersion.KITKAT /* 19 */:
                case 20:
                case AndroidSdkVersion.LOLLIPOP /* 21 */:
                    i2 = -16777216;
                    break;
                case 22:
                    i2 = -65536;
                    break;
                default:
                    switch (i3) {
                        case 64:
                        case 65:
                        case 67:
                        case 68:
                        case 69:
                        case 70:
                            i2 = -16777216;
                            break;
                        case 66:
                            i2 = -256;
                            break;
                        case 71:
                        case 72:
                        case 73:
                        case 74:
                        case 75:
                            i2 = -16776961;
                            break;
                        default:
                            i2 = 0;
                            break;
                    }
                    break;
            }
        } else {
            i2 = -65536;
        }
        if (i2 == 0 || ((~i2) & i) != 0) {
            w01.a(F40.a("Invalid type reference 0x", Integer.toHexString(i)));
        }
    }
}
