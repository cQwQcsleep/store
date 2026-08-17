package com.reandroid.dex.smali.model;

import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.PrimitiveKey;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.dex.value.DexValueType;
import com.sun.org.apache.xpath.internal.XPath;
import defpackage.l78;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SmaliValueDouble extends SmaliValueNumber<Double> {
    private double value;

    public SmaliValueDouble() {
        this(XPath.MATCH_SCORE_QNAME);
    }

    @Override // com.reandroid.dex.smali.model.Smali, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        smaliWriter.append(getValue());
    }

    @Override // com.reandroid.dex.smali.model.SmaliValueNumber, com.reandroid.dex.smali.model.SmaliValue, com.reandroid.dex.key.KeyItem
    public PrimitiveKey getKey() {
        return PrimitiveKey.of(getValue());
    }

    @Override // com.reandroid.dex.smali.model.SmaliValueNumber
    public Double getNumber() {
        return Double.valueOf(getValue());
    }

    public double getValue() {
        return this.value;
    }

    @Override // com.reandroid.dex.smali.model.SmaliValueNumber
    public long getValueAsLong() {
        return Double.doubleToLongBits(getValue());
    }

    @Override // com.reandroid.dex.smali.model.SmaliValue
    public DexValueType<?> getValueType() {
        return DexValueType.DOUBLE;
    }

    @Override // com.reandroid.dex.smali.model.SmaliValueNumber
    public int getWidth() {
        return 8;
    }

    @Override // com.reandroid.dex.smali.model.SmaliValue, com.reandroid.dex.smali.SmaliParser
    public void parse(SmaliReader smaliReader) throws IOException {
        smaliReader.skipSpaces();
        int iPosition = smaliReader.position();
        try {
            setValue(Double.parseDouble(smaliReader.readStringForNumber()));
        } catch (NumberFormatException e) {
            smaliReader.position(iPosition);
            l78.a(e.getMessage(), smaliReader);
        }
    }

    @Override // com.reandroid.dex.smali.model.SmaliValueNumber, com.reandroid.dex.smali.model.SmaliValue, com.reandroid.dex.key.KeyReference
    public void setKey(Key key) {
        setValue(((PrimitiveKey.DoubleKey) key).value());
    }

    @Override // com.reandroid.dex.smali.model.SmaliValueNumber
    public void setNumber(Double d) {
        setValue(d.doubleValue());
    }

    public void setValue(double d) {
        this.value = d;
    }

    public SmaliValueDouble(double d) {
        this.value = d;
    }
}
