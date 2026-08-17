package com.android.tools.r8.internal;

import defpackage.le6;
import defpackage.q68;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public enum Gl0 {
    b,
    c,
    d,
    e,
    f,
    g,
    h,
    i;

    Gl0() {
    }

    public static Gl0 a(AbstractC2624sj0 abstractC2624sj0) {
        if (abstractC2624sj0.I()) {
            return b;
        }
        if (abstractC2624sj0.A() || abstractC2624sj0.C()) {
            return c;
        }
        if (abstractC2624sj0.B()) {
            return d;
        }
        if (abstractC2624sj0.D()) {
            return g;
        }
        if (abstractC2624sj0.z()) {
            return h;
        }
        if (abstractC2624sj0.K()) {
            return e;
        }
        if (abstractC2624sj0.M()) {
            return i;
        }
        if (abstractC2624sj0.L()) {
            return f;
        }
        defpackage.gk0.a("Unexpected conversion of type: ", abstractC2624sj0);
        return null;
    }

    public final boolean b() {
        return (this == e || this == i || this == f) ? false : true;
    }

    public final int c() {
        return (this == g || this == h || this == i) ? 2 : 1;
    }

    public final AbstractC2005lY d() {
        int iOrdinal = ordinal();
        if (iOrdinal == 1) {
            return AbstractC2624sj0.k();
        }
        if (iOrdinal == 2) {
            return AbstractC2624sj0.j();
        }
        if (iOrdinal == 5) {
            return AbstractC2624sj0.l();
        }
        if (iOrdinal == 6) {
            return AbstractC2624sj0.i();
        }
        defpackage.gk0.a("Unexpected type in conversion to primitive: ", this);
        return null;
    }

    public static Gl0 a(El0 el0) {
        int i2 = Fl0.a[el0.ordinal()];
        if (i2 == 1) {
            return b;
        }
        if (i2 == 2) {
            return c;
        }
        if (i2 == 3) {
            return d;
        }
        if (i2 == 4) {
            return g;
        }
        if (i2 == 5) {
            return h;
        }
        defpackage.gk0.a("Unexpected value type: ", el0);
        return null;
    }

    public static Gl0 a(KN kn) {
        switch (kn.ordinal()) {
            case 0:
                return b;
            case 1:
            case 2:
            case XmlPullParser.END_TAG /* 3 */:
            case 4:
                return c;
            case XmlPullParser.CDSECT /* 5 */:
                return d;
            case XmlPullParser.ENTITY_REF /* 6 */:
                return g;
            case 7:
                return h;
            case 8:
                return e;
            case 9:
                return i;
            default:
                defpackage.gk0.a("Unexpected member type: ", kn);
                return null;
        }
    }

    public static Gl0 a(char c2) {
        if (c2 != 'F') {
            if (c2 != 'L') {
                if (c2 != 'S') {
                    if (c2 == 'V') {
                        throw new C1727iB("No value type for void type.");
                    }
                    if (c2 != 'I') {
                        if (c2 == 'J') {
                            return g;
                        }
                        if (c2 != 'Z') {
                            if (c2 != '[') {
                                switch (c2) {
                                    case 'B':
                                    case 'C':
                                        break;
                                    case 'D':
                                        return h;
                                    default:
                                        q68.a(c2);
                                        return null;
                                }
                            }
                        }
                    }
                }
                return c;
            }
            return b;
        }
        return d;
    }

    public static Gl0 a(com.android.tools.r8.graph.I2 i2) {
        return a((char) i2.f.f[0]);
    }

    public static Gl0 a(US us) {
        switch (Fl0.c[us.ordinal()]) {
            case 1:
            case 2:
            case XmlPullParser.END_TAG /* 3 */:
            case 4:
                return c;
            case XmlPullParser.CDSECT /* 5 */:
                return d;
            case XmlPullParser.ENTITY_REF /* 6 */:
                return g;
            case 7:
                return h;
            default:
                le6.a("Invalid numeric type '", us, "'");
                return null;
        }
    }

    public final boolean a() {
        return this == b;
    }
}
