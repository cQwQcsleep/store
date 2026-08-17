package com.android.tools.r8.internal;

import defpackage.x0g;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.nu, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public enum EnumC2211nu {
    b,
    c,
    d,
    e,
    f,
    g;

    EnumC2211nu() {
    }

    public final EnumC2211nu a() {
        switch (AbstractC2125mu.a[ordinal()]) {
            case 1:
                return g;
            case 2:
                return b;
            case XmlPullParser.END_TAG /* 3 */:
                return f;
            case 4:
                return e;
            case XmlPullParser.CDSECT /* 5 */:
                return d;
            case XmlPullParser.ENTITY_REF /* 6 */:
                return c;
            default:
                x0g.a("Unknown if condition type.");
                return null;
        }
    }
}
