package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.Function;
import it.unimi.dsi.fastutil.SafeMath;
import java.util.function.DoublePredicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Float2BooleanFunction extends Function<Float, Boolean>, DoublePredicate {
    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<Float, T> andThen(java.util.function.Function<? super Boolean, ? extends T> function) {
        return super.andThen(function);
    }

    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<T, Boolean> compose(java.util.function.Function<? super T, ? extends Float> function) {
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
        float fFloatValue = ((Float) obj).floatValue();
        boolean z = get(fFloatValue);
        if (z != defaultReturnValue() || containsKey(fFloatValue)) {
            return Boolean.valueOf(z);
        }
        return null;
    }

    boolean get(float f);

    @Deprecated
    default Boolean put(Float f, Boolean bool) {
        float fFloatValue = f.floatValue();
        boolean zContainsKey = containsKey(fFloatValue);
        boolean zPut = put(fFloatValue, bool.booleanValue());
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
        float fFloatValue = ((Float) obj).floatValue();
        if (containsKey(fFloatValue)) {
            return Boolean.valueOf(remove(fFloatValue));
        }
        return null;
    }

    @Override // java.util.function.DoublePredicate
    @Deprecated
    default boolean test(double d) {
        return get(SafeMath.safeDoubleToFloat(d));
    }

    default boolean containsKey(float f) {
        return true;
    }

    default boolean put(float f, boolean z) {
        throw new UnsupportedOperationException();
    }

    default boolean remove(float f) {
        throw new UnsupportedOperationException();
    }
}
