package com.reandroid.arsc.coder;

import com.reandroid.arsc.value.ValueType;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class CoderNull extends Coder {
    public static final CoderNull INS = new CoderNull();

    @Override // com.reandroid.arsc.coder.Coder
    public boolean canStartWith(char c) {
        return c == '@';
    }

    @Override // com.reandroid.arsc.coder.Coder
    public String decode(int i) {
        return i == 0 ? "@null" : "@empty";
    }

    @Override // com.reandroid.arsc.coder.Coder
    public EncodeResult encode(String str) {
        if (str.equals("@null")) {
            return new EncodeResult(ValueType.REFERENCE, 0);
        }
        if (str.equals("@empty")) {
            return new EncodeResult(ValueType.NULL, 1);
        }
        return null;
    }

    @Override // com.reandroid.arsc.coder.Coder
    public ValueType getValueType() {
        return ValueType.NULL;
    }
}
