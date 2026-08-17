package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Long2BooleanFunction extends Function<Long, Boolean>, java.util.function.LongPredicate {
    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<Long, T> andThen(java.util.function.Function<? super Boolean, ? extends T> function) {
        return super.andThen(function);
    }

    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<T, Boolean> compose(java.util.function.Function<? super T, ? extends Long> function) {
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
        long jLongValue = ((Long) obj).longValue();
        boolean z = get(jLongValue);
        if (z != defaultReturnValue() || containsKey(jLongValue)) {
            return Boolean.valueOf(z);
        }
        return null;
    }

    boolean get(long j);

    @Deprecated
    default Boolean put(Long l, Boolean bool) {
        long jLongValue = l.longValue();
        boolean zContainsKey = containsKey(jLongValue);
        boolean zPut = put(jLongValue, bool.booleanValue());
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
        long jLongValue = ((Long) obj).longValue();
        if (containsKey(jLongValue)) {
            return Boolean.valueOf(remove(jLongValue));
        }
        return null;
    }

    @Override // java.util.function.LongPredicate
    default boolean test(long j) {
        return get(j);
    }

    default boolean containsKey(long j) {
        return true;
    }

    default boolean put(long j, boolean z) {
        throw new UnsupportedOperationException();
    }

    default boolean remove(long j) {
        throw new UnsupportedOperationException();
    }
}
