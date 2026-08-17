package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Reference2ObjectFunction<K, V> extends Function<K, V> {
    @Override // it.unimi.dsi.fastutil.Function
    V get(Object obj);

    default V put(K k, V v) {
        throw new UnsupportedOperationException();
    }

    default V remove(Object obj) {
        throw new UnsupportedOperationException();
    }
}
