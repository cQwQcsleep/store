package it.unimi.dsi.fastutil.booleans;

import defpackage.oy0;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface BooleanSpliterator extends Spliterator.OfPrimitive<Boolean, BooleanConsumer, BooleanSpliterator> {
    @Override // java.util.Spliterator
    @Deprecated
    default void forEachRemaining(Consumer<? super Boolean> consumer) {
        BooleanConsumer oy0Var;
        if (consumer instanceof BooleanConsumer) {
            oy0Var = (BooleanConsumer) consumer;
        } else {
            Objects.requireNonNull(consumer);
            oy0Var = new oy0(consumer);
        }
        forEachRemaining(oy0Var);
    }

    @Override // java.util.Spliterator
    default BooleanComparator getComparator() {
        throw new IllegalStateException();
    }

    @Override // java.util.Spliterator
    @Deprecated
    default boolean tryAdvance(Consumer<? super Boolean> consumer) {
        BooleanConsumer oy0Var;
        if (consumer instanceof BooleanConsumer) {
            oy0Var = (BooleanConsumer) consumer;
        } else {
            Objects.requireNonNull(consumer);
            oy0Var = new oy0(consumer);
        }
        return tryAdvance(oy0Var);
    }

    @Override // java.util.Spliterator.OfPrimitive, java.util.Spliterator
    BooleanSpliterator trySplit();
}
