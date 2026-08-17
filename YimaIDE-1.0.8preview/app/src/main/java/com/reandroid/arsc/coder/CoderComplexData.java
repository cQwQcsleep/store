package com.reandroid.arsc.coder;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class CoderComplexData extends Coder {
    @Override // com.reandroid.arsc.coder.Coder
    public boolean canStartWith(char c) {
        return Coder.isNumberStart(c);
    }

    @Override // com.reandroid.arsc.coder.Coder
    public EncodeResult encode(String str) {
        Float f;
        ComplexUnit unit = parseUnit(str);
        if (unit == null || (f = Coder.parseFloat(str.substring(0, str.length() - unit.getSymbol().length()))) == null) {
            return null;
        }
        return new EncodeResult(unit.getValueType(), ComplexUtil.encodeComplex(f.floatValue(), unit));
    }

    public abstract ComplexUnit parseUnit(String str);
}
