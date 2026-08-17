package com.reandroid.dex.smali.model;

import com.reandroid.dex.key.FieldKey;
import com.reandroid.dex.smali.SmaliDirective;
import com.reandroid.dex.smali.SmaliParseException;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.dex.smali.SmaliRegion;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.dex.value.DexValueType;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SmaliValueEnum extends SmaliValueSectionData implements SmaliRegion {
    @Override // com.reandroid.dex.smali.model.SmaliValueSectionData, com.reandroid.dex.smali.model.Smali, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        getSmaliDirective().append(smaliWriter);
        super.append(smaliWriter);
    }

    @Override // com.reandroid.dex.smali.SmaliRegion
    public SmaliDirective getSmaliDirective() {
        return SmaliDirective.ENUM;
    }

    @Override // com.reandroid.dex.smali.model.SmaliValueSectionData
    public FieldKey getValue() {
        return (FieldKey) super.getValue();
    }

    @Override // com.reandroid.dex.smali.model.SmaliValueSectionData, com.reandroid.dex.smali.model.SmaliValue
    public DexValueType<?> getValueType() {
        return DexValueType.ENUM;
    }

    @Override // com.reandroid.dex.smali.model.SmaliValueSectionData, com.reandroid.dex.smali.model.SmaliValue, com.reandroid.dex.smali.SmaliParser
    public void parse(SmaliReader smaliReader) throws IOException {
        SmaliParseException.expect(smaliReader, getSmaliDirective());
        setValue(FieldKey.read(smaliReader));
    }

    @Override // com.reandroid.dex.smali.model.SmaliValueSectionData, com.reandroid.dex.smali.model.SmaliValue, com.reandroid.dex.key.KeyItem
    public FieldKey getKey() {
        return getValue();
    }
}
