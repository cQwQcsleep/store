package it.unimi.dsi.fastutil.doubles;

import java.util.ListIterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface DoubleListIterator extends DoubleBidirectionalIterator, ListIterator<Double> {
    @Override // java.util.ListIterator
    @Deprecated
    default void add(Double d) {
        add(d.doubleValue());
    }

    @Override // java.util.Iterator, java.util.ListIterator
    default void remove() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator
    @Deprecated
    default void set(Double d) {
        set(d.doubleValue());
    }

    @Override // it.unimi.dsi.fastutil.doubles.DoubleIterator, java.util.PrimitiveIterator.OfDouble, java.util.Iterator
    @Deprecated
    default Double next() {
        return super.next();
    }

    @Override // it.unimi.dsi.fastutil.doubles.DoubleBidirectionalIterator, it.unimi.dsi.fastutil.BidirectionalIterator
    @Deprecated
    default Double previous() {
        return super.previous();
    }

    default void add(double d) {
        throw new UnsupportedOperationException();
    }

    default void set(double d) {
        throw new UnsupportedOperationException();
    }
}
