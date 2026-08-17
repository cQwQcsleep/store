package org.bouncycastle.crypto;

import java.math.BigInteger;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface BasicAgreement {
    BigInteger calculateAgreement(CipherParameters cipherParameters);

    int getFieldSize();

    void init(CipherParameters cipherParameters);
}
