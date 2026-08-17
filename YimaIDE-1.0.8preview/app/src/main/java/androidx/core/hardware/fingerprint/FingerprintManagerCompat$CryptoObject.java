package androidx.core.hardware.fingerprint;

import java.security.Signature;
import javax.crypto.Cipher;
import javax.crypto.Mac;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class FingerprintManagerCompat$CryptoObject {
    private final Cipher mCipher;
    private final Mac mMac;
    private final Signature mSignature;

    public FingerprintManagerCompat$CryptoObject(Signature signature) {
        this.mSignature = signature;
        this.mCipher = null;
        this.mMac = null;
    }

    public Cipher getCipher() {
        return this.mCipher;
    }

    public Mac getMac() {
        return this.mMac;
    }

    public Signature getSignature() {
        return this.mSignature;
    }

    public FingerprintManagerCompat$CryptoObject(Cipher cipher) {
        this.mCipher = cipher;
        this.mSignature = null;
        this.mMac = null;
    }

    public FingerprintManagerCompat$CryptoObject(Mac mac) {
        this.mMac = mac;
        this.mCipher = null;
        this.mSignature = null;
    }
}
