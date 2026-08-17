package org.bouncycastle.eac;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class EACIOException extends IOException {
    private Throwable cause;

    public EACIOException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    public EACIOException(String str) {
        super(str);
    }
}
