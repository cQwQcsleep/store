package com.android.tools.r8.internal;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Hk0 extends AbstractList implements InterfaceC3186zJ, RandomAccess {
    public final InterfaceC3186zJ b;

    public Hk0(InterfaceC3186zJ interfaceC3186zJ) {
        this.b = interfaceC3186zJ;
    }

    @Override // com.android.tools.r8.internal.InterfaceC3186zJ
    public final void a(CL cl) {
        throw new UnsupportedOperationException();
    }

    @Override // com.android.tools.r8.internal.InterfaceC3186zJ
    public final Hk0 f() {
        return this;
    }

    @Override // com.android.tools.r8.internal.InterfaceC3186zJ
    public final T7 g(int i) {
        return this.b.g(i);
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i) {
        return (String) this.b.get(i);
    }

    @Override // com.android.tools.r8.internal.InterfaceC3186zJ
    public final List h() {
        return this.b.h();
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return new Fk0(this);
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator(int i) {
        return new Dk0(this, i);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.b.size();
    }
}
