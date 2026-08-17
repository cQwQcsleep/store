package com.sun.jna.platform.win32.COM;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.PointerByReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ConnectionPointContainer extends Unknown implements IConnectionPointContainer {
    public ConnectionPointContainer(Pointer pointer) {
        super(pointer);
    }

    public WinNT.HRESULT EnumConnectionPoints() {
        throw new UnsupportedOperationException();
    }

    @Override // com.sun.jna.platform.win32.COM.IConnectionPointContainer
    public WinNT.HRESULT FindConnectionPoint(Guid.REFIID refiid, PointerByReference pointerByReference) {
        return (WinNT.HRESULT) _invokeNativeObject(4, new Object[]{getPointer(), refiid, pointerByReference}, WinNT.HRESULT.class);
    }
}
