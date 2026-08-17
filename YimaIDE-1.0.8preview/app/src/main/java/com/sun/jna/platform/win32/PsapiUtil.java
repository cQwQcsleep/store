package com.sun.jna.platform.win32;

import com.sun.jna.Native;
import com.sun.jna.ptr.IntByReference;
import java.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class PsapiUtil {
    public static String GetProcessImageFileName(WinNT.HANDLE handle) {
        int i = 2048;
        while (true) {
            char[] cArr = new char[i];
            if (Psapi.INSTANCE.GetProcessImageFileName(handle, cArr, i) != 0) {
                return Native.toString(cArr);
            }
            if (Native.getLastError() != 122) {
                throw new Win32Exception(Native.getLastError());
            }
            i += 2048;
        }
    }

    public static int[] enumProcesses() {
        int[] iArr;
        IntByReference intByReference = new IntByReference();
        int i = 0;
        do {
            i += 1024;
            iArr = new int[i];
            if (!Psapi.INSTANCE.EnumProcesses(iArr, i * 4, intByReference)) {
                throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
            }
        } while (i == intByReference.getValue() / 4);
        return Arrays.copyOf(iArr, intByReference.getValue() / 4);
    }
}
