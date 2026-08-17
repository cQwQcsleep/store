package com.reandroid.dex.sections;

import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.arsc.item.NumberIntegerReference;
import com.reandroid.dex.base.IntegerPair;
import com.reandroid.dex.base.ParallelIntegerPair;
import com.reandroid.dex.base.ParallelReference;
import com.reandroid.dex.sections.SpecialItem;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SpecialSection<T extends SpecialItem> extends Section<T> {
    public SpecialSection(IntegerReference integerReference, SectionType<T> sectionType) {
        this(IntegerPair.of(new NumberIntegerReference(), new ParallelReference(integerReference)), sectionType);
    }

    private void updateItemOffsets(int i) {
        updateNextSection(getItemArray().updatePositionedItemOffsets(i));
    }

    @Override // com.reandroid.dex.sections.Section
    public int clearUnused() {
        return 0;
    }

    @Override // com.reandroid.dex.sections.Section
    public void clearUsageTypes() {
    }

    public ParallelIntegerPair getCountAndOffset() {
        return getItemArray().getCountAndOffset();
    }

    @Override // com.reandroid.dex.sections.Section
    public SpecialSectionArray<T> getItemArray() {
        return (SpecialSectionArray) super.getItemArray();
    }

    @Override // com.reandroid.dex.sections.Section
    public void onRefreshed(int i) {
        updateItemOffsets(i);
    }

    public SpecialSection(IntegerPair integerPair, SectionType<T> sectionType) {
        super(sectionType, new SpecialSectionArray(new ParallelIntegerPair(integerPair), sectionType));
    }
}
