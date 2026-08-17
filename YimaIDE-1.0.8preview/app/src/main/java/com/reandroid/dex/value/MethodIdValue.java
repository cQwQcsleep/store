package com.reandroid.dex.value;

import com.reandroid.dex.id.MethodId;
import com.reandroid.dex.key.MethodKey;
import com.reandroid.dex.sections.SectionType;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class MethodIdValue extends SectionIdValue<MethodId> {
    public MethodIdValue() {
        super(SectionType.METHOD_ID, DexValueType.METHOD);
    }

    @Override // com.reandroid.dex.value.SectionIdValue, com.reandroid.dex.value.SectionValue, com.reandroid.dex.value.DexValueBlock, com.reandroid.dex.key.KeyItem
    public MethodKey getKey() {
        return (MethodKey) super.getKey();
    }

    @Override // com.reandroid.dex.value.SectionValue, com.reandroid.dex.value.DexValueBlock
    public DexValueType<?> getValueType() {
        return DexValueType.METHOD;
    }
}
