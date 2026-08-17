package org.bouncycastle.pkcs;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class PKCSException extends Exception {
    private Throwable cause;

    public PKCSException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    public PKCSException(String str) {
        super(str);
    }
}
