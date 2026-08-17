package com.intellij.util.concurrency;

import androidx.collection.ScatterMapKt;
import com.intellij.concurrency.ContextAwareRunnable;
import com.intellij.concurrency.ThreadContext;
import com.intellij.openapi.application.AccessToken;
import com.intellij.openapi.diagnostic.ControlFlowException;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.util.ConcurrencyUtil;
import com.intellij.util.Function;
import com.intellij.util.ReflectionUtil;
import com.intellij.util.concurrency.BoundedTaskExecutor;
import com.intellij.util.containers.ContainerUtil;
import defpackage.iv3;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.LongUnaryOperator;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class BoundedTaskExecutor extends AbstractExecutorService implements AutoCloseable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Executor myBackendExecutor;
    private final boolean myChangeThreadName;
    private final int myMaxThreads;
    private final String myName;
    private volatile boolean myShutdown;
    private final AtomicLong myStatus;
    private final BlockingQueue<Runnable> myTaskQueue;

    /* JADX INFO: renamed from: com.intellij.util.concurrency.BoundedTaskExecutor$1, reason: invalid class name */
    public class AnonymousClass1 implements ContextAwareRunnable {
        final AtomicReference<Runnable> currentTask;
        final /* synthetic */ Runnable val$firstTask;
        final /* synthetic */ long val$status;

        public AnonymousClass1(Runnable runnable, long j) {
            this.val$firstTask = runnable;
            this.val$status = j;
            this.currentTask = new AtomicReference<>(runnable);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void executeFirstTaskAndHelpQueue() {
            Runnable runnablePollOrGiveUp = this.currentTask.get();
            do {
                this.currentTask.set(runnablePollOrGiveUp);
                BoundedTaskExecutor.doRun(runnablePollOrGiveUp);
                runnablePollOrGiveUp = BoundedTaskExecutor.this.pollOrGiveUp(this.val$status);
            } while (runnablePollOrGiveUp != null);
        }

        public void run() {
            AccessToken accessTokenResetThreadContext = AppExecutorUtil.propagateContext() ? ThreadContext.resetThreadContext() : AccessToken.EMPTY_ACCESS_TOKEN;
            try {
                if (BoundedTaskExecutor.this.myChangeThreadName) {
                    ConcurrencyUtil.runUnderThreadName(BoundedTaskExecutor.this.myName, new Runnable() { // from class: com.intellij.util.concurrency.a
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.b.executeFirstTaskAndHelpQueue();
                        }
                    });
                } else {
                    executeFirstTaskAndHelpQueue();
                }
            } finally {
                accessTokenResetThreadContext.close();
            }
        }

        public String toString() {
            return String.valueOf(BoundedTaskExecutor.info(this.currentTask.get()));
        }
    }

    public static final class LastTask extends FutureTask<Void> {
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "runnable", "com/intellij/util/concurrency/BoundedTaskExecutor$LastTask", "<init>"));
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public LastTask(Runnable runnable) {
            super(runnable, null);
            if (runnable == null) {
                $$$reportNull$$$0(0);
            }
        }
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 5 || i == 8 || i == 10 || i == 15) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 5 || i == 8 || i == 10 || i == 15) ? 2 : 3];
        switch (i) {
            case 1:
            case 3:
                objArr[0] = "backendExecutor";
                break;
            case 2:
            default:
                objArr[0] = "name";
                break;
            case 4:
                objArr[0] = "queue";
                break;
            case 5:
            case 8:
            case 10:
            case 15:
                objArr[0] = "com/intellij/util/concurrency/BoundedTaskExecutor";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case 14:
                objArr[0] = "unit";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[0] = "runnable";
                break;
            case 9:
                objArr[0] = "callable";
                break;
            case 11:
                objArr[0] = "command";
                break;
            case 12:
                objArr[0] = "firstTask";
                break;
            case 13:
                objArr[0] = "task";
                break;
        }
        if (i == 5) {
            objArr[1] = "shutdownNow";
        } else if (i == 8 || i == 10) {
            objArr[1] = "newTaskFor";
        } else if (i != 15) {
            objArr[1] = "com/intellij/util/concurrency/BoundedTaskExecutor";
        } else {
            objArr[1] = "clearAndCancelAll";
        }
        switch (i) {
            case 5:
            case 8:
            case 10:
            case 15:
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[2] = "awaitTermination";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 9:
                objArr[2] = "newTaskFor";
                break;
            case 11:
                objArr[2] = "execute";
                break;
            case 12:
                objArr[2] = "wrapAndExecute";
                break;
            case 13:
                objArr[2] = "doRun";
                break;
            case 14:
                objArr[2] = "waitAllTasksExecuted";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 5 && i != 8 && i != 10 && i != 15) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public BoundedTaskExecutor(String str, Executor executor, int i, boolean z, BlockingQueue<Runnable> blockingQueue) {
        if (str == null) {
            $$$reportNull$$$0(2);
        }
        if (executor == null) {
            $$$reportNull$$$0(3);
        }
        if (blockingQueue == null) {
            $$$reportNull$$$0(4);
        }
        this.myStatus = new AtomicLong();
        if (str.isEmpty() || !Character.isUpperCase(str.charAt(0))) {
            Logger.getInstance(BoundedTaskExecutor.class).warn("Pool name must be capitalized but got: '" + str + "'", new IllegalArgumentException());
        }
        this.myName = str;
        this.myBackendExecutor = executor;
        if (i < 1) {
            qf1.a("maxThreads must be >=1 but got: ", i);
            throw null;
        }
        if (executor instanceof BoundedTaskExecutor) {
            aca.a("backendExecutor is already BoundedTaskExecutor: ", executor);
            throw null;
        }
        this.myMaxThreads = i;
        this.myChangeThreadName = z;
        this.myTaskQueue = blockingQueue;
    }

    public static /* synthetic */ Future a(BoundedTaskExecutor boundedTaskExecutor, Runnable runnable, Object obj) {
        boundedTaskExecutor.getClass();
        LastTask lastTask = new LastTask(runnable);
        boundedTaskExecutor.execute(lastTask);
        return lastTask;
    }

    public static /* synthetic */ long c(long j) {
        return (j + 4294967297L) & Long.MAX_VALUE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void doRun(Runnable runnable) {
        if (runnable == null) {
            $$$reportNull$$$0(13);
        }
        try {
            runnable.run();
        } catch (Throwable th) {
            if (th instanceof ControlFlowException) {
                return;
            }
            try {
                Logger.getInstance(BoundedTaskExecutor.class).error(th);
            } catch (Throwable unused) {
            }
        }
    }

    private static int getTasksInProgress(long j) {
        return (int) j;
    }

    private long incrementCounterAndTimestamp() {
        return this.myStatus.updateAndGet(new LongUnaryOperator() { // from class: nz0
            @Override // java.util.function.LongUnaryOperator
            public final long applyAsLong(long j) {
                return BoundedTaskExecutor.c(j);
            }
        });
    }

    public static Object info(Runnable runnable) {
        Object field;
        boolean z = runnable instanceof FutureTask;
        String str = null;
        Object obj = runnable;
        if (z) {
            FutureTask futureTask = (FutureTask) runnable;
            if (futureTask.isCancelled()) {
                str = " (future cancelled)";
            } else if (futureTask.isDone()) {
                str = " (future done)";
            }
            Object field2 = ReflectionUtil.getField(runnable.getClass(), runnable, Callable.class, "callable");
            obj = runnable;
            if (field2 != null) {
                obj = field2;
            }
        }
        boolean z2 = obj instanceof Callable;
        Object obj2 = obj;
        if (z2 && obj.getClass().getName().equals("java.util.concurrent.Executors$RunnableAdapter") && (field = ReflectionUtil.getField(obj.getClass(), obj, Runnable.class, "task")) != null) {
            obj2 = obj;
            obj2 = obj;
            obj2 = field;
        }
        if (str == null) {
            return obj2;
        }
        return obj2.getClass() + str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Runnable pollOrGiveUp(long j) {
        Runnable runnablePoll;
        while (true) {
            if (getTasksInProgress(j) <= this.myMaxThreads && (runnablePoll = this.myTaskQueue.poll()) != null) {
                return runnablePoll;
            }
            if (this.myStatus.compareAndSet(j, j - 1)) {
                return null;
            }
            j = this.myStatus.get();
        }
    }

    private void wrapAndExecute(Runnable runnable, long j) {
        if (runnable == null) {
            $$$reportNull$$$0(12);
        }
        try {
            this.myBackendExecutor.execute(new AnonymousClass1(runnable, j));
        } catch (Error | RuntimeException e) {
            this.myStatus.decrementAndGet();
            throw e;
        }
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean awaitTermination(long j, TimeUnit timeUnit) throws Throwable {
        if (timeUnit == null) {
            $$$reportNull$$$0(6);
        }
        if (!isShutdown()) {
            k2d.a("must await termination after shutdown() or shutdownNow() only");
            return false;
        }
        long jNanoTime = System.nanoTime() + timeUnit.toNanos(j);
        while (!isTerminated()) {
            try {
                waitAllTasksExecuted(jNanoTime - System.nanoTime(), TimeUnit.NANOSECONDS);
            } catch (ExecutionException e) {
                rc6.a(e.getCause());
                return false;
            } catch (TimeoutException unused) {
                return false;
            }
        }
        return true;
    }

    public List<Runnable> clearAndCancelAll() {
        ArrayList arrayList = new ArrayList(this.myTaskQueue.size());
        this.myTaskQueue.drainTo(arrayList);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Runnable runnableUnwrapContextRunnable = Propagation.unwrapContextRunnable((Runnable) it.next());
            if ((runnableUnwrapContextRunnable instanceof FutureTask) && !(runnableUnwrapContextRunnable instanceof LastTask)) {
                ((FutureTask) runnableUnwrapContextRunnable).cancel(false);
            }
        }
        return arrayList;
    }

    @Override // java.lang.AutoCloseable
    public /* synthetic */ void close() {
        iv3.a(this);
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        if (runnable == null) {
            $$$reportNull$$$0(11);
        }
        if (!(runnable instanceof LastTask)) {
            runnable = AppScheduledExecutorService.capturePropagationAndCancellationContext(runnable);
        }
        if (isShutdown() && !(runnable instanceof LastTask)) {
            StringBuilder sb = new StringBuilder();
            sb.append(this);
            sb.append(" is already shutdown, trying to execute ");
            sb.append(runnable);
            Class<?> cls = runnable.getClass();
            sb.append(" (");
            sb.append(cls);
            sb.append(")");
            throw new RejectedExecutionException(sb.toString());
        }
        long jIncrementCounterAndTimestamp = incrementCounterAndTimestamp();
        if (getTasksInProgress(jIncrementCounterAndTimestamp) <= this.myMaxThreads) {
            wrapAndExecute(runnable, jIncrementCounterAndTimestamp);
        } else {
            if (!this.myTaskQueue.offer(runnable)) {
                throw new RejectedExecutionException();
            }
            Runnable runnablePollOrGiveUp = pollOrGiveUp(jIncrementCounterAndTimestamp);
            if (runnablePollOrGiveUp != null) {
                wrapAndExecute(runnablePollOrGiveUp, jIncrementCounterAndTimestamp);
            }
        }
    }

    public boolean isEmpty() {
        return getTasksInProgress(this.myStatus.get()) == 0;
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isShutdown() {
        return this.myShutdown;
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isTerminated() {
        return this.myShutdown && isEmpty() && this.myTaskQueue.isEmpty();
    }

    @Override // java.util.concurrent.AbstractExecutorService
    public <T> RunnableFuture<T> newTaskFor(Runnable runnable, T t) {
        if (runnable == null) {
            $$$reportNull$$$0(7);
        }
        RunnableFuture<T> runnableFutureNewTaskFor = newTaskFor(Executors.callable(runnable, t));
        if (runnableFutureNewTaskFor == null) {
            $$$reportNull$$$0(8);
        }
        return runnableFutureNewTaskFor;
    }

    @Override // java.util.concurrent.ExecutorService
    public void shutdown() {
        this.myShutdown = true;
    }

    @Override // java.util.concurrent.ExecutorService
    public List<Runnable> shutdownNow() {
        shutdown();
        List<Runnable> listClearAndCancelAll = clearAndCancelAll();
        if (listClearAndCancelAll == null) {
            $$$reportNull$$$0(5);
        }
        return listClearAndCancelAll;
    }

    public String toString() {
        int size = this.myTaskQueue.size();
        StringBuilder sb = new StringBuilder("BoundedExecutor(");
        sb.append(this.myMaxThreads);
        sb.append(")");
        String str = "";
        sb.append(isShutdown() ? " SHUTDOWN " : "");
        sb.append("; inProgress: ");
        sb.append(getTasksInProgress(this.myStatus.get()));
        if (size != 0) {
            str = "; queue: " + size;
        }
        sb.append(str);
        sb.append("; name: ");
        sb.append(this.myName);
        return sb.toString();
    }

    public synchronized void waitAllTasksExecuted(final long j, final TimeUnit timeUnit) throws Throwable {
        final BoundedTaskExecutor boundedTaskExecutor;
        Throwable th;
        if (timeUnit == null) {
            try {
                $$$reportNull$$$0(14);
            } catch (Throwable th2) {
                th = th2;
                boundedTaskExecutor = this;
                throw th;
            }
        }
        try {
            final CountDownLatch countDownLatch = new CountDownLatch(this.myMaxThreads);
            final CountDownLatch countDownLatch2 = new CountDownLatch(1);
            boundedTaskExecutor = this;
            try {
                final Runnable runnable = new Runnable() { // from class: com.intellij.util.concurrency.BoundedTaskExecutor.2
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            countDownLatch.countDown();
                            countDownLatch2.await();
                        } catch (InterruptedException e) {
                            rc6.a(e);
                        }
                    }

                    public String toString() {
                        return "LastTask to waitAllTasksExecuted for " + j + " " + timeUnit + " (" + System.identityHashCode(this) + ")";
                    }
                };
                List map = ContainerUtil.map((Collection) Collections.nCopies(boundedTaskExecutor.myMaxThreads, null), new Function() { // from class: oz0
                    @Override // com.intellij.util.Function
                    public final Object fun(Object obj) {
                        return BoundedTaskExecutor.a(this.b, runnable, obj);
                    }
                });
                long jNanoTime = System.nanoTime() + timeUnit.toNanos(j);
                try {
                    try {
                        if (!countDownLatch.await(j, timeUnit)) {
                            throw new TimeoutException("Interrupted by timeout. " + boundedTaskExecutor);
                        }
                        countDownLatch2.countDown();
                        ConcurrencyUtil.getAll(Math.max(0L, jNanoTime - System.nanoTime()), TimeUnit.NANOSECONDS, map);
                        return;
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } catch (Throwable th3) {
                    countDownLatch2.countDown();
                    throw th3;
                }
                countDownLatch2.countDown();
                throw th3;
            } catch (Throwable th4) {
                th = th4;
                th = th;
                throw th;
            }
        } catch (Throwable th5) {
            th = th5;
            boundedTaskExecutor = this;
        }
    }

    @Override // java.util.concurrent.AbstractExecutorService
    public <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        if (callable == null) {
            $$$reportNull$$$0(9);
        }
        FutureTask futureTaskCapturePropagationAndCancellationContext = AppScheduledExecutorService.capturePropagationAndCancellationContext(callable);
        if (futureTaskCapturePropagationAndCancellationContext == null) {
            $$$reportNull$$$0(10);
        }
        return futureTaskCapturePropagationAndCancellationContext;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public BoundedTaskExecutor(String str, Executor executor, int i, boolean z) {
        this(str, executor, i, z, new LinkedBlockingQueue());
        if (str == null) {
            $$$reportNull$$$0(0);
        }
        if (executor == null) {
            $$$reportNull$$$0(1);
        }
    }
}
