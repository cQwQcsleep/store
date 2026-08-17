package com.reandroid.archive.block;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class CertificateBlockV2 extends LengthPrefixedBytes implements CertificateBlock {
    public CertificateBlockV2() {
        super(false);
    }

    @Override // com.reandroid.archive.block.CertificateBlock
    public byte[] getCertificateBytes() {
        return (byte[]) getByteArray().toArray().clone();
    }

    @Override // com.reandroid.archive.block.CertificateBlock
    public void setCertificate(byte[] bArr) {
        getByteArray().set(bArr);
    }
}
