package com.sun.jna.platform.win32.COM.util;

import com.sun.jna.platform.win32.WinDef;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ComEventCallbackCookie implements IComEventCallbackCookie {
    WinDef.DWORD value;

    public ComEventCallbackCookie(WinDef.DWORD dword) {
        this.value = dword;
    }

    public WinDef.DWORD getValue() {
        return this.value;
    }
}
