package com.reandroid.dex.sections;

import com.reandroid.dex.base.IntegerPair;
import com.reandroid.dex.data.HiddenApiRestrictions;
import com.reandroid.dex.id.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ClassIdSection extends IdSection<ClassId> {
    public ClassIdSection(IntegerPair integerPair) {
        super(SectionType.CLASS_ID, new ClassIdSectionArray(integerPair));
    }

    @Override // com.reandroid.dex.sections.Section
    public ClassIdSectionArray getItemArray() {
        return (ClassIdSectionArray) super.getItemArray();
    }

    @Override // com.reandroid.dex.sections.Section
    public void onRefreshed() {
        HiddenApiRestrictions hiddenApiRestrictions;
        super.onRefreshed();
        Section section = getSectionList().getSection(SectionType.HIDDEN_API);
        if (section == null || (hiddenApiRestrictions = (HiddenApiRestrictions) section.get(0)) == null) {
            return;
        }
        hiddenApiRestrictions.removeIfAllNoRestrictions();
    }
}
