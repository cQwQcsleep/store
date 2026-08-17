package com.android.tools.r8.internal;

import java.util.SortedSet;

/* JADX INFO: renamed from: com.android.tools.r8.internal.c0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1196c0 extends AbstractC1111b0 implements CA {
    @Override // java.util.SortedSet
    public final Object first() {
        return Integer.valueOf(g0());
    }

    @Override // java.util.SortedSet
    public final SortedSet headSet(Object obj) {
        return c(((Integer) obj).intValue());
    }

    @Override // java.util.SortedSet
    public final Object last() {
        return Integer.valueOf(t());
    }

    @Override // java.util.SortedSet
    public final SortedSet subSet(Object obj, Object obj2) {
        return d(((Integer) obj).intValue(), ((Integer) obj2).intValue());
    }

    @Override // java.util.SortedSet
    public final SortedSet tailSet(Object obj) {
        return b(((Integer) obj).intValue());
    }
}
