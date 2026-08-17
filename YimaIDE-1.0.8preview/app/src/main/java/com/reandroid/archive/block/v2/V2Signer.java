package com.reandroid.archive.block.v2;

import com.reandroid.archive.block.BottomBlock;
import com.reandroid.archive.block.CertificateBlock;
import com.reandroid.archive.block.CertificateBlockList;
import com.reandroid.archive.block.CertificateBlockV2;
import com.reandroid.archive.block.LengthPrefixedBlock;
import com.reandroid.utils.collection.InstanceIterator;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class V2Signer extends LengthPrefixedBlock {
    private final CertificateBlockList certificateBlockList;
    private final BottomBlock unknown;
    private final V2Signature v2Signature;

    public V2Signer() {
        super(3, false);
        V2Signature v2Signature = new V2Signature();
        this.v2Signature = v2Signature;
        CertificateBlockList certificateBlockList = new CertificateBlockList();
        this.certificateBlockList = certificateBlockList;
        BottomBlock bottomBlock = new BottomBlock();
        this.unknown = bottomBlock;
        addChild(v2Signature);
        addChild(certificateBlockList);
        addChild(bottomBlock);
    }

    public void addCertificateBlock(CertificateBlockV2 certificateBlockV2) {
        this.certificateBlockList.add(certificateBlockV2);
    }

    public Iterator<CertificateBlock> getCertificates() {
        return InstanceIterator.of(this.certificateBlockList.iterator(), CertificateBlock.class);
    }

    public void removeCertificateBlock(CertificateBlockV2 certificateBlockV2) {
        this.certificateBlockList.remove(certificateBlockV2);
    }

    @Override // com.reandroid.archive.block.LengthPrefixedBlock
    public String toString() {
        return super.toString() + ", sig=" + this.v2Signature + ", certs=" + this.certificateBlockList;
    }
}
