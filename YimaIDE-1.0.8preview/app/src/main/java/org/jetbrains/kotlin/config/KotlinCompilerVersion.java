package org.jetbrains.kotlin.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public class KotlinCompilerVersion {
    private static final boolean IS_PRE_RELEASE = false;
    public static final String TEST_IS_PRE_RELEASE_SYSTEM_PROPERTY = "kotlin.test.is.pre.release";
    public static final String VERSION;
    public static final String VERSION_FILE_PATH = "/META-INF/compiler.version";

    static {
        try {
            String strLoadKotlinCompilerVersion = loadKotlinCompilerVersion();
            VERSION = strLoadKotlinCompilerVersion;
            if (strLoadKotlinCompilerVersion.equals("@snapshot@")) {
                return;
            }
            strLoadKotlinCompilerVersion.contains("-");
        } catch (IOException unused) {
            k2d.a("Failed to read compiler version from /META-INF/compiler.version");
        }
    }

    public static String getVersion() {
        String str = VERSION;
        if (str.equals("@snapshot@")) {
            return null;
        }
        return str;
    }

    public static boolean isPreRelease() {
        String property = System.getProperty(TEST_IS_PRE_RELEASE_SYSTEM_PROPERTY);
        if (property != null) {
            return Boolean.parseBoolean(property);
        }
        return false;
    }

    private static String loadKotlinCompilerVersion() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(KotlinCompilerVersion.class.getResourceAsStream(VERSION_FILE_PATH)));
        try {
            return bufferedReader.readLine();
        } finally {
            bufferedReader.close();
        }
    }
}
