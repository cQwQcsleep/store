package it.unimi.dsi.fastutil.floats;

import defpackage.ci5;
import java.util.Objects;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface FloatIterable extends Iterable<Float> {
    @Override // java.lang.Iterable
    @Deprecated
    default void forEach(Consumer<? super Float> consumer) {
        Objects.requireNonNull(consumer);
        forEach(consumer instanceof FloatConsumer ? (FloatConsumer) consumer : new ci5(consumer));
    }

    @Override // java.lang.Iterable, it.unimi.dsi.fastutil.floats.FloatCollection, it.unimi.dsi.fastutil.floats.FloatIterable
    FloatIterator iterator();

    @Override // it.unimi.dsi.fastutil.floats.FloatCollection, it.unimi.dsi.fastutil.floats.FloatIterable, it.unimi.dsi.fastutil.floats.FloatList, java.util.List
    default FloatSpliterator spliterator() {
        return FloatSpliterators.asSpliteratorUnknownSize(iterator(), 0);
    }

    default void forEach(FloatConsumer floatConsumer) {
        Objects.requireNonNull(floatConsumer);
        iterator().forEachRemaining(floatConsumer);
    }
}
