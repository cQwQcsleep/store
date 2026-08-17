package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.Function;
import it.unimi.dsi.fastutil.SafeMath;
import java.util.function.IntToDoubleFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Short2FloatFunction extends Function<Short, Float>, IntToDoubleFunction {
    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<Short, T> andThen(java.util.function.Function<? super Float, ? extends T> function) {
        return super.andThen(function);
    }

    @Override // java.util.function.IntToDoubleFunction
    @Deprecated
    default double applyAsDouble(int i) {
        return get(SafeMath.safeIntToShort(i));
    }

    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<T, Float> compose(java.util.function.Function<? super T, ? extends Short> function) {
        return super.compose(function);
    }

    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default boolean containsKey(Object obj) {
        if (obj == null) {
            return false;
        }
        return containsKey(((Short) obj).shortValue());
    }

    default float defaultReturnValue() {
        return 0.0f;
    }

    float get(short s);

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default Float get(Object obj) {
        if (obj == null) {
            return null;
        }
        short sShortValue = ((Short) obj).shortValue();
        float f = get(sShortValue);
        if (f != defaultReturnValue() || containsKey(sShortValue)) {
            return Float.valueOf(f);
        }
        return null;
    }

    @Deprecated
    default Float put(Short sh, Float f) {
        short sShortValue = sh.shortValue();
        boolean zContainsKey = containsKey(sShortValue);
        float fPut = put(sShortValue, f.floatValue());
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
        short sShortValue = ((Short) obj).shortValue();
        if (containsKey(sShortValue)) {
            return Float.valueOf(remove(sShortValue));
        }
        return null;
    }

    default boolean containsKey(short s) {
        return true;
    }

    default float put(short s, float f) {
        throw new UnsupportedOperationException();
    }

    default float remove(short s) {
        throw new UnsupportedOperationException();
    }
}
