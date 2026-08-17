package com.reandroid.dex.common;

import com.reandroid.arsc.base.Block;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface EditableItem {
    default void edit() {
    }

    void editInternal(Block block);
}
