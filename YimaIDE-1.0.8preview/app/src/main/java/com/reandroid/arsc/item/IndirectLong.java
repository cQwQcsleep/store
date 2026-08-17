package com.reandroid.arsc.item;

import com.reandroid.arsc.base.Block;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class IndirectLong extends IndirectItem<BlockItem> implements LongReference {
    public IndirectLong(BlockItem blockItem, int i) {
        super(blockItem, i);
    }

    @Override // com.reandroid.arsc.item.IntegerReference
    public int get() {
        return (int) getLong();
    }

    @Override // com.reandroid.arsc.item.LongReference
    public long getLong() {
        return Block.getLong(getBytesInternal(), getOffset());
    }

    public boolean isNull() {
        return getBytesInternal().length - getOffset() < 8;
    }

    @Override // com.reandroid.arsc.item.LongReference
    public void set(long j) {
        Block.putLong(getBytesInternal(), getOffset(), j);
    }

    public String toString() {
        return isNull() ? "NULL" : Long.toString(getLong());
    }

    @Override // com.reandroid.arsc.item.IntegerReference
    public void set(int i) {
        set(i);
    }
}
