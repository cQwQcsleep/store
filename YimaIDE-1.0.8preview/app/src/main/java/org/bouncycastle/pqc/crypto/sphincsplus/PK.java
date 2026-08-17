package org.bouncycastle.pqc.crypto.sphincsplus;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
class PK {
    final byte[] root;
    final byte[] seed;

    public PK(byte[] bArr, byte[] bArr2) {
        this.seed = bArr;
        this.root = bArr2;
    }
}
