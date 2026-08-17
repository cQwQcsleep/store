package com.sun.jna.platform.win32;

import com.sun.jna.Native;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class Ole32Util {
    public static Guid.GUID generateGUID() {
        Guid.GUID guid = new Guid.GUID();
        WinNT.HRESULT hresultCoCreateGuid = Ole32.INSTANCE.CoCreateGuid(guid);
        if (hresultCoCreateGuid.equals(WinError.S_OK)) {
            return guid;
        }
        throw new RuntimeException(hresultCoCreateGuid.toString());
    }

    public static Guid.GUID getGUIDFromString(String str) {
        Guid.GUID guid = new Guid.GUID();
        WinNT.HRESULT hresultIIDFromString = Ole32.INSTANCE.IIDFromString(str, guid);
        if (hresultIIDFromString.equals(WinError.S_OK)) {
            return guid;
        }
        throw new RuntimeException(hresultIIDFromString.toString());
    }

    public static String getStringFromGUID(Guid.GUID guid) {
        char[] cArr = new char[39];
        int iStringFromGUID2 = Ole32.INSTANCE.StringFromGUID2(new Guid.GUID(guid.getPointer()), cArr, 39);
        if (iStringFromGUID2 != 0) {
            cArr[iStringFromGUID2 - 1] = 0;
            return Native.toString(cArr);
        }
        f63.a("StringFromGUID2");
        return null;
    }
}
