package it.unimi.dsi.fastutil.ints;

import defpackage.bs6;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface IntSpliterator extends Spliterator.OfInt {
    @Override // java.util.Spliterator.OfInt, java.util.Spliterator
    @Deprecated
    default void forEachRemaining(Consumer<? super Integer> consumer) {
        java.util.function.IntConsumer bs6Var;
        if (consumer instanceof java.util.function.IntConsumer) {
            bs6Var = (java.util.function.IntConsumer) consumer;
        } else {
            Objects.requireNonNull(consumer);
            bs6Var = new bs6(consumer);
        }
        forEachRemaining(bs6Var);
    }

    @Override // java.util.Spliterator
    default IntComparator getComparator() {
        throw new IllegalStateException();
    }

    @Override // java.util.Spliterator.OfInt, java.util.Spliterator
    @Deprecated
    default boolean tryAdvance(Consumer<? super Integer> consumer) {
        java.util.function.IntConsumer bs6Var;
        if (consumer instanceof java.util.function.IntConsumer) {
            bs6Var = (java.util.function.IntConsumer) consumer;
        } else {
            Objects.requireNonNull(consumer);
            bs6Var = new bs6(consumer);
        }
        return tryAdvance(bs6Var);
    }

    @Override // java.util.Spliterator.OfInt, java.util.Spliterator.OfPrimitive, java.util.Spliterator
    IntSpliterator trySplit();
}
