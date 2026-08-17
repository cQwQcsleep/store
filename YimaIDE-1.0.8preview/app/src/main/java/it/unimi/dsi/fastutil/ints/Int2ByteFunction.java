package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Int2ByteFunction extends Function<Integer, Byte>, java.util.function.IntUnaryOperator {
    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<Integer, T> andThen(java.util.function.Function<? super Byte, ? extends T> function) {
        return super.andThen(function);
    }

    @Override // java.util.function.IntUnaryOperator
    default int applyAsInt(int i) {
        return get(i);
    }

    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<T, Byte> compose(java.util.function.Function<? super T, ? extends Integer> function) {
        return super.compose(function);
    }

    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default boolean containsKey(Object obj) {
        if (obj == null) {
            return false;
        }
        return containsKey(((Integer) obj).intValue());
    }

    default byte defaultReturnValue() {
        return (byte) 0;
    }

    byte get(int i);

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default Byte get(Object obj) {
        if (obj == null) {
            return null;
        }
        int iIntValue = ((Integer) obj).intValue();
        byte b = get(iIntValue);
        if (b != defaultReturnValue() || containsKey(iIntValue)) {
            return Byte.valueOf(b);
        }
        return null;
    }

    @Deprecated
    default Byte put(Integer num, Byte b) {
        int iIntValue = num.intValue();
        boolean zContainsKey = containsKey(iIntValue);
        byte bPut = put(iIntValue, b.byteValue());
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
        int iIntValue = ((Integer) obj).intValue();
        if (containsKey(iIntValue)) {
            return Byte.valueOf(remove(iIntValue));
        }
        return null;
    }

    default boolean containsKey(int i) {
        return true;
    }

    default byte put(int i, byte b) {
        throw new UnsupportedOperationException();
    }

    default byte remove(int i) {
        throw new UnsupportedOperationException();
    }
}
