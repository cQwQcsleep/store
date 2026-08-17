package com.intellij.util.io;

import java.io.IOException;
import java.nio.file.Path;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public class PersistentEnumerator implements DurableDataEnumerator, ScannableDataEnumeratorEx {
    protected final PersistentEnumeratorBase myEnumerator;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        if (i == 1 || i == 3 || i == 5 || i == 7 || i == 9) {
            objArr[0] = "dataDescriptor";
        } else if (i != 10) {
            objArr[0] = "file";
        } else {
            objArr[0] = "reader";
        }
        objArr[1] = "com/intellij/util/io/PersistentEnumerator";
        switch (i) {
            case 8:
            case 9:
                objArr[2] = "createDefaultEnumerator";
                break;
            case 10:
                objArr[2] = "forEach";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    public static <Data> PersistentEnumeratorBase createDefaultEnumerator(Path path, KeyDescriptor<Data> keyDescriptor, int i, StorageLockContext storageLockContext, int i2, boolean z) throws IOException {
        if (path == null) {
            $$$reportNull$$$0(8);
        }
        if (keyDescriptor == null) {
            $$$reportNull$$$0(9);
        }
        return new PersistentBTreeEnumerator(path, keyDescriptor, i, storageLockContext, i2, false, z);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        PersistentEnumeratorBase persistentEnumeratorBase = this.myEnumerator;
        if (persistentEnumeratorBase != null) {
            persistentEnumeratorBase.close();
        }
    }

    @Override // com.intellij.util.io.DataEnumerator
    public int enumerate(Object obj) throws IOException {
        return this.myEnumerator.enumerate(obj);
    }

    @Override // com.intellij.openapi.Forceable
    public void force() {
        this.myEnumerator.force();
    }

    @Override // com.intellij.openapi.Forceable
    public boolean isDirty() {
        return this.myEnumerator.isDirty();
    }

    public void markCorrupted() {
        this.myEnumerator.markCorrupted();
    }

    @Override // com.intellij.util.io.DataEnumerator
    public Object valueOf(int i) throws IOException {
        return this.myEnumerator.valueOf(i);
    }
}
