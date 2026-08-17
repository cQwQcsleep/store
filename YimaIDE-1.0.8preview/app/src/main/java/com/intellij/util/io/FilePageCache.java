package com.intellij.util.io;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.util.SmartList;
import com.intellij.util.SystemProperties;
import com.intellij.util.containers.hash.LongLinkedHashMap;
import com.intellij.util.lang.CompoundRuntimeException;
import defpackage.lq4;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectLinkedOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class FilePageCache {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final long cachedSizeLimit;
    private int myFastCacheHits;
    private int myHits;
    private long myLoadedPages;
    private volatile int myMappingChangeCount;
    private long myMaxLoadedSize;
    private volatile int myMaxRegisteredFiles;
    private long myPageDisposalUs;
    private long myPageLoadUs;
    private int myPageLoadsAboveSizeThreshold;
    private int myRegularPageLoads;
    private volatile int myUncachedFileAccess;
    private final ReentrantLock pagesAccessLock;
    private final ReentrantLock pagesAllocationLock;
    private final LongLinkedHashMap<DirectBufferWrapper> pagesByPageId;
    private final Long2ObjectLinkedOpenHashMap<DirectBufferWrapper> pagesToRemoveByPageId;
    private final Map<Path, Exception> stackTracesOfStorageRegistration;
    private final Map<Path, PagedFileStorage> storageByAbsolutePath;
    private final Int2ObjectMap<PagedFileStorage> storageById = new Int2ObjectOpenHashMap();
    private long totalSizeCached;
    private static final Logger LOG = Logger.getInstance(FilePageCache.class);
    private static final boolean THROW_ERROR_ON_DUPLICATE_STORAGE_REGISTRATION = SystemProperties.getBooleanProperty("FilePageCache.THROW_ERROR_ON_DUPLICATE_STORAGE_REGISTRATION", true);
    private static final boolean KEEP_STACK_TRACE_AT_STORAGE_REGISTRATION = SystemProperties.getBooleanProperty("FilePageCache.KEEP_STACK_TRACE_AT_STORAGE_REGISTRATION", true);

    /* JADX WARN: Code duplicated, block: B:15:0x001d  */
    /* JADX WARN: Code duplicated, block: B:21:0x002e  */
    /* JADX WARN: Code duplicated, block: B:8:0x000f  */
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        if (i == 1) {
            str = "@NotNull method %s.%s must not return null";
        } else if (i == 2) {
            str = "Seems accessed storage has been closed";
        } else if (i != 4) {
            str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        } else {
            str = "@NotNull method %s.%s must not return null";
        }
        if (i == 1) {
            i2 = 2;
        } else if (i == 2) {
            i2 = 0;
        } else if (i != 4) {
            i2 = 3;
        } else {
            i2 = 2;
        }
        Object[] objArr = new Object[i2];
        if (i == 1) {
            objArr[0] = "com/intellij/util/io/FilePageCache";
        } else if (i != 2) {
            if (i != 4) {
                objArr[0] = "storage";
            } else {
                objArr[0] = "com/intellij/util/io/FilePageCache";
            }
        }
        if (i == 1) {
            objArr[1] = "getStatistics";
        } else if (i != 2) {
            if (i != 4) {
                objArr[1] = "com/intellij/util/io/FilePageCache";
            } else {
                objArr[1] = "getBuffersForOwner";
            }
        }
        if (i != 1 && i != 2) {
            if (i == 3) {
                objArr[2] = "getBuffersForOwner";
            } else if (i != 4) {
                objArr[2] = "registerPagedFileStorage";
            }
        }
        String str2 = String.format(str, objArr);
        if (i != 1 && i != 2 && i != 4) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public FilePageCache(long j) {
        this.stackTracesOfStorageRegistration = KEEP_STACK_TRACE_AT_STORAGE_REGISTRATION ? new HashMap() : null;
        this.storageByAbsolutePath = new HashMap();
        this.pagesAccessLock = new ReentrantLock();
        this.pagesAllocationLock = new ReentrantLock();
        this.pagesToRemoveByPageId = new Long2ObjectLinkedOpenHashMap<>();
        if (j <= 0) {
            lq4.a("Capacity(=", j, ") must be >0");
            throw null;
        }
        this.cachedSizeLimit = j;
        this.pagesByPageId = new LongLinkedHashMap<DirectBufferWrapper>(10, 0.75f, true) { // from class: com.intellij.util.io.FilePageCache.1
            static final /* synthetic */ boolean $assertionsDisabled = false;

            private static /* synthetic */ void $$$reportNull$$$0(int i) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "wrapper", "com/intellij/util/io/FilePageCache$1", "put"));
            }

            @Override // com.intellij.util.containers.hash.LongLinkedHashMap
            public DirectBufferWrapper put(long j2, DirectBufferWrapper directBufferWrapper) {
                if (directBufferWrapper == null) {
                    $$$reportNull$$$0(0);
                }
                FilePageCache.access$114(FilePageCache.this, directBufferWrapper.getLength());
                DirectBufferWrapper directBufferWrapper2 = (DirectBufferWrapper) super.put(j2, directBufferWrapper);
                FilePageCache filePageCache = FilePageCache.this;
                filePageCache.myMaxLoadedSize = Math.max(filePageCache.myMaxLoadedSize, FilePageCache.this.totalSizeCached);
                return directBufferWrapper2;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.intellij.util.containers.hash.LongLinkedHashMap
            public DirectBufferWrapper remove(long j2) {
                DirectBufferWrapper directBufferWrapper = (DirectBufferWrapper) super.remove(j2);
                if (directBufferWrapper != null) {
                    FilePageCache.access$408(FilePageCache.this);
                    FilePageCache.this.assertUnderSegmentAllocationLock();
                    FilePageCache.this.pagesToRemoveByPageId.put(j2, directBufferWrapper);
                    FilePageCache.access$122(FilePageCache.this, directBufferWrapper.getLength());
                }
                return directBufferWrapper;
            }

            @Override // com.intellij.util.containers.hash.LongLinkedHashMap
            public boolean removeEldestEntry(LongLinkedHashMap.Entry<DirectBufferWrapper> entry) {
                return FilePageCache.this.totalSizeCached > FilePageCache.this.cachedSizeLimit;
            }
        };
    }

    public static /* synthetic */ long access$114(FilePageCache filePageCache, long j) {
        long j2 = filePageCache.totalSizeCached + j;
        filePageCache.totalSizeCached = j2;
        return j2;
    }

    public static /* synthetic */ long access$122(FilePageCache filePageCache, long j) {
        long j2 = filePageCache.totalSizeCached - j;
        filePageCache.totalSizeCached = j2;
        return j2;
    }

    public static /* synthetic */ int access$408(FilePageCache filePageCache) {
        int i = filePageCache.myMappingChangeCount;
        filePageCache.myMappingChangeCount = i + 1;
        return i;
    }

    private static DirectBufferWrapper allocateAndLoadPage(long j, boolean z, PagedFileStorage pagedFileStorage, boolean z2) throws IOException {
        if (z2) {
            StorageLockContext storageLockContext = pagedFileStorage.getStorageLockContext();
            if (z) {
                storageLockContext.checkReadAccess();
            } else {
                storageLockContext.checkWriteAccess();
            }
        }
        return new DirectBufferWrapper(pagedFileStorage, (j & 4294967295L) * ((long) pagedFileStorage.getPageSize()));
    }

    private void disposeRemovedSegments(PagedFileStorage pagedFileStorage) {
        assertUnderSegmentAllocationLock();
        if (this.pagesToRemoveByPageId.isEmpty()) {
            return;
        }
        ObjectIterator it = this.pagesToRemoveByPageId.values().iterator();
        while (it.hasNext()) {
            try {
                DirectBufferWrapper directBufferWrapper = (DirectBufferWrapper) it.next();
                if (directBufferWrapper.tryRelease(directBufferWrapper.getFile() == pagedFileStorage)) {
                    it.remove();
                }
            } catch (IOException e) {
                LOG.error(e);
            }
        }
    }

    private void ensureSize(long j) {
        this.pagesAccessLock.lock();
        while (this.totalSizeCached > j) {
            try {
                this.pagesByPageId.doRemoveEldestEntry();
            } catch (Throwable th) {
                this.pagesAccessLock.unlock();
                throw th;
            }
        }
        this.pagesAccessLock.unlock();
        disposeRemovedSegments(null);
    }

    private Map<Long, DirectBufferWrapper> getBuffersForOwner(PagedFileStorage pagedFileStorage) {
        if (pagedFileStorage == null) {
            $$$reportNull$$$0(3);
        }
        StorageLockContext storageLockContext = pagedFileStorage.getStorageLockContext();
        this.pagesAccessLock.lock();
        try {
            storageLockContext.checkReadAccess();
            TreeMap treeMap = new TreeMap();
            for (LongLinkedHashMap.Entry<DirectBufferWrapper> entry : this.pagesByPageId.entrySet()) {
                if (entry.getValue().getFile() == pagedFileStorage) {
                    treeMap.put(Long.valueOf(entry.getKey()), entry.getValue());
                }
            }
            this.pagesAccessLock.unlock();
            return treeMap;
        } catch (Throwable th) {
            this.pagesAccessLock.unlock();
            throw th;
        }
    }

    private PagedFileStorage getRegisteredPagedFileStorageByIndex(long j) throws ClosedStorageException {
        PagedFileStorage pagedFileStorage;
        int i = (int) ((j & (-4294967296L)) >> 32);
        synchronized (this.storageById) {
            try {
                pagedFileStorage = (PagedFileStorage) this.storageById.get(i);
                if (pagedFileStorage == null) {
                    throw new ClosedStorageException("storage is already closed");
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return pagedFileStorage;
    }

    public void assertUnderSegmentAllocationLock() {
    }

    public void flushBuffersForOwner(PagedFileStorage pagedFileStorage) throws IOException {
        pagedFileStorage.getStorageLockContext().checkReadAccess();
        Map<Long, DirectBufferWrapper> buffersForOwner = getBuffersForOwner(pagedFileStorage);
        if (buffersForOwner.isEmpty()) {
            return;
        }
        SmartList smartList = new SmartList();
        this.pagesAllocationLock.lock();
        try {
            try {
                for (DirectBufferWrapper directBufferWrapper : buffersForOwner.values()) {
                    if (directBufferWrapper.isDirty() && !directBufferWrapper.isReleased()) {
                        directBufferWrapper.force();
                    }
                }
            } catch (IOException e) {
                smartList.add(e);
            }
            this.pagesAllocationLock.unlock();
            if (!smartList.isEmpty()) {
                throw new IOException(new CompoundRuntimeException(smartList));
            }
        } catch (Throwable th) {
            this.pagesAllocationLock.unlock();
            throw th;
        }
    }

    public DirectBufferWrapper get(long j, boolean z, boolean z2) throws IOException {
        this.pagesAccessLock.lock();
        try {
            DirectBufferWrapper directBufferWrapper = this.pagesByPageId.get(j);
            if (directBufferWrapper != null) {
                this.myHits++;
                this.pagesAccessLock.unlock();
                return directBufferWrapper;
            }
            this.pagesAccessLock.unlock();
            this.pagesAllocationLock.lock();
            try {
                DirectBufferWrapper directBufferWrapper2 = (DirectBufferWrapper) this.pagesToRemoveByPageId.remove(j);
                ReentrantLock reentrantLock = this.pagesAccessLock;
                if (directBufferWrapper2 != null) {
                    reentrantLock.lock();
                    try {
                        this.pagesByPageId.put(j, directBufferWrapper2);
                        this.pagesAccessLock.unlock();
                        disposeRemovedSegments(null);
                        this.myHits++;
                        this.pagesAllocationLock.unlock();
                        return directBufferWrapper2;
                    } catch (Throwable th) {
                        this.pagesAccessLock.unlock();
                        throw th;
                    }
                }
                reentrantLock.lock();
                try {
                    DirectBufferWrapper directBufferWrapper3 = this.pagesByPageId.get(j);
                    ReentrantLock reentrantLock2 = this.pagesAccessLock;
                    if (directBufferWrapper3 != null) {
                        reentrantLock2.unlock();
                        this.pagesAllocationLock.unlock();
                        return directBufferWrapper3;
                    }
                    reentrantLock2.unlock();
                    long jNanoTime = System.nanoTime();
                    PagedFileStorage registeredPagedFileStorageByIndex = getRegisteredPagedFileStorageByIndex(j);
                    disposeRemovedSegments(null);
                    long jNanoTime2 = System.nanoTime();
                    DirectBufferWrapper directBufferWrapperAllocateAndLoadPage = allocateAndLoadPage(j, z, registeredPagedFileStorageByIndex, z2);
                    long jNanoTime3 = System.nanoTime();
                    this.myLoadedPages++;
                    this.myPageLoadUs += (jNanoTime3 - jNanoTime2) / 1000;
                    this.myPageDisposalUs += (jNanoTime2 - jNanoTime) / 1000;
                    this.pagesAccessLock.lock();
                    try {
                        if (this.totalSizeCached + ((long) registeredPagedFileStorageByIndex.getPageSize()) < this.cachedSizeLimit) {
                            this.myRegularPageLoads++;
                        } else {
                            this.myPageLoadsAboveSizeThreshold++;
                        }
                        this.pagesByPageId.put(j, directBufferWrapperAllocateAndLoadPage);
                        this.pagesAccessLock.unlock();
                        ensureSize(this.cachedSizeLimit);
                        this.pagesAllocationLock.unlock();
                        return directBufferWrapperAllocateAndLoadPage;
                    } catch (Throwable th2) {
                        this.pagesAccessLock.unlock();
                        throw th2;
                    }
                } catch (Throwable th3) {
                    this.pagesAccessLock.unlock();
                    throw th3;
                }
            } catch (Throwable th4) {
                this.pagesAllocationLock.unlock();
                throw th4;
            }
            this.pagesAllocationLock.unlock();
            throw th4;
        } catch (Throwable th5) {
            this.pagesAccessLock.unlock();
            throw th5;
        }
    }

    public void incrementFastCacheHitsCount() {
        this.myFastCacheHits++;
    }

    public void incrementUncachedFileAccess() {
        this.myUncachedFileAccess++;
    }

    public long registerPagedFileStorage(PagedFileStorage pagedFileStorage) {
        long j;
        Exception exc;
        if (pagedFileStorage == null) {
            $$$reportNull$$$0(0);
        }
        synchronized (this.storageById) {
            try {
                Path absolutePath = pagedFileStorage.getFile().toAbsolutePath();
                if (this.storageByAbsolutePath.get(absolutePath) != null) {
                    IllegalStateException illegalStateException = new IllegalStateException("Storage for [" + absolutePath + "] is already registered");
                    if (KEEP_STACK_TRACE_AT_STORAGE_REGISTRATION && (exc = this.stackTracesOfStorageRegistration.get(absolutePath)) != null) {
                        illegalStateException.addSuppressed(exc);
                    }
                    if (THROW_ERROR_ON_DUPLICATE_STORAGE_REGISTRATION) {
                        throw illegalStateException;
                    }
                    LOG.warn(illegalStateException.getMessage(), illegalStateException);
                }
                int size = this.storageById.size();
                while (this.storageById.get(size) != null) {
                    size++;
                }
                this.storageById.put(size, pagedFileStorage);
                this.storageByAbsolutePath.put(absolutePath, pagedFileStorage);
                if (KEEP_STACK_TRACE_AT_STORAGE_REGISTRATION) {
                    this.stackTracesOfStorageRegistration.put(absolutePath, new Exception("Storage[" + absolutePath + "] registration stack trace"));
                }
                this.myMaxRegisteredFiles = Math.max(this.myMaxRegisteredFiles, this.storageById.size());
                j = ((long) size) << 32;
            } catch (Throwable th) {
                throw th;
            }
        }
        return j;
    }

    public void removeStorage(long j) {
        synchronized (this.storageById) {
            try {
                PagedFileStorage pagedFileStorage = (PagedFileStorage) this.storageById.remove((int) (j >> 32));
                if (pagedFileStorage != null) {
                    Path absolutePath = pagedFileStorage.getFile().toAbsolutePath();
                    this.storageByAbsolutePath.remove(absolutePath);
                    if (KEEP_STACK_TRACE_AT_STORAGE_REGISTRATION) {
                        this.stackTracesOfStorageRegistration.remove(absolutePath);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void unmapBuffersForOwner(PagedFileStorage pagedFileStorage) {
        Map<Long, DirectBufferWrapper> buffersForOwner = getBuffersForOwner(pagedFileStorage);
        this.pagesAllocationLock.lock();
        try {
            if (!buffersForOwner.isEmpty()) {
                this.pagesAccessLock.lock();
                try {
                    Iterator<Long> it = buffersForOwner.keySet().iterator();
                    while (it.hasNext()) {
                        this.pagesByPageId.remove(it.next().longValue());
                    }
                    this.pagesAccessLock.unlock();
                } catch (Throwable th) {
                    this.pagesAccessLock.unlock();
                    throw th;
                }
            }
            disposeRemovedSegments(pagedFileStorage);
            this.pagesAllocationLock.unlock();
        } catch (Throwable th2) {
            this.pagesAllocationLock.unlock();
            throw th2;
        }
    }
}
