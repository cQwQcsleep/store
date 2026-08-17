package com.reandroid.dex.program;

import java.lang.annotation.ElementType;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface MethodParameterProgram extends ProgramElement {
    String getDebugName();

    @Override // com.reandroid.dex.program.ProgramElement, com.reandroid.dex.program.MethodProgram
    default ElementType getElementType() {
        return ElementType.PARAMETER;
    }

    void setDebugName(String str);
}
