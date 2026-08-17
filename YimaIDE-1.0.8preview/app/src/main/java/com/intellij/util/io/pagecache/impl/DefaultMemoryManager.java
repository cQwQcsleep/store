package com.intellij.util.io.pagecache.impl;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.util.io.DirectByteBufferAllocator;
import com.intellij.util.io.pagecache.FilePageCacheStatistics;
import defpackage.lq4;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class DefaultMemoryManager implements IMemoryManager {
    private static final Logger LOG = Logger.getInstance(DefaultMemoryManager.class);
    private final DirectByteBufferAllocator directBufferAllocator;
    private final AtomicLong heapBytesUsed;
    private final long heapCapacityBytes;
    private final AtomicLong nativeBytesUsed;
    private final long nativeCapacityBytes;
    private final FilePageCacheStatistics statistics;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        if (i != 1) {
            objArr[0] = "statistics";
        } else {
            objArr[0] = "buffer";
        }
        objArr[1] = "com/intellij/util/io/pagecache/impl/DefaultMemoryManager";
        if (i != 1) {
            objArr[2] = "<init>";
        } else {
            objArr[2] = "releaseBuffer";
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    public DefaultMemoryManager(long j, long j2, FilePageCacheStatistics filePageCacheStatistics) {
        if (filePageCacheStatistics == null) {
            $$$reportNull$$$0(0);
        }
        this.nativeBytesUsed = new AtomicLong(0L);
        this.heapBytesUsed = new AtomicLong(0L);
        this.directBufferAllocator = DirectByteBufferAllocator.ALLOCATOR;
        if (j <= 0) {
            lq4.a("nativeCapacityBytes(=", j, ") must be >0");
            throw null;
        }
        if (j2 <= 0) {
            lq4.a("heapCapacityBytes(=", j2, ") must be >0");
            throw null;
        }
        this.nativeCapacityBytes = j;
        this.heapCapacityBytes = j2;
        this.statistics = filePageCacheStatistics;
    }

    @Override // com.intellij.util.io.pagecache.impl.IMemoryManager
    public boolean hasOverflow() {
        return totalMemoryUsed() > this.nativeCapacityBytes;
    }

    @Override // com.intellij.util.io.pagecache.impl.IMemoryManager
    public long heapBytesUsed() {
        return this.heapBytesUsed.get();
    }

    @Override // com.intellij.util.io.pagecache.impl.IMemoryManager
    public long nativeBytesUsed() {
        return this.nativeBytesUsed.get();
    }

    @Override // com.intellij.util.io.pagecache.impl.IMemoryManager
    public long nativeCapacityBytes() {
        return this.nativeCapacityBytes;
    }

    @Override // com.intellij.util.io.pagecache.impl.IMemoryManager
    public void releaseBuffer(int i, ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            $$$reportNull$$$0(1);
        }
        if (!byteBuffer.isDirect()) {
            long jAddAndGet = this.heapBytesUsed.addAndGet(-i);
            if (jAddAndGet >= 0) {
                this.statistics.pageReclaimedHeap(i);
                return;
            }
            throw new IllegalStateException("heapBytesUsed(=" + jAddAndGet + ") must be >=0");
        }
        this.directBufferAllocator.release(byteBuffer);
        long jAddAndGet2 = this.nativeBytesUsed.addAndGet(-i);
        if (jAddAndGet2 >= 0) {
            this.statistics.pageReclaimedNative(i);
            return;
        }
        throw new IllegalStateException("nativeBytesUsed(=" + jAddAndGet2 + ") must be >=0");
    }

    public String toString() {
        return "DefaultMemoryManager{nativeCapacity: " + this.nativeCapacityBytes + ", heapCapacity: " + this.heapCapacityBytes + ", nativeUsed: " + this.nativeBytesUsed + ", heapUsed: " + this.heapBytesUsed + '}';
    }
}
