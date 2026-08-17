package com.reandroid.archive.block;

import com.reandroid.json.JSONObject;
import com.reandroid.utils.HexUtil;
import java.io.ByteArrayInputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Date;
import javax.security.auth.x500.X500Principal;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class CertificateUtil {
    private static CertificateFactory sCertFactory;

    public static X509Certificate generateCertificate(byte[] bArr) {
        CertificateFactory certFactory = getCertFactory();
        if (certFactory == null) {
            return null;
        }
        try {
            return (X509Certificate) certFactory.generateCertificate(new ByteArrayInputStream(bArr));
        } catch (CertificateException unused) {
            return null;
        }
    }

    private static CertificateFactory getCertFactory() {
        if (sCertFactory == null) {
            try {
                sCertFactory = CertificateFactory.getInstance("X.509");
            } catch (CertificateException unused) {
            }
        }
        return sCertFactory;
    }

    public static String printCertificate(CertificateBlock certificateBlock) {
        X509Certificate certificate = certificateBlock.getCertificate();
        if (certificate == null) {
            return "Failed to get certificate bytes = " + certificateBlock.getCertificateBytes().length;
        }
        StringBuilder sb = new StringBuilder("Subject: ");
        sb.append(certificate.getSubjectX500Principal());
        sb.append("\nIssuer: ");
        sb.append(certificate.getIssuerX500Principal());
        Date notBefore = certificate.getNotBefore();
        Date notAfter = certificate.getNotAfter();
        sb.append("\nValidity FROM: ");
        sb.append(notBefore);
        sb.append(", TO: ");
        sb.append(notAfter);
        sb.append(", PERIOD: ");
        sb.append(notAfter.getTime() - notBefore.getTime());
        sb.append("\nSerial: ");
        sb.append(HexUtil.toHex(certificate.getSerialNumber().longValue(), 1));
        sb.append("\nOID: ");
        sb.append(certificate.getSigAlgOID());
        return sb.toString();
    }

    public static JSONObject toJson(CertificateBlock certificateBlock) {
        JSONObject jSONObject = new JSONObject();
        X509Certificate certificate = certificateBlock.getCertificate();
        if (certificate == null) {
            jSONObject.put("error", "Failed to get certificate bytes = " + certificateBlock.getCertificateBytes().length);
            return jSONObject;
        }
        jSONObject.put("subject", toJson(certificate.getSubjectX500Principal()));
        jSONObject.put("issuer", toJson(certificate.getIssuerX500Principal()));
        jSONObject.put("not_before", certificate.getNotBefore().getTime());
        jSONObject.put("not_after", certificate.getNotAfter().getTime());
        jSONObject.put("serial", certificate.getSerialNumber().longValue());
        jSONObject.put("oid", certificate.getSigAlgOID());
        return jSONObject;
    }

    public static JSONObject toJson(X500Principal x500Principal) {
        JSONObject jSONObject = new JSONObject();
        if (x500Principal == null) {
            return null;
        }
        jSONObject.put("name", x500Principal.getName());
        return jSONObject;
    }
}
