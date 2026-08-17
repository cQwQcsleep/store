package com.reandroid.arsc.header;

import com.reandroid.arsc.chunk.ChunkType;
import com.reandroid.arsc.item.IntegerItem;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class StagedAliasHeader extends HeaderBlock {
    private final IntegerItem count;

    public StagedAliasHeader() {
        super(ChunkType.STAGED_ALIAS.ID);
        IntegerItem integerItem = new IntegerItem();
        this.count = integerItem;
        addChild(integerItem);
    }

    public IntegerItem getCountItem() {
        return this.count;
    }

    @Override // com.reandroid.arsc.header.HeaderBlock
    public String toString() {
        if (getChunkType() != ChunkType.STAGED_ALIAS) {
            return super.toString();
        }
        return getClass().getSimpleName() + " {count=" + getCountItem() + '}';
    }
}
