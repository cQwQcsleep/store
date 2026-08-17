package it.unimi.dsi.fastutil.booleans;

import defpackage.oy0;
import java.util.Objects;
import java.util.PrimitiveIterator;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface BooleanIterator extends PrimitiveIterator<Boolean, BooleanConsumer> {
    @Override // java.util.Iterator
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

    @Override // java.util.Iterator
    @Deprecated
    default Boolean next() {
        return Boolean.valueOf(nextBoolean());
    }

    boolean nextBoolean();

    @Override // java.util.PrimitiveIterator
    default void forEachRemaining(BooleanConsumer booleanConsumer) {
        Objects.requireNonNull(booleanConsumer);
        while (hasNext()) {
            booleanConsumer.accept(nextBoolean());
        }
    }
}
