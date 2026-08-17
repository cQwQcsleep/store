package com.sun.jna.platform.win32.COM;

import com.sun.jna.platform.win32.Guid;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface IPersist extends IUnknown {
    Guid.CLSID GetClassID();
}
