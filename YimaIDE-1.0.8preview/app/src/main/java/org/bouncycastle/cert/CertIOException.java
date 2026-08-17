package org.bouncycastle.cert;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class CertIOException extends IOException {
    private Throwable cause;

    public CertIOException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    public CertIOException(String str) {
        super(str);
    }
}
