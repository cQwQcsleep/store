package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0346z5;
import com.android.tools.r8.internal.C0650Lp;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Op, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0727Op {
    public final ConcurrentHashMap a = new ConcurrentHashMap();

    public final C0650Lp a(C0346z5 c0346z5) {
        return (C0650Lp) this.a.computeIfAbsent(c0346z5.getReference(), new Function() { // from class: kpa
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return new C0650Lp((C0245l1) obj);
            }
        });
    }
}
