package org.bouncycastle.pkix.jcajce;

import java.security.cert.CertPathValidatorException;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
class CRLNotFoundException extends CertPathValidatorException {
    public CRLNotFoundException(String str) {
        super(str);
    }

    public CRLNotFoundException(String str, Throwable th) {
        super(str, th);
    }
}
