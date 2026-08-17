package com.android.tools.r8.internal;

import com.android.apksig.internal.util.AndroidSdkVersion;
import com.android.tools.r8.DataResource;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.xi0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3050xi0 {
    public static final C3050xi0 e = new C3050xi0(0, 0, 1, "VZCBSIFJD");
    public static final C3050xi0 f = new C3050xi0(1, 1, 2, "VZCBSIFJD");
    public static final C3050xi0 g = new C3050xi0(2, 2, 3, "VZCBSIFJD");
    public static final C3050xi0 h = new C3050xi0(3, 3, 4, "VZCBSIFJD");
    public static final C3050xi0 i = new C3050xi0(4, 4, 5, "VZCBSIFJD");
    public static final C3050xi0 j = new C3050xi0(5, 5, 6, "VZCBSIFJD");
    public static final C3050xi0 k = new C3050xi0(6, 6, 7, "VZCBSIFJD");
    public static final C3050xi0 l = new C3050xi0(7, 7, 8, "VZCBSIFJD");
    public static final C3050xi0 m = new C3050xi0(8, 8, 9, "VZCBSIFJD");
    public final int a;
    public final String b;
    public final int c;
    public final int d;

    public C3050xi0(int i2, int i3, int i4, String str) {
        this.a = i2;
        this.b = str;
        this.c = i3;
        this.d = i4;
    }

    public static C3050xi0[] b(String str) {
        C3050xi0[] c3050xi0Arr = new C3050xi0[a(str)];
        int i2 = 0;
        int i3 = 1;
        while (str.charAt(i3) != ')') {
            int i4 = i3;
            while (str.charAt(i4) == '[') {
                i4++;
            }
            int iMax = i4 + 1;
            if (str.charAt(i4) == 'L') {
                iMax = Math.max(iMax, str.indexOf(59, iMax) + 1);
            }
            c3050xi0Arr[i2] = a(i3, iMax, str);
            i2++;
            i3 = iMax;
        }
        return c3050xi0Arr;
    }

    public static int c(String str) {
        char cCharAt = str.charAt(1);
        int i2 = 1;
        int i3 = 1;
        while (cCharAt != ')') {
            if (cCharAt == 'J' || cCharAt == 'D') {
                i2++;
                i3 += 2;
            } else {
                while (str.charAt(i2) == '[') {
                    i2++;
                }
                int iMax = i2 + 1;
                if (str.charAt(i2) == 'L') {
                    iMax = Math.max(iMax, str.indexOf(59, iMax) + 1);
                }
                i3++;
                i2 = iMax;
            }
            cCharAt = str.charAt(i2);
        }
        char cCharAt2 = str.charAt(i2 + 1);
        if (cCharAt2 == 'V') {
            return i3 << 2;
        }
        return (i3 << 2) | ((cCharAt2 == 'J' || cCharAt2 == 'D') ? 2 : 1);
    }

    public static C3050xi0 d(String str) {
        return new C3050xi0(11, 0, str.length(), str);
    }

    public static C3050xi0 e(String str) {
        return new C3050xi0(str.charAt(0) == '[' ? 9 : 12, 0, str.length(), str);
    }

    public static int f(String str) {
        int iMax = 1;
        while (str.charAt(iMax) != ')') {
            while (str.charAt(iMax) == '[') {
                iMax++;
            }
            int i2 = iMax + 1;
            iMax = str.charAt(iMax) == 'L' ? Math.max(i2, str.indexOf(59, i2) + 1) : i2;
        }
        return iMax + 1;
    }

    public static C3050xi0 g(String str) {
        return a(0, str.length(), str);
    }

    public final String a() {
        switch (this.a) {
            case 0:
                return "void";
            case 1:
                return "boolean";
            case 2:
                return "char";
            case XmlPullParser.END_TAG /* 3 */:
                return "byte";
            case 4:
                return "short";
            case XmlPullParser.CDSECT /* 5 */:
                return "int";
            case XmlPullParser.ENTITY_REF /* 6 */:
                return "float";
            case 7:
                return "long";
            case 8:
                return "double";
            case 9:
                int i2 = 1;
                int i3 = 1;
                while (this.b.charAt(this.c + i3) == '[') {
                    i3++;
                }
                StringBuilder sb = new StringBuilder(a(this.c + i3, this.d, this.b).a());
                while (this.b.charAt(this.c + i2) == '[') {
                    i2++;
                }
                while (i2 > 0) {
                    sb.append("[]");
                    i2--;
                }
                return sb.toString();
            case XmlPullParser.DOCDECL /* 10 */:
            case 12:
                return this.b.substring(this.c, this.d).replace(DataResource.SEPARATOR, '.');
            case AndroidSdkVersion.HONEYCOMB /* 11 */:
            default:
                x1f.a();
                return null;
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C3050xi0)) {
            return false;
        }
        C3050xi0 c3050xi0 = (C3050xi0) obj;
        int i2 = this.a;
        if (i2 == 12) {
            i2 = 10;
        }
        int i3 = c3050xi0.a;
        if (i2 != (i3 != 12 ? i3 : 10)) {
            return false;
        }
        int i4 = this.c;
        int i5 = this.d;
        int i6 = c3050xi0.c;
        if (i5 - i4 != c3050xi0.d - i6) {
            return false;
        }
        while (i4 < i5) {
            if (this.b.charAt(i4) != c3050xi0.b.charAt(i6)) {
                return false;
            }
            i4++;
            i6++;
        }
        return true;
    }

    public final int hashCode() {
        int i2 = this.a;
        int iCharAt = (i2 == 12 ? 10 : i2) * 13;
        if (i2 >= 9) {
            int i3 = this.d;
            for (int i4 = this.c; i4 < i3; i4++) {
                iCharAt = (this.b.charAt(i4) + iCharAt) * 17;
            }
        }
        return iCharAt;
    }

    public final String toString() {
        return b();
    }

    public final String b() {
        int i2 = this.a;
        if (i2 == 10) {
            return this.b.substring(this.c - 1, this.d + 1);
        }
        String str = this.b;
        if (i2 == 12) {
            return C40.a("L", str.substring(this.c, this.d), ";");
        }
        return str.substring(this.c, this.d);
    }

    public final int c() {
        int i2 = this.a;
        if (i2 == 12) {
            return 10;
        }
        return i2;
    }

    public static C3050xi0 a(int i2, int i3, String str) {
        char cCharAt = str.charAt(i2);
        if (cCharAt == '(') {
            return new C3050xi0(11, i2, i3, str);
        }
        if (cCharAt == 'F') {
            return k;
        }
        if (cCharAt == 'L') {
            return new C3050xi0(10, i2 + 1, i3 - 1, str);
        }
        if (cCharAt == 'S') {
            return i;
        }
        if (cCharAt == 'V') {
            return e;
        }
        if (cCharAt == 'I') {
            return j;
        }
        if (cCharAt == 'J') {
            return l;
        }
        if (cCharAt == 'Z') {
            return f;
        }
        if (cCharAt != '[') {
            switch (cCharAt) {
                case 'B':
                    return h;
                case 'C':
                    return g;
                case 'D':
                    return m;
                default:
                    w01.a("Invalid descriptor: ".concat(str));
                    return null;
            }
        }
        return new C3050xi0(9, i2, i3, str);
    }

    public static int a(String str) {
        int i2 = 0;
        int iMax = 1;
        while (str.charAt(iMax) != ')') {
            while (str.charAt(iMax) == '[') {
                iMax++;
            }
            int i3 = iMax + 1;
            iMax = str.charAt(iMax) == 'L' ? Math.max(i3, str.indexOf(59, i3) + 1) : i3;
            i2++;
        }
        return i2;
    }
}
