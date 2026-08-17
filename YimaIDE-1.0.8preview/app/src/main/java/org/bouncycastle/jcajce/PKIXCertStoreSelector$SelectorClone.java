package org.bouncycastle.jcajce;

import java.io.IOException;
import java.security.cert.Certificate;
import java.security.cert.X509CertSelector;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class PKIXCertStoreSelector$SelectorClone extends X509CertSelector {
    private final PKIXCertStoreSelector selector;

    public PKIXCertStoreSelector$SelectorClone(PKIXCertStoreSelector pKIXCertStoreSelector) {
        this.selector = pKIXCertStoreSelector;
        if (PKIXCertStoreSelector.access$100(pKIXCertStoreSelector) instanceof X509CertSelector) {
            X509CertSelector x509CertSelector = (X509CertSelector) PKIXCertStoreSelector.access$100(pKIXCertStoreSelector);
            setAuthorityKeyIdentifier(x509CertSelector.getAuthorityKeyIdentifier());
            setBasicConstraints(x509CertSelector.getBasicConstraints());
            setCertificate(x509CertSelector.getCertificate());
            setCertificateValid(x509CertSelector.getCertificateValid());
            setKeyUsage(x509CertSelector.getKeyUsage());
            setMatchAllSubjectAltNames(x509CertSelector.getMatchAllSubjectAltNames());
            setPrivateKeyValid(x509CertSelector.getPrivateKeyValid());
            setSerialNumber(x509CertSelector.getSerialNumber());
            setSubjectKeyIdentifier(x509CertSelector.getSubjectKeyIdentifier());
            setSubjectPublicKey(x509CertSelector.getSubjectPublicKey());
            try {
                setExtendedKeyUsage(x509CertSelector.getExtendedKeyUsage());
                setIssuer(x509CertSelector.getIssuerAsBytes());
                setNameConstraints(x509CertSelector.getNameConstraints());
                setPathToNames(x509CertSelector.getPathToNames());
                setPolicy(x509CertSelector.getPolicy());
                setSubject(x509CertSelector.getSubjectAsBytes());
                setSubjectAlternativeNames(x509CertSelector.getSubjectAlternativeNames());
                setSubjectPublicKeyAlgID(x509CertSelector.getSubjectPublicKeyAlgID());
            } catch (IOException e) {
                gma.a("base selector invalid: ", e.getMessage(), e);
                throw null;
            }
        }
    }

    @Override // java.security.cert.X509CertSelector, java.security.cert.CertSelector
    public boolean match(Certificate certificate) {
        PKIXCertStoreSelector pKIXCertStoreSelector = this.selector;
        if (pKIXCertStoreSelector == null) {
            return certificate != null;
        }
        return pKIXCertStoreSelector.match(certificate);
    }
}
