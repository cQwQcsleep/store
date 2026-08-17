package com.reandroid.archive.block;

import com.reandroid.arsc.item.ByteArray;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class LengthPrefixedBytes extends LengthPrefixedBlock {
    private final ByteArray byteArray;

    public LengthPrefixedBytes(boolean z) {
        super(1, z);
        ByteArray byteArray = new ByteArray();
        this.byteArray = byteArray;
        addChild(byteArray);
    }

    public ByteArray getByteArray() {
        return this.byteArray;
    }

    @Override // com.reandroid.archive.block.LengthPrefixedBlock
    public void onSizeLoaded(int i) {
        this.byteArray.setSize(i);
    }
}
