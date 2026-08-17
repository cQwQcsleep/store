package org.antlr.v4.runtime;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
public class RuntimeMetaData {
    public static final String VERSION = "4.13.2";

    public static void checkVersion(String str, String str2) {
        boolean z = (str == null || VERSION.equals(str) || getMajorMinorVersion(VERSION).equals(getMajorMinorVersion(str))) ? false : true;
        boolean z2 = (VERSION.equals(str2) || getMajorMinorVersion(VERSION).equals(getMajorMinorVersion(str2))) ? false : true;
        if (z) {
            System.err.printf("ANTLR Tool version %s used for code generation does not match the current runtime version %s%n", str, VERSION);
        }
        if (z2) {
            System.err.printf("ANTLR Runtime version %s used for parser compilation does not match the current runtime version %s%n", str2, VERSION);
        }
    }

    public static String getMajorMinorVersion(String str) {
        int iIndexOf = str.indexOf(46);
        int iIndexOf2 = iIndexOf >= 0 ? str.indexOf(46, iIndexOf + 1) : -1;
        int iIndexOf3 = str.indexOf(45);
        int length = str.length();
        if (iIndexOf2 >= 0) {
            length = Math.min(length, iIndexOf2);
        }
        if (iIndexOf3 >= 0) {
            length = Math.min(length, iIndexOf3);
        }
        return str.substring(0, length);
    }

    public static String getRuntimeVersion() {
        return VERSION;
    }
}
