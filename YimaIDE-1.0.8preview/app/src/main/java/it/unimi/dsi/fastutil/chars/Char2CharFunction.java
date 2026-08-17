package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.Function;
import it.unimi.dsi.fastutil.SafeMath;
import java.util.function.IntUnaryOperator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Char2CharFunction extends Function<Character, Character>, IntUnaryOperator {
    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<Character, T> andThen(java.util.function.Function<? super Character, ? extends T> function) {
        return super.andThen(function);
    }

    @Override // java.util.function.IntUnaryOperator
    @Deprecated
    default int applyAsInt(int i) {
        return get(SafeMath.safeIntToChar(i));
    }

    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<T, Character> compose(java.util.function.Function<? super T, ? extends Character> function) {
        return super.compose(function);
    }

    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default boolean containsKey(Object obj) {
        if (obj == null) {
            return false;
        }
        return containsKey(((Character) obj).charValue());
    }

    default char defaultReturnValue() {
        return (char) 0;
    }

    char get(char c);

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default Character get(Object obj) {
        if (obj == null) {
            return null;
        }
        char cCharValue = ((Character) obj).charValue();
        char c = get(cCharValue);
        if (c != defaultReturnValue() || containsKey(cCharValue)) {
            return Character.valueOf(c);
        }
        return null;
    }

    @Deprecated
    default Character put(Character ch, Character ch2) {
        char cCharValue = ch.charValue();
        boolean zContainsKey = containsKey(cCharValue);
        char cPut = put(cCharValue, ch2.charValue());
        if (zContainsKey) {
            return Character.valueOf(cPut);
        }
        return null;
    }

    @Deprecated
    default Character remove(Object obj) {
        if (obj == null) {
            return null;
        }
        char cCharValue = ((Character) obj).charValue();
        if (containsKey(cCharValue)) {
            return Character.valueOf(remove(cCharValue));
        }
        return null;
    }

    default boolean containsKey(char c) {
        return true;
    }

    default char put(char c, char c2) {
        throw new UnsupportedOperationException();
    }

    default char remove(char c) {
        throw new UnsupportedOperationException();
    }
}
