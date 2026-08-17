package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Double2BooleanFunction extends Function<Double, Boolean>, java.util.function.DoublePredicate {
    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<Double, T> andThen(java.util.function.Function<? super Boolean, ? extends T> function) {
        return super.andThen(function);
    }

    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<T, Boolean> compose(java.util.function.Function<? super T, ? extends Double> function) {
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
        double dDoubleValue = ((Double) obj).doubleValue();
        boolean z = get(dDoubleValue);
        if (z != defaultReturnValue() || containsKey(dDoubleValue)) {
            return Boolean.valueOf(z);
        }
        return null;
    }

    boolean get(double d);

    @Deprecated
    default Boolean put(Double d, Boolean bool) {
        double dDoubleValue = d.doubleValue();
        boolean zContainsKey = containsKey(dDoubleValue);
        boolean zPut = put(dDoubleValue, bool.booleanValue());
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
        double dDoubleValue = ((Double) obj).doubleValue();
        if (containsKey(dDoubleValue)) {
            return Boolean.valueOf(remove(dDoubleValue));
        }
        return null;
    }

    @Override // java.util.function.DoublePredicate
    default boolean test(double d) {
        return get(d);
    }

    default boolean containsKey(double d) {
        return true;
    }

    default boolean put(double d, boolean z) {
        throw new UnsupportedOperationException();
    }

    default boolean remove(double d) {
        throw new UnsupportedOperationException();
    }
}
