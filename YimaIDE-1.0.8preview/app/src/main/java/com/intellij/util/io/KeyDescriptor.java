package com.intellij.util.io;

import com.intellij.util.containers.hash.EqualityPolicy;
import java.io.DataOutput;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public interface KeyDescriptor<T> extends EqualityPolicy<T>, DataExternalizer<T> {
    @Override // com.intellij.util.containers.hash.EqualityPolicy
    int getHashCode(T t);

    void save(DataOutput dataOutput, T t) throws IOException;
}
