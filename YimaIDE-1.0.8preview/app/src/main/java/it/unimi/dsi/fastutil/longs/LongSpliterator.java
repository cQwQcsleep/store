package it.unimi.dsi.fastutil.longs;

import defpackage.uh9;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface LongSpliterator extends Spliterator.OfLong {
    @Override // java.util.Spliterator.OfLong, java.util.Spliterator
    @Deprecated
    default void forEachRemaining(Consumer<? super Long> consumer) {
        java.util.function.LongConsumer uh9Var;
        if (consumer instanceof java.util.function.LongConsumer) {
            uh9Var = (java.util.function.LongConsumer) consumer;
        } else {
            Objects.requireNonNull(consumer);
            uh9Var = new uh9(consumer);
        }
        forEachRemaining(uh9Var);
    }

    @Override // java.util.Spliterator
    default LongComparator getComparator() {
        throw new IllegalStateException();
    }

    @Override // java.util.Spliterator.OfLong, java.util.Spliterator
    @Deprecated
    default boolean tryAdvance(Consumer<? super Long> consumer) {
        java.util.function.LongConsumer uh9Var;
        if (consumer instanceof java.util.function.LongConsumer) {
            uh9Var = (java.util.function.LongConsumer) consumer;
        } else {
            Objects.requireNonNull(consumer);
            uh9Var = new uh9(consumer);
        }
        return tryAdvance(uh9Var);
    }

    @Override // java.util.Spliterator.OfLong, java.util.Spliterator.OfPrimitive, java.util.Spliterator
    LongSpliterator trySplit();
}
