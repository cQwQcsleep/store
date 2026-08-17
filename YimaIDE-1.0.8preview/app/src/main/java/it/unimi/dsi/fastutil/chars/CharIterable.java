package it.unimi.dsi.fastutil.chars;

import defpackage.zf1;
import java.util.Objects;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface CharIterable extends Iterable<Character> {
    @Override // java.lang.Iterable
    @Deprecated
    default void forEach(Consumer<? super Character> consumer) {
        Objects.requireNonNull(consumer);
        forEach(consumer instanceof CharConsumer ? (CharConsumer) consumer : new zf1(consumer));
    }

    @Override // java.lang.Iterable, it.unimi.dsi.fastutil.chars.CharCollection, it.unimi.dsi.fastutil.chars.CharIterable
    CharIterator iterator();

    @Override // it.unimi.dsi.fastutil.chars.CharCollection, it.unimi.dsi.fastutil.chars.CharIterable, it.unimi.dsi.fastutil.chars.CharList, java.util.List
    default CharSpliterator spliterator() {
        return CharSpliterators.asSpliteratorUnknownSize(iterator(), 0);
    }

    default void forEach(CharConsumer charConsumer) {
        Objects.requireNonNull(charConsumer);
        iterator().forEachRemaining(charConsumer);
    }
}
