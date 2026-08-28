package com.shadow.okio;

import com.shadow.kotlin.io.CloseableKt;
import com.shadow.kotlin.jvm.internal.DefaultConstructorMarker;
import com.shadow.kotlin.time.Duration;
import com.shadow.kotlin.time.DurationUnit;
import core.pro.android.notify.h;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import kotlin.jvm.functions.Function0;

/* loaded from: /workspace/unpacked/classes2.dex */
public class Timeout {
    public static final Companion Companion = new Companion(null);
    public static final Timeout NONE = new Timeout() { // from class: com.shadow.okio.Timeout$Companion$NONE$1
        @Override // com.shadow.okio.Timeout
        public Timeout deadlineNanoTime(long j) {
            return this;
        }

        @Override // com.shadow.okio.Timeout
        public void throwIfReached() {
        }

        @Override // com.shadow.okio.Timeout
        public Timeout timeout(long j, TimeUnit timeUnit) {
            CloseableKt.checkNotNullParameter(timeUnit, "unit");
            return this;
        }
    };
    private volatile Object cancelMark;
    private long deadlineNanoTime;
    private boolean hasDeadline;
    private long timeoutNanos;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final long minTimeout(long j, long j2) {
            return (j != 0 && (j2 == 0 || j < j2)) ? j : j2;
        }

        public final Timeout timeout(Timeout timeout, long j, DurationUnit durationUnit) {
            CloseableKt.checkNotNullParameter(timeout, "<this>");
            CloseableKt.checkNotNullParameter(durationUnit, "unit");
            return timeout.timeout(j, durationUnit.getTimeUnit$kotlin_stdlib());
        }

        /* renamed from: timeout-HG0u8IE, reason: not valid java name */
        public final Timeout m180timeoutHG0u8IE(Timeout timeout, long j) {
            CloseableKt.checkNotNullParameter(timeout, "$this$timeout");
            int i = Duration.a;
            long j2 = j >> 1;
            if ((((int) j) & 1) != 0) {
                j2 = j2 > 9223372036854L ? Long.MAX_VALUE : j2 < -9223372036854L ? Long.MIN_VALUE : j2 * 1000000;
            }
            return timeout.timeout(j2, TimeUnit.NANOSECONDS);
        }

        private Companion() {
        }
    }

    public void awaitSignal(Condition condition) throws InterruptedException, InterruptedIOException {
        CloseableKt.checkNotNullParameter(condition, "condition");
        try {
            boolean zHasDeadline = hasDeadline();
            long jTimeoutNanos = timeoutNanos();
            if (!zHasDeadline && jTimeoutNanos == 0) {
                condition.await();
                return;
            }
            if (zHasDeadline && jTimeoutNanos != 0) {
                jTimeoutNanos = Math.min(jTimeoutNanos, deadlineNanoTime() - System.nanoTime());
            } else if (zHasDeadline) {
                jTimeoutNanos = deadlineNanoTime() - System.nanoTime();
            }
            if (jTimeoutNanos <= 0) {
                throw new InterruptedIOException("timeout");
            }
            Object obj = this.cancelMark;
            if (condition.awaitNanos(jTimeoutNanos) <= 0 && this.cancelMark == obj) {
                throw new InterruptedIOException("timeout");
            }
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException("interrupted");
        }
    }

    public void cancel() {
        this.cancelMark = new Object();
    }

    public Timeout clearDeadline() {
        this.hasDeadline = false;
        return this;
    }

    public Timeout clearTimeout() {
        this.timeoutNanos = 0L;
        return this;
    }

    public final Timeout deadline(long j, TimeUnit timeUnit) {
        CloseableKt.checkNotNullParameter(timeUnit, "unit");
        if (j <= 0) {
            throw new IllegalArgumentException(h.c("duration <= 0: ", j).toString());
        }
        return deadlineNanoTime(timeUnit.toNanos(j) + System.nanoTime());
    }

    public long deadlineNanoTime() {
        if (this.hasDeadline) {
            return this.deadlineNanoTime;
        }
        throw new IllegalStateException("No deadline");
    }

    public boolean hasDeadline() {
        return this.hasDeadline;
    }

    public final <T> T intersectWith(Timeout timeout, Function0<? extends T> function0) {
        CloseableKt.checkNotNullParameter(timeout, "other");
        CloseableKt.checkNotNullParameter(function0, "block");
        long jTimeoutNanos = timeoutNanos();
        long jMinTimeout = Companion.minTimeout(timeout.timeoutNanos(), timeoutNanos());
        TimeUnit timeUnit = TimeUnit.NANOSECONDS;
        timeout(jMinTimeout, timeUnit);
        if (!hasDeadline()) {
            if (timeout.hasDeadline()) {
                deadlineNanoTime(timeout.deadlineNanoTime());
            }
            try {
                T t = (T) function0.invoke();
                timeout(jTimeoutNanos, timeUnit);
                if (timeout.hasDeadline()) {
                    clearDeadline();
                }
                return t;
            } catch (Throwable th) {
                timeout(jTimeoutNanos, TimeUnit.NANOSECONDS);
                if (timeout.hasDeadline()) {
                    clearDeadline();
                }
                throw th;
            }
        }
        long jDeadlineNanoTime = deadlineNanoTime();
        if (timeout.hasDeadline()) {
            deadlineNanoTime(Math.min(deadlineNanoTime(), timeout.deadlineNanoTime()));
        }
        try {
            T t2 = (T) function0.invoke();
            timeout(jTimeoutNanos, timeUnit);
            if (timeout.hasDeadline()) {
                deadlineNanoTime(jDeadlineNanoTime);
            }
            return t2;
        } catch (Throwable th2) {
            timeout(jTimeoutNanos, TimeUnit.NANOSECONDS);
            if (timeout.hasDeadline()) {
                deadlineNanoTime(jDeadlineNanoTime);
            }
            throw th2;
        }
    }

    public void throwIfReached() throws IOException {
        if (Thread.currentThread().isInterrupted()) {
            throw new InterruptedIOException("interrupted");
        }
        if (this.hasDeadline && this.deadlineNanoTime - System.nanoTime() <= 0) {
            throw new InterruptedIOException("deadline reached");
        }
    }

    public Timeout timeout(long j, TimeUnit timeUnit) {
        CloseableKt.checkNotNullParameter(timeUnit, "unit");
        if (j < 0) {
            throw new IllegalArgumentException(h.c("timeout < 0: ", j).toString());
        }
        this.timeoutNanos = timeUnit.toNanos(j);
        return this;
    }

    public long timeoutNanos() {
        return this.timeoutNanos;
    }

    public void waitUntilNotified(Object obj) throws InterruptedException, InterruptedIOException {
        CloseableKt.checkNotNullParameter(obj, "monitor");
        try {
            boolean zHasDeadline = hasDeadline();
            long jTimeoutNanos = timeoutNanos();
            if (!zHasDeadline && jTimeoutNanos == 0) {
                obj.wait();
                return;
            }
            long jNanoTime = System.nanoTime();
            if (zHasDeadline && jTimeoutNanos != 0) {
                jTimeoutNanos = Math.min(jTimeoutNanos, deadlineNanoTime() - jNanoTime);
            } else if (zHasDeadline) {
                jTimeoutNanos = deadlineNanoTime() - jNanoTime;
            }
            if (jTimeoutNanos <= 0) {
                throw new InterruptedIOException("timeout");
            }
            Object obj2 = this.cancelMark;
            long j = jTimeoutNanos / 1000000;
            Long.signum(j);
            obj.wait(j, (int) (jTimeoutNanos - (1000000 * j)));
            if (System.nanoTime() - jNanoTime >= jTimeoutNanos && this.cancelMark == obj2) {
                throw new InterruptedIOException("timeout");
            }
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException("interrupted");
        }
    }

    public Timeout deadlineNanoTime(long j) {
        this.hasDeadline = true;
        this.deadlineNanoTime = j;
        return this;
    }
}
