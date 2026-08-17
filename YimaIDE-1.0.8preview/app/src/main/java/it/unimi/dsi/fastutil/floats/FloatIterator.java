package it.unimi.dsi.fastutil.floats;

import defpackage.ci5;
import java.util.Objects;
import java.util.PrimitiveIterator;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface FloatIterator extends PrimitiveIterator<Float, FloatConsumer> {
    @Override // java.util.Iterator
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

    @Override // java.util.Iterator
    @Deprecated
    default Float next() {
        return Float.valueOf(nextFloat());
    }

    float nextFloat();

    @Override // java.util.PrimitiveIterator
    default void forEachRemaining(FloatConsumer floatConsumer) {
        Objects.requireNonNull(floatConsumer);
        while (hasNext()) {
            floatConsumer.accept(nextFloat());
        }
    }
}
