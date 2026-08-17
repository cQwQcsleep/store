package com.reandroid.arsc.coder;

import com.reandroid.arsc.value.ValueType;
import com.reandroid.utils.HexUtil;
import com.reandroid.utils.StringsUtil;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class CoderHex extends Coder {
    public static final CoderHex INS = new CoderHex();

    @Override // com.reandroid.arsc.coder.Coder
    public boolean canStartWith(char c) {
        return c == '0';
    }

    @Override // com.reandroid.arsc.coder.Coder
    public String decode(int i) {
        return HexUtil.toHex8(i);
    }

    @Override // com.reandroid.arsc.coder.Coder
    public EncodeResult encode(String str) {
        int length = str.length();
        if (length < 3 || length > 10) {
            return null;
        }
        char cCharAt = str.charAt(1);
        if (cCharAt != 'x') {
            if (cCharAt != 'X') {
                return null;
            }
            str = StringsUtil.toLowercase(str);
        }
        Integer hex = Coder.parseHex(str);
        if (hex == null) {
            return null;
        }
        return new EncodeResult(ValueType.HEX, hex.intValue());
    }

    @Override // com.reandroid.arsc.coder.Coder
    public ValueType getValueType() {
        return ValueType.HEX;
    }
}
