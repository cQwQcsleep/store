package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Long2LongFunction extends Function<Long, Long>, java.util.function.LongUnaryOperator {
    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<Long, T> andThen(java.util.function.Function<? super Long, ? extends T> function) {
        return super.andThen(function);
    }

    @Override // java.util.function.LongUnaryOperator
    default long applyAsLong(long j) {
        return get(j);
    }

    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<T, Long> compose(java.util.function.Function<? super T, ? extends Long> function) {
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

    default long defaultReturnValue() {
        return 0L;
    }

    long get(long j);

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default Long get(Object obj) {
        if (obj == null) {
            return null;
        }
        long jLongValue = ((Long) obj).longValue();
        long j = get(jLongValue);
        if (j != defaultReturnValue() || containsKey(jLongValue)) {
            return Long.valueOf(j);
        }
        return null;
    }

    @Deprecated
    default Long put(Long l, Long l2) {
        long jLongValue = l.longValue();
        boolean zContainsKey = containsKey(jLongValue);
        long jPut = put(jLongValue, l2.longValue());
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
        long jLongValue = ((Long) obj).longValue();
        if (containsKey(jLongValue)) {
            return Long.valueOf(remove(jLongValue));
        }
        return null;
    }

    default boolean containsKey(long j) {
        return true;
    }

    default long put(long j, long j2) {
        throw new UnsupportedOperationException();
    }

    default long remove(long j) {
        throw new UnsupportedOperationException();
    }
}
