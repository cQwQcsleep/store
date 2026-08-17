package org.bouncycastle.its.operator;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface ETSIDataEncryptor {
    byte[] encrypt(byte[] bArr);

    byte[] getKey();

    byte[] getNonce();
}
