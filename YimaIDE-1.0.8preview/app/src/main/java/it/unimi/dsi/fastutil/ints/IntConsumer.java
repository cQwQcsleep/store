package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.ints.IntConsumer;
import java.util.Objects;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface IntConsumer extends Consumer<Integer>, java.util.function.IntConsumer {
    static /* synthetic */ void n(IntConsumer intConsumer, java.util.function.IntConsumer intConsumer2, int i) {
        intConsumer.accept(i);
        intConsumer2.accept(i);
    }

    @Override // java.util.function.Consumer
    @Deprecated
    default void accept(Integer num) {
        accept(num.intValue());
    }

    @Override // java.util.function.IntConsumer
    default IntConsumer andThen(final java.util.function.IntConsumer intConsumer) {
        Objects.requireNonNull(intConsumer);
        return new IntConsumer() { // from class: as6
            @Override // java.util.function.IntConsumer
            public final void accept(int i) {
                IntConsumer.n(this.b, intConsumer, i);
            }
        };
    }

    @Override // java.util.function.Consumer
    @Deprecated
    default Consumer<Integer> andThen(Consumer<? super Integer> consumer) {
        return super.andThen(consumer);
    }
}
