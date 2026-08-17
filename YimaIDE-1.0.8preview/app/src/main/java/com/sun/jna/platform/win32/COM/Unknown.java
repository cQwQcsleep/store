package com.sun.jna.platform.win32.COM;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.PointerByReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Unknown extends COMInvoker implements IUnknown {

    public static class ByReference extends Unknown implements Structure.ByReference {
    }

    public Unknown(Pointer pointer) {
        setPointer(pointer);
    }

    @Override // com.sun.jna.platform.win32.COM.IUnknown
    public int AddRef() {
        return _invokeNativeInt(1, new Object[]{getPointer()});
    }

    @Override // com.sun.jna.platform.win32.COM.IUnknown
    public WinNT.HRESULT QueryInterface(Guid.REFIID refiid, PointerByReference pointerByReference) {
        return (WinNT.HRESULT) _invokeNativeObject(0, new Object[]{getPointer(), refiid, pointerByReference}, WinNT.HRESULT.class);
    }

    @Override // com.sun.jna.platform.win32.COM.IUnknown
    public int Release() {
        return _invokeNativeInt(2, new Object[]{getPointer()});
    }

    public Unknown() {
    }
}
