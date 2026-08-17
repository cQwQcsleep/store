package org.bouncycastle.eac;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class EACException extends Exception {
    private Throwable cause;

    public EACException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    public EACException(String str) {
        super(str);
    }
}
