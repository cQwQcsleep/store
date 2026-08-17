package com.google.common.cache;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import java.util.concurrent.ExecutionException;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public abstract class ForwardingLoadingCache<K, V> extends ForwardingCache<K, V> implements LoadingCache<K, V> {
    @Override // com.google.common.cache.LoadingCache, com.google.common.base.Function
    public V apply(K k) {
        return mo1872delegate().apply(k);
    }

    @Override // com.google.common.cache.ForwardingCache
    /* JADX INFO: renamed from: delegate */
    public abstract LoadingCache<K, V> mo1872delegate();

    @Override // com.google.common.cache.LoadingCache
    public V get(K k) throws ExecutionException {
        return mo1872delegate().get(k);
    }

    @Override // com.google.common.cache.LoadingCache
    public ImmutableMap<K, V> getAll(Iterable<? extends K> iterable) throws ExecutionException {
        return mo1872delegate().getAll(iterable);
    }

    @Override // com.google.common.cache.LoadingCache
    public V getUnchecked(K k) {
        return mo1872delegate().getUnchecked(k);
    }

    @Override // com.google.common.cache.LoadingCache
    public void refresh(K k) {
        mo1872delegate().refresh(k);
    }

    public static abstract class SimpleForwardingLoadingCache<K, V> extends ForwardingLoadingCache<K, V> {
        private final LoadingCache<K, V> delegate;

        public SimpleForwardingLoadingCache(LoadingCache<K, V> loadingCache) {
            this.delegate = (LoadingCache) Preconditions.checkNotNull(loadingCache);
        }

        @Override // com.google.common.cache.ForwardingLoadingCache, com.google.common.cache.ForwardingCache
        /* JADX INFO: renamed from: delegate */
        public final LoadingCache<K, V> mo1872delegate() {
            return this.delegate;
        }
    }
}
