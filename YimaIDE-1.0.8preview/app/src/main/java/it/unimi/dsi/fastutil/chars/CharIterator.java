package it.unimi.dsi.fastutil.chars;

import defpackage.zf1;
import java.util.Objects;
import java.util.PrimitiveIterator;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface CharIterator extends PrimitiveIterator<Character, CharConsumer> {
    @Override // java.util.Iterator
    @Deprecated
    default void forEachRemaining(Consumer<? super Character> consumer) {
        CharConsumer zf1Var;
        if (consumer instanceof CharConsumer) {
            zf1Var = (CharConsumer) consumer;
        } else {
            Objects.requireNonNull(consumer);
            zf1Var = new zf1(consumer);
        }
        forEachRemaining(zf1Var);
    }

    @Override // java.util.Iterator
    @Deprecated
    default Character next() {
        return Character.valueOf(nextChar());
    }

    char nextChar();

    @Override // java.util.PrimitiveIterator
    default void forEachRemaining(CharConsumer charConsumer) {
        Objects.requireNonNull(charConsumer);
        while (hasNext()) {
            charConsumer.accept(nextChar());
        }
    }
}
