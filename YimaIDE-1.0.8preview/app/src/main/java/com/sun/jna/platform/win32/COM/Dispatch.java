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
public class Dispatch extends Unknown implements IDispatch {

    public static class ByReference extends Dispatch implements Structure.ByReference {
    }

    public Dispatch() {
    }

    @Override // com.sun.jna.platform.win32.COM.IDispatch
    public WinNT.HRESULT GetIDsOfNames(Guid.REFIID refiid, WString[] wStringArr, int i, WinDef.LCID lcid, OaIdl.DISPIDByReference dISPIDByReference) {
        return (WinNT.HRESULT) _invokeNativeObject(5, new Object[]{getPointer(), refiid, wStringArr, Integer.valueOf(i), lcid, dISPIDByReference}, WinNT.HRESULT.class);
    }

    @Override // com.sun.jna.platform.win32.COM.IDispatch
    public WinNT.HRESULT GetTypeInfo(WinDef.UINT uint, WinDef.LCID lcid, PointerByReference pointerByReference) {
        return (WinNT.HRESULT) _invokeNativeObject(4, new Object[]{getPointer(), uint, lcid, pointerByReference}, WinNT.HRESULT.class);
    }

    @Override // com.sun.jna.platform.win32.COM.IDispatch
    public WinNT.HRESULT GetTypeInfoCount(WinDef.UINTByReference uINTByReference) {
        return (WinNT.HRESULT) _invokeNativeObject(3, new Object[]{getPointer(), uINTByReference}, WinNT.HRESULT.class);
    }

    @Override // com.sun.jna.platform.win32.COM.IDispatch
    public WinNT.HRESULT Invoke(OaIdl.DISPID dispid, Guid.REFIID refiid, WinDef.LCID lcid, WinDef.WORD word, OleAuto.DISPPARAMS.ByReference byReference, Variant.VARIANT.ByReference byReference2, OaIdl.EXCEPINFO.ByReference byReference3, IntByReference intByReference) {
        return (WinNT.HRESULT) _invokeNativeObject(6, new Object[]{getPointer(), dispid, refiid, lcid, word, byReference, byReference2, byReference3, intByReference}, WinNT.HRESULT.class);
    }

    public Dispatch(Pointer pointer) {
        super(pointer);
    }
}
