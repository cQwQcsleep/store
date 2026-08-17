package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.Function;
import java.util.function.LongFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Long2ReferenceFunction<V> extends Function<Long, V>, LongFunction<V> {
    @Override // java.util.function.LongFunction
    default V apply(long j) {
        return get(j);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<T, V> compose(java.util.function.Function<? super T, ? extends Long> function) {
        return super.compose(function);
    }

    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default boolean containsKey(Object obj) {
        if (obj == null) {
            return false;
        }
        return containsKey(((Long) obj).longValue());
    }

    default V defaultReturnValue() {
        return null;
    }

    V get(long j);

    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default V get(Object obj) {
        if (obj == null) {
            return null;
        }
        long jLongValue = ((Long) obj).longValue();
        V v = get(jLongValue);
        if (v != defaultReturnValue() || containsKey(jLongValue)) {
            return v;
        }
        return null;
    }

    @Deprecated
    default V put(Long l, V v) {
        long jLongValue = l.longValue();
        boolean zContainsKey = containsKey(jLongValue);
        V vPut = put(jLongValue, v);
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
        long jLongValue = ((Long) obj).longValue();
        if (containsKey(jLongValue)) {
            return remove(jLongValue);
        }
        return null;
    }

    default boolean containsKey(long j) {
        return true;
    }

    default V put(long j, V v) {
        throw new UnsupportedOperationException();
    }

    default V remove(long j) {
        throw new UnsupportedOperationException();
    }
}
