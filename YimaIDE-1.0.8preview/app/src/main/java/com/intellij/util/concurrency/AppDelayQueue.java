package com.intellij.util.concurrency;

import androidx.compose.animation.core.AnimationKt;
import com.intellij.openapi.diagnostic.Logger;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class AppDelayQueue extends DelayQueue<SchedulingWrapper.MyScheduledFutureTask<?>> {
    private static final Logger LOG = Logger.getInstance(AppDelayQueue.class);
    private volatile SchedulingWrapper.MyScheduledFutureTask<Void> myPoisonPill;
    private final AtomicReference<Throwable> shutdownTrace;
    private final TransferThread transferThread;

    public final class TransferThread extends Thread {
        private TransferThread() {
            super("Periodic tasks thread");
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (true) {
                try {
                    SchedulingWrapper.MyScheduledFutureTask<?> myScheduledFutureTaskTake = AppDelayQueue.this.take();
                    if (AppDelayQueue.LOG.isTraceEnabled()) {
                        AppDelayQueue.LOG.trace("Took " + BoundedTaskExecutor.info(myScheduledFutureTaskTake));
                    }
                    try {
                        if (!myScheduledFutureTaskTake.executeMeInBackendExecutor()) {
                            AppDelayQueue.LOG.debug("AppDelayQueue.TransferrerThread Stopped");
                            return;
                        }
                    } catch (Throwable th) {
                        AppDelayQueue.LOG.error("Error executing " + myScheduledFutureTaskTake, th);
                    }
                } catch (InterruptedException e) {
                    if (AppDelayQueue.this.shutdownTrace.get() == null) {
                        AppDelayQueue.LOG.error(e);
                    }
                }
            }
        }
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = i != 3 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i != 3 ? 3 : 2];
        if (i == 1) {
            objArr[0] = "unit";
        } else if (i == 2) {
            objArr[0] = "task";
        } else if (i != 3) {
            objArr[0] = "poisonPill";
        } else {
            objArr[0] = "com/intellij/util/concurrency/AppDelayQueue";
        }
        if (i != 3) {
            objArr[1] = "com/intellij/util/concurrency/AppDelayQueue";
        } else {
            objArr[1] = "getThread";
        }
        if (i == 1) {
            objArr[2] = "awaitTermination";
        } else if (i == 2) {
            objArr[2] = "offer";
        } else if (i != 3) {
            objArr[2] = "shutdown";
        }
        String str2 = String.format(str, objArr);
        if (i == 3) {
            throw new IllegalStateException(str2);
        }
    }

    public AppDelayQueue() {
        TransferThread transferThread = new TransferThread();
        this.transferThread = transferThread;
        this.shutdownTrace = new AtomicReference<>();
        transferThread.setDaemon(true);
        transferThread.start();
    }

    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        if (timeUnit == null) {
            $$$reportNull$$$0(1);
        }
        if (this.shutdownTrace.get() == null) {
            k2d.a("must call shutdown before");
            return false;
        }
        long jNanoTime = System.nanoTime() + timeUnit.toNanos(j);
        try {
            this.myPoisonPill.get(j, timeUnit);
            this.transferThread.join(Math.max(1L, jNanoTime - System.nanoTime()) / AnimationKt.MillisToNanos);
            return !this.transferThread.isAlive();
        } catch (ExecutionException e) {
            rc6.a(e);
            return false;
        } catch (TimeoutException unused) {
            return false;
        }
    }

    @Override // java.util.concurrent.DelayQueue
    public boolean offer(SchedulingWrapper.MyScheduledFutureTask<?> myScheduledFutureTask) {
        if (myScheduledFutureTask == null) {
            $$$reportNull$$$0(2);
        }
        Throwable th = this.shutdownTrace.get();
        if (th == null) {
            return super.offer(myScheduledFutureTask);
        }
        mg9.a("Already shutdown", th);
        return false;
    }
}
