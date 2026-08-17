package com.sun.jna.platform.win32.COM;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.OaIdl;
import com.sun.jna.platform.win32.OleAuto;
import com.sun.jna.platform.win32.Variant;
import com.sun.jna.platform.win32.WTypes;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class TypeInfo extends Unknown implements ITypeInfo {

    public static class ByReference extends TypeInfo implements Structure.ByReference {
    }

    public TypeInfo() {
    }

    @Override // com.sun.jna.platform.win32.COM.ITypeInfo
    public WinNT.HRESULT AddressOfMember(OaIdl.MEMBERID memberid, OaIdl.INVOKEKIND invokekind, PointerByReference pointerByReference) {
        return (WinNT.HRESULT) _invokeNativeObject(15, new Object[]{getPointer(), memberid, invokekind, pointerByReference}, WinNT.HRESULT.class);
    }

    @Override // com.sun.jna.platform.win32.COM.ITypeInfo
    public WinNT.HRESULT CreateInstance(IUnknown iUnknown, Guid.REFIID refiid, PointerByReference pointerByReference) {
        return (WinNT.HRESULT) _invokeNativeObject(16, new Object[]{getPointer(), iUnknown, refiid, pointerByReference}, WinNT.HRESULT.class);
    }

    @Override // com.sun.jna.platform.win32.COM.ITypeInfo
    public WinNT.HRESULT GetContainingTypeLib(PointerByReference pointerByReference, WinDef.UINTByReference uINTByReference) {
        return (WinNT.HRESULT) _invokeNativeObject(18, new Object[]{getPointer(), pointerByReference, uINTByReference}, WinNT.HRESULT.class);
    }

    @Override // com.sun.jna.platform.win32.COM.ITypeInfo
    public WinNT.HRESULT GetDllEntry(OaIdl.MEMBERID memberid, OaIdl.INVOKEKIND invokekind, WTypes.BSTRByReference bSTRByReference, WTypes.BSTRByReference bSTRByReference2, WinDef.WORDByReference wORDByReference) {
        return (WinNT.HRESULT) _invokeNativeObject(13, new Object[]{getPointer(), memberid, invokekind, bSTRByReference, bSTRByReference2, wORDByReference}, WinNT.HRESULT.class);
    }

    @Override // com.sun.jna.platform.win32.COM.ITypeInfo
    public WinNT.HRESULT GetDocumentation(OaIdl.MEMBERID memberid, WTypes.BSTRByReference bSTRByReference, WTypes.BSTRByReference bSTRByReference2, WinDef.DWORDByReference dWORDByReference, WTypes.BSTRByReference bSTRByReference3) {
        return (WinNT.HRESULT) _invokeNativeObject(12, new Object[]{getPointer(), memberid, bSTRByReference, bSTRByReference2, dWORDByReference, bSTRByReference3}, WinNT.HRESULT.class);
    }

    @Override // com.sun.jna.platform.win32.COM.ITypeInfo
    public WinNT.HRESULT GetFuncDesc(WinDef.UINT uint, PointerByReference pointerByReference) {
        return (WinNT.HRESULT) _invokeNativeObject(5, new Object[]{getPointer(), uint, pointerByReference}, WinNT.HRESULT.class);
    }

    @Override // com.sun.jna.platform.win32.COM.ITypeInfo
    public WinNT.HRESULT GetIDsOfNames(WTypes.LPOLESTR[] lpolestrArr, WinDef.UINT uint, OaIdl.MEMBERID[] memberidArr) {
        return (WinNT.HRESULT) _invokeNativeObject(10, new Object[]{getPointer(), lpolestrArr, uint, memberidArr}, WinNT.HRESULT.class);
    }

    @Override // com.sun.jna.platform.win32.COM.ITypeInfo
    public WinNT.HRESULT GetImplTypeFlags(WinDef.UINT uint, IntByReference intByReference) {
        return (WinNT.HRESULT) _invokeNativeObject(9, new Object[]{getPointer(), uint, intByReference}, WinNT.HRESULT.class);
    }

    @Override // com.sun.jna.platform.win32.COM.ITypeInfo
    public WinNT.HRESULT GetMops(OaIdl.MEMBERID memberid, WTypes.BSTRByReference bSTRByReference) {
        return (WinNT.HRESULT) _invokeNativeObject(17, new Object[]{getPointer(), memberid, bSTRByReference}, WinNT.HRESULT.class);
    }

    @Override // com.sun.jna.platform.win32.COM.ITypeInfo
    public WinNT.HRESULT GetNames(OaIdl.MEMBERID memberid, WTypes.BSTR[] bstrArr, WinDef.UINT uint, WinDef.UINTByReference uINTByReference) {
        return (WinNT.HRESULT) _invokeNativeObject(7, new Object[]{getPointer(), memberid, bstrArr, uint, uINTByReference}, WinNT.HRESULT.class);
    }

    @Override // com.sun.jna.platform.win32.COM.ITypeInfo
    public WinNT.HRESULT GetRefTypeInfo(OaIdl.HREFTYPE hreftype, PointerByReference pointerByReference) {
        return (WinNT.HRESULT) _invokeNativeObject(14, new Object[]{getPointer(), hreftype, pointerByReference}, WinNT.HRESULT.class);
    }

    @Override // com.sun.jna.platform.win32.COM.ITypeInfo
    public WinNT.HRESULT GetRefTypeOfImplType(WinDef.UINT uint, OaIdl.HREFTYPEByReference hREFTYPEByReference) {
        return (WinNT.HRESULT) _invokeNativeObject(8, new Object[]{getPointer(), uint, hREFTYPEByReference}, WinNT.HRESULT.class);
    }

    @Override // com.sun.jna.platform.win32.COM.ITypeInfo
    public WinNT.HRESULT GetTypeAttr(PointerByReference pointerByReference) {
        return (WinNT.HRESULT) _invokeNativeObject(3, new Object[]{getPointer(), pointerByReference}, WinNT.HRESULT.class);
    }

    @Override // com.sun.jna.platform.win32.COM.ITypeInfo
    public WinNT.HRESULT GetTypeComp(PointerByReference pointerByReference) {
        return (WinNT.HRESULT) _invokeNativeObject(4, new Object[]{getPointer(), pointerByReference}, WinNT.HRESULT.class);
    }

    @Override // com.sun.jna.platform.win32.COM.ITypeInfo
    public WinNT.HRESULT GetVarDesc(WinDef.UINT uint, PointerByReference pointerByReference) {
        return (WinNT.HRESULT) _invokeNativeObject(6, new Object[]{getPointer(), uint, pointerByReference}, WinNT.HRESULT.class);
    }

    @Override // com.sun.jna.platform.win32.COM.ITypeInfo
    public WinNT.HRESULT Invoke(WinDef.PVOID pvoid, OaIdl.MEMBERID memberid, WinDef.WORD word, OleAuto.DISPPARAMS.ByReference byReference, Variant.VARIANT.ByReference byReference2, OaIdl.EXCEPINFO.ByReference byReference3, WinDef.UINTByReference uINTByReference) {
        return (WinNT.HRESULT) _invokeNativeObject(11, new Object[]{getPointer(), pvoid, memberid, word, byReference, byReference2, byReference3, uINTByReference}, WinNT.HRESULT.class);
    }

    @Override // com.sun.jna.platform.win32.COM.ITypeInfo
    public void ReleaseFuncDesc(OaIdl.FUNCDESC funcdesc) {
        _invokeNativeVoid(20, new Object[]{getPointer(), funcdesc});
    }

    @Override // com.sun.jna.platform.win32.COM.ITypeInfo
    public void ReleaseTypeAttr(OaIdl.TYPEATTR typeattr) {
        _invokeNativeVoid(19, new Object[]{getPointer(), typeattr});
    }

    @Override // com.sun.jna.platform.win32.COM.ITypeInfo
    public void ReleaseVarDesc(OaIdl.VARDESC vardesc) {
        _invokeNativeVoid(21, new Object[]{getPointer(), vardesc});
    }

    public TypeInfo(Pointer pointer) {
        super(pointer);
    }
}
