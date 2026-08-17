package com.android.tools.r8.internal;

import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;

/* JADX INFO: renamed from: com.android.tools.r8.internal.c30, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1202c30 extends AbstractC3075y1 implements Z30 {
    public final /* synthetic */ C1627h30 b;

    public C1202c30(C1627h30 c1627h30) {
        this.b = c1627h30;
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
        C1627h30 c1627h30 = this.b;
        if (c1627h30.l != 0) {
            return c1627h30.c[c1627h30.g];
        }
        z0e.a();
        return null;
    }

    @Override // java.util.SortedSet
    public final SortedSet headSet(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.P30
    public final BU iterator() {
        return new C1118b30(this.b);
    }

    @Override // java.util.SortedSet
    public final Object last() {
        C1627h30 c1627h30 = this.b;
        if (c1627h30.l != 0) {
            return c1627h30.c[c1627h30.h];
        }
        z0e.a();
        return null;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        C1627h30 c1627h30 = this.b;
        int i = c1627h30.l;
        c1627h30.c(obj);
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

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new C1118b30(this.b);
    }
}
