package com.android.tools.r8.internal;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class GO {
    public final ConcurrentHashMap a = new ConcurrentHashMap();

    public final FO a(com.android.tools.r8.graph.B5 b5, int i) {
        return (FO) this.a.computeIfAbsent(new FO(b5.getReference(), i), Function.identity());
    }
}
