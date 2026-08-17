package com.sun.jna.ptr;

import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class NativeLongByReference extends ByReference {
    public NativeLongByReference() {
        this(new NativeLong(0L));
    }

    public NativeLong getValue() {
        return getPointer().getNativeLong(0L);
    }

    public void setValue(NativeLong nativeLong) {
        getPointer().setNativeLong(0L, nativeLong);
    }

    @Override // com.sun.jna.ptr.ByReference, com.sun.jna.PointerType
    public String toString() {
        return NativeLong.SIZE > 4 ? String.format("NativeLong@0x1$%x=0x%2$x (%2$d)", Long.valueOf(Pointer.nativeValue(getPointer())), Long.valueOf(getValue().longValue())) : String.format("NativeLong@0x1$%x=0x%2$x (%2$d)", Long.valueOf(Pointer.nativeValue(getPointer())), Integer.valueOf(getValue().intValue()));
    }

    public NativeLongByReference(NativeLong nativeLong) {
        super(NativeLong.SIZE);
        setValue(nativeLong);
    }
}
