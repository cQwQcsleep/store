package com.android.apksig;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.AlgorithmParameterSpec;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class JcaSignerEngine implements SignerEngine {
    private final AlgorithmParameterSpec mAlgorithmParameterSpec;
    private final PrivateKey mPrivateKey;
    private final String mSignatureAlgorithm;

    public JcaSignerEngine(PrivateKey privateKey, String str, AlgorithmParameterSpec algorithmParameterSpec) {
        if (privateKey == null) {
            w01.a("privateKey cannot be null");
            throw null;
        }
        if (str == null) {
            w01.a("signatureAlgorithm cannot be null");
            throw null;
        }
        this.mPrivateKey = privateKey;
        this.mSignatureAlgorithm = str;
        this.mAlgorithmParameterSpec = algorithmParameterSpec;
    }

    @Override // com.android.apksig.SignerEngine
    public byte[] sign(byte[] bArr) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException, InvalidAlgorithmParameterException {
        Signature signature = Signature.getInstance(this.mSignatureAlgorithm);
        signature.initSign(this.mPrivateKey);
        AlgorithmParameterSpec algorithmParameterSpec = this.mAlgorithmParameterSpec;
        if (algorithmParameterSpec != null) {
            signature.setParameter(algorithmParameterSpec);
        }
        signature.update(bArr);
        return signature.sign();
    }
}
