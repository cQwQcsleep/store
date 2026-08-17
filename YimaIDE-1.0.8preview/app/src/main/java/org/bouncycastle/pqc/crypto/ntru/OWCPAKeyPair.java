package org.bouncycastle.pqc.crypto.ntru;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
class OWCPAKeyPair {
    public final byte[] privateKey;
    public final byte[] publicKey;

    public OWCPAKeyPair(byte[] bArr, byte[] bArr2) {
        this.publicKey = bArr;
        this.privateKey = bArr2;
    }
}
