package org.bouncycastle.asn1;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface InMemoryRepresentable {
    ASN1Primitive getLoadedObject() throws IOException;
}
