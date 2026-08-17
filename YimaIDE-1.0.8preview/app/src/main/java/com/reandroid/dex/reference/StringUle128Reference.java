package com.reandroid.dex.reference;

import com.reandroid.arsc.base.BlockRefresh;
import com.reandroid.dex.id.StringId;
import com.reandroid.dex.key.StringKey;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.utils.CompareUtil;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class StringUle128Reference extends Ule128IdItemReference<StringId> implements BlockRefresh, Comparable<StringUle128Reference> {
    public StringUle128Reference(int i) {
        super(SectionType.STRING_ID, i);
    }

    @Override // java.lang.Comparable
    public int compareTo(StringUle128Reference stringUle128Reference) {
        if (stringUle128Reference == null) {
            return -1;
        }
        return CompareUtil.compare(getItem(), stringUle128Reference.getItem());
    }

    public String getString() {
        StringId item = getItem();
        if (item != null) {
            return item.getString();
        }
        return null;
    }

    public void setString(String str) {
        setKey(StringKey.create(str));
    }
}
