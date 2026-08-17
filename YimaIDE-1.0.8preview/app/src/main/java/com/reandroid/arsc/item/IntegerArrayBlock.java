package com.reandroid.arsc.item;

import com.reandroid.arsc.base.Block;
import java.util.AbstractList;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class IntegerArrayBlock extends ShortArrayBlock {
    @Override // com.reandroid.arsc.item.ShortArrayBlock
    public int get(int i) {
        if (i < 0 || i >= size()) {
            return 0;
        }
        return Block.getInteger(getBytesInternal(), i * 4);
    }

    @Override // com.reandroid.arsc.item.ShortArrayBlock
    public void put(int i, int i2) {
        Block.putInteger(getBytesInternal(), i * 4, i2);
    }

    @Override // com.reandroid.arsc.item.ShortArrayBlock
    public void setSize(int i) {
        if (i < 0) {
            i = 0;
        }
        int i2 = i * 4;
        if (i2 >= 0) {
            setBytesLength(i2);
            return;
        }
        throw new IndexOutOfBoundsException("Huge integers size = " + i + ", parent = " + getParent());
    }

    @Override // com.reandroid.arsc.item.ShortArrayBlock
    public int size() {
        return getBytesLength() / 4;
    }

    public final List<Integer> toList() {
        return new AbstractList<Integer>() { // from class: com.reandroid.arsc.item.IntegerArrayBlock.1
            @Override // java.util.AbstractList, java.util.List
            public Integer get(int i) {
                return Integer.valueOf(IntegerArrayBlock.this.get(i));
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
            public int size() {
                return IntegerArrayBlock.this.size();
            }
        };
    }
}
