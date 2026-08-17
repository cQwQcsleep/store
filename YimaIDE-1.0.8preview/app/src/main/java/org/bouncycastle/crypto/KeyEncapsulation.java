package org.bouncycastle.crypto;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface KeyEncapsulation {
    CipherParameters decrypt(byte[] bArr, int i, int i2, int i3);

    CipherParameters encrypt(byte[] bArr, int i, int i2);

    void init(CipherParameters cipherParameters);
}
