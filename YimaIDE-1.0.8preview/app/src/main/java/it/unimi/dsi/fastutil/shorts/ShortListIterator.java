package it.unimi.dsi.fastutil.shorts;

import java.util.ListIterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface ShortListIterator extends ShortBidirectionalIterator, ListIterator<Short> {
    @Override // java.util.ListIterator
    @Deprecated
    default void add(Short sh) {
        add(sh.shortValue());
    }

    @Override // java.util.Iterator, java.util.ListIterator
    default void remove() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator
    @Deprecated
    default void set(Short sh) {
        set(sh.shortValue());
    }

    @Override // java.util.Iterator, it.unimi.dsi.fastutil.shorts.ShortIterator
    @Deprecated
    default Short next() {
        return super.next();
    }

    @Override // it.unimi.dsi.fastutil.shorts.ShortBidirectionalIterator, it.unimi.dsi.fastutil.BidirectionalIterator
    @Deprecated
    default Short previous() {
        return super.previous();
    }

    default void add(short s) {
        throw new UnsupportedOperationException();
    }

    default void set(short s) {
        throw new UnsupportedOperationException();
    }
}
