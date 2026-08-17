package com.intellij.util.concurrency;

import com.intellij.concurrency.ThreadContext;
import com.intellij.openapi.application.AccessToken;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
final class ContextRunnable implements Runnable {
    private final ChildContext myContext;
    private final Runnable myRunnable;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        if (i != 1) {
            objArr[0] = "context";
        } else {
            objArr[0] = "runnable";
        }
        objArr[1] = "com/intellij/util/concurrency/ContextRunnable";
        objArr[2] = "<init>";
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    public ContextRunnable(ChildContext childContext, Runnable runnable) {
        if (childContext == null) {
            $$$reportNull$$$0(0);
        }
        if (runnable == null) {
            $$$reportNull$$$0(1);
        }
        if (runnable instanceof ContextRunnable) {
            w01.a("Can not wrap ContextRunnable into ContextRunnable");
            throw null;
        }
        this.myContext = childContext;
        this.myRunnable = runnable;
    }

    public Runnable getDelegate() {
        return this.myRunnable;
    }

    @Override // java.lang.Runnable
    public void run() {
        AccessToken accessTokenResetThreadContext = ThreadContext.resetThreadContext();
        try {
            this.myContext.runInChildContext(this.myRunnable);
            if (accessTokenResetThreadContext != null) {
                accessTokenResetThreadContext.close();
            }
        } catch (Throwable th) {
            if (accessTokenResetThreadContext != null) {
                try {
                    accessTokenResetThreadContext.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public String toString() {
        return this.myRunnable.toString();
    }
}
