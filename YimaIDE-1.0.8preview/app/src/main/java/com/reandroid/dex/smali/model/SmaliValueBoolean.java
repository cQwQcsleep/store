package com.reandroid.dex.smali.model;

import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.PrimitiveKey;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.dex.value.DexValueType;
import com.sun.org.apache.bcel.internal.classfile.ElementValue;
import defpackage.l78;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SmaliValueBoolean extends SmaliValue {
    private boolean value;

    @Override // com.reandroid.dex.smali.model.Smali, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        if (getValue()) {
            smaliWriter.append("true");
        } else {
            smaliWriter.append("false");
        }
    }

    @Override // com.reandroid.dex.smali.model.SmaliValue, com.reandroid.dex.key.KeyItem
    public PrimitiveKey getKey() {
        return PrimitiveKey.of(getValue());
    }

    public boolean getValue() {
        return this.value;
    }

    @Override // com.reandroid.dex.smali.model.SmaliValue
    public DexValueType<?> getValueType() {
        return DexValueType.BOOLEAN;
    }

    @Override // com.reandroid.dex.smali.model.SmaliValue, com.reandroid.dex.smali.SmaliParser
    public void parse(SmaliReader smaliReader) throws IOException {
        smaliReader.skipSpaces();
        if (smaliReader.startsWith(new byte[]{102, 97, 108, ElementValue.STRING, ElementValue.ENUM_CONSTANT})) {
            smaliReader.skip(5);
            setValue(false);
        } else if (!smaliReader.startsWith(new byte[]{116, 114, 117, ElementValue.ENUM_CONSTANT})) {
            l78.a("Not boolean value", smaliReader);
        } else {
            smaliReader.skip(4);
            setValue(true);
        }
    }

    @Override // com.reandroid.dex.smali.model.SmaliValue, com.reandroid.dex.key.KeyReference
    public void setKey(Key key) {
        setValue(((PrimitiveKey.BooleanKey) key).value());
    }

    public void setValue(boolean z) {
        this.value = z;
    }
}
