package org.bouncycastle.pqc.crypto.sphincsplus;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
class SK {
    final byte[] prf;
    final byte[] seed;

    public SK(byte[] bArr, byte[] bArr2) {
        this.seed = bArr;
        this.prf = bArr2;
    }
}
