package com.reandroid.dex.smali.model;

import com.reandroid.dex.debug.DebugElementType;
import com.reandroid.dex.debug.DebugEndLocal;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SmaliDebugEndLocal extends SmaliDebugRegister {
    @Override // com.reandroid.dex.smali.model.SmaliDebugRegister, com.reandroid.dex.smali.model.SmaliDebugElement
    public DebugElementType<DebugEndLocal> getDebugElementType() {
        return DebugElementType.END_LOCAL;
    }
}
