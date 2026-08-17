package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.Function;
import it.unimi.dsi.fastutil.SafeMath;
import java.util.function.IntToDoubleFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Short2DoubleFunction extends Function<Short, Double>, IntToDoubleFunction {
    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<Short, T> andThen(java.util.function.Function<? super Double, ? extends T> function) {
        return super.andThen(function);
    }

    @Override // java.util.function.IntToDoubleFunction
    @Deprecated
    default double applyAsDouble(int i) {
        return get(SafeMath.safeIntToShort(i));
    }

    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<T, Double> compose(java.util.function.Function<? super T, ? extends Short> function) {
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

    default double defaultReturnValue() {
        return 0.0d;
    }

    double get(short s);

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default Double get(Object obj) {
        if (obj == null) {
            return null;
        }
        short sShortValue = ((Short) obj).shortValue();
        double d = get(sShortValue);
        if (d != defaultReturnValue() || containsKey(sShortValue)) {
            return Double.valueOf(d);
        }
        return null;
    }

    @Deprecated
    default Double put(Short sh, Double d) {
        short sShortValue = sh.shortValue();
        boolean zContainsKey = containsKey(sShortValue);
        double dPut = put(sShortValue, d.doubleValue());
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
        short sShortValue = ((Short) obj).shortValue();
        if (containsKey(sShortValue)) {
            return Double.valueOf(remove(sShortValue));
        }
        return null;
    }

    default boolean containsKey(short s) {
        return true;
    }

    default double put(short s, double d) {
        throw new UnsupportedOperationException();
    }

    default double remove(short s) {
        throw new UnsupportedOperationException();
    }
}
