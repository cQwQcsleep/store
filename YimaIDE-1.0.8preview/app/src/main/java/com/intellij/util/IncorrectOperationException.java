package com.intellij.util;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class IncorrectOperationException extends RuntimeException {
    public IncorrectOperationException() {
    }

    public IncorrectOperationException(String str) {
        super(str);
    }

    public IncorrectOperationException(Throwable th) {
        super(th);
    }

    public IncorrectOperationException(String str, Throwable th) {
        super(str, th);
    }

    @Deprecated
    public IncorrectOperationException(String str, Exception exc) {
        super(str, exc);
    }
}
