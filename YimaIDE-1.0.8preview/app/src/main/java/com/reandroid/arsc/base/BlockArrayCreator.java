package com.reandroid.arsc.base;

import com.reandroid.arsc.base.Block;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public interface BlockArrayCreator<T extends Block> extends BlockCreator<T> {
    default T[] newArrayInstance(int i) {
        throw new RuntimeException();
    }
}
