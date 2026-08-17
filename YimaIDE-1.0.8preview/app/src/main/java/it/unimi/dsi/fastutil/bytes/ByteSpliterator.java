package it.unimi.dsi.fastutil.bytes;

import defpackage.w31;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface ByteSpliterator extends Spliterator.OfPrimitive<Byte, ByteConsumer, ByteSpliterator> {
    @Override // java.util.Spliterator
    @Deprecated
    default void forEachRemaining(Consumer<? super Byte> consumer) {
        ByteConsumer w31Var;
        if (consumer instanceof ByteConsumer) {
            w31Var = (ByteConsumer) consumer;
        } else {
            Objects.requireNonNull(consumer);
            w31Var = new w31(consumer);
        }
        forEachRemaining(w31Var);
    }

    @Override // java.util.Spliterator
    default ByteComparator getComparator() {
        throw new IllegalStateException();
    }

    @Override // java.util.Spliterator
    @Deprecated
    default boolean tryAdvance(Consumer<? super Byte> consumer) {
        ByteConsumer w31Var;
        if (consumer instanceof ByteConsumer) {
            w31Var = (ByteConsumer) consumer;
        } else {
            Objects.requireNonNull(consumer);
            w31Var = new w31(consumer);
        }
        return tryAdvance(w31Var);
    }

    @Override // java.util.Spliterator.OfPrimitive, java.util.Spliterator
    ByteSpliterator trySplit();
}
