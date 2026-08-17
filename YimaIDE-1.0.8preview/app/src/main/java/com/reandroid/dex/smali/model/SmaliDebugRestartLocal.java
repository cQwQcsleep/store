package com.reandroid.dex.smali.model;

import com.reandroid.dex.debug.DebugElementType;
import com.reandroid.dex.debug.DebugRestartLocal;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SmaliDebugRestartLocal extends SmaliDebugRegister {
    @Override // com.reandroid.dex.smali.model.SmaliDebugRegister, com.reandroid.dex.smali.model.SmaliDebugElement
    public DebugElementType<DebugRestartLocal> getDebugElementType() {
        return DebugElementType.RESTART_LOCAL;
    }
}
