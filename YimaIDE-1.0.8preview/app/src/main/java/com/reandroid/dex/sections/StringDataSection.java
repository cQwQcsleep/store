package com.reandroid.dex.sections;

import com.reandroid.dex.base.IntegerPair;
import com.reandroid.dex.common.SectionItem;
import com.reandroid.dex.data.StringData;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.pool.StringDataPool;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class StringDataSection extends DataSection<StringData> {
    public StringDataSection(IntegerPair integerPair, SectionType<StringData> sectionType) {
        super(sectionType, new StringDataArray(integerPair, sectionType.getCreator()));
    }

    @Override // com.reandroid.dex.sections.DataSection, com.reandroid.dex.sections.Section
    public int clearUnused() {
        return 0;
    }

    @Override // com.reandroid.dex.sections.DataSection, com.reandroid.dex.sections.Section
    public StringDataPool createPool() {
        return new StringDataPool(this);
    }

    @Override // com.reandroid.dex.sections.DataSection, com.reandroid.dex.sections.Section
    public StringDataArray getItemArray() {
        return (StringDataArray) super.getItemArray();
    }

    @Override // com.reandroid.dex.sections.DataSection, com.reandroid.dex.sections.Section
    public StringDataPool getPool() {
        return (StringDataPool) super.getPool();
    }

    @Override // com.reandroid.dex.sections.Section
    public boolean keyChanged(SectionItem sectionItem, Key key) {
        return super.keyChanged(sectionItem, key);
    }
}
