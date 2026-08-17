package com.sun.tools.javac.util;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class CompilerInternalException extends RuntimeException {
    private static final long serialVersionUID = 0;

    public CompilerInternalException(boolean z) {
        if (z) {
            super.fillInStackTrace();
        }
    }

    @Override // java.lang.Throwable
    public Throwable fillInStackTrace() {
        return this;
    }
}
