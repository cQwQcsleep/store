package it.unimi.dsi.fastutil.longs;

import defpackage.uh9;
import java.util.Objects;
import java.util.PrimitiveIterator;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface LongIterator extends PrimitiveIterator.OfLong {
    @Override // java.util.PrimitiveIterator.OfLong, java.util.Iterator
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

    @Override // java.util.PrimitiveIterator.OfLong, java.util.Iterator
    @Deprecated
    default Long next() {
        return Long.valueOf(nextLong());
    }

    @Override // java.util.PrimitiveIterator.OfLong
    long nextLong();
}
