package com.android.tools.r8.internal;

import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;

/* JADX INFO: renamed from: com.android.tools.r8.internal.gT, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1573gT extends AbstractC1365e1 {
    public final /* synthetic */ C2000lT b;

    public C1573gT(C2000lT c2000lT) {
        this.b = c2000lT;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        this.b.clear();
    }

    @Override // java.util.SortedSet
    public final Comparator comparator() {
        return null;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        return this.b.containsKey(obj);
    }

    @Override // java.util.SortedSet
    public final Object first() {
        C2000lT c2000lT = this.b;
        if (c2000lT.l != 0) {
            return c2000lT.c[c2000lT.g];
        }
        z0e.a();
        return null;
    }

    @Override // java.util.SortedSet
    public final SortedSet headSet(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC3028xU, com.android.tools.r8.internal.JU, java.util.Set
    public final InterfaceC2942wU iterator() {
        return new C1488fT(this.b);
    }

    @Override // java.util.SortedSet
    public final Object last() {
        C2000lT c2000lT = this.b;
        if (c2000lT.l != 0) {
            return c2000lT.c[c2000lT.h];
        }
        z0e.a();
        return null;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        C2000lT c2000lT = this.b;
        int i = c2000lT.l;
        c2000lT.c(obj);
        return this.b.l != i;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.b.l;
    }

    @Override // java.util.SortedSet
    public final SortedSet subSet(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.SortedSet
    public final SortedSet tailSet(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC3028xU, com.android.tools.r8.internal.JU, java.util.Set
    public final BU iterator() {
        return new C1488fT(this.b);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new C1488fT(this.b);
    }
}
