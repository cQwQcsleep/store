package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.Function;
import it.unimi.dsi.fastutil.SafeMath;
import java.util.function.IntUnaryOperator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Char2ShortFunction extends Function<Character, Short>, IntUnaryOperator {
    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<Character, T> andThen(java.util.function.Function<? super Short, ? extends T> function) {
        return super.andThen(function);
    }

    @Override // java.util.function.IntUnaryOperator
    @Deprecated
    default int applyAsInt(int i) {
        return get(SafeMath.safeIntToChar(i));
    }

    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<T, Short> compose(java.util.function.Function<? super T, ? extends Character> function) {
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

    default short defaultReturnValue() {
        return (short) 0;
    }

    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default Short get(Object obj) {
        if (obj == null) {
            return null;
        }
        char cCharValue = ((Character) obj).charValue();
        short s = get(cCharValue);
        if (s != defaultReturnValue() || containsKey(cCharValue)) {
            return Short.valueOf(s);
        }
        return null;
    }

    short get(char c);

    @Deprecated
    default Short put(Character ch, Short sh) {
        char cCharValue = ch.charValue();
        boolean zContainsKey = containsKey(cCharValue);
        short sPut = put(cCharValue, sh.shortValue());
        if (zContainsKey) {
            return Short.valueOf(sPut);
        }
        return null;
    }

    @Deprecated
    default Short remove(Object obj) {
        if (obj == null) {
            return null;
        }
        char cCharValue = ((Character) obj).charValue();
        if (containsKey(cCharValue)) {
            return Short.valueOf(remove(cCharValue));
        }
        return null;
    }

    default boolean containsKey(char c) {
        return true;
    }

    default short put(char c, short s) {
        throw new UnsupportedOperationException();
    }

    default short remove(char c) {
        throw new UnsupportedOperationException();
    }
}
