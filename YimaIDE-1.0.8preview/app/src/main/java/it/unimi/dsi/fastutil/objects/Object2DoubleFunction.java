package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.Function;
import java.util.function.ToDoubleFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Object2DoubleFunction<K> extends Function<K, Double>, ToDoubleFunction<K> {
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<K, T> andThen(java.util.function.Function<? super Double, ? extends T> function) {
        return super.andThen(function);
    }

    @Override // java.util.function.ToDoubleFunction
    default double applyAsDouble(K k) {
        return getDouble(k);
    }

    default double defaultReturnValue() {
        return 0.0d;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default Double get(Object obj) {
        double d = getDouble(obj);
        if (d != defaultReturnValue() || containsKey(obj)) {
            return Double.valueOf(d);
        }
        return null;
    }

    double getDouble(Object obj);

    @Deprecated
    default Double put(K k, Double d) {
        boolean zContainsKey = containsKey(k);
        double dPut = put(k, d.doubleValue());
        if (zContainsKey) {
            return Double.valueOf(dPut);
        }
        return null;
    }

    @Deprecated
    default Double remove(Object obj) {
        if (containsKey(obj)) {
            return Double.valueOf(removeDouble(obj));
        }
        return null;
    }

    default double removeDouble(Object obj) {
        throw new UnsupportedOperationException();
    }

    default double put(K k, double d) {
        throw new UnsupportedOperationException();
    }
}
