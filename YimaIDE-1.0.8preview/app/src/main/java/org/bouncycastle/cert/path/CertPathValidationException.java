package org.bouncycastle.cert.path;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class CertPathValidationException extends Exception {
    private final Exception cause;

    public CertPathValidationException(String str, Exception exc) {
        super(str);
        this.cause = exc;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    public CertPathValidationException(String str) {
        this(str, null);
    }
}
