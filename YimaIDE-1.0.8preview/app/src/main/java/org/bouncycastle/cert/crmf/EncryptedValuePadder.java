package org.bouncycastle.cert.crmf;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface EncryptedValuePadder {
    byte[] getPaddedData(byte[] bArr);

    byte[] getUnpaddedData(byte[] bArr);
}
