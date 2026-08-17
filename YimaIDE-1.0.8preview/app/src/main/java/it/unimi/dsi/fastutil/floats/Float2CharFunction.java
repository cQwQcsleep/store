package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.Function;
import it.unimi.dsi.fastutil.SafeMath;
import java.util.function.DoubleToIntFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Float2CharFunction extends Function<Float, Character>, DoubleToIntFunction {
    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<Float, T> andThen(java.util.function.Function<? super Character, ? extends T> function) {
        return super.andThen(function);
    }

    @Override // java.util.function.DoubleToIntFunction
    @Deprecated
    default int applyAsInt(double d) {
        return get(SafeMath.safeDoubleToFloat(d));
    }

    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<T, Character> compose(java.util.function.Function<? super T, ? extends Float> function) {
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

    default char defaultReturnValue() {
        return (char) 0;
    }

    char get(float f);

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default Character get(Object obj) {
        if (obj == null) {
            return null;
        }
        float fFloatValue = ((Float) obj).floatValue();
        char c = get(fFloatValue);
        if (c != defaultReturnValue() || containsKey(fFloatValue)) {
            return Character.valueOf(c);
        }
        return null;
    }

    @Deprecated
    default Character put(Float f, Character ch) {
        float fFloatValue = f.floatValue();
        boolean zContainsKey = containsKey(fFloatValue);
        char cPut = put(fFloatValue, ch.charValue());
        if (zContainsKey) {
            return Character.valueOf(cPut);
        }
        return null;
    }

    @Deprecated
    default Character remove(Object obj) {
        if (obj == null) {
            return null;
        }
        float fFloatValue = ((Float) obj).floatValue();
        if (containsKey(fFloatValue)) {
            return Character.valueOf(remove(fFloatValue));
        }
        return null;
    }

    default boolean containsKey(float f) {
        return true;
    }

    default char put(float f, char c) {
        throw new UnsupportedOperationException();
    }

    default char remove(float f) {
        throw new UnsupportedOperationException();
    }
}
