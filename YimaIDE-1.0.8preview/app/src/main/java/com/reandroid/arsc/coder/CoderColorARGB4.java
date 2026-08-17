package com.reandroid.arsc.coder;

import com.reandroid.arsc.value.ValueType;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class CoderColorARGB4 extends CoderColor {
    public static final CoderColorARGB4 INS = new CoderColorARGB4();

    public CoderColorARGB4() {
        super(5);
    }

    @Override // com.reandroid.arsc.coder.Coder
    public ValueType getValueType() {
        return ValueType.COLOR_ARGB4;
    }
}
