package org.bouncycastle.openssl;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
interface PEMKeyPairParser {
    PEMKeyPair parse(byte[] bArr) throws IOException;
}
