package com.intellij.util.io.keyStorage;

import com.intellij.openapi.Forceable;
import java.io.Closeable;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public interface AppendableObjectStorage<Data> extends Forceable, Closeable {

    @FunctionalInterface
    public interface StorageObjectProcessor<Data> {
        boolean process(int i, Data data) throws IOException;
    }

    int append(Data data) throws IOException;

    boolean checkBytesAreTheSame(int i, Data data) throws IOException;

    void clear() throws IOException;

    int getCurrentLength();

    boolean processAll(StorageObjectProcessor<? super Data> storageObjectProcessor) throws IOException;

    Data read(int i, boolean z) throws IOException;
}
