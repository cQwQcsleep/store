package com.sun.org.apache.xalan.internal.utils;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class ConfigurationError extends Error {
    private static final long serialVersionUID = 749136645488750664L;
    private Exception exception;

    public ConfigurationError(String str, Exception exc) {
        super(str);
        this.exception = exc;
    }

    public Exception getException() {
        return this.exception;
    }
}
