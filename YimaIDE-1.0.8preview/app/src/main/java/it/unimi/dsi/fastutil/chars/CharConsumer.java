package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.SafeMath;
import it.unimi.dsi.fastutil.chars.CharConsumer;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface CharConsumer extends Consumer<Character>, IntConsumer {
    static /* synthetic */ void b(CharConsumer charConsumer, CharConsumer charConsumer2, char c) {
        charConsumer.accept(c);
        charConsumer2.accept(c);
    }

    void accept(char c);

    @Override // java.util.function.IntConsumer
    @Deprecated
    default void accept(int i) {
        accept(SafeMath.safeIntToChar(i));
    }

    @Override // java.util.function.IntConsumer
    default CharConsumer andThen(final IntConsumer intConsumer) {
        CharConsumer charConsumer;
        if (intConsumer instanceof CharConsumer) {
            charConsumer = (CharConsumer) intConsumer;
        } else {
            Objects.requireNonNull(intConsumer);
            charConsumer = new CharConsumer() { // from class: uf1
                @Override // it.unimi.dsi.fastutil.chars.CharConsumer
                public final void accept(char c) {
                    intConsumer.accept(c);
                }
            };
        }
        return andThen(charConsumer);
    }

    @Override // java.util.function.Consumer
    @Deprecated
    default void accept(Character ch) {
        accept(ch.charValue());
    }

    default CharConsumer andThen(final CharConsumer charConsumer) {
        Objects.requireNonNull(charConsumer);
        return new CharConsumer() { // from class: tf1
            @Override // it.unimi.dsi.fastutil.chars.CharConsumer
            public final void accept(char c) {
                CharConsumer.b(this.b, charConsumer, c);
            }
        };
    }

    @Override // java.util.function.Consumer
    @Deprecated
    default Consumer<Character> andThen(Consumer<? super Character> consumer) {
        return super.andThen(consumer);
    }
}
