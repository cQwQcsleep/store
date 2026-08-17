package org.bouncycastle.jce.spec;

import java.security.spec.KeySpec;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class ECKeySpec implements KeySpec {
    private ECParameterSpec spec;

    public ECKeySpec(ECParameterSpec eCParameterSpec) {
        this.spec = eCParameterSpec;
    }

    public ECParameterSpec getParams() {
        return this.spec;
    }
}
