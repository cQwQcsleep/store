package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.Function;
import java.util.function.ToIntFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Object2ShortFunction<K> extends Function<K, Short>, ToIntFunction<K> {
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<K, T> andThen(java.util.function.Function<? super Short, ? extends T> function) {
        return super.andThen(function);
    }

    @Override // java.util.function.ToIntFunction
    default int applyAsInt(K k) {
        return getShort(k);
    }

    default short defaultReturnValue() {
        return (short) 0;
    }

    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default Short get(Object obj) {
        short s = getShort(obj);
        if (s != defaultReturnValue() || containsKey(obj)) {
            return Short.valueOf(s);
        }
        return null;
    }

    short getShort(Object obj);

    @Deprecated
    default Short put(K k, Short sh) {
        boolean zContainsKey = containsKey(k);
        short sPut = put(k, sh.shortValue());
        if (zContainsKey) {
            return Short.valueOf(sPut);
        }
        return null;
    }

    @Deprecated
    default Short remove(Object obj) {
        if (containsKey(obj)) {
            return Short.valueOf(removeShort(obj));
        }
        return null;
    }

    default short removeShort(Object obj) {
        throw new UnsupportedOperationException();
    }

    default short put(K k, short s) {
        throw new UnsupportedOperationException();
    }
}
