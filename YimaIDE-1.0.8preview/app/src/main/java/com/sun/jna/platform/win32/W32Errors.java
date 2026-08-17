package com.sun.jna.platform.win32;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class W32Errors implements WinError {
    public static final boolean FAILED(WinNT.HRESULT hresult) {
        return hresult != null && FAILED(hresult.intValue());
    }

    public static final int FILTER_HRESULT_FROM_FLT_NTSTATUS(int i) {
        return (i & (-2147418113)) | WinNT.STANDARD_RIGHTS_ALL;
    }

    public static final int HRESULT_CODE(int i) {
        return i & 65535;
    }

    public static final int HRESULT_FACILITY(int i) {
        return (i >> 16) & 8191;
    }

    public static final WinNT.HRESULT HRESULT_FROM_WIN32(int i) {
        if (i > 0) {
            i = (i & 65535) | (-2147024896);
        }
        return new WinNT.HRESULT(i);
    }

    public static short HRESULT_SEVERITY(int i) {
        return (short) ((i >> 31) & 1);
    }

    public static int MAKE_HRESULT(short s, short s2, short s3) {
        return (s << 31) | (s2 << 16) | s3;
    }

    public static final int MAKE_SCODE(short s, short s2, short s3) {
        return (s << 31) | (s2 << 16) | s3;
    }

    public static final int SCODE_CODE(int i) {
        return i & 65535;
    }

    public static final int SCODE_FACILITY(short s) {
        return ((short) (s >> 16)) & 8191;
    }

    public static short SCODE_SEVERITY(short s) {
        return (short) (((short) (s >> 31)) & 1);
    }

    public static final boolean SUCCEEDED(WinNT.HRESULT hresult) {
        return hresult == null || SUCCEEDED(hresult.intValue());
    }

    public static final boolean FAILED(int i) {
        return i < 0;
    }

    public static final boolean SUCCEEDED(int i) {
        return i >= 0;
    }
}
