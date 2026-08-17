package org.bouncycastle.cert.jcajce;

import java.security.Provider;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
class ProviderCertHelper extends CertHelper {
    private final Provider provider;

    public ProviderCertHelper(Provider provider) {
        this.provider = provider;
    }

    @Override // org.bouncycastle.cert.jcajce.CertHelper
    public CertificateFactory createCertificateFactory(String str) throws CertificateException {
        return CertificateFactory.getInstance(str, this.provider);
    }
}
