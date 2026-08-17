package com.reandroid.dex.reference;

import com.reandroid.arsc.io.BlockReader;
import com.reandroid.dex.base.Ule128Item;
import com.reandroid.dex.id.IdItem;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.sections.SectionType;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Ule128IdItemReference<T extends IdItem> extends Ule128Item implements IdReference<T> {
    private T item;
    private final SectionType<T> sectionType;
    private final int usageType;

    public Ule128IdItemReference(SectionType<T> sectionType, int i) {
        this.sectionType = sectionType;
        this.usageType = i;
    }

    private void updateItemUsage() {
        T t = this.item;
        if (t != null) {
            t.addUsageType(this.usageType);
        }
    }

    @Override // com.reandroid.dex.key.KeyItem
    public Key getKey() {
        IdItem item = getItem();
        if (item != null) {
            return item.getKey();
        }
        return null;
    }

    @Override // com.reandroid.dex.reference.DexReference
    public SectionType<T> getSectionType() {
        return this.sectionType;
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
        checkNonNullItem(t);
        if (t != null) {
            set(t.getIdx());
        }
        this.item = t;
        updateItemUsage();
    }

    @Override // com.reandroid.dex.reference.DexReference
    public void setItem(T t) {
        int idx;
        if (t != null) {
            idx = t.getIdx();
            t.addUsageType(this.usageType);
        } else {
            idx = 0;
        }
        this.item = t;
        set(idx);
    }

    @Override // com.reandroid.dex.key.KeyReference
    public void setKey(Key key) {
        setItem((IdItem) getPool(getSectionType()).getOrCreate(key));
    }

    @Override // com.reandroid.dex.base.Le128
    public String toString() {
        T t = this.item;
        if (t != null) {
            return t.toString();
        }
        return getSectionType().getName() + ": " + get();
    }

    @Override // com.reandroid.dex.reference.DexReference
    public T getItem() {
        return this.item;
    }
}
