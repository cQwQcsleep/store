package com.android.tools.r8.internal;

import java.util.Comparator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class N extends AbstractC1196c0 {
    public final /* synthetic */ O b;

    public N(O o) {
        this.b = o;
    }

    @Override // com.android.tools.r8.internal.CA
    public final CA b(int i) {
        return this.b.b(i).keySet();
    }

    @Override // com.android.tools.r8.internal.CA
    public final CA c(int i) {
        return this.b.c(i).keySet();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        this.b.clear();
    }

    @Override // java.util.SortedSet
    public final Comparator comparator() {
        return this.b.comparator();
    }

    @Override // com.android.tools.r8.internal.CA
    public final CA d(int i, int i2) {
        return this.b.a(i, i2).keySet();
    }

    @Override // com.android.tools.r8.internal.InterfaceC1215cA
    public final boolean f(int i) {
        return this.b.a(i);
    }

    @Override // com.android.tools.r8.internal.CA
    public final int g0() {
        return this.b.a();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.b.size();
    }

    @Override // com.android.tools.r8.internal.CA
    public final int t() {
        return this.b.d();
    }
}
