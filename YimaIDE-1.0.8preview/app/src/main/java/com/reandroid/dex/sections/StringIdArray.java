package com.reandroid.dex.sections;

import com.reandroid.arsc.base.BlockCreator;
import com.reandroid.arsc.base.Creator;
import com.reandroid.dex.base.IntegerPair;
import com.reandroid.dex.data.StringData;
import com.reandroid.dex.id.StringId;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class StringIdArray extends IdSectionArray<StringId> {
    private StringDataArray mStringDataArray;

    public StringIdArray(IntegerPair integerPair, Creator<StringId> creator) {
        super(integerPair, creator);
    }

    private StringDataArray getStringDataArray() {
        SectionList sectionList;
        StringDataArray stringDataArray = this.mStringDataArray;
        if (stringDataArray != null || (sectionList = (SectionList) getParent(SectionList.class)) == null) {
            return stringDataArray;
        }
        StringDataArray itemArray = ((StringDataSection) sectionList.getOrCreateSection(SectionType.STRING_DATA)).getItemArray();
        this.mStringDataArray = itemArray;
        return itemArray;
    }

    @Override // com.reandroid.dex.sections.SectionArray
    public void clear() {
        getStringDataArray().clear();
        super.clear();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public StringId createNext() {
        StringData stringDataCreateNext = getStringDataArray().createNext();
        StringId stringIdCreateNext = super/*com.reandroid.arsc.container.BlockList*/.createNext();
        stringIdCreateNext.linkStringData(stringDataCreateNext);
        return stringIdCreateNext;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setSize(int i) {
        BlockCreator stringDataArray = getStringDataArray();
        stringDataArray.setSize(i);
        super.setSize(i);
        for (int i2 = 0; i2 < i; i2++) {
            get(i2).linkStringData((StringData) stringDataArray.get(i2));
        }
    }
}
