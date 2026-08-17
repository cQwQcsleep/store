package com.android.tools.r8.internal;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedSet;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class QT extends AbstractC1365e1 {
    public final PT b = new PT(this);
    public final /* synthetic */ C1574gU c;

    public QT(C1574gU c1574gU) {
        this.c = c1574gU;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        this.c.clear();
    }

    @Override // java.util.SortedSet
    public final Comparator comparator() {
        return this.b;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return entry.equals(this.c.d(entry.getKey()));
    }

    @Override // java.util.SortedSet
    public final Object first() {
        return this.c.e;
    }

    @Override // java.util.SortedSet
    public final SortedSet headSet(Object obj) {
        return this.c.headMap(((OT) obj).getKey()).i();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC3028xU, com.android.tools.r8.internal.JU, java.util.Set
    public final InterfaceC2942wU iterator() {
        return new TT(this.c);
    }

    @Override // java.util.SortedSet
    public final Object last() {
        return this.c.f;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        ST stD = this.c.d(((Map.Entry) obj).getKey());
        if (stD != null) {
            this.c.remove(stD.b);
        }
        return stD != null;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.c.d;
    }

    @Override // java.util.SortedSet
    public final SortedSet subSet(Object obj, Object obj2) {
        C1574gU c1574gU = this.c;
        Object key = ((OT) obj).getKey();
        Object key2 = ((OT) obj2).getKey();
        c1574gU.getClass();
        return new C1318dU(c1574gU, key, false, key2, false).i();
    }

    @Override // java.util.SortedSet
    public final SortedSet tailSet(Object obj) {
        return this.c.tailMap(((OT) obj).getKey()).i();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC3028xU, com.android.tools.r8.internal.JU, java.util.Set
    public final BU iterator() {
        return new TT(this.c);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new TT(this.c);
    }
}
