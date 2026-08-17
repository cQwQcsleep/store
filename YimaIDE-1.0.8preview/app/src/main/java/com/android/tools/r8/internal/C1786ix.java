package com.android.tools.r8.internal;

import java.util.Comparator;
import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ix, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1786ix extends AbstractC1196c0 {
    public final /* synthetic */ C2214nx b;

    public C1786ix(C2214nx c2214nx) {
        this.b = c2214nx;
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
        C2214nx c2214nx = this.b;
        if (c2214nx.l != 0) {
            return c2214nx.c[c2214nx.g];
        }
        z0e.a();
        return 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC1215cA, java.util.Set
    public final InterfaceC1045aA iterator() {
        return new C1702hx(this.b);
    }

    @Override // com.android.tools.r8.internal.AbstractC1111b0
    public final boolean k(int i) {
        C2214nx c2214nx = this.b;
        int i2 = c2214nx.l;
        c2214nx.remove(i);
        return this.b.l != i2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.b.l;
    }

    @Override // com.android.tools.r8.internal.CA
    public final int t() {
        C2214nx c2214nx = this.b;
        if (c2214nx.l != 0) {
            return c2214nx.c[c2214nx.h];
        }
        z0e.a();
        return 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC1215cA, java.util.Set
    public final InterfaceC1640hA iterator() {
        return new C1702hx(this.b);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new C1702hx(this.b);
    }
}
