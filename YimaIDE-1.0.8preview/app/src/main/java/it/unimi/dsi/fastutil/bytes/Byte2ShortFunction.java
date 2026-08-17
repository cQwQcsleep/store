package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.Function;
import it.unimi.dsi.fastutil.SafeMath;
import java.util.function.IntUnaryOperator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Byte2ShortFunction extends Function<Byte, Short>, IntUnaryOperator {
    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<Byte, T> andThen(java.util.function.Function<? super Short, ? extends T> function) {
        return super.andThen(function);
    }

    @Override // java.util.function.IntUnaryOperator
    @Deprecated
    default int applyAsInt(int i) {
        return get(SafeMath.safeIntToByte(i));
    }

    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<T, Short> compose(java.util.function.Function<? super T, ? extends Byte> function) {
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

    default short defaultReturnValue() {
        return (short) 0;
    }

    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default Short get(Object obj) {
        if (obj == null) {
            return null;
        }
        byte bByteValue = ((Byte) obj).byteValue();
        short s = get(bByteValue);
        if (s != defaultReturnValue() || containsKey(bByteValue)) {
            return Short.valueOf(s);
        }
        return null;
    }

    short get(byte b);

    @Deprecated
    default Short put(Byte b, Short sh) {
        byte bByteValue = b.byteValue();
        boolean zContainsKey = containsKey(bByteValue);
        short sPut = put(bByteValue, sh.shortValue());
        if (zContainsKey) {
            return Short.valueOf(sPut);
        }
        return null;
    }

    @Deprecated
    default Short remove(Object obj) {
        if (obj == null) {
            return null;
        }
        byte bByteValue = ((Byte) obj).byteValue();
        if (containsKey(bByteValue)) {
            return Short.valueOf(remove(bByteValue));
        }
        return null;
    }

    default boolean containsKey(byte b) {
        return true;
    }

    default short put(byte b, short s) {
        throw new UnsupportedOperationException();
    }

    default short remove(byte b) {
        throw new UnsupportedOperationException();
    }
}
