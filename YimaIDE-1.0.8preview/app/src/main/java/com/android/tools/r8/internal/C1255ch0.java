package com.android.tools.r8.internal;

import com.android.tools.r8.threading.ThreadingModule;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ch0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1255ch0 implements ThreadingModule {
    public static final /* synthetic */ boolean a = true;

    @Override // com.android.tools.r8.threading.ThreadingModule
    public final void awaitFutures(List list) {
        if (a) {
            return;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (!((Future) it.next()).isDone()) {
                x1f.a();
                return;
            }
        }
    }

    @Override // com.android.tools.r8.threading.ThreadingModule
    public final ExecutorService createSingleThreadedExecutorService() {
        return new sP();
    }

    @Override // com.android.tools.r8.threading.ThreadingModule
    public final ExecutorService createThreadedExecutorService(int i) {
        return createSingleThreadedExecutorService();
    }

    @Override // com.android.tools.r8.threading.ThreadingModule
    public final Future submit(Callable callable, ExecutorService executorService) throws ExecutionException {
        try {
            Object objCall = callable.call();
            return objCall == null ? C2638su.c : new C2638su(objCall);
        } catch (Exception e) {
            throw new ExecutionException(e);
        }
    }
}
