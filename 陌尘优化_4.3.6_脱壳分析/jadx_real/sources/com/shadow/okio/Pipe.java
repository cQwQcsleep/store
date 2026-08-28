package com.shadow.okio;

import com.shadow.kotlin.io.CloseableKt;
import core.pro.android.notify.h;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class Pipe {
    private final Buffer buffer = new Buffer();
    private boolean canceled;
    private final Condition condition;
    private Sink foldedSink;
    private final ReentrantLock lock;
    private final long maxBufferSize;
    private final Sink sink;
    private boolean sinkClosed;
    private final Source source;
    private boolean sourceClosed;

    public Pipe(long j) {
        this.maxBufferSize = j;
        ReentrantLock reentrantLock = new ReentrantLock();
        this.lock = reentrantLock;
        Condition conditionNewCondition = reentrantLock.newCondition();
        CloseableKt.checkNotNullExpressionValue(conditionNewCondition, "newCondition(...)");
        this.condition = conditionNewCondition;
        if (j < 1) {
            throw new IllegalArgumentException(h.c("maxBufferSize < 1: ", j).toString());
        }
        this.sink = new Sink() { // from class: com.shadow.okio.Pipe.sink.1
            private final Timeout timeout = new Timeout();

            @Override // com.shadow.okio.Sink, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                ReentrantLock lock = Pipe.this.getLock();
                Pipe pipe = Pipe.this;
                lock.lock();
                try {
                    if (pipe.getSinkClosed$okio()) {
                        lock.unlock();
                        return;
                    }
                    Sink foldedSink$okio = pipe.getFoldedSink$okio();
                    if (foldedSink$okio == null) {
                        if (pipe.getSourceClosed$okio() && pipe.getBuffer$okio().size() > 0) {
                            throw new IOException("source is closed");
                        }
                        pipe.setSinkClosed$okio(true);
                        pipe.getCondition().signalAll();
                        foldedSink$okio = null;
                    }
                    if (foldedSink$okio != null) {
                        Pipe pipe2 = Pipe.this;
                        Timeout timeout = foldedSink$okio.timeout();
                        Timeout timeout2 = pipe2.sink().timeout();
                        long jTimeoutNanos = timeout.timeoutNanos();
                        long jMinTimeout = Timeout.Companion.minTimeout(timeout2.timeoutNanos(), timeout.timeoutNanos());
                        TimeUnit timeUnit = TimeUnit.NANOSECONDS;
                        timeout.timeout(jMinTimeout, timeUnit);
                        if (!timeout.hasDeadline()) {
                            if (timeout2.hasDeadline()) {
                                timeout.deadlineNanoTime(timeout2.deadlineNanoTime());
                            }
                            try {
                                foldedSink$okio.close();
                                timeout.timeout(jTimeoutNanos, timeUnit);
                                if (timeout2.hasDeadline()) {
                                    timeout.clearDeadline();
                                    return;
                                }
                                return;
                            } catch (Throwable th) {
                                timeout.timeout(jTimeoutNanos, TimeUnit.NANOSECONDS);
                                if (timeout2.hasDeadline()) {
                                    timeout.clearDeadline();
                                }
                                throw th;
                            }
                        }
                        long jDeadlineNanoTime = timeout.deadlineNanoTime();
                        if (timeout2.hasDeadline()) {
                            timeout.deadlineNanoTime(Math.min(timeout.deadlineNanoTime(), timeout2.deadlineNanoTime()));
                        }
                        try {
                            foldedSink$okio.close();
                            timeout.timeout(jTimeoutNanos, timeUnit);
                            if (timeout2.hasDeadline()) {
                                timeout.deadlineNanoTime(jDeadlineNanoTime);
                            }
                        } catch (Throwable th2) {
                            timeout.timeout(jTimeoutNanos, TimeUnit.NANOSECONDS);
                            if (timeout2.hasDeadline()) {
                                timeout.deadlineNanoTime(jDeadlineNanoTime);
                            }
                            throw th2;
                        }
                    }
                } finally {
                    lock.unlock();
                }
            }

            @Override // com.shadow.okio.Sink, java.io.Flushable
            public void flush() {
                ReentrantLock lock = Pipe.this.getLock();
                Pipe pipe = Pipe.this;
                lock.lock();
                try {
                    if (pipe.getSinkClosed$okio()) {
                        throw new IllegalStateException("closed");
                    }
                    if (pipe.getCanceled$okio()) {
                        throw new IOException("canceled");
                    }
                    Sink foldedSink$okio = pipe.getFoldedSink$okio();
                    if (foldedSink$okio == null) {
                        if (pipe.getSourceClosed$okio() && pipe.getBuffer$okio().size() > 0) {
                            throw new IOException("source is closed");
                        }
                        foldedSink$okio = null;
                    }
                    if (foldedSink$okio != null) {
                        Pipe pipe2 = Pipe.this;
                        Timeout timeout = foldedSink$okio.timeout();
                        Timeout timeout2 = pipe2.sink().timeout();
                        long jTimeoutNanos = timeout.timeoutNanos();
                        long jMinTimeout = Timeout.Companion.minTimeout(timeout2.timeoutNanos(), timeout.timeoutNanos());
                        TimeUnit timeUnit = TimeUnit.NANOSECONDS;
                        timeout.timeout(jMinTimeout, timeUnit);
                        if (!timeout.hasDeadline()) {
                            if (timeout2.hasDeadline()) {
                                timeout.deadlineNanoTime(timeout2.deadlineNanoTime());
                            }
                            try {
                                foldedSink$okio.flush();
                                timeout.timeout(jTimeoutNanos, timeUnit);
                                if (timeout2.hasDeadline()) {
                                    timeout.clearDeadline();
                                    return;
                                }
                                return;
                            } catch (Throwable th) {
                                timeout.timeout(jTimeoutNanos, TimeUnit.NANOSECONDS);
                                if (timeout2.hasDeadline()) {
                                    timeout.clearDeadline();
                                }
                                throw th;
                            }
                        }
                        long jDeadlineNanoTime = timeout.deadlineNanoTime();
                        if (timeout2.hasDeadline()) {
                            timeout.deadlineNanoTime(Math.min(timeout.deadlineNanoTime(), timeout2.deadlineNanoTime()));
                        }
                        try {
                            foldedSink$okio.flush();
                            timeout.timeout(jTimeoutNanos, timeUnit);
                            if (timeout2.hasDeadline()) {
                                timeout.deadlineNanoTime(jDeadlineNanoTime);
                            }
                        } catch (Throwable th2) {
                            timeout.timeout(jTimeoutNanos, TimeUnit.NANOSECONDS);
                            if (timeout2.hasDeadline()) {
                                timeout.deadlineNanoTime(jDeadlineNanoTime);
                            }
                            throw th2;
                        }
                    }
                } finally {
                    lock.unlock();
                }
            }

            @Override // com.shadow.okio.Sink
            public Timeout timeout() {
                return this.timeout;
            }

            /* JADX WARN: Code restructure failed: missing block: B:29:0x007b, code lost:
            
                if (r2 == null) goto L56;
             */
            /* JADX WARN: Code restructure failed: missing block: B:30:0x007d, code lost:
            
                r0 = r12.this$0;
                r1 = r2.timeout();
                r0 = r0.sink().timeout();
                r3 = r1.timeoutNanos();
                r5 = com.shadow.okio.Timeout.Companion.minTimeout(r0.timeoutNanos(), r1.timeoutNanos());
                r7 = java.util.concurrent.TimeUnit.NANOSECONDS;
                r1.timeout(r5, r7);
             */
            /* JADX WARN: Code restructure failed: missing block: B:31:0x00a6, code lost:
            
                if (r1.hasDeadline() == false) goto L44;
             */
            /* JADX WARN: Code restructure failed: missing block: B:32:0x00a8, code lost:
            
                r5 = r1.deadlineNanoTime();
             */
            /* JADX WARN: Code restructure failed: missing block: B:33:0x00b0, code lost:
            
                if (r0.hasDeadline() == false) goto L63;
             */
            /* JADX WARN: Code restructure failed: missing block: B:34:0x00b2, code lost:
            
                r1.deadlineNanoTime(java.lang.Math.min(r1.deadlineNanoTime(), r0.deadlineNanoTime()));
             */
            /* JADX WARN: Code restructure failed: missing block: B:35:0x00c1, code lost:
            
                r2.write(r13, r14);
             */
            /* JADX WARN: Code restructure failed: missing block: B:36:0x00c4, code lost:
            
                r1.timeout(r3, r7);
             */
            /* JADX WARN: Code restructure failed: missing block: B:37:0x00cb, code lost:
            
                if (r0.hasDeadline() == false) goto L77;
             */
            /* JADX WARN: Code restructure failed: missing block: B:38:0x00cd, code lost:
            
                r1.deadlineNanoTime(r5);
             */
            /* JADX WARN: Code restructure failed: missing block: B:39:0x00d1, code lost:
            
                r13 = move-exception;
             */
            /* JADX WARN: Code restructure failed: missing block: B:40:0x00d2, code lost:
            
                r1.timeout(r3, java.util.concurrent.TimeUnit.NANOSECONDS);
             */
            /* JADX WARN: Code restructure failed: missing block: B:41:0x00db, code lost:
            
                if (r0.hasDeadline() != false) goto L42;
             */
            /* JADX WARN: Code restructure failed: missing block: B:42:0x00dd, code lost:
            
                r1.deadlineNanoTime(r5);
             */
            /* JADX WARN: Code restructure failed: missing block: B:43:0x00e0, code lost:
            
                throw r13;
             */
            /* JADX WARN: Code restructure failed: missing block: B:45:0x00e5, code lost:
            
                if (r0.hasDeadline() == false) goto L66;
             */
            /* JADX WARN: Code restructure failed: missing block: B:46:0x00e7, code lost:
            
                r1.deadlineNanoTime(r0.deadlineNanoTime());
             */
            /* JADX WARN: Code restructure failed: missing block: B:47:0x00ee, code lost:
            
                r2.write(r13, r14);
             */
            /* JADX WARN: Code restructure failed: missing block: B:48:0x00f1, code lost:
            
                r1.timeout(r3, r7);
             */
            /* JADX WARN: Code restructure failed: missing block: B:49:0x00f8, code lost:
            
                if (r0.hasDeadline() == false) goto L79;
             */
            /* JADX WARN: Code restructure failed: missing block: B:50:0x00fa, code lost:
            
                r1.clearDeadline();
             */
            /* JADX WARN: Code restructure failed: missing block: B:51:0x00fe, code lost:
            
                r13 = move-exception;
             */
            /* JADX WARN: Code restructure failed: missing block: B:52:0x00ff, code lost:
            
                r1.timeout(r3, java.util.concurrent.TimeUnit.NANOSECONDS);
             */
            /* JADX WARN: Code restructure failed: missing block: B:53:0x0108, code lost:
            
                if (r0.hasDeadline() != false) goto L54;
             */
            /* JADX WARN: Code restructure failed: missing block: B:54:0x010a, code lost:
            
                r1.clearDeadline();
             */
            /* JADX WARN: Code restructure failed: missing block: B:55:0x010d, code lost:
            
                throw r13;
             */
            /* JADX WARN: Code restructure failed: missing block: B:56:0x010e, code lost:
            
                return;
             */
            /* JADX WARN: Code restructure failed: missing block: B:77:?, code lost:
            
                return;
             */
            /* JADX WARN: Code restructure failed: missing block: B:78:?, code lost:
            
                return;
             */
            /* JADX WARN: Code restructure failed: missing block: B:79:?, code lost:
            
                return;
             */
            /* JADX WARN: Code restructure failed: missing block: B:80:?, code lost:
            
                return;
             */
            @Override // com.shadow.okio.Sink
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void write(Buffer buffer, long j2) {
                Sink foldedSink$okio;
                CloseableKt.checkNotNullParameter(buffer, "source");
                ReentrantLock lock = Pipe.this.getLock();
                Pipe pipe = Pipe.this;
                lock.lock();
                try {
                    if (pipe.getSinkClosed$okio()) {
                        throw new IllegalStateException("closed");
                    }
                    if (!pipe.getCanceled$okio()) {
                        while (true) {
                            if (j2 <= 0) {
                                foldedSink$okio = null;
                                break;
                            }
                            foldedSink$okio = pipe.getFoldedSink$okio();
                            if (foldedSink$okio == null) {
                                if (pipe.getSourceClosed$okio()) {
                                    throw new IOException("source is closed");
                                }
                                long maxBufferSize$okio = pipe.getMaxBufferSize$okio() - pipe.getBuffer$okio().size();
                                if (maxBufferSize$okio == 0) {
                                    this.timeout.awaitSignal(pipe.getCondition());
                                    if (pipe.getCanceled$okio()) {
                                        throw new IOException("canceled");
                                    }
                                } else {
                                    long jMin = Math.min(maxBufferSize$okio, j2);
                                    pipe.getBuffer$okio().write(buffer, jMin);
                                    j2 -= jMin;
                                    pipe.getCondition().signalAll();
                                }
                            }
                        }
                    } else {
                        throw new IOException("canceled");
                    }
                } finally {
                    lock.unlock();
                }
            }
        };
        this.source = new Source() { // from class: com.shadow.okio.Pipe.source.1
            private final Timeout timeout = new Timeout();

            @Override // com.shadow.okio.Source, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                ReentrantLock lock = Pipe.this.getLock();
                Pipe pipe = Pipe.this;
                lock.lock();
                try {
                    pipe.setSourceClosed$okio(true);
                    pipe.getCondition().signalAll();
                } finally {
                    lock.unlock();
                }
            }

            @Override // com.shadow.okio.Source
            public long read(Buffer buffer, long j2) {
                CloseableKt.checkNotNullParameter(buffer, "sink");
                ReentrantLock lock = Pipe.this.getLock();
                Pipe pipe = Pipe.this;
                lock.lock();
                try {
                    if (pipe.getSourceClosed$okio()) {
                        throw new IllegalStateException("closed");
                    }
                    if (pipe.getCanceled$okio()) {
                        throw new IOException("canceled");
                    }
                    while (pipe.getBuffer$okio().size() == 0) {
                        if (pipe.getSinkClosed$okio()) {
                            lock.unlock();
                            return -1L;
                        }
                        this.timeout.awaitSignal(pipe.getCondition());
                        if (pipe.getCanceled$okio()) {
                            throw new IOException("canceled");
                        }
                    }
                    long j3 = pipe.getBuffer$okio().read(buffer, j2);
                    pipe.getCondition().signalAll();
                    lock.unlock();
                    return j3;
                } catch (Throwable th) {
                    lock.unlock();
                    throw th;
                }
            }

            @Override // com.shadow.okio.Source
            public Timeout timeout() {
                return this.timeout;
            }
        };
    }

    private final void forward(Sink sink, Function1<? super Sink, Unit> function1) {
        Timeout timeout = sink.timeout();
        Timeout timeout2 = sink().timeout();
        long jTimeoutNanos = timeout.timeoutNanos();
        long jMinTimeout = Timeout.Companion.minTimeout(timeout2.timeoutNanos(), timeout.timeoutNanos());
        TimeUnit timeUnit = TimeUnit.NANOSECONDS;
        timeout.timeout(jMinTimeout, timeUnit);
        if (!timeout.hasDeadline()) {
            if (timeout2.hasDeadline()) {
                timeout.deadlineNanoTime(timeout2.deadlineNanoTime());
            }
            try {
                function1.invoke(sink);
                timeout.timeout(jTimeoutNanos, timeUnit);
                if (timeout2.hasDeadline()) {
                    timeout.clearDeadline();
                    return;
                }
                return;
            } catch (Throwable th) {
                timeout.timeout(jTimeoutNanos, TimeUnit.NANOSECONDS);
                if (timeout2.hasDeadline()) {
                    timeout.clearDeadline();
                }
                throw th;
            }
        }
        long jDeadlineNanoTime = timeout.deadlineNanoTime();
        if (timeout2.hasDeadline()) {
            timeout.deadlineNanoTime(Math.min(timeout.deadlineNanoTime(), timeout2.deadlineNanoTime()));
        }
        try {
            function1.invoke(sink);
            timeout.timeout(jTimeoutNanos, timeUnit);
            if (timeout2.hasDeadline()) {
                timeout.deadlineNanoTime(jDeadlineNanoTime);
            }
        } catch (Throwable th2) {
            timeout.timeout(jTimeoutNanos, TimeUnit.NANOSECONDS);
            if (timeout2.hasDeadline()) {
                timeout.deadlineNanoTime(jDeadlineNanoTime);
            }
            throw th2;
        }
    }

    /* renamed from: -deprecated_sink, reason: not valid java name */
    public final Sink m178deprecated_sink() {
        return this.sink;
    }

    /* renamed from: -deprecated_source, reason: not valid java name */
    public final Source m179deprecated_source() {
        return this.source;
    }

    public final void cancel() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            this.canceled = true;
            this.buffer.clear();
            this.condition.signalAll();
        } finally {
            reentrantLock.unlock();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0055, code lost:
    
        com.shadow.kotlin.io.CloseableKt.throwUninitializedPropertyAccessException("sinkBuffer");
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x005a, code lost:
    
        throw null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void fold(Sink sink) throws IOException {
        Buffer buffer;
        boolean z;
        CloseableKt.checkNotNullParameter(sink, "sink");
        while (true) {
            this.lock.lock();
            try {
                if (this.foldedSink != null) {
                    throw new IllegalStateException("sink already folded");
                }
                if (this.canceled) {
                    this.foldedSink = sink;
                    throw new IOException("canceled");
                }
                boolean z2 = this.sinkClosed;
                if (this.buffer.exhausted()) {
                    this.sourceClosed = true;
                    this.foldedSink = sink;
                    buffer = null;
                    z = true;
                } else {
                    buffer = new Buffer();
                    Buffer buffer2 = this.buffer;
                    buffer.write(buffer2, buffer2.size());
                    this.condition.signalAll();
                    z = false;
                }
                if (z) {
                    if (z2) {
                        sink.close();
                        return;
                    }
                    return;
                }
                if (buffer == null) {
                    break;
                }
                try {
                    sink.write(buffer, buffer.size());
                    sink.flush();
                } catch (Throwable th) {
                    ReentrantLock reentrantLock = this.lock;
                    reentrantLock.lock();
                    this.sourceClosed = true;
                    this.condition.signalAll();
                    throw th;
                }
                ReentrantLock reentrantLock2 = this.lock;
                reentrantLock2.lock();
                try {
                    this.sourceClosed = true;
                    this.condition.signalAll();
                    throw th;
                } finally {
                    reentrantLock2.unlock();
                }
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }

    public final Buffer getBuffer$okio() {
        return this.buffer;
    }

    public final boolean getCanceled$okio() {
        return this.canceled;
    }

    public final Condition getCondition() {
        return this.condition;
    }

    public final Sink getFoldedSink$okio() {
        return this.foldedSink;
    }

    public final ReentrantLock getLock() {
        return this.lock;
    }

    public final long getMaxBufferSize$okio() {
        return this.maxBufferSize;
    }

    public final boolean getSinkClosed$okio() {
        return this.sinkClosed;
    }

    public final boolean getSourceClosed$okio() {
        return this.sourceClosed;
    }

    public final void setCanceled$okio(boolean z) {
        this.canceled = z;
    }

    public final void setFoldedSink$okio(Sink sink) {
        this.foldedSink = sink;
    }

    public final void setSinkClosed$okio(boolean z) {
        this.sinkClosed = z;
    }

    public final void setSourceClosed$okio(boolean z) {
        this.sourceClosed = z;
    }

    public final Sink sink() {
        return this.sink;
    }

    public final Source source() {
        return this.source;
    }
}
