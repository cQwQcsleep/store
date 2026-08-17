package org.jetbrains.kotlin.storage;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0004\b\n\u0010\u000bB%\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0004\b\n\u0010\fJ\b\u0010\u0002\u001a\u00020\tH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/storage/CancellableSimpleLock;", "Lorg/jetbrains/kotlin/storage/DefaultSimpleLock;", "lock", "Ljava/util/concurrent/locks/Lock;", "checkCancelled", "Ljava/lang/Runnable;", "interruptedExceptionHandler", "Lkotlin/Function1;", "Ljava/lang/InterruptedException;", HttpUrl.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/util/concurrent/locks/Lock;Ljava/lang/Runnable;Lkotlin/jvm/functions/Function1;)V", "(Ljava/lang/Runnable;Lkotlin/jvm/functions/Function1;)V", "org.jetbrains.kotlin:util.runtime"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CancellableSimpleLock extends DefaultSimpleLock {
    private final Runnable checkCancelled;
    private final Function1<InterruptedException, Unit> interruptedExceptionHandler;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public CancellableSimpleLock(Lock lock, Runnable runnable, Function1<? super InterruptedException, Unit> function1) {
        super(lock);
        lock.getClass();
        runnable.getClass();
        function1.getClass();
        this.checkCancelled = runnable;
        this.interruptedExceptionHandler = function1;
    }

    @Override // org.jetbrains.kotlin.storage.DefaultSimpleLock
    public void lock() {
        while (!getLock().tryLock(50L, TimeUnit.MILLISECONDS)) {
            try {
                this.checkCancelled.run();
            } catch (InterruptedException e) {
                this.interruptedExceptionHandler.invoke(e);
                return;
            }
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public CancellableSimpleLock(Runnable runnable, Function1<? super InterruptedException, Unit> function1) {
        this(new ReentrantLock(), runnable, function1);
        runnable.getClass();
        function1.getClass();
    }
}
