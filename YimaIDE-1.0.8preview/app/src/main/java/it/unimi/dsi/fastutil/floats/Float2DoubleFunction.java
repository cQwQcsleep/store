package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.Function;
import it.unimi.dsi.fastutil.SafeMath;
import java.util.function.DoubleUnaryOperator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Float2DoubleFunction extends Function<Float, Double>, DoubleUnaryOperator {
    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<Float, T> andThen(java.util.function.Function<? super Double, ? extends T> function) {
        return super.andThen(function);
    }

    @Override // java.util.function.DoubleUnaryOperator
    @Deprecated
    default double applyAsDouble(double d) {
        return get(SafeMath.safeDoubleToFloat(d));
    }

    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<T, Double> compose(java.util.function.Function<? super T, ? extends Float> function) {
        return super.compose(function);
    }

    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default boolean containsKey(Object obj) {
        if (obj == null) {
            return false;
        }
        return containsKey(((Float) obj).floatValue());
    }

    default double defaultReturnValue() {
        return 0.0d;
    }

    double get(float f);

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default Double get(Object obj) {
        if (obj == null) {
            return null;
        }
        float fFloatValue = ((Float) obj).floatValue();
        double d = get(fFloatValue);
        if (d != defaultReturnValue() || containsKey(fFloatValue)) {
            return Double.valueOf(d);
        }
        return null;
    }

    @Deprecated
    default Double put(Float f, Double d) {
        float fFloatValue = f.floatValue();
        boolean zContainsKey = containsKey(fFloatValue);
        double dPut = put(fFloatValue, d.doubleValue());
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
        float fFloatValue = ((Float) obj).floatValue();
        if (containsKey(fFloatValue)) {
            return Double.valueOf(remove(fFloatValue));
        }
        return null;
    }

    default boolean containsKey(float f) {
        return true;
    }

    default double put(float f, double d) {
        throw new UnsupportedOperationException();
    }

    default double remove(float f) {
        throw new UnsupportedOperationException();
    }
}
