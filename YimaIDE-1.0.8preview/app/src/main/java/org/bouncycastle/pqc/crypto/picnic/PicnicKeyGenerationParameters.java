package org.bouncycastle.pqc.crypto.picnic;

import java.security.SecureRandom;
import org.bouncycastle.crypto.KeyGenerationParameters;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class PicnicKeyGenerationParameters extends KeyGenerationParameters {
    private final PicnicParameters parameters;

    public PicnicKeyGenerationParameters(SecureRandom secureRandom, PicnicParameters picnicParameters) {
        super(secureRandom, -1);
        this.parameters = picnicParameters;
    }

    public PicnicParameters getParameters() {
        return this.parameters;
    }
}
