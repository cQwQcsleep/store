package com.reandroid.dex.value;

import com.reandroid.arsc.base.Block;
import com.reandroid.dex.base.UsageMarker;
import com.reandroid.dex.data.AnnotationElement;
import com.reandroid.dex.id.IdItem;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.utils.collection.SingleIterator;
import defpackage.i1d;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class SectionIdValue<T extends IdItem> extends SectionValue<T> {
    public SectionIdValue(SectionType<T> sectionType, DexValueType<?> dexValueType) {
        super(sectionType, dexValueType);
    }

    @Override // com.reandroid.dex.common.EditableItem
    public void editInternal(Block block) {
    }

    @Override // com.reandroid.dex.value.SectionValue, com.reandroid.dex.value.DexValueBlock, com.reandroid.dex.key.KeyItem
    public Key getKey() {
        T item = getItem();
        if (item != null) {
            return item.getKey();
        }
        return null;
    }

    @Override // com.reandroid.dex.value.SectionValue
    public T getReplacement(T t) {
        if (t != null) {
            t = (T) t.getReplace();
        }
        if (t != null) {
            return t;
        }
        i1d.a("Section data can not be null: ", getSectionType().getName());
        return null;
    }

    @Override // com.reandroid.dex.value.SectionValue
    public int getSectionValue(T t) {
        if (t != null) {
            return t.getIndex();
        }
        i1d.a("Section data can not be null: ", getSectionType().getName());
        return 0;
    }

    @Override // com.reandroid.dex.value.DexValueBlock
    public void replaceKeys(Key key, Key key2) {
        Key key3 = getKey();
        Key keyReplaceKey = key3.replaceKey(key, key2);
        if (key3 != keyReplaceKey) {
            setKey(keyReplaceKey);
        }
    }

    @Override // com.reandroid.dex.value.SectionValue
    public void updateUsageType(T t) {
        if (t != null) {
            t.addUsageType(getParent(AnnotationElement.class) != null ? UsageMarker.USAGE_ANNOTATION : UsageMarker.USAGE_ENCODED_VALUE);
        }
    }

    @Override // com.reandroid.dex.value.DexValueBlock
    public Iterator<IdItem> usedIds() {
        return SingleIterator.of(getItem());
    }
}
