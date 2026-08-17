package com.intellij.util.concurrency;

import com.intellij.concurrency.ThreadContext;
import com.intellij.openapi.application.AccessToken;
import com.intellij.openapi.progress.ProcessCanceledException;
import com.intellij.util.ExceptionUtilRt;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
final class ContextCallable<V> implements Callable<V> {
    private final Callable<? extends V> myCallable;
    private final ChildContext myChildContext;
    private final boolean myRoot;
    private final AtomicBoolean myTracker;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        if (i == 1) {
            objArr[0] = "callable";
        } else if (i != 2) {
            objArr[0] = "context";
        } else {
            objArr[0] = "cancellationTracker";
        }
        objArr[1] = "com/intellij/util/concurrency/ContextCallable";
        objArr[2] = "<init>";
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    public ContextCallable(boolean z, ChildContext childContext, Callable<? extends V> callable, AtomicBoolean atomicBoolean) {
        if (childContext == null) {
            $$$reportNull$$$0(0);
        }
        if (callable == null) {
            $$$reportNull$$$0(1);
        }
        if (atomicBoolean == null) {
            $$$reportNull$$$0(2);
        }
        this.myRoot = z;
        this.myChildContext = childContext;
        this.myCallable = callable;
        this.myTracker = atomicBoolean;
    }

    public static /* synthetic */ RunResult a(ContextCallable contextCallable) {
        contextCallable.getClass();
        try {
            return new RunResult(contextCallable.myCallable.call());
        } catch (Exception e) {
            return new RunResult(e);
        }
    }

    public static /* synthetic */ RunResult b(ContextCallable contextCallable) {
        AccessToken accessTokenInstallThreadContext = ThreadContext.installThreadContext(contextCallable.myChildContext.getContext(), true);
        try {
            AccessToken accessTokenApplyContextActions = contextCallable.myChildContext.applyContextActions(false);
            try {
                try {
                    RunResult runResult = new RunResult(contextCallable.myCallable.call());
                    if (accessTokenApplyContextActions != null) {
                        accessTokenApplyContextActions.close();
                    }
                    if (accessTokenInstallThreadContext != null) {
                        accessTokenInstallThreadContext.close();
                    }
                    return runResult;
                } catch (Exception e) {
                    RunResult runResult2 = new RunResult(e);
                    if (accessTokenApplyContextActions != null) {
                        accessTokenApplyContextActions.close();
                    }
                    if (accessTokenInstallThreadContext != null) {
                        accessTokenInstallThreadContext.close();
                    }
                    return runResult2;
                }
            } catch (Throwable th) {
                if (accessTokenApplyContextActions != null) {
                    try {
                        accessTokenApplyContextActions.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        } catch (Throwable th3) {
            if (accessTokenInstallThreadContext != null) {
                try {
                    accessTokenInstallThreadContext.close();
                } catch (Throwable th4) {
                    th3.addSuppressed(th4);
                }
            }
            throw th3;
        }
    }

    @Override // java.util.concurrent.Callable
    public V call() throws Exception {
        RunResult runResult;
        if (this.myTracker.getAndSet(true)) {
            throw new ProcessCanceledException();
        }
        if (this.myRoot) {
            runResult = (RunResult) this.myChildContext.runInChildContext(true, new Function0() { // from class: com.intellij.util.concurrency.b
                public final Object invoke() {
                    return ContextCallable.a(this.b);
                }
            });
        } else {
            final Supplier supplier = new Supplier() { // from class: com.intellij.util.concurrency.c
                @Override // java.util.function.Supplier
                public final Object get() {
                    return ContextCallable.b(this.b);
                }
            };
            Continuation<Unit> continuation = this.myChildContext.getContinuation();
            runResult = continuation == null ? (RunResult) supplier.get() : (RunResult) Propagation.runAsCoroutine(continuation, true, new Function0() { // from class: com.intellij.util.concurrency.d
                public final Object invoke() {
                    return (ContextCallable.RunResult) supplier.get();
                }
            });
        }
        return (V) runResult.get();
    }

    public static class RunResult<V, E extends Exception> {
        boolean isSuccess = false;
        Object result;

        public RunResult(V v) {
            this.result = v;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public V get() throws Exception {
            boolean z = this.isSuccess;
            V v = (V) this.result;
            if (z) {
                return v;
            }
            throw ((Exception) ExceptionUtilRt.addRethrownStackAsSuppressed((Exception) v));
        }

        public RunResult(E e) {
            this.result = e;
        }
    }
}
