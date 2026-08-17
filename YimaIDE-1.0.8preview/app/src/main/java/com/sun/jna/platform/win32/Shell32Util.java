package com.sun.jna.platform.win32;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.WString;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class Shell32Util {
    public static final String[] CommandLineToArgv(String str) {
        WString wString = new WString(str);
        IntByReference intByReference = new IntByReference();
        Pointer pointerCommandLineToArgvW = Shell32.INSTANCE.CommandLineToArgvW(wString, intByReference);
        if (pointerCommandLineToArgvW == null) {
            throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
        }
        try {
            return pointerCommandLineToArgvW.getWideStringArray(0L, intByReference.getValue());
        } finally {
            Kernel32.INSTANCE.LocalFree(pointerCommandLineToArgvW);
        }
    }

    public static String getFolderPath(WinDef.HWND hwnd, int i, WinDef.DWORD dword) {
        char[] cArr = new char[260];
        WinNT.HRESULT hresultSHGetFolderPath = Shell32.INSTANCE.SHGetFolderPath(hwnd, i, null, dword, cArr);
        if (hresultSHGetFolderPath.equals(WinError.S_OK)) {
            return Native.toString(cArr);
        }
        throw new Win32Exception(hresultSHGetFolderPath);
    }

    public static String getKnownFolderPath(Guid.GUID guid) throws Win32Exception {
        int flag = ShlObj.KNOWN_FOLDER_FLAG.NONE.getFlag();
        PointerByReference pointerByReference = new PointerByReference();
        WinNT.HRESULT hresultSHGetKnownFolderPath = Shell32.INSTANCE.SHGetKnownFolderPath(guid, flag, null, pointerByReference);
        if (!W32Errors.SUCCEEDED(hresultSHGetKnownFolderPath.intValue())) {
            throw new Win32Exception(hresultSHGetKnownFolderPath);
        }
        String wideString = pointerByReference.getValue().getWideString(0L);
        Ole32.INSTANCE.CoTaskMemFree(pointerByReference.getValue());
        return wideString;
    }

    public static final String getSpecialFolderPath(int i, boolean z) {
        char[] cArr = new char[260];
        if (Shell32.INSTANCE.SHGetSpecialFolderPath(null, cArr, i, z)) {
            return Native.toString(cArr);
        }
        throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
    }

    public static String getFolderPath(int i) {
        return getFolderPath(null, i, ShlObj.SHGFP_TYPE_CURRENT);
    }
}
