package org.bouncycastle.crypto.modes.gcm;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface GCMExponentiator {
    void exponentiateX(long j, byte[] bArr);

    void init(byte[] bArr);
}
