package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.SafeMath;
import it.unimi.dsi.fastutil.shorts.ShortConsumer;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface ShortConsumer extends Consumer<Short>, IntConsumer {
    static /* synthetic */ void r(ShortConsumer shortConsumer, ShortConsumer shortConsumer2, short s) {
        shortConsumer.accept(s);
        shortConsumer2.accept(s);
    }

    @Override // java.util.function.IntConsumer
    @Deprecated
    default void accept(int i) {
        accept(SafeMath.safeIntToShort(i));
    }

    void accept(short s);

    @Override // java.util.function.IntConsumer
    default ShortConsumer andThen(final IntConsumer intConsumer) {
        ShortConsumer shortConsumer;
        if (intConsumer instanceof ShortConsumer) {
            shortConsumer = (ShortConsumer) intConsumer;
        } else {
            Objects.requireNonNull(intConsumer);
            shortConsumer = new ShortConsumer() { // from class: y9d
                @Override // it.unimi.dsi.fastutil.shorts.ShortConsumer
                public final void accept(short s) {
                    intConsumer.accept(s);
                }
            };
        }
        return andThen(shortConsumer);
    }

    @Override // java.util.function.Consumer
    @Deprecated
    default void accept(Short sh) {
        accept(sh.shortValue());
    }

    default ShortConsumer andThen(final ShortConsumer shortConsumer) {
        Objects.requireNonNull(shortConsumer);
        return new ShortConsumer() { // from class: z9d
            @Override // it.unimi.dsi.fastutil.shorts.ShortConsumer
            public final void accept(short s) {
                ShortConsumer.r(this.b, shortConsumer, s);
            }
        };
    }

    @Override // java.util.function.Consumer
    @Deprecated
    default Consumer<Short> andThen(Consumer<? super Short> consumer) {
        return super.andThen(consumer);
    }
}
