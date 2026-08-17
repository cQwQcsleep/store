package com.sun.tools.javac;

import java.io.PrintWriter;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Main {
    @Deprecated
    public Main() {
    }

    public static int compile(String[] strArr) {
        return new com.sun.tools.javac.main.Main("javac").compile(strArr).exitCode;
    }

    public static void main(String[] strArr) throws Exception {
        System.exit(compile(strArr));
    }

    public static int compile(String[] strArr, PrintWriter printWriter) {
        return new com.sun.tools.javac.main.Main("javac", printWriter).compile(strArr).exitCode;
    }
}
