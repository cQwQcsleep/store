package org.bouncycastle.crypto.prng;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface EntropySource {
    int entropySize();

    byte[] getEntropy();

    boolean isPredictionResistant();
}
