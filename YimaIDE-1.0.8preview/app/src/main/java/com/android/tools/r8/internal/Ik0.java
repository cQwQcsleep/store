package com.android.tools.r8.internal;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Ik0 extends AbstractList implements AJ, RandomAccess {
    public final AJ b;

    public Ik0(AJ aj) {
        this.b = aj;
    }

    @Override // com.android.tools.r8.internal.AJ
    public final void a(U7 u7) {
        throw new UnsupportedOperationException();
    }

    @Override // com.android.tools.r8.internal.AJ
    public final Object e(int i) {
        return this.b.e(i);
    }

    @Override // com.android.tools.r8.internal.AJ
    public final AJ f() {
        return this;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i) {
        return (String) this.b.get(i);
    }

    @Override // com.android.tools.r8.internal.AJ
    public final List h() {
        return this.b.h();
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return new Gk0(this);
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator(int i) {
        return new Ek0(this, i);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.b.size();
    }
}
