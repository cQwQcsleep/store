package com.reandroid.dex.reference;

import com.reandroid.arsc.base.BlockRefresh;
import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.dex.common.EditableItem;
import com.reandroid.dex.common.SectionItem;
import com.reandroid.dex.key.KeyReference;
import com.reandroid.dex.sections.SectionType;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface DexReference<T extends SectionItem> extends IntegerReference, KeyReference, BlockRefresh, EditableItem {
    T getItem();

    SectionType<T> getSectionType();

    void pullItem();

    void setItem(T t);
}
