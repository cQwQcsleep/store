package com.sun.jna.platform.win32.COM;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinBase;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.PointerByReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class RunningObjectTable extends Unknown implements IRunningObjectTable {

    public static class ByReference extends RunningObjectTable implements Structure.ByReference {
    }

    public RunningObjectTable() {
    }

    @Override // com.sun.jna.platform.win32.COM.IRunningObjectTable
    public WinNT.HRESULT EnumRunning(PointerByReference pointerByReference) {
        return (WinNT.HRESULT) _invokeNativeObject(9, new Object[]{getPointer(), pointerByReference}, WinNT.HRESULT.class);
    }

    @Override // com.sun.jna.platform.win32.COM.IRunningObjectTable
    public WinNT.HRESULT GetObject(Pointer pointer, PointerByReference pointerByReference) {
        return (WinNT.HRESULT) _invokeNativeObject(6, new Object[]{getPointer(), pointer, pointerByReference}, WinNT.HRESULT.class);
    }

    @Override // com.sun.jna.platform.win32.COM.IRunningObjectTable
    public WinNT.HRESULT GetTimeOfLastChange(Pointer pointer, WinBase.FILETIME.ByReference byReference) {
        return (WinNT.HRESULT) _invokeNativeObject(8, new Object[]{getPointer(), pointer, byReference}, WinNT.HRESULT.class);
    }

    @Override // com.sun.jna.platform.win32.COM.IRunningObjectTable
    public WinNT.HRESULT IsRunning(Pointer pointer) {
        return (WinNT.HRESULT) _invokeNativeObject(5, new Object[]{getPointer(), pointer}, WinNT.HRESULT.class);
    }

    @Override // com.sun.jna.platform.win32.COM.IRunningObjectTable
    public WinNT.HRESULT NoteChangeTime(WinDef.DWORD dword, WinBase.FILETIME filetime) {
        return (WinNT.HRESULT) _invokeNativeObject(7, new Object[]{getPointer(), dword, filetime}, WinNT.HRESULT.class);
    }

    @Override // com.sun.jna.platform.win32.COM.IRunningObjectTable
    public WinNT.HRESULT Register(WinDef.DWORD dword, Pointer pointer, Pointer pointer2, WinDef.DWORDByReference dWORDByReference) {
        return (WinNT.HRESULT) _invokeNativeObject(3, new Object[]{getPointer(), dword, pointer, pointer2, dWORDByReference}, WinNT.HRESULT.class);
    }

    @Override // com.sun.jna.platform.win32.COM.IRunningObjectTable
    public WinNT.HRESULT Revoke(WinDef.DWORD dword) {
        return (WinNT.HRESULT) _invokeNativeObject(4, new Object[]{getPointer(), dword}, WinNT.HRESULT.class);
    }

    public RunningObjectTable(Pointer pointer) {
        super(pointer);
    }
}
