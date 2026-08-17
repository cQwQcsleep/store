package org.bouncycastle.crypto;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface RawAgreement {
    void calculateAgreement(CipherParameters cipherParameters, byte[] bArr, int i);

    int getAgreementSize();

    void init(CipherParameters cipherParameters);
}
