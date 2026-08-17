package com.sun.org.apache.xerces.internal.xni;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XNIException extends RuntimeException {
    static final long serialVersionUID = 9019819772686063775L;
    private Exception fException;

    public XNIException(Exception exc) {
        super(exc.getMessage());
        this.fException = exc;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.fException;
    }

    public Exception getException() {
        return this.fException;
    }

    public XNIException(String str) {
        super(str);
    }

    public XNIException(String str, Exception exc) {
        super(str);
        this.fException = exc;
    }
}
