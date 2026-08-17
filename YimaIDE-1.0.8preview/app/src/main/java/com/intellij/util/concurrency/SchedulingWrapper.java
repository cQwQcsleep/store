package com.intellij.util.concurrency;

import androidx.collection.ScatterMapKt;
import androidx.compose.compiler.plugins.kotlin.lower.ComposableFunctionBodyTransformerKt;
import com.intellij.concurrency.ContextAwareRunnable;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.util.IncorrectOperationException;
import com.intellij.util.concurrency.SchedulingWrapper;
import com.intellij.util.containers.ContainerUtil;
import defpackage.iv3;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public class SchedulingWrapper implements ScheduledExecutorService, AutoCloseable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Logger LOG = Logger.getInstance(SchedulingWrapper.class);
    private static final AtomicLong sequencer = new AtomicLong();
    public final ExecutorService backendExecutorService;
    protected final AppDelayQueue delayQueue;
    private final MyScheduledFutureTask<Void> myLaxativePill;
    private final AtomicBoolean shutdown;

    /* JADX WARN: Code duplicated, block: B:9:0x0012  */
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        if (i != 2 && i != 3 && i != 4 && i != 5) {
            switch (i) {
                case 11:
                case 14:
                case 16:
                case 18:
                case 23:
                case 25:
                case 27:
                case 29:
                case ComposableFunctionBodyTransformerKt.BITS_PER_INT /* 31 */:
                case 33:
                case 36:
                case 38:
                    str = "@NotNull method %s.%s must not return null";
                    break;
                default:
                    str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                    break;
            }
        } else {
            str = "@NotNull method %s.%s must not return null";
        }
        if (i != 2 && i != 3 && i != 4 && i != 5) {
            switch (i) {
                case 11:
                case 14:
                case 16:
                case 18:
                case 23:
                case 25:
                case 27:
                case 29:
                case ComposableFunctionBodyTransformerKt.BITS_PER_INT /* 31 */:
                case 33:
                case 36:
                case 38:
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
                objArr[0] = "delayQueue";
                break;
            case 2:
            case 3:
            case 4:
            case 5:
            case 11:
            case 14:
            case 16:
            case 18:
            case 23:
            case 25:
            case 27:
            case 29:
            case ComposableFunctionBodyTransformerKt.BITS_PER_INT /* 31 */:
            case 33:
            case 36:
            case 38:
                objArr[0] = "com/intellij/util/concurrency/SchedulingWrapper";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case 8:
            case 10:
            case 13:
            case 20:
            case 22:
            case 35:
            case 40:
                objArr[0] = "unit";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 26:
            case 28:
            case 30:
                objArr[0] = "task";
                break;
            case 9:
            case 19:
            case 21:
            case 24:
            case 41:
                objArr[0] = "command";
                break;
            case 12:
            case 15:
                objArr[0] = "callable";
                break;
            case 17:
                objArr[0] = "t";
                break;
            case 32:
            case 34:
            case 37:
            case 39:
                objArr[0] = "tasks";
                break;
            default:
                objArr[0] = "backendExecutorService";
                break;
        }
        if (i == 2) {
            objArr[1] = "shutdownNow";
        } else if (i == 3 || i == 4) {
            objArr[1] = "doShutdown";
        } else if (i != 5) {
            switch (i) {
                case 11:
                case 14:
                    objArr[1] = "schedule";
                    break;
                case 16:
                case 25:
                    objArr[1] = "createTask";
                    break;
                case 18:
                    objArr[1] = "delayedExecute";
                    break;
                case 23:
                    objArr[1] = "scheduleWithFixedDelay";
                    break;
                case 27:
                case 29:
                case ComposableFunctionBodyTransformerKt.BITS_PER_INT /* 31 */:
                    objArr[1] = "submit";
                    break;
                case 33:
                case 36:
                    objArr[1] = "invokeAll";
                    break;
                case 38:
                    objArr[1] = "invokeAny";
                    break;
                default:
                    objArr[1] = "com/intellij/util/concurrency/SchedulingWrapper";
                    break;
            }
        } else {
            objArr[1] = "getMyTasksFromDelayQueue";
        }
        switch (i) {
            case 2:
            case 3:
            case 4:
            case 5:
            case 11:
            case 14:
            case 16:
            case 18:
            case 23:
            case 25:
            case 27:
            case 29:
            case ComposableFunctionBodyTransformerKt.BITS_PER_INT /* 31 */:
            case 33:
            case 36:
            case 38:
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[2] = "awaitTermination";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[2] = "futureDone";
                break;
            case 8:
                objArr[2] = "triggerTime";
                break;
            case 9:
            case 10:
            case 12:
            case 13:
                objArr[2] = "schedule";
                break;
            case 15:
            case 24:
                objArr[2] = "createTask";
                break;
            case 17:
                objArr[2] = "delayedExecute";
                break;
            case 19:
            case 20:
                objArr[2] = "scheduleAtFixedRate";
                break;
            case 21:
            case 22:
                objArr[2] = "scheduleWithFixedDelay";
                break;
            case 26:
            case 28:
            case 30:
                objArr[2] = "submit";
                break;
            case 32:
            case 34:
            case 35:
                objArr[2] = "invokeAll";
                break;
            case 37:
            case 39:
            case 40:
                objArr[2] = "invokeAny";
                break;
            case 41:
                objArr[2] = "execute";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 2 && i != 3 && i != 4 && i != 5) {
            switch (i) {
                case 11:
                case 14:
                case 16:
                case 18:
                case 23:
                case 25:
                case 27:
                case 29:
                case ComposableFunctionBodyTransformerKt.BITS_PER_INT /* 31 */:
                case 33:
                case 36:
                case 38:
                    break;
                default:
                    throw new IllegalArgumentException(str2);
            }
        }
        throw new IllegalStateException(str2);
    }

    public SchedulingWrapper(ExecutorService executorService, AppDelayQueue appDelayQueue) {
        if (executorService == null) {
            $$$reportNull$$$0(0);
        }
        if (appDelayQueue == null) {
            $$$reportNull$$$0(1);
        }
        this.shutdown = new AtomicBoolean();
        this.myLaxativePill = new MyScheduledFutureTask<Void>(new Runnable() { // from class: kvc
            @Override // java.lang.Runnable
            public final void run() {
                SchedulingWrapper.c();
            }
        }, null, 0L) { // from class: com.intellij.util.concurrency.SchedulingWrapper.1
            @Override // com.intellij.util.concurrency.SchedulingWrapper.MyScheduledFutureTask
            public boolean executeMeInBackendExecutor() {
                SchedulingWrapper.this.onDelayQueuePurgedOnShutdown();
                set(null);
                return true;
            }

            @Override // com.intellij.util.concurrency.SchedulingWrapper.MyScheduledFutureTask, java.util.concurrent.FutureTask
            public String toString() {
                return "laxative for " + SchedulingWrapper.this;
            }
        };
        this.delayQueue = appDelayQueue;
        if (executorService instanceof ScheduledExecutorService) {
            kg9.a("backendExecutorService: ", executorService, " is already ScheduledExecutorService");
            throw null;
        }
        this.backendExecutorService = executorService;
    }

    public static /* synthetic */ void c() {
    }

    private void checkAlreadyShutdown() {
        if (isShutdown()) {
            throw new RejectedExecutionException("Already shutdown");
        }
    }

    private MyScheduledFutureTask<?> createTask(Runnable runnable, long j, long j2) {
        if (runnable == null) {
            $$$reportNull$$$0(24);
        }
        if (!AppExecutorUtil.propagateContext()) {
            return new MyScheduledFutureTask<>(this, runnable, null, j, j2);
        }
        MyScheduledFutureTask<?> myScheduledFutureTaskCapturePropagationContext = Propagation.capturePropagationContext(this, runnable, j, j2);
        if (myScheduledFutureTaskCapturePropagationContext == null) {
            $$$reportNull$$$0(25);
        }
        return myScheduledFutureTaskCapturePropagationContext;
    }

    private List<MyScheduledFutureTask<?>> getMyTasksFromDelayQueue() {
        ArrayList arrayList = new ArrayList();
        for (MyScheduledFutureTask myScheduledFutureTask : this.delayQueue) {
            if (myScheduledFutureTask.getBackendExecutorService() == this.backendExecutorService) {
                arrayList.add(myScheduledFutureTask);
            }
        }
        return arrayList;
    }

    private long overflowFree(long j) {
        MyScheduledFutureTask<?> myScheduledFutureTaskPeek = this.delayQueue.peek();
        if (myScheduledFutureTaskPeek != null) {
            long delay = myScheduledFutureTaskPeek.getDelay(TimeUnit.NANOSECONDS);
            if (delay < 0 && j - delay < 0) {
                return delay + Long.MAX_VALUE;
            }
        }
        return j;
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        TimeUnit timeUnit2 = TimeUnit.NANOSECONDS;
        if (timeUnit == null) {
            $$$reportNull$$$0(6);
        }
        if (!isShutdown()) {
            k2d.a("must await termination after shutdown() or shutdownNow() only");
            return false;
        }
        long jNanoTime = System.nanoTime() + timeUnit.toNanos(j);
        try {
            this.myLaxativePill.get(jNanoTime - System.nanoTime(), timeUnit2);
        } catch (CancellationException | ExecutionException unused) {
        } catch (TimeoutException unused2) {
            return false;
        }
        List<MyScheduledFutureTask<?>> myTasksFromDelayQueue = getMyTasksFromDelayQueue();
        Iterator<MyScheduledFutureTask<?>> it = myTasksFromDelayQueue.iterator();
        while (it.hasNext()) {
            try {
                it.next().get(jNanoTime - System.nanoTime(), timeUnit2);
            } catch (CancellationException | ExecutionException unused3) {
            } catch (TimeoutException unused4) {
                return false;
            }
        }
        this.delayQueue.removeAll(myTasksFromDelayQueue);
        return this.backendExecutorService.awaitTermination(jNanoTime - System.nanoTime(), timeUnit2);
    }

    @Override // java.lang.AutoCloseable
    public /* synthetic */ void close() {
        iv3.a(this);
    }

    public <T> MyScheduledFutureTask<T> delayedExecute(MyScheduledFutureTask<T> myScheduledFutureTask) {
        if (myScheduledFutureTask == null) {
            $$$reportNull$$$0(17);
        }
        checkAlreadyShutdown();
        this.delayQueue.offer((MyScheduledFutureTask<?>) myScheduledFutureTask);
        TimeUnit timeUnit = TimeUnit.DAYS;
        if (myScheduledFutureTask.getDelay(timeUnit) <= 31 || myScheduledFutureTask.isPeriodic()) {
            return myScheduledFutureTask;
        }
        throw new IllegalArgumentException("Unsupported crazy delay " + myScheduledFutureTask.getDelay(timeUnit) + " days: " + BoundedTaskExecutor.info(myScheduledFutureTask));
    }

    public List<Runnable> doShutdown() {
        if (!this.shutdown.compareAndSet(false, true)) {
            List<Runnable> list = Collections.EMPTY_LIST;
            if (list == null) {
                $$$reportNull$$$0(3);
            }
            return list;
        }
        List<MyScheduledFutureTask<?>> myTasksFromDelayQueue = getMyTasksFromDelayQueue();
        Iterator<MyScheduledFutureTask<?>> it = myTasksFromDelayQueue.iterator();
        while (it.hasNext()) {
            it.next().cancel(false);
        }
        this.delayQueue.removeAll(myTasksFromDelayQueue);
        this.delayQueue.offer((MyScheduledFutureTask<?>) this.myLaxativePill);
        return myTasksFromDelayQueue;
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        if (runnable == null) {
            $$$reportNull$$$0(41);
        }
        checkAlreadyShutdown();
        this.backendExecutorService.execute(runnable);
    }

    public void futureDone(Future<?> future) {
        if (future == null) {
            $$$reportNull$$$0(7);
        }
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) throws InterruptedException {
        if (collection == null) {
            $$$reportNull$$$0(34);
        }
        if (timeUnit == null) {
            $$$reportNull$$$0(35);
        }
        checkAlreadyShutdown();
        List<Future<T>> listInvokeAll = this.backendExecutorService.invokeAll(collection, j, timeUnit);
        if (listInvokeAll == null) {
            $$$reportNull$$$0(36);
        }
        return listInvokeAll;
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> T invokeAny(Collection<? extends Callable<T>> collection) throws ExecutionException, InterruptedException {
        if (collection == null) {
            $$$reportNull$$$0(37);
        }
        checkAlreadyShutdown();
        T t = (T) this.backendExecutorService.invokeAny(collection);
        if (t == null) {
            $$$reportNull$$$0(38);
        }
        return t;
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isShutdown() {
        return this.shutdown.get();
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isTerminated() {
        return isShutdown() && this.backendExecutorService.isTerminated() && getMyTasksFromDelayQueue().isEmpty();
    }

    public void onDelayQueuePurgedOnShutdown() {
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public <V> ScheduledFuture<V> schedule(Callable<V> callable, long j, TimeUnit timeUnit) {
        if (callable == null) {
            $$$reportNull$$$0(12);
        }
        if (timeUnit == null) {
            $$$reportNull$$$0(13);
        }
        MyScheduledFutureTask myScheduledFutureTaskDelayedExecute = delayedExecute(createTask(callable, triggerTime(j, timeUnit)));
        if (myScheduledFutureTaskDelayedExecute == null) {
            $$$reportNull$$$0(14);
        }
        return myScheduledFutureTaskDelayedExecute;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.intellij.util.IncorrectOperationException */
    @Override // java.util.concurrent.ScheduledExecutorService
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable runnable, long j, long j2, TimeUnit timeUnit) throws IncorrectOperationException {
        if (runnable == null) {
            $$$reportNull$$$0(19);
        }
        if (timeUnit == null) {
            $$$reportNull$$$0(20);
        }
        throw new IncorrectOperationException("Not supported because it's bad for hibernation; use scheduleWithFixedDelay() with the same parameters instead.");
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        if (runnable == null) {
            $$$reportNull$$$0(21);
        }
        if (timeUnit == null) {
            $$$reportNull$$$0(22);
        }
        if (j2 <= 0) {
            t01.a("delay must be positive but got: ", j2);
            return null;
        }
        MyScheduledFutureTask myScheduledFutureTaskDelayedExecute = delayedExecute(createTask(runnable, triggerTime(j, timeUnit), timeUnit.toNanos(-j2)));
        if (myScheduledFutureTaskDelayedExecute == null) {
            $$$reportNull$$$0(23);
        }
        return myScheduledFutureTaskDelayedExecute;
    }

    @Override // java.util.concurrent.ExecutorService
    public void shutdown() {
        doShutdown();
    }

    @Override // java.util.concurrent.ExecutorService
    public List<Runnable> shutdownNow() {
        List<Runnable> listDoShutdown = doShutdown();
        ExecutorService executorService = this.backendExecutorService;
        List<Runnable> listClearAndCancelAll = executorService instanceof BoundedTaskExecutor ? ((BoundedTaskExecutor) executorService).clearAndCancelAll() : Collections.EMPTY_LIST;
        try {
            this.myLaxativePill.get();
            List<Runnable> listConcat = ContainerUtil.concat((List) listDoShutdown, (List) listClearAndCancelAll);
            if (listConcat == null) {
                $$$reportNull$$$0(2);
            }
            return listConcat;
        } catch (InterruptedException | ExecutionException e) {
            rc6.a(e);
            return null;
        }
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> Future<T> submit(Callable<T> callable) {
        if (callable == null) {
            $$$reportNull$$$0(26);
        }
        checkAlreadyShutdown();
        Future<T> futureSubmit = this.backendExecutorService.submit(callable);
        if (futureSubmit == null) {
            $$$reportNull$$$0(27);
        }
        return futureSubmit;
    }

    public long triggerTime(long j, TimeUnit timeUnit) {
        if (timeUnit == null) {
            $$$reportNull$$$0(8);
        }
        if (j < 0) {
            j = 0;
        }
        return triggerTime(timeUnit.toNanos(j));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long triggerTime(long j) {
        long jNanoTime = System.nanoTime();
        if (j >= 4611686018427387903L) {
            j = overflowFree(j);
        }
        return jNanoTime + j;
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> T invokeAny(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        if (collection == null) {
            $$$reportNull$$$0(39);
        }
        if (timeUnit == null) {
            $$$reportNull$$$0(40);
        }
        checkAlreadyShutdown();
        return (T) this.backendExecutorService.invokeAny(collection, j, timeUnit);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> Future<T> submit(Runnable runnable, T t) {
        if (runnable == null) {
            $$$reportNull$$$0(28);
        }
        checkAlreadyShutdown();
        Future<T> futureSubmit = this.backendExecutorService.submit(runnable, t);
        if (futureSubmit == null) {
            $$$reportNull$$$0(29);
        }
        return futureSubmit;
    }

    @Override // java.util.concurrent.ExecutorService
    public Future<?> submit(Runnable runnable) {
        if (runnable == null) {
            $$$reportNull$$$0(30);
        }
        checkAlreadyShutdown();
        Future<?> futureSubmit = this.backendExecutorService.submit(runnable);
        if (futureSubmit == null) {
            $$$reportNull$$$0(31);
        }
        return futureSubmit;
    }

    public class MyScheduledFutureTask<V> extends FutureTask<V> implements ContextAwareRunnable, RunnableScheduledFuture<V> {
        private final long period;
        private final long sequenceNumber;
        final /* synthetic */ SchedulingWrapper this$0;
        private long time;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            String str = i != 5 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
            Object[] objArr = new Object[i != 5 ? 3 : 2];
            if (i == 2) {
                objArr[0] = "callable";
            } else if (i == 3) {
                objArr[0] = "unit";
            } else if (i == 4) {
                objArr[0] = "other";
            } else if (i != 5) {
                objArr[0] = "r";
            } else {
                objArr[0] = "com/intellij/util/concurrency/SchedulingWrapper$MyScheduledFutureTask";
            }
            if (i != 5) {
                objArr[1] = "com/intellij/util/concurrency/SchedulingWrapper$MyScheduledFutureTask";
            } else {
                objArr[1] = "getBackendExecutorService";
            }
            if (i == 3) {
                objArr[2] = "getDelay";
            } else if (i == 4) {
                objArr[2] = "compareTo";
            } else if (i != 5) {
                objArr[2] = "<init>";
            }
            String str2 = String.format(str, objArr);
            if (i == 5) {
                throw new IllegalStateException(str2);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MyScheduledFutureTask(SchedulingWrapper schedulingWrapper, Runnable runnable, V v, long j) {
            super(runnable, v);
            if (runnable == null) {
                $$$reportNull$$$0(0);
            }
            this.this$0 = schedulingWrapper;
            this.time = j;
            this.period = 0L;
            this.sequenceNumber = SchedulingWrapper.sequencer.getAndIncrement();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ExecutorService getBackendExecutorService() {
            ExecutorService executorService = this.this$0.backendExecutorService;
            if (executorService == null) {
                $$$reportNull$$$0(5);
            }
            return executorService;
        }

        private void setNextRunTime() {
            long j = this.period;
            if (j > 0) {
                this.time += j;
            } else {
                this.time = this.this$0.triggerTime(-j);
            }
        }

        @Override // java.util.concurrent.FutureTask, java.util.concurrent.Future
        public boolean cancel(boolean z) {
            boolean zCancel = super.cancel(z);
            this.this$0.delayQueue.remove(this);
            return zCancel;
        }

        @Override // java.lang.Comparable
        public int compareTo(Delayed delayed) {
            if (delayed == null) {
                $$$reportNull$$$0(4);
            }
            if (delayed == this) {
                return 0;
            }
            if (delayed instanceof MyScheduledFutureTask) {
                MyScheduledFutureTask myScheduledFutureTask = (MyScheduledFutureTask) delayed;
                long j = this.time - myScheduledFutureTask.time;
                if (j < 0) {
                    return -1;
                }
                return (j <= 0 && this.sequenceNumber < myScheduledFutureTask.sequenceNumber) ? -1 : 1;
            }
            TimeUnit timeUnit = TimeUnit.NANOSECONDS;
            long delay = getDelay(timeUnit) - delayed.getDelay(timeUnit);
            if (delay < 0) {
                return -1;
            }
            return delay > 0 ? 1 : 0;
        }

        public boolean executeMeInBackendExecutor() {
            if (isDone()) {
                return true;
            }
            this.this$0.backendExecutorService.execute(this);
            return true;
        }

        @Override // java.util.concurrent.Delayed
        public long getDelay(TimeUnit timeUnit) {
            if (timeUnit == null) {
                $$$reportNull$$$0(3);
            }
            return timeUnit.convert(this.time - System.nanoTime(), TimeUnit.NANOSECONDS);
        }

        @Override // java.util.concurrent.RunnableScheduledFuture
        public boolean isPeriodic() {
            return this.period != 0;
        }

        @Override // java.util.concurrent.FutureTask, java.util.concurrent.RunnableFuture, java.lang.Runnable
        public void run() {
            if (!isPeriodic()) {
                super.run();
                this.this$0.futureDone(this);
            } else {
                if (!runAndReset() || this.this$0.isShutdown()) {
                    return;
                }
                setNextRunTime();
                this.this$0.delayQueue.offer((MyScheduledFutureTask<?>) this);
                if (this.this$0.isShutdown()) {
                    this.this$0.delayQueue.remove(this);
                }
            }
        }

        @Override // java.util.concurrent.FutureTask
        public void setException(Throwable th) {
            try {
                if (!Logger.shouldRethrow(th)) {
                    SchedulingWrapper.LOG.error(th);
                }
            } finally {
                super.setException(th);
            }
        }

        @Override // java.util.concurrent.FutureTask
        public String toString() {
            Object objInfo = BoundedTaskExecutor.info(this);
            StringBuilder sb = new StringBuilder("Delay: ");
            sb.append(getDelay(TimeUnit.MILLISECONDS));
            sb.append("ms; ");
            if (objInfo == this) {
                objInfo = super.toString();
            }
            sb.append(objInfo);
            sb.append(" backendExecutorService: ");
            sb.append(this.this$0.backendExecutorService);
            return sb.toString();
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MyScheduledFutureTask(SchedulingWrapper schedulingWrapper, Runnable runnable, V v, long j, long j2) {
            super(runnable, v);
            if (runnable == null) {
                $$$reportNull$$$0(1);
            }
            this.this$0 = schedulingWrapper;
            this.time = j;
            this.period = j2;
            this.sequenceNumber = SchedulingWrapper.sequencer.getAndIncrement();
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MyScheduledFutureTask(SchedulingWrapper schedulingWrapper, Callable<V> callable, long j) {
            super(callable);
            if (callable == null) {
                $$$reportNull$$$0(2);
            }
            this.this$0 = schedulingWrapper;
            this.time = j;
            this.period = 0L;
            this.sequenceNumber = SchedulingWrapper.sequencer.getAndIncrement();
        }
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection) throws InterruptedException {
        if (collection == null) {
            $$$reportNull$$$0(32);
        }
        checkAlreadyShutdown();
        List<Future<T>> listInvokeAll = this.backendExecutorService.invokeAll(collection);
        if (listInvokeAll == null) {
            $$$reportNull$$$0(33);
        }
        return listInvokeAll;
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public ScheduledFuture<?> schedule(Runnable runnable, long j, TimeUnit timeUnit) {
        if (runnable == null) {
            $$$reportNull$$$0(9);
        }
        if (timeUnit == null) {
            $$$reportNull$$$0(10);
        }
        ScheduledFuture<?> scheduledFutureSchedule = schedule(Executors.callable(runnable), j, timeUnit);
        if (scheduledFutureSchedule == null) {
            $$$reportNull$$$0(11);
        }
        return scheduledFutureSchedule;
    }

    private <V> MyScheduledFutureTask<V> createTask(Callable<V> callable, long j) {
        if (callable == null) {
            $$$reportNull$$$0(15);
        }
        if (!AppExecutorUtil.propagateContext()) {
            return new MyScheduledFutureTask<>(this, callable, j);
        }
        MyScheduledFutureTask<V> myScheduledFutureTaskCapturePropagationContext = Propagation.capturePropagationContext(this, callable, j);
        if (myScheduledFutureTaskCapturePropagationContext == null) {
            $$$reportNull$$$0(16);
        }
        return myScheduledFutureTaskCapturePropagationContext;
    }
}
