package com.reandroid.dex.smali.model;

import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.PrimitiveKey;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.dex.value.DexValueType;
import com.reandroid.utils.NumberX;
import com.reandroid.utils.NumbersUtil;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SmaliValueX extends SmaliValueNumber<NumberX> {
    private long value;
    private int width;

    public SmaliValueX(NumberX numberX) {
        this(numberX.width(), numberX.longValue());
    }

    @Override // com.reandroid.dex.smali.model.Smali, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        smaliWriter.append((CharSequence) NumberX.toHexString(getWidth(), getValueAsLong()));
    }

    @Override // com.reandroid.dex.smali.model.SmaliValueNumber, com.reandroid.dex.smali.model.SmaliValue, com.reandroid.dex.key.KeyItem
    public PrimitiveKey getKey() {
        return PrimitiveKey.of(getWidth(), getValueAsLong());
    }

    @Override // com.reandroid.dex.smali.model.SmaliValueNumber
    public NumberX getNumber() {
        return NumberX.valueOf(getWidth(), getValueAsLong());
    }

    @Override // com.reandroid.dex.smali.model.SmaliValueNumber
    public long getValueAsLong() {
        return this.value;
    }

    @Override // com.reandroid.dex.smali.model.SmaliValue
    public DexValueType<?> getValueType() {
        return null;
    }

    @Override // com.reandroid.dex.smali.model.SmaliValueNumber
    public int getWidth() {
        return this.width;
    }

    @Override // com.reandroid.dex.smali.model.SmaliValue, com.reandroid.dex.smali.SmaliParser
    public void parse(SmaliReader smaliReader) throws IOException {
        smaliReader.skipSpaces();
        SmaliValueNumber<?> smaliValueNumberCreateNumber = SmaliValueNumber.createNumber(smaliReader);
        smaliValueNumberCreateNumber.parse(smaliReader);
        long valueAsLong = smaliValueNumberCreateNumber.getValueAsLong();
        set(NumbersUtil.max(getWidth(), NumberX.widthOfSigned(valueAsLong)), valueAsLong);
    }

    public void set(int i, long j) {
        this.width = i;
        this.value = j;
    }

    @Override // com.reandroid.dex.smali.model.SmaliValueNumber, com.reandroid.dex.smali.model.SmaliValue, com.reandroid.dex.key.KeyReference
    public void setKey(Key key) {
        PrimitiveKey primitiveKey = (PrimitiveKey) key;
        if (primitiveKey.isNumber()) {
            set(primitiveKey.width(), primitiveKey.getValueAsLong());
            return;
        }
        throw new NumberFormatException("Incompatible key: " + key.getClass() + ", " + key);
    }

    @Override // com.reandroid.dex.smali.model.SmaliValueNumber
    public void setNumber(NumberX numberX) {
        set(numberX.width(), numberX.longValue());
    }

    public void setWidth(int i) {
        this.width = i;
    }

    public SmaliValueX() {
        this(1, 0L);
    }

    public SmaliValueX(int i, long j) {
        this.width = i;
        this.value = j;
    }
}
