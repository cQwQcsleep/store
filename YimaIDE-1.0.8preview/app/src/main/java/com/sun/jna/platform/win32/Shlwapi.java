package com.sun.jna.platform.win32;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface Shlwapi extends StdCallLibrary {
    public static final Shlwapi INSTANCE = (Shlwapi) Native.load("Shlwapi", Shlwapi.class, W32APIOptions.DEFAULT_OPTIONS);

    boolean PathIsUNC(String str);

    WinNT.HRESULT StrRetToStr(ShTypes.STRRET strret, Pointer pointer, PointerByReference pointerByReference);
}
