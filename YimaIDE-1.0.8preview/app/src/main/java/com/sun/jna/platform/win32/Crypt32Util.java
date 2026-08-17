package com.sun.jna.platform.win32;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import java.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class Crypt32Util {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    public static String CertNameToStr(int i, int i2, WinCrypt.DATA_BLOB data_blob) {
        int i3 = Boolean.getBoolean("w32.ascii") ? 1 : Native.WCHAR_SIZE;
        Crypt32 crypt32 = Crypt32.INSTANCE;
        int iCertNameToStr = crypt32.CertNameToStr(i, data_blob, i2, Pointer.NULL, 0);
        Memory memory = new Memory(i3 * iCertNameToStr);
        crypt32.CertNameToStr(i, data_blob, i2, memory, iCertNameToStr);
        return Boolean.getBoolean("w32.ascii") ? memory.getString(0L) : memory.getWideString(0L);
    }

    public static byte[] cryptProtectData(byte[] bArr, byte[] bArr2, int i, String str, WinCrypt.CRYPTPROTECT_PROMPTSTRUCT cryptprotect_promptstruct) {
        Pointer pointer;
        byte[] data;
        Pointer pointer2;
        WinCrypt.DATA_BLOB data_blob = new WinCrypt.DATA_BLOB(bArr);
        WinCrypt.DATA_BLOB data_blob2 = new WinCrypt.DATA_BLOB();
        Win32Exception win32Exception = null;
        WinCrypt.DATA_BLOB data_blob3 = bArr2 == null ? null : new WinCrypt.DATA_BLOB(bArr2);
        try {
            if (Crypt32.INSTANCE.CryptProtectData(data_blob, str, data_blob3, null, cryptprotect_promptstruct, i, data_blob2)) {
                data = data_blob2.getData();
            } else {
                data = null;
                win32Exception = new Win32Exception(Kernel32.INSTANCE.GetLastError());
            }
            Pointer pointer3 = data_blob.pbData;
            if (pointer3 != null) {
                pointer3.clear(data_blob.cbData);
            }
            if (data_blob3 != null && (pointer2 = data_blob3.pbData) != null) {
                pointer2.clear(data_blob3.cbData);
            }
            Pointer pointer4 = data_blob2.pbData;
            if (pointer4 != null) {
                pointer4.clear(data_blob2.cbData);
                try {
                    Kernel32Util.freeLocalMemory(data_blob2.pbData);
                } catch (Win32Exception e) {
                    if (win32Exception == null) {
                        win32Exception = e;
                    } else {
                        win32Exception.addSuppressedReflected(e);
                    }
                }
            }
            if (win32Exception == null) {
                return data;
            }
            if (data == null) {
                throw win32Exception;
            }
            Arrays.fill(data, (byte) 0);
            throw win32Exception;
        } catch (Throwable th) {
            Pointer pointer5 = data_blob.pbData;
            if (pointer5 != null) {
                pointer5.clear(data_blob.cbData);
            }
            if (data_blob3 != null && (pointer = data_blob3.pbData) != null) {
                pointer.clear(data_blob3.cbData);
            }
            Pointer pointer6 = data_blob2.pbData;
            if (pointer6 == null) {
                throw th;
            }
            pointer6.clear(data_blob2.cbData);
            try {
                Kernel32Util.freeLocalMemory(data_blob2.pbData);
                throw th;
            } catch (Win32Exception unused) {
                throw th;
            }
        }
    }

    public static byte[] cryptUnprotectData(byte[] bArr, byte[] bArr2, int i, WinCrypt.CRYPTPROTECT_PROMPTSTRUCT cryptprotect_promptstruct) {
        Pointer pointer;
        byte[] data;
        Pointer pointer2;
        WinCrypt.DATA_BLOB data_blob = new WinCrypt.DATA_BLOB(bArr);
        WinCrypt.DATA_BLOB data_blob2 = new WinCrypt.DATA_BLOB();
        Win32Exception win32Exception = null;
        WinCrypt.DATA_BLOB data_blob3 = bArr2 == null ? null : new WinCrypt.DATA_BLOB(bArr2);
        try {
            if (Crypt32.INSTANCE.CryptUnprotectData(data_blob, null, data_blob3, null, cryptprotect_promptstruct, i, data_blob2)) {
                data = data_blob2.getData();
            } else {
                data = null;
                win32Exception = new Win32Exception(Kernel32.INSTANCE.GetLastError());
            }
            Pointer pointer3 = data_blob.pbData;
            if (pointer3 != null) {
                pointer3.clear(data_blob.cbData);
            }
            if (data_blob3 != null && (pointer2 = data_blob3.pbData) != null) {
                pointer2.clear(data_blob3.cbData);
            }
            Pointer pointer4 = data_blob2.pbData;
            if (pointer4 != null) {
                pointer4.clear(data_blob2.cbData);
                try {
                    Kernel32Util.freeLocalMemory(data_blob2.pbData);
                } catch (Win32Exception e) {
                    if (win32Exception == null) {
                        win32Exception = e;
                    } else {
                        win32Exception.addSuppressedReflected(e);
                    }
                }
            }
            if (win32Exception == null) {
                return data;
            }
            if (data == null) {
                throw win32Exception;
            }
            Arrays.fill(data, (byte) 0);
            throw win32Exception;
        } catch (Throwable th) {
            Pointer pointer5 = data_blob.pbData;
            if (pointer5 != null) {
                pointer5.clear(data_blob.cbData);
            }
            if (data_blob3 != null && (pointer = data_blob3.pbData) != null) {
                pointer.clear(data_blob3.cbData);
            }
            Pointer pointer6 = data_blob2.pbData;
            if (pointer6 == null) {
                throw th;
            }
            pointer6.clear(data_blob2.cbData);
            try {
                Kernel32Util.freeLocalMemory(data_blob2.pbData);
                throw th;
            } catch (Win32Exception unused) {
                throw th;
            }
        }
    }

    public static byte[] cryptProtectData(byte[] bArr, int i) {
        return cryptProtectData(bArr, null, i, "", null);
    }

    public static byte[] cryptUnprotectData(byte[] bArr, int i) {
        return cryptUnprotectData(bArr, null, i, null);
    }

    public static byte[] cryptProtectData(byte[] bArr) {
        return cryptProtectData(bArr, 0);
    }

    public static byte[] cryptUnprotectData(byte[] bArr) {
        return cryptUnprotectData(bArr, 0);
    }
}
