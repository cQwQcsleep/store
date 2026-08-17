package com.reandroid.arsc.item;

import com.reandroid.arsc.base.Block;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class IndirectInteger extends IndirectItem<BlockItem> implements IntegerReference {
    public IndirectInteger(BlockItem blockItem, int i) {
        super(blockItem, i);
    }

    @Override // com.reandroid.arsc.item.IntegerReference
    public int get() {
        return Block.getInteger(getBytesInternal(), getOffset());
    }

    public boolean isNull() {
        return getBytesInternal().length - getOffset() < 4;
    }

    @Override // com.reandroid.arsc.item.IntegerReference
    public void set(int i) {
        Block.putInteger(getBytesInternal(), getOffset(), i);
    }

    public String toString() {
        return isNull() ? "NULL" : Integer.toString(get());
    }
}
