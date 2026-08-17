package com.reandroid.dex.data;

import com.reandroid.arsc.base.Creator;
import com.reandroid.arsc.container.CountedBlockList;
import com.reandroid.arsc.item.IntegerItem;
import com.reandroid.dex.base.DexException;
import com.reandroid.dex.base.DexPositionAlign;
import com.reandroid.dex.common.SectionTool;
import com.reandroid.dex.data.ShortIdList;
import com.reandroid.dex.id.IdItem;
import com.reandroid.dex.key.ArrayKey;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.KeyList;
import com.reandroid.dex.reference.ShortIdReference;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.NumbersUtil;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.collection.ComputeIterator;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ShortIdList<T extends IdItem> extends DataItem implements Comparable<ShortIdList<T>> {
    private final DexPositionAlign positionAlign;
    private final CountedBlockList<ShortIdReference<T>> referenceList;

    public static class ReferenceCreator<T extends IdItem> implements Creator<ShortIdReference<T>> {
        private final SectionType<T> sectionType;
        private final int usageType;

        public ReferenceCreator(SectionType<T> sectionType, int i) {
            this.sectionType = sectionType;
            this.usageType = i;
        }

        @Override // com.reandroid.arsc.base.Creator
        public ShortIdReference<T> newInstance() {
            return new ShortIdReference<>(this.sectionType, this.usageType);
        }
    }

    public ShortIdList(SectionType<T> sectionType, int i) {
        super(3);
        IntegerItem integerItem = new IntegerItem();
        CountedBlockList<ShortIdReference<T>> countedBlockList = new CountedBlockList<>(new ReferenceCreator(sectionType, i), integerItem);
        this.referenceList = countedBlockList;
        DexPositionAlign dexPositionAlign = new DexPositionAlign();
        this.positionAlign = dexPositionAlign;
        addChildBlock(0, integerItem);
        addChildBlock(1, countedBlockList);
        addChildBlock(2, dexPositionAlign);
    }

    public static /* synthetic */ boolean b(IdItem idItem, IdItem idItem2) {
        return idItem2 == idItem;
    }

    private ShortIdReference<T> createNext() {
        return this.referenceList.createNext();
    }

    private void ensureSize(int i) {
        this.referenceList.ensureSize(i);
    }

    private ShortIdReference<T> getOrCreateReference(int i) {
        ensureSize(i + 1);
        return this.referenceList.get(i);
    }

    private ShortIdReference<T> getReference(int i) {
        return this.referenceList.get(i);
    }

    public static /* synthetic */ boolean p(IdItem idItem) {
        return idItem == null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.reandroid.dex.base.DexException */
    public T addNewItem(Key key) throws DexException {
        ShortIdReference<T> shortIdReferenceCreateNext = createNext();
        shortIdReferenceCreateNext.setKey(key);
        return (T) shortIdReferenceCreateNext.getItem();
    }

    public void clear() {
        setSize(0);
    }

    @Override // java.lang.Comparable
    public int compareTo(ShortIdList<T> shortIdList) {
        if (shortIdList == this) {
            return 0;
        }
        int size = size();
        int size2 = shortIdList.size();
        int iMin = NumbersUtil.min(size, size2);
        for (int i = 0; i < iMin; i++) {
            int iCompareIdx = SectionTool.compareIdx(getItem(i), shortIdList.getItem(i));
            if (iCompareIdx != 0) {
                return iCompareIdx;
            }
        }
        return CompareUtil.compare(size, size2);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ShortIdList shortIdList = (ShortIdList) obj;
        int size = size();
        if (size != shortIdList.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!ObjectsUtil.equals(getItem(i), shortIdList.getItem(i))) {
                return false;
            }
        }
        return true;
    }

    public T getItem(int i) {
        ShortIdReference<T> reference = getReference(i);
        if (reference != null) {
            return (T) reference.getItem();
        }
        return null;
    }

    public Key getItemKey(int i) {
        ShortIdReference<T> reference = getReference(i);
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

    public Iterator<T> iterator() {
        return ComputeIterator.of(this.referenceList.iterator(), new Function() { // from class: bad
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((ShortIdReference) obj).getItem();
            }
        });
    }

    @Override // com.reandroid.dex.common.SectionItemContainer
    public void onPreRefresh() {
        super.onPreRefresh();
        removeNulls();
    }

    public boolean remove(final T t) {
        return removeIf(new Predicate() { // from class: cad
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ShortIdList.b(t, (IdItem) obj);
            }
        });
    }

    public boolean removeIf(final Predicate<? super T> predicate) {
        return this.referenceList.removeIf(new Predicate() { // from class: aad
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return predicate.test(((ShortIdReference) obj).getItem());
            }
        });
    }

    public void removeNulls() {
        removeIf(new Predicate() { // from class: dad
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ShortIdList.p((IdItem) obj);
            }
        });
    }

    @Override // com.reandroid.dex.data.DataItem, com.reandroid.dex.common.SectionItem
    public void removeSelf() {
        clear();
        super.removeSelf();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.reandroid.dex.base.DexException */
    public T setItemKeyAt(int i, Key key) throws DexException {
        ShortIdReference<T> reference = getReference(i);
        if (reference == null) {
            return null;
        }
        reference.setKey(key);
        return (T) reference.getItem();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.reandroid.dex.base.DexException */
    public void setKey(Key key) throws DexException {
        KeyList<?> key2 = getKey();
        KeyList keyList = (KeyList) key;
        if (KeyList.equalsIgnoreEmpty(keyList, (KeyList) ObjectsUtil.cast(key2))) {
            return;
        }
        int size = keyList.size();
        setSize(size);
        for (int i = 0; i < size; i++) {
            getReference(i).setKey(keyList.get(i));
        }
        keyChanged(key2);
    }

    public void setSize(int i) {
        this.referenceList.setSize(i);
    }

    public int size() {
        return this.referenceList.size();
    }

    public boolean sort(final Comparator<? super T> comparator) {
        return this.referenceList.sort(new Comparator() { // from class: ead
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return comparator.compare(((ShortIdReference) obj).getItem(), ((ShortIdReference) obj2).getItem());
            }
        });
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.reandroid.dex.base.DexException */
    public void addNewItem(T t) throws DexException {
        createNext().setItem((IdItem) t);
    }
}
