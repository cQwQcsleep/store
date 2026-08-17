package com.sun.tools.javac.platform;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class PlatformUtils {
    public static PlatformDescription lookupPlatformDescription(String str) {
        System.out.println("lookupPlatformDescription: " + str);
        int iIndexOf = str.indexOf(":");
        try {
            return new JDKPlatformProvider().getPlatform(iIndexOf != -1 ? str.substring(0, iIndexOf) : str, iIndexOf != -1 ? str.substring(iIndexOf + 1) : "");
        } catch (PlatformProvider.PlatformNotSupported unused) {
            return null;
        }
    }
}
