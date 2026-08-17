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
public class SmaliValueFloat extends SmaliValueNumber<Float> {
    private float value;
    public static final byte[] POSITIVE_INFINITY = {ElementValue.PRIMITIVE_INT, 110, 102, 105, 110, 105, 116, 121, 102};
    public static final byte[] NEGATIVE_INFINITY = {45, ElementValue.PRIMITIVE_INT, 110, 102, 105, 110, 105, 116, 121, 102};
    public static final byte[] NAN = {78, 97, 78, 102};

    public SmaliValueFloat(float f) {
        this.value = f;
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
    public Float getNumber() {
        return Float.valueOf(getValue());
    }

    public float getValue() {
        return this.value;
    }

    @Override // com.reandroid.dex.smali.model.SmaliValueNumber
    public long getValueAsLong() {
        return Float.floatToIntBits(getValue());
    }

    @Override // com.reandroid.dex.smali.model.SmaliValue
    public DexValueType<?> getValueType() {
        return DexValueType.FLOAT;
    }

    @Override // com.reandroid.dex.smali.model.SmaliValueNumber
    public int getWidth() {
        return 4;
    }

    @Override // com.reandroid.dex.smali.model.SmaliValue, com.reandroid.dex.smali.SmaliParser
    public void parse(SmaliReader smaliReader) throws IOException {
        float f;
        smaliReader.skipSpaces();
        int iPosition = smaliReader.position();
        try {
            byte[] bArr = POSITIVE_INFINITY;
            if (smaliReader.startsWith(bArr)) {
                smaliReader.skip(bArr.length);
                f = Float.POSITIVE_INFINITY;
            } else {
                byte[] bArr2 = NEGATIVE_INFINITY;
                if (smaliReader.startsWith(bArr2)) {
                    smaliReader.skip(bArr2.length);
                    f = Float.NEGATIVE_INFINITY;
                } else {
                    byte[] bArr3 = NAN;
                    if (smaliReader.startsWith(bArr3)) {
                        smaliReader.skip(bArr3.length);
                        f = Float.NaN;
                    } else {
                        f = Float.parseFloat(smaliReader.readStringForNumber());
                    }
                }
            }
            setValue(f);
        } catch (NumberFormatException e) {
            smaliReader.position(iPosition);
            l78.a(e.getMessage(), smaliReader);
        }
    }

    @Override // com.reandroid.dex.smali.model.SmaliValueNumber, com.reandroid.dex.smali.model.SmaliValue, com.reandroid.dex.key.KeyReference
    public void setKey(Key key) {
        setValue(((PrimitiveKey.FloatKey) key).value());
    }

    @Override // com.reandroid.dex.smali.model.SmaliValueNumber
    public void setNumber(Float f) {
        setValue(f.floatValue());
    }

    public void setValue(float f) {
        this.value = f;
    }

    public SmaliValueFloat() {
        this(0.0f);
    }
}
