package com.sun.jna.platform.win32.COM.util;

import com.sun.jna.platform.win32.COM.COMException;
import com.sun.jna.platform.win32.COM.util.annotation.ComInterface;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
@ComInterface(iid = "{B196B284-BAB4-101A-B69C-00AA00341D07}")
public interface IConnectionPoint {
    IComEventCallbackCookie advise(Class<?> cls, IComEventCallbackListener iComEventCallbackListener) throws COMException;

    void unadvise(Class<?> cls, IComEventCallbackCookie iComEventCallbackCookie) throws COMException;
}
