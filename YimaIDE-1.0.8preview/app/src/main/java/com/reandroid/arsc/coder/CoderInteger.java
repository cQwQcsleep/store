package com.reandroid.arsc.coder;

import com.reandroid.arsc.value.ValueType;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class CoderInteger extends Coder {
    public static final CoderInteger INS = new CoderInteger();

    @Override // com.reandroid.arsc.coder.Coder
    public boolean canStartWith(char c) {
        return Coder.isNumberStart(c);
    }

    @Override // com.reandroid.arsc.coder.Coder
    public String decode(int i) {
        return Integer.toString(i);
    }

    @Override // com.reandroid.arsc.coder.Coder
    public EncodeResult encode(String str) {
        Integer integer;
        int iIndexOf = str.indexOf(46);
        if (iIndexOf < 0) {
            iIndexOf = str.indexOf(120);
        }
        if (iIndexOf < 0 && (integer = Coder.parseInteger(str)) != null) {
            return new EncodeResult(ValueType.DEC, integer.intValue());
        }
        return null;
    }

    @Override // com.reandroid.arsc.coder.Coder
    public ValueType getValueType() {
        return ValueType.DEC;
    }
}
