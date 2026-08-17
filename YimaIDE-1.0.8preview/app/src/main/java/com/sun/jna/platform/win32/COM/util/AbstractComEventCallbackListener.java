package com.sun.jna.platform.win32.COM.util;

import com.sun.jna.platform.win32.COM.IDispatchCallback;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class AbstractComEventCallbackListener implements IComEventCallbackListener {
    IDispatchCallback dispatchCallback = null;

    @Override // com.sun.jna.platform.win32.COM.util.IComEventCallbackListener
    public void setDispatchCallbackListener(IDispatchCallback iDispatchCallback) {
        this.dispatchCallback = iDispatchCallback;
    }
}
