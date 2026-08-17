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
import com.sun.jna.win32.StdCallLibrary;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
@Structure.FieldOrder({"QueryInterfaceCallback", "AddRefCallback", "ReleaseCallback", "GetTypeInfoCountCallback", "GetTypeInfoCallback", "GetIDsOfNamesCallback", "InvokeCallback"})
public class DispatchVTable extends Structure {
    public AddRefCallback AddRefCallback;
    public GetIDsOfNamesCallback GetIDsOfNamesCallback;
    public GetTypeInfoCallback GetTypeInfoCallback;
    public GetTypeInfoCountCallback GetTypeInfoCountCallback;
    public InvokeCallback InvokeCallback;
    public QueryInterfaceCallback QueryInterfaceCallback;
    public ReleaseCallback ReleaseCallback;

    public interface AddRefCallback extends StdCallLibrary.StdCallCallback {
        int invoke(Pointer pointer);
    }

    public static class ByReference extends DispatchVTable implements Structure.ByReference {
    }

    public interface GetIDsOfNamesCallback extends StdCallLibrary.StdCallCallback {
        WinNT.HRESULT invoke(Pointer pointer, Guid.REFIID refiid, WString[] wStringArr, int i, WinDef.LCID lcid, OaIdl.DISPIDByReference dISPIDByReference);
    }

    public interface GetTypeInfoCallback extends StdCallLibrary.StdCallCallback {
        WinNT.HRESULT invoke(Pointer pointer, WinDef.UINT uint, WinDef.LCID lcid, PointerByReference pointerByReference);
    }

    public interface GetTypeInfoCountCallback extends StdCallLibrary.StdCallCallback {
        WinNT.HRESULT invoke(Pointer pointer, WinDef.UINTByReference uINTByReference);
    }

    public interface InvokeCallback extends StdCallLibrary.StdCallCallback {
        WinNT.HRESULT invoke(Pointer pointer, OaIdl.DISPID dispid, Guid.REFIID refiid, WinDef.LCID lcid, WinDef.WORD word, OleAuto.DISPPARAMS.ByReference byReference, Variant.VARIANT.ByReference byReference2, OaIdl.EXCEPINFO.ByReference byReference3, IntByReference intByReference);
    }

    public interface QueryInterfaceCallback extends StdCallLibrary.StdCallCallback {
        WinNT.HRESULT invoke(Pointer pointer, Guid.REFIID refiid, PointerByReference pointerByReference);
    }

    public interface ReleaseCallback extends StdCallLibrary.StdCallCallback {
        int invoke(Pointer pointer);
    }
}
