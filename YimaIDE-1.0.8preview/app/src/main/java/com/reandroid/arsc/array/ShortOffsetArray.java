package com.reandroid.arsc.array;

import com.reandroid.arsc.item.ShortArrayBlock;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class ShortOffsetArray extends ShortArrayBlock implements OffsetArray {
    @Override // com.reandroid.arsc.item.ShortArrayBlock, com.reandroid.arsc.array.OffsetArray
    public void clear() {
        super.setSize(0);
    }

    @Override // com.reandroid.arsc.array.OffsetArray
    public int getOffset(int i) {
        int i2 = super.get(i);
        if (i2 == 65535) {
            return -1;
        }
        return i2 * 4;
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
        if (i2 != -1) {
            i2 /= 4;
        }
        super.put(i, i2);
    }
}
