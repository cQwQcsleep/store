package org.bouncycastle.pqc.crypto.ntru;

import org.bouncycastle.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class NTRUPublicKeyParameters extends NTRUKeyParameters {
    final byte[] publicKey;

    public NTRUPublicKeyParameters(NTRUParameters nTRUParameters, byte[] bArr) {
        super(false, nTRUParameters);
        this.publicKey = Arrays.clone(bArr);
    }

    public byte[] getEncoded() {
        return getPublicKey();
    }

    public byte[] getPublicKey() {
        return Arrays.clone(this.publicKey);
    }
}
