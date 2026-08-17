package com.reandroid.dex.base;

import com.reandroid.arsc.base.BlockRefresh;
import com.reandroid.arsc.item.IntegerReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ParallelIntegerPair implements IntegerPair, BlockRefresh {
    private final ParallelReference first;
    private final ParallelReference second;

    public ParallelIntegerPair(IntegerPair integerPair) {
        this.first = new ParallelReference(integerPair.getFirst(), null);
        this.second = new ParallelReference(integerPair.getSecond(), null);
    }

    public void refresh() {
        getFirst().refresh();
        getSecond().refresh();
    }

    public void setReference2(IntegerPair integerPair) {
        IntegerReference second;
        IntegerReference integerReference;
        if (integerPair == null) {
            integerReference = null;
            second = null;
        } else {
            IntegerReference first = integerPair.getFirst();
            second = integerPair.getSecond();
            integerReference = first;
        }
        getFirst().setReference2(integerReference);
        getSecond().setReference2(second);
    }

    @Override // com.reandroid.dex.base.IntegerPair
    public ParallelReference getFirst() {
        return this.first;
    }

    @Override // com.reandroid.dex.base.IntegerPair
    public ParallelReference getSecond() {
        return this.second;
    }
}
