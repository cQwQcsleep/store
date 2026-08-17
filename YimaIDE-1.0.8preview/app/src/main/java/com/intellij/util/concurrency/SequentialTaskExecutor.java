package com.intellij.util.concurrency;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class SequentialTaskExecutor {
    /* JADX WARN: Code duplicated, block: B:18:0x0029  */
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 1 || i == 4) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 1 || i == 4) ? 2 : 3];
        if (i == 1) {
            objArr[0] = "com/intellij/util/concurrency/SequentialTaskExecutor";
        } else if (i == 3) {
            objArr[0] = "executor";
        } else if (i != 4) {
            objArr[0] = "name";
        } else {
            objArr[0] = "com/intellij/util/concurrency/SequentialTaskExecutor";
        }
        if (i == 1 || i == 4) {
            objArr[1] = "createSequentialApplicationPoolExecutor";
        } else {
            objArr[1] = "com/intellij/util/concurrency/SequentialTaskExecutor";
        }
        if (i != 1 && i != 4) {
            objArr[2] = "createSequentialApplicationPoolExecutor";
        }
        String str2 = String.format(str, objArr);
        if (i != 1 && i != 4) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public static ExecutorService createSequentialApplicationPoolExecutor(String str, Executor executor) {
        if (str == null) {
            $$$reportNull$$$0(2);
        }
        if (executor == null) {
            $$$reportNull$$$0(3);
        }
        ExecutorService executorServiceCreateBoundedApplicationPoolExecutor = AppExecutorUtil.createBoundedApplicationPoolExecutor(str, executor, 1);
        if (executorServiceCreateBoundedApplicationPoolExecutor == null) {
            $$$reportNull$$$0(4);
        }
        return executorServiceCreateBoundedApplicationPoolExecutor;
    }
}
