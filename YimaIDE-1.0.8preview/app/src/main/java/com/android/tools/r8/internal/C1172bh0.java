package com.android.tools.r8.internal;

import com.android.tools.r8.threading.ThreadingModule;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/* JADX INFO: renamed from: com.android.tools.r8.internal.bh0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1172bh0 implements ThreadingModule {
    @Override // com.android.tools.r8.threading.ThreadingModule
    public final void awaitFutures(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            try {
                try {
                    ((Future) it.next()).get();
                } catch (InterruptedException e) {
                    throw new RuntimeException("Interrupted while waiting for future.", e);
                }
            } catch (Throwable th) {
                while (it.hasNext()) {
                    ((Future) it.next()).get();
                }
                throw th;
            }
        }
        while (it.hasNext()) {
            ((Future) it.next()).get();
        }
    }

    @Override // com.android.tools.r8.threading.ThreadingModule
    public final ExecutorService createSingleThreadedExecutorService() {
        return Executors.newSingleThreadExecutor();
    }

    @Override // com.android.tools.r8.threading.ThreadingModule
    public final ExecutorService createThreadedExecutorService(int i) {
        return Executors.newWorkStealingPool(i);
    }

    @Override // com.android.tools.r8.threading.ThreadingModule
    public final Future submit(Callable callable, ExecutorService executorService) {
        return executorService.submit(callable);
    }
}
