package com.intellij.util.io;

import androidx.collection.ScatterMapKt;
import com.intellij.util.indexing.impl.IndexDebugProperties;
import com.intellij.util.io.pagecache.impl.PageContentLockingStrategy;
import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class StorageLockContext {
    static final StorageLockContext DEFAULT_CONTEXT;
    private static final FilePageCache DEFAULT_FILE_PAGE_CACHE = new FilePageCache(PageCacheUtils.FILE_PAGE_CACHE_OLD_CAPACITY_BYTES);
    private static final FilePageCacheLockFree DEFAULT_FILE_PAGE_CACHE_NEW;
    private final boolean cacheChannels;
    private final PageContentLockingStrategy defaultPageContentLockingStrategy;
    private final boolean disableAssertions;
    private final FilePageCache legacyFilePageCache;
    private final ReentrantReadWriteLock lock;
    private final FilePageCacheLockFree newFilePageCache;
    private final boolean useReadWriteLock;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 3 || i == 4 || i == 5 || i == 6) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 3 || i == 4 || i == 5 || i == 6) ? 2 : 3];
        switch (i) {
            case 1:
            case 2:
                objArr[0] = "operation";
                break;
            case 3:
            case 4:
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[0] = "com/intellij/util/io/StorageLockContext";
                break;
            default:
                objArr[0] = "legacyFilePageCache";
                break;
        }
        if (i == 3) {
            objArr[1] = "getBufferCache";
        } else if (i == 4) {
            objArr[1] = "pageCache";
        } else if (i == 5) {
            objArr[1] = "lockingStrategyWithGlobalLock";
        } else if (i != 6) {
            objArr[1] = "com/intellij/util/io/StorageLockContext";
        } else {
            objArr[1] = "getStatistics";
        }
        switch (i) {
            case 1:
                objArr[2] = "executeOp";
                break;
            case 2:
                objArr[2] = "executeIdempotentOp";
                break;
            case 3:
            case 4:
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 3 && i != 4 && i != 5 && i != 6) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    static {
        FilePageCacheLockFree filePageCacheLockFree;
        if (PageCacheUtils.LOCK_FREE_PAGE_CACHE_ENABLED) {
            long j = PageCacheUtils.FILE_PAGE_CACHE_NEW_CAPACITY_BYTES;
            filePageCacheLockFree = new FilePageCacheLockFree(j, (long) (j * PageCacheUtils.HEAP_CAPACITY_FRACTION));
        } else {
            filePageCacheLockFree = null;
        }
        DEFAULT_FILE_PAGE_CACHE_NEW = filePageCacheLockFree;
        DEFAULT_CONTEXT = new StorageLockContext(false);
    }

    public StorageLockContext(FilePageCache filePageCache, FilePageCacheLockFree filePageCacheLockFree, boolean z, boolean z2, boolean z3) {
        if (filePageCache == null) {
            $$$reportNull$$$0(0);
        }
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        this.lock = reentrantReadWriteLock;
        this.defaultPageContentLockingStrategy = new PageContentLockingStrategy.SharedLockLockingStrategy(reentrantReadWriteLock);
        this.useReadWriteLock = z;
        this.cacheChannels = z2;
        this.disableAssertions = z3;
        this.legacyFilePageCache = filePageCache;
        this.newFilePageCache = filePageCacheLockFree;
    }

    public void assertUnderSegmentAllocationLock() {
        if (IndexDebugProperties.DEBUG) {
            this.legacyFilePageCache.assertUnderSegmentAllocationLock();
        }
    }

    public void checkReadAccess() {
        if (this.disableAssertions || !IndexDebugProperties.DEBUG || this.lock.getReadHoldCount() > 0 || this.lock.writeLock().isHeldByCurrentThread()) {
            return;
        }
        k2d.a("Must hold StorageLock read lock to access PagedFileStorage");
    }

    public void checkReadLockNotHeld() {
        if (this.disableAssertions || readLockHolds() <= 0) {
            return;
        }
        k2d.a("StorageLock.readLock must NOT be held here (write lock is about to be taken?)");
    }

    public void checkWriteAccess() {
        if (this.disableAssertions || !IndexDebugProperties.DEBUG || this.lock.writeLock().isHeldByCurrentThread()) {
            return;
        }
        k2d.a("Must hold StorageLock write lock to access PagedFileStorage");
    }

    public <R> R executeIdempotentOp(Path path, FileChannelInterruptsRetryer.FileChannelIdempotentOperation<R> fileChannelIdempotentOperation, boolean z) throws IOException {
        if (fileChannelIdempotentOperation == null) {
            $$$reportNull$$$0(2);
        }
        if (useChannelCache()) {
            return (R) PageCacheUtils.CHANNELS_CACHE.executeIdempotentOp(path, fileChannelIdempotentOperation, z);
        }
        getBufferCache().incrementUncachedFileAccess();
        OpenChannelsCache.ChannelDescriptor channelDescriptor = new OpenChannelsCache.ChannelDescriptor(path, z);
        try {
            R r = (R) channelDescriptor.channel().executeOperation(fileChannelIdempotentOperation);
            channelDescriptor.close();
            return r;
        } catch (Throwable th) {
            try {
                channelDescriptor.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public <R> R executeOp(Path path, OpenChannelsCache.FileChannelOperation<R> fileChannelOperation, boolean z) throws IOException {
        if (fileChannelOperation == null) {
            $$$reportNull$$$0(1);
        }
        if (useChannelCache()) {
            return (R) PageCacheUtils.CHANNELS_CACHE.executeOp(path, fileChannelOperation, z);
        }
        getBufferCache().incrementUncachedFileAccess();
        OpenChannelsCache.ChannelDescriptor channelDescriptor = new OpenChannelsCache.ChannelDescriptor(path, z);
        try {
            R rExecute = fileChannelOperation.execute(channelDescriptor.channel());
            channelDescriptor.close();
            return rExecute;
        } catch (Throwable th) {
            try {
                channelDescriptor.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public FilePageCache getBufferCache() {
        FilePageCache filePageCache = this.legacyFilePageCache;
        if (filePageCache == null) {
            $$$reportNull$$$0(3);
        }
        return filePageCache;
    }

    public void lockRead() {
        boolean z = this.useReadWriteLock;
        ReentrantReadWriteLock reentrantReadWriteLock = this.lock;
        if (z) {
            reentrantReadWriteLock.readLock().lock();
        } else {
            reentrantReadWriteLock.writeLock().lock();
        }
    }

    public void lockWrite() {
        this.lock.writeLock().lock();
    }

    public Lock readLock() {
        boolean z = this.useReadWriteLock;
        ReentrantReadWriteLock reentrantReadWriteLock = this.lock;
        return z ? reentrantReadWriteLock.readLock() : reentrantReadWriteLock.writeLock();
    }

    public int readLockHolds() {
        return this.lock.getReadHoldCount();
    }

    public void unlockRead() {
        boolean z = this.useReadWriteLock;
        ReentrantReadWriteLock reentrantReadWriteLock = this.lock;
        if (z) {
            reentrantReadWriteLock.readLock().unlock();
        } else {
            reentrantReadWriteLock.writeLock().unlock();
        }
    }

    public void unlockWrite() {
        this.lock.writeLock().unlock();
    }

    public boolean useChannelCache() {
        return this.cacheChannels;
    }

    public Lock writeLock() {
        return this.lock.writeLock();
    }

    public StorageLockContext(boolean z, boolean z2, boolean z3) {
        this(DEFAULT_FILE_PAGE_CACHE, DEFAULT_FILE_PAGE_CACHE_NEW, z, z2, z3);
    }

    public StorageLockContext(boolean z, boolean z2) {
        this(z, z2, false);
    }

    public StorageLockContext(boolean z) {
        this(z, false, false);
    }

    public StorageLockContext() {
        this(false, false, false);
    }
}
