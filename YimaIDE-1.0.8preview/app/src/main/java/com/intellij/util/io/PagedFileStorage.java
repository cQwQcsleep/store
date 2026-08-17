package com.intellij.util.io;

import androidx.collection.ScatterMapKt;
import com.intellij.openapi.Forceable;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.ThrowableNotNullFunction;
import com.intellij.util.ArrayUtil;
import com.intellij.util.ExceptionUtil;
import com.intellij.util.ThrowableRunnable;
import com.intellij.util.io.PagedFileStorage;
import com.intellij.util.io.ResilientFileChannel;
import com.intellij.util.io.storage.AbstractStorage;
import defpackage.swa;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.function.Supplier;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class PagedFileStorage implements Forceable, Closeable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private volatile boolean isDirty;
    private final Path myFile;
    private final Object myInputStreamLock;
    private final PagedFileStorageCache myLastAccessedBufferCache;
    private final boolean myNativeBytesOrder;
    private final int myPageSize;
    private final boolean myReadOnly;
    private volatile long mySize;
    private long myStorageIndex;
    private final StorageLockContext myStorageLockContext;
    private final boolean myValuesAreBufferAligned;
    static final Logger LOG = Logger.getInstance(PagedFileStorage.class);
    private static final int DEFAULT_PAGE_SIZE = PageCacheUtils.DEFAULT_PAGE_SIZE;
    private static final ThreadLocal<byte[]> ourTypedIOBuffer = ThreadLocal.withInitial(new Supplier() { // from class: zwa
        @Override // java.util.function.Supplier
        public final Object get() {
            return PagedFileStorage.c();
        }
    });
    public static final ThreadLocal<StorageLockContext> THREAD_LOCAL_STORAGE_LOCK_CONTEXT = new ThreadLocal<>();

    /* JADX WARN: Code duplicated, block: B:11:0x0019  */
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        if (i != 1 && i != 2 && i != 4 && i != 5 && i != 7 && i != 8) {
            switch (i) {
                case 12:
                case 13:
                case 14:
                case 15:
                case 16:
                    str = "@NotNull method %s.%s must not return null";
                    break;
                default:
                    str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                    break;
            }
        } else {
            str = "@NotNull method %s.%s must not return null";
        }
        if (i != 1 && i != 2 && i != 4 && i != 5 && i != 7 && i != 8) {
            switch (i) {
                case 12:
                case 13:
                case 14:
                case 15:
                case 16:
                    i2 = 2;
                    break;
                default:
                    i2 = 3;
                    break;
            }
        } else {
            i2 = 2;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 1:
            case 2:
            case 4:
            case 5:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
                objArr[0] = "com/intellij/util/io/PagedFileStorage";
                break;
            case 3:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[0] = "consumer";
                break;
            case 9:
            case 10:
                objArr[0] = "operation";
                break;
            case 11:
                objArr[0] = "data";
                break;
            default:
                objArr[0] = "file";
                break;
        }
        if (i == 1) {
            objArr[1] = "getStorageLockContext";
        } else if (i == 2) {
            objArr[1] = "getFile";
        } else if (i != 4 && i != 5) {
            if (i != 7 && i != 8) {
                switch (i) {
                    case 12:
                    case 13:
                        objArr[1] = "doGetBufferWrapper";
                        break;
                    case 14:
                    case 15:
                    case 16:
                        objArr[1] = "lookupStorageContext";
                        break;
                    default:
                        objArr[1] = "com/intellij/util/io/PagedFileStorage";
                        break;
                }
            } else {
                objArr[1] = "readChannel";
            }
        } else {
            objArr[1] = "readInputStream";
        }
        switch (i) {
            case 1:
            case 2:
            case 4:
            case 5:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
                break;
            case 3:
                objArr[2] = "readInputStream";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[2] = "readChannel";
                break;
            case 9:
                objArr[2] = "executeOp";
                break;
            case 10:
                objArr[2] = "executeIdempotentOp";
                break;
            case 11:
                objArr[2] = "putBuffer";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 1 && i != 2 && i != 4 && i != 5 && i != 7 && i != 8) {
            switch (i) {
                case 12:
                case 13:
                case 14:
                case 15:
                case 16:
                    break;
                default:
                    throw new IllegalArgumentException(str2);
            }
        }
        throw new IllegalStateException(str2);
    }

    public PagedFileStorage(Path path, StorageLockContext storageLockContext, int i, boolean z, boolean z2) {
        if (path == null) {
            $$$reportNull$$$0(0);
        }
        this.myLastAccessedBufferCache = new PagedFileStorageCache();
        this.myInputStreamLock = new Object();
        this.mySize = -1L;
        this.myFile = path;
        this.myReadOnly = PersistentHashMapValueStorage.CreationTimeOptions.READONLY.get() == Boolean.TRUE;
        StorageLockContext storageLockContextLookupStorageContext = lookupStorageContext(storageLockContext);
        this.myStorageLockContext = storageLockContextLookupStorageContext;
        this.myPageSize = Math.max(i <= 0 ? DEFAULT_PAGE_SIZE : i, AbstractStorage.PAGE_SIZE);
        this.myValuesAreBufferAligned = z;
        this.myStorageIndex = storageLockContextLookupStorageContext.getBufferCache().registerPagedFileStorage(this);
        this.myNativeBytesOrder = z2;
    }

    public static /* synthetic */ byte[] c() {
        return new byte[8];
    }

    public static /* synthetic */ Object d(long j, ResilientFileChannel resilientFileChannel) throws IOException {
        resilientFileChannel.truncate(j);
        return null;
    }

    private DirectBufferWrapper doGetBufferWrapper(long j, boolean z, boolean z2) throws IOException {
        if (this.myReadOnly && z) {
            a16.a("Read-only storage can't be modified");
            return null;
        }
        DirectBufferWrapper pageFromCache = this.myLastAccessedBufferCache.getPageFromCache(j);
        if (pageFromCache != null) {
            this.myStorageLockContext.getBufferCache().incrementFastCacheHitsCount();
            return pageFromCache;
        }
        if (j < 0 || j > 4294967295L) {
            throw new AssertionError("Page " + j + " is outside of [0, 4294967295)");
        }
        if (this.myStorageIndex == -1) {
            throw new ClosedStorageException("storage is already closed; path " + this.myFile);
        }
        DirectBufferWrapper directBufferWrapper = this.myStorageLockContext.getBufferCache().get(this.myStorageIndex | j, !z, z2);
        this.myLastAccessedBufferCache.updateCache(j, directBufferWrapper);
        if (directBufferWrapper == null) {
            $$$reportNull$$$0(13);
        }
        return directBufferWrapper;
    }

    public static /* synthetic */ IOException e(PagedFileStorage pagedFileStorage) {
        return new IOException("Failed to close PagedFileStorage[" + pagedFileStorage.getFile() + "]");
    }

    public static /* synthetic */ void f(PagedFileStorage pagedFileStorage) {
        pagedFileStorage.unmapAll();
        pagedFileStorage.myStorageLockContext.getBufferCache().removeStorage(pagedFileStorage.myStorageIndex);
        pagedFileStorage.myStorageIndex = -1L;
    }

    private void fillWithZeros(long j, long j2) throws IOException {
        byte[] bArr = new byte[8192];
        long j3 = j;
        long j4 = j2;
        while (j4 > 0) {
            int iMin = (int) Math.min(j4, 8192L);
            if (iMin <= 0) {
                throw new AssertionError("Bug: toFill(=" + iMin + ") -- must be positive. Details: from: " + j + ", length: " + j2 + " -> offset: " + j3 + ", remaining: " + j4);
            }
            put(j3, bArr, 0, iMin);
            long j5 = iMin;
            j4 -= j5;
            j3 += j5;
        }
    }

    public static /* synthetic */ void g(PagedFileStorage pagedFileStorage) throws IOException {
        pagedFileStorage.getClass();
        PageCacheUtils.CHANNELS_CACHE.closeChannel(pagedFileStorage.myFile);
    }

    private DirectBufferWrapper getBuffer(long j) throws IOException {
        return getBufferWrapper(j, true, true);
    }

    private DirectBufferWrapper getBufferWrapper(long j, boolean z, boolean z2) throws IOException {
        DirectBufferWrapper directBufferWrapperDoGetBufferWrapper;
        do {
            directBufferWrapperDoGetBufferWrapper = doGetBufferWrapper(j, z, z2);
        } while (!directBufferWrapperDoGetBufferWrapper.tryLock());
        return directBufferWrapperDoGetBufferWrapper;
    }

    private DirectBufferWrapper getReadOnlyBuffer(long j, boolean z) throws IOException {
        return getBufferWrapper(j, false, z);
    }

    private static byte[] getThreadLocalTypedIOBuffer() {
        return ourTypedIOBuffer.get();
    }

    public static /* synthetic */ Object h(ThrowableNotNullFunction throwableNotNullFunction, ResilientFileChannel resilientFileChannel) throws IOException {
        resilientFileChannel.position(0L);
        return throwableNotNullFunction.fun(Channels.newInputStream(resilientFileChannel));
    }

    public static /* synthetic */ Object i(long j, ResilientFileChannel resilientFileChannel) throws IOException {
        resilientFileChannel.write(ByteBuffer.allocate(1), j - 1);
        return null;
    }

    public static StorageLockContext lookupStorageContext(StorageLockContext storageLockContext) {
        StorageLockContext storageLockContext2 = THREAD_LOCAL_STORAGE_LOCK_CONTEXT.get();
        if (storageLockContext2 != null) {
            if (storageLockContext == null || storageLockContext == storageLockContext2) {
                return storageLockContext2;
            }
            swa.a("Context(", storageLockContext, ") != THREAD_LOCAL_STORAGE_LOCK_CONTEXT(", storageLockContext2, ")");
            return null;
        }
        if (storageLockContext != null) {
            return storageLockContext;
        }
        StorageLockContext storageLockContext3 = StorageLockContext.DEFAULT_CONTEXT;
        if (storageLockContext3 == null) {
            $$$reportNull$$$0(16);
        }
        return storageLockContext3;
    }

    private void unmapAll() {
        this.myStorageLockContext.getBufferCache().unmapBuffersForOwner(this);
        this.myLastAccessedBufferCache.clear();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws Exception {
        ExceptionUtil.runAllAndRethrowAllExceptions(IOException.class, new Supplier() { // from class: vwa
            @Override // java.util.function.Supplier
            public final Object get() {
                return PagedFileStorage.e(this.b);
            }
        }, new ThrowableRunnable() { // from class: wwa
            @Override // com.intellij.util.ThrowableRunnable
            public final void run() throws IOException {
                this.a.force();
            }
        }, new ThrowableRunnable() { // from class: xwa
            @Override // com.intellij.util.ThrowableRunnable
            public final void run() {
                PagedFileStorage.f(this.a);
            }
        }, new ThrowableRunnable() { // from class: ywa
            @Override // com.intellij.util.ThrowableRunnable
            public final void run() throws IOException {
                PagedFileStorage.g(this.a);
            }
        });
    }

    public void ensureCachedSizeAtLeast(long j) {
        if (this.mySize < j) {
            this.mySize = j;
        }
    }

    public <R> R executeIdempotentOp(FileChannelInterruptsRetryer.FileChannelIdempotentOperation<R> fileChannelIdempotentOperation, boolean z) throws IOException {
        if (fileChannelIdempotentOperation == null) {
            $$$reportNull$$$0(10);
        }
        return (R) this.myStorageLockContext.executeIdempotentOp(this.myFile, fileChannelIdempotentOperation, z);
    }

    public <R> R executeOp(OpenChannelsCache.FileChannelOperation<R> fileChannelOperation, boolean z) throws IOException {
        if (fileChannelOperation == null) {
            $$$reportNull$$$0(9);
        }
        return (R) this.myStorageLockContext.executeOp(this.myFile, fileChannelOperation, z);
    }

    @Override // com.intellij.openapi.Forceable
    public void force() throws IOException {
        boolean z = IOStatistics.DEBUG;
        long jCurrentTimeMillis = z ? System.currentTimeMillis() : 0L;
        if (this.isDirty) {
            this.myStorageLockContext.getBufferCache().flushBuffersForOwner(this);
            this.isDirty = false;
        }
        if (z) {
            long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
            if (jCurrentTimeMillis2 > 100) {
                IOStatistics.dump("Flushed " + this.myFile + " for " + jCurrentTimeMillis2);
            }
        }
    }

    public void get(long j, byte[] bArr, int i, int i2, boolean z) throws IOException {
        int i3 = i;
        while (i2 > 0) {
            int i4 = this.myPageSize;
            int i5 = (int) (j % ((long) i4));
            int iMin = Math.min(i2, i4 - i5);
            DirectBufferWrapper readOnlyBuffer = getReadOnlyBuffer(j / ((long) i4), z);
            byte[] bArr2 = bArr;
            boolean z2 = z;
            try {
                readOnlyBuffer.readToArray(bArr2, i3, i5, iMin, z2);
                readOnlyBuffer.unlock();
                i2 -= iMin;
                i3 += iMin;
                j += (long) iMin;
                bArr = bArr2;
                z = z2;
            } catch (Throwable th) {
                readOnlyBuffer.unlock();
                throw th;
            }
        }
    }

    public DirectBufferWrapper getByteBuffer(long j, boolean z) throws IOException {
        return getBufferWrapper(j / ((long) this.myPageSize), z, true);
    }

    public Path getFile() {
        Path path = this.myFile;
        if (path == null) {
            $$$reportNull$$$0(2);
        }
        return path;
    }

    public int getInt(long j) throws IOException {
        if (!this.myValuesAreBufferAligned) {
            get(j, getThreadLocalTypedIOBuffer(), 0, 4, true);
            return Bits.getInt(getThreadLocalTypedIOBuffer(), 0);
        }
        int i = this.myPageSize;
        long j2 = j / ((long) i);
        int i2 = (int) (j % ((long) i));
        DirectBufferWrapper readOnlyBuffer = getReadOnlyBuffer(j2, true);
        try {
            return readOnlyBuffer.getInt(i2);
        } finally {
            readOnlyBuffer.unlock();
        }
    }

    public long getLong(long j) throws IOException {
        if (!this.myValuesAreBufferAligned) {
            get(j, getThreadLocalTypedIOBuffer(), 0, 8, true);
            return Bits.getLong(getThreadLocalTypedIOBuffer(), 0);
        }
        int i = this.myPageSize;
        long j2 = j / ((long) i);
        int i2 = (int) (j % ((long) i));
        DirectBufferWrapper readOnlyBuffer = getReadOnlyBuffer(j2, true);
        try {
            return readOnlyBuffer.getLong(i2);
        } finally {
            readOnlyBuffer.unlock();
        }
    }

    public int getOffsetInPage(long j) {
        return (int) (j % ((long) this.myPageSize));
    }

    public int getPageSize() {
        return this.myPageSize;
    }

    public StorageLockContext getStorageLockContext() {
        StorageLockContext storageLockContext = this.myStorageLockContext;
        if (storageLockContext == null) {
            $$$reportNull$$$0(1);
        }
        return storageLockContext;
    }

    @Override // com.intellij.openapi.Forceable
    public boolean isDirty() {
        return this.isDirty;
    }

    public boolean isNativeBytesOrder() {
        return this.myNativeBytesOrder;
    }

    public boolean isReadOnly() {
        return this.myReadOnly;
    }

    public long length() {
        long size = this.mySize;
        if (size != -1) {
            return size;
        }
        if (!Files.exists(this.myFile, new LinkOption[0])) {
            this.mySize = 0L;
            return 0L;
        }
        try {
            size = Files.size(this.myFile);
            this.mySize = size;
            return size;
        } catch (IOException e) {
            LOG.error(e);
            return size;
        }
    }

    public void lockRead() {
        this.myStorageLockContext.lockRead();
    }

    public void lockWrite() {
        this.myStorageLockContext.lockWrite();
    }

    public void markDirty() {
        if (this.isDirty) {
            return;
        }
        this.isDirty = true;
    }

    public void put(long j, byte[] bArr, int i, int i2) throws IOException {
        while (i2 > 0) {
            int i3 = this.myPageSize;
            long j2 = j / ((long) i3);
            int i4 = (int) (j % ((long) i3));
            int iMin = Math.min(i2, i3 - i4);
            DirectBufferWrapper buffer = getBuffer(j2);
            try {
                buffer.putFromArray(bArr, i, i4, iMin);
                buffer.unlock();
                i2 -= iMin;
                i += iMin;
                j += (long) iMin;
            } catch (Throwable th) {
                buffer.unlock();
                throw th;
            }
        }
    }

    public void putInt(long j, int i) throws IOException {
        if (!this.myValuesAreBufferAligned) {
            Bits.putInt(getThreadLocalTypedIOBuffer(), 0, i);
            put(j, getThreadLocalTypedIOBuffer(), 0, 4);
            return;
        }
        int i2 = this.myPageSize;
        long j2 = j / ((long) i2);
        int i3 = (int) (j % ((long) i2));
        DirectBufferWrapper buffer = getBuffer(j2);
        try {
            buffer.putInt(i3, i);
        } finally {
            buffer.unlock();
        }
    }

    public void putLong(long j, long j2) throws IOException {
        if (!this.myValuesAreBufferAligned) {
            Bits.putLong(getThreadLocalTypedIOBuffer(), 0, j2);
            put(j, getThreadLocalTypedIOBuffer(), 0, 8);
            return;
        }
        int i = this.myPageSize;
        long j3 = j / ((long) i);
        int i2 = (int) (j % ((long) i));
        DirectBufferWrapper buffer = getBuffer(j3);
        try {
            buffer.putLong(i2, j2);
        } finally {
            buffer.unlock();
        }
    }

    public <R> R readInputStream(final ThrowableNotNullFunction<? super InputStream, R, ? extends IOException> throwableNotNullFunction) throws IOException {
        R r;
        if (throwableNotNullFunction == null) {
            $$$reportNull$$$0(3);
        }
        synchronized (this.myInputStreamLock) {
            try {
                r = (R) executeOp(new OpenChannelsCache.FileChannelOperation() { // from class: axa
                    @Override // com.intellij.util.io.OpenChannelsCache.FileChannelOperation
                    public final Object execute(ResilientFileChannel resilientFileChannel) {
                        return PagedFileStorage.h(throwableNotNullFunction, resilientFileChannel);
                    }
                }, true);
            } catch (NoSuchFileException unused) {
                R rFun = throwableNotNullFunction.fun(new ByteArrayInputStream(ArrayUtil.EMPTY_BYTE_ARRAY));
                if (rFun == null) {
                    $$$reportNull$$$0(5);
                }
                return rFun;
            }
        }
        if (r == null) {
            $$$reportNull$$$0(4);
        }
        return r;
    }

    public void resize(final long j) throws IOException {
        long size;
        boolean zExists = Files.exists(this.myFile, new LinkOption[0]);
        Path path = this.myFile;
        if (zExists) {
            size = Files.size(path);
        } else {
            Files.createDirectories(path.getParent(), new FileAttribute[0]);
            size = 0;
        }
        if (size == j && size == length()) {
            return;
        }
        long j2 = j - size;
        this.mySize = -1L;
        StorageLockContext storageLockContext = this.myStorageLockContext;
        if (j2 <= 0) {
            storageLockContext.executeOp(this.myFile, new OpenChannelsCache.FileChannelOperation() { // from class: uwa
                @Override // com.intellij.util.io.OpenChannelsCache.FileChannelOperation
                public final Object execute(ResilientFileChannel resilientFileChannel) {
                    return PagedFileStorage.d(j, resilientFileChannel);
                }
            }, false);
            this.mySize = j;
        } else {
            storageLockContext.executeOp(this.myFile, new OpenChannelsCache.FileChannelOperation() { // from class: twa
                @Override // com.intellij.util.io.OpenChannelsCache.FileChannelOperation
                public final Object execute(ResilientFileChannel resilientFileChannel) {
                    return PagedFileStorage.i(j, resilientFileChannel);
                }
            }, false);
            this.mySize = j;
            fillWithZeros(size, j2);
        }
    }

    public String toString() {
        return "PagedFileStorage[" + this.myFile + "]";
    }

    public void unlockRead() {
        this.myStorageLockContext.unlockRead();
    }

    public void unlockWrite() {
        this.myStorageLockContext.unlockWrite();
    }

    public byte get(long j, boolean z) throws IOException {
        int i = this.myPageSize;
        long j2 = j / ((long) i);
        int i2 = (int) (j % ((long) i));
        DirectBufferWrapper readOnlyBuffer = getReadOnlyBuffer(j2, z);
        try {
            return readOnlyBuffer.get(i2, z);
        } finally {
            readOnlyBuffer.unlock();
        }
    }
}
