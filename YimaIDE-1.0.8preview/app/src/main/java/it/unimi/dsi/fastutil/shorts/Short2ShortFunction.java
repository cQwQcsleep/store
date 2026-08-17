package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.Function;
import it.unimi.dsi.fastutil.SafeMath;
import java.util.function.IntUnaryOperator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Short2ShortFunction extends Function<Short, Short>, IntUnaryOperator {
    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<Short, T> andThen(java.util.function.Function<? super Short, ? extends T> function) {
        return super.andThen(function);
    }

    @Override // java.util.function.IntUnaryOperator
    @Deprecated
    default int applyAsInt(int i) {
        return get(SafeMath.safeIntToShort(i));
    }

    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<T, Short> compose(java.util.function.Function<? super T, ? extends Short> function) {
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

    default short defaultReturnValue() {
        return (short) 0;
    }

    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default Short get(Object obj) {
        if (obj == null) {
            return null;
        }
        short sShortValue = ((Short) obj).shortValue();
        short s = get(sShortValue);
        if (s != defaultReturnValue() || containsKey(sShortValue)) {
            return Short.valueOf(s);
        }
        return null;
    }

    short get(short s);

    @Deprecated
    default Short put(Short sh, Short sh2) {
        short sShortValue = sh.shortValue();
        boolean zContainsKey = containsKey(sShortValue);
        short sPut = put(sShortValue, sh2.shortValue());
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
        short sShortValue = ((Short) obj).shortValue();
        if (containsKey(sShortValue)) {
            return Short.valueOf(remove(sShortValue));
        }
        return null;
    }

    default boolean containsKey(short s) {
        return true;
    }

    default short put(short s, short s2) {
        throw new UnsupportedOperationException();
    }

    default short remove(short s) {
        throw new UnsupportedOperationException();
    }
}
