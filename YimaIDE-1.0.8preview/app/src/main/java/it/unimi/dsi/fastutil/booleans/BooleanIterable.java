package it.unimi.dsi.fastutil.booleans;

import defpackage.oy0;
import java.util.Objects;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface BooleanIterable extends Iterable<Boolean> {
    @Override // java.lang.Iterable
    @Deprecated
    default void forEach(Consumer<? super Boolean> consumer) {
        Objects.requireNonNull(consumer);
        forEach(consumer instanceof BooleanConsumer ? (BooleanConsumer) consumer : new oy0(consumer));
    }

    @Override // java.lang.Iterable, it.unimi.dsi.fastutil.booleans.BooleanCollection, it.unimi.dsi.fastutil.booleans.BooleanIterable
    BooleanIterator iterator();

    @Override // it.unimi.dsi.fastutil.booleans.BooleanCollection, it.unimi.dsi.fastutil.booleans.BooleanIterable, it.unimi.dsi.fastutil.booleans.BooleanList, java.util.List
    default BooleanSpliterator spliterator() {
        return BooleanSpliterators.asSpliteratorUnknownSize(iterator(), 0);
    }

    default void forEach(BooleanConsumer booleanConsumer) {
        Objects.requireNonNull(booleanConsumer);
        iterator().forEachRemaining(booleanConsumer);
    }
}
