package org.bouncycastle.jcajce.spec;

import java.security.PrivateKey;
import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class KEMExtractSpec implements AlgorithmParameterSpec {
    private final byte[] encapsulation;
    private final String keyAlgorithmName;
    private final int keySizeInBits;
    private final PrivateKey privateKey;

    public KEMExtractSpec(PrivateKey privateKey, byte[] bArr, String str, int i) {
        this.privateKey = privateKey;
        this.encapsulation = Arrays.clone(bArr);
        this.keyAlgorithmName = str;
        this.keySizeInBits = i;
    }

    public byte[] getEncapsulation() {
        return Arrays.clone(this.encapsulation);
    }

    public String getKeyAlgorithmName() {
        return this.keyAlgorithmName;
    }

    public int getKeySize() {
        return this.keySizeInBits;
    }

    public PrivateKey getPrivateKey() {
        return this.privateKey;
    }

    public KEMExtractSpec(PrivateKey privateKey, byte[] bArr, String str) {
        this(privateKey, bArr, str, 256);
    }
}
