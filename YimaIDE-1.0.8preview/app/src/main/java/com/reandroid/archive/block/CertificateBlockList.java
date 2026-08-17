package com.reandroid.archive.block;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class CertificateBlockList extends LengthPrefixedList<CertificateBlockV2> {
    public CertificateBlockList() {
        super(false);
    }

    @Override // com.reandroid.arsc.base.BlockCreator
    /* JADX INFO: renamed from: newInstance, reason: merged with bridge method [inline-methods] */
    public CertificateBlockV2 mo6464newInstance() {
        return new CertificateBlockV2();
    }
}
