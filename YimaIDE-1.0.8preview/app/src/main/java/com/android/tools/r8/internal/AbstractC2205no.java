package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;
import java.util.function.BiPredicate;

/* JADX INFO: renamed from: com.android.tools.r8.internal.no, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2205no implements BiPredicate {
    public final int a(C0322w2 c0322w2) {
        if (c0322w2 == null) {
            return 0;
        }
        return a((Object) c0322w2);
    }

    public abstract int a(Object obj);

    public abstract boolean a(Object obj, Object obj2);

    public final boolean b(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj == null || obj2 == null) {
            return false;
        }
        return a(obj, obj2);
    }

    @Override // java.util.function.BiPredicate
    public final boolean test(Object obj, Object obj2) {
        return b(obj, obj2);
    }

    public final C2119mo b(C0322w2 c0322w2) {
        return new C2119mo(this, c0322w2);
    }
}
