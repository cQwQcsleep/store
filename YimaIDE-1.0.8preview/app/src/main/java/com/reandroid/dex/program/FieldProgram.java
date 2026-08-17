package com.reandroid.dex.program;

import com.reandroid.dex.common.AccessFlag;
import com.reandroid.dex.key.FieldKey;
import com.reandroid.dex.key.Key;
import java.lang.annotation.ElementType;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface FieldProgram extends AccessibleProgram {
    @Override // com.reandroid.dex.program.ProgramElement, com.reandroid.dex.program.MethodProgram
    default ElementType getElementType() {
        return ElementType.FIELD;
    }

    @Override // com.reandroid.dex.program.ProgramElement, com.reandroid.dex.data.DefIndex
    FieldKey getKey();

    Key getStaticValue();

    default boolean isEnum() {
        return AccessFlag.ENUM.isSet(getElementType(), getAccessFlagsValue());
    }

    default boolean isInstance() {
        return !isStatic();
    }
}
