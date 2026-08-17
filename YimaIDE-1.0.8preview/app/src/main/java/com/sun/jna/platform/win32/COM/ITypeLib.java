package com.sun.jna.platform.win32.COM;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.OaIdl;
import com.sun.jna.platform.win32.WTypes;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.PointerByReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface ITypeLib extends IUnknown {
    WinNT.HRESULT FindName(WTypes.LPOLESTR lpolestr, WinDef.ULONG ulong, Pointer[] pointerArr, OaIdl.MEMBERID[] memberidArr, WinDef.USHORTByReference uSHORTByReference);

    WinNT.HRESULT GetDocumentation(int i, WTypes.BSTRByReference bSTRByReference, WTypes.BSTRByReference bSTRByReference2, WinDef.DWORDByReference dWORDByReference, WTypes.BSTRByReference bSTRByReference3);

    WinNT.HRESULT GetLibAttr(PointerByReference pointerByReference);

    WinNT.HRESULT GetTypeComp(PointerByReference pointerByReference);

    WinNT.HRESULT GetTypeInfo(WinDef.UINT uint, PointerByReference pointerByReference);

    WinDef.UINT GetTypeInfoCount();

    WinNT.HRESULT GetTypeInfoOfGuid(Guid.GUID guid, PointerByReference pointerByReference);

    WinNT.HRESULT GetTypeInfoType(WinDef.UINT uint, OaIdl.TYPEKIND.ByReference byReference);

    WinNT.HRESULT IsName(WTypes.LPOLESTR lpolestr, WinDef.ULONG ulong, WinDef.BOOLByReference bOOLByReference);

    void ReleaseTLibAttr(OaIdl.TLIBATTR tlibattr);
}
