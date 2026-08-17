package it.unimi.dsi.fastutil.chars;

import defpackage.zf1;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface CharSpliterator extends Spliterator.OfPrimitive<Character, CharConsumer, CharSpliterator> {
    @Override // java.util.Spliterator
    @Deprecated
    default void forEachRemaining(Consumer<? super Character> consumer) {
        CharConsumer zf1Var;
        if (consumer instanceof CharConsumer) {
            zf1Var = (CharConsumer) consumer;
        } else {
            Objects.requireNonNull(consumer);
            zf1Var = new zf1(consumer);
        }
        forEachRemaining(zf1Var);
    }

    @Override // java.util.Spliterator
    default CharComparator getComparator() {
        throw new IllegalStateException();
    }

    @Override // java.util.Spliterator
    @Deprecated
    default boolean tryAdvance(Consumer<? super Character> consumer) {
        CharConsumer zf1Var;
        if (consumer instanceof CharConsumer) {
            zf1Var = (CharConsumer) consumer;
        } else {
            Objects.requireNonNull(consumer);
            zf1Var = new zf1(consumer);
        }
        return tryAdvance(zf1Var);
    }

    @Override // java.util.Spliterator.OfPrimitive, java.util.Spliterator
    CharSpliterator trySplit();
}
