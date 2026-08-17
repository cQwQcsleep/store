package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.SafeMath;
import it.unimi.dsi.fastutil.bytes.ByteConsumer;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface ByteConsumer extends Consumer<Byte>, IntConsumer {
    static /* synthetic */ void g(ByteConsumer byteConsumer, ByteConsumer byteConsumer2, byte b) {
        byteConsumer.accept(b);
        byteConsumer2.accept(b);
    }

    void accept(byte b);

    @Override // java.util.function.IntConsumer
    @Deprecated
    default void accept(int i) {
        accept(SafeMath.safeIntToByte(i));
    }

    @Override // java.util.function.IntConsumer
    default ByteConsumer andThen(final IntConsumer intConsumer) {
        ByteConsumer byteConsumer;
        if (intConsumer instanceof ByteConsumer) {
            byteConsumer = (ByteConsumer) intConsumer;
        } else {
            Objects.requireNonNull(intConsumer);
            byteConsumer = new ByteConsumer() { // from class: t31
                @Override // it.unimi.dsi.fastutil.bytes.ByteConsumer
                public final void accept(byte b) {
                    intConsumer.accept(b);
                }
            };
        }
        return andThen(byteConsumer);
    }

    @Override // java.util.function.Consumer
    @Deprecated
    default void accept(Byte b) {
        accept(b.byteValue());
    }

    default ByteConsumer andThen(final ByteConsumer byteConsumer) {
        Objects.requireNonNull(byteConsumer);
        return new ByteConsumer() { // from class: u31
            @Override // it.unimi.dsi.fastutil.bytes.ByteConsumer
            public final void accept(byte b) {
                ByteConsumer.g(this.b, byteConsumer, b);
            }
        };
    }

    @Override // java.util.function.Consumer
    @Deprecated
    default Consumer<Byte> andThen(Consumer<? super Byte> consumer) {
        return super.andThen(consumer);
    }
}
