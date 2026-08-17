package com.reandroid.dex.smali.model;

import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.PrimitiveKey;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.dex.value.DexValueType;
import com.reandroid.utils.HexUtil;
import defpackage.l78;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SmaliValueInteger extends SmaliValueNumber<Integer> {
    private int value;

    public SmaliValueInteger(int i) {
        this.value = i;
    }

    @Override // com.reandroid.dex.smali.model.Smali, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        smaliWriter.appendHex(getValue());
    }

    @Override // com.reandroid.dex.smali.model.SmaliValueNumber, com.reandroid.dex.smali.model.SmaliValue, com.reandroid.dex.key.KeyItem
    public PrimitiveKey getKey() {
        return PrimitiveKey.of(getValue());
    }

    @Override // com.reandroid.dex.smali.model.SmaliValueNumber
    public Integer getNumber() {
        return Integer.valueOf(getValue());
    }

    public int getValue() {
        return this.value;
    }

    @Override // com.reandroid.dex.smali.model.SmaliValueNumber
    public long getValueAsLong() {
        return getValue();
    }

    @Override // com.reandroid.dex.smali.model.SmaliValue
    public DexValueType<?> getValueType() {
        return DexValueType.INT;
    }

    @Override // com.reandroid.dex.smali.model.SmaliValueNumber
    public int getWidth() {
        return 4;
    }

    @Override // com.reandroid.dex.smali.model.SmaliValue, com.reandroid.dex.smali.SmaliParser
    public void parse(SmaliReader smaliReader) throws IOException {
        smaliReader.skipSpaces();
        try {
            setValue(HexUtil.parseHexInteger(smaliReader.readStringForNumber()));
        } catch (NumberFormatException unused) {
            smaliReader.position(smaliReader.position());
            l78.a("Invalid hex integer", smaliReader);
        }
    }

    @Override // com.reandroid.dex.smali.model.SmaliValueNumber, com.reandroid.dex.smali.model.SmaliValue, com.reandroid.dex.key.KeyReference
    public void setKey(Key key) {
        setValue(((PrimitiveKey.IntegerKey) key).value());
    }

    @Override // com.reandroid.dex.smali.model.SmaliValueNumber
    public void setNumber(Integer num) {
        setValue(num.intValue());
    }

    public void setValue(int i) {
        this.value = i;
    }

    public SmaliValueInteger() {
        this(0);
    }
}
