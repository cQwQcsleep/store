package org.bouncycastle.tsp.cms;

import java.net.URI;
import org.bouncycastle.asn1.ASN1Boolean;
import org.bouncycastle.asn1.ASN1IA5String;
import org.bouncycastle.asn1.ASN1UTF8String;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.DERUTF8String;
import org.bouncycastle.asn1.cms.Attributes;
import org.bouncycastle.asn1.cms.MetaData;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.operator.DigestCalculator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class CMSTimeStampedGenerator {
    protected URI dataUri;
    protected MetaData metaData;

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.bouncycastle.cms.CMSException */
    public void initialiseMessageImprintDigestCalculator(DigestCalculator digestCalculator) throws CMSException {
        new MetaDataUtil(this.metaData).initialiseMessageImprintDigestCalculator(digestCalculator);
    }

    public void setDataUri(URI uri) {
        this.dataUri = uri;
    }

    public void setMetaData(boolean z, String str, String str2, Attributes attributes) {
        setMetaData(z, (ASN1UTF8String) (str != null ? new DERUTF8String(str) : null), (ASN1IA5String) (str2 != null ? new DERIA5String(str2) : null), attributes);
    }

    public void setMetaData(boolean z, String str, String str2) {
        setMetaData(z, str, str2, (Attributes) null);
    }

    private void setMetaData(boolean z, ASN1UTF8String aSN1UTF8String, ASN1IA5String aSN1IA5String, Attributes attributes) {
        this.metaData = new MetaData(ASN1Boolean.getInstance(z), aSN1UTF8String, aSN1IA5String, attributes);
    }
}
