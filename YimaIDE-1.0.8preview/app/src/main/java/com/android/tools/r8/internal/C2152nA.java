package com.android.tools.r8.internal;

import defpackage.qc6;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* JADX INFO: renamed from: com.android.tools.r8.internal.nA, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2152nA extends AbstractC1299dA implements InterfaceC1981lA, RandomAccess, Serializable, Cloneable {
    @Override // com.android.tools.r8.internal.InterfaceC1981lA
    public final void a(int i, int[] iArr, int i2, int i3) {
        if (i != 0 || i3 != 0 || i2 < 0 || i2 > iArr.length) {
            qc6.a();
        }
    }

    @Override // java.util.List
    public final void add(int i, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public final boolean addAll(int i, Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // com.android.tools.r8.internal.InterfaceC1981lA
    public final void b(int i, int i2) {
        throw new UnsupportedOperationException();
    }

    @Override // com.android.tools.r8.internal.InterfaceC1981lA
    public final int c(int i, int i2) {
        throw new UnsupportedOperationException();
    }

    public final Object clone() {
        return AbstractC2238oA.a;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        List list = (List) obj;
        return (list == this || list.isEmpty()) ? 0 : -1;
    }

    @Override // java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        return (obj instanceof List) && ((List) obj).isEmpty();
    }

    @Override // java.util.List
    public final Object get(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // com.android.tools.r8.internal.InterfaceC1981lA
    public final int h(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // com.android.tools.r8.internal.AbstractC1299dA, java.util.Collection
    public final int hashCode() {
        return 1;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1981lA
    public final int i(int i) {
        throw new IndexOutOfBoundsException();
    }

    @Override // java.util.List
    public final int indexOf(Object obj) {
        return -1;
    }

    @Override // com.android.tools.r8.internal.AbstractC1299dA, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC1215cA, java.util.Set
    public final InterfaceC1045aA iterator() {
        return AbstractC1895kA.a;
    }

    @Override // com.android.tools.r8.internal.V
    public final boolean j(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public final int lastIndexOf(Object obj) {
        return -1;
    }

    @Override // java.util.List
    public final ListIterator listIterator(int i) {
        if (i == 0) {
            return AbstractC1895kA.a;
        }
        jb9.a(String.valueOf(i));
        return null;
    }

    @Override // java.util.List
    public final Object remove(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public final Object set(int i, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public final List subList(int i, int i2) {
        if (i == 0 && i2 == 0) {
            return this;
        }
        qc6.a();
        return null;
    }

    @Override // com.android.tools.r8.internal.V, java.util.AbstractCollection
    public final String toString() {
        return "[]";
    }

    @Override // com.android.tools.r8.internal.AbstractC1299dA, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC1215cA, java.util.Set
    public final InterfaceC1640hA iterator() {
        return AbstractC1895kA.a;
    }

    @Override // com.android.tools.r8.internal.AbstractC1299dA, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC1215cA, java.util.Set
    public final InterfaceC2067mA iterator() {
        return AbstractC1895kA.a;
    }

    @Override // com.android.tools.r8.internal.AbstractC1299dA, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return AbstractC1895kA.a;
    }

    @Override // com.android.tools.r8.internal.V, java.util.AbstractCollection, java.util.Collection
    public final boolean add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // com.android.tools.r8.internal.V
    public final boolean add(Integer num) {
        throw new UnsupportedOperationException();
    }

    @Override // com.android.tools.r8.internal.InterfaceC1981lA
    public final void a(int i, int i2) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public final ListIterator listIterator() {
        return AbstractC1895kA.a;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1981lA, java.util.List
    public final InterfaceC2067mA listIterator() {
        return AbstractC1895kA.a;
    }
}
