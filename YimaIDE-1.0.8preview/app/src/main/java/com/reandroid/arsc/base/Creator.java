package com.reandroid.arsc.base;

import com.reandroid.arsc.base.Block;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface Creator<T extends Block> extends BlockCreator<T> {
    T newInstance();

    default T newInstanceAt(int i) {
        return (T) newInstance();
    }
}
