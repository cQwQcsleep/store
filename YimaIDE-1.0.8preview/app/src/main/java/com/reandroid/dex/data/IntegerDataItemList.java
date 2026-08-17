package com.reandroid.dex.data;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.Creator;
import com.reandroid.arsc.container.CountedBlockList;
import com.reandroid.arsc.item.IntegerItem;
import com.reandroid.dex.base.DexPositionAlign;
import com.reandroid.dex.data.DataItem;
import com.reandroid.dex.data.IntegerDataItemList;
import com.reandroid.dex.key.ArrayKey;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.KeyList;
import com.reandroid.dex.reference.IntegerDataReference;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.collection.ComputeIterator;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class IntegerDataItemList<T extends DataItem> extends DataItem implements Iterable<T> {
    private final DexPositionAlign positionAlign;
    private final CountedBlockList<IntegerDataReference<T>> referenceList;

    public static class ReferenceCreator<T extends DataItem> implements Creator<IntegerDataReference<T>> {
        private final SectionType<T> sectionType;
        private final int usageType;

        public ReferenceCreator(SectionType<T> sectionType, int i) {
            this.sectionType = sectionType;
            this.usageType = i;
        }

        @Override // com.reandroid.arsc.base.Creator
        public IntegerDataReference<T> newInstance() {
            return new IntegerDataReference<>(this.sectionType, this.usageType);
        }
    }

    public IntegerDataItemList(SectionType<T> sectionType, int i, DexPositionAlign dexPositionAlign) {
        super(3);
        this.positionAlign = dexPositionAlign;
        IntegerItem integerItem = new IntegerItem();
        CountedBlockList<IntegerDataReference<T>> countedBlockList = new CountedBlockList<>(new ReferenceCreator(sectionType, i), integerItem);
        this.referenceList = countedBlockList;
        addChildBlock(0, integerItem);
        addChildBlock(1, countedBlockList);
        addChildBlock(2, dexPositionAlign);
    }

    public static /* synthetic */ boolean b(IntegerDataReference integerDataReference) {
        return integerDataReference.getItem() == null;
    }

    private IntegerDataReference<T> createNext() {
        return this.referenceList.createNext();
    }

    private void ensureSize(int i) {
        this.referenceList.ensureSize(i);
    }

    private IntegerDataReference<T> getOrCreateReference(int i) {
        ensureSize(i + 1);
        return this.referenceList.get(i);
    }

    private IntegerDataReference<T> getReference(int i) {
        return this.referenceList.get(i);
    }

    public static /* synthetic */ boolean k(DataItem dataItem, DataItem dataItem2) {
        return dataItem2 == dataItem;
    }

    public T addNewItem(Key key) {
        IntegerDataReference<T> integerDataReferenceCreateNext = createNext();
        integerDataReferenceCreateNext.setKey(key);
        return (T) integerDataReferenceCreateNext.getItem();
    }

    public void clear() {
        setSize(0);
    }

    public void clearAt(int i) {
        IntegerDataReference<T> reference = getReference(i);
        if (reference != null) {
            reference.setItem((DataItem) null);
        }
    }

    @Override // com.reandroid.dex.common.SectionItem, com.reandroid.dex.common.EditableItem
    public void editInternal(Block block) {
        int size = size();
        for (int i = 0; i < size; i++) {
            getReference(i).editInternal(this);
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        IntegerDataItemList integerDataItemList = (IntegerDataItemList) obj;
        int size = size();
        if (size != integerDataItemList.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!ObjectsUtil.equals(getItem(i), integerDataItemList.getItem(i))) {
                return false;
            }
        }
        return true;
    }

    public T getItem(int i) {
        IntegerDataReference<T> reference = getReference(i);
        if (reference != null) {
            return (T) reference.getItem();
        }
        return null;
    }

    public Key getItemKey(int i) {
        IntegerDataReference<T> reference = getReference(i);
        if (reference != null) {
            return reference.getKey();
        }
        return null;
    }

    public void getItemKeys(Key[] keyArr) {
        int length = keyArr.length;
        for (int i = 0; i < length; i++) {
            keyArr[i] = getItemKey(i);
        }
    }

    @Override // com.reandroid.dex.data.DataItem, com.reandroid.dex.common.SectionItem, com.reandroid.dex.key.KeyItem
    public KeyList<?> getKey() {
        Key[] keyArr = new Key[size()];
        getItemKeys(keyArr);
        return ArrayKey.create(keyArr);
    }

    public T getOrCreateAt(int i) {
        return (T) getOrCreateReference(i).getOrCreate();
    }

    public DexPositionAlign getPositionAlign() {
        return this.positionAlign;
    }

    public int hashCode() {
        int size = size();
        int iHash = 1;
        for (int i = 0; i < size; i++) {
            iHash = (iHash * 31) + ObjectsUtil.hash(getItem(i));
        }
        return iHash;
    }

    public boolean isEmpty() {
        return !iterator().hasNext();
    }

    @Override // java.lang.Iterable
    public Iterator<T> iterator() {
        return ComputeIterator.of(this.referenceList.iterator(), new Function() { // from class: bt6
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((IntegerDataReference) obj).getItem();
            }
        });
    }

    @Override // com.reandroid.dex.common.SectionItemContainer
    public void onPreRefresh() {
        super.onPreRefresh();
        removeNulls();
    }

    public void remove(final T t) {
        removeIf(new Predicate() { // from class: ct6
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return IntegerDataItemList.k(t, (DataItem) obj);
            }
        });
    }

    public boolean removeIf(final Predicate<? super T> predicate) {
        return this.referenceList.removeIf(new Predicate() { // from class: zs6
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return predicate.test(((IntegerDataReference) obj).getItem());
            }
        });
    }

    public void removeNulls() {
        this.referenceList.removeIf(new Predicate() { // from class: ys6
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return IntegerDataItemList.b((IntegerDataReference) obj);
            }
        });
    }

    @Override // com.reandroid.dex.data.DataItem, com.reandroid.dex.common.SectionItem
    public void removeSelf() {
        clear();
        super.removeSelf();
    }

    public T setItemKeyAt(int i, Key key) {
        IntegerDataReference<T> orCreateReference = getOrCreateReference(i);
        orCreateReference.setKey(key);
        return (T) orCreateReference.getItem();
    }

    public void setKey(Key key) {
        KeyList keyList = (KeyList) key;
        int size = keyList.size();
        setSize(size);
        for (int i = 0; i < size; i++) {
            getReference(i).setKey(keyList.get(i));
        }
    }

    public void setSize(int i) {
        this.referenceList.setSize(i);
    }

    public int size() {
        return this.referenceList.size();
    }

    public boolean sort(final Comparator<? super T> comparator) {
        removeNulls();
        return this.referenceList.sort(new Comparator() { // from class: at6
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return comparator.compare(((IntegerDataReference) obj).getItem(), ((IntegerDataReference) obj2).getItem());
            }
        });
    }

    public void addNewItem(T t) {
        createNext().setItem((DataItem) t);
    }

    public T addNewItem() {
        return (T) createNext().getOrCreate();
    }
}
