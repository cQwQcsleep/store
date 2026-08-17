package com.android.tools.r8.internal;

import java.util.Comparator;
import java.util.SortedSet;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class V0 extends AbstractC1365e1 {
    public final /* synthetic */ W0 b;

    public V0(W0 w0) {
        this.b = w0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        this.b.clear();
    }

    @Override // java.util.SortedSet
    public final Comparator comparator() {
        this.b.comparator();
        return null;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        return this.b.containsKey(obj);
    }

    @Override // java.util.SortedSet
    public final Object first() {
        return this.b.firstKey();
    }

    @Override // java.util.SortedSet
    public final SortedSet headSet(Object obj) {
        return this.b.headMap(obj).keySet();
    }

    @Override // java.util.SortedSet
    public final Object last() {
        return this.b.lastKey();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.b.size();
    }

    @Override // java.util.SortedSet
    public final SortedSet subSet(Object obj, Object obj2) {
        return this.b.subMap(obj, obj2).keySet();
    }

    @Override // java.util.SortedSet
    public final SortedSet tailSet(Object obj) {
        return this.b.tailMap(obj).keySet();
    }
}
