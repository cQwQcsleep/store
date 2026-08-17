package com.reandroid.dex.reference;

import com.reandroid.dex.common.SectionItem;
import com.reandroid.dex.id.StringId;
import com.reandroid.dex.key.StringKey;
import com.reandroid.dex.sections.SectionType;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class IndirectStringReference extends IdItemIndirectReference<StringId> {
    public IndirectStringReference(SectionItem sectionItem, int i, int i2) {
        super(SectionType.STRING_ID, sectionItem, i, i2);
    }

    public static boolean equals(IndirectStringReference indirectStringReference, IndirectStringReference indirectStringReference2) {
        if (indirectStringReference == indirectStringReference2) {
            return true;
        }
        if (indirectStringReference == null) {
            return false;
        }
        return StringId.equals(indirectStringReference.getItem(), indirectStringReference2.getItem());
    }

    @Override // com.reandroid.dex.reference.IdItemIndirectReference, com.reandroid.dex.key.KeyItem
    public StringKey getKey() {
        return (StringKey) super.getKey();
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
