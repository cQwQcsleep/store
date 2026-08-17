package com.android.tools.r8.internal;

import com.android.apksig.internal.util.AndroidSdkVersion;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.uZ, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public enum EnumC2776uZ implements ZA {
    c("BYTE"),
    d("CHAR"),
    e("SHORT"),
    f("INT"),
    g("LONG"),
    h("FLOAT"),
    i("DOUBLE"),
    j("BOOLEAN"),
    k("STRING"),
    l("CLASS"),
    m("ENUM"),
    n("ANNOTATION"),
    o("ARRAY");

    public final int b;

    EnumC2776uZ(String str) {
        this.b = i;
    }

    public static EnumC2776uZ b(int i2) {
        switch (i2) {
            case 0:
                return c;
            case 1:
                return d;
            case 2:
                return e;
            case XmlPullParser.END_TAG /* 3 */:
                return f;
            case 4:
                return g;
            case XmlPullParser.CDSECT /* 5 */:
                return h;
            case XmlPullParser.ENTITY_REF /* 6 */:
                return i;
            case 7:
                return j;
            case 8:
                return k;
            case 9:
                return l;
            case XmlPullParser.DOCDECL /* 10 */:
                return m;
            case AndroidSdkVersion.HONEYCOMB /* 11 */:
                return n;
            case 12:
                return o;
            default:
                return null;
        }
    }

    @Override // com.android.tools.r8.internal.ZA
    public final int a() {
        return this.b;
    }
}
