package com.reandroid.archive.block.v2;

import com.reandroid.archive.block.BottomBlock;
import com.reandroid.archive.block.CertificateBlock;
import com.reandroid.archive.block.LengthPrefixedBlock;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class V2SignedData extends LengthPrefixedBlock {
    private final V2Signer signer;
    private final BottomBlock unknown;

    public V2SignedData() {
        super(2, false);
        V2Signer v2Signer = new V2Signer();
        this.signer = v2Signer;
        BottomBlock bottomBlock = new BottomBlock();
        this.unknown = bottomBlock;
        addChild(v2Signer);
        addChild(bottomBlock);
    }

    public Iterator<CertificateBlock> getCertificates() {
        return getSigner().getCertificates();
    }

    public V2Signer getSigner() {
        return this.signer;
    }
}
