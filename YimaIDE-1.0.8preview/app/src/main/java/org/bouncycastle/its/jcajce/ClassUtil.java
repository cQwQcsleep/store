package org.bouncycastle.its.jcajce;

import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.spec.GCMParameterSpec;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
class ClassUtil {
    public static AlgorithmParameterSpec getGCMSpec(byte[] bArr, int i) {
        return new GCMParameterSpec(i, bArr);
    }
}
