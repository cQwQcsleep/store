package com.reandroid.arsc.array;

import com.reandroid.arsc.item.IntegerArrayBlock;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class IntegerOffsetArray extends IntegerArrayBlock implements OffsetArray {
    @Override // com.reandroid.arsc.item.ShortArrayBlock, com.reandroid.arsc.array.OffsetArray
    public void clear() {
        super.setSize(0);
    }

    @Override // com.reandroid.arsc.array.OffsetArray
    public int getOffset(int i) {
        return super.get(i);
    }

    @Override // com.reandroid.arsc.array.OffsetArray
    public int[] getOffsets() {
        int size = size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = getOffset(i);
        }
        return iArr;
    }

    @Override // com.reandroid.arsc.array.OffsetArray
    public void setOffset(int i, int i2) {
        super.put(i, i2);
    }
}
