package com.intellij.util.io.storage;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.util.io.PagedFileStorage;
import java.io.IOException;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class DataTable implements IDataTable {
    private static final Logger LOG = Logger.getInstance(DataTable.class);
    private final PagedFileStorage myFile;
    private volatile boolean myIsDirty;
    private volatile int myWasteSize;

    private void fillInHeader(int i, int i2) throws IOException {
        this.myFile.putInt(0L, i);
        this.myFile.putInt(4L, i2);
    }

    private void markClean() throws IOException {
        if (this.myIsDirty) {
            this.myIsDirty = false;
            fillInHeader(523190095, this.myWasteSize);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws Exception {
        markClean();
        this.myFile.close();
    }

    @Override // com.intellij.openapi.Forceable
    public void force() throws IOException {
        markClean();
        this.myFile.force();
    }

    @Override // com.intellij.openapi.Forceable
    public boolean isDirty() {
        return this.myIsDirty || this.myFile.isDirty();
    }
}
