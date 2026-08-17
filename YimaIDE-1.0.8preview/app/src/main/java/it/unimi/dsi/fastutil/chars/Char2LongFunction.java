package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.Function;
import it.unimi.dsi.fastutil.SafeMath;
import java.util.function.IntToLongFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Char2LongFunction extends Function<Character, Long>, IntToLongFunction {
    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<Character, T> andThen(java.util.function.Function<? super Long, ? extends T> function) {
        return super.andThen(function);
    }

    @Override // java.util.function.IntToLongFunction
    @Deprecated
    default long applyAsLong(int i) {
        return get(SafeMath.safeIntToChar(i));
    }

    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<T, Long> compose(java.util.function.Function<? super T, ? extends Character> function) {
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

    default long defaultReturnValue() {
        return 0L;
    }

    long get(char c);

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default Long get(Object obj) {
        if (obj == null) {
            return null;
        }
        char cCharValue = ((Character) obj).charValue();
        long j = get(cCharValue);
        if (j != defaultReturnValue() || containsKey(cCharValue)) {
            return Long.valueOf(j);
        }
        return null;
    }

    @Deprecated
    default Long put(Character ch, Long l) {
        char cCharValue = ch.charValue();
        boolean zContainsKey = containsKey(cCharValue);
        long jPut = put(cCharValue, l.longValue());
        if (zContainsKey) {
            return Long.valueOf(jPut);
        }
        return null;
    }

    @Deprecated
    default Long remove(Object obj) {
        if (obj == null) {
            return null;
        }
        char cCharValue = ((Character) obj).charValue();
        if (containsKey(cCharValue)) {
            return Long.valueOf(remove(cCharValue));
        }
        return null;
    }

    default boolean containsKey(char c) {
        return true;
    }

    default long put(char c, long j) {
        throw new UnsupportedOperationException();
    }

    default long remove(char c) {
        throw new UnsupportedOperationException();
    }
}
