package com.reandroid.dex.reference;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.item.IndirectItem;
import com.reandroid.dex.base.UsageMarker;
import com.reandroid.dex.common.SectionItem;
import com.reandroid.dex.common.SectionTool;
import com.reandroid.dex.id.IdItem;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.sections.SectionType;
import defpackage.i1d;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class IdItemIndirectReference<T extends IdItem> extends IndirectItem<SectionItem> implements IdReference<T>, Comparable<IdReference<T>> {
    private T item;
    private final SectionType<T> sectionType;
    private final int usageType;

    public IdItemIndirectReference(SectionType<T> sectionType, SectionItem sectionItem, int i, int i2) {
        super(sectionItem, i);
        this.sectionType = sectionType;
        this.usageType = i2;
        Block.putInteger(getBytesInternal(), getOffset(), -1);
    }

    private String buildMessage() {
        SectionItem sectionItem = (SectionItem) getBlockItem();
        StringBuilder sb = new StringBuilder("Parent = ");
        sb.append(sectionItem);
        for (Field field : sectionItem.getClass().getFields()) {
            try {
                field.setAccessible(true);
                if (field.get(sectionItem) == this) {
                    sb.append(", Field = ");
                    sb.append(field.getName());
                    break;
                }
                continue;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        sb.append(", section = ");
        sb.append(getSectionType().getName());
        return sb.toString();
    }

    private void updateItemUsage() {
        T t;
        int i = this.usageType;
        if (i == UsageMarker.USAGE_NONE || (t = this.item) == null) {
            return;
        }
        t.addUsageType(i);
    }

    public void checkNonNullItem(T t) {
        if (t != null) {
            return;
        }
        x0e.a(buildMessage());
    }

    @Override // java.lang.Comparable
    public int compareTo(IdReference<T> idReference) {
        return SectionTool.compareIdx((T) getItem(), idReference.getItem());
    }

    public int get() {
        return Block.getInteger(getBytesInternal(), getOffset());
    }

    public int getItemIndex(T t) {
        if (t != null) {
            return t.getIdx();
        }
        i1d.a("Can't set null for reference of: ", getSectionType().getName());
        return 0;
    }

    public Key getKey() {
        IdItem item = getItem();
        if (item != null) {
            return item.getKey();
        }
        return null;
    }

    public SectionType<T> getSectionType() {
        return this.sectionType;
    }

    public T pullItem(int i) {
        return (T) ((SectionItem) getBlockItem()).getSectionItem(getSectionType(), i);
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

    public void replaceKeys(Key key, Key key2) {
        Key keyReplaceKey;
        Key key3 = getKey();
        if (key3 == null || key3 == (keyReplaceKey = key3.replaceKey(key, key2))) {
            return;
        }
        setKey(keyReplaceKey);
    }

    public void set(int i) {
        Block.putInteger(getBytesInternal(), getOffset(), i);
    }

    @Override // com.reandroid.dex.reference.DexReference
    public void setItem(T t) {
        if (t == this.item) {
            return;
        }
        set(getItemIndex(t));
        this.item = t;
        updateItemUsage();
    }

    public void setKey(Key key) {
        setItem((IdItem) ((SectionItem) getBlockItem()).getOrCreateSectionItem(getSectionType(), key));
    }

    public String toString() {
        if (this.item != null) {
            return get() + ": " + this.item.toString();
        }
        return getSectionType().getName() + ": " + get();
    }

    public void unlink() {
        this.item = null;
        set(0);
    }

    @Override // com.reandroid.dex.reference.DexReference
    public T getItem() {
        return this.item;
    }

    @Override // com.reandroid.dex.reference.DexReference
    public void pullItem() {
        this.item = (T) pullItem(get());
        updateItemUsage();
    }

    public IdItemIndirectReference(SectionType<T> sectionType, SectionItem sectionItem, int i) {
        this(sectionType, sectionItem, i, UsageMarker.USAGE_NONE);
    }
}
