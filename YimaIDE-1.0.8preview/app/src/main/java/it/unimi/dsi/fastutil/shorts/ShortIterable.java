package it.unimi.dsi.fastutil.shorts;

import defpackage.had;
import java.util.Objects;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface ShortIterable extends Iterable<Short> {
    @Override // java.lang.Iterable
    @Deprecated
    default void forEach(Consumer<? super Short> consumer) {
        Objects.requireNonNull(consumer);
        forEach(consumer instanceof ShortConsumer ? (ShortConsumer) consumer : new had(consumer));
    }

    @Override // java.lang.Iterable, it.unimi.dsi.fastutil.shorts.ShortCollection, it.unimi.dsi.fastutil.shorts.ShortIterable
    ShortIterator iterator();

    @Override // it.unimi.dsi.fastutil.shorts.ShortCollection, it.unimi.dsi.fastutil.shorts.ShortIterable, it.unimi.dsi.fastutil.shorts.ShortList, java.util.List
    default ShortSpliterator spliterator() {
        return ShortSpliterators.asSpliteratorUnknownSize(iterator(), 0);
    }

    default void forEach(ShortConsumer shortConsumer) {
        Objects.requireNonNull(shortConsumer);
        iterator().forEachRemaining(shortConsumer);
    }
}
