package com.sun.jna.platform.win32.COM;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.PointerByReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class EnumMoniker extends Unknown implements IEnumMoniker {
    public EnumMoniker(Pointer pointer) {
        super(pointer);
    }

    @Override // com.sun.jna.platform.win32.COM.IEnumMoniker
    public WinNT.HRESULT Clone(PointerByReference pointerByReference) {
        return (WinNT.HRESULT) _invokeNativeObject(6, new Object[]{getPointer(), pointerByReference}, WinNT.HRESULT.class);
    }

    @Override // com.sun.jna.platform.win32.COM.IEnumMoniker
    public WinNT.HRESULT Next(WinDef.ULONG ulong, PointerByReference pointerByReference, WinDef.ULONGByReference uLONGByReference) {
        return (WinNT.HRESULT) _invokeNativeObject(3, new Object[]{getPointer(), ulong, pointerByReference, uLONGByReference}, WinNT.HRESULT.class);
    }

    @Override // com.sun.jna.platform.win32.COM.IEnumMoniker
    public WinNT.HRESULT Reset() {
        return (WinNT.HRESULT) _invokeNativeObject(5, new Object[]{getPointer()}, WinNT.HRESULT.class);
    }

    @Override // com.sun.jna.platform.win32.COM.IEnumMoniker
    public WinNT.HRESULT Skip(WinDef.ULONG ulong) {
        return (WinNT.HRESULT) _invokeNativeObject(4, new Object[]{getPointer(), ulong}, WinNT.HRESULT.class);
    }
}
