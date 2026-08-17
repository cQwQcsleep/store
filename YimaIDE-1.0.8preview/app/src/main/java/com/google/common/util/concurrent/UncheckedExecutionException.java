package com.google.common.util.concurrent;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
public class UncheckedExecutionException extends RuntimeException {
    private static final long serialVersionUID = 0;

    @Deprecated
    public UncheckedExecutionException() {
    }

    @Deprecated
    public UncheckedExecutionException(String str) {
        super(str);
    }

    public UncheckedExecutionException(String str, Throwable th) {
        super(str, th);
    }

    public UncheckedExecutionException(Throwable th) {
        super(th);
    }
}
