package it.unimi.dsi.fastutil.doubles;

import defpackage.dw3;
import java.util.Objects;
import java.util.PrimitiveIterator;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface DoubleIterator extends PrimitiveIterator.OfDouble {
    @Override // java.util.PrimitiveIterator.OfDouble, java.util.Iterator
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

    @Override // java.util.PrimitiveIterator.OfDouble, java.util.Iterator
    @Deprecated
    default Double next() {
        return Double.valueOf(nextDouble());
    }

    @Override // java.util.PrimitiveIterator.OfDouble
    double nextDouble();
}
