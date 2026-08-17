package com.android.tools.r8.internal;

import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.lA, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface InterfaceC1981lA extends List, Comparable, InterfaceC1215cA {
    void a(int i, int i2);

    void a(int i, int[] iArr, int i2, int i3);

    @Override // com.android.tools.r8.internal.InterfaceC1215cA
    boolean add(int i);

    void b(int i, int i2);

    int c(int i, int i2);

    int h(int i);

    int i(int i);

    @Override // java.util.List, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC1215cA, java.util.Set
    InterfaceC2067mA iterator();

    @Override // java.util.List
    InterfaceC2067mA listIterator();
}
