package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.Function;
import it.unimi.dsi.fastutil.SafeMath;
import java.util.function.IntPredicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Char2BooleanFunction extends Function<Character, Boolean>, IntPredicate {
    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<Character, T> andThen(java.util.function.Function<? super Boolean, ? extends T> function) {
        return super.andThen(function);
    }

    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<T, Boolean> compose(java.util.function.Function<? super T, ? extends Character> function) {
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

    default boolean defaultReturnValue() {
        return false;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default Boolean get(Object obj) {
        if (obj == null) {
            return null;
        }
        char cCharValue = ((Character) obj).charValue();
        boolean z = get(cCharValue);
        if (z != defaultReturnValue() || containsKey(cCharValue)) {
            return Boolean.valueOf(z);
        }
        return null;
    }

    boolean get(char c);

    @Deprecated
    default Boolean put(Character ch, Boolean bool) {
        char cCharValue = ch.charValue();
        boolean zContainsKey = containsKey(cCharValue);
        boolean zPut = put(cCharValue, bool.booleanValue());
        if (zContainsKey) {
            return Boolean.valueOf(zPut);
        }
        return null;
    }

    @Deprecated
    default Boolean remove(Object obj) {
        if (obj == null) {
            return null;
        }
        char cCharValue = ((Character) obj).charValue();
        if (containsKey(cCharValue)) {
            return Boolean.valueOf(remove(cCharValue));
        }
        return null;
    }

    @Override // java.util.function.IntPredicate
    @Deprecated
    default boolean test(int i) {
        return get(SafeMath.safeIntToChar(i));
    }

    default boolean containsKey(char c) {
        return true;
    }

    default boolean put(char c, boolean z) {
        throw new UnsupportedOperationException();
    }

    default boolean remove(char c) {
        throw new UnsupportedOperationException();
    }
}
