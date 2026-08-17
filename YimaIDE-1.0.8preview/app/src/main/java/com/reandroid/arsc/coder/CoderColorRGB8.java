package com.reandroid.arsc.coder;

import com.reandroid.arsc.value.ValueType;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class CoderColorRGB8 extends CoderColor {
    public static final CoderColorRGB8 INS = new CoderColorRGB8();

    public CoderColorRGB8() {
        super(7);
    }

    @Override // com.reandroid.arsc.coder.Coder
    public ValueType getValueType() {
        return ValueType.COLOR_RGB8;
    }
}
