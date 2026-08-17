package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.Function;
import java.util.function.ToLongFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Reference2LongFunction<K> extends Function<K, Long>, ToLongFunction<K> {
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<K, T> andThen(java.util.function.Function<? super Long, ? extends T> function) {
        return super.andThen(function);
    }

    @Override // java.util.function.ToLongFunction
    default long applyAsLong(K k) {
        return getLong(k);
    }

    default long defaultReturnValue() {
        return 0L;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default Long get(Object obj) {
        long j = getLong(obj);
        if (j != defaultReturnValue() || containsKey(obj)) {
            return Long.valueOf(j);
        }
        return null;
    }

    long getLong(Object obj);

    @Deprecated
    default Long put(K k, Long l) {
        boolean zContainsKey = containsKey(k);
        long jPut = put(k, l.longValue());
        if (zContainsKey) {
            return Long.valueOf(jPut);
        }
        return null;
    }

    @Deprecated
    default Long remove(Object obj) {
        if (containsKey(obj)) {
            return Long.valueOf(removeLong(obj));
        }
        return null;
    }

    default long removeLong(Object obj) {
        throw new UnsupportedOperationException();
    }

    default long put(K k, long j) {
        throw new UnsupportedOperationException();
    }
}
