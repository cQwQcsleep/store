package org.bouncycastle.jce.provider;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class PKIXNameConstraintValidatorException extends Exception {
    private Throwable cause;

    public PKIXNameConstraintValidatorException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    public PKIXNameConstraintValidatorException(String str) {
        super(str);
    }
}
