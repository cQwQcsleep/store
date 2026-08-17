package com.sun.jna.platform.win32.COM;

import com.sun.jna.platform.win32.WinNT;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class COMException extends RuntimeException {
    private static final long serialVersionUID = 1;
    private final WinNT.HRESULT hresult;

    public COMException() {
        this("", (Throwable) null);
    }

    public WinNT.HRESULT getHresult() {
        return this.hresult;
    }

    public boolean matchesErrorCode(int i) {
        WinNT.HRESULT hresult = this.hresult;
        return hresult != null && hresult.intValue() == i;
    }

    public COMException(String str) {
        this(str, (Throwable) null);
    }

    public COMException(Throwable th) {
        this((String) null, th);
    }

    public COMException(String str, Throwable th) {
        super(str, th);
        this.hresult = null;
    }

    public COMException(String str, WinNT.HRESULT hresult) {
        super(str);
        this.hresult = hresult;
    }
}
