package com.android.apksig.internal.util;

import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class GuaranteedEncodedFormX509Certificate extends DelegatingX509Certificate {
    private static final long serialVersionUID = 1;
    private final byte[] mEncodedForm;
    private int mHash;

    public GuaranteedEncodedFormX509Certificate(X509Certificate x509Certificate, byte[] bArr) {
        super(x509Certificate);
        this.mHash = -1;
        this.mEncodedForm = bArr != null ? (byte[]) bArr.clone() : null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof X509Certificate)) {
            return false;
        }
        try {
            return Arrays.equals(getEncoded(), ((X509Certificate) obj).getEncoded());
        } catch (CertificateEncodingException unused) {
            return false;
        }
    }

    public byte[] getEncoded() throws CertificateEncodingException {
        byte[] bArr = this.mEncodedForm;
        if (bArr != null) {
            return (byte[]) bArr.clone();
        }
        return null;
    }

    public int hashCode() {
        if (this.mHash == -1) {
            try {
                this.mHash = Arrays.hashCode(getEncoded());
            } catch (CertificateEncodingException unused) {
                this.mHash = 0;
            }
        }
        return this.mHash;
    }
}
