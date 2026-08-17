package com.sun.jna.platform.win32.COM;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.PointerByReference;
import com.sun.jna.win32.StdCallLibrary;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
@Structure.FieldOrder({"QueryInterfaceCallback", "AddRefCallback", "ReleaseCallback"})
public class UnknownVTable extends Structure {
    public AddRefCallback AddRefCallback;
    public QueryInterfaceCallback QueryInterfaceCallback;
    public ReleaseCallback ReleaseCallback;

    public interface AddRefCallback extends StdCallLibrary.StdCallCallback {
        int invoke(Pointer pointer);
    }

    public static class ByReference extends UnknownVTable implements Structure.ByReference {
    }

    public interface QueryInterfaceCallback extends StdCallLibrary.StdCallCallback {
        WinNT.HRESULT invoke(Pointer pointer, Guid.REFIID refiid, PointerByReference pointerByReference);
    }

    public interface ReleaseCallback extends StdCallLibrary.StdCallCallback {
        int invoke(Pointer pointer);
    }
}
