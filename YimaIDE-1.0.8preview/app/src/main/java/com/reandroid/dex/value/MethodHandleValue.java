package com.reandroid.dex.value;

import com.reandroid.dex.id.MethodHandleId;
import com.reandroid.dex.key.MethodHandleKey;
import com.reandroid.dex.sections.SectionType;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class MethodHandleValue extends SectionIdValue<MethodHandleId> {
    public MethodHandleValue() {
        super(SectionType.METHOD_HANDLE, DexValueType.METHOD_HANDLE);
    }

    @Override // com.reandroid.dex.value.SectionIdValue, com.reandroid.dex.value.SectionValue, com.reandroid.dex.value.DexValueBlock, com.reandroid.dex.key.KeyItem
    public MethodHandleKey getKey() {
        return (MethodHandleKey) super.getKey();
    }

    @Override // com.reandroid.dex.value.SectionValue, com.reandroid.dex.value.DexValueBlock
    public DexValueType<?> getValueType() {
        return DexValueType.METHOD_HANDLE;
    }
}
