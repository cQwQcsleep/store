package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Int2IntFunction extends Function<Integer, Integer>, java.util.function.IntUnaryOperator {
    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<Integer, T> andThen(java.util.function.Function<? super Integer, ? extends T> function) {
        return super.andThen(function);
    }

    @Override // java.util.function.IntUnaryOperator
    default int applyAsInt(int i) {
        return get(i);
    }

    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<T, Integer> compose(java.util.function.Function<? super T, ? extends Integer> function) {
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

    default int defaultReturnValue() {
        return 0;
    }

    int get(int i);

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default Integer get(Object obj) {
        if (obj == null) {
            return null;
        }
        int iIntValue = ((Integer) obj).intValue();
        int i = get(iIntValue);
        if (i != defaultReturnValue() || containsKey(iIntValue)) {
            return Integer.valueOf(i);
        }
        return null;
    }

    @Deprecated
    default Integer put(Integer num, Integer num2) {
        int iIntValue = num.intValue();
        boolean zContainsKey = containsKey(iIntValue);
        int iPut = put(iIntValue, num2.intValue());
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
        int iIntValue = ((Integer) obj).intValue();
        if (containsKey(iIntValue)) {
            return Integer.valueOf(remove(iIntValue));
        }
        return null;
    }

    default boolean containsKey(int i) {
        return true;
    }

    default int put(int i, int i2) {
        throw new UnsupportedOperationException();
    }

    default int remove(int i) {
        throw new UnsupportedOperationException();
    }
}
