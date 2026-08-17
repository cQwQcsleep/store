package org.jline.utils;

import java.io.File;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class OSUtils {
    public static String INFOCMP_COMMAND;
    public static final boolean IS_AIX;
    public static final boolean IS_CONEMU;
    public static final boolean IS_CYGWIN;

    @Deprecated
    public static final boolean IS_MINGW;
    public static final boolean IS_MSYSTEM;
    public static final boolean IS_OSX;
    public static final boolean IS_WINDOWS;
    public static final boolean IS_WSL;
    public static final boolean IS_WSL1;
    public static final boolean IS_WSL2;
    public static String STTY_COMMAND;
    public static String STTY_F_OPTION;
    public static String TEST_COMMAND;
    public static String TTY_COMMAND;

    static {
        String strConcat;
        String strConcat2;
        String strConcat3;
        boolean zContains = System.getProperty("os.name").toLowerCase().contains("win");
        IS_WINDOWS = zContains;
        boolean z = zContains && System.getenv("PWD") != null && System.getenv("PWD").startsWith("/");
        IS_CYGWIN = z;
        IS_MINGW = zContains && System.getenv("MSYSTEM") != null && System.getenv("MSYSTEM").startsWith("MINGW");
        boolean z2 = zContains && System.getenv("MSYSTEM") != null && (System.getenv("MSYSTEM").startsWith("MINGW") || System.getenv("MSYSTEM").equals("MSYS"));
        IS_MSYSTEM = z2;
        boolean z3 = System.getenv("WSL_DISTRO_NAME") != null;
        IS_WSL = z3;
        boolean z4 = z3 && System.getenv("WSL_INTEROP") == null;
        IS_WSL1 = z4;
        IS_WSL2 = z3 && !z4;
        IS_CONEMU = zContains && System.getenv("ConEmuPID") != null;
        IS_OSX = System.getProperty("os.name").toLowerCase().contains("mac");
        IS_AIX = System.getProperty("os.name").equals("AIX");
        String str = (z || z2) ? ".exe" : "";
        String str2 = System.getenv("PATH");
        String strConcat4 = null;
        if (str2 != null) {
            strConcat = null;
            strConcat2 = null;
            strConcat3 = null;
            for (String str3 : str2.split(File.pathSeparator)) {
                File file = new File(str3, "tty".concat(str));
                if (strConcat4 == null && file.canExecute()) {
                    strConcat4 = file.getAbsolutePath();
                }
                File file2 = new File(str3, "stty".concat(str));
                if (strConcat == null && file2.canExecute()) {
                    strConcat = file2.getAbsolutePath();
                }
                File file3 = new File(str3, "infocmp".concat(str));
                if (strConcat2 == null && file3.canExecute()) {
                    strConcat2 = file3.getAbsolutePath();
                }
                File file4 = new File(str3, "test".concat(str));
                if (strConcat3 == null && file4.canExecute()) {
                    strConcat3 = file4.getAbsolutePath();
                }
            }
        } else {
            strConcat = null;
            strConcat2 = null;
            strConcat3 = null;
        }
        if (strConcat4 == null) {
            strConcat4 = "tty".concat(str);
        }
        if (strConcat == null) {
            strConcat = "stty".concat(str);
        }
        if (strConcat2 == null) {
            strConcat2 = "infocmp".concat(str);
        }
        if (strConcat3 == null) {
            strConcat3 = "test".concat(str);
        }
        String str4 = IS_OSX ? "-f" : "-F";
        TTY_COMMAND = strConcat4;
        STTY_COMMAND = strConcat;
        STTY_F_OPTION = str4;
        INFOCMP_COMMAND = strConcat2;
        TEST_COMMAND = strConcat3;
    }
}
