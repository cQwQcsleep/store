package com.reandroid.dex.base;

import androidx.compose.compiler.plugins.kotlin.analysis.StabilityExternalClassNameMatchingKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class DexException extends RuntimeException {
    private static final int MAX_TRACE = 15;

    public DexException(String str) {
        super(str);
    }

    private static void append(Throwable th, StringBuilder sb) {
        if (th == null) {
            return;
        }
        sb.append(th.getClass().getName());
        sb.append(": ");
        sb.append(th.getMessage());
        StackTraceElement[] stackTrace = th.getStackTrace();
        int length = stackTrace.length;
        if (length > 15) {
            length = 15;
        }
        for (int i = 0; i < length; i++) {
            append(stackTrace[i], sb);
        }
        Throwable cause = th.getCause();
        if (cause == null || cause == th) {
            return;
        }
        sb.append("Caused by: ");
        append(cause, sb);
    }

    @Override // java.lang.Throwable
    public String toString() {
        StringBuilder sb = new StringBuilder();
        append(this, sb);
        return sb.toString();
    }

    public DexException(String str, Throwable th) {
        super(str, th);
    }

    public DexException(Throwable th) {
        super(th);
    }

    private static void append(StackTraceElement stackTraceElement, StringBuilder sb) {
        if (stackTraceElement == null) {
            return;
        }
        sb.append("\n\tat ");
        sb.append(stackTraceElement.getClassName());
        sb.append(StabilityExternalClassNameMatchingKt.STABILITY_PACKAGE_SEPARATOR);
        sb.append(stackTraceElement.getMethodName());
        sb.append('(');
        sb.append(stackTraceElement.getFileName());
        sb.append(':');
        sb.append(stackTraceElement.getLineNumber());
        sb.append(')');
    }
}
