package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.Function;
import java.util.function.LongToDoubleFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Long2FloatFunction extends Function<Long, Float>, LongToDoubleFunction {
    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<Long, T> andThen(java.util.function.Function<? super Float, ? extends T> function) {
        return super.andThen(function);
    }

    @Override // java.util.function.LongToDoubleFunction
    default double applyAsDouble(long j) {
        return get(j);
    }

    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<T, Float> compose(java.util.function.Function<? super T, ? extends Long> function) {
        return super.compose(function);
    }

    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default boolean containsKey(Object obj) {
        if (obj == null) {
            return false;
        }
        return containsKey(((Long) obj).longValue());
    }

    default float defaultReturnValue() {
        return 0.0f;
    }

    float get(long j);

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default Float get(Object obj) {
        if (obj == null) {
            return null;
        }
        long jLongValue = ((Long) obj).longValue();
        float f = get(jLongValue);
        if (f != defaultReturnValue() || containsKey(jLongValue)) {
            return Float.valueOf(f);
        }
        return null;
    }

    @Deprecated
    default Float put(Long l, Float f) {
        long jLongValue = l.longValue();
        boolean zContainsKey = containsKey(jLongValue);
        float fPut = put(jLongValue, f.floatValue());
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
        long jLongValue = ((Long) obj).longValue();
        if (containsKey(jLongValue)) {
            return Float.valueOf(remove(jLongValue));
        }
        return null;
    }

    default boolean containsKey(long j) {
        return true;
    }

    default float put(long j, float f) {
        throw new UnsupportedOperationException();
    }

    default float remove(long j) {
        throw new UnsupportedOperationException();
    }
}
