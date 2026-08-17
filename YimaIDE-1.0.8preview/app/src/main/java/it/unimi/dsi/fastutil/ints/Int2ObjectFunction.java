package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.Function;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Int2ObjectFunction<V> extends Function<Integer, V>, IntFunction<V> {
    @Override // java.util.function.IntFunction
    default V apply(int i) {
        return get(i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<T, V> compose(java.util.function.Function<? super T, ? extends Integer> function) {
        return super.compose(function);
    }

    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default boolean containsKey(Object obj) {
        if (obj == null) {
            return false;
        }
        return containsKey(((Integer) obj).intValue());
    }

    default V defaultReturnValue() {
        return null;
    }

    V get(int i);

    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default V get(Object obj) {
        if (obj == null) {
            return null;
        }
        int iIntValue = ((Integer) obj).intValue();
        V v = get(iIntValue);
        if (v != defaultReturnValue() || containsKey(iIntValue)) {
            return v;
        }
        return null;
    }

    @Deprecated
    default V getOrDefault(Object obj, V v) {
        int iIntValue;
        V v2;
        return (obj != null && ((v2 = get((iIntValue = ((Integer) obj).intValue()))) != defaultReturnValue() || containsKey(iIntValue))) ? v2 : v;
    }

    @Deprecated
    default V put(Integer num, V v) {
        int iIntValue = num.intValue();
        boolean zContainsKey = containsKey(iIntValue);
        V vPut = put(iIntValue, v);
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
        int iIntValue = ((Integer) obj).intValue();
        if (containsKey(iIntValue)) {
            return remove(iIntValue);
        }
        return null;
    }

    default boolean containsKey(int i) {
        return true;
    }

    default V put(int i, V v) {
        throw new UnsupportedOperationException();
    }

    default V remove(int i) {
        throw new UnsupportedOperationException();
    }
}
