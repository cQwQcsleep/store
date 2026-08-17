package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.Function;
import it.unimi.dsi.fastutil.SafeMath;
import java.util.function.IntToDoubleFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Byte2DoubleFunction extends Function<Byte, Double>, IntToDoubleFunction {
    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<Byte, T> andThen(java.util.function.Function<? super Double, ? extends T> function) {
        return super.andThen(function);
    }

    @Override // java.util.function.IntToDoubleFunction
    @Deprecated
    default double applyAsDouble(int i) {
        return get(SafeMath.safeIntToByte(i));
    }

    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<T, Double> compose(java.util.function.Function<? super T, ? extends Byte> function) {
        return super.compose(function);
    }

    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default boolean containsKey(Object obj) {
        if (obj == null) {
            return false;
        }
        return containsKey(((Byte) obj).byteValue());
    }

    default double defaultReturnValue() {
        return 0.0d;
    }

    double get(byte b);

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default Double get(Object obj) {
        if (obj == null) {
            return null;
        }
        byte bByteValue = ((Byte) obj).byteValue();
        double d = get(bByteValue);
        if (d != defaultReturnValue() || containsKey(bByteValue)) {
            return Double.valueOf(d);
        }
        return null;
    }

    @Deprecated
    default Double put(Byte b, Double d) {
        byte bByteValue = b.byteValue();
        boolean zContainsKey = containsKey(bByteValue);
        double dPut = put(bByteValue, d.doubleValue());
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
        byte bByteValue = ((Byte) obj).byteValue();
        if (containsKey(bByteValue)) {
            return Double.valueOf(remove(bByteValue));
        }
        return null;
    }

    default boolean containsKey(byte b) {
        return true;
    }

    default double put(byte b, double d) {
        throw new UnsupportedOperationException();
    }

    default double remove(byte b) {
        throw new UnsupportedOperationException();
    }
}
