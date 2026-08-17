package org.bouncycastle.its.operator;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface ETSIDataDecryptor {
    byte[] decrypt(byte[] bArr, byte[] bArr2, byte[] bArr3);

    byte[] getKey();
}
