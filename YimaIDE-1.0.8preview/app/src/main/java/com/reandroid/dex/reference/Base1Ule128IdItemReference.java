package com.reandroid.dex.reference;

import com.reandroid.arsc.io.BlockReader;
import com.reandroid.dex.base.Ule128Item;
import com.reandroid.dex.base.UsageMarker;
import com.reandroid.dex.id.IdItem;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.dex.smali.SmaliFormat;
import com.reandroid.dex.smali.SmaliWriter;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Base1Ule128IdItemReference<T extends IdItem> extends Ule128Item implements IdReference<T>, SmaliFormat {
    private T item;
    private final SectionType<T> sectionType;
    private final int usageType;

    public Base1Ule128IdItemReference(SectionType<T> sectionType, int i) {
        this.sectionType = sectionType;
        this.usageType = i;
    }

    private void updateItemUsage() {
        T t = this.item;
        if (t != null) {
            t.addUsageType(this.usageType);
        }
    }

    public void append(SmaliWriter smaliWriter) throws IOException {
        IdItem item = getItem();
        if (item != null) {
            item.append(smaliWriter);
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
        T t;
        int i = get();
        if (i == 0) {
            t = null;
        } else {
            int i2 = i - 1;
            IdItem idItem = (IdItem) getSectionItem(getSectionType(), i2);
            checkNonNullItem(idItem, i2);
            t = (T) idItem;
        }
        this.item = t;
        updateItemUsage();
    }

    public void refresh() {
        T t = (T) getItem();
        if (t != null) {
            t = (T) t.getReplace();
        }
        int idx = t != null ? t.getIdx() + 1 : 0;
        this.item = t;
        set(idx);
        updateItemUsage();
    }

    @Override // com.reandroid.dex.reference.DexReference
    public void setItem(T t) {
        if (t != null) {
            t = (T) t.getReplace();
        }
        int idx = t != null ? t.getIdx() + 1 : 0;
        this.item = t;
        set(idx);
        if (t != null) {
            t.addUsageType(UsageMarker.USAGE_DEBUG);
        }
    }

    @Override // com.reandroid.dex.key.KeyReference
    public void setKey(Key key) {
        setItem(key != null ? (IdItem) getSection(getSectionType()).getOrCreate(key) : null);
    }

    @Override // com.reandroid.dex.base.Le128
    public String toString() {
        Key key = getKey();
        if (key != null) {
            return key.toString();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(getSectionType().getName());
        sb.append(": ");
        sb.append(get() - 1);
        return sb.toString();
    }

    @Override // com.reandroid.dex.reference.DexReference
    public T getItem() {
        return this.item;
    }

    public Base1Ule128IdItemReference(SectionType<T> sectionType) {
        this(sectionType, UsageMarker.USAGE_DEBUG);
    }
}
