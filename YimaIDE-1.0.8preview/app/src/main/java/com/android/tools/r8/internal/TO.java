package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class TO extends SO {
    public TO(ConcurrentHashMap concurrentHashMap) {
        super(concurrentHashMap);
    }

    @Override // com.android.tools.r8.internal.SO
    public final Object a(com.android.tools.r8.graph.B5 b5) {
        return b5.getReference();
    }

    @Override // com.android.tools.r8.internal.SO
    public final com.android.tools.r8.graph.B2 b(Object obj) {
        C0322w2 c0322w2 = (C0322w2) obj;
        return AbstractC0507Gc.a(c0322w2, c0322w2);
    }

    @Override // com.android.tools.r8.internal.SO
    public /* bridge */ /* synthetic */ QO a(Object obj) {
        return super.a(obj);
    }
}
