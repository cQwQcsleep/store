package org.bouncycastle.cert.cmp;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class CMPException extends Exception {
    private Throwable cause;

    public CMPException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    public CMPException(String str) {
        super(str);
    }
}
