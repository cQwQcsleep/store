package org.bouncycastle.tsp;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class TSPIOException extends IOException {
    Throwable underlyingException;

    public TSPIOException(String str, Throwable th) {
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

    public TSPIOException(String str) {
        super(str);
    }
}
