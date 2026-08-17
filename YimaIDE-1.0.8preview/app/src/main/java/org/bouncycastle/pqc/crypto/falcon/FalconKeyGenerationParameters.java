package org.bouncycastle.pqc.crypto.falcon;

import java.security.SecureRandom;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumEngine;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class FalconKeyGenerationParameters extends KeyGenerationParameters {
    private final FalconParameters params;

    public FalconKeyGenerationParameters(SecureRandom secureRandom, FalconParameters falconParameters) {
        super(secureRandom, DilithiumEngine.DilithiumPolyT1PackedBytes);
        this.params = falconParameters;
    }

    public FalconParameters getParameters() {
        return this.params;
    }
}
