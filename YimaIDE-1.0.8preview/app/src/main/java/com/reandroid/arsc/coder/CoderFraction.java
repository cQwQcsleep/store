package com.reandroid.arsc.coder;

import com.reandroid.arsc.value.ValueType;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class CoderFraction extends CoderComplexData {
    public static final CoderFraction INS = new CoderFraction();

    @Override // com.reandroid.arsc.coder.Coder
    public String decode(int i) {
        return ComplexUtil.decodeComplex(true, i);
    }

    @Override // com.reandroid.arsc.coder.CoderComplexData, com.reandroid.arsc.coder.Coder
    public EncodeResult encode(String str) {
        ComplexUnit unit;
        Float f;
        if (str.indexOf(37) < 0 || (unit = parseUnit(str)) == null || (f = Coder.parseFloat(str.substring(0, str.length() - unit.getSymbol().length()))) == null) {
            return null;
        }
        return new EncodeResult(unit.getValueType(), ComplexUtil.encodeComplex(f.floatValue(), unit));
    }

    @Override // com.reandroid.arsc.coder.Coder
    public ValueType getValueType() {
        return ValueType.FRACTION;
    }

    @Override // com.reandroid.arsc.coder.CoderComplexData
    public ComplexUnit parseUnit(String str) {
        return UnitFraction.fromPostfix(str);
    }
}
