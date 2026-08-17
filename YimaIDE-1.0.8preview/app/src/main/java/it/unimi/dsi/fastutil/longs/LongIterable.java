package it.unimi.dsi.fastutil.longs;

import defpackage.uh9;
import java.util.Objects;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface LongIterable extends Iterable<Long> {
    @Override // it.unimi.dsi.fastutil.longs.LongIterable
    @Deprecated
    default void forEach(Consumer<? super Long> consumer) {
        Objects.requireNonNull(consumer);
        forEach(consumer instanceof java.util.function.LongConsumer ? (java.util.function.LongConsumer) consumer : new uh9(consumer));
    }

    @Override // java.lang.Iterable, it.unimi.dsi.fastutil.longs.LongCollection, it.unimi.dsi.fastutil.longs.LongIterable, it.unimi.dsi.fastutil.longs.LongSet, java.util.Set
    LongIterator iterator();

    @Override // it.unimi.dsi.fastutil.longs.LongCollection, it.unimi.dsi.fastutil.longs.LongIterable, it.unimi.dsi.fastutil.longs.LongSet, java.util.Set
    default LongSpliterator spliterator() {
        return LongSpliterators.asSpliteratorUnknownSize(iterator(), 0);
    }

    default void forEach(java.util.function.LongConsumer longConsumer) {
        Objects.requireNonNull(longConsumer);
        iterator().forEachRemaining(longConsumer);
    }
}
