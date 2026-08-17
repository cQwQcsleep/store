package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Int2ShortFunction extends Function<Integer, Short>, java.util.function.IntUnaryOperator {
    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<Integer, T> andThen(java.util.function.Function<? super Short, ? extends T> function) {
        return super.andThen(function);
    }

    @Override // java.util.function.IntUnaryOperator
    default int applyAsInt(int i) {
        return get(i);
    }

    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<T, Short> compose(java.util.function.Function<? super T, ? extends Integer> function) {
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

    default short defaultReturnValue() {
        return (short) 0;
    }

    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default Short get(Object obj) {
        if (obj == null) {
            return null;
        }
        int iIntValue = ((Integer) obj).intValue();
        short s = get(iIntValue);
        if (s != defaultReturnValue() || containsKey(iIntValue)) {
            return Short.valueOf(s);
        }
        return null;
    }

    short get(int i);

    @Deprecated
    default Short put(Integer num, Short sh) {
        int iIntValue = num.intValue();
        boolean zContainsKey = containsKey(iIntValue);
        short sPut = put(iIntValue, sh.shortValue());
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
        int iIntValue = ((Integer) obj).intValue();
        if (containsKey(iIntValue)) {
            return Short.valueOf(remove(iIntValue));
        }
        return null;
    }

    default boolean containsKey(int i) {
        return true;
    }

    default short put(int i, short s) {
        throw new UnsupportedOperationException();
    }

    default short remove(int i) {
        throw new UnsupportedOperationException();
    }
}
