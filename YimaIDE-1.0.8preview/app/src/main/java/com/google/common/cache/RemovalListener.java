package com.google.common.cache;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public interface RemovalListener<K, V> {
    void onRemoval(RemovalNotification<K, V> removalNotification);
}
