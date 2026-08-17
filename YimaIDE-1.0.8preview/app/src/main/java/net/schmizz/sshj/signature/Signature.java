package net.schmizz.sshj.signature;

import java.security.PrivateKey;
import java.security.PublicKey;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface Signature {
    byte[] encode(byte[] bArr);

    String getSignatureName();

    void initSign(PrivateKey privateKey);

    void initVerify(PublicKey publicKey);

    byte[] sign();

    void update(byte[] bArr);

    void update(byte[] bArr, int i, int i2);

    boolean verify(byte[] bArr);
}
