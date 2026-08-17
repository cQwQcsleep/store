package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.Function;
import it.unimi.dsi.fastutil.SafeMath;
import java.util.function.IntUnaryOperator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Char2IntFunction extends Function<Character, Integer>, IntUnaryOperator {
    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<Character, T> andThen(java.util.function.Function<? super Integer, ? extends T> function) {
        return super.andThen(function);
    }

    @Override // java.util.function.IntUnaryOperator
    @Deprecated
    default int applyAsInt(int i) {
        return get(SafeMath.safeIntToChar(i));
    }

    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<T, Integer> compose(java.util.function.Function<? super T, ? extends Character> function) {
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

    default int defaultReturnValue() {
        return 0;
    }

    int get(char c);

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default Integer get(Object obj) {
        if (obj == null) {
            return null;
        }
        char cCharValue = ((Character) obj).charValue();
        int i = get(cCharValue);
        if (i != defaultReturnValue() || containsKey(cCharValue)) {
            return Integer.valueOf(i);
        }
        return null;
    }

    @Deprecated
    default Integer put(Character ch, Integer num) {
        char cCharValue = ch.charValue();
        boolean zContainsKey = containsKey(cCharValue);
        int iPut = put(cCharValue, num.intValue());
        if (zContainsKey) {
            return Integer.valueOf(iPut);
        }
        return null;
    }

    @Deprecated
    default Integer remove(Object obj) {
        if (obj == null) {
            return null;
        }
        char cCharValue = ((Character) obj).charValue();
        if (containsKey(cCharValue)) {
            return Integer.valueOf(remove(cCharValue));
        }
        return null;
    }

    default boolean containsKey(char c) {
        return true;
    }

    default int put(char c, int i) {
        throw new UnsupportedOperationException();
    }

    default int remove(char c) {
        throw new UnsupportedOperationException();
    }
}
