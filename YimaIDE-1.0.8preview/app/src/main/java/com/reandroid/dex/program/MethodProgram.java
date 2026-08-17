package com.reandroid.dex.program;

import com.reandroid.dex.common.AccessFlag;
import com.reandroid.dex.key.MethodKey;
import java.lang.annotation.ElementType;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface MethodProgram extends AccessibleProgram {
    default ElementType getElementType() {
        return ElementType.METHOD;
    }

    @Override // com.reandroid.dex.program.ProgramElement, com.reandroid.dex.data.DefIndex
    MethodKey getKey();

    default boolean isBridge() {
        return AccessFlag.BRIDGE.isSet(getAccessFlagsValue());
    }

    default boolean isConstructor() {
        return AccessFlag.CONSTRUCTOR.isSet(getAccessFlagsValue());
    }

    default boolean isDirect() {
        return isConstructor() || isStatic() || isPrivate();
    }

    default boolean isVarArgs() {
        return AccessFlag.VARARGS.isSet(getAccessFlagsValue());
    }

    default boolean isVirtual() {
        return !isDirect();
    }
}
