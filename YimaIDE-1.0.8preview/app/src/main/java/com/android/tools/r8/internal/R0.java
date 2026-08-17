package com.android.tools.r8.internal;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class R0 extends AbstractCollection implements InterfaceC1231cQ {
    public transient Set b;
    public transient Set c;

    @Override // com.android.tools.r8.internal.InterfaceC1231cQ
    public Set F() {
        Set set = this.b;
        if (set != null) {
            return set;
        }
        Set setA = a();
        this.b = setA;
        return setA;
    }

    public int a(Object obj) {
        AbstractC0871Ud.a(0, "count");
        int iB = b(obj);
        int i = 0 - iB;
        if (i > 0) {
            a(obj, i);
            return iB;
        }
        if (i < 0) {
            b(-i, obj);
        }
        return iB;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean add(Object obj) {
        a(obj, 1);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean addAll(Collection collection) {
        collection.getClass();
        if (collection instanceof InterfaceC1231cQ) {
            return AbstractC1656hQ.a((InterfaceC1231cQ) this, (InterfaceC1231cQ) collection);
        }
        if (collection.isEmpty()) {
            return false;
        }
        return NC.a(this, collection.iterator());
    }

    public Set b() {
        return new Q0(this);
    }

    public abstract int c();

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean contains(Object obj) {
        return b(obj) > 0;
    }

    public abstract Iterator d();

    public abstract Iterator e();

    @Override // com.android.tools.r8.internal.InterfaceC1231cQ
    public Set entrySet() {
        Set set = this.c;
        if (set != null) {
            return set;
        }
        Set setB = b();
        this.c = setB;
        return setB;
    }

    @Override // java.util.Collection, com.android.tools.r8.internal.InterfaceC1231cQ
    public final boolean equals(Object obj) {
        return AbstractC1656hQ.a(this, obj);
    }

    @Override // java.util.Collection, com.android.tools.r8.internal.InterfaceC1231cQ
    public final int hashCode() {
        return entrySet().hashCode();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return entrySet().isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean remove(Object obj) {
        return b(1, obj) > 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean removeAll(Collection collection) {
        if (collection instanceof InterfaceC1231cQ) {
            collection = ((InterfaceC1231cQ) collection).F();
        }
        return F().removeAll(collection);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean retainAll(Collection collection) {
        collection.getClass();
        if (collection instanceof InterfaceC1231cQ) {
            collection = ((InterfaceC1231cQ) collection).F();
        }
        return F().retainAll(collection);
    }

    @Override // java.util.AbstractCollection
    public final String toString() {
        return entrySet().toString();
    }

    public Set a() {
        return new P0(this);
    }

    public int a(Object obj, int i) {
        throw new UnsupportedOperationException();
    }

    @Override // com.android.tools.r8.internal.InterfaceC1231cQ
    public boolean a(int i, Object obj) {
        AbstractC0871Ud.a(i, "oldCount");
        AbstractC0871Ud.a(0, "newCount");
        if (b(obj) != i) {
            return false;
        }
        a(obj);
        return true;
    }
}
