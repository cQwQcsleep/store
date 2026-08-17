package com.intellij.util.io;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.SystemInfoRt;
import com.intellij.openapi.util.ThrowableComputable;
import com.intellij.util.ConcurrencyUtil;
import com.intellij.util.ExceptionUtil;
import com.intellij.util.SystemProperties;
import com.intellij.util.io.DirectByteBufferAllocator;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class DirectByteBufferAllocator {
    public static final DirectByteBufferAllocator ALLOCATOR;
    private static final boolean USE_POOLED_ALLOCATOR;
    private static final ExecutorService singleThreadAllocator;
    private final int maxBuffersToCacheInBytes;
    private final ConcurrentSkipListMap<Integer, ArrayBlockingQueue<ByteBuffer>> buffersPool = new ConcurrentSkipListMap<>();
    private final AtomicLong totalSizeOfBuffersInCache = new AtomicLong();
    private final AtomicLong totalSizeOfBuffersAllocated = new AtomicLong();
    private final AtomicInteger hits = new AtomicInteger();
    private final AtomicInteger misses = new AtomicInteger();
    private final AtomicInteger reclaimed = new AtomicInteger();
    private final AtomicInteger disposed = new AtomicInteger();

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 2 || i == 3) ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[(i == 2 || i == 3) ? 3 : 2];
        if (i == 2 || i == 3) {
            objArr[0] = "buffer";
        } else {
            objArr[0] = "com/intellij/util/io/DirectByteBufferAllocator";
        }
        if (i == 2 || i == 3) {
            objArr[1] = "com/intellij/util/io/DirectByteBufferAllocator";
        } else {
            objArr[1] = "allocate";
        }
        if (i == 2) {
            objArr[2] = "release";
        } else if (i == 3) {
            objArr[2] = "releaseBufferWithoutCaching";
        }
        String str2 = String.format(str, objArr);
        if (i != 2 && i != 3) {
            throw new IllegalStateException(str2);
        }
        throw new IllegalArgumentException(str2);
    }

    static {
        singleThreadAllocator = (SystemInfoRt.isLinux && SystemProperties.getBooleanProperty("idea.limit.paged.storage.allocators", true)) ? ConcurrencyUtil.newSingleThreadExecutor("DirectBufferWrapper allocation thread") : null;
        boolean booleanProperty = SystemProperties.getBooleanProperty("idea.index.use.pooled.page.allocator", true);
        USE_POOLED_ALLOCATOR = booleanProperty;
        ALLOCATOR = new DirectByteBufferAllocator(booleanProperty ? PageCacheUtils.MAX_DIRECT_BUFFERS_POOL_BYTES : 0);
    }

    private DirectByteBufferAllocator(int i) {
        if (i >= 0) {
            this.maxBuffersToCacheInBytes = i;
        } else {
            ty8.a("sizeLimitInBytes(=", i, ") must be >=0");
            throw null;
        }
    }

    private static ByteBuffer allocateNewBuffer(final int i) {
        return allocate(new ThrowableComputable() { // from class: vt3
            public final Object compute() {
                return ByteBuffer.allocateDirect(i);
            }
        });
    }

    public static /* synthetic */ ArrayBlockingQueue b(Integer num) {
        return new ArrayBlockingQueue(40);
    }

    private void releaseBufferWithoutCaching(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            $$$reportNull$$$0(3);
        }
        this.totalSizeOfBuffersAllocated.addAndGet(-byteBuffer.capacity());
        ByteBufferUtil.cleanBuffer(byteBuffer);
        this.disposed.incrementAndGet();
    }

    private boolean useBuffersCache() {
        return this.maxBuffersToCacheInBytes > 0;
    }

    public ByteBuffer allocate(int i) {
        if (useBuffersCache()) {
            Map.Entry<Integer, ArrayBlockingQueue<ByteBuffer>> entryCeilingEntry = this.buffersPool.ceilingEntry(Integer.valueOf(i));
            while (entryCeilingEntry != null) {
                Integer key = entryCeilingEntry.getKey();
                int iIntValue = key.intValue();
                if (iIntValue > i * 2) {
                    break;
                }
                ByteBuffer byteBufferPoll = entryCeilingEntry.getValue().poll();
                if (byteBufferPoll != null) {
                    byteBufferPoll.clear().limit(i);
                    this.totalSizeOfBuffersInCache.addAndGet(-iIntValue);
                    this.hits.incrementAndGet();
                    return byteBufferPoll;
                }
                entryCeilingEntry = this.buffersPool.higherEntry(key);
            }
        }
        this.misses.incrementAndGet();
        this.totalSizeOfBuffersAllocated.addAndGet(i);
        ByteBuffer byteBufferAllocateNewBuffer = allocateNewBuffer(i);
        if (byteBufferAllocateNewBuffer == null) {
            $$$reportNull$$$0(1);
        }
        return byteBufferAllocateNewBuffer;
    }

    public void release(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            $$$reportNull$$$0(2);
        }
        if (useBuffersCache() && this.totalSizeOfBuffersInCache.get() < this.maxBuffersToCacheInBytes) {
            int iCapacity = byteBuffer.capacity();
            if (this.buffersPool.computeIfAbsent(Integer.valueOf(iCapacity), new Function() { // from class: tt3
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return DirectByteBufferAllocator.b((Integer) obj);
                }
            }).offer(byteBuffer)) {
                this.totalSizeOfBuffersInCache.addAndGet(iCapacity);
                this.reclaimed.incrementAndGet();
                return;
            }
        }
        releaseBufferWithoutCaching(byteBuffer);
    }

    private static <E extends Exception> ByteBuffer allocate(final ThrowableComputable<? extends ByteBuffer, E> throwableComputable) throws Exception {
        ExecutorService executorService = singleThreadAllocator;
        if (executorService != null) {
            try {
                Objects.requireNonNull(throwableComputable);
                return (ByteBuffer) executorService.submit(new Callable() { // from class: ut3
                    @Override // java.util.concurrent.Callable
                    public final Object call() {
                        return (ByteBuffer) throwableComputable.compute();
                    }
                }).get();
            } catch (InterruptedException e) {
                Logger.getInstance(DirectByteBufferAllocator.class).error("ByteBuffer allocation in dedicated thread was interrupted", e);
                return (ByteBuffer) throwableComputable.compute();
            } catch (ExecutionException e2) {
                Throwable cause = e2.getCause();
                if (!(cause instanceof OutOfMemoryError)) {
                    ExceptionUtil.rethrow(e2);
                    rc6.a(e2);
                    return null;
                }
                throw ((OutOfMemoryError) cause);
            }
        }
        return (ByteBuffer) throwableComputable.compute();
    }
}
