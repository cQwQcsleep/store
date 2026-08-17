package com.reandroid.dex.reference;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.dex.base.Ule128Item;
import com.reandroid.dex.base.UsageMarker;
import com.reandroid.dex.data.DataItem;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.sections.SectionType;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DataItemUle128Reference<T extends DataItem> extends Ule128Item implements DataReference<T> {
    private T item;
    private final SectionType<T> sectionType;
    private final int usageType;

    public DataItemUle128Reference(SectionType<T> sectionType, int i) {
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
        T t = (T) createSectionItem(getSectionType());
        copyToIfPresent(t);
        setItem((DataItem) t);
        return t;
    }

    private void updateItemUsage() {
        T t;
        int i = this.usageType;
        if (i == UsageMarker.USAGE_NONE || (t = this.item) == null) {
            return;
        }
        t.addUsageType(i);
    }

    public void addUniqueUser(Block block) {
        DataItem item = getItem();
        if (item != null) {
            item.addUniqueUser(block);
        }
    }

    @Override // com.reandroid.dex.common.EditableItem
    public void editInternal(Block block) {
        DataItem uniqueItem = getUniqueItem(block);
        if (uniqueItem != null) {
            uniqueItem.editInternal(block);
        }
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
        T t2 = (T) createSectionItem(getSectionType());
        setItem((DataItem) t2);
        return t2;
    }

    public T getOrCreateUniqueItem(Block block) {
        T t = (T) getUniqueItem(block);
        if (t != null) {
            return t;
        }
        T t2 = (T) createSectionItem(getSectionType());
        setItem((DataItem) t2);
        addUniqueUser(block);
        return t2;
    }

    @Override // com.reandroid.dex.reference.DexReference
    public SectionType<T> getSectionType() {
        return this.sectionType;
    }

    public T getUniqueItem(Block block) {
        T t = (T) getItem();
        if (t == null) {
            return null;
        }
        if (t.isSharedItem(block)) {
            t = (T) createNewCopy();
        }
        t.addUniqueUser(block);
        return t;
    }

    @Override // com.reandroid.dex.base.Le128
    public void onReadBytes(BlockReader blockReader) throws IOException {
        super.onReadBytes(blockReader);
        pullItem();
    }

    @Override // com.reandroid.dex.reference.DexReference
    public void pullItem() {
        this.item = (T) getSectionItem(getSectionType(), get());
        updateItemUsage();
    }

    public void refresh() {
        T t = (T) getItem();
        if (t != null) {
            t = (T) t.getReplace();
        }
        int idx = t != null ? t.getIdx() : 0;
        this.item = t;
        set(idx);
        updateItemUsage();
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
        setItem((DataItem) getOrCreateSectionItem(getSectionType(), key));
    }

    @Override // com.reandroid.dex.base.Le128
    public String toString() {
        T t = this.item;
        if (t != null) {
            return t.toString();
        }
        return this.sectionType.getName() + ": " + get();
    }

    @Override // com.reandroid.dex.reference.DataReference
    public void unlink() {
        this.item = null;
        set(0);
    }

    @Override // com.reandroid.dex.reference.DexReference
    public T getItem() {
        return this.item;
    }
}
