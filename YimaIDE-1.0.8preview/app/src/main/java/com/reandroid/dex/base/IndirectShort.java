package com.reandroid.dex.base;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.item.BlockItem;
import com.reandroid.arsc.item.IndirectItem;
import com.reandroid.arsc.item.IntegerReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class IndirectShort extends IndirectItem<BlockItem> implements IntegerReference {
    public IndirectShort(BlockItem blockItem, int i) {
        super(blockItem, i);
    }

    public int get() {
        return Block.getShortUnsigned(getBytesInternal(), getOffset());
    }

    public boolean isNull() {
        return getBytesInternal().length - getOffset() < 2;
    }

    public void set(int i) {
        Block.putShort(getBytesInternal(), getOffset(), i);
    }

    public String toString() {
        return isNull() ? "NULL" : Integer.toString(get());
    }
}
