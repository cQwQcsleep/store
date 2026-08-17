package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.Function;
import it.unimi.dsi.fastutil.SafeMath;
import java.util.function.IntPredicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Short2BooleanFunction extends Function<Short, Boolean>, IntPredicate {
    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<Short, T> andThen(java.util.function.Function<? super Boolean, ? extends T> function) {
        return super.andThen(function);
    }

    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<T, Boolean> compose(java.util.function.Function<? super T, ? extends Short> function) {
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
        short sShortValue = ((Short) obj).shortValue();
        boolean z = get(sShortValue);
        if (z != defaultReturnValue() || containsKey(sShortValue)) {
            return Boolean.valueOf(z);
        }
        return null;
    }

    boolean get(short s);

    @Deprecated
    default Boolean put(Short sh, Boolean bool) {
        short sShortValue = sh.shortValue();
        boolean zContainsKey = containsKey(sShortValue);
        boolean zPut = put(sShortValue, bool.booleanValue());
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
        short sShortValue = ((Short) obj).shortValue();
        if (containsKey(sShortValue)) {
            return Boolean.valueOf(remove(sShortValue));
        }
        return null;
    }

    @Override // java.util.function.IntPredicate
    @Deprecated
    default boolean test(int i) {
        return get(SafeMath.safeIntToShort(i));
    }

    default boolean containsKey(short s) {
        return true;
    }

    default boolean put(short s, boolean z) {
        throw new UnsupportedOperationException();
    }

    default boolean remove(short s) {
        throw new UnsupportedOperationException();
    }
}
