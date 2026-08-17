package it.unimi.dsi.fastutil.longs;

import java.util.ListIterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface LongListIterator extends LongBidirectionalIterator, ListIterator<Long> {
    @Override // java.util.ListIterator
    @Deprecated
    default void add(Long l) {
        add(l.longValue());
    }

    @Override // java.util.Iterator, java.util.ListIterator
    default void remove() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator
    @Deprecated
    default void set(Long l) {
        set(l.longValue());
    }

    @Override // it.unimi.dsi.fastutil.longs.LongIterator, java.util.PrimitiveIterator.OfLong, java.util.Iterator
    @Deprecated
    default Long next() {
        return super.next();
    }

    @Override // it.unimi.dsi.fastutil.longs.LongBidirectionalIterator, it.unimi.dsi.fastutil.BidirectionalIterator
    @Deprecated
    default Long previous() {
        return super.previous();
    }

    default void add(long j) {
        throw new UnsupportedOperationException();
    }

    default void set(long j) {
        throw new UnsupportedOperationException();
    }
}
