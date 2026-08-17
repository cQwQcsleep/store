package com.sun.jna.platform.win32.COM;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.PointerByReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
@Structure.FieldOrder({"vtbl"})
public class UnknownListener extends Structure {
    public UnknownVTable.ByReference vtbl = constructVTable();

    public UnknownListener(IUnknownCallback iUnknownCallback) {
        initVTable(iUnknownCallback);
        super.write();
    }

    public UnknownVTable.ByReference constructVTable() {
        return new UnknownVTable.ByReference();
    }

    public void initVTable(final IUnknownCallback iUnknownCallback) {
        this.vtbl.QueryInterfaceCallback = new UnknownVTable.QueryInterfaceCallback() { // from class: com.sun.jna.platform.win32.COM.UnknownListener.1
            @Override // com.sun.jna.platform.win32.COM.UnknownVTable.QueryInterfaceCallback
            public WinNT.HRESULT invoke(Pointer pointer, Guid.REFIID refiid, PointerByReference pointerByReference) {
                return iUnknownCallback.QueryInterface(refiid, pointerByReference);
            }
        };
        this.vtbl.AddRefCallback = new UnknownVTable.AddRefCallback() { // from class: com.sun.jna.platform.win32.COM.UnknownListener.2
            @Override // com.sun.jna.platform.win32.COM.UnknownVTable.AddRefCallback
            public int invoke(Pointer pointer) {
                return iUnknownCallback.AddRef();
            }
        };
        this.vtbl.ReleaseCallback = new UnknownVTable.ReleaseCallback() { // from class: com.sun.jna.platform.win32.COM.UnknownListener.3
            @Override // com.sun.jna.platform.win32.COM.UnknownVTable.ReleaseCallback
            public int invoke(Pointer pointer) {
                return iUnknownCallback.Release();
            }
        };
    }
}
