package it.unimi.dsi.fastutil.floats;

import defpackage.ci5;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface FloatSpliterator extends Spliterator.OfPrimitive<Float, FloatConsumer, FloatSpliterator> {
    @Override // java.util.Spliterator
    @Deprecated
    default void forEachRemaining(Consumer<? super Float> consumer) {
        FloatConsumer ci5Var;
        if (consumer instanceof FloatConsumer) {
            ci5Var = (FloatConsumer) consumer;
        } else {
            Objects.requireNonNull(consumer);
            ci5Var = new ci5(consumer);
        }
        forEachRemaining(ci5Var);
    }

    @Override // java.util.Spliterator
    default FloatComparator getComparator() {
        throw new IllegalStateException();
    }

    @Override // java.util.Spliterator
    @Deprecated
    default boolean tryAdvance(Consumer<? super Float> consumer) {
        FloatConsumer ci5Var;
        if (consumer instanceof FloatConsumer) {
            ci5Var = (FloatConsumer) consumer;
        } else {
            Objects.requireNonNull(consumer);
            ci5Var = new ci5(consumer);
        }
        return tryAdvance(ci5Var);
    }

    @Override // java.util.Spliterator.OfPrimitive, java.util.Spliterator
    FloatSpliterator trySplit();
}
