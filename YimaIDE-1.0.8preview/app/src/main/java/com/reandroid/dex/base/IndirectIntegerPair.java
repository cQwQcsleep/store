package com.reandroid.dex.base;

import com.reandroid.arsc.item.BlockItem;
import com.reandroid.arsc.item.IndirectInteger;
import com.reandroid.arsc.item.IntegerReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class IndirectIntegerPair implements IntegerPair {
    private final IntegerReference first;
    private final IntegerReference second;

    public IndirectIntegerPair(BlockItem blockItem, int i) {
        this.first = new IndirectInteger(blockItem, i);
        this.second = new IndirectInteger(blockItem, i + 4);
    }

    @Override // com.reandroid.dex.base.IntegerPair
    public IntegerReference getFirst() {
        return this.first;
    }

    @Override // com.reandroid.dex.base.IntegerPair
    public IntegerReference getSecond() {
        return this.second;
    }

    public String toString() {
        return "(" + getFirst() + ", " + getSecond() + ")";
    }
}
