package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.Function;
import it.unimi.dsi.fastutil.SafeMath;
import java.util.function.DoubleToLongFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Float2LongFunction extends Function<Float, Long>, DoubleToLongFunction {
    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<Float, T> andThen(java.util.function.Function<? super Long, ? extends T> function) {
        return super.andThen(function);
    }

    @Override // java.util.function.DoubleToLongFunction
    @Deprecated
    default long applyAsLong(double d) {
        return get(SafeMath.safeDoubleToFloat(d));
    }

    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<T, Long> compose(java.util.function.Function<? super T, ? extends Float> function) {
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

    default long defaultReturnValue() {
        return 0L;
    }

    long get(float f);

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default Long get(Object obj) {
        if (obj == null) {
            return null;
        }
        float fFloatValue = ((Float) obj).floatValue();
        long j = get(fFloatValue);
        if (j != defaultReturnValue() || containsKey(fFloatValue)) {
            return Long.valueOf(j);
        }
        return null;
    }

    @Deprecated
    default Long put(Float f, Long l) {
        float fFloatValue = f.floatValue();
        boolean zContainsKey = containsKey(fFloatValue);
        long jPut = put(fFloatValue, l.longValue());
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
        float fFloatValue = ((Float) obj).floatValue();
        if (containsKey(fFloatValue)) {
            return Long.valueOf(remove(fFloatValue));
        }
        return null;
    }

    default boolean containsKey(float f) {
        return true;
    }

    default long put(float f, long j) {
        throw new UnsupportedOperationException();
    }

    default long remove(float f) {
        throw new UnsupportedOperationException();
    }
}
