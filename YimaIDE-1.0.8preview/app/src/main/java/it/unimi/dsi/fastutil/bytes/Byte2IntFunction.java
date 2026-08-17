package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.Function;
import it.unimi.dsi.fastutil.SafeMath;
import java.util.function.IntUnaryOperator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Byte2IntFunction extends Function<Byte, Integer>, IntUnaryOperator {
    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<Byte, T> andThen(java.util.function.Function<? super Integer, ? extends T> function) {
        return super.andThen(function);
    }

    @Override // java.util.function.IntUnaryOperator
    @Deprecated
    default int applyAsInt(int i) {
        return get(SafeMath.safeIntToByte(i));
    }

    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<T, Integer> compose(java.util.function.Function<? super T, ? extends Byte> function) {
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

    default int defaultReturnValue() {
        return 0;
    }

    int get(byte b);

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default Integer get(Object obj) {
        if (obj == null) {
            return null;
        }
        byte bByteValue = ((Byte) obj).byteValue();
        int i = get(bByteValue);
        if (i != defaultReturnValue() || containsKey(bByteValue)) {
            return Integer.valueOf(i);
        }
        return null;
    }

    @Deprecated
    default Integer put(Byte b, Integer num) {
        byte bByteValue = b.byteValue();
        boolean zContainsKey = containsKey(bByteValue);
        int iPut = put(bByteValue, num.intValue());
        if (zContainsKey) {
            return Integer.valueOf(iPut);
        }
        return null;
    }

    @Deprecated
    default Integer remove(Object obj) {
        if (obj == null) {
            return null;
        }
        byte bByteValue = ((Byte) obj).byteValue();
        if (containsKey(bByteValue)) {
            return Integer.valueOf(remove(bByteValue));
        }
        return null;
    }

    default boolean containsKey(byte b) {
        return true;
    }

    default int put(byte b, int i) {
        throw new UnsupportedOperationException();
    }

    default int remove(byte b) {
        throw new UnsupportedOperationException();
    }
}
