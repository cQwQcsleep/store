package org.bouncycastle.cms;

import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
interface CMSSecureReadable {
    ASN1Set getAuthAttrSet();

    ASN1ObjectIdentifier getContentType();

    InputStream getInputStream() throws CMSException, IOException;

    boolean hasAdditionalData();

    void setAuthAttrSet(ASN1Set aSN1Set);
}
