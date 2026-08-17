package com.reandroid.arsc.header;

import com.reandroid.arsc.chunk.ChunkType;
import com.reandroid.arsc.item.IntegerItem;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class TableHeader extends HeaderBlock {
    private final IntegerItem packageCount;

    public TableHeader() {
        super(ChunkType.TABLE.ID);
        IntegerItem integerItem = new IntegerItem();
        this.packageCount = integerItem;
        addChild(integerItem);
    }

    public IntegerItem getPackageCount() {
        return this.packageCount;
    }

    @Override // com.reandroid.arsc.header.HeaderBlock
    public String toString() {
        if (getChunkType() != ChunkType.TABLE) {
            return super.toString();
        }
        return getClass().getSimpleName() + " {packageCount=" + getPackageCount() + '}';
    }
}
