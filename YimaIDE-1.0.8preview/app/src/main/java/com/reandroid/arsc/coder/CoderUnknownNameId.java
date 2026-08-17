package com.reandroid.arsc.coder;

import com.reandroid.arsc.value.ValueType;
import com.reandroid.utils.HexUtil;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class CoderUnknownNameId extends Coder {
    public static final CoderUnknownNameId INS = new CoderUnknownNameId();
    private static final int LENGTH = 11;
    private static final String PREFIX = "r0x";
    private static final char PREFIX_CHAR = 'r';

    @Override // com.reandroid.arsc.coder.Coder
    public boolean canStartWith(char c) {
        return c == 'r';
    }

    @Override // com.reandroid.arsc.coder.Coder
    public String decode(int i) {
        return HexUtil.toHex8(PREFIX, i);
    }

    @Override // com.reandroid.arsc.coder.Coder
    public EncodeResult encode(String str) {
        Integer hex;
        if (str == null || str.length() != 11 || !str.startsWith(PREFIX) || (hex = Coder.parseHex(str.substring(1))) == null) {
            return null;
        }
        return new EncodeResult(getValueType(), hex.intValue());
    }

    @Override // com.reandroid.arsc.coder.Coder
    public ValueType getValueType() {
        return ValueType.REFERENCE;
    }
}
