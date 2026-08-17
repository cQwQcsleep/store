package com.reandroid.dex.reference;

import com.reandroid.arsc.base.Block;
import com.reandroid.dex.id.IdItem;
import defpackage.i1d;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface IdReference<T extends IdItem> extends DexReference<T> {
    default void checkNonNullItem(T t, int i) {
        if (t != null) {
            return;
        }
        throw new NullPointerException("Null item (" + i + ")" + getSectionType().getName());
    }

    @Override // com.reandroid.dex.common.EditableItem
    default void editInternal(Block block) {
        T item = getItem();
        if (item != null) {
            item.editInternal(block);
        }
    }

    default void checkNonNullItem(T t) {
        if (t != null) {
            return;
        }
        i1d.a("Null item for: ", getSectionType().getName());
    }
}
