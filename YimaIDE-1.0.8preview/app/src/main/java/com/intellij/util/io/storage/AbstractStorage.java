package com.intellij.util.io.storage;

import androidx.collection.ScatterMapKt;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.util.ConcurrencyUtil;
import com.intellij.util.SystemProperties;
import com.intellij.util.ThrowableRunnable;
import com.intellij.util.io.IOUtil;
import com.intellij.util.io.StorageLockContext;
import com.intellij.util.io.storage.AbstractStorage;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public abstract class AbstractStorage implements IStorage {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    protected StorageLockContext myContext;
    protected DataTable myDataTable;
    protected AbstractRecordsTable myRecordsTable;
    public static final StorageLockContext SHARED = new StorageLockContext(true, true);
    public static final int PAGE_SIZE = SystemProperties.getIntProperty("idea.io.page.size", 8192);
    protected static final Logger LOG = Logger.getInstance(AbstractStorage.class);

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        if (i != 3 && i != 6) {
            switch (i) {
                case 8:
                case 9:
                    objArr[0] = "path";
                    break;
                case 10:
                case 11:
                    objArr[0] = "bytes";
                    break;
                case 12:
                case 13:
                case 14:
                case 15:
                    objArr[0] = "runnable";
                    break;
                default:
                    objArr[0] = "storageFilePath";
                    break;
            }
        } else {
            objArr[0] = "context";
        }
        objArr[1] = "com/intellij/util/io/storage/AbstractStorage";
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[2] = "<init>";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[2] = "tryInit";
                break;
            case 8:
                objArr[2] = "compact";
                break;
            case 9:
                objArr[2] = "createOrTruncateFile";
                break;
            case 10:
                objArr[2] = "writeBytes";
                break;
            case 11:
                objArr[2] = "replaceBytes";
                break;
            case 12:
            case 13:
                objArr[2] = "withReadLock";
                break;
            case 14:
            case 15:
                objArr[2] = "withWriteLock";
                break;
            default:
                objArr[2] = "deleteFiles";
                break;
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    public static /* synthetic */ void a(AbstractStorage abstractStorage) throws IOException {
        abstractStorage.myDataTable.force();
        abstractStorage.myRecordsTable.force();
    }

    public static /* synthetic */ void b(AbstractStorage abstractStorage) {
        Logger logger = LOG;
        IOUtil.closeSafe(logger, abstractStorage.myRecordsTable);
        IOUtil.closeSafe(logger, abstractStorage.myDataTable);
    }

    public void dispose() throws Throwable {
        withWriteLock(new ThrowableRunnable() { // from class: wq
            @Override // com.intellij.util.ThrowableRunnable
            public final void run() {
                AbstractStorage.b(this.a);
            }
        });
    }

    @Override // com.intellij.openapi.Forceable
    public void force() throws Throwable {
        withWriteLock(new ThrowableRunnable() { // from class: xq
            @Override // com.intellij.util.ThrowableRunnable
            public final void run() throws IOException {
                AbstractStorage.a(this.a);
            }
        });
    }

    @Override // com.intellij.openapi.Forceable
    public boolean isDirty() {
        return this.myDataTable.isDirty() || this.myRecordsTable.isDirty();
    }

    public <E extends Throwable> void withWriteLock(ThrowableRunnable<E> throwableRunnable) throws Throwable {
        if (throwableRunnable == null) {
            $$$reportNull$$$0(15);
        }
        ConcurrencyUtil.withLock(this.myContext.writeLock(), throwableRunnable);
    }
}
