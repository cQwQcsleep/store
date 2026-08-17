package com.reandroid.arsc.header;

import com.reandroid.arsc.chunk.ChunkType;
import com.reandroid.arsc.item.IntegerItem;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class OverlayablePolicyHeader extends HeaderBlock {
    private final IntegerItem entryCount;
    private final IntegerItem flags;

    public OverlayablePolicyHeader() {
        super(ChunkType.OVERLAYABLE_POLICY.ID);
        IntegerItem integerItem = new IntegerItem();
        this.flags = integerItem;
        IntegerItem integerItem2 = new IntegerItem();
        this.entryCount = integerItem2;
        addChild(integerItem);
        addChild(integerItem2);
    }

    public IntegerItem getEntryCount() {
        return this.entryCount;
    }

    public IntegerItem getFlags() {
        return this.flags;
    }

    @Override // com.reandroid.arsc.header.HeaderBlock
    public String toString() {
        if (getChunkType() != ChunkType.OVERLAYABLE_POLICY) {
            return super.toString();
        }
        return getClass().getSimpleName() + " {flags=" + getFlags().toHex() + ", entryCount=" + getEntryCount() + '}';
    }
}
