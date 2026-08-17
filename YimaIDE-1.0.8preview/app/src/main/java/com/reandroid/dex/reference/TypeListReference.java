package com.reandroid.dex.reference;

import com.reandroid.dex.common.SectionItem;
import com.reandroid.dex.data.TypeList;
import com.reandroid.dex.id.TypeId;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.key.TypeListKey;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.utils.collection.EmptyIterator;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class TypeListReference extends DataItemIndirectReference<TypeList> implements Iterable<TypeId> {
    public TypeListReference(SectionItem sectionItem, int i, int i2) {
        super(SectionType.TYPE_LIST, sectionItem, i, i2);
    }

    public void add(TypeKey typeKey) {
        TypeListKey key = getKey();
        setKey(key != null ? key.add(typeKey) : TypeListKey.create(typeKey));
    }

    public TypeId get(int i) {
        TypeList item = getItem();
        if (item != null) {
            return item.getItem(i);
        }
        return null;
    }

    public TypeId getForRegister(int i) {
        TypeList item = getItem();
        if (item != null) {
            return item.getTypeIdForRegister(i);
        }
        return null;
    }

    @Override // com.reandroid.dex.reference.DataItemIndirectReference, com.reandroid.dex.key.KeyItem
    public TypeListKey getKey() {
        return (TypeListKey) super.getKey();
    }

    public TypeKey getType(int i) {
        TypeId typeId = get(i);
        if (typeId != null) {
            return typeId.getKey();
        }
        return null;
    }

    public Iterator<TypeKey> getTypeKeys() {
        TypeList item = getItem();
        return item != null ? item.getTypeKeys() : EmptyIterator.of();
    }

    @Override // java.lang.Iterable
    public Iterator<TypeId> iterator() {
        TypeList item = getItem();
        return item != null ? item.iterator() : EmptyIterator.of();
    }

    public void remove(TypeKey typeKey) {
        TypeListKey key = getKey();
        if (key == null) {
            return;
        }
        setKey(key.remove(typeKey));
    }

    @Override // com.reandroid.dex.reference.DataItemIndirectReference, com.reandroid.dex.key.KeyReference
    public void setKey(Key key) {
        TypeListKey typeListKey = (TypeListKey) key;
        if (typeListKey != null && typeListKey.isEmpty()) {
            key = null;
        }
        super.setKey(key);
    }

    public int size() {
        TypeList item = getItem();
        if (item != null) {
            return item.size();
        }
        return 0;
    }

    public void remove(int i) {
        TypeListKey key = getKey();
        if (key == null) {
            return;
        }
        setKey(key.remove(i));
    }
}
