package org.bouncycastle.pqc.crypto.ntru;

import org.bouncycastle.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class NTRUPrivateKeyParameters extends NTRUKeyParameters {
    final byte[] privateKey;

    public NTRUPrivateKeyParameters(NTRUParameters nTRUParameters, byte[] bArr) {
        super(true, nTRUParameters);
        this.privateKey = Arrays.clone(bArr);
    }

    public byte[] getEncoded() {
        return getPrivateKey();
    }

    public byte[] getPrivateKey() {
        return Arrays.clone(this.privateKey);
    }
}
