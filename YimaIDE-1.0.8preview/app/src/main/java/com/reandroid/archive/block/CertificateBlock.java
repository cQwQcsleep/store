package com.reandroid.archive.block;

import com.reandroid.json.JSONObject;
import java.security.cert.X509Certificate;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public interface CertificateBlock {
    default X509Certificate getCertificate() {
        return CertificateUtil.generateCertificate(getCertificateBytes());
    }

    byte[] getCertificateBytes();

    default String printCertificate() {
        return CertificateUtil.printCertificate(this);
    }

    void setCertificate(byte[] bArr);

    default JSONObject toJson() {
        return CertificateUtil.toJson(this);
    }
}
