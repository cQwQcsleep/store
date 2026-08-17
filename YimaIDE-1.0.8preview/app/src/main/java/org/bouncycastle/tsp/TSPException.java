package org.bouncycastle.tsp;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class TSPException extends Exception {
    Throwable underlyingException;

    public TSPException(String str, Throwable th) {
        super(str);
        this.underlyingException = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.underlyingException;
    }

    public Exception getUnderlyingException() {
        return (Exception) this.underlyingException;
    }

    public TSPException(String str) {
        super(str);
    }
}
