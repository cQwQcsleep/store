package com.hierynomus.sshj.key;

import com.hierynomus.sshj.signature.SignatureEdDSA;
import net.schmizz.sshj.common.KeyType;
import net.schmizz.sshj.signature.SignatureDSA;
import net.schmizz.sshj.signature.SignatureECDSA;
import net.schmizz.sshj.signature.SignatureRSA;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
public class KeyAlgorithms {
    public static Factory ECDSASHANistp256() {
        KeyType keyType = KeyType.ECDSA256;
        return new Factory(keyType.toString(), new SignatureECDSA.Factory256(), keyType);
    }

    public static Factory ECDSASHANistp256CertV01() {
        KeyType keyType = KeyType.ECDSA256_CERT;
        return new Factory(keyType.toString(), new SignatureECDSA.Factory256(), keyType);
    }

    public static Factory ECDSASHANistp384() {
        KeyType keyType = KeyType.ECDSA384;
        return new Factory(keyType.toString(), new SignatureECDSA.Factory384(), keyType);
    }

    public static Factory ECDSASHANistp384CertV01() {
        KeyType keyType = KeyType.ECDSA384_CERT;
        return new Factory(keyType.toString(), new SignatureECDSA.Factory384(), keyType);
    }

    public static Factory ECDSASHANistp521() {
        KeyType keyType = KeyType.ECDSA521;
        return new Factory(keyType.toString(), new SignatureECDSA.Factory521(), keyType);
    }

    public static Factory ECDSASHANistp521CertV01() {
        KeyType keyType = KeyType.ECDSA521_CERT;
        return new Factory(keyType.toString(), new SignatureECDSA.Factory521(), keyType);
    }

    public static Factory EdDSA25519() {
        KeyType keyType = KeyType.ED25519;
        return new Factory(keyType.toString(), new SignatureEdDSA.Factory(), keyType);
    }

    public static Factory EdDSA25519CertV01() {
        KeyType keyType = KeyType.ED25519_CERT;
        return new Factory(keyType.toString(), new SignatureEdDSA.Factory(), keyType);
    }

    public static Factory RSASHA256() {
        return new Factory("rsa-sha2-256", new SignatureRSA.FactoryRSASHA256(), KeyType.RSA);
    }

    public static Factory RSASHA512() {
        return new Factory("rsa-sha2-512", new SignatureRSA.FactoryRSASHA512(), KeyType.RSA);
    }

    public static Factory SSHDSA() {
        KeyType keyType = KeyType.DSA;
        return new Factory(keyType.toString(), new SignatureDSA.Factory(), keyType);
    }

    public static Factory SSHDSSCertV01() {
        KeyType keyType = KeyType.DSA_CERT;
        return new Factory(keyType.toString(), new SignatureDSA.Factory(), keyType);
    }

    public static Factory SSHRSA() {
        return new Factory("ssh-rsa", new SignatureRSA.FactorySSHRSA(), KeyType.RSA);
    }

    public static Factory SSHRSACertV01() {
        return new Factory("ssh-rsa-cert-v01@openssh.com", new SignatureRSA.FactoryCERT(), KeyType.RSA_CERT);
    }
}
