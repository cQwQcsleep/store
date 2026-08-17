package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class W implements InterfaceC1640hA {
    @Override // java.util.Iterator
    public final Object next() {
        return Integer.valueOf(q());
    }

    @Override // java.util.Iterator, java.util.ListIterator
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
