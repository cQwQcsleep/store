package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Int2BooleanFunction extends Function<Integer, Boolean>, java.util.function.IntPredicate {
    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<Integer, T> andThen(java.util.function.Function<? super Boolean, ? extends T> function) {
        return super.andThen(function);
    }

    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<T, Boolean> compose(java.util.function.Function<? super T, ? extends Integer> function) {
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

    default boolean defaultReturnValue() {
        return false;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default Boolean get(Object obj) {
        if (obj == null) {
            return null;
        }
        int iIntValue = ((Integer) obj).intValue();
        boolean z = get(iIntValue);
        if (z != defaultReturnValue() || containsKey(iIntValue)) {
            return Boolean.valueOf(z);
        }
        return null;
    }

    boolean get(int i);

    @Deprecated
    default Boolean put(Integer num, Boolean bool) {
        int iIntValue = num.intValue();
        boolean zContainsKey = containsKey(iIntValue);
        boolean zPut = put(iIntValue, bool.booleanValue());
        if (zContainsKey) {
            return Boolean.valueOf(zPut);
        }
        return null;
    }

    @Deprecated
    default Boolean remove(Object obj) {
        if (obj == null) {
            return null;
        }
        int iIntValue = ((Integer) obj).intValue();
        if (containsKey(iIntValue)) {
            return Boolean.valueOf(remove(iIntValue));
        }
        return null;
    }

    @Override // java.util.function.IntPredicate
    default boolean test(int i) {
        return get(i);
    }

    default boolean containsKey(int i) {
        return true;
    }

    default boolean put(int i, boolean z) {
        throw new UnsupportedOperationException();
    }

    default boolean remove(int i) {
        throw new UnsupportedOperationException();
    }
}
