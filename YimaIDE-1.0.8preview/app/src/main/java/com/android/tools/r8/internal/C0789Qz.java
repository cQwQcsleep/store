package com.android.tools.r8.internal;

import java.util.Comparator;
import java.util.NoSuchElementException;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Qz, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0789Qz extends C2130mz implements InterfaceC0763Pz {
    @Override // java.util.SortedMap
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public InterfaceC0763Pz subMap(Integer num, Integer num2) {
        return a(num.intValue(), num2.intValue());
    }

    @Override // java.util.SortedMap
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public InterfaceC0763Pz headMap(Integer num) {
        return c(num.intValue());
    }

    @Override // com.android.tools.r8.internal.InterfaceC0763Pz, java.util.SortedMap
    public final AbstractC3239zx comparator() {
        return null;
    }

    public int d() {
        throw new NoSuchElementException();
    }

    @Override // java.util.SortedMap
    public Integer firstKey() {
        return Integer.valueOf(a());
    }

    @Override // java.util.SortedMap
    public Integer lastKey() {
        return Integer.valueOf(d());
    }

    @Override // java.util.SortedMap
    public final /* bridge */ /* synthetic */ Comparator comparator() {
        return null;
    }

    @Override // com.android.tools.r8.internal.C2130mz, com.android.tools.r8.internal.InterfaceC2045lz
    public NU c() {
        return PU.a;
    }

    @Override // com.android.tools.r8.internal.C2130mz, java.util.Map, com.android.tools.r8.internal.InterfaceC0763Pz, java.util.SortedMap
    public NU entrySet() {
        return PU.a;
    }

    public InterfaceC0763Pz c(int i) {
        return AbstractC0815Rz.a;
    }

    @Override // com.android.tools.r8.internal.C2130mz, java.util.Map, java.util.SortedMap
    public CA keySet() {
        return EA.a;
    }

    public InterfaceC0763Pz b(int i) {
        return AbstractC0815Rz.a;
    }

    public int a() {
        throw new NoSuchElementException();
    }

    @Override // java.util.SortedMap
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public InterfaceC0763Pz tailMap(Integer num) {
        return b(num.intValue());
    }

    public InterfaceC0763Pz a(int i, int i2) {
        return AbstractC0815Rz.a;
    }
}
