package org.bouncycastle.crypto;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface SignerWithRecovery extends Signer {
    byte[] getRecoveredMessage();

    boolean hasFullMessage();

    void updateWithRecoveredMessage(byte[] bArr) throws InvalidCipherTextException;
}
