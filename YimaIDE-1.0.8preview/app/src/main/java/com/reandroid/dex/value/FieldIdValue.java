package com.reandroid.dex.value;

import com.reandroid.dex.id.FieldId;
import com.reandroid.dex.key.FieldKey;
import com.reandroid.dex.sections.SectionType;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class FieldIdValue extends SectionIdValue<FieldId> {
    public FieldIdValue() {
        super(SectionType.FIELD_ID, DexValueType.FIELD);
    }

    @Override // com.reandroid.dex.value.SectionIdValue, com.reandroid.dex.value.SectionValue, com.reandroid.dex.value.DexValueBlock, com.reandroid.dex.key.KeyItem
    public FieldKey getKey() {
        return (FieldKey) super.getKey();
    }

    @Override // com.reandroid.dex.value.SectionValue, com.reandroid.dex.value.DexValueBlock
    public DexValueType<?> getValueType() {
        return DexValueType.FIELD;
    }
}
