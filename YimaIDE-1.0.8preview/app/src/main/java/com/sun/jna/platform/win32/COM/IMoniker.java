package com.sun.jna.platform.win32.COM;

import com.sun.jna.Pointer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface IMoniker extends IPersistStream {
    void BindToObject();

    void BindToStorage();

    void CommonPrefixWith();

    void ComposeWith();

    void Enum();

    String GetDisplayName(Pointer pointer, Pointer pointer2);

    void GetTimeOfLastChange();

    void Hash();

    void Inverse();

    void IsEqual();

    void IsRunning();

    void IsSystemMoniker();

    void ParseDisplayName();

    void Reduce();

    void RelativePathTo();
}
