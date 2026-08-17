package com.google.common.cache;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public interface Weigher<K, V> {
    int weigh(K k, V v);
}
