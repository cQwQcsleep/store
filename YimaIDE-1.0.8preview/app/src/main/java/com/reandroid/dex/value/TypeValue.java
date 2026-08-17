package com.reandroid.dex.value;

import com.reandroid.dex.id.TypeId;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.sections.SectionType;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class TypeValue extends SectionIdValue<TypeId> {
    public TypeValue() {
        super(SectionType.TYPE_ID, DexValueType.TYPE);
    }

    @Override // com.reandroid.dex.value.DexValueBlock
    public TypeKey getDataTypeKey() {
        return TypeKey.CLASS;
    }

    @Override // com.reandroid.dex.value.SectionIdValue, com.reandroid.dex.value.SectionValue, com.reandroid.dex.value.DexValueBlock, com.reandroid.dex.key.KeyItem
    public TypeKey getKey() {
        return (TypeKey) super.getKey();
    }

    @Override // com.reandroid.dex.value.SectionValue, com.reandroid.dex.value.DexValueBlock
    public DexValueType<?> getValueType() {
        return DexValueType.TYPE;
    }
}
