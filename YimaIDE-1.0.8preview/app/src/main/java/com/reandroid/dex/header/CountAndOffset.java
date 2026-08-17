package com.reandroid.dex.header;

import com.reandroid.dex.base.BlockIntegerPair;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class CountAndOffset extends BlockIntegerPair {
    public int getCount() {
        return getFirst().get();
    }

    public int getOffset() {
        return getSecond().get();
    }

    public void setCount(int i) {
        getFirst().set(i);
    }

    public void setOffset(int i) {
        getSecond().set(i);
    }
}
