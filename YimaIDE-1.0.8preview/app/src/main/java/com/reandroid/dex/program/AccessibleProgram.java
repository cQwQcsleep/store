package com.reandroid.dex.program;

import com.reandroid.dex.common.AccessFlag;
import com.reandroid.dex.common.Modifier;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface AccessibleProgram extends ProgramElement {
    default void addAccessFlag(AccessFlag accessFlag) {
        int accessFlagsValue = getAccessFlagsValue();
        int value = accessFlag.getValue();
        if ((value & 7) != 0) {
            accessFlagsValue &= -8;
        }
        setAccessFlagsValue(value | accessFlagsValue);
    }

    default Iterator<? extends Modifier> getAccessFlags() {
        return AccessFlag.valuesOf(getElementType(), getAccessFlagsValue());
    }

    int getAccessFlagsValue();

    default Iterator<? extends Modifier> getModifiers() {
        return getAccessFlags();
    }

    default boolean isAbstract() {
        return AccessFlag.ABSTRACT.isSet(getAccessFlagsValue());
    }

    default boolean isFinal() {
        return AccessFlag.FINAL.isSet(getElementType(), getAccessFlagsValue());
    }

    default boolean isInternal() {
        return (getAccessFlagsValue() & 7) == 0;
    }

    default boolean isNative() {
        return AccessFlag.NATIVE.isSet(getAccessFlagsValue());
    }

    default boolean isPrivate() {
        return AccessFlag.PRIVATE.isSet(getElementType(), getAccessFlagsValue());
    }

    default boolean isProtected() {
        return AccessFlag.PROTECTED.isSet(getAccessFlagsValue());
    }

    default boolean isPublic() {
        return AccessFlag.PUBLIC.isSet(getElementType(), getAccessFlagsValue());
    }

    default boolean isStatic() {
        return AccessFlag.STATIC.isSet(getElementType(), getAccessFlagsValue());
    }

    default boolean isSynthetic() {
        return AccessFlag.SYNTHETIC.isSet(getAccessFlagsValue());
    }

    default void removeAccessFlag(AccessFlag accessFlag) {
        setAccessFlagsValue((~accessFlag.getValue()) & getAccessFlagsValue());
    }

    void setAccessFlagsValue(int i);
}
