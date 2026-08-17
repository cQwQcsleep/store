package org.bouncycastle.pqc.crypto.crystals.kyber;

import io.github.rosemoe.sora.widget.CodeEditor;
import java.security.SecureRandom;
import org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumEngine;
import org.bouncycastle.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
class KyberEngine {
    private static final int KyberEta2 = 2;
    private static final int KyberIndCpaMsgBytes = 32;
    public static final int KyberN = 256;
    public static final int KyberPolyBytes = 384;
    public static final int KyberQ = 3329;
    public static final int KyberQinv = 62209;
    private static final int KyberSharedSecretBytes = 32;
    public static final int KyberSymBytes = 32;
    private final int CryptoBytes;
    private final int CryptoCipherTextBytes;
    private final int CryptoPublicKeyBytes;
    private final int CryptoSecretKeyBytes;
    private final int KyberCipherTextBytes;
    private final int KyberEta1;
    private final int KyberIndCpaBytes;
    private final int KyberIndCpaPublicKeyBytes;
    private final int KyberIndCpaSecretKeyBytes;
    private final int KyberK;
    private final int KyberPolyCompressedBytes;
    private final int KyberPolyVecBytes;
    private final int KyberPolyVecCompressedBytes;
    private final int KyberPublicKeyBytes;
    private final int KyberSecretKeyBytes;
    private KyberIndCpa indCpa;
    private SecureRandom random;
    private final int sessionKeyLength;
    private final Symmetric symmetric;

    /* JADX WARN: Code duplicated, block: B:16:0x0056  */
    /* JADX WARN: Code duplicated, block: B:18:0x005e  */
    public KyberEngine(int i, boolean z) {
        int i2;
        Symmetric shakeSymmetric;
        this.KyberK = i;
        if (i != 2) {
            if (i == 3) {
                this.KyberEta1 = 2;
            } else {
                if (i != 4) {
                    ty8.a("K: ", i, " is not supported for Crystals Kyber");
                    throw null;
                }
                this.KyberEta1 = 2;
                this.KyberPolyCompressedBytes = 160;
                i2 = i * 352;
            }
            this.KyberPolyVecCompressedBytes = i2;
            this.sessionKeyLength = 32;
            int i3 = i * KyberPolyBytes;
            this.KyberPolyVecBytes = i3;
            int i4 = i3 + 32;
            this.KyberIndCpaPublicKeyBytes = i4;
            this.KyberIndCpaSecretKeyBytes = i3;
            int i5 = this.KyberPolyVecCompressedBytes + this.KyberPolyCompressedBytes;
            this.KyberIndCpaBytes = i5;
            this.KyberPublicKeyBytes = i4;
            int i6 = i3 + i4 + 64;
            this.KyberSecretKeyBytes = i6;
            this.KyberCipherTextBytes = i5;
            this.CryptoBytes = 32;
            this.CryptoSecretKeyBytes = i6;
            this.CryptoPublicKeyBytes = i4;
            this.CryptoCipherTextBytes = i5;
            if (z) {
                shakeSymmetric = new Symmetric.AesSymmetric();
            } else {
                shakeSymmetric = new Symmetric.ShakeSymmetric();
            }
            this.symmetric = shakeSymmetric;
            this.indCpa = new KyberIndCpa(this);
        }
        this.KyberEta1 = 3;
        this.KyberPolyCompressedBytes = CodeEditor.FLAG_DRAW_SOFT_WRAP;
        i2 = i * DilithiumEngine.DilithiumPolyT1PackedBytes;
        this.KyberPolyVecCompressedBytes = i2;
        this.sessionKeyLength = 32;
        int i7 = i * KyberPolyBytes;
        this.KyberPolyVecBytes = i7;
        int i8 = i7 + 32;
        this.KyberIndCpaPublicKeyBytes = i8;
        this.KyberIndCpaSecretKeyBytes = i7;
        int i9 = this.KyberPolyVecCompressedBytes + this.KyberPolyCompressedBytes;
        this.KyberIndCpaBytes = i9;
        this.KyberPublicKeyBytes = i8;
        int i10 = i7 + i8 + 64;
        this.KyberSecretKeyBytes = i10;
        this.KyberCipherTextBytes = i9;
        this.CryptoBytes = 32;
        this.CryptoSecretKeyBytes = i10;
        this.CryptoPublicKeyBytes = i8;
        this.CryptoCipherTextBytes = i9;
        if (z) {
            shakeSymmetric = new Symmetric.AesSymmetric();
        } else {
            shakeSymmetric = new Symmetric.ShakeSymmetric();
        }
        this.symmetric = shakeSymmetric;
        this.indCpa = new KyberIndCpa(this);
    }

    private void cmov(byte[] bArr, byte[] bArr2, int i, boolean z) {
        if (z) {
            System.arraycopy(bArr2, 0, bArr, 0, i);
        } else {
            System.arraycopy(bArr, 0, bArr, 0, i);
        }
    }

    public static int getKyberEta2() {
        return 2;
    }

    public static int getKyberIndCpaMsgBytes() {
        return 32;
    }

    public byte[][] generateKemKeyPair() {
        byte[][] bArrGenerateKeyPair = this.indCpa.generateKeyPair();
        int i = this.KyberIndCpaSecretKeyBytes;
        byte[] bArr = new byte[i];
        System.arraycopy(bArrGenerateKeyPair[1], 0, bArr, 0, i);
        byte[] bArr2 = new byte[32];
        this.symmetric.hash_h(bArr2, bArrGenerateKeyPair[0], 0);
        byte[] bArr3 = new byte[32];
        this.random.nextBytes(bArr3);
        int i2 = this.KyberIndCpaPublicKeyBytes;
        byte[] bArr4 = new byte[i2];
        System.arraycopy(bArrGenerateKeyPair[0], 0, bArr4, 0, i2);
        int i3 = i2 - 32;
        return new byte[][]{Arrays.copyOfRange(bArr4, 0, i3), Arrays.copyOfRange(bArr4, i3, i2), bArr, bArr2, bArr3};
    }

    public int getCryptoBytes() {
        return this.CryptoBytes;
    }

    public int getCryptoCipherTextBytes() {
        return this.CryptoCipherTextBytes;
    }

    public int getCryptoPublicKeyBytes() {
        return this.CryptoPublicKeyBytes;
    }

    public int getCryptoSecretKeyBytes() {
        return this.CryptoSecretKeyBytes;
    }

    public int getKyberCipherTextBytes() {
        return this.KyberCipherTextBytes;
    }

    public int getKyberEta1() {
        return this.KyberEta1;
    }

    public int getKyberIndCpaBytes() {
        return this.KyberIndCpaBytes;
    }

    public int getKyberIndCpaPublicKeyBytes() {
        return this.KyberIndCpaPublicKeyBytes;
    }

    public int getKyberIndCpaSecretKeyBytes() {
        return this.KyberIndCpaSecretKeyBytes;
    }

    public int getKyberK() {
        return this.KyberK;
    }

    public int getKyberPolyCompressedBytes() {
        return this.KyberPolyCompressedBytes;
    }

    public int getKyberPolyVecBytes() {
        return this.KyberPolyVecBytes;
    }

    public int getKyberPolyVecCompressedBytes() {
        return this.KyberPolyVecCompressedBytes;
    }

    public int getKyberPublicKeyBytes() {
        return this.KyberPublicKeyBytes;
    }

    public int getKyberSecretKeyBytes() {
        return this.KyberSecretKeyBytes;
    }

    public void getRandomBytes(byte[] bArr) {
        this.random.nextBytes(bArr);
    }

    public Symmetric getSymmetric() {
        return this.symmetric;
    }

    public void init(SecureRandom secureRandom) {
        this.random = secureRandom;
    }

    public byte[] kemDecrypt(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[64];
        byte[] bArr4 = new byte[64];
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr2, this.KyberIndCpaSecretKeyBytes, bArr2.length);
        System.arraycopy(this.indCpa.decrypt(bArr, bArr2), 0, bArr3, 0, 32);
        System.arraycopy(bArr2, this.KyberSecretKeyBytes - 64, bArr3, 32, 32);
        this.symmetric.hash_g(bArr4, bArr3);
        boolean z = !Arrays.constantTimeAreEqual(bArr, this.indCpa.encrypt(Arrays.copyOfRange(bArr3, 0, 32), bArrCopyOfRange, Arrays.copyOfRange(bArr4, 32, 64)));
        this.symmetric.hash_h(bArr4, bArr, 32);
        int i = this.KyberSecretKeyBytes;
        cmov(bArr4, Arrays.copyOfRange(bArr2, i - 32, i), 32, z);
        return Arrays.copyOfRange(bArr4, 0, this.sessionKeyLength);
    }

    public byte[][] kemEncrypt(byte[] bArr) {
        String str;
        if (bArr.length == this.KyberIndCpaPublicKeyBytes) {
            PolyVec polyVec = new PolyVec(this);
            if (Arrays.areEqual(this.indCpa.packPublicKey(polyVec, this.indCpa.unpackPublicKey(polyVec, bArr)), bArr)) {
                byte[] bArr2 = new byte[64];
                byte[] bArr3 = new byte[64];
                byte[] bArr4 = new byte[32];
                this.random.nextBytes(bArr4);
                System.arraycopy(bArr4, 0, bArr2, 0, 32);
                this.symmetric.hash_h(bArr2, bArr, 32);
                this.symmetric.hash_g(bArr3, bArr2);
                byte[] bArrEncrypt = this.indCpa.encrypt(Arrays.copyOfRange(bArr2, 0, 32), bArr, Arrays.copyOfRange(bArr3, 32, 64));
                int i = this.sessionKeyLength;
                byte[] bArr5 = new byte[i];
                System.arraycopy(bArr3, 0, bArr5, 0, i);
                return new byte[][]{bArr5, bArrEncrypt};
            }
            str = "Input validation: Modulus check failed for ml-kem encapsulation";
        } else {
            str = "Input validation Error: Type check failed for ml-kem encapsulation";
        }
        w01.a(str);
        return null;
    }
}
