package com.sun.org.apache.xml.internal.utils;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class WrappedRuntimeException extends RuntimeException {
    static final long serialVersionUID = 7140414456714658073L;
    private Exception m_exception;

    public WrappedRuntimeException(Exception exc) {
        super(exc.getMessage());
        this.m_exception = exc;
    }

    public Exception getException() {
        return this.m_exception;
    }

    public WrappedRuntimeException(String str, Exception exc) {
        super(str);
        this.m_exception = exc;
    }
}
