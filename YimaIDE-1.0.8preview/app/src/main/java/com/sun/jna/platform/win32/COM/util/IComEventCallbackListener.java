package com.sun.jna.platform.win32.COM.util;

import com.sun.jna.platform.win32.COM.IDispatchCallback;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface IComEventCallbackListener {
    void errorReceivingCallbackEvent(String str, Exception exc);

    void setDispatchCallbackListener(IDispatchCallback iDispatchCallback);
}
