package com.android.tools.r8.internal;

import com.android.tools.r8.graph.E0;
import java.util.IdentityHashMap;
import java.util.LinkedHashSet;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class ZX {
    public final IdentityHashMap a = new IdentityHashMap();

    public final C0775Ql a(com.android.tools.r8.graph.E0 e0, com.android.tools.r8.graph.E0 e1) {
        C0775Ql c0775Ql = new C0775Ql(new LinkedHashSet());
        a(c0775Ql, e0);
        return c0775Ql;
    }

    public abstract void a(C0775Ql c0775Ql, com.android.tools.r8.graph.E0 e0);

    public final C0775Ql a(final com.android.tools.r8.graph.E0 e0) {
        return (C0775Ql) this.a.computeIfAbsent(e0, new Function() { // from class: w6g
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.a(e0, (E0) obj);
            }
        });
    }
}
