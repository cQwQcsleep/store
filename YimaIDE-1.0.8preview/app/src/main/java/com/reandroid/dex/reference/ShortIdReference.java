package com.reandroid.dex.reference;

import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.ShortItem;
import com.reandroid.dex.base.DexException;
import com.reandroid.dex.base.UsageMarker;
import com.reandroid.dex.common.SectionTool;
import com.reandroid.dex.id.IdItem;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.utils.HexUtil;
import defpackage.i1d;
import defpackage.jq6;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ShortIdReference<T extends IdItem> extends ShortItem implements IdReference<T> {
    private T item;
    private final SectionType<T> sectionType;
    private final int usageType;

    public ShortIdReference(SectionType<T> sectionType, int i) {
        this.sectionType = sectionType;
        this.usageType = i;
        super.set(-1);
    }

    private String buildMessage() {
        return "Parent = " + getParent() + ", section = " + getSectionType().getName();
    }

    private SectionTool getSectionTool() {
        return (SectionTool) getParentInstance(SectionTool.class);
    }

    private void updateItemUsage() {
        T t;
        int i = this.usageType;
        if (i == UsageMarker.USAGE_NONE || (t = this.item) == null) {
            return;
        }
        t.addUsageType(i);
    }

    @Override // com.reandroid.dex.reference.IdReference
    public void checkNonNullItem(T t) {
        if (t != null) {
            return;
        }
        x0e.a(buildMessage());
    }

    public int getItemIndex(T t) {
        if (t != null) {
            return t.getIdx();
        }
        i1d.a("Can't set null for reference of: ", getSectionType().getName());
        return 0;
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

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.reandroid.dex.base.DexException */
    public void replaceKeys(Key key, Key key2) throws DexException {
        Key keyReplaceKey;
        Key key3 = getKey();
        if (key3 == null || key3 == (keyReplaceKey = key3.replaceKey(key, key2))) {
            return;
        }
        setKey(keyReplaceKey);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.reandroid.dex.base.DexException */
    public void set(int i) throws DexException {
        if (((-65536) & i) == 0) {
            super.set(i);
        } else {
            jq6.a("Short value out of range ", HexUtil.toHex(i, 4), " > 0xffff");
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.reandroid.dex.base.DexException */
    @Override // com.reandroid.dex.reference.DexReference
    public void setItem(T t) throws DexException {
        if (t == this.item) {
            return;
        }
        set(getItemIndex(t));
        this.item = t;
        updateItemUsage();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.reandroid.dex.base.DexException */
    @Override // com.reandroid.dex.key.KeyReference
    public void setKey(Key key) throws DexException {
        setItem((IdItem) getSectionTool().getOrCreateSectionItem(getSectionType(), key));
    }

    public String toString() {
        if (this.item != null) {
            return get() + ": " + this.item.toString();
        }
        return getSectionType().getName() + ": " + get();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.reandroid.dex.base.DexException */
    public void unlink() throws DexException {
        this.item = null;
        set(0);
    }

    @Override // com.reandroid.dex.reference.DexReference
    public T getItem() {
        return this.item;
    }
}
