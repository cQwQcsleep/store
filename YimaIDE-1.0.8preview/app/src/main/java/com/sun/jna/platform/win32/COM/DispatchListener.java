package com.sun.jna.platform.win32.COM;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.OaIdl;
import com.sun.jna.platform.win32.OleAuto;
import com.sun.jna.platform.win32.Variant;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
@Structure.FieldOrder({"vtbl"})
public class DispatchListener extends Structure {
    public DispatchVTable.ByReference vtbl = constructVTable();

    public DispatchListener(IDispatchCallback iDispatchCallback) {
        initVTable(iDispatchCallback);
        super.write();
    }

    public DispatchVTable.ByReference constructVTable() {
        return new DispatchVTable.ByReference();
    }

    public void initVTable(final IDispatchCallback iDispatchCallback) {
        this.vtbl.QueryInterfaceCallback = new DispatchVTable.QueryInterfaceCallback() { // from class: com.sun.jna.platform.win32.COM.DispatchListener.1
            @Override // com.sun.jna.platform.win32.COM.DispatchVTable.QueryInterfaceCallback
            public WinNT.HRESULT invoke(Pointer pointer, Guid.REFIID refiid, PointerByReference pointerByReference) {
                return iDispatchCallback.QueryInterface(refiid, pointerByReference);
            }
        };
        this.vtbl.AddRefCallback = new DispatchVTable.AddRefCallback() { // from class: com.sun.jna.platform.win32.COM.DispatchListener.2
            @Override // com.sun.jna.platform.win32.COM.DispatchVTable.AddRefCallback
            public int invoke(Pointer pointer) {
                return iDispatchCallback.AddRef();
            }
        };
        this.vtbl.ReleaseCallback = new DispatchVTable.ReleaseCallback() { // from class: com.sun.jna.platform.win32.COM.DispatchListener.3
            @Override // com.sun.jna.platform.win32.COM.DispatchVTable.ReleaseCallback
            public int invoke(Pointer pointer) {
                return iDispatchCallback.Release();
            }
        };
        this.vtbl.GetTypeInfoCountCallback = new DispatchVTable.GetTypeInfoCountCallback() { // from class: com.sun.jna.platform.win32.COM.DispatchListener.4
            @Override // com.sun.jna.platform.win32.COM.DispatchVTable.GetTypeInfoCountCallback
            public WinNT.HRESULT invoke(Pointer pointer, WinDef.UINTByReference uINTByReference) {
                return iDispatchCallback.GetTypeInfoCount(uINTByReference);
            }
        };
        this.vtbl.GetTypeInfoCallback = new DispatchVTable.GetTypeInfoCallback() { // from class: com.sun.jna.platform.win32.COM.DispatchListener.5
            @Override // com.sun.jna.platform.win32.COM.DispatchVTable.GetTypeInfoCallback
            public WinNT.HRESULT invoke(Pointer pointer, WinDef.UINT uint, WinDef.LCID lcid, PointerByReference pointerByReference) {
                return iDispatchCallback.GetTypeInfo(uint, lcid, pointerByReference);
            }
        };
        this.vtbl.GetIDsOfNamesCallback = new DispatchVTable.GetIDsOfNamesCallback() { // from class: com.sun.jna.platform.win32.COM.DispatchListener.6
            @Override // com.sun.jna.platform.win32.COM.DispatchVTable.GetIDsOfNamesCallback
            public WinNT.HRESULT invoke(Pointer pointer, Guid.REFIID refiid, WString[] wStringArr, int i, WinDef.LCID lcid, OaIdl.DISPIDByReference dISPIDByReference) {
                return iDispatchCallback.GetIDsOfNames(refiid, wStringArr, i, lcid, dISPIDByReference);
            }
        };
        this.vtbl.InvokeCallback = new DispatchVTable.InvokeCallback() { // from class: com.sun.jna.platform.win32.COM.DispatchListener.7
            @Override // com.sun.jna.platform.win32.COM.DispatchVTable.InvokeCallback
            public WinNT.HRESULT invoke(Pointer pointer, OaIdl.DISPID dispid, Guid.REFIID refiid, WinDef.LCID lcid, WinDef.WORD word, OleAuto.DISPPARAMS.ByReference byReference, Variant.VARIANT.ByReference byReference2, OaIdl.EXCEPINFO.ByReference byReference3, IntByReference intByReference) {
                return iDispatchCallback.Invoke(dispid, refiid, lcid, word, byReference, byReference2, byReference3, intByReference);
            }
        };
    }
}
