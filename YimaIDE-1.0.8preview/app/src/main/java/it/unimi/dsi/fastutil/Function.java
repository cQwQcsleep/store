package it.unimi.dsi.fastutil;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Function<K, V> extends java.util.function.Function<K, V> {
    @Override // java.util.function.Function
    default V apply(K k) {
        return get(k);
    }

    default void clear() {
        throw new UnsupportedOperationException();
    }

    default boolean containsKey(Object obj) {
        return true;
    }

    V get(Object obj);

    default int size() {
        return -1;
    }
}
