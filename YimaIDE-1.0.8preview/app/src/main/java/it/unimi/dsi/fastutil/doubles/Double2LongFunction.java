package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.Function;
import java.util.function.DoubleToLongFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Double2LongFunction extends Function<Double, Long>, DoubleToLongFunction {
    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<Double, T> andThen(java.util.function.Function<? super Long, ? extends T> function) {
        return super.andThen(function);
    }

    @Override // java.util.function.DoubleToLongFunction
    default long applyAsLong(double d) {
        return get(d);
    }

    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<T, Long> compose(java.util.function.Function<? super T, ? extends Double> function) {
        return super.compose(function);
    }

    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default boolean containsKey(Object obj) {
        if (obj == null) {
            return false;
        }
        return containsKey(((Double) obj).doubleValue());
    }

    default long defaultReturnValue() {
        return 0L;
    }

    long get(double d);

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default Long get(Object obj) {
        if (obj == null) {
            return null;
        }
        double dDoubleValue = ((Double) obj).doubleValue();
        long j = get(dDoubleValue);
        if (j != defaultReturnValue() || containsKey(dDoubleValue)) {
            return Long.valueOf(j);
        }
        return null;
    }

    @Deprecated
    default Long put(Double d, Long l) {
        double dDoubleValue = d.doubleValue();
        boolean zContainsKey = containsKey(dDoubleValue);
        long jPut = put(dDoubleValue, l.longValue());
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
        double dDoubleValue = ((Double) obj).doubleValue();
        if (containsKey(dDoubleValue)) {
            return Long.valueOf(remove(dDoubleValue));
        }
        return null;
    }

    default boolean containsKey(double d) {
        return true;
    }

    default long put(double d, long j) {
        throw new UnsupportedOperationException();
    }

    default long remove(double d) {
        throw new UnsupportedOperationException();
    }
}
