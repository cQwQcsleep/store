package com.sun.jna.platform.win32;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface Version extends StdCallLibrary {
    public static final Version INSTANCE = (Version) Native.load("version", Version.class, W32APIOptions.DEFAULT_OPTIONS);

    boolean GetFileVersionInfo(String str, int i, int i2, Pointer pointer);

    int GetFileVersionInfoSize(String str, IntByReference intByReference);

    boolean VerQueryValue(Pointer pointer, String str, PointerByReference pointerByReference, IntByReference intByReference);
}
