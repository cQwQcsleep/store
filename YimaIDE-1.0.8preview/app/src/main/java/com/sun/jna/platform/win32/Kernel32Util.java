package com.sun.jna.platform.win32;

import com.sun.jna.LastErrorException;
import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.TypeMapper;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import com.sun.jna.win32.W32APITypeMapper;
import defpackage.aca;
import defpackage.kka;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class Kernel32Util implements WinDef {
    public static final String VOLUME_GUID_PATH_PREFIX = "\\\\?\\Volume{";
    public static final String VOLUME_GUID_PATH_SUFFIX = "}\\";

    /* JADX WARN: Code duplicated, block: B:30:0x0041  */
    /* JADX WARN: Code duplicated, block: B:31:0x0042  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.sun.jna.platform.win32.WinNT$HANDLE] */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r5v0, types: [int] */
    public static final String QueryFullProcessImageName(int i, int i2) throws Throwable {
        Win32Exception win32Exception;
        ?? r0 = 0;
        e = null;
        try {
            try {
                Kernel32 kernel32 = Kernel32.INSTANCE;
                WinNT.HANDLE handleOpenProcess = kernel32.OpenProcess(1040, false, i);
                try {
                    if (handleOpenProcess == null) {
                        throw new Win32Exception(kernel32.GetLastError());
                    }
                    String strQueryFullProcessImageName = QueryFullProcessImageName(handleOpenProcess, i2);
                    try {
                        closeHandle(handleOpenProcess);
                    } catch (Win32Exception e) {
                        e = e;
                    }
                    if (e == null) {
                        return strQueryFullProcessImageName;
                    }
                    throw e;
                } catch (Win32Exception e2) {
                    throw e2;
                }
            } catch (Throwable th) {
                th = th;
                r0 = i;
                win32Exception = null;
                try {
                    closeHandle(r0);
                } catch (Win32Exception e3) {
                    if (win32Exception == null) {
                        win32Exception = e3;
                    } else {
                        win32Exception.addSuppressed(e3);
                    }
                }
                if (win32Exception != null) {
                    throw win32Exception;
                }
                throw th;
            }
        } catch (Win32Exception e4) {
            throw e4;
        } catch (Throwable th2) {
            th = th2;
            win32Exception = null;
            closeHandle(r0);
            if (win32Exception != null) {
                throw win32Exception;
            }
            throw th;
        }
    }

    public static void closeHandle(WinNT.HANDLE handle) {
        if (handle == null) {
            return;
        }
        Kernel32 kernel32 = Kernel32.INSTANCE;
        if (!kernel32.CloseHandle(handle)) {
            throw new Win32Exception(kernel32.GetLastError());
        }
    }

    public static void closeHandleRef(WinNT.HANDLEByReference hANDLEByReference) {
        closeHandle(hANDLEByReference == null ? null : hANDLEByReference.getValue());
    }

    public static void closeHandleRefs(WinNT.HANDLEByReference... hANDLEByReferenceArr) {
        Win32Exception win32Exception = null;
        for (WinNT.HANDLEByReference hANDLEByReference : hANDLEByReferenceArr) {
            try {
                closeHandleRef(hANDLEByReference);
            } catch (Win32Exception e) {
                if (win32Exception == null) {
                    win32Exception = e;
                } else {
                    win32Exception.addSuppressedReflected(e);
                }
            }
        }
        if (win32Exception != null) {
            throw win32Exception;
        }
    }

    public static void closeHandles(WinNT.HANDLE... handleArr) {
        Win32Exception win32Exception = null;
        for (WinNT.HANDLE handle : handleArr) {
            try {
                closeHandle(handle);
            } catch (Win32Exception e) {
                if (win32Exception == null) {
                    win32Exception = e;
                } else {
                    win32Exception.addSuppressedReflected(e);
                }
            }
        }
        if (win32Exception != null) {
            throw win32Exception;
        }
    }

    public static void deleteFile(String str) {
        Kernel32 kernel32 = Kernel32.INSTANCE;
        if (!kernel32.DeleteFile(str)) {
            throw new Win32Exception(kernel32.GetLastError());
        }
    }

    public static String expandEnvironmentStrings(String str) {
        if (str == null) {
            return "";
        }
        Kernel32 kernel32 = Kernel32.INSTANCE;
        int iExpandEnvironmentStrings = kernel32.ExpandEnvironmentStrings(str, null, 0);
        if (iExpandEnvironmentStrings == 0) {
            throw new Win32Exception(kernel32.GetLastError());
        }
        TypeMapper typeMapper = W32APITypeMapper.DEFAULT;
        TypeMapper typeMapper2 = W32APITypeMapper.UNICODE;
        Memory memory = typeMapper == typeMapper2 ? new Memory(Native.WCHAR_SIZE * iExpandEnvironmentStrings) : new Memory(iExpandEnvironmentStrings + 1);
        if (kernel32.ExpandEnvironmentStrings(str, memory, iExpandEnvironmentStrings) != 0) {
            return typeMapper == typeMapper2 ? memory.getWideString(0L) : memory.getString(0L);
        }
        throw new Win32Exception(kernel32.GetLastError());
    }

    public static final String extractVolumeGUID(String str) {
        if (str != null && str.length() > 13 && str.startsWith(VOLUME_GUID_PATH_PREFIX) && str.endsWith(VOLUME_GUID_PATH_SUFFIX)) {
            return str.substring(11, str.length() - 2);
        }
        aca.a("Bad volume GUID path format: ", str);
        return null;
    }

    public static long findEnvironmentStringBlockEntryEnd(Pointer pointer, long j, boolean z) {
        long j2 = z ? 2L : 1L;
        while (pointer.getByte(j) != 0) {
            j += j2;
        }
        return j;
    }

    public static String formatMessage(int i) {
        PointerByReference pointerByReference = new PointerByReference();
        if (Kernel32.INSTANCE.FormatMessage(4864, null, i, 0, pointerByReference, 0, null) == 0) {
            throw new LastErrorException(Native.getLastError());
        }
        Pointer value = pointerByReference.getValue();
        try {
            return value.getWideString(0L).trim();
        } finally {
            freeLocalMemory(value);
        }
    }

    public static String formatMessageFromLastErrorCode(int i) {
        return formatMessage(W32Errors.HRESULT_FROM_WIN32(i));
    }

    public static void freeGlobalMemory(Pointer pointer) {
        Kernel32 kernel32 = Kernel32.INSTANCE;
        if (kernel32.GlobalFree(pointer) != null) {
            throw new Win32Exception(kernel32.GetLastError());
        }
    }

    public static void freeLocalMemory(Pointer pointer) {
        Kernel32 kernel32 = Kernel32.INSTANCE;
        if (kernel32.LocalFree(pointer) != null) {
            throw new Win32Exception(kernel32.GetLastError());
        }
    }

    public static String getComputerName() {
        int i = WinBase.MAX_COMPUTERNAME_LENGTH + 1;
        char[] cArr = new char[i];
        IntByReference intByReference = new IntByReference(i);
        Kernel32 kernel32 = Kernel32.INSTANCE;
        if (kernel32.GetComputerName(cArr, intByReference)) {
            return Native.toString(cArr);
        }
        throw new Win32Exception(kernel32.GetLastError());
    }

    public static int getDriveType(String str) {
        return Kernel32.INSTANCE.GetDriveType(str);
    }

    public static String getEnvironmentVariable(String str) {
        Kernel32 kernel32 = Kernel32.INSTANCE;
        int iGetEnvironmentVariable = kernel32.GetEnvironmentVariable(str, null, 0);
        if (iGetEnvironmentVariable == 0) {
            return null;
        }
        if (iGetEnvironmentVariable < 0) {
            throw new Win32Exception(kernel32.GetLastError());
        }
        char[] cArr = new char[iGetEnvironmentVariable];
        if (kernel32.GetEnvironmentVariable(str, cArr, iGetEnvironmentVariable) > 0) {
            return Native.toString(cArr);
        }
        throw new Win32Exception(kernel32.GetLastError());
    }

    public static Map<String, String> getEnvironmentVariables(Pointer pointer, long j) {
        if (pointer == null) {
            return null;
        }
        TreeMap treeMap = new TreeMap();
        boolean zIsWideCharEnvironmentStringBlock = isWideCharEnvironmentStringBlock(pointer, j);
        long j2 = zIsWideCharEnvironmentStringBlock ? 2L : 1L;
        while (true) {
            String environmentStringBlockEntry = readEnvironmentStringBlockEntry(pointer, j, zIsWideCharEnvironmentStringBlock);
            int length = environmentStringBlockEntry.length();
            if (length == 0) {
                return treeMap;
            }
            int iIndexOf = environmentStringBlockEntry.indexOf(61);
            if (iIndexOf < 0) {
                w01.a("Missing variable value separator in ".concat(environmentStringBlockEntry));
                return null;
            }
            treeMap.put(environmentStringBlockEntry.substring(0, iIndexOf), environmentStringBlockEntry.substring(iIndexOf + 1));
            j += ((long) (length + 1)) * j2;
        }
    }

    public static int getFileAttributes(String str) {
        Kernel32 kernel32 = Kernel32.INSTANCE;
        int iGetFileAttributes = kernel32.GetFileAttributes(str);
        if (iGetFileAttributes != -1) {
            return iGetFileAttributes;
        }
        throw new Win32Exception(kernel32.GetLastError());
    }

    /* JADX WARN: Code duplicated, block: B:40:0x0076  */
    /* JADX WARN: Code duplicated, block: B:41:0x0077  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v2, types: [com.sun.jna.platform.win32.WinNT$HANDLE] */
    /* JADX WARN: Type inference failed for: r1v3 */
    public static int getFileType(String str) throws Throwable {
        Throwable th;
        int iGetLastError;
        if (!new File(str).exists()) {
            throw new FileNotFoundException(str);
        }
        ?? r1 = 0;
        win32Exception = null;
        Win32Exception win32Exception = null;
        Win32Exception win32Exception2 = null;
        try {
            try {
                Kernel32 kernel32 = Kernel32.INSTANCE;
                WinNT.HANDLE handleCreateFile = kernel32.CreateFile(str, Integer.MIN_VALUE, 1, new WinBase.SECURITY_ATTRIBUTES(), 3, 128, new WinNT.HANDLEByReference().getValue());
                try {
                    if (WinBase.INVALID_HANDLE_VALUE.equals(handleCreateFile)) {
                        throw new Win32Exception(kernel32.GetLastError());
                    }
                    int iGetFileType = kernel32.GetFileType(handleCreateFile);
                    if (iGetFileType == 0 && (iGetLastError = kernel32.GetLastError()) != 0) {
                        throw new Win32Exception(iGetLastError);
                    }
                    try {
                        closeHandle(handleCreateFile);
                    } catch (Win32Exception e) {
                        win32Exception2 = e;
                    }
                    if (win32Exception2 == null) {
                        return iGetFileType;
                    }
                    throw win32Exception2;
                } catch (Win32Exception e2) {
                    throw e2;
                }
            } catch (Throwable th2) {
                r1 = str;
                th = th2;
                try {
                    closeHandle(r1);
                } catch (Win32Exception e3) {
                    if (r1 == 0) {
                        win32Exception = e3;
                    } else {
                        r1.addSuppressedReflected(e3);
                    }
                }
                if (win32Exception != null) {
                    throw win32Exception;
                }
                throw th;
            }
        } catch (Win32Exception e4) {
            throw e4;
        } catch (Throwable th3) {
            th = th3;
            closeHandle(r1);
            if (win32Exception != null) {
                throw win32Exception;
            }
            throw th;
        }
    }

    public static String getLastErrorMessage() {
        return formatMessageFromLastErrorCode(Kernel32.INSTANCE.GetLastError());
    }

    public static List<String> getLogicalDriveStrings() {
        Kernel32 kernel32 = Kernel32.INSTANCE;
        WinDef.DWORD dwordGetLogicalDriveStrings = kernel32.GetLogicalDriveStrings(new WinDef.DWORD(0L), null);
        if (dwordGetLogicalDriveStrings.intValue() <= 0) {
            throw new Win32Exception(kernel32.GetLastError());
        }
        char[] cArr = new char[dwordGetLogicalDriveStrings.intValue()];
        int iIntValue = kernel32.GetLogicalDriveStrings(dwordGetLogicalDriveStrings, cArr).intValue();
        if (iIntValue > 0) {
            return Native.toStringList(cArr, 0, iIntValue);
        }
        throw new Win32Exception(kernel32.GetLastError());
    }

    public static final WinNT.SYSTEM_LOGICAL_PROCESSOR_INFORMATION[] getLogicalProcessorInformation() {
        int iGetLastError;
        int size = new WinNT.SYSTEM_LOGICAL_PROCESSOR_INFORMATION().size();
        WinDef.DWORDByReference dWORDByReference = new WinDef.DWORDByReference(new WinDef.DWORD(size));
        do {
            Memory memory = new Memory(dWORDByReference.getValue().intValue());
            Kernel32 kernel32 = Kernel32.INSTANCE;
            if (kernel32.GetLogicalProcessorInformation(memory, dWORDByReference)) {
                return (WinNT.SYSTEM_LOGICAL_PROCESSOR_INFORMATION[]) new WinNT.SYSTEM_LOGICAL_PROCESSOR_INFORMATION(memory).toArray(new WinNT.SYSTEM_LOGICAL_PROCESSOR_INFORMATION[dWORDByReference.getValue().intValue() / size]);
            }
            iGetLastError = kernel32.GetLastError();
        } while (iGetLastError == 122);
        kka.a(iGetLastError);
        return null;
    }

    public static final WinNT.SYSTEM_LOGICAL_PROCESSOR_INFORMATION_EX[] getLogicalProcessorInformationEx(int i) {
        int iGetLastError;
        WinDef.DWORDByReference dWORDByReference = new WinDef.DWORDByReference(new WinDef.DWORD(1L));
        do {
            Memory memory = new Memory(dWORDByReference.getValue().intValue());
            Kernel32 kernel32 = Kernel32.INSTANCE;
            if (kernel32.GetLogicalProcessorInformationEx(i, memory, dWORDByReference)) {
                ArrayList arrayList = new ArrayList();
                int i2 = 0;
                while (i2 < dWORDByReference.getValue().intValue()) {
                    WinNT.SYSTEM_LOGICAL_PROCESSOR_INFORMATION_EX system_logical_processor_information_exFromPointer = WinNT.SYSTEM_LOGICAL_PROCESSOR_INFORMATION_EX.fromPointer(memory.share(i2));
                    arrayList.add(system_logical_processor_information_exFromPointer);
                    i2 += system_logical_processor_information_exFromPointer.size;
                }
                return (WinNT.SYSTEM_LOGICAL_PROCESSOR_INFORMATION_EX[]) arrayList.toArray(new WinNT.SYSTEM_LOGICAL_PROCESSOR_INFORMATION_EX[0]);
            }
            iGetLastError = kernel32.GetLastError();
        } while (iGetLastError == 122);
        kka.a(iGetLastError);
        return null;
    }

    public static List<Tlhelp32.MODULEENTRY32W> getModules(int i) {
        Kernel32 kernel32;
        Kernel32 kernel33 = Kernel32.INSTANCE;
        WinNT.HANDLE handleCreateToolhelp32Snapshot = kernel33.CreateToolhelp32Snapshot(Tlhelp32.TH32CS_SNAPMODULE, new WinDef.DWORD(i));
        if (handleCreateToolhelp32Snapshot == null) {
            throw new Win32Exception(kernel33.GetLastError());
        }
        Win32Exception e = null;
        try {
            try {
                Tlhelp32.MODULEENTRY32W moduleentry32w = new Tlhelp32.MODULEENTRY32W();
                if (!kernel33.Module32FirstW(handleCreateToolhelp32Snapshot, moduleentry32w)) {
                    throw new Win32Exception(kernel33.GetLastError());
                }
                ArrayList arrayList = new ArrayList();
                arrayList.add(moduleentry32w);
                Tlhelp32.MODULEENTRY32W moduleentry32w2 = new Tlhelp32.MODULEENTRY32W();
                while (true) {
                    kernel32 = Kernel32.INSTANCE;
                    if (!kernel32.Module32NextW(handleCreateToolhelp32Snapshot, moduleentry32w2)) {
                        break;
                    }
                    arrayList.add(moduleentry32w2);
                    moduleentry32w2 = new Tlhelp32.MODULEENTRY32W();
                }
                int iGetLastError = kernel32.GetLastError();
                if (iGetLastError != 0 && iGetLastError != 18) {
                    throw new Win32Exception(iGetLastError);
                }
                try {
                    closeHandle(handleCreateToolhelp32Snapshot);
                } catch (Win32Exception e2) {
                    e = e2;
                }
                if (e == null) {
                    return arrayList;
                }
                throw e;
            } catch (Win32Exception e3) {
                throw e3;
            }
        } catch (Throwable th) {
            try {
                closeHandle(handleCreateToolhelp32Snapshot);
            } catch (Win32Exception e4) {
                if (0 == 0) {
                    e = e4;
                } else {
                    e.addSuppressedReflected(e4);
                }
            }
            if (e != null) {
                throw e;
            }
            throw th;
        }
    }

    public static final int getPrivateProfileInt(String str, String str2, int i, String str3) {
        return Kernel32.INSTANCE.GetPrivateProfileInt(str, str2, i, str3);
    }

    public static final String[] getPrivateProfileSection(String str, String str2) {
        char[] cArr = new char[32768];
        Kernel32 kernel32 = Kernel32.INSTANCE;
        if (kernel32.GetPrivateProfileSection(str, cArr, new WinDef.DWORD(32768L), str2).intValue() != 0) {
            return new String(cArr).split("\u0000");
        }
        throw new Win32Exception(kernel32.GetLastError());
    }

    public static final String[] getPrivateProfileSectionNames(String str) {
        char[] cArr = new char[65536];
        Kernel32 kernel32 = Kernel32.INSTANCE;
        if (kernel32.GetPrivateProfileSectionNames(cArr, new WinDef.DWORD(65536L), str).intValue() != 0) {
            return new String(cArr).split("\u0000");
        }
        throw new Win32Exception(kernel32.GetLastError());
    }

    public static final String getPrivateProfileString(String str, String str2, String str3, String str4) {
        char[] cArr = new char[1024];
        Kernel32.INSTANCE.GetPrivateProfileString(str, str2, str3, cArr, new WinDef.DWORD(1024L), str4);
        return Native.toString(cArr);
    }

    public static byte[] getResource(String str, String str2, String str3) {
        byte[] byteArray;
        Pointer memory;
        Pointer memory2;
        Kernel32 kernel32 = Kernel32.INSTANCE;
        Win32Exception win32Exception = null;
        WinDef.HMODULE hmoduleLoadLibraryEx = kernel32.LoadLibraryEx(str, null, 2);
        if (hmoduleLoadLibraryEx == null) {
            throw new Win32Exception(kernel32.GetLastError());
        }
        try {
            try {
                memory = new Pointer(Long.parseLong(str2));
            } catch (NumberFormatException unused) {
                memory = new Memory(Native.WCHAR_SIZE * (str2.length() + 1));
                memory.setWideString(0L, str2);
            }
            try {
                memory2 = new Pointer(Long.parseLong(str3));
            } catch (NumberFormatException unused2) {
                memory2 = new Memory(Native.WCHAR_SIZE * (str3.length() + 1));
                memory2.setWideString(0L, str3);
            }
            Kernel32 kernel33 = Kernel32.INSTANCE;
            WinDef.HRSRC hrsrcFindResource = kernel33.FindResource(hmoduleLoadLibraryEx, memory2, memory);
            if (hrsrcFindResource == null) {
                throw new Win32Exception(kernel33.GetLastError());
            }
            WinNT.HANDLE handleLoadResource = kernel33.LoadResource(hmoduleLoadLibraryEx, hrsrcFindResource);
            if (handleLoadResource == null) {
                throw new Win32Exception(kernel33.GetLastError());
            }
            int iSizeofResource = kernel33.SizeofResource(hmoduleLoadLibraryEx, hrsrcFindResource);
            if (iSizeofResource == 0) {
                throw new Win32Exception(kernel33.GetLastError());
            }
            Pointer pointerLockResource = kernel33.LockResource(handleLoadResource);
            if (pointerLockResource == null) {
                throw new IllegalStateException("LockResource returned null.");
            }
            byteArray = pointerLockResource.getByteArray(0L, iSizeofResource);
            if (!kernel33.FreeLibrary(hmoduleLoadLibraryEx)) {
                throw new Win32Exception(kernel33.GetLastError());
            }
            if (win32Exception == null) {
                return byteArray;
            }
            throw win32Exception;
        } catch (Win32Exception e) {
            Kernel32 kernel34 = Kernel32.INSTANCE;
            if (!kernel34.FreeLibrary(hmoduleLoadLibraryEx)) {
                Win32Exception win32Exception2 = new Win32Exception(kernel34.GetLastError());
                win32Exception2.addSuppressedReflected(e);
                throw win32Exception2;
            }
            win32Exception = e;
            byteArray = null;
        } catch (Throwable th) {
            Kernel32 kernel35 = Kernel32.INSTANCE;
            if (kernel35.FreeLibrary(hmoduleLoadLibraryEx)) {
                throw th;
            }
            throw new Win32Exception(kernel35.GetLastError());
        }
    }

    public static Map<String, List<String>> getResourceNames(String str) {
        Pointer memory;
        Kernel32 kernel32 = Kernel32.INSTANCE;
        Win32Exception win32Exception = null;
        WinDef.HMODULE hmoduleLoadLibraryEx = kernel32.LoadLibraryEx(str, null, 2);
        if (hmoduleLoadLibraryEx == null) {
            throw new Win32Exception(kernel32.GetLastError());
        }
        final ArrayList<String> arrayList = new ArrayList();
        final LinkedHashMap linkedHashMap = new LinkedHashMap();
        WinBase.EnumResTypeProc enumResTypeProc = new WinBase.EnumResTypeProc() { // from class: com.sun.jna.platform.win32.Kernel32Util.1
            @Override // com.sun.jna.platform.win32.WinBase.EnumResTypeProc
            public boolean invoke(WinDef.HMODULE hmodule, Pointer pointer, Pointer pointer2) {
                long jNativeValue = Pointer.nativeValue(pointer);
                List list = arrayList;
                if (jNativeValue > 65535) {
                    list.add(pointer.getWideString(0L));
                    return true;
                }
                list.add(Pointer.nativeValue(pointer) + "");
                return true;
            }
        };
        WinBase.EnumResNameProc enumResNameProc = new WinBase.EnumResNameProc() { // from class: com.sun.jna.platform.win32.Kernel32Util.2
            @Override // com.sun.jna.platform.win32.WinBase.EnumResNameProc
            public boolean invoke(WinDef.HMODULE hmodule, Pointer pointer, Pointer pointer2, Pointer pointer3) {
                String wideString;
                if (Pointer.nativeValue(pointer) <= 65535) {
                    wideString = Pointer.nativeValue(pointer) + "";
                } else {
                    wideString = pointer.getWideString(0L);
                }
                long jNativeValue = Pointer.nativeValue(pointer2);
                Map map = linkedHashMap;
                if (jNativeValue >= 65535) {
                    ((List) map.get(wideString)).add(pointer2.getWideString(0L));
                    return true;
                }
                ((List) map.get(wideString)).add(Pointer.nativeValue(pointer2) + "");
                return true;
            }
        };
        try {
            if (!kernel32.EnumResourceTypes(hmoduleLoadLibraryEx, enumResTypeProc, null)) {
                throw new Win32Exception(kernel32.GetLastError());
            }
            for (String str2 : arrayList) {
                linkedHashMap.put(str2, new ArrayList());
                try {
                    memory = new Pointer(Long.parseLong(str2));
                } catch (NumberFormatException unused) {
                    memory = new Memory(Native.WCHAR_SIZE * (str2.length() + 1));
                    memory.setWideString(0L, str2);
                }
                Kernel32 kernel33 = Kernel32.INSTANCE;
                if (!kernel33.EnumResourceNames(hmoduleLoadLibraryEx, memory, enumResNameProc, null)) {
                    throw new Win32Exception(kernel33.GetLastError());
                }
            }
            Kernel32 kernel34 = Kernel32.INSTANCE;
            if (!kernel34.FreeLibrary(hmoduleLoadLibraryEx)) {
                throw new Win32Exception(kernel34.GetLastError());
            }
            if (win32Exception == null) {
                return linkedHashMap;
            }
            throw win32Exception;
        } catch (Win32Exception e) {
            win32Exception = e;
            Kernel32 kernel35 = Kernel32.INSTANCE;
            if (!kernel35.FreeLibrary(hmoduleLoadLibraryEx)) {
                Win32Exception win32Exception2 = new Win32Exception(kernel35.GetLastError());
                win32Exception2.addSuppressedReflected(win32Exception);
                throw win32Exception2;
            }
        } catch (Throwable th) {
            Kernel32 kernel36 = Kernel32.INSTANCE;
            if (kernel36.FreeLibrary(hmoduleLoadLibraryEx)) {
                throw th;
            }
            throw new Win32Exception(kernel36.GetLastError());
        }
    }

    public static String getTempPath() {
        WinDef.DWORD dword = new WinDef.DWORD(260L);
        char[] cArr = new char[dword.intValue()];
        Kernel32 kernel32 = Kernel32.INSTANCE;
        if (kernel32.GetTempPath(dword, cArr).intValue() != 0) {
            return Native.toString(cArr);
        }
        throw new Win32Exception(kernel32.GetLastError());
    }

    public static final List<String> getVolumePathNamesForVolumeName(String str) {
        char[] cArr = new char[WinUser.WM_SYSKEYUP];
        IntByReference intByReference = new IntByReference();
        Kernel32 kernel32 = Kernel32.INSTANCE;
        if (!kernel32.GetVolumePathNamesForVolumeName(str, cArr, WinUser.WM_SYSKEYUP, intByReference)) {
            int iGetLastError = kernel32.GetLastError();
            if (iGetLastError != 234) {
                kka.a(iGetLastError);
                return null;
            }
            int value = intByReference.getValue();
            cArr = new char[value];
            if (!kernel32.GetVolumePathNamesForVolumeName(str, cArr, value, intByReference)) {
                throw new Win32Exception(kernel32.GetLastError());
            }
        }
        return Native.toStringList(cArr, 0, intByReference.getValue());
    }

    public static boolean isWideCharEnvironmentStringBlock(Pointer pointer, long j) {
        byte b = pointer.getByte(j);
        return ByteOrder.LITTLE_ENDIAN.equals(ByteOrder.nativeOrder()) ? isWideCharEnvironmentStringBlock(pointer.getByte(j + 1)) : isWideCharEnvironmentStringBlock(b);
    }

    public static final List<String> queryDosDevice(String str, int i) {
        char[] cArr = new char[i];
        Kernel32 kernel32 = Kernel32.INSTANCE;
        int iQueryDosDevice = kernel32.QueryDosDevice(str, cArr, i);
        if (iQueryDosDevice != 0) {
            return Native.toStringList(cArr, 0, iQueryDosDevice);
        }
        throw new Win32Exception(kernel32.GetLastError());
    }

    public static String readEnvironmentStringBlockEntry(Pointer pointer, long j, boolean z) {
        int iFindEnvironmentStringBlockEntryEnd = (int) (findEnvironmentStringBlockEntryEnd(pointer, j, z) - j);
        if (iFindEnvironmentStringBlockEntryEnd == 0) {
            return "";
        }
        if (z) {
            iFindEnvironmentStringBlockEntryEnd /= 2;
        }
        char[] cArr = new char[iFindEnvironmentStringBlockEntryEnd];
        long j2 = z ? 2L : 1L;
        ByteOrder byteOrderNativeOrder = ByteOrder.nativeOrder();
        int i = 0;
        while (i < iFindEnvironmentStringBlockEntryEnd) {
            byte b = pointer.getByte(j);
            if (z) {
                byte b2 = pointer.getByte(j + 1);
                if (ByteOrder.LITTLE_ENDIAN.equals(byteOrderNativeOrder)) {
                    cArr[i] = (char) ((b & 255) | ((b2 << 8) & Winspool.PRINTER_CHANGE_JOB));
                } else {
                    cArr[i] = (char) (((b << 8) & Winspool.PRINTER_CHANGE_JOB) | (b2 & 255));
                }
            } else {
                cArr[i] = (char) (b & 255);
            }
            i++;
            j += j2;
        }
        return new String(cArr);
    }

    public static final void writePrivateProfileSection(String str, String[] strArr, String str2) {
        StringBuilder sb = new StringBuilder();
        for (String str3 : strArr) {
            sb.append(str3);
            sb.append((char) 0);
        }
        sb.append((char) 0);
        Kernel32 kernel32 = Kernel32.INSTANCE;
        if (!kernel32.WritePrivateProfileSection(str, sb.toString(), str2)) {
            throw new Win32Exception(kernel32.GetLastError());
        }
    }

    public static final void writePrivateProfileString(String str, String str2, String str3, String str4) {
        Kernel32 kernel32 = Kernel32.INSTANCE;
        if (!kernel32.WritePrivateProfileString(str, str2, str3, str4)) {
            throw new Win32Exception(kernel32.GetLastError());
        }
    }

    private static boolean isWideCharEnvironmentStringBlock(byte b) {
        return b == 0;
    }

    public static String formatMessage(WinNT.HRESULT hresult) {
        return formatMessage(hresult.intValue());
    }

    public static final String QueryFullProcessImageName(WinNT.HANDLE handle, int i) {
        Kernel32 kernel32;
        IntByReference intByReference = new IntByReference();
        int i2 = 260;
        do {
            char[] cArr = new char[i2];
            intByReference.setValue(i2);
            kernel32 = Kernel32.INSTANCE;
            if (kernel32.QueryFullProcessImageName(handle, i, cArr, intByReference)) {
                return new String(cArr, 0, intByReference.getValue());
            }
            i2 += 1024;
        } while (kernel32.GetLastError() == 122);
        throw new Win32Exception(kernel32.GetLastError());
    }

    public static Map<String, String> getEnvironmentVariables() {
        Kernel32 kernel32 = Kernel32.INSTANCE;
        Pointer pointerGetEnvironmentStrings = kernel32.GetEnvironmentStrings();
        if (pointerGetEnvironmentStrings != null) {
            try {
                Map<String, String> environmentVariables = getEnvironmentVariables(pointerGetEnvironmentStrings, 0L);
                if (kernel32.FreeEnvironmentStrings(pointerGetEnvironmentStrings)) {
                    return environmentVariables;
                }
                throw new LastErrorException(kernel32.GetLastError());
            } catch (Throwable th) {
                if (!Kernel32.INSTANCE.FreeEnvironmentStrings(pointerGetEnvironmentStrings)) {
                    throw new LastErrorException(Kernel32.INSTANCE.GetLastError());
                }
                throw th;
            }
        }
        throw new LastErrorException(kernel32.GetLastError());
    }
}
