package com.intellij.util.io.storage;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.util.io.PagedFileStorage;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public abstract class AbstractRecordsTable implements IRecordsTable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Logger LOG = Logger.getInstance(AbstractRecordsTable.class);
    private boolean myIsDirty;
    protected final PagedFileStorage myStorage;

    private int getSafelyClosedMagic() {
        return getImplVersion() + 523190100;
    }

    private void markClean() throws IOException {
        if (this.myIsDirty) {
            this.myIsDirty = false;
            this.myStorage.putInt(0L, getSafelyClosedMagic());
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws Exception {
        markClean();
        this.myStorage.close();
    }

    @Override // com.intellij.openapi.Forceable
    public void force() throws IOException {
        markClean();
        this.myStorage.force();
    }

    public abstract int getImplVersion();

    @Override // com.intellij.openapi.Forceable
    public boolean isDirty() {
        return this.myIsDirty || this.myStorage.isDirty();
    }
}
