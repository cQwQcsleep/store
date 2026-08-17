package org.bouncycastle.asn1;

import java.io.InputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface ASN1OctetStringParser extends ASN1Encodable, InMemoryRepresentable {
    InputStream getOctetStream();
}
