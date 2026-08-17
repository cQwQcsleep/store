package it.unimi.dsi.fastutil.doubles;

import defpackage.dw3;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface DoubleSpliterator extends Spliterator.OfDouble {
    @Override // java.util.Spliterator.OfDouble, java.util.Spliterator
    @Deprecated
    default void forEachRemaining(Consumer<? super Double> consumer) {
        java.util.function.DoubleConsumer dw3Var;
        if (consumer instanceof java.util.function.DoubleConsumer) {
            dw3Var = (java.util.function.DoubleConsumer) consumer;
        } else {
            Objects.requireNonNull(consumer);
            dw3Var = new dw3(consumer);
        }
        forEachRemaining(dw3Var);
    }

    @Override // java.util.Spliterator
    default DoubleComparator getComparator() {
        throw new IllegalStateException();
    }

    @Override // java.util.Spliterator.OfDouble, java.util.Spliterator
    @Deprecated
    default boolean tryAdvance(Consumer<? super Double> consumer) {
        java.util.function.DoubleConsumer dw3Var;
        if (consumer instanceof java.util.function.DoubleConsumer) {
            dw3Var = (java.util.function.DoubleConsumer) consumer;
        } else {
            Objects.requireNonNull(consumer);
            dw3Var = new dw3(consumer);
        }
        return tryAdvance(dw3Var);
    }

    @Override // java.util.Spliterator.OfDouble, java.util.Spliterator.OfPrimitive, java.util.Spliterator
    DoubleSpliterator trySplit();
}
