package com.reandroid.dex.debug;

import com.reandroid.dex.smali.model.Smali;
import com.reandroid.dex.smali.model.SmaliDebugEpilogue;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DebugEpilogue extends DebugElement {
    public DebugEpilogue() {
        super(DebugElementType.EPILOGUE);
    }

    @Override // com.reandroid.dex.debug.DebugElement
    public void fromSmali(Smali smali) {
        super.fromSmali(smali);
        if (smali instanceof SmaliDebugEpilogue) {
            return;
        }
        throw new ClassCastException("Mismatch class: " + smali.getClass() + ", expecting: " + SmaliDebugEpilogue.class);
    }

    @Override // com.reandroid.dex.debug.DebugElement
    public DebugElementType<DebugEpilogue> getElementType() {
        return DebugElementType.EPILOGUE;
    }
}
