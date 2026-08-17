package com.android.tools.r8.internal;

import java.io.Serializable;
import java.util.Collection;

/* JADX INFO: renamed from: com.android.tools.r8.internal.zU, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3197zU implements InterfaceC3028xU, Serializable {
    public final InterfaceC3028xU b;

    public C3197zU(C2685tU c2685tU) {
        this.b = c2685tU;
    }

    @Override // java.util.Collection
    public final boolean add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public final boolean addAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public final boolean contains(Object obj) {
        return this.b.contains(obj);
    }

    @Override // java.util.Collection
    public final boolean containsAll(Collection collection) {
        return this.b.containsAll(collection);
    }

    @Override // java.util.Collection
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return this.b.equals(obj);
    }

    @Override // java.util.Collection
    public final int hashCode() {
        return this.b.hashCode();
    }

    @Override // java.util.Collection
    public final boolean isEmpty() {
        return this.b.isEmpty();
    }

    @Override // java.util.Collection, java.lang.Iterable
    public final BU iterator() {
        BU it = this.b.iterator();
        CU cu = FU.a;
        return new EU(it);
    }

    @Override // java.util.Collection
    public final boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public final boolean removeAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public final boolean retainAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public final int size() {
        return this.b.size();
    }

    @Override // java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        return this.b.toArray(objArr);
    }

    public final String toString() {
        return this.b.toString();
    }

    @Override // java.util.Collection
    public final Object[] toArray() {
        return this.b.toArray();
    }
}
