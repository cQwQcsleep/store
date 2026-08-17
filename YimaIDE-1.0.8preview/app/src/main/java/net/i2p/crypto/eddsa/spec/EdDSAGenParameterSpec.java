package net.i2p.crypto.eddsa.spec;

import java.security.spec.AlgorithmParameterSpec;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class EdDSAGenParameterSpec implements AlgorithmParameterSpec {
    private final String name;

    public EdDSAGenParameterSpec(String str) {
        this.name = str;
    }

    public String getName() {
        return this.name;
    }
}
