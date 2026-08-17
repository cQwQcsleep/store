package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.doubles.DoubleConsumer;
import java.util.Objects;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface DoubleConsumer extends Consumer<Double>, java.util.function.DoubleConsumer {
    static /* synthetic */ void i(DoubleConsumer doubleConsumer, java.util.function.DoubleConsumer doubleConsumer2, double d) {
        doubleConsumer.accept(d);
        doubleConsumer2.accept(d);
    }

    @Override // java.util.function.Consumer
    @Deprecated
    default void accept(Double d) {
        accept(d.doubleValue());
    }

    @Override // java.util.function.DoubleConsumer
    default DoubleConsumer andThen(final java.util.function.DoubleConsumer doubleConsumer) {
        Objects.requireNonNull(doubleConsumer);
        return new DoubleConsumer() { // from class: cw3
            @Override // java.util.function.DoubleConsumer
            public final void accept(double d) {
                DoubleConsumer.i(this.b, doubleConsumer, d);
            }
        };
    }

    @Override // java.util.function.Consumer
    @Deprecated
    default Consumer<Double> andThen(Consumer<? super Double> consumer) {
        return super.andThen(consumer);
    }
}
