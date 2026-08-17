package org.bouncycastle.pqc.crypto.ntru;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
class OWCPADecryptResult {
    final int fail;
    final byte[] rm;

    public OWCPADecryptResult(byte[] bArr, int i) {
        this.rm = bArr;
        this.fail = i;
    }
}
