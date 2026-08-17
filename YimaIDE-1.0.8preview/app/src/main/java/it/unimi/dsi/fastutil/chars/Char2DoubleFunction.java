package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.Function;
import it.unimi.dsi.fastutil.SafeMath;
import java.util.function.IntToDoubleFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Char2DoubleFunction extends Function<Character, Double>, IntToDoubleFunction {
    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<Character, T> andThen(java.util.function.Function<? super Double, ? extends T> function) {
        return super.andThen(function);
    }

    @Override // java.util.function.IntToDoubleFunction
    @Deprecated
    default double applyAsDouble(int i) {
        return get(SafeMath.safeIntToChar(i));
    }

    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<T, Double> compose(java.util.function.Function<? super T, ? extends Character> function) {
        return super.compose(function);
    }

    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default boolean containsKey(Object obj) {
        if (obj == null) {
            return false;
        }
        return containsKey(((Character) obj).charValue());
    }

    default double defaultReturnValue() {
        return 0.0d;
    }

    double get(char c);

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default Double get(Object obj) {
        if (obj == null) {
            return null;
        }
        char cCharValue = ((Character) obj).charValue();
        double d = get(cCharValue);
        if (d != defaultReturnValue() || containsKey(cCharValue)) {
            return Double.valueOf(d);
        }
        return null;
    }

    @Deprecated
    default Double put(Character ch, Double d) {
        char cCharValue = ch.charValue();
        boolean zContainsKey = containsKey(cCharValue);
        double dPut = put(cCharValue, d.doubleValue());
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
        char cCharValue = ((Character) obj).charValue();
        if (containsKey(cCharValue)) {
            return Double.valueOf(remove(cCharValue));
        }
        return null;
    }

    default boolean containsKey(char c) {
        return true;
    }

    default double put(char c, double d) {
        throw new UnsupportedOperationException();
    }

    default double remove(char c) {
        throw new UnsupportedOperationException();
    }
}
