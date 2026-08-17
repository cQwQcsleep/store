package com.android.tools.r8.internal;

import java.util.SortedMap;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Pz, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface InterfaceC0763Pz extends InterfaceC2045lz, SortedMap {
    int a();

    InterfaceC0763Pz a(int i, int i2);

    /* JADX INFO: renamed from: a */
    InterfaceC0763Pz tailMap(Integer num);

    /* JADX INFO: renamed from: a */
    InterfaceC0763Pz subMap(Integer num, Integer num2);

    InterfaceC0763Pz b(int i);

    /* JADX INFO: renamed from: b */
    InterfaceC0763Pz headMap(Integer num);

    @Override // com.android.tools.r8.internal.InterfaceC2045lz
    NU c();

    InterfaceC0763Pz c(int i);

    @Override // java.util.SortedMap
    AbstractC3239zx comparator();

    int d();

    @Override // com.android.tools.r8.internal.InterfaceC0763Pz, java.util.SortedMap
    NU entrySet();

    @Override // java.util.SortedMap
    Integer firstKey();

    @Override // com.android.tools.r8.internal.InterfaceC0763Pz, java.util.SortedMap
    CA keySet();

    @Override // java.util.SortedMap
    Integer lastKey();

    @Override // com.android.tools.r8.internal.InterfaceC0763Pz, java.util.SortedMap
    P30 values();
}
