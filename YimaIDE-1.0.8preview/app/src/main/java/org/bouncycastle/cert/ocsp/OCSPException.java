package org.bouncycastle.cert.ocsp;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class OCSPException extends Exception {
    private Throwable cause;

    public OCSPException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    public OCSPException(String str) {
        super(str);
    }
}
