package com.android.tools.r8.internal;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* JADX INFO: renamed from: com.android.tools.r8.internal.zL, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C3188zL extends AbstractList {
    public final List b;

    public C3188zL(List list) {
        list.getClass();
        this.b = list;
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i, Object obj) {
        List list = this.b;
        int size = list.size();
        DX.b(i, size);
        list.add(size - i, obj);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        this.b.clear();
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i) {
        List list = this.b;
        int size = list.size();
        DX.a(i, size);
        return list.get((size - 1) - i);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return listIterator();
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator(int i) {
        int size = this.b.size();
        DX.b(i, size);
        return new C3103yL(this, this.b.listIterator(size - i));
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object remove(int i) {
        List list = this.b;
        int size = list.size();
        DX.a(i, size);
        return list.remove((size - 1) - i);
    }

    @Override // java.util.AbstractList
    public final void removeRange(int i, int i2) {
        subList(i, i2).clear();
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i, Object obj) {
        List list = this.b;
        int size = list.size();
        DX.a(i, size);
        return list.set((size - 1) - i, obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.b.size();
    }

    @Override // java.util.AbstractList, java.util.List
    public final List subList(int i, int i2) {
        DX.a(i, i2, this.b.size());
        List list = this.b;
        int size = list.size();
        DX.b(i2, size);
        int size2 = this.b.size();
        DX.b(i, size2);
        List listSubList = list.subList(size - i2, size2 - i);
        if (listSubList instanceof AbstractC0551Hu) {
            return ((AbstractC0551Hu) listSubList).j();
        }
        if (listSubList instanceof C3188zL) {
            return ((C3188zL) listSubList).b;
        }
        return listSubList instanceof RandomAccess ? new C3019xL(listSubList) : new C3188zL(listSubList);
    }
}
