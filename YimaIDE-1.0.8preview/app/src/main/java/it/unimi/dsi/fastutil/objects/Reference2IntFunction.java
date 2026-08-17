package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.Function;
import java.util.function.ToIntFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Reference2IntFunction<K> extends Function<K, Integer>, ToIntFunction<K> {
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<K, T> andThen(java.util.function.Function<? super Integer, ? extends T> function) {
        return super.andThen(function);
    }

    @Override // java.util.function.ToIntFunction
    default int applyAsInt(K k) {
        return getInt(k);
    }

    default int defaultReturnValue() {
        return 0;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default Integer get(Object obj) {
        int i = getInt(obj);
        if (i != defaultReturnValue() || containsKey(obj)) {
            return Integer.valueOf(i);
        }
        return null;
    }

    int getInt(Object obj);

    @Deprecated
    default Integer put(K k, Integer num) {
        boolean zContainsKey = containsKey(k);
        int iPut = put(k, num.intValue());
        if (zContainsKey) {
            return Integer.valueOf(iPut);
        }
        return null;
    }

    @Deprecated
    default Integer remove(Object obj) {
        if (containsKey(obj)) {
            return Integer.valueOf(removeInt(obj));
        }
        return null;
    }

    default int removeInt(Object obj) {
        throw new UnsupportedOperationException();
    }

    default int put(K k, int i) {
        throw new UnsupportedOperationException();
    }
}
