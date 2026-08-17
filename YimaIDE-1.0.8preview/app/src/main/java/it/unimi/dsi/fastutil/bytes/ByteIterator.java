package it.unimi.dsi.fastutil.bytes;

import defpackage.w31;
import java.util.Objects;
import java.util.PrimitiveIterator;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface ByteIterator extends PrimitiveIterator<Byte, ByteConsumer> {
    @Override // java.util.Iterator
    @Deprecated
    default void forEachRemaining(Consumer<? super Byte> consumer) {
        ByteConsumer w31Var;
        if (consumer instanceof ByteConsumer) {
            w31Var = (ByteConsumer) consumer;
        } else {
            Objects.requireNonNull(consumer);
            w31Var = new w31(consumer);
        }
        forEachRemaining(w31Var);
    }

    @Override // java.util.Iterator
    @Deprecated
    default Byte next() {
        return Byte.valueOf(nextByte());
    }

    byte nextByte();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.PrimitiveIterator
    default void forEachRemaining(ByteConsumer byteConsumer) {
        Objects.requireNonNull(byteConsumer);
        while (hasNext()) {
            byteConsumer.accept(nextByte());
        }
    }
}
