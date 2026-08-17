package com.android.tools.r8;

import com.android.tools.r8.internal.Pl0;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Version {
    public static final String LABEL = "8.5.10";

    public static int a(String str) {
        if (str.equals("main")) {
            return -1;
        }
        return Integer.parseInt(str.substring(0, str.indexOf(46)));
    }

    public static int b(String str) {
        if (str.equals("main")) {
            return -1;
        }
        int iIndexOf = str.indexOf(46) + 1;
        return Integer.parseInt(str.substring(iIndexOf, str.indexOf(46, iIndexOf)));
    }

    public static int c(String str) {
        if (str.equals("main")) {
            return -1;
        }
        int iIndexOf = str.indexOf(46, str.indexOf(46) + 1) + 1;
        int iIndexOf2 = str.indexOf(45, iIndexOf);
        if (iIndexOf2 == -1) {
            iIndexOf2 = str.length();
        }
        return Integer.parseInt(str.substring(iIndexOf, iIndexOf2));
    }

    public static String d(String str) {
        if (str.equals("main")) {
            return null;
        }
        int iIndexOf = str.indexOf(45) + 1;
        return iIndexOf > 0 ? str.substring(iIndexOf) : XmlPullParser.NO_NAMESPACE;
    }

    public static int getMajorVersion() {
        return a(LABEL);
    }

    public static int getMinorVersion() {
        return b(LABEL);
    }

    public static int getPatchVersion() {
        return c(LABEL);
    }

    public static String getPreReleaseString() {
        return d(LABEL);
    }

    public static String getVersionString() {
        return "8.5.10 (" + Pl0.c.a() + ")";
    }

    public static boolean isDevelopmentVersion() {
        return a(LABEL, Pl0.c.c());
    }

    public static boolean a() {
        return false;
    }

    public static boolean a(String str, boolean z) {
        return str.equals("main") || str.endsWith("-dev") || z;
    }
}
