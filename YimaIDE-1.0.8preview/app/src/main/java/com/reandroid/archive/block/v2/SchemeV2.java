package com.reandroid.archive.block.v2;

import com.reandroid.archive.block.CertificateBlock;
import com.reandroid.archive.block.SignatureId;
import com.reandroid.archive.block.SignatureScheme;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class SchemeV2 extends SignatureScheme {
    private final V2SignedDataList signedDataList;

    public SchemeV2() {
        super(1, SignatureId.V2);
        V2SignedDataList v2SignedDataList = new V2SignedDataList();
        this.signedDataList = v2SignedDataList;
        addChild(v2SignedDataList);
    }

    @Override // com.reandroid.archive.block.SignatureScheme
    public Iterator<CertificateBlock> getCertificates() {
        return getSignedDataList().getCertificates();
    }

    public V2SignedDataList getSignedDataList() {
        return this.signedDataList;
    }
}
