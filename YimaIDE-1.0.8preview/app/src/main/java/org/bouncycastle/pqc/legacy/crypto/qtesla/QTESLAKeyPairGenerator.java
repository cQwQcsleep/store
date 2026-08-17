package org.bouncycastle.pqc.legacy.crypto.qtesla;

import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class QTESLAKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private SecureRandom secureRandom;
    private int securityCategory;

    private byte[] allocatePrivate(int i) {
        return new byte[QTESLASecurityCategory.getPrivateSize(i)];
    }

    private byte[] allocatePublic(int i) {
        return new byte[QTESLASecurityCategory.getPublicSize(i)];
    }

    public AsymmetricCipherKeyPair generateKeyPair() {
        byte[] bArrAllocatePrivate = allocatePrivate(this.securityCategory);
        byte[] bArrAllocatePublic = allocatePublic(this.securityCategory);
        int i = this.securityCategory;
        if (i == 5) {
            QTesla1p.generateKeyPair(bArrAllocatePublic, bArrAllocatePrivate, this.secureRandom);
        } else {
            if (i != 6) {
                jt6.a("unknown security category: ", this.securityCategory);
                return null;
            }
            QTesla3p.generateKeyPair(bArrAllocatePublic, bArrAllocatePrivate, this.secureRandom);
        }
        return new AsymmetricCipherKeyPair(new QTESLAPublicKeyParameters(this.securityCategory, bArrAllocatePublic), new QTESLAPrivateKeyParameters(this.securityCategory, bArrAllocatePrivate));
    }

    public void init(KeyGenerationParameters keyGenerationParameters) {
        QTESLAKeyGenerationParameters qTESLAKeyGenerationParameters = (QTESLAKeyGenerationParameters) keyGenerationParameters;
        this.secureRandom = qTESLAKeyGenerationParameters.getRandom();
        this.securityCategory = qTESLAKeyGenerationParameters.getSecurityCategory();
    }
}
