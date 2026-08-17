package com.reandroid.arsc.item;

import com.reandroid.arsc.base.Block;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class IndirectBoolean implements BooleanReference {
    private final int bitIndex;
    private final BlockItem blockItem;
    private final int byteOffset;

    public IndirectBoolean(BlockItem blockItem, int i, int i2) {
        this.blockItem = blockItem;
        this.byteOffset = i;
        this.bitIndex = i2;
    }

    public boolean get() {
        return Block.getBit(this.blockItem.getBytesInternal(), this.byteOffset, this.bitIndex);
    }

    public void set(boolean z) {
        Block.putBit(this.blockItem.getBytesInternal(), this.byteOffset, this.bitIndex, z);
    }

    public String toString() {
        return Boolean.toString(get());
    }
}
