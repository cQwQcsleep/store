package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.Function;
import it.unimi.dsi.fastutil.SafeMath;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Char2ReferenceFunction<V> extends Function<Character, V>, IntFunction<V> {
    @Override // java.util.function.IntFunction
    @Deprecated
    default V apply(int i) {
        return get(SafeMath.safeIntToChar(i));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<T, V> compose(java.util.function.Function<? super T, ? extends Character> function) {
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

    default V defaultReturnValue() {
        return null;
    }

    V get(char c);

    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default V get(Object obj) {
        if (obj == null) {
            return null;
        }
        char cCharValue = ((Character) obj).charValue();
        V v = get(cCharValue);
        if (v != defaultReturnValue() || containsKey(cCharValue)) {
            return v;
        }
        return null;
    }

    @Deprecated
    default V put(Character ch, V v) {
        char cCharValue = ch.charValue();
        boolean zContainsKey = containsKey(cCharValue);
        V vPut = put(cCharValue, v);
        if (zContainsKey) {
            return vPut;
        }
        return null;
    }

    @Deprecated
    default V remove(Object obj) {
        if (obj == null) {
            return null;
        }
        char cCharValue = ((Character) obj).charValue();
        if (containsKey(cCharValue)) {
            return remove(cCharValue);
        }
        return null;
    }

    default boolean containsKey(char c) {
        return true;
    }

    default V put(char c, V v) {
        throw new UnsupportedOperationException();
    }

    default V remove(char c) {
        throw new UnsupportedOperationException();
    }
}
