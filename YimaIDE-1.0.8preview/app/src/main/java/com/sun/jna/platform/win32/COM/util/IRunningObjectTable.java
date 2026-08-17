package com.sun.jna.platform.win32.COM.util;

import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface IRunningObjectTable {
    Iterable<IDispatch> enumRunning();

    <T> List<T> getActiveObjectsByInterface(Class<T> cls);
}
