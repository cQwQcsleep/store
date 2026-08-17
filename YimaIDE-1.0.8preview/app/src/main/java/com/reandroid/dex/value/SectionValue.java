package com.reandroid.dex.value;

import com.reandroid.arsc.io.BlockReader;
import com.reandroid.dex.base.UsageMarker;
import com.reandroid.dex.common.SectionItem;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.KeyItem;
import com.reandroid.dex.reference.DexReference;
import com.reandroid.dex.sections.Section;
import com.reandroid.dex.sections.SectionList;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.dex.smali.SmaliFormat;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.dex.smali.model.SmaliValue;
import com.reandroid.dex.smali.model.SmaliValueSectionData;
import com.reandroid.utils.HexUtil;
import java.io.IOException;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class SectionValue<T extends SectionItem> extends DexValueBlock<NumberValue> implements SmaliFormat, KeyItem, DexReference<T> {
    private T mData;
    private final SectionType<T> sectionType;

    public SectionValue(SectionType<T> sectionType, DexValueType<?> dexValueType) {
        super(new NumberValue(), dexValueType);
        this.sectionType = sectionType;
    }

    private Section<T> getOrCreateSection() {
        SectionList sectionList = (SectionList) getParentInstance(SectionList.class);
        if (sectionList != null) {
            return sectionList.getOrCreateSection(getSectionType());
        }
        x0e.a("Null parent SectionList");
        return null;
    }

    private void refreshItem() {
        T t = (T) getReplacement(this.mData);
        this.mData = t;
        set(getSectionValue(t));
        updateUsageType(t);
    }

    @Override // com.reandroid.dex.value.DexValueBlock, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        UsageMarker item = getItem();
        if (item != null) {
            ((SmaliFormat) item).append(smaliWriter);
            return;
        }
        smaliWriter.append("value error: ");
        smaliWriter.append((CharSequence) getSectionType().getName());
        smaliWriter.append(' ');
        smaliWriter.append((CharSequence) HexUtil.toHex(get(), getValueSize()));
    }

    @Override // com.reandroid.dex.value.DexValueBlock
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Objects.equals(getKey(), ((SectionValue) obj).getKey());
    }

    @Override // com.reandroid.dex.value.DexValueBlock
    public void fromSmali(SmaliValue smaliValue) {
        setKey(((SmaliValueSectionData) smaliValue).getValue());
    }

    public int get() {
        return (int) getValueContainer().getUnsignedNumber();
    }

    @Override // com.reandroid.dex.reference.DexReference
    public T getItem() {
        return this.mData;
    }

    @Override // com.reandroid.dex.value.DexValueBlock, com.reandroid.dex.key.KeyItem
    public abstract Key getKey();

    public abstract T getReplacement(T t);

    public Section<T> getSection() {
        SectionList sectionList = (SectionList) getParentInstance(SectionList.class);
        if (sectionList != null) {
            return sectionList.getSection(getSectionType());
        }
        return null;
    }

    @Override // com.reandroid.dex.reference.DexReference
    public SectionType<T> getSectionType() {
        return this.sectionType;
    }

    public abstract int getSectionValue(T t);

    @Override // com.reandroid.dex.value.DexValueBlock
    public abstract DexValueType<?> getValueType();

    @Override // com.reandroid.dex.value.DexValueBlock
    public int hashCode() {
        Key key = getKey();
        if (key != null) {
            return key.hashCode();
        }
        return 0;
    }

    @Override // com.reandroid.dex.value.DexValueBlock
    public void merge(DexValueBlock<?> dexValueBlock) {
        super.merge(dexValueBlock);
        setKey(((SectionValue) dexValueBlock).getKey());
    }

    public void onPreRefresh() {
        refreshItem();
    }

    public void onReadBytes(BlockReader blockReader) throws IOException {
        getValueTypeItem().onReadBytes(blockReader);
        NumberValue valueContainer = getValueContainer();
        valueContainer.setSize(getValueSize() + 1);
        valueContainer.readBytes(blockReader);
        pullItem();
    }

    @Override // com.reandroid.dex.reference.DexReference
    public void pullItem() {
        Section<T> section = getSection();
        if (section != null) {
            T t = (T) section.getSectionItem(get());
            this.mData = t;
            updateUsageType(t);
        }
    }

    public void set(int i) {
        NumberValue valueContainer = getValueContainer();
        valueContainer.setUnsignedNumber(((long) i) & 4294967295L);
        setValueSize(valueContainer.getSize() - 1);
    }

    @Override // com.reandroid.dex.reference.DexReference
    public void setItem(T t) {
        if (t == this.mData) {
            return;
        }
        this.mData = t;
        set(getSectionValue(t));
        updateUsageType(t);
    }

    @Override // com.reandroid.dex.value.DexValueBlock, com.reandroid.dex.key.KeyReference
    public void setKey(Key key) {
        setItem(getOrCreateSection().getOrCreate(key));
    }

    @Override // com.reandroid.dex.value.DexValueBlock
    public String toString() {
        return SmaliWriter.toStringSafe(this);
    }

    public abstract void updateUsageType(T t);
}
