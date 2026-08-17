package org.bouncycastle.crypto.prng.drbg;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface SP80090DRBG {
    int generate(byte[] bArr, byte[] bArr2, boolean z);

    int getBlockSize();

    void reseed(byte[] bArr);
}
