package com.reandroid.dex.value;

import com.reandroid.dex.id.ProtoId;
import com.reandroid.dex.key.ProtoKey;
import com.reandroid.dex.sections.SectionType;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ProtoValue extends SectionIdValue<ProtoId> {
    public ProtoValue() {
        super(SectionType.PROTO_ID, DexValueType.PROTO);
    }

    @Override // com.reandroid.dex.value.SectionIdValue, com.reandroid.dex.value.SectionValue, com.reandroid.dex.value.DexValueBlock, com.reandroid.dex.key.KeyItem
    public ProtoKey getKey() {
        return (ProtoKey) super.getKey();
    }

    @Override // com.reandroid.dex.value.SectionValue, com.reandroid.dex.value.DexValueBlock
    public DexValueType<?> getValueType() {
        return DexValueType.PROTO;
    }
}
