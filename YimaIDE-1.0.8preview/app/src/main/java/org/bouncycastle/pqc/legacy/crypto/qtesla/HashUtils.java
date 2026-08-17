package org.bouncycastle.pqc.legacy.crypto.qtesla;

import io.github.rosemoe.sora.widget.CodeEditor;
import org.bouncycastle.crypto.digests.CSHAKEDigest;
import org.bouncycastle.crypto.digests.SHAKEDigest;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
class HashUtils {
    static final int SECURE_HASH_ALGORITHM_KECCAK_128_RATE = 168;
    static final int SECURE_HASH_ALGORITHM_KECCAK_256_RATE = 136;

    public static void customizableSecureHashAlgorithmKECCAK128Simple(byte[] bArr, int i, int i2, short s, byte[] bArr2, int i3, int i4) {
        CSHAKEDigest cSHAKEDigest = new CSHAKEDigest(CodeEditor.FLAG_DRAW_SOFT_WRAP, (byte[]) null, new byte[]{(byte) s, (byte) (s >> 8)});
        cSHAKEDigest.update(bArr2, i3, i4);
        cSHAKEDigest.doFinal(bArr, i, i2);
    }

    public static void customizableSecureHashAlgorithmKECCAK256Simple(byte[] bArr, int i, int i2, short s, byte[] bArr2, int i3, int i4) {
        CSHAKEDigest cSHAKEDigest = new CSHAKEDigest(256, (byte[]) null, new byte[]{(byte) s, (byte) (s >> 8)});
        cSHAKEDigest.update(bArr2, i3, i4);
        cSHAKEDigest.doFinal(bArr, i, i2);
    }

    public static void secureHashAlgorithmKECCAK128(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
        SHAKEDigest sHAKEDigest = new SHAKEDigest(CodeEditor.FLAG_DRAW_SOFT_WRAP);
        sHAKEDigest.update(bArr2, i3, i4);
        sHAKEDigest.doFinal(bArr, i, i2);
    }

    public static void secureHashAlgorithmKECCAK256(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
        SHAKEDigest sHAKEDigest = new SHAKEDigest(256);
        sHAKEDigest.update(bArr2, i3, i4);
        sHAKEDigest.doFinal(bArr, i, i2);
    }
}
