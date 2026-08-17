package org.bouncycastle.dvcs;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class DVCSException extends Exception {
    private static final long serialVersionUID = 389345256020131488L;
    private Throwable cause;

    public DVCSException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    public DVCSException(String str) {
        super(str);
    }
}
