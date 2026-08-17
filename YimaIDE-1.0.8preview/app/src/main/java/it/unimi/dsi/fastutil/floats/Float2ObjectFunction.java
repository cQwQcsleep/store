package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.Function;
import it.unimi.dsi.fastutil.SafeMath;
import java.util.function.DoubleFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Float2ObjectFunction<V> extends Function<Float, V>, DoubleFunction<V> {
    @Override // java.util.function.DoubleFunction
    @Deprecated
    default V apply(double d) {
        return get(SafeMath.safeDoubleToFloat(d));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<T, V> compose(java.util.function.Function<? super T, ? extends Float> function) {
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

    default V defaultReturnValue() {
        return null;
    }

    V get(float f);

    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default V get(Object obj) {
        if (obj == null) {
            return null;
        }
        float fFloatValue = ((Float) obj).floatValue();
        V v = get(fFloatValue);
        if (v != defaultReturnValue() || containsKey(fFloatValue)) {
            return v;
        }
        return null;
    }

    @Deprecated
    default V put(Float f, V v) {
        float fFloatValue = f.floatValue();
        boolean zContainsKey = containsKey(fFloatValue);
        V vPut = put(fFloatValue, v);
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
        float fFloatValue = ((Float) obj).floatValue();
        if (containsKey(fFloatValue)) {
            return remove(fFloatValue);
        }
        return null;
    }

    default boolean containsKey(float f) {
        return true;
    }

    default V put(float f, V v) {
        throw new UnsupportedOperationException();
    }

    default V remove(float f) {
        throw new UnsupportedOperationException();
    }
}
