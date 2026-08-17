package com.intellij.util.io;

import androidx.collection.ScatterMapKt;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.util.SystemProperties;
import com.intellij.util.io.pagecache.PagedStorage;
import com.intellij.util.io.pagecache.impl.PagesTable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class PagedFileStorageWithRWLockedPageContent implements PagedStorage {
    private final AtomicLong actualSize;
    private volatile Future<?> closingInProgress;
    private final AtomicInteger dirtyPagesCount;
    private final Path file;
    private final boolean nativeBytesOrder;
    private final FilePageCacheLockFree pageCache;
    private final int pageSize;
    private final PagesTable pages;
    private final boolean readOnly;
    private static final Logger LOG = Logger.getInstance(PagedFileStorageWithRWLockedPageContent.class);
    public static final int DEFAULT_PAGE_SIZE = PageCacheUtils.DEFAULT_PAGE_SIZE;
    private static final StorageLockContext DEFAULT_LOCK_CONTEXT = new StorageLockContext(false);
    public static final ThreadLocal<StorageLockContext> THREAD_LOCAL_STORAGE_LOCK_CONTEXT = new ThreadLocal<>();
    private static final int MAX_ATTEMPTS_TO_ACQUIRE_PAGE = SystemProperties.getIntProperty("vfs.lock-free-impl.max-attempts-to-acquire-page", 10000);
    private static final byte[] ZEROES = new byte[8192];

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 11 || i == 13 || i == 19 || i == 20) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 11 || i == 13 || i == 19 || i == 20) ? 2 : 3];
        switch (i) {
            case 1:
            case 4:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 10:
                objArr[0] = "strategy";
                break;
            case 2:
            case 5:
            case 8:
            default:
                objArr[0] = "file";
                break;
            case 3:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case 9:
                objArr[0] = "storageLockContext";
                break;
            case 11:
            case 13:
            case 19:
            case 20:
                objArr[0] = "com/intellij/util/io/PagedFileStorageWithRWLockedPageContent";
                break;
            case 12:
                objArr[0] = "data";
                break;
            case 14:
            case 15:
                objArr[0] = "operation";
                break;
            case 16:
                objArr[0] = "pageToLoad";
                break;
            case 17:
                objArr[0] = "bufferToSave";
                break;
            case 18:
                objArr[0] = "pageBuffer";
                break;
        }
        if (i == 11) {
            objArr[1] = "getFile";
        } else if (i == 13) {
            objArr[1] = "pageByOffset";
        } else if (i == 19 || i == 20) {
            objArr[1] = "findOutAppropriateContext";
        } else {
            objArr[1] = "com/intellij/util/io/PagedFileStorageWithRWLockedPageContent";
        }
        switch (i) {
            case 2:
            case 3:
            case 4:
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
            case 9:
            case 10:
                objArr[2] = "<init>";
                break;
            case 11:
            case 13:
            case 19:
            case 20:
                break;
            case 12:
                objArr[2] = "putBuffer";
                break;
            case 14:
                objArr[2] = "executeOp";
                break;
            case 15:
                objArr[2] = "executeIdempotentOp";
                break;
            case 16:
                objArr[2] = "loadPageData";
                break;
            case 17:
                objArr[2] = "flushPage";
                break;
            case 18:
                objArr[2] = "fillWithZeroes";
                break;
            default:
                objArr[2] = "createWithDefaults";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 11 && i != 13 && i != 19 && i != 20) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    @Override // java.lang.AutoCloseable
    public void close() throws IOException {
        if (isClosed()) {
            return;
        }
        this.pageCache.tryToReclaimAll(this.pages);
        try {
            closeAsync().get();
        } catch (InterruptedException e) {
            InterruptedIOException interruptedIOException = new InterruptedIOException("Closing storage for " + this.file + " was interrupted");
            interruptedIOException.addSuppressed(e);
            throw interruptedIOException;
        } catch (ExecutionException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof IOException) {
                throw ((IOException) cause);
            }
            lh0.a("Can't close storage for ", this.file, cause);
        }
    }

    public synchronized Future<?> closeAsync() {
        try {
            if (!isClosed()) {
                CompletableFuture<Object> completableFuture = new CompletableFuture<>();
                this.closingInProgress = completableFuture;
                this.pageCache.enqueueStoragePagesClosing(this, completableFuture);
            }
        } catch (Throwable th) {
            throw th;
        }
        return this.closingInProgress;
    }

    @Override // com.intellij.openapi.Forceable
    public void force() throws IOException {
        if (isDirty()) {
            this.pages.flushAll();
        }
    }

    public Path getFile() {
        Path path = this.file;
        if (path == null) {
            $$$reportNull$$$0(11);
        }
        return path;
    }

    public boolean isClosed() {
        return this.closingInProgress != null;
    }

    @Override // com.intellij.openapi.Forceable
    public boolean isDirty() {
        return this.dirtyPagesCount.get() > 0;
    }

    public boolean isNativeBytesOrder() {
        return this.nativeBytesOrder;
    }

    public boolean isReadOnly() {
        return this.readOnly;
    }

    public PagesTable pages() {
        return this.pages;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("PagedFileStorage[");
        sb.append(this.file);
        sb.append("]{size: ");
        sb.append(this.actualSize.get());
        sb.append(", dirtyPages: ");
        sb.append(this.dirtyPagesCount.get());
        sb.append("}{pageSize: ");
        sb.append(this.pageSize);
        sb.append(", ");
        sb.append(isClosed() ? "closed " : "");
        sb.append(isReadOnly() ? "readOnly " : "");
        sb.append(isNativeBytesOrder() ? "nativeByteOrder" : "");
        sb.append("}");
        return sb.toString();
    }
}
