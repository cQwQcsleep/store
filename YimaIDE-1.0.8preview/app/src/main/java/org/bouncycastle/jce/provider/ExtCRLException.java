package org.bouncycastle.jce.provider;

import java.security.cert.CRLException;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
class ExtCRLException extends CRLException {
    Throwable cause;

    public ExtCRLException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }
}
