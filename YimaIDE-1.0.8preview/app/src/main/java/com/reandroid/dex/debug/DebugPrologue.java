package com.reandroid.dex.debug;

import com.reandroid.dex.smali.model.Smali;
import com.reandroid.dex.smali.model.SmaliDebugPrologue;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DebugPrologue extends DebugElement {
    public DebugPrologue() {
        super(DebugElementType.PROLOGUE);
    }

    @Override // com.reandroid.dex.debug.DebugElement
    public void fromSmali(Smali smali) {
        super.fromSmali(smali);
        if (smali instanceof SmaliDebugPrologue) {
            return;
        }
        throw new ClassCastException("Mismatch class: " + smali.getClass() + ", expecting: " + SmaliDebugPrologue.class);
    }

    @Override // com.reandroid.dex.debug.DebugElement
    public DebugElementType<DebugPrologue> getElementType() {
        return DebugElementType.PROLOGUE;
    }
}
