package org.bouncycastle.cert.crmf;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class CRMFException extends Exception {
    private Throwable cause;

    public CRMFException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    public CRMFException(String str) {
        this(str, null);
    }
}
