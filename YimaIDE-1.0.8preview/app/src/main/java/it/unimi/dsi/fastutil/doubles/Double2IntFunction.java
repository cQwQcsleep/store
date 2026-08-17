package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.Function;
import java.util.function.DoubleToIntFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Double2IntFunction extends Function<Double, Integer>, DoubleToIntFunction {
    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<Double, T> andThen(java.util.function.Function<? super Integer, ? extends T> function) {
        return super.andThen(function);
    }

    @Override // java.util.function.DoubleToIntFunction
    default int applyAsInt(double d) {
        return get(d);
    }

    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<T, Integer> compose(java.util.function.Function<? super T, ? extends Double> function) {
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

    default int defaultReturnValue() {
        return 0;
    }

    int get(double d);

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default Integer get(Object obj) {
        if (obj == null) {
            return null;
        }
        double dDoubleValue = ((Double) obj).doubleValue();
        int i = get(dDoubleValue);
        if (i != defaultReturnValue() || containsKey(dDoubleValue)) {
            return Integer.valueOf(i);
        }
        return null;
    }

    @Deprecated
    default Integer put(Double d, Integer num) {
        double dDoubleValue = d.doubleValue();
        boolean zContainsKey = containsKey(dDoubleValue);
        int iPut = put(dDoubleValue, num.intValue());
        if (zContainsKey) {
            return Integer.valueOf(iPut);
        }
        return null;
    }

    @Deprecated
    default Integer remove(Object obj) {
        if (obj == null) {
            return null;
        }
        double dDoubleValue = ((Double) obj).doubleValue();
        if (containsKey(dDoubleValue)) {
            return Integer.valueOf(remove(dDoubleValue));
        }
        return null;
    }

    default boolean containsKey(double d) {
        return true;
    }

    default int put(double d, int i) {
        throw new UnsupportedOperationException();
    }

    default int remove(double d) {
        throw new UnsupportedOperationException();
    }
}
