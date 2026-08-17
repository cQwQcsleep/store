package com.reandroid.dex.value;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.container.FixedBlockContainer;
import com.reandroid.arsc.item.ByteItem;
import com.reandroid.dex.id.IdItem;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.KeyReference;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.smali.SmaliFormat;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.dex.smali.model.SmaliValue;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.collection.EmptyIterator;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DexValueBlock<T extends Block> extends FixedBlockContainer implements KeyReference, SmaliFormat {
    private final T valueContainer;
    private final ByteItem valueTypeItem;

    public DexValueBlock(T t, DexValueType<?> dexValueType) {
        super(2);
        ByteItem byteItem = new ByteItem();
        this.valueTypeItem = byteItem;
        this.valueContainer = t;
        addChild(0, byteItem);
        addChild(1, t);
        byteItem.set((byte) dexValueType.getFlag(0));
    }

    private DexValueType<?> getValueTypeReal() {
        return DexValueType.fromFlag(this.valueTypeItem.get());
    }

    public void append(SmaliWriter smaliWriter) throws IOException {
        getKey().append(smaliWriter);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return ObjectsUtil.equals(getValueContainer(), ((DexValueBlock) obj).getValueContainer());
    }

    public void fromSmali(SmaliValue smaliValue) {
        throw new RuntimeException("Method not implemented: ".concat(getClass().getSimpleName()));
    }

    public TypeKey getDataTypeKey() {
        return TypeKey.OBJECT;
    }

    public Key getKey() {
        throw new RuntimeException("Method not implemented: " + getClass());
    }

    public T getValueContainer() {
        return this.valueContainer;
    }

    public int getValueSize() {
        return DexValueType.decodeSize(this.valueTypeItem.get());
    }

    public DexValueType<?> getValueType() {
        return getValueTypeReal();
    }

    public ByteItem getValueTypeItem() {
        return this.valueTypeItem;
    }

    public int hashCode() {
        return ((getValueType().getType() + 31) * 31) + getValueContainer().hashCode();
    }

    public boolean is(DexValueType<?> dexValueType) {
        return dexValueType == getValueType();
    }

    public void merge(DexValueBlock<?> dexValueBlock) {
        this.valueTypeItem.set(dexValueBlock.valueTypeItem.getByte());
    }

    public void replaceKeys(Key key, Key key2) {
    }

    public void setKey(Key key) {
        throw new RuntimeException("Method not implemented: " + getClass());
    }

    public void setValueSize(int i) {
        this.valueTypeItem.set((byte) getValueType().getFlag(i));
    }

    public String toString() {
        return String.valueOf(getValueContainer());
    }

    public Iterator<IdItem> usedIds() {
        return EmptyIterator.of();
    }

    public DexValueBlock(DexValueType<?> dexValueType) {
        this(null, dexValueType);
    }
}
