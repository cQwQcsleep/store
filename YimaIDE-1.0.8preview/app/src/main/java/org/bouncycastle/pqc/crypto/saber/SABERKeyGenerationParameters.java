package org.bouncycastle.pqc.crypto.saber;

import java.security.SecureRandom;
import org.bouncycastle.crypto.KeyGenerationParameters;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class SABERKeyGenerationParameters extends KeyGenerationParameters {
    private SABERParameters params;

    public SABERKeyGenerationParameters(SecureRandom secureRandom, SABERParameters sABERParameters) {
        super(secureRandom, 256);
        this.params = sABERParameters;
    }

    public SABERParameters getParameters() {
        return this.params;
    }
}
