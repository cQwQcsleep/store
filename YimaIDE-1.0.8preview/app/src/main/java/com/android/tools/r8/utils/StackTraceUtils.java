package com.android.tools.r8.utils;

import defpackage.f63;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class StackTraceUtils {
    private static final PrintStream a;
    private static final int b;
    private static int c;

    static {
        String property = System.getProperty("com.android.tools.r8.internalPathToStacktraces");
        if (property == null) {
            f63.a("pathToWriteStacktrace is null");
            return;
        }
        try {
            a = new PrintStream(property, StandardCharsets.UTF_8.name());
            String property2 = System.getProperty("com.android.tools.r8.internalStackTraceSamplingInterval");
            b = property2 == null ? 1000 : Integer.parseInt(property2);
            c = 0;
        } catch (IOException e) {
            rc6.a(e);
        }
    }

    public static void printCurrentStack(long j) {
        int i = c;
        int i2 = i + 1;
        c = i2;
        if (i < b) {
            RuntimeException runtimeException = new RuntimeException("------(" + j + "," + i2 + ")------");
            PrintStream printStream = a;
            runtimeException.printStackTrace(printStream);
            printStream.println("@@@@");
        }
    }
}
