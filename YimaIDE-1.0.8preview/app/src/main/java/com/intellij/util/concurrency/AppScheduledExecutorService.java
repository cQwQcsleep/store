package com.intellij.util.concurrency;

import androidx.collection.ScatterMapKt;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.progress.ProcessCanceledException;
import com.intellij.openapi.util.LowMemoryWatcherManager;
import com.intellij.util.IncorrectOperationException;
import defpackage.iv3;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class AppScheduledExecutorService extends SchedulingWrapper {
    private final LowMemoryWatcherManager myLowMemoryWatcherManager;
    private final String myName;

    /* JADX INFO: renamed from: com.intellij.util.concurrency.AppScheduledExecutorService$1, reason: invalid class name */
    public class AnonymousClass1 extends SchedulingWrapper.MyScheduledFutureTask<Void> {
        @Override // com.intellij.util.concurrency.SchedulingWrapper.MyScheduledFutureTask
        public boolean executeMeInBackendExecutor() {
            set(null);
            return false;
        }
    }

    public static final class Holder {
        private static final AppScheduledExecutorService INSTANCE = new AppScheduledExecutorService("Global instance", 1, TimeUnit.MINUTES);
    }

    /* JADX WARN: Code duplicated, block: B:37:0x0062  */
    /* JADX WARN: Code duplicated, block: B:58:0x00a8  */
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 1 || i == 2 || i == 3 || i == 5 || i == 12 || i == 14 || i == 8 || i == 9) ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[(i == 1 || i == 2 || i == 3 || i == 5 || i == 12 || i == 14 || i == 8 || i == 9) ? 3 : 2];
        if (i == 1) {
            objArr[0] = "name";
        } else if (i == 2) {
            objArr[0] = "unit";
        } else if (i == 3) {
            objArr[0] = "threadListener";
        } else if (i == 5) {
            objArr[0] = "unit";
        } else if (i == 12) {
            objArr[0] = "r";
        } else if (i == 14) {
            objArr[0] = "callable";
        } else if (i == 8) {
            objArr[0] = "unit";
        } else if (i != 9) {
            objArr[0] = "com/intellij/util/concurrency/AppScheduledExecutorService";
        } else {
            objArr[0] = "command";
        }
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 5:
            case 8:
            case 9:
            case 12:
            case 14:
                objArr[1] = "com/intellij/util/concurrency/AppScheduledExecutorService";
                break;
            case 4:
                objArr[1] = "shutdownNow";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[1] = "statistics";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[1] = "getPeriodicTasksThread";
                break;
            case 10:
            case 11:
            case 15:
                objArr[1] = "capturePropagationAndCancellationContext";
                break;
            case 13:
                objArr[1] = "captureContextCancellationForRunnableThatDoesNotOutliveContextScope";
                break;
            default:
                objArr[1] = "getInstance";
                break;
        }
        if (i == 1 || i == 2) {
            objArr[2] = "<init>";
        } else if (i == 3) {
            objArr[2] = "setNewThreadListener";
        } else if (i == 5) {
            objArr[2] = "awaitTermination";
        } else if (i == 12) {
            objArr[2] = "captureContextCancellationForRunnableThatDoesNotOutliveContextScope";
        } else if (i == 14) {
            objArr[2] = "capturePropagationAndCancellationContext";
        } else if (i == 8) {
            objArr[2] = "waitForLowMemoryWatcherManagerInit";
        } else if (i == 9) {
            objArr[2] = "capturePropagationAndCancellationContext";
        }
        String str2 = String.format(str, objArr);
        if (i != 1 && i != 2 && i != 3 && i != 5 && i != 12 && i != 14 && i != 8 && i != 9) {
            throw new IllegalStateException(str2);
        }
        throw new IllegalArgumentException(str2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppScheduledExecutorService(String str, long j, TimeUnit timeUnit) {
        super(new BackendThreadPoolExecutor(new MyThreadFactory(null), j, timeUnit), new AppDelayQueue());
        if (str == null) {
            $$$reportNull$$$0(1);
        }
        if (timeUnit == null) {
            $$$reportNull$$$0(2);
        }
        this.myName = str;
        this.myLowMemoryWatcherManager = new LowMemoryWatcherManager(this);
    }

    public static Runnable capturePropagationAndCancellationContext(Runnable runnable) {
        if (runnable == null) {
            $$$reportNull$$$0(9);
        }
        if (!AppExecutorUtil.propagateContext()) {
            if (runnable == null) {
                $$$reportNull$$$0(10);
            }
            return runnable;
        }
        Runnable runnableCapturePropagationContext = Propagation.capturePropagationContext(runnable, false);
        if (runnableCapturePropagationContext == null) {
            $$$reportNull$$$0(11);
        }
        return runnableCapturePropagationContext;
    }

    public static ScheduledExecutorService getInstance() {
        AppScheduledExecutorService appScheduledExecutorService = Holder.INSTANCE;
        if (appScheduledExecutorService == null) {
            $$$reportNull$$$0(0);
        }
        return appScheduledExecutorService;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.intellij.util.IncorrectOperationException */
    public static List<Runnable> notAllowedMethodCall() throws IncorrectOperationException {
        throw new IncorrectOperationException("You must not call this method on the global app pool");
    }

    @Override // com.intellij.util.concurrency.SchedulingWrapper, java.util.concurrent.ExecutorService
    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        if (timeUnit == null) {
            $$$reportNull$$$0(5);
        }
        long jNanoTime = System.nanoTime() + timeUnit.toNanos(j);
        AppDelayQueue appDelayQueue = this.delayQueue;
        long jNanoTime2 = jNanoTime - System.nanoTime();
        TimeUnit timeUnit2 = TimeUnit.NANOSECONDS;
        if (appDelayQueue.awaitTermination(jNanoTime2, timeUnit2)) {
            return super.awaitTermination(jNanoTime - System.nanoTime(), timeUnit2);
        }
        return false;
    }

    @Override // com.intellij.util.concurrency.SchedulingWrapper
    public void onDelayQueuePurgedOnShutdown() {
        ((BackendThreadPoolExecutor) this.backendExecutorService).superShutdown();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.intellij.util.IncorrectOperationException */
    @Override // com.intellij.util.concurrency.SchedulingWrapper, java.util.concurrent.ExecutorService
    public void shutdown() throws IncorrectOperationException {
        notAllowedMethodCall();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.intellij.util.IncorrectOperationException */
    @Override // com.intellij.util.concurrency.SchedulingWrapper, java.util.concurrent.ExecutorService
    public List<Runnable> shutdownNow() throws IncorrectOperationException {
        List<Runnable> listNotAllowedMethodCall = notAllowedMethodCall();
        if (listNotAllowedMethodCall == null) {
            $$$reportNull$$$0(4);
        }
        return listNotAllowedMethodCall;
    }

    public static final class BackendThreadPoolExecutor extends ThreadPoolExecutor implements AutoCloseable {
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            String str = (i == 3 || i == 4) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
            Object[] objArr = new Object[(i == 3 || i == 4) ? 2 : 3];
            if (i == 1) {
                objArr[0] = "unit";
            } else if (i == 2) {
                objArr[0] = "command";
            } else if (i == 3 || i == 4) {
                objArr[0] = "com/intellij/util/concurrency/AppScheduledExecutorService$BackendThreadPoolExecutor";
            } else if (i != 5) {
                objArr[0] = "factory";
            } else {
                objArr[0] = "threadFactory";
            }
            if (i == 3) {
                objArr[1] = "superShutdownNow";
            } else if (i != 4) {
                objArr[1] = "com/intellij/util/concurrency/AppScheduledExecutorService$BackendThreadPoolExecutor";
            } else {
                objArr[1] = "shutdownNow";
            }
            if (i == 2) {
                objArr[2] = "execute";
            } else if (i != 3 && i != 4) {
                if (i != 5) {
                    objArr[2] = "<init>";
                } else {
                    objArr[2] = "setThreadFactory";
                }
            }
            String str2 = String.format(str, objArr);
            if (i != 3 && i != 4) {
                throw new IllegalArgumentException(str2);
            }
            throw new IllegalStateException(str2);
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public BackendThreadPoolExecutor(ThreadFactory threadFactory, long j, TimeUnit timeUnit) {
            super(1, Integer.MAX_VALUE, j, timeUnit, new SynchronousQueue(), threadFactory);
            if (threadFactory == null) {
                $$$reportNull$$$0(0);
            }
            if (timeUnit == null) {
                $$$reportNull$$$0(1);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void superShutdown() {
            super.shutdown();
        }

        @Override // java.util.concurrent.ThreadPoolExecutor
        public void afterExecute(Runnable runnable, Throwable th) {
            if (th == null || (th instanceof ProcessCanceledException)) {
                return;
            }
            Logger.getInstance(SchedulingWrapper.class).error("Worker exited due to exception", th);
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.intellij.util.IncorrectOperationException */
        @Override // java.util.concurrent.ThreadPoolExecutor
        public void allowCoreThreadTimeOut(boolean z) throws IncorrectOperationException {
            AppScheduledExecutorService.notAllowedMethodCall();
        }

        @Override // java.lang.AutoCloseable
        public /* synthetic */ void close() {
            iv3.a(this);
        }

        @Override // java.util.concurrent.ThreadPoolExecutor, java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            if (runnable == null) {
                $$$reportNull$$$0(2);
            }
            super.execute(AppScheduledExecutorService.capturePropagationAndCancellationContext(runnable));
        }

        @Override // java.util.concurrent.AbstractExecutorService
        public <T> RunnableFuture<T> newTaskFor(Runnable runnable, T t) {
            return newTaskFor(Executors.callable(runnable, t));
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.intellij.util.IncorrectOperationException */
        @Override // java.util.concurrent.ThreadPoolExecutor
        public void setCorePoolSize(int i) throws IncorrectOperationException {
            AppScheduledExecutorService.notAllowedMethodCall();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.intellij.util.IncorrectOperationException */
        @Override // java.util.concurrent.ThreadPoolExecutor
        public void setKeepAliveTime(long j, TimeUnit timeUnit) throws IncorrectOperationException {
            AppScheduledExecutorService.notAllowedMethodCall();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.intellij.util.IncorrectOperationException */
        @Override // java.util.concurrent.ThreadPoolExecutor
        public void setMaximumPoolSize(int i) throws IncorrectOperationException {
            AppScheduledExecutorService.notAllowedMethodCall();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.intellij.util.IncorrectOperationException */
        @Override // java.util.concurrent.ThreadPoolExecutor
        public void setThreadFactory(ThreadFactory threadFactory) throws IncorrectOperationException {
            if (threadFactory == null) {
                $$$reportNull$$$0(5);
            }
            AppScheduledExecutorService.notAllowedMethodCall();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.intellij.util.IncorrectOperationException */
        @Override // java.util.concurrent.ThreadPoolExecutor, java.util.concurrent.ExecutorService
        public void shutdown() throws IncorrectOperationException {
            AppScheduledExecutorService.notAllowedMethodCall();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.intellij.util.IncorrectOperationException */
        @Override // java.util.concurrent.ThreadPoolExecutor, java.util.concurrent.ExecutorService
        public List<Runnable> shutdownNow() throws IncorrectOperationException {
            List<Runnable> listNotAllowedMethodCall = AppScheduledExecutorService.notAllowedMethodCall();
            if (listNotAllowedMethodCall == null) {
                $$$reportNull$$$0(4);
            }
            return listNotAllowedMethodCall;
        }

        @Override // java.util.concurrent.AbstractExecutorService
        public <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
            return AppScheduledExecutorService.capturePropagationAndCancellationContext(callable);
        }
    }

    public static final class MyThreadFactory extends CountingThreadFactory {
        private final ThreadFactory myThreadFactory;
        private BiConsumer<? super Thread, ? super Runnable> newThreadListener;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            String str = i != 1 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
            Object[] objArr = new Object[i != 1 ? 3 : 2];
            if (i == 1) {
                objArr[0] = "com/intellij/util/concurrency/AppScheduledExecutorService$MyThreadFactory";
            } else if (i != 2) {
                objArr[0] = "r";
            } else {
                objArr[0] = "threadListener";
            }
            if (i != 1) {
                objArr[1] = "com/intellij/util/concurrency/AppScheduledExecutorService$MyThreadFactory";
            } else {
                objArr[1] = "newThread";
            }
            if (i != 1) {
                if (i != 2) {
                    objArr[2] = "newThread";
                } else {
                    objArr[2] = "setNewThreadListener";
                }
            }
            String str2 = String.format(str, objArr);
            if (i == 1) {
                throw new IllegalStateException(str2);
            }
        }

        private MyThreadFactory() {
            this.myThreadFactory = Executors.privilegedThreadFactory();
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            if (runnable == null) {
                $$$reportNull$$$0(0);
            }
            Thread threadNewThread = this.myThreadFactory.newThread(runnable);
            threadNewThread.setName("ApplicationImpl pooled thread " + this.counter.incrementAndGet());
            threadNewThread.setPriority(4);
            BiConsumer<? super Thread, ? super Runnable> biConsumer = this.newThreadListener;
            if (biConsumer != null) {
                biConsumer.accept(threadNewThread, runnable);
            }
            return threadNewThread;
        }

        public /* synthetic */ MyThreadFactory(AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    public static <T> FutureTask<T> capturePropagationAndCancellationContext(Callable<T> callable) {
        if (callable == null) {
            $$$reportNull$$$0(14);
        }
        if (!AppExecutorUtil.propagateContext()) {
            return new FutureTask<>(callable);
        }
        FutureTask<T> futureTaskCapturePropagationContext = Propagation.capturePropagationContext(callable);
        if (futureTaskCapturePropagationContext == null) {
            $$$reportNull$$$0(15);
        }
        return futureTaskCapturePropagationContext;
    }
}
