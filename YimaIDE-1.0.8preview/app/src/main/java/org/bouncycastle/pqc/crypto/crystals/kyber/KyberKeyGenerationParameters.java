package org.bouncycastle.pqc.crypto.crystals.kyber;

import java.security.SecureRandom;
import org.bouncycastle.crypto.KeyGenerationParameters;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class KyberKeyGenerationParameters extends KeyGenerationParameters {
    private final KyberParameters params;

    public KyberKeyGenerationParameters(SecureRandom secureRandom, KyberParameters kyberParameters) {
        super(secureRandom, 256);
        this.params = kyberParameters;
    }

    public KyberParameters getParameters() {
        return this.params;
    }
}
