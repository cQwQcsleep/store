package com.reandroid.arsc.coder;

import com.reandroid.arsc.value.ValueType;
import com.reandroid.utils.HexUtil;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class CoderUnknownStringRef extends Coder {
    public static final CoderUnknownStringRef INS = new CoderUnknownStringRef();
    private static final int LENGTH = 27;
    private static final String PREFIX = "string-reference@0x";
    private static final char PREFIX_CHAR = 's';

    @Override // com.reandroid.arsc.coder.Coder
    public boolean canStartWith(char c) {
        return c == 's';
    }

    @Override // com.reandroid.arsc.coder.Coder
    public String decode(int i) {
        return HexUtil.toHex8(PREFIX, i);
    }

    @Override // com.reandroid.arsc.coder.Coder
    public EncodeResult encode(String str) {
        Integer hex;
        if (str == null || str.length() != LENGTH || !str.startsWith(PREFIX) || (hex = Coder.parseHex(str.substring(20))) == null) {
            return null;
        }
        return new EncodeResult(ValueType.STRING, hex.intValue());
    }

    @Override // com.reandroid.arsc.coder.Coder
    public ValueType getValueType() {
        return ValueType.STRING;
    }
}
