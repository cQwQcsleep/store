package com.reandroid.arsc.coder;

import com.reandroid.arsc.value.ValueType;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class CoderColorRGB4 extends CoderColor {
    public static final CoderColorRGB4 INS = new CoderColorRGB4();

    public CoderColorRGB4() {
        super(4);
    }

    @Override // com.reandroid.arsc.coder.Coder
    public ValueType getValueType() {
        return ValueType.COLOR_RGB4;
    }
}
