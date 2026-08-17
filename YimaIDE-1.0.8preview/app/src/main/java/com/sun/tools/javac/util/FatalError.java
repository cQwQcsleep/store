package com.sun.tools.javac.util;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class FatalError extends Error {
    private static final long serialVersionUID = 0;

    public FatalError(JCDiagnostic jCDiagnostic) {
        super(jCDiagnostic.toString());
    }

    public FatalError(JCDiagnostic jCDiagnostic, Throwable th) {
        super(jCDiagnostic.toString(), th);
    }

    public FatalError(String str) {
        super(str);
    }
}
