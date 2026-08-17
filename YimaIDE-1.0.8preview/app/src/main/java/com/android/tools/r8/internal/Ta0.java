package com.android.tools.r8.internal;

import java.util.Objects;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class Ta0 implements Comparable<Ta0> {
    public static final Ta0 f = a(0, 0, 0);
    public static final Ta0 g = a(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
    public final int b;
    public final int c;
    public final int d;
    public final String e;

    public Ta0(int i, int i2, int i3, String str) {
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.e = str;
    }

    public static Ta0 a(String str) {
        int iIndexOf = str.indexOf(46);
        String strSubstring = null;
        if (iIndexOf <= 0) {
            w01.a("Invalid semantic version: ".concat(str));
            return null;
        }
        int i = iIndexOf + 1;
        int iIndexOf2 = str.indexOf(46, i);
        if (iIndexOf2 <= i) {
            w01.a("Invalid semantic version: ".concat(str));
            return null;
        }
        int i2 = iIndexOf2 + 1;
        int iIndexOf3 = str.indexOf(45, i2);
        int length = -1;
        if (iIndexOf3 == -1) {
            iIndexOf3 = str.length();
        } else {
            if (iIndexOf3 <= i2) {
                w01.a("Invalid semantic version: ".concat(str));
                return null;
            }
            length = str.length();
        }
        try {
            int i3 = Integer.parseInt(str.substring(0, iIndexOf));
            int i4 = Integer.parseInt(str.substring(i, iIndexOf2));
            int i5 = Integer.parseInt(str.substring(i2, iIndexOf3));
            if (length >= 0) {
                strSubstring = str.substring(iIndexOf3 + 1, length);
            }
            return new Ta0(i3, i4, i5, strSubstring);
        } catch (NumberFormatException e) {
            nrd.a("Invalid semantic version: ".concat(str), e);
            return null;
        }
    }

    public static Ta0 d() {
        return g;
    }

    public boolean b(Ta0 ta0) {
        return a(ta0) || equals(ta0);
    }

    public int c() {
        return this.d;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Ta0 ta0) {
        Ta0 ta1 = ta0;
        if (equals(ta1)) {
            return 0;
        }
        return b(ta1) ? -1 : 1;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof Ta0)) {
            return false;
        }
        Ta0 ta0 = (Ta0) obj;
        return this.b == ta0.b && this.c == ta0.c && this.d == ta0.d && Objects.equals(this.e, ta0.e);
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(this.b), Integer.valueOf(this.c), Integer.valueOf(this.d), this.e);
    }

    public String toString() {
        int i = this.b;
        int i2 = this.c;
        int i3 = this.d;
        String str = this.e;
        return i + "." + i2 + "." + i3 + (str != null ? "-".concat(str) : XmlPullParser.NO_NAMESPACE);
    }

    public int b() {
        return this.c;
    }

    public static Ta0 a(int i, int i2, int i3) {
        return new Ta0(i, i2, i3, null);
    }

    public int a() {
        return this.b;
    }

    public final boolean a(Ta0 ta0) {
        int i = this.b;
        int i2 = ta0.b;
        if (i != i2) {
            return i > i2;
        }
        int i3 = this.c;
        int i4 = ta0.c;
        if (i3 != i4) {
            return i3 > i4;
        }
        return this.d > ta0.d;
    }
}
