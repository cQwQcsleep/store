package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.Function;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Reference2BooleanFunction<K> extends Function<K, Boolean>, Predicate<K> {
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<K, T> andThen(java.util.function.Function<? super Boolean, ? extends T> function) {
        return super.andThen(function);
    }

    default boolean defaultReturnValue() {
        return false;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default Boolean get(Object obj) {
        boolean z = getBoolean(obj);
        if (z != defaultReturnValue() || containsKey(obj)) {
            return Boolean.valueOf(z);
        }
        return null;
    }

    boolean getBoolean(Object obj);

    @Deprecated
    default Boolean put(K k, Boolean bool) {
        boolean zContainsKey = containsKey(k);
        boolean zPut = put(k, bool.booleanValue());
        if (zContainsKey) {
            return Boolean.valueOf(zPut);
        }
        return null;
    }

    @Deprecated
    default Boolean remove(Object obj) {
        if (containsKey(obj)) {
            return Boolean.valueOf(removeBoolean(obj));
        }
        return null;
    }

    default boolean removeBoolean(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.function.Predicate
    default boolean test(K k) {
        return getBoolean(k);
    }

    default boolean put(K k, boolean z) {
        throw new UnsupportedOperationException();
    }
}
