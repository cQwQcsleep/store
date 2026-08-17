package com.sun.jna.platform.win32.COM;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface IPersistStream extends IPersist {
    void GetSizeMax();

    boolean IsDirty();

    void Load(IStream iStream);

    void Save(IStream iStream);
}
