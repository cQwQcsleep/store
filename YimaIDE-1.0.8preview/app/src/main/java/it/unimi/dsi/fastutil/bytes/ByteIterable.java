package it.unimi.dsi.fastutil.bytes;

import defpackage.w31;
import java.util.Objects;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface ByteIterable extends Iterable<Byte> {
    @Override // java.lang.Iterable
    @Deprecated
    default void forEach(Consumer<? super Byte> consumer) {
        Objects.requireNonNull(consumer);
        forEach(consumer instanceof ByteConsumer ? (ByteConsumer) consumer : new w31(consumer));
    }

    @Override // java.lang.Iterable, it.unimi.dsi.fastutil.bytes.ByteCollection, it.unimi.dsi.fastutil.bytes.ByteIterable
    ByteIterator iterator();

    @Override // it.unimi.dsi.fastutil.bytes.ByteCollection, it.unimi.dsi.fastutil.bytes.ByteIterable, it.unimi.dsi.fastutil.bytes.ByteList, java.util.List
    default ByteSpliterator spliterator() {
        return ByteSpliterators.asSpliteratorUnknownSize(iterator(), 0);
    }

    default void forEach(ByteConsumer byteConsumer) {
        Objects.requireNonNull(byteConsumer);
        iterator().forEachRemaining(byteConsumer);
    }
}
