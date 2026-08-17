package com.android.tools.r8.internal;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedSet;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class WT extends AbstractC1365e1 {
    public final /* synthetic */ C1318dU b;

    public WT(C1318dU c1318dU) {
        this.b = c1318dU;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        this.b.clear();
    }

    @Override // java.util.SortedSet
    public final Comparator comparator() {
        return this.b.j.i().comparator();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        ST stD = this.b.j.d(entry.getKey());
        return stD != null && this.b.d(stD.b) && entry.equals(stD);
    }

    @Override // java.util.SortedSet
    public final Object first() {
        return this.b.j();
    }

    @Override // java.util.SortedSet
    public final SortedSet headSet(Object obj) {
        return this.b.headMap(((OT) obj).getKey()).i();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean isEmpty() {
        C1318dU c1318dU = this.b;
        ST st = c1318dU.j.e;
        return !(c1318dU.j() != null);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC3028xU, com.android.tools.r8.internal.JU, java.util.Set
    public final InterfaceC2942wU iterator() {
        return new ZT(this.b);
    }

    @Override // java.util.SortedSet
    public final Object last() {
        return this.b.k();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        ST stD = this.b.j.d(((Map.Entry) obj).getKey());
        if (stD != null && this.b.d(stD.b)) {
            this.b.remove(stD.b);
        }
        return stD != null;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        ZT zt = new ZT(this.b);
        int i = 0;
        while (zt.hasNext()) {
            i++;
            zt.next();
        }
        return i;
    }

    @Override // java.util.SortedSet
    public final SortedSet subSet(Object obj, Object obj2) {
        return this.b.subMap(((OT) obj).getKey(), ((OT) obj2).getKey()).i();
    }

    @Override // java.util.SortedSet
    public final SortedSet tailSet(Object obj) {
        return this.b.tailMap(((OT) obj).getKey()).i();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC3028xU, com.android.tools.r8.internal.JU, java.util.Set
    public final BU iterator() {
        return new ZT(this.b);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new ZT(this.b);
    }
}
