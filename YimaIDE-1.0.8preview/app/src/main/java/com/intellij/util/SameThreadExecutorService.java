package com.intellij.util;

import defpackage.iv3;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class SameThreadExecutorService extends AbstractExecutorService implements AutoCloseable {
    private volatile boolean isTerminated;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = i != 1 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i != 1 ? 3 : 2];
        if (i == 1) {
            objArr[0] = "com/intellij/util/SameThreadExecutorService";
        } else if (i != 2) {
            objArr[0] = "theUnit";
        } else {
            objArr[0] = "command";
        }
        if (i != 1) {
            objArr[1] = "com/intellij/util/SameThreadExecutorService";
        } else {
            objArr[1] = "shutdownNow";
        }
        if (i != 1) {
            if (i != 2) {
                objArr[2] = "awaitTermination";
            } else {
                objArr[2] = "execute";
            }
        }
        String str2 = String.format(str, objArr);
        if (i == 1) {
            throw new IllegalStateException(str2);
        }
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean awaitTermination(long j, TimeUnit timeUnit) {
        if (timeUnit == null) {
            $$$reportNull$$$0(0);
        }
        if (isShutdown()) {
            return true;
        }
        k2d.a("Must call shutdown*() before awaitTermination()");
        return false;
    }

    @Override // java.lang.AutoCloseable
    public /* synthetic */ void close() {
        iv3.a(this);
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        if (runnable == null) {
            $$$reportNull$$$0(2);
        }
        if (isShutdown()) {
            k2d.a("Must not call execute() after pool is shut down");
        } else {
            runnable.run();
        }
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isShutdown() {
        return this.isTerminated;
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isTerminated() {
        return this.isTerminated;
    }

    @Override // java.util.concurrent.ExecutorService
    public void shutdown() {
        this.isTerminated = true;
    }

    @Override // java.util.concurrent.ExecutorService
    public List<Runnable> shutdownNow() {
        shutdown();
        List<Runnable> list = Collections.EMPTY_LIST;
        if (list == null) {
            $$$reportNull$$$0(1);
        }
        return list;
    }
}
