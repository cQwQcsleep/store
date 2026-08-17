package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.Function;
import java.util.function.LongToIntFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Long2ByteFunction extends Function<Long, Byte>, LongToIntFunction {
    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<Long, T> andThen(java.util.function.Function<? super Byte, ? extends T> function) {
        return super.andThen(function);
    }

    @Override // java.util.function.LongToIntFunction
    default int applyAsInt(long j) {
        return get(j);
    }

    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<T, Byte> compose(java.util.function.Function<? super T, ? extends Long> function) {
        return super.compose(function);
    }

    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default boolean containsKey(Object obj) {
        if (obj == null) {
            return false;
        }
        return containsKey(((Long) obj).longValue());
    }

    default byte defaultReturnValue() {
        return (byte) 0;
    }

    byte get(long j);

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default Byte get(Object obj) {
        if (obj == null) {
            return null;
        }
        long jLongValue = ((Long) obj).longValue();
        byte b = get(jLongValue);
        if (b != defaultReturnValue() || containsKey(jLongValue)) {
            return Byte.valueOf(b);
        }
        return null;
    }

    @Deprecated
    default Byte put(Long l, Byte b) {
        long jLongValue = l.longValue();
        boolean zContainsKey = containsKey(jLongValue);
        byte bPut = put(jLongValue, b.byteValue());
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
        long jLongValue = ((Long) obj).longValue();
        if (containsKey(jLongValue)) {
            return Byte.valueOf(remove(jLongValue));
        }
        return null;
    }

    default boolean containsKey(long j) {
        return true;
    }

    default byte put(long j, byte b) {
        throw new UnsupportedOperationException();
    }

    default byte remove(long j) {
        throw new UnsupportedOperationException();
    }
}
