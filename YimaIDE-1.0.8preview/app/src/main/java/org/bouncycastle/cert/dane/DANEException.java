package org.bouncycastle.cert.dane;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class DANEException extends Exception {
    private Throwable cause;

    public DANEException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    public DANEException(String str) {
        super(str);
    }
}
