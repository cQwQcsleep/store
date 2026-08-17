package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.Function;
import it.unimi.dsi.fastutil.SafeMath;
import java.util.function.IntUnaryOperator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Byte2ByteFunction extends Function<Byte, Byte>, IntUnaryOperator {
    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<Byte, T> andThen(java.util.function.Function<? super Byte, ? extends T> function) {
        return super.andThen(function);
    }

    @Override // java.util.function.IntUnaryOperator
    @Deprecated
    default int applyAsInt(int i) {
        return get(SafeMath.safeIntToByte(i));
    }

    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<T, Byte> compose(java.util.function.Function<? super T, ? extends Byte> function) {
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

    default byte defaultReturnValue() {
        return (byte) 0;
    }

    byte get(byte b);

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default Byte get(Object obj) {
        if (obj == null) {
            return null;
        }
        byte bByteValue = ((Byte) obj).byteValue();
        byte b = get(bByteValue);
        if (b != defaultReturnValue() || containsKey(bByteValue)) {
            return Byte.valueOf(b);
        }
        return null;
    }

    @Deprecated
    default Byte put(Byte b, Byte b2) {
        byte bByteValue = b.byteValue();
        boolean zContainsKey = containsKey(bByteValue);
        byte bPut = put(bByteValue, b2.byteValue());
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
        byte bByteValue = ((Byte) obj).byteValue();
        if (containsKey(bByteValue)) {
            return Byte.valueOf(remove(bByteValue));
        }
        return null;
    }

    default boolean containsKey(byte b) {
        return true;
    }

    default byte put(byte b, byte b2) {
        throw new UnsupportedOperationException();
    }

    default byte remove(byte b) {
        throw new UnsupportedOperationException();
    }
}
