package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.Function;
import it.unimi.dsi.fastutil.SafeMath;
import java.util.function.DoubleUnaryOperator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Float2FloatFunction extends Function<Float, Float>, DoubleUnaryOperator {
    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<Float, T> andThen(java.util.function.Function<? super Float, ? extends T> function) {
        return super.andThen(function);
    }

    @Override // java.util.function.DoubleUnaryOperator
    @Deprecated
    default double applyAsDouble(double d) {
        return get(SafeMath.safeDoubleToFloat(d));
    }

    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<T, Float> compose(java.util.function.Function<? super T, ? extends Float> function) {
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

    default float defaultReturnValue() {
        return 0.0f;
    }

    float get(float f);

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default Float get(Object obj) {
        if (obj == null) {
            return null;
        }
        float fFloatValue = ((Float) obj).floatValue();
        float f = get(fFloatValue);
        if (f != defaultReturnValue() || containsKey(fFloatValue)) {
            return Float.valueOf(f);
        }
        return null;
    }

    @Deprecated
    default Float put(Float f, Float f2) {
        float fFloatValue = f.floatValue();
        boolean zContainsKey = containsKey(fFloatValue);
        float fPut = put(fFloatValue, f2.floatValue());
        if (zContainsKey) {
            return Float.valueOf(fPut);
        }
        return null;
    }

    @Deprecated
    default Float remove(Object obj) {
        if (obj == null) {
            return null;
        }
        float fFloatValue = ((Float) obj).floatValue();
        if (containsKey(fFloatValue)) {
            return Float.valueOf(remove(fFloatValue));
        }
        return null;
    }

    default boolean containsKey(float f) {
        return true;
    }

    default float put(float f, float f2) {
        throw new UnsupportedOperationException();
    }

    default float remove(float f) {
        throw new UnsupportedOperationException();
    }
}
