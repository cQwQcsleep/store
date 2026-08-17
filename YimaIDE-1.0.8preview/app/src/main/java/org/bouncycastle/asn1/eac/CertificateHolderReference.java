package org.bouncycastle.asn1.eac;

import defpackage.mx5;
import java.io.UnsupportedEncodingException;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class CertificateHolderReference {
    private static final String ReferenceEncoding = "ISO-8859-1";
    private String countryCode;
    private String holderMnemonic;
    private String sequenceNumber;

    public CertificateHolderReference(byte[] bArr) {
        try {
            String str = new String(bArr, "ISO-8859-1");
            this.countryCode = str.substring(0, 2);
            this.holderMnemonic = str.substring(2, str.length() - 5);
            this.sequenceNumber = str.substring(str.length() - 5);
        } catch (UnsupportedEncodingException e) {
            mx5.a(e);
            throw null;
        }
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public byte[] getEncoded() {
        try {
            return (this.countryCode + this.holderMnemonic + this.sequenceNumber).getBytes("ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            mx5.a(e);
            return null;
        }
    }

    public String getHolderMnemonic() {
        return this.holderMnemonic;
    }

    public String getSequenceNumber() {
        return this.sequenceNumber;
    }

    public CertificateHolderReference(String str, String str2, String str3) {
        this.countryCode = str;
        this.holderMnemonic = str2;
        this.sequenceNumber = str3;
    }
}
