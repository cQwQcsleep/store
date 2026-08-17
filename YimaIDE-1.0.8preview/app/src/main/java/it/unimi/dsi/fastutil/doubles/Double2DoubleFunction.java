package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Double2DoubleFunction extends Function<Double, Double>, java.util.function.DoubleUnaryOperator {
    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<Double, T> andThen(java.util.function.Function<? super Double, ? extends T> function) {
        return super.andThen(function);
    }

    @Override // java.util.function.DoubleUnaryOperator
    default double applyAsDouble(double d) {
        return get(d);
    }

    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<T, Double> compose(java.util.function.Function<? super T, ? extends Double> function) {
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

    default double defaultReturnValue() {
        return 0.0d;
    }

    double get(double d);

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default Double get(Object obj) {
        if (obj == null) {
            return null;
        }
        double dDoubleValue = ((Double) obj).doubleValue();
        double d = get(dDoubleValue);
        if (d != defaultReturnValue() || containsKey(dDoubleValue)) {
            return Double.valueOf(d);
        }
        return null;
    }

    @Deprecated
    default Double put(Double d, Double d2) {
        double dDoubleValue = d.doubleValue();
        boolean zContainsKey = containsKey(dDoubleValue);
        double dPut = put(dDoubleValue, d2.doubleValue());
        if (zContainsKey) {
            return Double.valueOf(dPut);
        }
        return null;
    }

    @Deprecated
    default Double remove(Object obj) {
        if (obj == null) {
            return null;
        }
        double dDoubleValue = ((Double) obj).doubleValue();
        if (containsKey(dDoubleValue)) {
            return Double.valueOf(remove(dDoubleValue));
        }
        return null;
    }

    default boolean containsKey(double d) {
        return true;
    }

    default double put(double d, double d2) {
        throw new UnsupportedOperationException();
    }

    default double remove(double d) {
        throw new UnsupportedOperationException();
    }
}
