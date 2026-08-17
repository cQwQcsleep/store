package it.unimi.dsi.fastutil.doubles;

import defpackage.dw3;
import java.util.Objects;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface DoubleIterable extends Iterable<Double> {
    @Override // it.unimi.dsi.fastutil.doubles.DoubleIterable
    @Deprecated
    default void forEach(Consumer<? super Double> consumer) {
        Objects.requireNonNull(consumer);
        forEach(consumer instanceof java.util.function.DoubleConsumer ? (java.util.function.DoubleConsumer) consumer : new dw3(consumer));
    }

    @Override // java.lang.Iterable, it.unimi.dsi.fastutil.doubles.DoubleCollection, it.unimi.dsi.fastutil.doubles.DoubleIterable, it.unimi.dsi.fastutil.doubles.DoubleSet, java.util.Set
    DoubleIterator iterator();

    @Override // it.unimi.dsi.fastutil.doubles.DoubleCollection, it.unimi.dsi.fastutil.doubles.DoubleIterable, it.unimi.dsi.fastutil.doubles.DoubleSet, java.util.Set
    default DoubleSpliterator spliterator() {
        return DoubleSpliterators.asSpliteratorUnknownSize(iterator(), 0);
    }

    default void forEach(java.util.function.DoubleConsumer doubleConsumer) {
        Objects.requireNonNull(doubleConsumer);
        iterator().forEachRemaining(doubleConsumer);
    }
}
