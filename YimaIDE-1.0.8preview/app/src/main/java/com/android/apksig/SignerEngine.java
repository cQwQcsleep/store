package com.android.apksig;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public interface SignerEngine {
    byte[] sign(byte[] bArr) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException, InvalidAlgorithmParameterException;
}
