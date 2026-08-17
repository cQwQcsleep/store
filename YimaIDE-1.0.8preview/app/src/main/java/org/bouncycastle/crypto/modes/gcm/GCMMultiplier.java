package org.bouncycastle.crypto.modes.gcm;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface GCMMultiplier {
    void init(byte[] bArr);

    void multiplyH(byte[] bArr);
}
