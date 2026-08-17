package com.android.tools.r8.internal;

import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.lH, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1988lH {
    public static final C1988lH d = new C1988lH(null, null, null);
    public final String a;
    public final String b;
    public final String c;

    public C1988lH(String str, String str2, String str3) {
        this.a = str;
        this.b = str2;
        this.c = str3;
    }

    public static C1988lH a(String str) {
        return str != null ? new C1988lH(str, null, null) : d;
    }

    public final String toString() {
        if (d == this) {
            return "<*>";
        }
        String str = this.a;
        if (str != null) {
            return str;
        }
        String str2 = this.b;
        String str3 = XmlPullParser.NO_NAMESPACE;
        if (str2 == null) {
            str2 = XmlPullParser.NO_NAMESPACE;
        }
        String str4 = this.c;
        if (str4 != null) {
            str3 = str4;
        }
        return str2 + "<*>" + str3;
    }
}
