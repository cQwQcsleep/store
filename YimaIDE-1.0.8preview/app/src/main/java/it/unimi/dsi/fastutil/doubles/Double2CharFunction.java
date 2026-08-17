package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.Function;
import java.util.function.DoubleToIntFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Double2CharFunction extends Function<Double, Character>, DoubleToIntFunction {
    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<Double, T> andThen(java.util.function.Function<? super Character, ? extends T> function) {
        return super.andThen(function);
    }

    @Override // java.util.function.DoubleToIntFunction
    default int applyAsInt(double d) {
        return get(d);
    }

    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<T, Character> compose(java.util.function.Function<? super T, ? extends Double> function) {
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

    default char defaultReturnValue() {
        return (char) 0;
    }

    char get(double d);

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default Character get(Object obj) {
        if (obj == null) {
            return null;
        }
        double dDoubleValue = ((Double) obj).doubleValue();
        char c = get(dDoubleValue);
        if (c != defaultReturnValue() || containsKey(dDoubleValue)) {
            return Character.valueOf(c);
        }
        return null;
    }

    @Deprecated
    default Character put(Double d, Character ch) {
        double dDoubleValue = d.doubleValue();
        boolean zContainsKey = containsKey(dDoubleValue);
        char cPut = put(dDoubleValue, ch.charValue());
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
        double dDoubleValue = ((Double) obj).doubleValue();
        if (containsKey(dDoubleValue)) {
            return Character.valueOf(remove(dDoubleValue));
        }
        return null;
    }

    default boolean containsKey(double d) {
        return true;
    }

    default char put(double d, char c) {
        throw new UnsupportedOperationException();
    }

    default char remove(double d) {
        throw new UnsupportedOperationException();
    }
}
