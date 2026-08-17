package com.reandroid.dex.value;

import com.reandroid.dex.data.EncodedArray;
import com.reandroid.dex.id.IdItem;
import com.reandroid.dex.key.ArrayValueKey;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.dex.smali.model.SmaliValue;
import com.reandroid.dex.smali.model.SmaliValueArray;
import com.reandroid.utils.collection.IterableIterator;
import java.io.IOException;
import java.util.Iterator;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ArrayValue extends DexValueBlock<EncodedArray> implements Iterable<DexValueBlock<?>> {
    public ArrayValue() {
        super(new EncodedArray(), DexValueType.ARRAY);
    }

    public void add(DexValueBlock<?> dexValueBlock) {
        getValueContainer().add(dexValueBlock);
    }

    @Override // com.reandroid.dex.value.DexValueBlock, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        smaliWriter.append('{');
        smaliWriter.indentPlus();
        int size = size();
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                smaliWriter.append(',');
            }
            smaliWriter.newLine();
            get(i).append(smaliWriter);
        }
        smaliWriter.indentMinus();
        if (size > 0) {
            smaliWriter.newLine();
        }
        smaliWriter.append('}');
    }

    public void clear() {
        getValueContainer().clear();
    }

    public Iterator<DexValueBlock<?>> clonedIterator() {
        return getValueContainer().clonedIterator();
    }

    public <T1 extends DexValueBlock<?>> T1 createNext(DexValueType<T1> dexValueType) {
        T1 t1 = (T1) dexValueType.newInstance();
        add(t1);
        return t1;
    }

    @Override // com.reandroid.dex.value.DexValueBlock
    public void fromSmali(SmaliValue smaliValue) {
        for (SmaliValue smaliValue2 : (SmaliValueArray) smaliValue) {
            createNext(smaliValue2.getValueType()).fromSmali(smaliValue2);
        }
    }

    public DexValueBlock<?> get(int i) {
        return getValueContainer().get(i);
    }

    @Override // com.reandroid.dex.value.DexValueBlock
    public TypeKey getDataTypeKey() {
        DexValueBlock<?> dexValueBlock = get(0);
        if (dexValueBlock == null) {
            return TypeKey.OBJECT.setArrayDimension(1);
        }
        TypeKey dataTypeKey = dexValueBlock.getDataTypeKey();
        return dataTypeKey.setArrayDimension(dataTypeKey.getArrayDimension() + 1);
    }

    public Key getKey(int i) {
        DexValueBlock<?> dexValueBlock = get(i);
        if (dexValueBlock != null) {
            return dexValueBlock.getKey();
        }
        return null;
    }

    @Override // com.reandroid.dex.value.DexValueBlock
    public DexValueType<?> getValueType() {
        return DexValueType.ARRAY;
    }

    @Override // java.lang.Iterable
    public Iterator<DexValueBlock<?>> iterator() {
        return getValueContainer().iterator();
    }

    @Override // com.reandroid.dex.value.DexValueBlock
    public void merge(DexValueBlock<?> dexValueBlock) {
        super.merge(dexValueBlock);
        getValueContainer().merge(((ArrayValue) dexValueBlock).getValueContainer());
    }

    public boolean remove(int i) {
        return getValueContainer().remove(i);
    }

    public boolean removeIf(Predicate<? super DexValueBlock<?>> predicate) {
        return getValueContainer().removeIf(predicate);
    }

    @Override // com.reandroid.dex.value.DexValueBlock
    public void replaceKeys(Key key, Key key2) {
        Iterator<DexValueBlock<?>> it = iterator();
        while (it.hasNext()) {
            it.next().replaceKeys(key, key2);
        }
    }

    @Override // com.reandroid.dex.value.DexValueBlock, com.reandroid.dex.key.KeyReference
    public void setKey(Key key) {
        getValueContainer().setKey(key);
    }

    public int size() {
        return getValueContainer().size();
    }

    @Override // com.reandroid.dex.value.DexValueBlock
    public Iterator<IdItem> usedIds() {
        return new IterableIterator<DexValueBlock<?>, IdItem>(iterator()) { // from class: com.reandroid.dex.value.ArrayValue.1
            public Iterator<IdItem> iterator(DexValueBlock<?> dexValueBlock) {
                return dexValueBlock.usedIds();
            }
        };
    }

    public <T1 extends DexValueBlock<?>> Iterator<T1> iterator(Class<T1> cls) {
        return getValueContainer().iterator(cls);
    }

    public boolean remove(DexValueBlock<?> dexValueBlock) {
        return getValueContainer().remove(dexValueBlock);
    }

    public <T1 extends DexValueBlock<?>> Iterator<T1> iterator(Class<T1> cls, Predicate<? super T1> predicate) {
        return getValueContainer().iterator(cls, predicate);
    }

    @Override // com.reandroid.dex.value.DexValueBlock, com.reandroid.dex.key.KeyItem
    public ArrayValueKey getKey() {
        return getValueContainer().getKey();
    }
}
