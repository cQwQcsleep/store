package com.sun.jna.platform.win32;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import defpackage.b9g;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class VersionUtil {
    public static VerRsrc.VS_FIXEDFILEINFO getFileVersionInfo(String str) {
        IntByReference intByReference = new IntByReference();
        Version version = Version.INSTANCE;
        int iGetFileVersionInfoSize = version.GetFileVersionInfoSize(str, intByReference);
        if (iGetFileVersionInfoSize == 0) {
            throw new Win32Exception(Native.getLastError());
        }
        Memory memory = new Memory(iGetFileVersionInfoSize);
        PointerByReference pointerByReference = new PointerByReference();
        if (!version.GetFileVersionInfo(str, 0, iGetFileVersionInfoSize, memory)) {
            throw new Win32Exception(Native.getLastError());
        }
        if (!version.VerQueryValue(memory, "\\", pointerByReference, new IntByReference())) {
            b9g.a("Unable to extract version info from the file: \"", str, "\"");
            return null;
        }
        VerRsrc.VS_FIXEDFILEINFO vs_fixedfileinfo = new VerRsrc.VS_FIXEDFILEINFO(pointerByReference.getValue());
        vs_fixedfileinfo.read();
        return vs_fixedfileinfo;
    }
}
