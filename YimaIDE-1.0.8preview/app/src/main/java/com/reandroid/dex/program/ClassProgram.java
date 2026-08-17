package com.reandroid.dex.program;

import com.reandroid.dex.common.AccessFlag;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.key.TypeListKey;
import java.lang.annotation.ElementType;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface ClassProgram extends AccessibleProgram {
    Iterator<? extends MethodProgram> getDirectMethods();

    @Override // com.reandroid.dex.program.ProgramElement, com.reandroid.dex.program.MethodProgram
    default ElementType getElementType() {
        return ElementType.TYPE;
    }

    Iterator<? extends FieldProgram> getInstanceFields();

    TypeListKey getInterfacesKey();

    @Override // com.reandroid.dex.program.ProgramElement, com.reandroid.dex.data.DefIndex
    TypeKey getKey();

    String getSourceFileName();

    Iterator<? extends FieldProgram> getStaticFields();

    TypeKey getSuperClassKey();

    Iterator<? extends MethodProgram> getVirtualMethods();

    default boolean isAnnotation() {
        return AccessFlag.ANNOTATION.isSet(getAccessFlagsValue());
    }

    default boolean isEnum() {
        return AccessFlag.ENUM.isSet(getAccessFlagsValue());
    }

    default boolean isInterface() {
        return AccessFlag.INTERFACE.isSet(getAccessFlagsValue());
    }
}
