package it.unimi.dsi.fastutil.booleans;

import java.util.ListIterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface BooleanListIterator extends BooleanBidirectionalIterator, ListIterator<Boolean> {
    @Override // java.util.ListIterator
    @Deprecated
    default void add(Boolean bool) {
        add(bool.booleanValue());
    }

    @Override // java.util.Iterator, java.util.ListIterator
    default void remove() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator
    @Deprecated
    default void set(Boolean bool) {
        set(bool.booleanValue());
    }

    @Override // it.unimi.dsi.fastutil.booleans.BooleanIterator, java.util.Iterator
    @Deprecated
    default Boolean next() {
        return super.next();
    }

    @Override // it.unimi.dsi.fastutil.booleans.BooleanBidirectionalIterator, it.unimi.dsi.fastutil.BidirectionalIterator
    @Deprecated
    default Boolean previous() {
        return super.previous();
    }

    default void add(boolean z) {
        throw new UnsupportedOperationException();
    }

    default void set(boolean z) {
        throw new UnsupportedOperationException();
    }
}
