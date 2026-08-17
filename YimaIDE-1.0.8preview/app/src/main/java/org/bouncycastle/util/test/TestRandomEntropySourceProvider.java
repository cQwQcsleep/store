package org.bouncycastle.util.test;

import java.security.SecureRandom;
import org.bouncycastle.crypto.prng.EntropySource;
import org.bouncycastle.crypto.prng.EntropySourceProvider;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class TestRandomEntropySourceProvider implements EntropySourceProvider {
    private final boolean _predictionResistant;
    private final SecureRandom _sr = new SecureRandom();

    public TestRandomEntropySourceProvider(boolean z) {
        this._predictionResistant = z;
    }

    public EntropySource get(final int i) {
        return new EntropySource() { // from class: org.bouncycastle.util.test.TestRandomEntropySourceProvider.1
            public int entropySize() {
                return i;
            }

            public byte[] getEntropy() {
                byte[] bArr = new byte[(i + 7) / 8];
                TestRandomEntropySourceProvider.this._sr.nextBytes(bArr);
                return bArr;
            }

            public boolean isPredictionResistant() {
                return TestRandomEntropySourceProvider.this._predictionResistant;
            }
        };
    }
}
