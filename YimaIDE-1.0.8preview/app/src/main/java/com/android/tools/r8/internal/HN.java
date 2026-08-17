package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class HN {
    public static FN a(C0333y c0333y, ExecutorService executorService) {
        boolean z = FN.h;
        AbstractC3148ys abstractC3148ysA = c0333y.A();
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        ConcurrentHashMap concurrentHashMap2 = new ConcurrentHashMap();
        new com.android.tools.r8.graph.L5(c0333y, new GN(ConcurrentHashMap.newKeySet(), new EN(c0333y, abstractC3148ysA, concurrentHashMap, concurrentHashMap2))).a(executorService);
        return new FN(concurrentHashMap, concurrentHashMap2, c0333y, abstractC3148ysA);
    }
}
