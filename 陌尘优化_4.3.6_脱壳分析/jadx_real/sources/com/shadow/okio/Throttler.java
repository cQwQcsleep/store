package com.shadow.okio;

import com.shadow.kotlin.io.CloseableKt;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class Throttler {
    private long allocatedUntil;
    private long bytesPerSecond;
    private final Condition condition;
    private final ReentrantLock lock;
    private long maxByteCount;
    private long waitByteCount;

    public Throttler(long j) {
        this.allocatedUntil = j;
        this.waitByteCount = 8192L;
        this.maxByteCount = 262144L;
        ReentrantLock reentrantLock = new ReentrantLock();
        this.lock = reentrantLock;
        Condition conditionNewCondition = reentrantLock.newCondition();
        CloseableKt.checkNotNullExpressionValue(conditionNewCondition, "newCondition(...)");
        this.condition = conditionNewCondition;
    }

    public static /* synthetic */ void bytesPerSecond$default(Throttler throttler, long j, long j2, long j3, int i, Object obj) {
        if ((i & 2) != 0) {
            j2 = throttler.waitByteCount;
        }
        long j4 = j2;
        if ((i & 4) != 0) {
            j3 = throttler.maxByteCount;
        }
        throttler.bytesPerSecond(j, j4, j3);
    }

    private final long bytesToNanos(long j) {
        return (j * 1000000000) / this.bytesPerSecond;
    }

    private final long nanosToBytes(long j) {
        return (j * this.bytesPerSecond) / 1000000000;
    }

    public final long byteCountOrWaitNanos$okio(long j, long j2) {
        if (this.bytesPerSecond == 0) {
            return j2;
        }
        long jMax = Math.max(this.allocatedUntil - j, 0L);
        long jNanosToBytes = this.maxByteCount - nanosToBytes(jMax);
        if (jNanosToBytes >= j2) {
            this.allocatedUntil = j + jMax + bytesToNanos(j2);
            return j2;
        }
        long j3 = this.waitByteCount;
        if (jNanosToBytes >= j3) {
            this.allocatedUntil = j + bytesToNanos(this.maxByteCount);
            return jNanosToBytes;
        }
        long jMin = Math.min(j3, j2);
        long jBytesToNanos = jMax + bytesToNanos(jMin - this.maxByteCount);
        if (jBytesToNanos != 0) {
            return -jBytesToNanos;
        }
        this.allocatedUntil = j + bytesToNanos(this.maxByteCount);
        return jMin;
    }

    public final void bytesPerSecond(long j) {
        bytesPerSecond$default(this, j, 0L, 0L, 6, null);
    }

    public final Condition getCondition() {
        return this.condition;
    }

    public final ReentrantLock getLock() {
        return this.lock;
    }

    public final Sink sink(Sink sink) {
        CloseableKt.checkNotNullParameter(sink, "sink");
        return new ForwardingSink(sink) { // from class: com.shadow.okio.Throttler.sink.1
            @Override // com.shadow.okio.ForwardingSink, com.shadow.okio.Sink
            public void write(Buffer buffer, long j) throws IOException {
                CloseableKt.checkNotNullParameter(buffer, "source");
                while (j > 0) {
                    try {
                        long jTake$okio = this.take$okio(j);
                        super.write(buffer, jTake$okio);
                        j -= jTake$okio;
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                        throw new InterruptedIOException("interrupted");
                    }
                }
            }
        };
    }

    public final Source source(Source source) {
        CloseableKt.checkNotNullParameter(source, "source");
        return new ForwardingSource(source) { // from class: com.shadow.okio.Throttler.source.1
            @Override // com.shadow.okio.ForwardingSource, com.shadow.okio.Source
            public long read(Buffer buffer, long j) throws InterruptedIOException {
                CloseableKt.checkNotNullParameter(buffer, "sink");
                try {
                    return super.read(buffer, this.take$okio(j));
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                    throw new InterruptedIOException("interrupted");
                }
            }
        };
    }

    public final long take$okio(long j) {
        if (j <= 0) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        while (true) {
            try {
                long jByteCountOrWaitNanos$okio = byteCountOrWaitNanos$okio(System.nanoTime(), j);
                if (jByteCountOrWaitNanos$okio >= 0) {
                    return jByteCountOrWaitNanos$okio;
                }
                this.condition.awaitNanos(-jByteCountOrWaitNanos$okio);
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    public final void bytesPerSecond(long j, long j2) {
        bytesPerSecond$default(this, j, j2, 0L, 4, null);
    }

    public final void bytesPerSecond(long j, long j2, long j3) {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            if (j < 0) {
                throw new IllegalArgumentException("Failed requirement.");
            }
            if (j2 <= 0) {
                throw new IllegalArgumentException("Failed requirement.");
            }
            if (j3 >= j2) {
                this.bytesPerSecond = j;
                this.waitByteCount = j2;
                this.maxByteCount = j3;
                this.condition.signalAll();
                return;
            }
            throw new IllegalArgumentException("Failed requirement.");
        } finally {
            reentrantLock.unlock();
        }
    }

    public Throttler() {
        this(System.nanoTime());
    }
}
