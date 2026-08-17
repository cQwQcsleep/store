package com.reandroid.dex.sections;

import com.reandroid.arsc.base.BlockCreator;
import com.reandroid.arsc.base.Creator;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.dex.base.IntegerPair;
import com.reandroid.dex.data.StringData;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.collection.Swappable;
import java.io.IOException;
import java.util.Comparator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class StringDataArray extends DataSectionArray<StringData> {
    public StringDataArray(IntegerPair integerPair, Creator<StringData> creator) {
        super(integerPair, creator);
    }

    private StringIdArray getStringIdArray() {
        StringIdSection stringIdSection;
        SectionList sectionList = (SectionList) getParent(SectionList.class);
        return (sectionList == null || (stringIdSection = (StringIdSection) sectionList.getSection(SectionType.STRING_ID)) == null) ? (StringIdArray) ObjectsUtil.getNull() : stringIdSection.getItemArray();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void moveTo(StringData stringData, int i) {
        super/*com.reandroid.arsc.container.BlockList*/.moveTo(stringData, i);
        getStringIdArray().moveTo(stringData.getOffsetReference(), i);
    }

    @Override // com.reandroid.dex.base.BlockListArray
    public void onReadBytes(BlockReader blockReader) throws IOException {
    }

    @Override // com.reandroid.dex.base.BlockListArray
    public void readChildes(BlockReader blockReader) throws IOException {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean sort(Comparator<? super StringData> comparator, Swappable swappable) {
        BlockCreator stringIdArray = getStringIdArray();
        if (stringIdArray != null) {
            return super/*com.reandroid.arsc.container.BlockList*/.sort(comparator, stringIdArray);
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean swap(StringData stringData, StringData stringData2) {
        boolean zSwap = super/*com.reandroid.arsc.container.BlockList*/.swap(stringData, stringData2);
        getStringIdArray().swap(stringData.getOffsetReference(), stringData2.getOffsetReference());
        return zSwap;
    }

    public boolean sort(Comparator<? super StringData> comparator) {
        return sort(comparator, null);
    }
}
