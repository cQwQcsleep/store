package com.reandroid.archive.block.pad;

import com.reandroid.archive.block.CertificateBlock;
import com.reandroid.archive.block.SignatureId;
import com.reandroid.archive.block.SignatureScheme;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.ByteArray;
import com.reandroid.utils.collection.EmptyIterator;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class SchemePadding extends SignatureScheme {
    private final ByteArray byteArray;

    public SchemePadding() {
        super(1, SignatureId.PADDING);
        ByteArray byteArray = new ByteArray();
        this.byteArray = byteArray;
        addChild(byteArray);
    }

    @Override // com.reandroid.archive.block.SignatureScheme
    public Iterator<CertificateBlock> getCertificates() {
        return EmptyIterator.of();
    }

    public int getPadding() {
        return this.byteArray.size();
    }

    public byte[] getPaddingBytes() {
        return this.byteArray.getBytes();
    }

    public void onReadBytes(BlockReader blockReader) throws IOException {
        this.byteArray.setSize(((int) getSignatureInfo().getDataSize()) - 4);
        super/*com.reandroid.arsc.base.BlockContainer*/.onReadBytes(blockReader);
    }

    public void setPadding(int i) {
        this.byteArray.setSize(i);
    }

    @Override // com.reandroid.archive.block.SignatureScheme
    public String toString() {
        return "padding = " + getPadding();
    }

    public void setPadding(byte[] bArr) {
        this.byteArray.set(bArr);
    }
}
