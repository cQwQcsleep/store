package com.sun.jna.platform.win32.COM;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.OaIdl;
import com.sun.jna.platform.win32.WTypes;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.PointerByReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class TypeLib extends Unknown implements ITypeLib {

    public static class ByReference extends TypeLib implements Structure.ByReference {
    }

    public TypeLib() {
    }

    @Override // com.sun.jna.platform.win32.COM.ITypeLib
    public WinNT.HRESULT FindName(WTypes.LPOLESTR lpolestr, WinDef.ULONG ulong, Pointer[] pointerArr, OaIdl.MEMBERID[] memberidArr, WinDef.USHORTByReference uSHORTByReference) {
        return (WinNT.HRESULT) _invokeNativeObject(11, new Object[]{getPointer(), lpolestr, ulong, pointerArr, memberidArr, uSHORTByReference}, WinNT.HRESULT.class);
    }

    @Override // com.sun.jna.platform.win32.COM.ITypeLib
    public WinNT.HRESULT GetDocumentation(int i, WTypes.BSTRByReference bSTRByReference, WTypes.BSTRByReference bSTRByReference2, WinDef.DWORDByReference dWORDByReference, WTypes.BSTRByReference bSTRByReference3) {
        return (WinNT.HRESULT) _invokeNativeObject(9, new Object[]{getPointer(), Integer.valueOf(i), bSTRByReference, bSTRByReference2, dWORDByReference, bSTRByReference3}, WinNT.HRESULT.class);
    }

    @Override // com.sun.jna.platform.win32.COM.ITypeLib
    public WinNT.HRESULT GetLibAttr(PointerByReference pointerByReference) {
        return (WinNT.HRESULT) _invokeNativeObject(7, new Object[]{getPointer(), pointerByReference}, WinNT.HRESULT.class);
    }

    @Override // com.sun.jna.platform.win32.COM.ITypeLib
    public WinNT.HRESULT GetTypeComp(PointerByReference pointerByReference) {
        return (WinNT.HRESULT) _invokeNativeObject(8, new Object[]{getPointer(), pointerByReference}, WinNT.HRESULT.class);
    }

    @Override // com.sun.jna.platform.win32.COM.ITypeLib
    public WinNT.HRESULT GetTypeInfo(WinDef.UINT uint, PointerByReference pointerByReference) {
        return (WinNT.HRESULT) _invokeNativeObject(4, new Object[]{getPointer(), uint, pointerByReference}, WinNT.HRESULT.class);
    }

    @Override // com.sun.jna.platform.win32.COM.ITypeLib
    public WinDef.UINT GetTypeInfoCount() {
        return (WinDef.UINT) _invokeNativeObject(3, new Object[]{getPointer()}, WinDef.UINT.class);
    }

    @Override // com.sun.jna.platform.win32.COM.ITypeLib
    public WinNT.HRESULT GetTypeInfoOfGuid(Guid.GUID guid, PointerByReference pointerByReference) {
        return (WinNT.HRESULT) _invokeNativeObject(6, new Object[]{getPointer(), guid, pointerByReference}, WinNT.HRESULT.class);
    }

    @Override // com.sun.jna.platform.win32.COM.ITypeLib
    public WinNT.HRESULT GetTypeInfoType(WinDef.UINT uint, OaIdl.TYPEKIND.ByReference byReference) {
        return (WinNT.HRESULT) _invokeNativeObject(5, new Object[]{getPointer(), uint, byReference}, WinNT.HRESULT.class);
    }

    @Override // com.sun.jna.platform.win32.COM.ITypeLib
    public WinNT.HRESULT IsName(WTypes.LPOLESTR lpolestr, WinDef.ULONG ulong, WinDef.BOOLByReference bOOLByReference) {
        return (WinNT.HRESULT) _invokeNativeObject(10, new Object[]{getPointer(), lpolestr, ulong, bOOLByReference}, WinNT.HRESULT.class);
    }

    @Override // com.sun.jna.platform.win32.COM.ITypeLib
    public void ReleaseTLibAttr(OaIdl.TLIBATTR tlibattr) {
        _invokeNativeObject(12, new Object[]{getPointer(), tlibattr.getPointer()}, WinNT.HRESULT.class);
    }

    public TypeLib(Pointer pointer) {
        super(pointer);
    }
}
