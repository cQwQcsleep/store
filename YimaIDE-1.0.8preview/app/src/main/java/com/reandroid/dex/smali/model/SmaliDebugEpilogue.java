package com.reandroid.dex.smali.model;

import com.reandroid.dex.debug.DebugElementType;
import com.reandroid.dex.debug.DebugEpilogue;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SmaliDebugEpilogue extends SmaliDebugElement {
    @Override // com.reandroid.dex.smali.model.SmaliDebugElement
    public DebugElementType<DebugEpilogue> getDebugElementType() {
        return DebugElementType.EPILOGUE;
    }
}
