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
public class SmaliValueShort extends SmaliValueNumber<Short> {
    private short value;

    public SmaliValueShort(short s) {
        this.value = s;
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
    public Short getNumber() {
        return Short.valueOf(getValue());
    }

    public short getValue() {
        return this.value;
    }

    @Override // com.reandroid.dex.smali.model.SmaliValueNumber
    public long getValueAsLong() {
        return getValue();
    }

    @Override // com.reandroid.dex.smali.model.SmaliValue
    public DexValueType<?> getValueType() {
        return DexValueType.SHORT;
    }

    @Override // com.reandroid.dex.smali.model.SmaliValueNumber
    public int getWidth() {
        return 2;
    }

    @Override // com.reandroid.dex.smali.model.SmaliValue, com.reandroid.dex.smali.SmaliParser
    public void parse(SmaliReader smaliReader) throws IOException {
        smaliReader.skipSpaces();
        int iPosition = smaliReader.position();
        try {
            setValue(HexUtil.parseHexShort(smaliReader.readStringForNumber()));
        } catch (NumberFormatException e) {
            smaliReader.position(iPosition);
            l78.a(e.getMessage(), smaliReader);
        }
    }

    @Override // com.reandroid.dex.smali.model.SmaliValueNumber, com.reandroid.dex.smali.model.SmaliValue, com.reandroid.dex.key.KeyReference
    public void setKey(Key key) {
        setValue(((PrimitiveKey.ShortKey) key).value());
    }

    @Override // com.reandroid.dex.smali.model.SmaliValueNumber
    public void setNumber(Short sh) {
        setValue(sh.shortValue());
    }

    public void setValue(short s) {
        this.value = s;
    }

    public SmaliValueShort() {
        this((short) 0);
    }
}
