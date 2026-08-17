package com.reandroid.dex.reference;

import com.reandroid.arsc.item.BlockItem;
import com.reandroid.dex.base.IndirectShort;
import com.reandroid.dex.common.MethodHandleType;
import com.reandroid.dex.id.IdItem;
import com.reandroid.dex.sections.SectionType;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class MethodHandleTypeReference extends IndirectShort {
    public MethodHandleTypeReference(BlockItem blockItem, int i) {
        super(blockItem, i);
    }

    public MethodHandleType getHandleType() {
        return MethodHandleType.valueOf(get());
    }

    public SectionType<IdItem> getSectionType() {
        MethodHandleType handleType = getHandleType();
        if (handleType != null) {
            return handleType.isField() ? SectionType.FIELD_ID : SectionType.METHOD_ID;
        }
        return null;
    }

    public void setMethodHandleType(MethodHandleType methodHandleType) {
        set(methodHandleType.type());
    }

    @Override // com.reandroid.dex.base.IndirectShort
    public String toString() {
        MethodHandleType handleType = getHandleType();
        if (handleType != null) {
            return handleType.name();
        }
        return "unknown: " + get();
    }
}
