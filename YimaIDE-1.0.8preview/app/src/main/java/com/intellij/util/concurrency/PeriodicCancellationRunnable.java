package com.intellij.util.concurrency;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlinx.coroutines.JobKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0001¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\b\u001a\u00020\u0004H\u0016R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/intellij/util/concurrency/PeriodicCancellationRunnable;", "Ljava/lang/Runnable;", "continuation", "Lkotlin/coroutines/Continuation;", "", "runnable", "<init>", "(Lkotlin/coroutines/Continuation;Ljava/lang/Runnable;)V", "run", "intellij.platform.util"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class PeriodicCancellationRunnable implements Runnable {
    private final Continuation<Unit> continuation;
    private final Runnable runnable;

    public PeriodicCancellationRunnable(Continuation<? super Unit> continuation, Runnable runnable) {
        continuation.getClass();
        runnable.getClass();
        this.continuation = continuation;
        this.runnable = runnable;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            Propagation.runAsCoroutine(this.continuation, false, new AnonymousClass1(this.runnable));
        } catch (CancellationException e) {
            if (!JobKt.getJob(this.continuation.getContext()).isCancelled()) {
                throw e;
            }
        }
    }

    /* JADX INFO: renamed from: com.intellij.util.concurrency.PeriodicCancellationRunnable$run$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function0<Unit> {
        public AnonymousClass1(Object obj) {
            super(0, obj, Runnable.class, "run", "run()V", 0);
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m1874invoke() {
            ((Runnable) ((CallableReference) this).receiver).run();
        }

        public /* bridge */ /* synthetic */ Object invoke() {
            m1874invoke();
            return Unit.INSTANCE;
        }
    }
}
