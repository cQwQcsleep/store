package com.sun.jna.ptr;

import com.sun.jna.Native;
import com.sun.jna.Pointer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class PointerByReference extends ByReference {
    public PointerByReference(Pointer pointer) {
        super(Native.POINTER_SIZE);
        setValue(pointer);
    }

    public Pointer getValue() {
        return getPointer().getPointer(0L);
    }

    public void setValue(Pointer pointer) {
        getPointer().setPointer(0L, pointer);
    }

    public PointerByReference() {
        this(null);
    }
}
