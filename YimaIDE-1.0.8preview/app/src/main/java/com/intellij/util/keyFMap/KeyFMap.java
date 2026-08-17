package com.intellij.util.keyFMap;

import com.intellij.openapi.util.Key;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public interface KeyFMap {
    public static final KeyFMap EMPTY_MAP = EmptyFMap.create();

    <V> V get(Key<V> key);

    Key<?>[] getKeys();

    boolean isEmpty();

    KeyFMap minus(Key<?> key);

    <V> KeyFMap plus(Key<V> key, V v);

    int size();
}
