package com.reandroid.dex.data;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.Creator;
import com.reandroid.arsc.container.BlockList;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.dex.base.Ule128Item;
import com.reandroid.dex.id.IdItem;
import com.reandroid.dex.key.ArrayKey;
import com.reandroid.dex.key.ArrayValueKey;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.KeyReference;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.dex.value.DexValueBlock;
import com.reandroid.dex.value.DexValueType;
import com.reandroid.dex.value.NullValue;
import com.reandroid.dex.value.SectionValue;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.collection.InstanceIterator;
import com.reandroid.utils.collection.IterableIterator;
import java.io.IOException;
import java.util.Iterator;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class EncodedArray extends DataItem implements KeyReference, Iterable<DexValueBlock<?>> {
    private static final Creator<DexValueBlock<?>> CREATOR = new Creator() { // from class: ib4
        @Override // com.reandroid.arsc.base.Creator
        public final Block newInstance() {
            return NullValue.PLACE_HOLDER;
        }
    };
    private final BlockList<DexValueBlock<?>> valueList;
    private final Ule128Item valuesCountReference;

    public EncodedArray() {
        super(2);
        Block ule128Item = new Ule128Item();
        this.valuesCountReference = ule128Item;
        BlockList<DexValueBlock<?>> blockList = new BlockList<>();
        this.valueList = blockList;
        blockList.setCreator(CREATOR);
        addChildBlock(0, ule128Item);
        addChildBlock(1, blockList);
    }

    private BlockList<DexValueBlock<?>> getValueList() {
        return this.valueList;
    }

    public void add(Key key) {
        DexValueBlock<?> dexValueBlockNewInstance = DexValueType.forKey(key).newInstance();
        add(dexValueBlockNewInstance);
        dexValueBlockNewInstance.setKey(key);
    }

    public void clear() {
        getValueList().clearChildes();
    }

    public Iterator<DexValueBlock<?>> clonedIterator() {
        return getValueList().clonedIterator();
    }

    @Override // com.reandroid.dex.data.DataItem
    public void copyFrom(DataItem dataItem) {
        merge((EncodedArray) dataItem);
    }

    public void ensureSize(int i) {
        if (i > size()) {
            setSize(i);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        EncodedArray encodedArray = (EncodedArray) obj;
        int size = size();
        if (size != encodedArray.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!ObjectsUtil.equals(get(i), encodedArray.get(i))) {
                return false;
            }
        }
        return true;
    }

    public DexValueBlock<?> get(int i) {
        return getValueList().get(i);
    }

    @Override // com.reandroid.dex.data.DataItem, com.reandroid.dex.common.SectionItem, com.reandroid.dex.key.KeyItem
    public ArrayValueKey getKey() {
        int size = size();
        Key[] keyArr = new Key[size];
        for (int i = 0; i < size; i++) {
            keyArr[i] = get(i).getKey();
        }
        return (ArrayValueKey) checkKey(ArrayValueKey.of(keyArr));
    }

    public <T1 extends DexValueBlock<?>> T1 getOrCreate(DexValueType<T1> dexValueType, int i) {
        BlockList<DexValueBlock<?>> valueList = getValueList();
        valueList.ensureSize(i + 1);
        T1 t1 = valueList.get(i);
        if (t1 != null && t1 != NullValue.PLACE_HOLDER && t1.getValueType() == dexValueType) {
            return t1;
        }
        T1 t2 = (T1) dexValueType.newInstance();
        valueList.set(i, t2);
        return t2;
    }

    @Override // com.reandroid.dex.common.SectionItem
    public SectionType<EncodedArray> getSectionType() {
        return SectionType.ENCODED_ARRAY;
    }

    public Key getValueKey(int i) {
        DexValueBlock<?> dexValueBlock = get(i);
        if (dexValueBlock != null) {
            return dexValueBlock.getKey();
        }
        return null;
    }

    public int hashCode() {
        int size = size();
        int iHashCode = 1;
        for (int i = 0; i < size; i++) {
            iHashCode = (iHashCode * 31) + get(i).hashCode();
        }
        return iHashCode;
    }

    @Override // java.lang.Iterable
    public Iterator<DexValueBlock<?>> iterator() {
        return getValueList().iterator();
    }

    public void merge(EncodedArray encodedArray) {
        int size = encodedArray.size();
        getValueList().ensureCapacity(size);
        for (int i = 0; i < size; i++) {
            DexValueBlock<?> dexValueBlock = encodedArray.get(i);
            getOrCreate(dexValueBlock.getValueType(), i).merge(dexValueBlock);
        }
    }

    @Override // com.reandroid.dex.common.SectionItemContainer
    public void onReadBytes(BlockReader blockReader) throws IOException {
        this.valuesCountReference.onReadBytes(blockReader);
        BlockList<DexValueBlock<?>> valueList = getValueList();
        int i = this.valuesCountReference.get();
        valueList.ensureCapacity(i);
        for (int i2 = 0; i2 < i; i2++) {
            DexValueBlock<?> dexValueBlockCreate = DexValueType.create(blockReader);
            valueList.add(dexValueBlockCreate);
            dexValueBlockCreate.onReadBytes(blockReader);
        }
    }

    @Override // com.reandroid.dex.data.DataItem, com.reandroid.dex.common.SectionItemContainer
    public void onRefreshed() {
        super.onRefreshed();
        this.valuesCountReference.set(size());
    }

    public boolean remove(int i) {
        return getValueList().remove(i) != null;
    }

    public boolean removeIf(Predicate<? super DexValueBlock<?>> predicate) {
        return getValueList().removeIf(predicate);
    }

    public void set(int i, DexValueBlock<?> dexValueBlock) {
        ensureSize(i + 1);
        getValueList().set(i, dexValueBlock);
    }

    @Override // com.reandroid.dex.key.KeyReference
    public void setKey(Key key) {
        ArrayKey arrayKey = (ArrayKey) key;
        clear();
        int size = arrayKey.size();
        for (int i = 0; i < size; i++) {
            add(arrayKey.get(i));
        }
    }

    public void setSize(int i) {
        if (i < 0) {
            b1e.a("Invalid size: ", i);
            return;
        }
        this.valuesCountReference.set(i);
        BlockList<DexValueBlock<?>> valueList = getValueList();
        if (i == 0) {
            valueList.setSize(0);
            return;
        }
        int size = valueList.size();
        if (i <= size) {
            if (i < size) {
                valueList.setSize(i);
            }
        } else {
            NullValue nullValue = NullValue.PLACE_HOLDER;
            int i2 = i - size;
            valueList.ensureCapacity(i2);
            for (int i3 = 0; i3 < i2; i3++) {
                valueList.add(nullValue);
            }
        }
    }

    public int size() {
        return getValueList().getCount();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Iterator<DexValueBlock<?>> it = iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (z) {
                sb.append(", ");
            }
            sb.append(it.next());
            z = true;
        }
        sb.append(']');
        return sb.toString();
    }

    @Override // com.reandroid.dex.data.DataItem, com.reandroid.dex.common.IdUsageIterator
    public Iterator<IdItem> usedIds() {
        return new IterableIterator<DexValueBlock<?>, IdItem>(iterator()) { // from class: com.reandroid.dex.data.EncodedArray.1
            public Iterator<IdItem> iterator(DexValueBlock<?> dexValueBlock) {
                return dexValueBlock.usedIds();
            }
        };
    }

    public <T1 extends DexValueBlock<?>> Iterator<T1> iterator(Class<T1> cls) {
        return InstanceIterator.of(iterator(), cls);
    }

    public <T1 extends DexValueBlock<?>> Iterator<T1> iterator(Class<T1> cls, Predicate<? super T1> predicate) {
        return InstanceIterator.of(iterator(), cls, predicate);
    }

    public Iterator<DexValueBlock<?>> iterator(int i, int i2) {
        return getValueList().iterator(i, i2);
    }

    public boolean remove(DexValueBlock<?> dexValueBlock) {
        return getValueList().remove(dexValueBlock);
    }

    public void add(DexValueBlock<?> dexValueBlock) {
        getValueList().add(dexValueBlock);
    }

    public <T1 extends IdItem> SectionValue<T1> getOrCreate(SectionType<T1> sectionType, int i) {
        return (SectionValue) getOrCreate(DexValueType.get(sectionType), i);
    }
}
