package com.google.common.cache;

import com.google.common.base.Preconditions;
import com.google.common.collect.ForwardingObject;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public abstract class ForwardingCache<K, V> extends ForwardingObject implements Cache<K, V> {
    @Override // com.google.common.cache.Cache
    public ConcurrentMap<K, V> asMap() {
        return mo1872delegate().asMap();
    }

    @Override // com.google.common.cache.Cache
    public void cleanUp() {
        mo1872delegate().cleanUp();
    }

    @Override // 
    /* JADX INFO: renamed from: delegate */
    public abstract Cache<K, V> mo1872delegate();

    @Override // com.google.common.cache.Cache
    public V get(K k, Callable<? extends V> callable) throws ExecutionException {
        return mo1872delegate().get(k, callable);
    }

    @Override // com.google.common.cache.Cache
    public ImmutableMap<K, V> getAllPresent(Iterable<? extends Object> iterable) {
        return mo1872delegate().getAllPresent(iterable);
    }

    @Override // com.google.common.cache.Cache
    public V getIfPresent(Object obj) {
        return mo1872delegate().getIfPresent(obj);
    }

    @Override // com.google.common.cache.Cache
    public void invalidate(Object obj) {
        mo1872delegate().invalidate(obj);
    }

    @Override // com.google.common.cache.Cache
    public void invalidateAll(Iterable<? extends Object> iterable) {
        mo1872delegate().invalidateAll(iterable);
    }

    @Override // com.google.common.cache.Cache
    public void put(K k, V v) {
        mo1872delegate().put(k, v);
    }

    @Override // com.google.common.cache.Cache
    public void putAll(Map<? extends K, ? extends V> map) {
        mo1872delegate().putAll(map);
    }

    @Override // com.google.common.cache.Cache
    public long size() {
        return mo1872delegate().size();
    }

    @Override // com.google.common.cache.Cache
    public CacheStats stats() {
        return mo1872delegate().stats();
    }

    public static abstract class SimpleForwardingCache<K, V> extends ForwardingCache<K, V> {
        private final Cache<K, V> delegate;

        public SimpleForwardingCache(Cache<K, V> cache) {
            this.delegate = (Cache) Preconditions.checkNotNull(cache);
        }

        @Override // com.google.common.cache.ForwardingCache
        /* JADX INFO: renamed from: delegate, reason: merged with bridge method [inline-methods] */
        public final Cache<K, V> mo1872delegate() {
            return this.delegate;
        }
    }

    @Override // com.google.common.cache.Cache
    public void invalidateAll() {
        mo1872delegate().invalidateAll();
    }
}
