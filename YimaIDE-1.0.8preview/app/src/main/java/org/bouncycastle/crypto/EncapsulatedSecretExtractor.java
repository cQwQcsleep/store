package org.bouncycastle.crypto;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface EncapsulatedSecretExtractor {
    byte[] extractSecret(byte[] bArr);

    int getEncapsulationLength();
}
