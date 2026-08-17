package it.unimi.dsi.fastutil.ints;

import defpackage.bs6;
import java.util.Objects;
import java.util.PrimitiveIterator;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface IntIterator extends PrimitiveIterator.OfInt {
    @Override // java.util.PrimitiveIterator.OfInt, java.util.Iterator
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

    @Override // java.util.PrimitiveIterator.OfInt, java.util.Iterator
    @Deprecated
    default Integer next() {
        return Integer.valueOf(nextInt());
    }

    @Override // java.util.PrimitiveIterator.OfInt
    int nextInt();
}
