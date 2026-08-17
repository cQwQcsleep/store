package com.sun.jna.platform.win32.COM;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.OaIdl;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.PointerByReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class TypeComp extends Unknown {

    public static class ByReference extends TypeComp implements Structure.ByReference {
    }

    public TypeComp() {
    }

    public WinNT.HRESULT Bind(WString wString, WinDef.ULONG ulong, WinDef.WORD word, PointerByReference pointerByReference, OaIdl.DESCKIND.ByReference byReference, OaIdl.BINDPTR.ByReference byReference2) {
        return (WinNT.HRESULT) _invokeNativeObject(3, new Object[]{getPointer(), wString, ulong, word, pointerByReference, byReference, byReference2}, WinNT.HRESULT.class);
    }

    public WinNT.HRESULT BindType(WString wString, WinDef.ULONG ulong, PointerByReference pointerByReference, PointerByReference pointerByReference2) {
        return (WinNT.HRESULT) _invokeNativeObject(4, new Object[]{getPointer(), wString, ulong, pointerByReference, pointerByReference2}, WinNT.HRESULT.class);
    }

    public TypeComp(Pointer pointer) {
        super(pointer);
    }
}
