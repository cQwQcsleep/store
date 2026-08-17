package com.android.tools.r8.internal;

import java.util.Comparator;
import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ez, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1447ez extends AbstractC1196c0 {
    public final /* synthetic */ C1874jz b;

    public C1447ez(C1874jz c1874jz) {
        this.b = c1874jz;
    }

    @Override // com.android.tools.r8.internal.CA
    public final CA b(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // com.android.tools.r8.internal.CA
    public final CA c(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        this.b.clear();
    }

    @Override // java.util.SortedSet
    public final /* bridge */ /* synthetic */ Comparator comparator() {
        return null;
    }

    @Override // com.android.tools.r8.internal.CA
    public final CA d(int i, int i2) {
        throw new UnsupportedOperationException();
    }

    @Override // com.android.tools.r8.internal.InterfaceC1215cA
    public final boolean f(int i) {
        return this.b.a(i);
    }

    @Override // com.android.tools.r8.internal.CA
    public final int g0() {
        C1874jz c1874jz = this.b;
        if (c1874jz.l != 0) {
            return c1874jz.c[c1874jz.g];
        }
        z0e.a();
        return 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC1215cA, java.util.Set
    public final InterfaceC1045aA iterator() {
        return new C1361dz(this.b);
    }

    @Override // com.android.tools.r8.internal.AbstractC1111b0
    public final boolean k(int i) {
        C1874jz c1874jz = this.b;
        int i2 = c1874jz.l;
        c1874jz.remove(i);
        return this.b.l != i2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.b.l;
    }

    @Override // com.android.tools.r8.internal.CA
    public final int t() {
        C1874jz c1874jz = this.b;
        if (c1874jz.l != 0) {
            return c1874jz.c[c1874jz.h];
        }
        z0e.a();
        return 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC1215cA, java.util.Set
    public final InterfaceC1640hA iterator() {
        return new C1361dz(this.b);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new C1361dz(this.b);
    }
}
