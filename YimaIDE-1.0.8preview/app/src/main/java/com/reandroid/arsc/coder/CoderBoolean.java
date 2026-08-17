package com.reandroid.arsc.coder;

import com.reandroid.arsc.value.ValueType;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class CoderBoolean extends Coder {
    public static final CoderBoolean INS = new CoderBoolean();

    @Override // com.reandroid.arsc.coder.Coder
    public boolean canStartWith(char c) {
        return c == 'f' || c == 't';
    }

    @Override // com.reandroid.arsc.coder.Coder
    public String decode(int i) {
        return i == 0 ? "false" : "true";
    }

    @Override // com.reandroid.arsc.coder.Coder
    public EncodeResult encode(String str) {
        int i;
        if ("true".equals(str)) {
            i = -1;
        } else {
            if (!"false".equals(str)) {
                return null;
            }
            i = 0;
        }
        return new EncodeResult(ValueType.BOOLEAN, i);
    }

    @Override // com.reandroid.arsc.coder.Coder
    public ValueType getValueType() {
        return ValueType.BOOLEAN;
    }
}
