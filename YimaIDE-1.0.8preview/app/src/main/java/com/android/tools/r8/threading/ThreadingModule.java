package com.android.tools.r8.threading;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface ThreadingModule {
    <T> void awaitFutures(List<Future<T>> list) throws ExecutionException;

    ExecutorService createSingleThreadedExecutorService();

    ExecutorService createThreadedExecutorService(int i);

    <T> Future<T> submit(Callable<T> callable, ExecutorService executorService) throws ExecutionException;
}
