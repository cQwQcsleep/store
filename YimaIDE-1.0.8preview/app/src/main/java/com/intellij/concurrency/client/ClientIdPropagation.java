package com.intellij.concurrency.client;

import com.intellij.concurrency.client.ClientIdPropagation;
import com.intellij.psi.impl.source.tree.ChildRole;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;
import java.util.function.Function;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u001a\u0010\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0003H\u0007\u001a\"\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00170\t\"\u0004\b\u0000\u0010\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00170\tH\u0007\u001a\"\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00170\u001a\"\u0004\b\u0000\u0010\u00172\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u0002H\u00170\u001aH\u0007\u001a\"\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\u00170\u001d\"\u0004\b\u0000\u0010\u00172\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u00170\u001dH\u0007\u001a4\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u0002H\u0017\u0012\u0004\u0012\u0002H 0\r\"\u0004\b\u0000\u0010\u0017\"\u0004\b\u0001\u0010 2\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u0002H\u0017\u0012\u0004\u0012\u0002H 0\rH\u0007\u001a4\u0010\"\u001a\u000e\u0012\u0004\u0012\u0002H\u0017\u0012\u0004\u0012\u0002H#0\u0011\"\u0004\b\u0000\u0010\u0017\"\u0004\b\u0001\u0010#2\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u0002H\u0017\u0012\u0004\u0012\u0002H#0\u0011H\u0007\".\u0010\u0000\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00020\u00018\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007\"6\u0010\b\u001a\u001c\u0012\u0018\u0012\u0016\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t0\u00020\u00018\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\n\u0010\u0005\u001a\u0004\b\u000b\u0010\u0007\">\u0010\f\u001a$\u0012 \u0012\u001e\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\r\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\r0\u00020\u00018\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u0005\u001a\u0004\b\u000f\u0010\u0007\">\u0010\u0010\u001a$\u0012 \u0012\u001e\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0011\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00110\u00020\u00018\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u0005\u001a\u0004\b\u0013\u0010\u0007¨\u0006%"}, d2 = {"captureClientIdInRunnableFun", "Ljava/util/concurrent/atomic/AtomicReference;", "Lkotlin/Function1;", "Ljava/lang/Runnable;", "getCaptureClientIdInRunnableFun$annotations", "()V", "getCaptureClientIdInRunnableFun", "()Ljava/util/concurrent/atomic/AtomicReference;", "captureClientIdInCallableFun", "Ljava/util/concurrent/Callable;", "getCaptureClientIdInCallableFun$annotations", "getCaptureClientIdInCallableFun", "captureClientIdInFunctionFun", "Ljava/util/function/Function;", "getCaptureClientIdInFunctionFun$annotations", "getCaptureClientIdInFunctionFun", "captureClientIdInBiConsumerFun", "Ljava/util/function/BiConsumer;", "getCaptureClientIdInBiConsumerFun$annotations", "getCaptureClientIdInBiConsumerFun", "captureClientIdInRunnable", "runnable", "captureClientIdInCallable", "T", "callable", "captureClientIdInProcessor", "Lcom/intellij/util/Processor;", "processor", "captureClientId", "Lkotlin/Function0;", "action", "captureClientIdInFunction", "R", "function", "captureClientIdInBiConsumer", "U", "biConsumer", "intellij.platform.util"}, k = 2, mv = {2, 0, 0}, xi = ChildRole.TRY_BLOCK)
public final class ClientIdPropagation {
    private static final AtomicReference<Function1<Runnable, Runnable>> captureClientIdInRunnableFun = new AtomicReference<>(new Function1() { // from class: v02
        public final Object invoke(Object obj) {
            return ClientIdPropagation.b((Runnable) obj);
        }
    });
    private static final AtomicReference<Function1<Callable<?>, Callable<?>>> captureClientIdInCallableFun = new AtomicReference<>(new Function1() { // from class: w02
        public final Object invoke(Object obj) {
            return ClientIdPropagation.a((Callable) obj);
        }
    });
    private static final AtomicReference<Function1<Function<?, ?>, Function<?, ?>>> captureClientIdInFunctionFun = new AtomicReference<>(new Function1() { // from class: x02
        public final Object invoke(Object obj) {
            return ClientIdPropagation.c((Function) obj);
        }
    });
    private static final AtomicReference<Function1<BiConsumer<?, ?>, BiConsumer<?, ?>>> captureClientIdInBiConsumerFun = new AtomicReference<>(new Function1() { // from class: y02
        public final Object invoke(Object obj) {
            return ClientIdPropagation.d((BiConsumer) obj);
        }
    });

    public static Callable a(Callable callable) {
        callable.getClass();
        return callable;
    }

    public static Runnable b(Runnable runnable) {
        runnable.getClass();
        return runnable;
    }

    public static Function c(Function function) {
        function.getClass();
        return function;
    }

    @Deprecated(message = "ClientId propagation is handled by context propagation. You don't need to do it manually. The method will be removed soon.")
    public static final <T> Callable<T> captureClientIdInCallable(Callable<T> callable) {
        callable.getClass();
        Object objInvoke = captureClientIdInCallableFun.get().invoke(callable);
        objInvoke.getClass();
        return (Callable) objInvoke;
    }

    @Deprecated(message = "ClientId propagation is handled by context propagation. You don't need to do it manually. The method will be removed soon.")
    public static final Runnable captureClientIdInRunnable(Runnable runnable) {
        runnable.getClass();
        return (Runnable) captureClientIdInRunnableFun.get().invoke(runnable);
    }

    public static BiConsumer d(BiConsumer biConsumer) {
        biConsumer.getClass();
        return biConsumer;
    }
}
