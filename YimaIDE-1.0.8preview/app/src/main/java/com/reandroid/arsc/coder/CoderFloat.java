package com.reandroid.arsc.coder;

import com.reandroid.arsc.value.ValueType;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class CoderFloat extends Coder {
    public static final CoderFloat INS = new CoderFloat();

    @Override // com.reandroid.arsc.coder.Coder
    public boolean canStartWith(char c) {
        return Coder.isNumberStart(c);
    }

    @Override // com.reandroid.arsc.coder.Coder
    public String decode(int i) {
        return Float.toString(Float.intBitsToFloat(i));
    }

    @Override // com.reandroid.arsc.coder.Coder
    public EncodeResult encode(String str) {
        Float f;
        if (str.indexOf(46) > 0 && (f = Coder.parseFloat(str)) != null) {
            return new EncodeResult(ValueType.FLOAT, Float.floatToIntBits(f.floatValue()));
        }
        return null;
    }

    @Override // com.reandroid.arsc.coder.Coder
    public ValueType getValueType() {
        return ValueType.FLOAT;
    }
}
