package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.Function;
import java.util.function.DoubleFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Double2ReferenceFunction<V> extends Function<Double, V>, DoubleFunction<V> {
    @Override // java.util.function.DoubleFunction
    default V apply(double d) {
        return get(d);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<T, V> compose(java.util.function.Function<? super T, ? extends Double> function) {
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

    default V defaultReturnValue() {
        return null;
    }

    V get(double d);

    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default V get(Object obj) {
        if (obj == null) {
            return null;
        }
        double dDoubleValue = ((Double) obj).doubleValue();
        V v = get(dDoubleValue);
        if (v != defaultReturnValue() || containsKey(dDoubleValue)) {
            return v;
        }
        return null;
    }

    @Deprecated
    default V put(Double d, V v) {
        double dDoubleValue = d.doubleValue();
        boolean zContainsKey = containsKey(dDoubleValue);
        V vPut = put(dDoubleValue, v);
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
        double dDoubleValue = ((Double) obj).doubleValue();
        if (containsKey(dDoubleValue)) {
            return remove(dDoubleValue);
        }
        return null;
    }

    default boolean containsKey(double d) {
        return true;
    }

    default V put(double d, V v) {
        throw new UnsupportedOperationException();
    }

    default V remove(double d) {
        throw new UnsupportedOperationException();
    }
}
