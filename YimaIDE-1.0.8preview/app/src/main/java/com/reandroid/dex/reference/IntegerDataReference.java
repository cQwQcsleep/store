package com.reandroid.dex.reference;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.IntegerItem;
import com.reandroid.dex.base.DexException;
import com.reandroid.dex.common.SectionTool;
import com.reandroid.dex.data.DataItem;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.sections.SectionType;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class IntegerDataReference<T extends DataItem> extends IntegerItem implements DataReference<T> {
    private T item;
    private final SectionType<T> sectionType;
    private final int usageType;

    public IntegerDataReference(SectionType<T> sectionType, int i) {
        this.sectionType = sectionType;
        this.usageType = i;
    }

    private void copyToIfPresent(T t) {
        DataItem item = getItem();
        if (item != null) {
            t.copyFrom(item);
        }
    }

    private T createNewCopy() {
        T t = (T) getSectionTool().createSectionItem(getSectionType());
        copyToIfPresent(t);
        setItem((DataItem) t);
        return t;
    }

    private void updateItemUsage() {
        T t = this.item;
        if (t != null) {
            t.addUsageType(this.usageType);
            t.addUniqueUser(this);
        }
    }

    @Override // com.reandroid.dex.common.EditableItem
    public void editInternal(Block block) {
        DataItem uniqueItem = getUniqueItem();
        if (uniqueItem != null) {
            uniqueItem.editInternal(this);
        }
    }

    @Override // com.reandroid.dex.reference.DexReference
    public T getItem() {
        DataItem dataItem;
        T t = this.item;
        if (t == null || (dataItem = (DataItem) t.getReplace()) == t) {
            return t;
        }
        setItem(dataItem);
        return this.item;
    }

    @Override // com.reandroid.dex.key.KeyItem
    public Key getKey() {
        DataItem item = getItem();
        if (item != null) {
            return item.getKey();
        }
        return null;
    }

    @Override // com.reandroid.dex.reference.DataReference
    public T getOrCreate() {
        T t = (T) getItem();
        if (t != null) {
            return t;
        }
        T t2 = (T) getSectionTool().createSectionItem(getSectionType());
        setItem((DataItem) t2);
        return t2;
    }

    public SectionTool getSectionTool() {
        return (SectionTool) getParentInstance(SectionTool.class);
    }

    @Override // com.reandroid.dex.reference.DexReference
    public SectionType<T> getSectionType() {
        return this.sectionType;
    }

    public T getUniqueItem() {
        T t = (T) getItem();
        return (t == null || !t.isSharedItem(this)) ? t : (T) createNewCopy();
    }

    public void onReadBytes(BlockReader blockReader) throws IOException {
        super/*com.reandroid.arsc.item.BlockItem*/.onReadBytes(blockReader);
        pullItem();
    }

    @Override // com.reandroid.dex.reference.DexReference
    public void pullItem() {
        this.item = (T) getSectionTool().getSectionItem(getSectionType(), get());
        updateItemUsage();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.reandroid.dex.base.DexException */
    public void refresh() throws DexException {
        int idx;
        T t = (T) getItem();
        if (t != null) {
            idx = t.getIdx();
            if (idx == 0) {
                throw new DexException("Invalid reference: " + t);
            }
        } else {
            idx = 0;
        }
        this.item = t;
        set(idx);
        updateItemUsage();
    }

    public void replaceKeys(Key key, Key key2) {
        Key keyReplaceKey;
        Key key3 = getKey();
        if (key3 == null || key3 == (keyReplaceKey = key3.replaceKey(key, key2))) {
            return;
        }
        setKey(keyReplaceKey);
    }

    @Override // com.reandroid.dex.reference.DexReference
    public void setItem(T t) {
        int offset = t != null ? t.getOffset() : 0;
        this.item = t;
        set(offset);
        updateItemUsage();
    }

    @Override // com.reandroid.dex.key.KeyReference
    public void setKey(Key key) {
        setItem((DataItem) getSectionTool().getOrCreateSectionItem(getSectionType(), key));
    }

    public String toString() {
        T t = this.item;
        if (t != null) {
            return get() + ":" + t.toString();
        }
        return getSectionType().getName() + ": " + get();
    }

    @Override // com.reandroid.dex.reference.DataReference
    public void unlink() {
        this.item = null;
        set(0);
    }
}
