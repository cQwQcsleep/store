package com.reandroid.dex.value;

import com.reandroid.dex.id.FieldId;
import com.reandroid.dex.key.EnumKey;
import com.reandroid.dex.key.FieldKey;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.dex.smali.SmaliWriter;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class EnumValue extends SectionIdValue<FieldId> {
    public EnumValue() {
        super(SectionType.FIELD_ID, DexValueType.ENUM);
    }

    @Override // com.reandroid.dex.value.SectionValue, com.reandroid.dex.value.DexValueBlock, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        getKey().append(smaliWriter);
    }

    @Override // com.reandroid.dex.value.SectionIdValue, com.reandroid.dex.value.SectionValue, com.reandroid.dex.value.DexValueBlock, com.reandroid.dex.key.KeyItem
    public EnumKey getKey() {
        return EnumKey.create((FieldKey) super.getKey());
    }

    @Override // com.reandroid.dex.value.SectionValue, com.reandroid.dex.value.DexValueBlock
    public DexValueType<?> getValueType() {
        return DexValueType.ENUM;
    }

    @Override // com.reandroid.dex.value.SectionValue, com.reandroid.dex.value.DexValueBlock
    public String toString() {
        return getKey().toString();
    }
}
