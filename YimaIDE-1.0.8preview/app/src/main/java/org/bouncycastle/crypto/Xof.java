package org.bouncycastle.crypto;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface Xof extends ExtendedDigest {
    int doFinal(byte[] bArr, int i, int i2);

    int doOutput(byte[] bArr, int i, int i2);
}
