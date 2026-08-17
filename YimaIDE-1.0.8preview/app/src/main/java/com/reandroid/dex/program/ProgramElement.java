package com.reandroid.dex.program;

import com.reandroid.dex.key.ProgramKey;
import java.lang.annotation.ElementType;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface ProgramElement extends AnnotatedProgram {
    ElementType getElementType();

    ProgramKey getKey();
}
