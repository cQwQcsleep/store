package com.reandroid.archive.block.v2;

import com.reandroid.archive.block.CertificateBlock;
import com.reandroid.archive.block.LengthPrefixedList;
import com.reandroid.utils.collection.IterableIterator;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class V2SignedDataList extends LengthPrefixedList<V2SignedData> {
    public V2SignedDataList() {
        super(false);
    }

    public Iterator<CertificateBlock> getCertificates() {
        return new IterableIterator<V2SignedData, CertificateBlock>(iterator()) { // from class: com.reandroid.archive.block.v2.V2SignedDataList.1
            @Override // com.reandroid.utils.collection.IterableIterator
            public Iterator<CertificateBlock> iterator(V2SignedData v2SignedData) {
                return v2SignedData.getCertificates();
            }
        };
    }

    @Override // com.reandroid.arsc.base.BlockCreator
    /* JADX INFO: renamed from: newInstance, reason: merged with bridge method [inline-methods] */
    public V2SignedData mo6464newInstance() {
        return new V2SignedData();
    }
}
