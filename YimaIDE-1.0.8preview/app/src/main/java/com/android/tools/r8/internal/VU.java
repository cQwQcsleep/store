package com.android.tools.r8.internal;

import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class VU {
    public static boolean a(com.android.tools.r8.graph.E0 e0, Predicate predicate, boolean z) {
        return e0 != null ? predicate.test(e0) : z;
    }

    public static boolean a(Object obj, Object obj2) {
        return obj == obj2;
    }

    public static boolean a(AbstractC2624sj0 abstractC2624sj0, Object obj) {
        return !a((Object) abstractC2624sj0, obj);
    }
}
