package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.Function;
import java.util.function.IntToLongFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Int2LongFunction extends Function<Integer, Long>, IntToLongFunction {
    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<Integer, T> andThen(java.util.function.Function<? super Long, ? extends T> function) {
        return super.andThen(function);
    }

    @Override // java.util.function.IntToLongFunction
    default long applyAsLong(int i) {
        return get(i);
    }

    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<T, Long> compose(java.util.function.Function<? super T, ? extends Integer> function) {
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

    default long defaultReturnValue() {
        return 0L;
    }

    long get(int i);

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default Long get(Object obj) {
        if (obj == null) {
            return null;
        }
        int iIntValue = ((Integer) obj).intValue();
        long j = get(iIntValue);
        if (j != defaultReturnValue() || containsKey(iIntValue)) {
            return Long.valueOf(j);
        }
        return null;
    }

    @Deprecated
    default Long put(Integer num, Long l) {
        int iIntValue = num.intValue();
        boolean zContainsKey = containsKey(iIntValue);
        long jPut = put(iIntValue, l.longValue());
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
        int iIntValue = ((Integer) obj).intValue();
        if (containsKey(iIntValue)) {
            return Long.valueOf(remove(iIntValue));
        }
        return null;
    }

    default boolean containsKey(int i) {
        return true;
    }

    default long put(int i, long j) {
        throw new UnsupportedOperationException();
    }

    default long remove(int i) {
        throw new UnsupportedOperationException();
    }
}
