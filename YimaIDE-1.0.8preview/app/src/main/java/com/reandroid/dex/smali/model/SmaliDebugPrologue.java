package com.reandroid.dex.smali.model;

import com.reandroid.dex.debug.DebugElementType;
import com.reandroid.dex.debug.DebugPrologue;
import com.reandroid.dex.smali.SmaliRegion;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SmaliDebugPrologue extends SmaliDebugElement implements SmaliRegion {
    @Override // com.reandroid.dex.smali.model.SmaliDebugElement
    public DebugElementType<DebugPrologue> getDebugElementType() {
        return DebugElementType.PROLOGUE;
    }
}
