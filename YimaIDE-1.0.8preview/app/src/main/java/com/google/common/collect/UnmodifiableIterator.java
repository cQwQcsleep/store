package com.google.common.collect;

import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
public abstract class UnmodifiableIterator<E> implements Iterator<E> {
    @Override // java.util.Iterator
    @Deprecated
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
