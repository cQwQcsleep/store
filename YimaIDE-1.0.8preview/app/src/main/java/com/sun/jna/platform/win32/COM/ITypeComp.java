package com.sun.jna.platform.win32.COM;

import com.sun.jna.WString;
import com.sun.jna.platform.win32.OaIdl;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.PointerByReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface ITypeComp extends IUnknown {
    WinNT.HRESULT Bind(WString wString, WinDef.ULONG ulong, WinDef.WORD word, PointerByReference pointerByReference, OaIdl.DESCKIND.ByReference byReference, OaIdl.BINDPTR.ByReference byReference2);

    WinNT.HRESULT BindType(WString wString, WinDef.ULONG ulong, PointerByReference pointerByReference, PointerByReference pointerByReference2);
}
