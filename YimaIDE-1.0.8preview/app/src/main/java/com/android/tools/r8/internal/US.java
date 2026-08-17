package com.android.tools.r8.internal;

import defpackage.le6;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public enum US {
    b,
    c,
    d,
    e,
    f,
    g,
    h;

    US() {
    }

    public final com.android.tools.r8.graph.I2 a(com.android.tools.r8.graph.B1 b1) {
        switch (TS.a[ordinal()]) {
            case 1:
                return b1.x1;
            case 2:
                return b1.y1;
            case XmlPullParser.END_TAG /* 3 */:
                return b1.D1;
            case 4:
                return b1.B1;
            case XmlPullParser.CDSECT /* 5 */:
                return b1.C1;
            case XmlPullParser.ENTITY_REF /* 6 */:
                return b1.A1;
            case 7:
                return b1.z1;
            default:
                le6.a("Invalid numeric type '", this, "'");
                return null;
        }
    }

    public static US a(com.android.tools.r8.graph.I2 i2) {
        byte b2 = i2.f.f[0];
        if (b2 == 70) {
            return g;
        }
        if (b2 == 83) {
            return d;
        }
        if (b2 == 73) {
            return e;
        }
        if (b2 != 74) {
            switch (b2) {
                case 66:
                    return b;
                case 67:
                    return c;
                case 68:
                    return h;
                default:
                    return null;
            }
        }
        return f;
    }

    public boolean a() {
        return this == f || this == h;
    }
}
