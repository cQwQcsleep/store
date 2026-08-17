package org.eclipse.jdt.internal.compiler.env;

import java.io.File;
import java.io.IOException;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class AutomaticModuleNaming {
    private static final String AUTOMATIC_MODULE_NAME = "Automatic-Module-Name";

    public static char[] determineAutomaticModuleName(String str) {
        String value;
        Throwable th = null;
        try {
            JarFile jarFile = new JarFile(str);
            try {
                Manifest manifest = jarFile.getManifest();
                return (manifest == null || (value = manifest.getMainAttributes().getValue("Automatic-Module-Name")) == null) ? determineAutomaticModuleNameFromFileName(str, true, true) : value.toCharArray();
            } finally {
                jarFile.close();
            }
        } catch (Throwable th2) {
            if (0 == 0) {
                throw th2;
            }
            if (null == th2) {
                throw null;
            }
            try {
                th.addSuppressed(th2);
                throw null;
            } catch (IOException unused) {
            }
        }
    }

    public static char[] determineAutomaticModuleNameFromFileName(String str, boolean z, boolean z2) {
        int i;
        int length = str.length();
        int iLastIndexOf = z ? str.lastIndexOf(File.separatorChar) + 1 : 0;
        if (z2 && (str.endsWith(".jar") || str.endsWith(".JAR"))) {
            length -= 4;
        }
        int i2 = iLastIndexOf;
        loop0: while (true) {
            if (i2 >= length - 1) {
                i = length;
                break;
            }
            if (str.charAt(i2) == '-') {
                int i3 = i2 + 1;
                if (str.charAt(i3) >= '0' && str.charAt(i3) <= '9') {
                    int i4 = i2 + 2;
                    while (true) {
                        if (i4 < length) {
                            char cCharAt = str.charAt(i4);
                            if (cCharAt != '.') {
                                if (cCharAt >= '0' && cCharAt <= '9') {
                                    i4++;
                                }
                            }
                        }
                        i = i2;
                        break loop0;
                    }
                }
            }
            i2++;
        }
        StringBuilder sb = new StringBuilder(i - iLastIndexOf);
        boolean z3 = false;
        while (iLastIndexOf < i) {
            char cCharAt2 = str.charAt(iLastIndexOf);
            if ((cCharAt2 >= 'A' && cCharAt2 <= 'Z') || ((cCharAt2 >= 'a' && cCharAt2 <= 'z') || (cCharAt2 >= '0' && cCharAt2 <= '9'))) {
                if (z3) {
                    sb.append('.');
                    z3 = false;
                }
                sb.append(cCharAt2);
            } else if (sb.length() > 0) {
                z3 = true;
            }
            iLastIndexOf++;
        }
        return sb.toString().toCharArray();
    }

    public static char[] determineAutomaticModuleNameFromManifest(Manifest manifest) {
        String value;
        if (manifest == null || (value = manifest.getMainAttributes().getValue("Automatic-Module-Name")) == null) {
            return null;
        }
        return value.toCharArray();
    }

    public static char[] determineAutomaticModuleName(String str, boolean z, Manifest manifest) {
        String value;
        if (manifest != null && (value = manifest.getMainAttributes().getValue("Automatic-Module-Name")) != null) {
            return value.toCharArray();
        }
        return determineAutomaticModuleNameFromFileName(str, true, z);
    }
}
