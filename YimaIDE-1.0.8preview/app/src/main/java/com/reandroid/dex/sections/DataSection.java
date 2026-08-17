package com.reandroid.dex.sections;

import com.reandroid.dex.base.IntegerPair;
import com.reandroid.dex.data.DataItem;
import com.reandroid.dex.pool.DexSectionPool;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DataSection<T extends DataItem> extends Section<T> {
    public DataSection(IntegerPair integerPair, SectionType<T> sectionType) {
        super(sectionType, new DataSectionArray(integerPair, sectionType.getCreator()));
    }

    private int estimateLastOffset() {
        DataItem last = getItemArray().getLast();
        if (last != null) {
            return last.getOffset() + last.countBytes();
        }
        int offset = getOffset() + countBytes();
        return offset == 0 ? estimateMainOffset() : offset;
    }

    private int estimateMainOffset() {
        int offset = getOffset();
        if (offset != 0) {
            return offset;
        }
        Section<?> nextSection = getNextSection();
        if (nextSection == null && (nextSection = getPreviousSection()) != null) {
            offset = nextSection.countBytes();
        }
        if (nextSection != null) {
            offset += nextSection.getOffset();
        }
        if (offset == 0) {
            offset = 1;
        }
        getOffsetReference().set(offset);
        return offset;
    }

    private void updateItemOffsets(int i) {
        updateNextSection(getItemArray().updatePositionedItemOffsets(i));
    }

    @Override // com.reandroid.dex.sections.Section
    public int clearUnused() {
        return super.clearUnused();
    }

    @Override // com.reandroid.dex.sections.Section
    public T createItem() {
        int iEstimateLastOffset = estimateLastOffset();
        T tCreateNext = getItemArray().createNext();
        tCreateNext.setPosition(iEstimateLastOffset);
        return tCreateNext;
    }

    @Override // com.reandroid.dex.sections.Section
    public DexSectionPool<T> createPool() {
        return new DexSectionPool<>(this);
    }

    @Override // com.reandroid.dex.sections.Section
    public DataSectionArray<T> getItemArray() {
        return (DataSectionArray) super.getItemArray();
    }

    @Override // com.reandroid.dex.sections.Section
    public DexSectionPool<T> getPool() {
        return super.getPool();
    }

    @Override // com.reandroid.dex.sections.Section
    public T getSectionItem(int i) {
        return (T) getItemArray().getAt(i);
    }

    @Override // com.reandroid.dex.sections.Section
    public void onRefreshed(int i) {
        updateItemOffsets(i);
    }

    public DataSection(SectionType<T> sectionType, DataSectionArray<T> dataSectionArray) {
        super(sectionType, dataSectionArray);
    }
}
