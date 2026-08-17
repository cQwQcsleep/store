package com.android.tools.r8.internal;

import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Z20 extends V {
    public final /* synthetic */ C1627h30 b;

    public Z20(C1627h30 c1627h30) {
        this.b = c1627h30;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final void clear() {
        this.b.clear();
    }

    @Override // com.android.tools.r8.internal.InterfaceC1215cA
    public final boolean f(int i) {
        return this.b.d(i);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC1215cA, java.util.Set
    public final InterfaceC1640hA iterator() {
        return new C1542g30(this.b);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final int size() {
        return this.b.l;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return new C1542g30(this.b);
    }
}
