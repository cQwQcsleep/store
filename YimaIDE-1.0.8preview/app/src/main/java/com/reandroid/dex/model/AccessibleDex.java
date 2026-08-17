package com.reandroid.dex.model;

import com.reandroid.dex.program.AccessibleProgram;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface AccessibleDex extends AnnotatedDex, AccessibleProgram {
    @Override // com.reandroid.dex.program.AccessibleProgram
    default int getAccessFlagsValue() {
        return getProgramElement().getAccessFlagsValue();
    }

    @Override // com.reandroid.dex.model.AnnotatedDex
    AccessibleProgram getProgramElement();

    @Override // com.reandroid.dex.program.AccessibleProgram
    default void setAccessFlagsValue(int i) {
        getProgramElement().setAccessFlagsValue(i);
    }
}
