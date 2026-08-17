package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.Function;
import java.util.function.DoubleToIntFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Double2ByteFunction extends Function<Double, Byte>, DoubleToIntFunction {
    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<Double, T> andThen(java.util.function.Function<? super Byte, ? extends T> function) {
        return super.andThen(function);
    }

    @Override // java.util.function.DoubleToIntFunction
    default int applyAsInt(double d) {
        return get(d);
    }

    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<T, Byte> compose(java.util.function.Function<? super T, ? extends Double> function) {
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

    default byte defaultReturnValue() {
        return (byte) 0;
    }

    byte get(double d);

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default Byte get(Object obj) {
        if (obj == null) {
            return null;
        }
        double dDoubleValue = ((Double) obj).doubleValue();
        byte b = get(dDoubleValue);
        if (b != defaultReturnValue() || containsKey(dDoubleValue)) {
            return Byte.valueOf(b);
        }
        return null;
    }

    @Deprecated
    default Byte put(Double d, Byte b) {
        double dDoubleValue = d.doubleValue();
        boolean zContainsKey = containsKey(dDoubleValue);
        byte bPut = put(dDoubleValue, b.byteValue());
        if (zContainsKey) {
            return Byte.valueOf(bPut);
        }
        return null;
    }

    @Deprecated
    default Byte remove(Object obj) {
        if (obj == null) {
            return null;
        }
        double dDoubleValue = ((Double) obj).doubleValue();
        if (containsKey(dDoubleValue)) {
            return Byte.valueOf(remove(dDoubleValue));
        }
        return null;
    }

    default boolean containsKey(double d) {
        return true;
    }

    default byte put(double d, byte b) {
        throw new UnsupportedOperationException();
    }

    default byte remove(double d) {
        throw new UnsupportedOperationException();
    }
}
