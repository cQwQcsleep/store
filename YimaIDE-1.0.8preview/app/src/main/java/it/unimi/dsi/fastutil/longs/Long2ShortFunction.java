package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.Function;
import java.util.function.LongToIntFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Long2ShortFunction extends Function<Long, Short>, LongToIntFunction {
    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<Long, T> andThen(java.util.function.Function<? super Short, ? extends T> function) {
        return super.andThen(function);
    }

    @Override // java.util.function.LongToIntFunction
    default int applyAsInt(long j) {
        return get(j);
    }

    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<T, Short> compose(java.util.function.Function<? super T, ? extends Long> function) {
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

    default short defaultReturnValue() {
        return (short) 0;
    }

    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default Short get(Object obj) {
        if (obj == null) {
            return null;
        }
        long jLongValue = ((Long) obj).longValue();
        short s = get(jLongValue);
        if (s != defaultReturnValue() || containsKey(jLongValue)) {
            return Short.valueOf(s);
        }
        return null;
    }

    short get(long j);

    @Deprecated
    default Short put(Long l, Short sh) {
        long jLongValue = l.longValue();
        boolean zContainsKey = containsKey(jLongValue);
        short sPut = put(jLongValue, sh.shortValue());
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
        long jLongValue = ((Long) obj).longValue();
        if (containsKey(jLongValue)) {
            return Short.valueOf(remove(jLongValue));
        }
        return null;
    }

    default boolean containsKey(long j) {
        return true;
    }

    default short put(long j, short s) {
        throw new UnsupportedOperationException();
    }

    default short remove(long j) {
        throw new UnsupportedOperationException();
    }
}
