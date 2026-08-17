package com.android.tools.r8.internal;

import java.util.Set;
import java.util.SortedMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class O extends M implements InterfaceC0425Cy {
    public /* bridge */ /* synthetic */ JU b() {
        return b();
    }

    @Override // com.android.tools.r8.internal.M
    /* JADX INFO: renamed from: e */
    public final JU entrySet() {
        return b();
    }

    @Override // com.android.tools.r8.internal.M, java.util.Map
    public final Set entrySet() {
        return b();
    }

    @Override // java.util.SortedMap
    public final Object firstKey() {
        return Integer.valueOf(a());
    }

    @Override // java.util.SortedMap
    public final SortedMap headMap(Object obj) {
        return c(((Integer) obj).intValue());
    }

    public /* bridge */ /* synthetic */ Set keySet() {
        return keySet();
    }

    @Override // java.util.SortedMap
    public final Object lastKey() {
        return Integer.valueOf(d());
    }

    @Override // java.util.SortedMap
    public final SortedMap subMap(Object obj, Object obj2) {
        return a(((Integer) obj).intValue(), ((Integer) obj2).intValue());
    }

    @Override // java.util.SortedMap
    public final SortedMap tailMap(Object obj) {
        return b(((Integer) obj).intValue());
    }
}
