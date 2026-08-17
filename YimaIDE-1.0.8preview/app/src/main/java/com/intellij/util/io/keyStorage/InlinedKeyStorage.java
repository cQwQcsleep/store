package com.intellij.util.io.keyStorage;

import com.intellij.util.io.InlineKeyDescriptor;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class InlinedKeyStorage<Data> implements AppendableObjectStorage<Data> {
    private final InlineKeyDescriptor<Data> myDescriptor;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        if (i != 1) {
            objArr[0] = "descriptor";
        } else {
            objArr[0] = "processor";
        }
        objArr[1] = "com/intellij/util/io/keyStorage/InlinedKeyStorage";
        if (i != 1) {
            objArr[2] = "<init>";
        } else {
            objArr[2] = "processAll";
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    public InlinedKeyStorage(InlineKeyDescriptor<Data> inlineKeyDescriptor) {
        if (inlineKeyDescriptor == null) {
            $$$reportNull$$$0(0);
        }
        this.myDescriptor = inlineKeyDescriptor;
    }

    @Override // com.intellij.util.io.keyStorage.AppendableObjectStorage
    public int append(Data data) throws IOException {
        return this.myDescriptor.toInt(data);
    }

    @Override // com.intellij.util.io.keyStorage.AppendableObjectStorage
    public boolean checkBytesAreTheSame(int i, Data data) {
        return false;
    }

    @Override // com.intellij.util.io.keyStorage.AppendableObjectStorage
    public void clear() throws IOException {
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
    }

    @Override // com.intellij.openapi.Forceable
    public void force() {
    }

    @Override // com.intellij.util.io.keyStorage.AppendableObjectStorage
    public int getCurrentLength() {
        return -1;
    }

    @Override // com.intellij.openapi.Forceable
    public boolean isDirty() {
        return false;
    }

    @Override // com.intellij.util.io.keyStorage.AppendableObjectStorage
    public boolean processAll(AppendableObjectStorage.StorageObjectProcessor<? super Data> storageObjectProcessor) throws IOException {
        if (storageObjectProcessor == null) {
            $$$reportNull$$$0(1);
        }
        throw new UnsupportedOperationException();
    }

    @Override // com.intellij.util.io.keyStorage.AppendableObjectStorage
    public Data read(int i, boolean z) throws IOException {
        return this.myDescriptor.fromInt(i);
    }
}
