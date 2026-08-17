package com.sun.jna.platform.win32;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class VersionHelpers {
    public static boolean IsWindows10OrGreater() {
        return IsWindowsVersionOrGreater(10, 0, 0);
    }

    public static boolean IsWindows7OrGreater() {
        return IsWindowsVersionOrGreater(6, 1, 0);
    }

    public static boolean IsWindows7SP1OrGreater() {
        return IsWindowsVersionOrGreater(6, 1, 1);
    }

    public static boolean IsWindows8OrGreater() {
        return IsWindowsVersionOrGreater(6, 2, 0);
    }

    public static boolean IsWindows8Point1OrGreater() {
        return IsWindowsVersionOrGreater(6, 3, 0);
    }

    public static boolean IsWindowsServer() {
        WinNT.OSVERSIONINFOEX osversioninfoex = new WinNT.OSVERSIONINFOEX();
        osversioninfoex.dwOSVersionInfoSize = new WinDef.DWORD(osversioninfoex.size());
        osversioninfoex.wProductType = (byte) 1;
        Kernel32 kernel32 = Kernel32.INSTANCE;
        return !kernel32.VerifyVersionInfoW(osversioninfoex, 128, kernel32.VerSetConditionMask(0L, 128, (byte) 1));
    }

    public static boolean IsWindowsVersionOrGreater(int i, int i2, int i3) {
        WinNT.OSVERSIONINFOEX osversioninfoex = new WinNT.OSVERSIONINFOEX();
        osversioninfoex.dwOSVersionInfoSize = new WinDef.DWORD(osversioninfoex.size());
        osversioninfoex.dwMajorVersion = new WinDef.DWORD(i);
        osversioninfoex.dwMinorVersion = new WinDef.DWORD(i2);
        osversioninfoex.wServicePackMajor = new WinDef.WORD(i3);
        Kernel32 kernel32 = Kernel32.INSTANCE;
        return kernel32.VerifyVersionInfoW(osversioninfoex, 35, kernel32.VerSetConditionMask(kernel32.VerSetConditionMask(kernel32.VerSetConditionMask(0L, 2, (byte) 3), 1, (byte) 3), 32, (byte) 3));
    }

    public static boolean IsWindowsVistaOrGreater() {
        return IsWindowsVersionOrGreater(6, 0, 0);
    }

    public static boolean IsWindowsVistaSP1OrGreater() {
        return IsWindowsVersionOrGreater(6, 0, 1);
    }

    public static boolean IsWindowsVistaSP2OrGreater() {
        return IsWindowsVersionOrGreater(6, 0, 2);
    }

    public static boolean IsWindowsXPOrGreater() {
        return IsWindowsVersionOrGreater(5, 1, 0);
    }

    public static boolean IsWindowsXPSP1OrGreater() {
        return IsWindowsVersionOrGreater(5, 1, 1);
    }

    public static boolean IsWindowsXPSP2OrGreater() {
        return IsWindowsVersionOrGreater(5, 1, 2);
    }

    public static boolean IsWindowsXPSP3OrGreater() {
        return IsWindowsVersionOrGreater(5, 1, 3);
    }
}
