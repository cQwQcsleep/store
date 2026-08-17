package org.bouncycastle.tsp.cms;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1String;
import org.bouncycastle.asn1.cms.Attributes;
import org.bouncycastle.asn1.cms.MetaData;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.operator.DigestCalculator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class MetaDataUtil {
    private final MetaData metaData;

    public MetaDataUtil(MetaData metaData) {
        this.metaData = metaData;
    }

    private String convertString(ASN1String aSN1String) {
        if (aSN1String != null) {
            return aSN1String.toString();
        }
        return null;
    }

    public String getFileName() {
        MetaData metaData = this.metaData;
        if (metaData != null) {
            return convertString(metaData.getFileNameUTF8());
        }
        return null;
    }

    public String getMediaType() {
        MetaData metaData = this.metaData;
        if (metaData != null) {
            return convertString(metaData.getMediaTypeIA5());
        }
        return null;
    }

    public Attributes getOtherMetaData() {
        MetaData metaData = this.metaData;
        if (metaData != null) {
            return metaData.getOtherMetaData();
        }
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.bouncycastle.cms.CMSException */
    public void initialiseMessageImprintDigestCalculator(DigestCalculator digestCalculator) throws CMSException {
        MetaData metaData = this.metaData;
        if (metaData == null || !metaData.isHashProtected()) {
            return;
        }
        try {
            digestCalculator.getOutputStream().write(this.metaData.getEncoded("DER"));
        } catch (IOException e) {
            throw new CMSException("unable to initialise calculator from metaData: " + e.getMessage(), e);
        }
    }
}
