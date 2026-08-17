package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.longs.LongConsumer;
import java.util.Objects;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface LongConsumer extends Consumer<Long>, java.util.function.LongConsumer {
    static /* synthetic */ void m(LongConsumer longConsumer, java.util.function.LongConsumer longConsumer2, long j) {
        longConsumer.accept(j);
        longConsumer2.accept(j);
    }

    @Override // java.util.function.Consumer
    @Deprecated
    default void accept(Long l) {
        accept(l.longValue());
    }

    @Override // java.util.function.LongConsumer
    default LongConsumer andThen(final java.util.function.LongConsumer longConsumer) {
        Objects.requireNonNull(longConsumer);
        return new LongConsumer() { // from class: rh9
            @Override // java.util.function.LongConsumer
            public final void accept(long j) {
                LongConsumer.m(this.b, longConsumer, j);
            }
        };
    }

    @Override // java.util.function.Consumer
    @Deprecated
    default Consumer<Long> andThen(Consumer<? super Long> consumer) {
        return super.andThen(consumer);
    }
}
