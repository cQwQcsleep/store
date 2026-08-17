package org.bouncycastle.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class SM2ParameterSpec implements AlgorithmParameterSpec {
    private byte[] id;

    public SM2ParameterSpec(byte[] bArr) {
        if (bArr != null) {
            this.id = Arrays.clone(bArr);
        } else {
            x0e.a("id string cannot be null");
            throw null;
        }
    }

    public byte[] getID() {
        return Arrays.clone(this.id);
    }
}
