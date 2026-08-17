package com.sun.jna.platform.win32.COM;

import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.PointerByReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface IUnknown {
    public static final Guid.IID IID_IUNKNOWN = new Guid.IID("{00000000-0000-0000-C000-000000000046}");

    int AddRef();

    WinNT.HRESULT QueryInterface(Guid.REFIID refiid, PointerByReference pointerByReference);

    int Release();
}
