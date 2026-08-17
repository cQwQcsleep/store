package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.Function;
import it.unimi.dsi.fastutil.SafeMath;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Short2ReferenceFunction<V> extends Function<Short, V>, IntFunction<V> {
    @Override // java.util.function.IntFunction
    @Deprecated
    default V apply(int i) {
        return get(SafeMath.safeIntToShort(i));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<T, V> compose(java.util.function.Function<? super T, ? extends Short> function) {
        return super.compose(function);
    }

    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default boolean containsKey(Object obj) {
        if (obj == null) {
            return false;
        }
        return containsKey(((Short) obj).shortValue());
    }

    default V defaultReturnValue() {
        return null;
    }

    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default V get(Object obj) {
        if (obj == null) {
            return null;
        }
        short sShortValue = ((Short) obj).shortValue();
        V v = get(sShortValue);
        if (v != defaultReturnValue() || containsKey(sShortValue)) {
            return v;
        }
        return null;
    }

    V get(short s);

    @Deprecated
    default V put(Short sh, V v) {
        short sShortValue = sh.shortValue();
        boolean zContainsKey = containsKey(sShortValue);
        V vPut = put(sShortValue, v);
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
        short sShortValue = ((Short) obj).shortValue();
        if (containsKey(sShortValue)) {
            return remove(sShortValue);
        }
        return null;
    }

    default boolean containsKey(short s) {
        return true;
    }

    default V put(short s, V v) {
        throw new UnsupportedOperationException();
    }

    default V remove(short s) {
        throw new UnsupportedOperationException();
    }
}
