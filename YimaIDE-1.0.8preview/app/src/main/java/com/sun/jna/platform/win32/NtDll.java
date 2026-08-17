package com.sun.jna.platform.win32;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface NtDll extends StdCallLibrary {
    public static final NtDll INSTANCE = (NtDll) Native.load("NtDll", NtDll.class, W32APIOptions.DEFAULT_OPTIONS);

    int NtQuerySecurityObject(WinNT.HANDLE handle, int i, Pointer pointer, int i2, IntByReference intByReference);

    int NtSetSecurityObject(WinNT.HANDLE handle, int i, Pointer pointer);

    int RtlNtStatusToDosError(int i);

    int ZwQueryKey(WinNT.HANDLE handle, int i, Structure structure, int i2, IntByReference intByReference);
}
