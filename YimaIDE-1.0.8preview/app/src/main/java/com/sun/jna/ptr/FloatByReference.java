package com.sun.jna.ptr;

import com.sun.jna.Pointer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class FloatByReference extends ByReference {
    public FloatByReference(float f) {
        super(4);
        setValue(f);
    }

    public float getValue() {
        return getPointer().getFloat(0L);
    }

    public void setValue(float f) {
        getPointer().setFloat(0L, f);
    }

    @Override // com.sun.jna.ptr.ByReference, com.sun.jna.PointerType
    public String toString() {
        return String.format("float@0x%x=%s", Long.valueOf(Pointer.nativeValue(getPointer())), Float.valueOf(getValue()));
    }

    public FloatByReference() {
        this(0.0f);
    }
}
