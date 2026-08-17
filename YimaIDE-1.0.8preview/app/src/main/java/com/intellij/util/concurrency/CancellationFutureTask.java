package com.intellij.util.concurrency;

import com.intellij.util.concurrency.CancellationFutureTask;
import java.util.concurrent.CancellationException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.Job;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B-\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0014"}, d2 = {"Lcom/intellij/util/concurrency/CancellationFutureTask;", "V", "Ljava/util/concurrent/FutureTask;", "job", "Lkotlinx/coroutines/Job;", "callable", "Lcom/intellij/util/concurrency/ContextCallable;", "executionTracker", "Ljava/util/concurrent/atomic/AtomicBoolean;", "context", "Lcom/intellij/util/concurrency/ChildContext;", "<init>", "(Lkotlinx/coroutines/Job;Lcom/intellij/util/concurrency/ContextCallable;Ljava/util/concurrent/atomic/AtomicBoolean;Lcom/intellij/util/concurrency/ChildContext;)V", "getExecutionTracker", "()Ljava/util/concurrent/atomic/AtomicBoolean;", "getContext", "()Lcom/intellij/util/concurrency/ChildContext;", "cancel", "", "mayInterruptIfRunning", "intellij.platform.util"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class CancellationFutureTask<V> extends FutureTask<V> {
    private final ChildContext context;
    private final AtomicBoolean executionTracker;
    private final Job job;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CancellationFutureTask(Job job, ContextCallable<V> contextCallable, AtomicBoolean atomicBoolean, ChildContext childContext) {
        super(contextCallable);
        job.getClass();
        contextCallable.getClass();
        atomicBoolean.getClass();
        childContext.getClass();
        this.job = job;
        this.executionTracker = atomicBoolean;
        this.context = childContext;
        job.invokeOnCompletion(true, true, new Function1() { // from class: ac1
            public final Object invoke(Object obj) {
                return CancellationFutureTask.a(this.b, (Throwable) obj);
            }
        });
    }

    public static Unit a(CancellationFutureTask cancellationFutureTask, Throwable th) {
        if (th instanceof CancellationException) {
            cancellationFutureTask.cancel(false);
        }
        return Unit.INSTANCE;
    }

    @Override // java.util.concurrent.FutureTask, java.util.concurrent.Future
    public boolean cancel(boolean mayInterruptIfRunning) {
        boolean andSet = this.executionTracker.getAndSet(true);
        boolean zCancel = super.cancel(mayInterruptIfRunning);
        this.job.cancel((CancellationException) null);
        if (!andSet) {
            this.context.cancelAllIntelliJElements();
        }
        return zCancel;
    }
}
