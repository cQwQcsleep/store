package com.google.common.cache;

import com.google.common.collect.ImmutableMap;
import com.google.common.util.concurrent.UncheckedExecutionException;
import java.util.LinkedHashMap;
import java.util.concurrent.ExecutionException;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public abstract class AbstractLoadingCache<K, V> extends AbstractCache<K, V> implements LoadingCache<K, V> {
    @Override // com.google.common.cache.LoadingCache, com.google.common.base.Function
    public final V apply(K k) {
        return getUnchecked(k);
    }

    @Override // com.google.common.cache.LoadingCache
    public ImmutableMap<K, V> getAll(Iterable<? extends K> iterable) throws ExecutionException {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (K k : iterable) {
            if (!linkedHashMap.containsKey(k)) {
                linkedHashMap.put(k, get(k));
            }
        }
        return ImmutableMap.copyOf(linkedHashMap);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.common.util.concurrent.UncheckedExecutionException */
    @Override // com.google.common.cache.LoadingCache
    public V getUnchecked(K k) throws UncheckedExecutionException {
        try {
            return get(k);
        } catch (ExecutionException e) {
            throw new UncheckedExecutionException(e.getCause());
        }
    }

    @Override // com.google.common.cache.LoadingCache
    public void refresh(K k) {
        throw new UnsupportedOperationException();
    }
}
