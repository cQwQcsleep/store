package it.unimi.dsi.fastutil.ints;

import defpackage.bs6;
import java.util.Objects;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface IntIterable extends Iterable<Integer> {
    @Override // it.unimi.dsi.fastutil.ints.IntIterable
    @Deprecated
    default void forEach(Consumer<? super Integer> consumer) {
        Objects.requireNonNull(consumer);
        forEach(consumer instanceof java.util.function.IntConsumer ? (java.util.function.IntConsumer) consumer : new bs6(consumer));
    }

    @Override // java.lang.Iterable, it.unimi.dsi.fastutil.ints.IntCollection, it.unimi.dsi.fastutil.ints.IntIterable, it.unimi.dsi.fastutil.ints.IntSet, java.util.Set
    IntIterator iterator();

    @Override // it.unimi.dsi.fastutil.ints.IntCollection, it.unimi.dsi.fastutil.ints.IntIterable, it.unimi.dsi.fastutil.ints.IntSet, java.util.Set
    default IntSpliterator spliterator() {
        return IntSpliterators.asSpliteratorUnknownSize(iterator(), 0);
    }

    default void forEach(java.util.function.IntConsumer intConsumer) {
        Objects.requireNonNull(intConsumer);
        iterator().forEachRemaining(intConsumer);
    }
}
