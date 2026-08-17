package com.sun.org.apache.xerces.internal.utils;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class ConfigurationError extends Error {
    private static final long serialVersionUID = 8095902236393167968L;
    private Exception exception;

    public ConfigurationError(String str, Exception exc) {
        super(str);
        this.exception = exc;
    }

    public Exception getException() {
        return this.exception;
    }
}
