package com.intellij.util.io.pagecache.impl;

import com.intellij.util.ThrowableRunnable;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public class Throttler {
    private long lastExecutedAtNs;
    private final long thresholdNs;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        if (i != 1) {
            objArr[0] = "unit";
        } else {
            objArr[0] = "task";
        }
        objArr[1] = "com/intellij/util/io/pagecache/impl/Throttler";
        if (i != 1) {
            objArr[2] = "<init>";
        } else {
            objArr[2] = "runThrottled";
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    public Throttler(long j, TimeUnit timeUnit) {
        if (timeUnit == null) {
            $$$reportNull$$$0(0);
        }
        this.thresholdNs = timeUnit.toNanos(j);
    }

    public boolean isTimeForNextRun(long j) {
        if (j - this.thresholdNs <= this.lastExecutedAtNs) {
            return false;
        }
        this.lastExecutedAtNs = j;
        return true;
    }

    public <E extends Throwable> boolean runThrottled(long j, ThrowableRunnable<E> throwableRunnable) throws Throwable {
        if (throwableRunnable == null) {
            $$$reportNull$$$0(1);
        }
        if (!isTimeForNextRun(j)) {
            return false;
        }
        throwableRunnable.run();
        return true;
    }
}
