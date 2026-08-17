package com.sun.jna.platform.win32.COM.util;

import com.sun.jna.platform.win32.OaIdl;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface IDispatch extends IUnknown {
    <T> T getProperty(Class<T> cls, OaIdl.DISPID dispid, Object... objArr);

    <T> T getProperty(Class<T> cls, String str, Object... objArr);

    <T> T invokeMethod(Class<T> cls, OaIdl.DISPID dispid, Object... objArr);

    <T> T invokeMethod(Class<T> cls, String str, Object... objArr);

    <T> void setProperty(OaIdl.DISPID dispid, T t);

    <T> void setProperty(String str, T t);
}
