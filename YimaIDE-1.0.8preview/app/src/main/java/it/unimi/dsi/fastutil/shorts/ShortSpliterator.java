package it.unimi.dsi.fastutil.shorts;

import defpackage.had;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface ShortSpliterator extends Spliterator.OfPrimitive<Short, ShortConsumer, ShortSpliterator> {
    @Override // java.util.Spliterator
    @Deprecated
    default void forEachRemaining(Consumer<? super Short> consumer) {
        ShortConsumer hadVar;
        if (consumer instanceof ShortConsumer) {
            hadVar = (ShortConsumer) consumer;
        } else {
            Objects.requireNonNull(consumer);
            hadVar = new had(consumer);
        }
        forEachRemaining(hadVar);
    }

    @Override // java.util.Spliterator
    default ShortComparator getComparator() {
        throw new IllegalStateException();
    }

    @Override // java.util.Spliterator
    @Deprecated
    default boolean tryAdvance(Consumer<? super Short> consumer) {
        ShortConsumer hadVar;
        if (consumer instanceof ShortConsumer) {
            hadVar = (ShortConsumer) consumer;
        } else {
            Objects.requireNonNull(consumer);
            hadVar = new had(consumer);
        }
        return tryAdvance(hadVar);
    }

    @Override // java.util.Spliterator.OfPrimitive, java.util.Spliterator
    ShortSpliterator trySplit();
}
