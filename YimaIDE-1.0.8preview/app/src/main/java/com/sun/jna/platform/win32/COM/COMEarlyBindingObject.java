package com.sun.jna.platform.win32.COM;

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
public class COMEarlyBindingObject extends COMBindingBaseObject implements IDispatch {
    public COMEarlyBindingObject(Guid.CLSID clsid, boolean z, int i) {
        super(clsid, z, i);
    }

    @Override // com.sun.jna.platform.win32.COM.IUnknown
    public int AddRef() {
        return getIDispatch().AddRef();
    }

    @Override // com.sun.jna.platform.win32.COM.IDispatch
    public WinNT.HRESULT GetIDsOfNames(Guid.REFIID refiid, WString[] wStringArr, int i, WinDef.LCID lcid, OaIdl.DISPIDByReference dISPIDByReference) {
        return getIDispatch().GetIDsOfNames(refiid, wStringArr, i, lcid, dISPIDByReference);
    }

    @Override // com.sun.jna.platform.win32.COM.IDispatch
    public WinNT.HRESULT GetTypeInfo(WinDef.UINT uint, WinDef.LCID lcid, PointerByReference pointerByReference) {
        return getIDispatch().GetTypeInfo(uint, lcid, pointerByReference);
    }

    @Override // com.sun.jna.platform.win32.COM.IDispatch
    public WinNT.HRESULT GetTypeInfoCount(WinDef.UINTByReference uINTByReference) {
        return getIDispatch().GetTypeInfoCount(uINTByReference);
    }

    @Override // com.sun.jna.platform.win32.COM.IDispatch
    public WinNT.HRESULT Invoke(OaIdl.DISPID dispid, Guid.REFIID refiid, WinDef.LCID lcid, WinDef.WORD word, OleAuto.DISPPARAMS.ByReference byReference, Variant.VARIANT.ByReference byReference2, OaIdl.EXCEPINFO.ByReference byReference3, IntByReference intByReference) {
        return getIDispatch().Invoke(dispid, refiid, lcid, word, byReference, byReference2, byReference3, intByReference);
    }

    @Override // com.sun.jna.platform.win32.COM.IUnknown
    public WinNT.HRESULT QueryInterface(Guid.REFIID refiid, PointerByReference pointerByReference) {
        return getIDispatch().QueryInterface(refiid, pointerByReference);
    }

    @Override // com.sun.jna.platform.win32.COM.IUnknown
    public int Release() {
        return getIDispatch().Release();
    }

    public String getStringProperty(OaIdl.DISPID dispid) {
        Variant.VARIANT.ByReference byReference = new Variant.VARIANT.ByReference();
        oleMethod(2, byReference, dispid);
        return byReference.getValue().toString();
    }

    public void setProperty(OaIdl.DISPID dispid, boolean z) {
        oleMethod(4, (Variant.VARIANT.ByReference) null, dispid, new Variant.VARIANT(z));
    }
}
