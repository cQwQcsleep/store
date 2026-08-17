package com.intellij.util.io;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public interface DataExternalizer<T> {
    T read(DataInput dataInput) throws IOException;

    void save(DataOutput dataOutput, T t) throws IOException;
}
