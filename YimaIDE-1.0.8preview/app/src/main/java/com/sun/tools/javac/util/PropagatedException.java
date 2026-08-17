package com.sun.tools.javac.util;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class PropagatedException extends RuntimeException {
    static final long serialVersionUID = -6065309339888775367L;

    public PropagatedException(RuntimeException runtimeException) {
        super(runtimeException);
    }

    @Override // java.lang.Throwable
    public RuntimeException getCause() {
        return (RuntimeException) super.getCause();
    }
}
