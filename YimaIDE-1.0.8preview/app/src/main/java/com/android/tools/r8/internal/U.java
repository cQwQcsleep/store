package com.android.tools.r8.internal;

import java.util.Set;
import java.util.SortedMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class U extends S implements InterfaceC0763Pz {
    @Override // com.android.tools.r8.internal.InterfaceC0763Pz
    /* JADX INFO: renamed from: a */
    public final InterfaceC0763Pz subMap(Integer num, Integer num2) {
        return a(num.intValue(), num2.intValue());
    }

    @Override // com.android.tools.r8.internal.InterfaceC0763Pz
    /* JADX INFO: renamed from: b */
    public final InterfaceC0763Pz headMap(Integer num) {
        return c(num.intValue());
    }

    public /* bridge */ /* synthetic */ JU c() {
        return c();
    }

    @Override // com.android.tools.r8.internal.S
    /* JADX INFO: renamed from: e */
    public final JU entrySet() {
        return c();
    }

    @Override // com.android.tools.r8.internal.S, java.util.Map
    public final NU entrySet() {
        return c();
    }

    @Override // com.android.tools.r8.internal.InterfaceC0763Pz, java.util.SortedMap
    public final Integer firstKey() {
        return Integer.valueOf(a());
    }

    @Override // java.util.SortedMap
    public final SortedMap headMap(Object obj) {
        return c(((Integer) obj).intValue());
    }

    public /* bridge */ /* synthetic */ InterfaceC3177zA keySet() {
        return keySet();
    }

    @Override // com.android.tools.r8.internal.InterfaceC0763Pz, java.util.SortedMap
    public final Integer lastKey() {
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

    @Override // com.android.tools.r8.internal.S, java.util.Map
    public final Set entrySet() {
        return c();
    }

    @Override // java.util.Map, java.util.SortedMap
    public /* bridge */ /* synthetic */ Set keySet() {
        return keySet();
    }

    @Override // java.util.SortedMap
    public final Object firstKey() {
        return Integer.valueOf(a());
    }

    @Override // java.util.SortedMap
    public final Object lastKey() {
        return Integer.valueOf(d());
    }

    @Override // com.android.tools.r8.internal.InterfaceC0763Pz
    /* JADX INFO: renamed from: a */
    public final InterfaceC0763Pz tailMap(Integer num) {
        return b(num.intValue());
    }
}
