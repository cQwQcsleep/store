package com.reandroid.dex.sections;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.OffsetSupplier;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.dex.base.DexArraySupplier;
import com.reandroid.dex.base.DexPositionAlign;
import com.reandroid.dex.base.FixedDexContainer;
import com.reandroid.dex.base.IntegerPair;
import com.reandroid.dex.base.UsageMarker;
import com.reandroid.dex.common.FullRefresh;
import com.reandroid.dex.common.SectionItem;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.pool.DexSectionPool;
import com.reandroid.dex.sections.Section;
import com.reandroid.utils.CompareUtil;
import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Section<T extends SectionItem> extends FixedDexContainer implements DexArraySupplier<T>, OffsetSupplier, Iterable<T>, FullRefresh {
    private DexSectionPool<T> dexSectionPool;
    private final SectionArray<T> itemArray;
    private final DexPositionAlign sectionAlign;
    private final SectionType<T> sectionType;

    public Section(SectionType<T> sectionType, SectionArray<T> sectionArray) {
        super(2);
        this.sectionType = sectionType;
        this.itemArray = sectionArray;
        DexPositionAlign dexPositionAlignFixedAligner = fixedAligner(sectionType.sectionAlignment());
        this.sectionAlign = dexPositionAlignFixedAligner;
        addChild(0, dexPositionAlignFixedAligner);
        addChild(1, sectionArray);
    }

    public static /* synthetic */ boolean b(SectionItem sectionItem) {
        return sectionItem.getUsageType() == UsageMarker.USAGE_NONE;
    }

    private static DexPositionAlign fixedAligner(final int i) {
        return new DexPositionAlign(i) { // from class: com.reandroid.dex.sections.Section.1
            public void setAlignment(int i2) {
                super.setAlignment(i);
            }

            public void setSize(int i2) {
                if (i == 0) {
                    i2 = 0;
                }
                super.setSize(i2);
            }
        };
    }

    public void add(T t) {
        getItemArray().add(t);
    }

    public Iterator<T> arrayIterator() {
        return getItemArray().arrayIterator();
    }

    public void clear() {
        clearPoolMap();
        getItemArray().clear();
    }

    public void clearPoolMap() {
        DexSectionPool<T> loadedPool = getLoadedPool();
        if (loadedPool != null) {
            loadedPool.clear();
            this.dexSectionPool = null;
        }
    }

    public int clearUnused() {
        int count = getCount();
        removeEntries(new Predicate() { // from class: h1d
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Section.b((SectionItem) obj);
            }
        });
        return count - getCount();
    }

    public void clearUsageTypes() {
        UsageMarker.clearUsageTypes(iterator());
    }

    public Iterator<T> clonedIterator() {
        return getItemArray().clonedIterator();
    }

    public int compareOffset(Section<?> section) {
        if (section == null) {
            return 1;
        }
        return CompareUtil.compare(getOffset(), section.getOffset());
    }

    public boolean contains(Key key) {
        return getPool().contains(key);
    }

    public T createItem() {
        return getItemArray().createNext();
    }

    public DexSectionPool<T> createPool() {
        return new DexSectionPool<>(this);
    }

    public T get(Key key) {
        return getPool().get(key);
    }

    public Iterator<T> getAll(Key key) {
        return getPool().getAll(key);
    }

    @Override // com.reandroid.common.CountSupplier
    public int getCount() {
        return getItemArray().getCount();
    }

    public SectionArray<T> getItemArray() {
        return this.itemArray;
    }

    public DexSectionPool<T> getLoadedPool() {
        return this.dexSectionPool;
    }

    public Section<?> getNextSection() {
        int iIndexOf;
        SectionList sectionList = getSectionList();
        if (sectionList == null || (iIndexOf = sectionList.indexOf(this)) < 0) {
            return null;
        }
        return sectionList.get(iIndexOf + 1);
    }

    public int getOffset() {
        return getOffsetReference().get();
    }

    @Override // com.reandroid.dex.base.DexArraySupplier
    public IntegerReference getOffsetReference() {
        return getItemArray().getOffsetReference();
    }

    public T getOrCreate(Key key) {
        return (T) getPool().getOrCreate(key);
    }

    public DexSectionPool<T> getPool() {
        DexSectionPool<T> dexSectionPool = this.dexSectionPool;
        if (dexSectionPool != null) {
            return dexSectionPool;
        }
        DexSectionPool<T> dexSectionPoolCreatePool = createPool();
        this.dexSectionPool = dexSectionPoolCreatePool;
        dexSectionPoolCreatePool.load();
        return dexSectionPoolCreatePool;
    }

    public Section<?> getPreviousSection() {
        int iIndexOf;
        SectionList sectionList = getSectionList();
        if (sectionList == null || (iIndexOf = sectionList.indexOf(this)) < 0) {
            return null;
        }
        return sectionList.get(iIndexOf - 1);
    }

    public T getSectionItem(Key key) {
        return getPool().get(key);
    }

    public SectionList getSectionList() {
        return (SectionList) getParent(SectionList.class);
    }

    public SectionType<T> getSectionType() {
        return this.sectionType;
    }

    public boolean isEmpty() {
        return getCount() == 0;
    }

    @Override // com.reandroid.dex.base.FixedDexContainer
    public boolean isValidOffset(int i) {
        if (i == 0) {
            return getSectionType() == SectionType.HEADER;
        }
        return i > 0;
    }

    @Override // com.reandroid.dex.base.DexArraySupplier, java.lang.Iterable
    public Iterator<T> iterator() {
        return getItemArray().iterator();
    }

    public boolean keyChanged(SectionItem sectionItem, Key key) {
        DexSectionPool<T> loadedPool = getLoadedPool();
        if (loadedPool != null) {
            return loadedPool.updateKey(key, sectionItem.getKey(), sectionItem);
        }
        return false;
    }

    public void onPreRefresh() {
        super/*com.reandroid.arsc.base.BlockContainer*/.onPreRefresh();
        removeEntries(new Predicate() { // from class: g1d
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((SectionItem) obj).isBlank();
            }
        });
    }

    @Override // com.reandroid.dex.base.FixedDexContainer
    public void onReadBytes(BlockReader blockReader) throws IOException {
        this.sectionAlign.setAlignment(0);
        super.onReadBytes(blockReader);
    }

    public void onRefreshed() {
        int offset = getOffset();
        int iAlign = offset + this.sectionAlign.align(offset);
        getOffsetReference().set(iAlign);
        onRefreshed(iAlign);
        clearPoolMap();
    }

    public void onRemove(SectionList sectionList) {
        clear();
        sectionList.getMapList().remove((SectionType<?>) getSectionType());
        setParent((Block) null);
        setIndex(-1);
    }

    public void onRemoving(T t) {
        DexSectionPool<T> dexSectionPool = this.dexSectionPool;
        if (dexSectionPool != null) {
            dexSectionPool.remove((SectionItem) t);
        }
    }

    @Override // com.reandroid.dex.common.FullRefresh
    public void refreshFull() {
        clearPoolMap();
        getItemArray().refreshFull();
        sort();
        refresh();
    }

    public boolean remove(Key key) {
        return false;
    }

    public boolean removeEntries(Predicate<? super T> predicate) {
        return getItemArray().removeIf(predicate);
    }

    public void removeSelf() {
        SectionList sectionList = getSectionList();
        if (sectionList != null) {
            sectionList.remove(this);
        }
    }

    public boolean removeWithKeys(Predicate<? super Key> predicate) {
        return false;
    }

    public boolean sort() throws ClassCastException {
        if (getItemArray().getFirst() instanceof Comparable) {
            return sort(CompareUtil.getComparatorUnchecked());
        }
        return false;
    }

    public String toString() {
        return getSectionType() + ", offset = " + getOffset() + ", count = " + getCount();
    }

    public void updateNextSection(int i) {
        Section<?> nextSection = getNextSection();
        if (nextSection != null) {
            nextSection.getOffsetReference().set(i);
        }
    }

    public Iterator<T> iterator(Predicate<? super T> predicate) {
        return getItemArray().iterator(predicate);
    }

    public T getSectionItem(int i) {
        return null;
    }

    @Override // com.reandroid.common.ArraySupplier
    public T get(int i) {
        return getItemArray().get(i);
    }

    public boolean sort(Comparator<? super T> comparator) {
        return getItemArray().sort(comparator);
    }

    public void onRefreshed(int i) {
        updateNextSection(i + getItemArray().countBytes());
    }

    public Section(IntegerPair integerPair, SectionType<T> sectionType) {
        this(sectionType, new SectionArray(integerPair, sectionType.getCreator()));
    }
}
