package org.bouncycastle.openssl;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface PEMEncryptor {
    byte[] encrypt(byte[] bArr) throws PEMException;

    String getAlgorithm();

    byte[] getIV();
}
