package org.bouncycastle.pqc.crypto.sphincs;

import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.pqc.crypto.newhope.NewHope;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class SPHINCS256KeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private SecureRandom random;
    private Digest treeDigest;

    public AsymmetricCipherKeyPair generateKeyPair() {
        Tree.leafaddr leafaddrVar = new Tree.leafaddr();
        byte[] bArr = new byte[1088];
        this.random.nextBytes(bArr);
        byte[] bArr2 = new byte[1056];
        System.arraycopy(bArr, 32, bArr2, 0, NewHope.POLY_SIZE);
        leafaddrVar.level = 11;
        leafaddrVar.subtree = 0L;
        leafaddrVar.subleaf = 0L;
        Tree.treehash(new HashFunctions(this.treeDigest), bArr2, NewHope.POLY_SIZE, 5, bArr, leafaddrVar, bArr2, 0);
        return new AsymmetricCipherKeyPair(new SPHINCSPublicKeyParameters(bArr2, this.treeDigest.getAlgorithmName()), new SPHINCSPrivateKeyParameters(bArr, this.treeDigest.getAlgorithmName()));
    }

    public void init(KeyGenerationParameters keyGenerationParameters) {
        this.random = keyGenerationParameters.getRandom();
        this.treeDigest = ((SPHINCS256KeyGenerationParameters) keyGenerationParameters).getTreeDigest();
    }
}
