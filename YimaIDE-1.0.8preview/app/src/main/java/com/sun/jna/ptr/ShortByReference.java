package com.sun.jna.ptr;

import com.sun.jna.Pointer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ShortByReference extends ByReference {
    public ShortByReference(short s) {
        super(2);
        setValue(s);
    }

    public short getValue() {
        return getPointer().getShort(0L);
    }

    public void setValue(short s) {
        getPointer().setShort(0L, s);
    }

    @Override // com.sun.jna.ptr.ByReference, com.sun.jna.PointerType
    public String toString() {
        return String.format("short@0x%1$x=0x%2$x (%2$d)", Long.valueOf(Pointer.nativeValue(getPointer())), Short.valueOf(getValue()));
    }

    public ShortByReference() {
        this((short) 0);
    }
}
