package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.SafeMath;
import it.unimi.dsi.fastutil.floats.FloatConsumer;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface FloatConsumer extends Consumer<Float>, DoubleConsumer {
    static /* synthetic */ void d(FloatConsumer floatConsumer, FloatConsumer floatConsumer2, float f) {
        floatConsumer.accept(f);
        floatConsumer2.accept(f);
    }

    @Override // java.util.function.DoubleConsumer
    @Deprecated
    default void accept(double d) {
        accept(SafeMath.safeDoubleToFloat(d));
    }

    void accept(float f);

    @Override // java.util.function.DoubleConsumer
    default FloatConsumer andThen(final DoubleConsumer doubleConsumer) {
        FloatConsumer floatConsumer;
        if (doubleConsumer instanceof FloatConsumer) {
            floatConsumer = (FloatConsumer) doubleConsumer;
        } else {
            Objects.requireNonNull(doubleConsumer);
            floatConsumer = new FloatConsumer() { // from class: ai5
                @Override // it.unimi.dsi.fastutil.floats.FloatConsumer
                public final void accept(float f) {
                    doubleConsumer.accept(f);
                }
            };
        }
        return andThen(floatConsumer);
    }

    @Override // java.util.function.Consumer
    @Deprecated
    default void accept(Float f) {
        accept(f.floatValue());
    }

    default FloatConsumer andThen(final FloatConsumer floatConsumer) {
        Objects.requireNonNull(floatConsumer);
        return new FloatConsumer() { // from class: bi5
            @Override // it.unimi.dsi.fastutil.floats.FloatConsumer
            public final void accept(float f) {
                FloatConsumer.d(this.b, floatConsumer, f);
            }
        };
    }

    @Override // java.util.function.Consumer
    @Deprecated
    default Consumer<Float> andThen(Consumer<? super Float> consumer) {
        return super.andThen(consumer);
    }
}
