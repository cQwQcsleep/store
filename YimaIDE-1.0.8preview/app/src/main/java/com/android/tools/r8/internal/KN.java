package com.android.tools.r8.internal;

import defpackage.q68;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public enum KN {
    b,
    c,
    d,
    e,
    f,
    g,
    h,
    i,
    j,
    k;

    KN() {
    }

    /* JADX WARN: Code duplicated, block: B:38:0x004a A[RETURN] */
    public static KN a(KN kn, Gl0 gl0) {
        switch (gl0.ordinal()) {
            case 0:
                KN kn2 = b;
                if (kn == kn2) {
                    return kn2;
                }
                return null;
            case 1:
                KN kn3 = f;
                if (kn == kn3 || kn == j) {
                    return kn3;
                }
                return null;
            case 2:
                KN kn4 = g;
                if (kn == kn4 || kn == j) {
                    return kn4;
                }
                return null;
            case XmlPullParser.END_TAG /* 3 */:
                if (kn == f || kn == g || kn == j) {
                    return kn;
                }
                return null;
            case 4:
                if (kn == f || kn == g || kn == b || kn == j) {
                    return kn;
                }
                return null;
            case XmlPullParser.CDSECT /* 5 */:
                KN kn5 = h;
                if (kn == kn5 || kn == k) {
                    return kn5;
                }
                return null;
            case XmlPullParser.ENTITY_REF /* 6 */:
                KN kn6 = i;
                if (kn == kn6 || kn == k) {
                    return kn6;
                }
                return null;
            case 7:
                if (kn == h || kn == i || kn == k) {
                    return kn;
                }
                return null;
            default:
                defpackage.gk0.a("Unexpected type constraint: ", gl0);
                return null;
        }
    }

    public final boolean a() {
        return (this == j || this == k) ? false : true;
    }

    public static KN a(char c2) {
        if (c2 != 'F') {
            if (c2 != 'L' && c2 != 'N') {
                if (c2 == 'S') {
                    return e;
                }
                if (c2 == 'V') {
                    throw new C1727iB("No member type for void type.");
                }
                if (c2 == 'I') {
                    return f;
                }
                if (c2 != 'J') {
                    if (c2 != 'Z') {
                        if (c2 != '[') {
                            switch (c2) {
                                case 'B':
                                    break;
                                case 'C':
                                    return d;
                                case 'D':
                                    return i;
                                default:
                                    q68.a(c2);
                                    return null;
                            }
                        }
                    }
                    return c;
                }
                return h;
            }
            return b;
        }
        return g;
    }
}
