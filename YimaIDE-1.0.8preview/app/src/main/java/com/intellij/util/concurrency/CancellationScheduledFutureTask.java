package com.intellij.util.concurrency;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlinx.coroutines.Job;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
final class CancellationScheduledFutureTask<V> extends SchedulingWrapper.MyScheduledFutureTask<V> {
    private final ChildContext myChildContext;
    private final AtomicBoolean myExecutionTracker;
    private final Job myJob;

    /* JADX WARN: Code duplicated, block: B:14:0x0028  */
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        if (i == 1) {
            objArr[0] = "context";
        } else if (i == 2) {
            objArr[0] = "executionTracker";
        } else if (i == 3) {
            objArr[0] = "callable";
        } else if (i == 5) {
            objArr[0] = "context";
        } else if (i != 6) {
            objArr[0] = "self";
        } else {
            objArr[0] = "r";
        }
        objArr[1] = "com/intellij/util/concurrency/CancellationScheduledFutureTask";
        objArr[2] = "<init>";
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CancellationScheduledFutureTask(SchedulingWrapper schedulingWrapper, ChildContext childContext, Job job, Runnable runnable, long j, long j2) {
        super(schedulingWrapper, runnable, null, j, j2);
        if (schedulingWrapper == null) {
            $$$reportNull$$$0(4);
        }
        if (childContext == null) {
            $$$reportNull$$$0(5);
        }
        if (runnable == null) {
            $$$reportNull$$$0(6);
        }
        Objects.requireNonNull(schedulingWrapper);
        this.myJob = job;
        this.myChildContext = childContext;
        this.myExecutionTracker = new AtomicBoolean(false);
    }

    @Override // com.intellij.util.concurrency.SchedulingWrapper.MyScheduledFutureTask, java.util.concurrent.FutureTask, java.util.concurrent.Future
    public boolean cancel(boolean z) {
        boolean zCancel = super.cancel(z);
        Job job = this.myJob;
        if (job != null) {
            job.cancel((CancellationException) null);
        }
        if (!this.myExecutionTracker.getAndSet(true)) {
            this.myChildContext.cancelAllIntelliJElements();
        }
        return zCancel;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CancellationScheduledFutureTask(SchedulingWrapper schedulingWrapper, ChildContext childContext, Job job, AtomicBoolean atomicBoolean, Callable<V> callable, long j) {
        super(schedulingWrapper, callable, j);
        if (schedulingWrapper == null) {
            $$$reportNull$$$0(0);
        }
        if (childContext == null) {
            $$$reportNull$$$0(1);
        }
        if (atomicBoolean == null) {
            $$$reportNull$$$0(2);
        }
        if (callable == null) {
            $$$reportNull$$$0(3);
        }
        Objects.requireNonNull(schedulingWrapper);
        this.myJob = job;
        this.myChildContext = childContext;
        this.myExecutionTracker = atomicBoolean;
    }
}
