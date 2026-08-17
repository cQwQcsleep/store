package com.intellij.util.io;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public interface DataEnumerator<Data> {
    int enumerate(Data data) throws IOException;

    Data valueOf(int i) throws IOException;
}
