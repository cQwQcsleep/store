package org.joni;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public class ConfigSupport {
    public static boolean getBoolean(String str, boolean z) {
        return !System.getProperty(str, z ? "true" : "false").equals("false");
    }

    public static int getInt(String str, int i) {
        String property = System.getProperty(str);
        return property != null ? Integer.parseInt(property) : i;
    }
}
