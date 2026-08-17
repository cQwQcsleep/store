package com.sun.jna.platform.win32.COM;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.Variant;
import com.sun.jna.platform.win32.WTypes;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class RecordInfo extends Unknown implements IRecordInfo {

    public static class ByReference extends RecordInfo implements Structure.ByReference {
    }

    public RecordInfo() {
    }

    @Override // com.sun.jna.platform.win32.COM.IRecordInfo
    public WinNT.HRESULT GetField(WinDef.PVOID pvoid, WString wString, Variant.VARIANT variant) {
        return null;
    }

    @Override // com.sun.jna.platform.win32.COM.IRecordInfo
    public WinNT.HRESULT GetFieldNames(WinDef.ULONG ulong, WTypes.BSTR bstr) {
        return null;
    }

    @Override // com.sun.jna.platform.win32.COM.IRecordInfo
    public WinNT.HRESULT GetFieldNoCopy(WinDef.PVOID pvoid, WString wString, Variant.VARIANT variant, WinDef.PVOID pvoid2) {
        return null;
    }

    @Override // com.sun.jna.platform.win32.COM.IRecordInfo
    public WinNT.HRESULT GetGuid(Guid.GUID guid) {
        return null;
    }

    @Override // com.sun.jna.platform.win32.COM.IRecordInfo
    public WinNT.HRESULT GetName(WTypes.BSTR bstr) {
        return null;
    }

    @Override // com.sun.jna.platform.win32.COM.IRecordInfo
    public WinNT.HRESULT GetSize(WinDef.ULONG ulong) {
        return null;
    }

    @Override // com.sun.jna.platform.win32.COM.IRecordInfo
    public WinNT.HRESULT GetTypeInfo(ITypeInfo iTypeInfo) {
        return null;
    }

    @Override // com.sun.jna.platform.win32.COM.IRecordInfo
    public WinDef.BOOL IsMatchingType(IRecordInfo iRecordInfo) {
        return null;
    }

    @Override // com.sun.jna.platform.win32.COM.IRecordInfo
    public WinNT.HRESULT PutField(WinDef.ULONG ulong, WinDef.PVOID pvoid, WString wString, Variant.VARIANT variant) {
        return null;
    }

    @Override // com.sun.jna.platform.win32.COM.IRecordInfo
    public WinNT.HRESULT PutFieldNoCopy(WinDef.ULONG ulong, WinDef.PVOID pvoid, WString wString, Variant.VARIANT variant) {
        return null;
    }

    @Override // com.sun.jna.platform.win32.COM.IRecordInfo
    public WinNT.HRESULT RecordClear(WinDef.PVOID pvoid) {
        return null;
    }

    @Override // com.sun.jna.platform.win32.COM.IRecordInfo
    public WinNT.HRESULT RecordCopy(WinDef.PVOID pvoid, WinDef.PVOID pvoid2) {
        return null;
    }

    @Override // com.sun.jna.platform.win32.COM.IRecordInfo
    public WinDef.PVOID RecordCreate() {
        return null;
    }

    @Override // com.sun.jna.platform.win32.COM.IRecordInfo
    public WinNT.HRESULT RecordCreateCopy(WinDef.PVOID pvoid, WinDef.PVOID pvoid2) {
        return null;
    }

    @Override // com.sun.jna.platform.win32.COM.IRecordInfo
    public WinNT.HRESULT RecordDestroy(WinDef.PVOID pvoid) {
        return null;
    }

    @Override // com.sun.jna.platform.win32.COM.IRecordInfo
    public WinNT.HRESULT RecordInit(WinDef.PVOID pvoid) {
        return null;
    }

    public RecordInfo(Pointer pointer) {
        super(pointer);
    }
}
