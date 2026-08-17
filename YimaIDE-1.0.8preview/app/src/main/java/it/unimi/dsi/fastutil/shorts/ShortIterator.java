package it.unimi.dsi.fastutil.shorts;

import defpackage.had;
import java.util.Objects;
import java.util.PrimitiveIterator;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface ShortIterator extends PrimitiveIterator<Short, ShortConsumer> {
    @Override // java.util.Iterator
    @Deprecated
    default void forEachRemaining(Consumer<? super Short> consumer) {
        ShortConsumer hadVar;
        if (consumer instanceof ShortConsumer) {
            hadVar = (ShortConsumer) consumer;
        } else {
            Objects.requireNonNull(consumer);
            hadVar = new had(consumer);
        }
        forEachRemaining(hadVar);
    }

    @Override // java.util.Iterator
    @Deprecated
    default Short next() {
        return Short.valueOf(nextShort());
    }

    short nextShort();

    @Override // java.util.PrimitiveIterator
    default void forEachRemaining(ShortConsumer shortConsumer) {
        Objects.requireNonNull(shortConsumer);
        while (hasNext()) {
            shortConsumer.accept(nextShort());
        }
    }
}
