package com.reandroid.arsc.coder;

import com.reandroid.arsc.value.ValueType;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class CoderColorARGB8 extends CoderColor {
    public static final CoderColorARGB8 INS = new CoderColorARGB8();

    public CoderColorARGB8() {
        super(9);
    }

    @Override // com.reandroid.arsc.coder.Coder
    public ValueType getValueType() {
        return ValueType.COLOR_ARGB8;
    }
}
