package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.Function;
import it.unimi.dsi.fastutil.SafeMath;
import java.util.function.IntToLongFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Short2LongFunction extends Function<Short, Long>, IntToLongFunction {
    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<Short, T> andThen(java.util.function.Function<? super Long, ? extends T> function) {
        return super.andThen(function);
    }

    @Override // java.util.function.IntToLongFunction
    @Deprecated
    default long applyAsLong(int i) {
        return get(SafeMath.safeIntToShort(i));
    }

    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<T, Long> compose(java.util.function.Function<? super T, ? extends Short> function) {
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

    default long defaultReturnValue() {
        return 0L;
    }

    long get(short s);

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default Long get(Object obj) {
        if (obj == null) {
            return null;
        }
        short sShortValue = ((Short) obj).shortValue();
        long j = get(sShortValue);
        if (j != defaultReturnValue() || containsKey(sShortValue)) {
            return Long.valueOf(j);
        }
        return null;
    }

    @Deprecated
    default Long put(Short sh, Long l) {
        short sShortValue = sh.shortValue();
        boolean zContainsKey = containsKey(sShortValue);
        long jPut = put(sShortValue, l.longValue());
        if (zContainsKey) {
            return Long.valueOf(jPut);
        }
        return null;
    }

    @Deprecated
    default Long remove(Object obj) {
        if (obj == null) {
            return null;
        }
        short sShortValue = ((Short) obj).shortValue();
        if (containsKey(sShortValue)) {
            return Long.valueOf(remove(sShortValue));
        }
        return null;
    }

    default boolean containsKey(short s) {
        return true;
    }

    default long put(short s, long j) {
        throw new UnsupportedOperationException();
    }

    default long remove(short s) {
        throw new UnsupportedOperationException();
    }
}
