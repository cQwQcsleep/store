package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
abstract class TransformedIterator<F, T> implements Iterator<T> {
    final Iterator<? extends F> backingIterator;

    public TransformedIterator(Iterator<? extends F> it2) {
        this.backingIterator = (Iterator) Preconditions.checkNotNull(it2);
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.backingIterator.hasNext();
    }

    @Override // java.util.Iterator
    public final T next() {
        return transform(this.backingIterator.next());
    }

    @Override // java.util.Iterator
    public final void remove() {
        this.backingIterator.remove();
    }

    public abstract T transform(F f);
}
