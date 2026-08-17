package com.intellij.util.concurrency;

import androidx.collection.ScatterMapKt;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class AppExecutorUtil {
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        switch (i) {
            case 2:
            case 3:
            case 4:
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
            case 9:
            case 11:
            case 12:
            case 13:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
            case 10:
            default:
                str = "@NotNull method %s.%s must not return null";
                break;
        }
        switch (i) {
            case 2:
            case 3:
            case 4:
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
            case 9:
            case 11:
            case 12:
            case 13:
                i2 = 3;
                break;
            case 10:
            default:
                i2 = 2;
                break;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 2:
            case 3:
            case 4:
            case 5:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 11:
                objArr[0] = "name";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case 8:
            case 12:
                objArr[0] = "backendExecutor";
                break;
            case 9:
                objArr[0] = "parentDisposable";
                break;
            case 10:
            default:
                objArr[0] = "com/intellij/util/concurrency/AppExecutorUtil";
                break;
            case 13:
                objArr[0] = "comparator";
                break;
        }
        switch (i) {
            case 1:
                objArr[1] = "getAppExecutorService";
                break;
            case 2:
            case 3:
            case 4:
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
            case 9:
            case 11:
            case 12:
            case 13:
                objArr[1] = "com/intellij/util/concurrency/AppExecutorUtil";
                break;
            case 10:
                objArr[1] = "createBoundedApplicationPoolExecutor";
                break;
            default:
                objArr[1] = "getAppScheduledExecutorService";
                break;
        }
        switch (i) {
            case 2:
                objArr[2] = "createBoundedScheduledExecutorService";
                break;
            case 3:
            case 4:
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
            case 9:
                objArr[2] = "createBoundedApplicationPoolExecutor";
                break;
            case 11:
            case 12:
            case 13:
                objArr[2] = "createCustomPriorityQueueBoundedApplicationPoolExecutor";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i) {
            case 2:
            case 3:
            case 4:
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
            case 9:
            case 11:
            case 12:
            case 13:
                throw new IllegalArgumentException(str2);
            case 10:
            default:
                throw new IllegalStateException(str2);
        }
    }

    public static ExecutorService createBoundedApplicationPoolExecutor(String str, Executor executor, int i) {
        if (str == null) {
            $$$reportNull$$$0(5);
        }
        if (executor == null) {
            $$$reportNull$$$0(6);
        }
        return new BoundedTaskExecutor(str, executor, i, true);
    }

    public static ExecutorService getAppExecutorService() {
        ExecutorService executorService = ((AppScheduledExecutorService) getAppScheduledExecutorService()).backendExecutorService;
        if (executorService == null) {
            $$$reportNull$$$0(1);
        }
        return executorService;
    }

    public static ScheduledExecutorService getAppScheduledExecutorService() {
        ScheduledExecutorService appScheduledExecutorService = AppScheduledExecutorService.getInstance();
        if (appScheduledExecutorService == null) {
            $$$reportNull$$$0(0);
        }
        return appScheduledExecutorService;
    }

    public static boolean propagateContext() {
        return Propagation.isPropagateThreadContext();
    }
}
